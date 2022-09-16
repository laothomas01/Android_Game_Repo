package com.GameVersion2.game.driver;

import com.GameVersion2.game.Entities.*;
import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.GameVersion2.game.Util.Graphics2D;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.JsonValue;


public class GameStateManager extends ApplicationAdapter {


    // ----------------- GAME STATES --------------------------
    enum State {PAUSE, RUN, STOPPED}


    State state;

    //------------------------------------------------------


    //--------------------Handle Enemy and Enemy Spawner Data----------------
    int enemyWaveNumber;
    int maxEnemyWaves;
    int[] enemyTypes;
    float enemySpawnCoolDown;

    //------------------------------------------------------


    //---------------------------rendering time calculated between current and next frame----------------------------
    float deltaTime;
    //-------------------------------------------------------------------------------------------------------------------
    Player player;
    GameObjectManager entityManager = new GameObjectManager();

    //json values used for retrieving json data
    JsonValue jsonWaves;
    JsonValue jsonWave;

    //-------------------------------------------------

    //game event timer


    //seconds to wait before an event such as leveling up occurs
    private float eventTimeInSeconds = 0f;
    //max time to wait for an event to happen
    private float periodOfTimeSeconds = 15f;
    //max wait time testing variable for leveling up player
    float testTimeBeforeUpgrade = 0;
    float period = 5;


    //------------------------ TESTING ENEMY DETECTION ------------------------

    float detectionWaitTime = 0f;
    float enemyDetectionPeriod = 2f;

    //-------------------------------------------------------------------------

    public void create() {


        //initialize starting state

        state = State.RUN;

        /**
         * PLACE THESE VALUES INTO THE RENDER:
         * -increment enemy wave number after N amount of time passed.
         * -check when enemyWaveNumber passes maxNumber of waves. if passes, keep wave number at max
         * -make sure to check how much time has passed during runtime.
         */

        //------------------------------update in render-----------------------

        enemyWaveNumber = 0;
        jsonWaves = AppManager.loadJsonFile("entityData.json").get("waves");
        //size of the collection of enemy waves
        maxEnemyWaves = jsonWaves.size;
        //----------------------------------------------------------------------
        /**
         * Have player add in a basic skill when starting up the game
         */
        //on create, load up player's base stats


        player = new Player();
//        System.out.println("STARTING STATS:" + player.toString());

        //adding of skill
        Gdx.input.setInputProcessor(new GameInputProcessor());
        //see base stats first
    }

    /**
     * WRITING TEST CASES
     */

    //----------TESTING PLAYER LEVEL UP--------------------
    public void testPlayerLevelUp() {
//        player.setHasLeveledUp(true);
        if (player.HasLeveled()) {
            setGameState(State.PAUSE);
        } else {
            setGameState(State.RUN);
        }
    }

