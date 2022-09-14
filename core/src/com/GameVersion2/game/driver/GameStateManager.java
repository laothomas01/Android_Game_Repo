package com.GameVersion2.game.driver;

import com.GameVersion2.game.Entities.Enemy;
import com.GameVersion2.game.Entities.ExpDrop;
import com.GameVersion2.game.Entities.Projectile;
import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Entities.Player;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.GameVersion2.game.Util.Graphics2D;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.JsonValue;


/**
 * CRUD operations on the state of the game
 * <p>
 * -initializations, closing the app, running the app, pausing the app,
 */


public class GameStateManager extends ApplicationAdapter {
    // ----------------- GAME STATES --------------------------
    enum State {PAUSE, RUN, STOPPED}

    State state;

    public State getGameState() {
        return state;
    }

    //------------------------------------------------------

    //--------------------handle enemy data----------------
    int enemyWaveNumber;
    int maxEnemyWaves;
    int[] enemyTypes;
    float enemySpawnCoolDown;

    //------------------------------------------------------


    //rendering time calculated between current and next frame
    float deltaTime;

    Player player;

    //handle garbage collection and updating of entities. i think this can be done better with a linked list?????
    GameObjectManager entityManager = new GameObjectManager();


    //json values used for retrieving json data
    JsonValue jsonWaves;
    JsonValue jsonWave;

    //-------------------------------------------------

    //game event timer


    //seconds to wait before an event occurs
    private float eventTimeInSeconds = 0f;

    //max time to wait for an event to happen
    private float periodOfTimeSeconds = 10f;


    //max wait time testing variable for leveling up player
    float testTimeBeforeUpgrade = 0;
    float period = 5;

//    ExpDrop exp;

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

//        loadPlayerStats = AppManager.loadJsonFile("BasicEntityStats.json").get("playerBaseStats");

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

    //TESTING QUEUEING SEEN ENEMIES
    public void testSeenEnemy(Enemy e) {
        if (player.detectEntity(e)) {
            player.storeSeenEntity(e);
        }
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
                for (Enemy e : entityManager.getEnemies()) {
                    //temporary
//                    e.getPhysics().setMoveSpeed(0);
//                    e.getPhysics().performImpulseCollision(player);
                    //update all enemy objects
                    e.Update(deltaTime);
                    testSeenEnemy(e);

                }
                System.out.println("ENEMIES SEEN:" + player.getSeenEnemies());

//                System.out.println(entityManager.getProjectiles().toString());
//                entityManager.spawnBullets(deltaTime);


                //----------------------------------------------
                /**
                 * GAME OVER State
                 * @TODO: implement game over screen UI
                 */
//                System.out.println("AFTER UPGRADE:" + player.toString());
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





