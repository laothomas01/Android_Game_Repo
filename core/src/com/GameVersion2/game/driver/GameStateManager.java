package com.GameVersion2.game.driver;

import com.GameVersion2.game.Entities.*;
import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.GameVersion2.game.Util.Graphics2D;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.JsonValue;


public class GameStateManager extends ApplicationAdapter {
    // ----------------- GAME STATES --------------------------
    enum State {PAUSE, RUN, STOPPED}

    State state;

    public State getGameState() {
        return state;
    }
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

//    ExpDrop exp;

    //------------------------ TESTING ENEMY DETECTION ------------------------

    float detectionWaitTime = 0f;
    float enemyDetectionPeriod = 3f;

    //-------------------------------------------------------------------------

    public void create() {

        //initialize starting state
//        exp = new ExpDrop();
//        exp.getPhysics().setPosition(AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2);


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
        player.setHasLeveledUp(true);
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
    public void testEnemyDetection() {
        //detect an enemy and put into a queue of detected enemies
        for (Entity e : entityManager.getEnemies()) {
            player.addSeenEnemy(e);
        }
        //get the first seen enemy from the queue storing seen enemies using a FIFO structure
        Entity currentlySeenEnemy = player.getSeenEnemies().peek();
        if (currentlySeenEnemy != null) {
            currentlySeenEnemy.getGraphics().setColor(Color.YELLOW);
            if (player.detectEntity(currentlySeenEnemy)) {
                //if current enemy seen, color it yellow
                currentlySeenEnemy.getGraphics().setColor(Color.GREEN);
                //player shoot
            }

//            else {
//                currentlySeenEnemy.getGraphics().setColor(Color.YELLOW);
//                //if current enemy is not seen, just color it back to normal
//                //begin timer if enemy not detected
//                detectionWaitTime += deltaTime;
//                if (detectionWaitTime > enemyDetectionPeriod) {
//                    detectionWaitTime -= enemyDetectionPeriod;
//                    //signify enemy is not current target anymore
//                    player.getSeenEnemies().remove(currentlySeenEnemy);
//                }
//            }
            player.shoot(currentlySeenEnemy, deltaTime, entityManager);
        }

        for (Entity p : GameObjectManager.getProjectiles()) {
            p.update(deltaTime);
            if (p.getPhysics().hasCollided(currentlySeenEnemy) || p.lifeSpanExpired(deltaTime)) {
                entityManager.getGarbageCollection().add(p);
            }
        }
        System.out.println(GameObjectManager.getProjectiles().toString());
        GameObjectManager.getProjectiles().removeAll(entityManager.getGarbageCollection(), false);
    }
    //--------------------------------


    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


//        exp.getGraphics().drawSprite();
//        exp.update(deltaTime);

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


                testEnemyDetection();

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