    public void testUpgradeScreen() {
        if (player.HasLeveled()) {


            Graphics2D.drawFontSprite("LEVELED UP!", AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2 + 50);
            Graphics2D.drawFontSprite("SELECT AN UPGRADE\n[A] +10 Size\n[S] +10 Speed\n[D] Change Color", AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2);
            if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A))) {
                System.out.println("SIZE!");
                player.setHasLeveledUp(false);
                player.getPhysics().setSpriteSize(player.getPhysics().getSpriteWidth() + 10, player.getPhysics().getSpriteHeight() + 10);
            } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S))) {
                System.out.println("SPEED!");
                player.getPhysics().setMoveSpeed(player.getPhysics().getMoveSpeed() + 100);
                player.setHasLeveledUp(false);
            } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D))) {
                System.out.println("COLOR!");
                player.getGraphics().setColor(Color.PINK);
                player.setHasLeveledUp(false);
            }
        }
    }

    public void testGradualUpgrades() {
        System.out.println("TIME BEFORE UPGRADE:" + testTimeBeforeUpgrade);
        System.out.println("LEVELED UP:" + player.HasLeveled());
        //level up every 5 seconds
        testTimeBeforeUpgrade += deltaTime;
        if (testTimeBeforeUpgrade > period) {
            testTimeBeforeUpgrade -= period;
            player.setHasLeveledUp(true);
        }

    }


    //------------------------------------------------------


    /**
     * Shoot at enemies
     * Detect enemies
     * Queue seen enemies
     * Handle bullet collision
     */
    public void testEntityAndPlayerInteraction() {

        //detect an enemy and put into a queue of detected enemies
        for (Entity e : entityManager.getEnemies()) {
            //handle player and enemy collision
            player.addSeenEnemy(e);
        }
        //get the first seen enemy from the queue storing seen enemies using a FIFO structure
        Entity currentlySeenEnemy = player.getSeenEnemies().peek();
        //data of currently seen enemy has to be present
        if (currentlySeenEnemy != null) {
            //if current enemy is seen,create a bullet with a direction towards that enemy
            if (player.detectEntity(currentlySeenEnemy)) {
                currentlySeenEnemy.getGraphics().setColor(Color.GREEN);
                player.shoot(currentlySeenEnemy, deltaTime, entityManager);
            } else {
                currentlySeenEnemy.getGraphics().setColor(Color.RED);
                // ------ checking if currently targeted enemy is out of player range  --------------
                detectionWaitTime += deltaTime;
                // ------ wait time exceed max wait time.
                if (detectionWaitTime > enemyDetectionPeriod) {
                    detectionWaitTime -= enemyDetectionPeriod;
                    currentlySeenEnemy.getGraphics().setColor(Color.RED);
                    //if you are out of range of the current enemy, just clear the queue and start over
                    player.getSeenEnemies().clear();
                    //bullets will contain direction vector data about currently seen enemy
                    //remove all traces of such bullets if enemy is not in range
                    //we do not want stray bullets
                    GameObjectManager.getProjectiles().clear();
                }
                //-------------------------------------------------------------------
            }
        }
        //update all projectile sprites with new data
        for (Entity p : GameObjectManager.getProjectiles()) {
            p.update(deltaTime);
            /**
             * If bullet collides with enemy, remove bullet and remove enemy
             */
            if (p.getPhysics().hasCollided(currentlySeenEnemy)) {
                p.getPhysics().setMoveSpeed(0);
                entityManager.getGarbageCollection().add(p);
                entityManager.getGarbageCollection().add(currentlySeenEnemy);
                //-----------------------drop exp object when an enemy object dies--------
                //an exp drop should have the same position as the currently eliminated enemy
                GameObjectManager.getExpDrops().add(new expDrop(currentlySeenEnemy.getPhysics().getPosition()));
                //------------------------------------------------------------------------
                player.getSeenEnemies().remove(currentlySeenEnemy);
            }
            /**
             * If bullet does not hit enemy but keeps going, delete object after a certain period of time
             */
            else if (p.lifeSpanExpired(deltaTime)) {
                entityManager.getGarbageCollection().add(p);
            }
        }

        //if player collides with exp drop object, increase player exp bar
        for (Entity exp : GameObjectManager.getExpDrops()) {
            exp.update(deltaTime);
            if (player.getPhysics().hasCollided(exp)) {
                entityManager.getGarbageCollection().add(exp);
                //if player collects an exp drop, gain exp.
            }
        }

        /**
         * GARBAGE COLLECTION RIGHT NOW IS UNSCALABLE!
         * FIND A WAY TO WRITE A METHOD TO HANDLE ALL OBJECT REMOVAL
         *
         * LOTS OF REPEATED CODE!
         */
        GameObjectManager.getExpDrops().removeAll(entityManager.getGarbageCollection(), false);
        //clear out all objects needing to be removed
        entityManager.getEnemies().removeAll(entityManager.getGarbageCollection(), false);
        GameObjectManager.getProjectiles().removeAll(entityManager.getGarbageCollection(), false);
        //after removing all objects, clear out the garbage
        entityManager.getGarbageCollection().clear();

    }
    //--------------------------------

    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //testing level up logic
        //level up => pause game => pick upgrade => persist stats in player object => print player data => dispose of font sprite =>  set game state to run => repeat if leveled up

        switch (state) {
            case RUN:
//                /**
//                 * GAME States:
//                 * -MENU/TITLE SCREEN
//                 * -GAMEPLAY State
//                 * -GAME OVER SCREEN State
//                 */
//
//                // calculaton of time difference between previous and current frame
//                //60 FPS  => 1 second / 60 frames = 0.0166 sec / 1 frame
//
//                //Update enemy waves
//                //increments time seconds and resets time seconds after reached period of time
//
//
//                //------------------------------------------------------------------------------
//
//                /**
//                 * TITLE SCREEN State
//                 * @TODO: implement title screen UI
//                 */
//
//
//                //----------------------------------------------
//
//                /**
//                 * GAME PLAY State
//                 * @TODO: move to gameplay screen
//                 */
//
//
//                /** PERFORM CREATE:
//                 *
//                 * C/CRUD - create enemy entities. add to arraylist
//                 *
//                 * spawning from a list of enemies should be randomized. currently, random function is TOO SLOW!
//                 */

                updateEnemyWave();


                for (int i = 0; i < enemyTypes.length; ++i) {
                    entityManager.spawnEnemies(deltaTime, enemyTypes[i], 5, enemySpawnCoolDown);
                }


                /**
                 *
                 *
                 * Update game entities:
                 *
                 * common entity updates:
                 * -collision
                 * -entity removal
                 * -status updates
                 *
                 * PLAYER - keyboard input
                 *
                 * ENEMIES
                 *
                 * PROJECTILES
                 *
                 * GARBAGE COLLECTION
                 */
                for (Entity e : entityManager.getEnemies()) {
                    //update all enemy objects
                    e.update(deltaTime);
                }

                testEntityAndPlayerInteraction();
                testPlayerLevelUp();
                testUpgradeScreen();

                //----------------------------------------------
                /**
                 * GAME OVER State
                 * @TODO: implement game over screen UI
                 */
                //----------------------------------------------
                player.update(deltaTime);
                handleMovementInputs();

            case PAUSE:
                /**
                 * If has leveled up, display interactable gui or in-game user-input to select an ability
                 */


                break;
            default:
                break;
        }


    }

    @Override
    public void resize(int width, int height) {

    }


    public void updateEnemyWave() {
        eventTimeInSeconds += deltaTime;
        if (eventTimeInSeconds > periodOfTimeSeconds) {
            eventTimeInSeconds -= periodOfTimeSeconds;
            //check if wave number passed max enemy waves
            //if not, change wave number
            //if, keep at max number of waves
            if (enemyWaveNumber < maxEnemyWaves - 1) {
                enemyWaveNumber++;
            } else {
                enemyWaveNumber = maxEnemyWaves - 1;
            }
        }

        jsonWave = jsonWaves.get(enemyWaveNumber);
        enemyTypes = jsonWave.get("enemyTypes").asIntArray();
        enemySpawnCoolDown = jsonWave.get("spawnCoolDown").asFloat();
    }


    //called when application is not running
    public void pause() {
        this.state = State.PAUSE;

    }


    //called when the Application is resumed from a paused state.
    @Override
    public void resume() {

        this.state = State.RUN;
    }

    public void dispose() {


        /**
         * WIll BE WRITING DATA TO FILE HERE
         */


    }

    public void setGameState(State s) {
        this.state = s;
    }


    private void handleMovementInputs() {

        if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.UP) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.W)))) {
            if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.UP) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.RIGHT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.W) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D))) {
                player.getPhysics().setDirectionVector(1, 1);

            } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.UP) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.LEFT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.W) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A))) {
                player.getPhysics().setDirectionVector(-1, 1);
            } else {
                player.getPhysics().setDirectionVector(0, 1);
            }
        } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.DOWN) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S)))) {
            if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.DOWN) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.RIGHT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D))) {
                player.getPhysics().setDirectionVector(1, -1);
            } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.DOWN) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.LEFT)) || (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S) && GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A))) {
                player.getPhysics().setDirectionVector(-1, -1);
            } else {
                player.getPhysics().setDirectionVector(0, -1);
            }
        } else if (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.LEFT) || GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A)) {
            player.getPhysics().setDirectionVector(-1, 0);
        } else if (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.RIGHT) || GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D)) {
            player.getPhysics().setDirectionVector(1, 0);
        } else {
            player.getPhysics().setDirectionVector(0, 0);
        }


        GameInputProcessor.GameKeys.update();
    }

}





