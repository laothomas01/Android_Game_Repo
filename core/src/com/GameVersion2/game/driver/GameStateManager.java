package com.GameVersion2.game.driver;

import com.GameVersion2.game.Entities.*;
import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.GameVersion2.game.Util.Graphics2D;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import org.graalvm.compiler.graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class GameStateManager extends ApplicationAdapter {

    //-------------TESTING SKILL GENERATION-----------------


    //------------------------------------------------------


    // ----------------- GAME STATES --------------------------
    enum State {PAUSE, RUN, STOPPED}


//    public void loadCollectionOfAbilities() {
////        int counter = 0;
////        String[] angle_pos = {"radial", "non-radial"};
////        String[] angle_delta = {"uniform", "non-uniform"};
////        String[] nProj = {"single", "multiple"};
////
////        Map<Integer, Map<Object, Object>> skillConfigs = new HashMap<>();
//////        Array<Map<Object, Object>> skillConfigs = new Array<>();
////
////        for (String i : angle_pos) {
////            for (String j : angle_delta) {
////                for (String k : nProj) {
////                    Map<Object, Object> dict = new HashMap<>();
////                    dict.put("angle_pos", i);
////                    dict.put("angle_delta", j);
////                    dict.put("nProj", k);
////                    skillConfigs.put(counter, dict);
////                    counter++;
//////
////                }
////            }
////        }
////
////        counter = 0;
//
//
//    }
//    public void check

    State state = null;

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

//        loadCollectionOfAbilities();
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

    }

    /**
     * WRITING TEST CASES
     */

    //----------TESTING PLAYER LEVEL UP--------------------
   /* public void testPlayerLevelUp() {
//        player.setHasLeveledUp(true);
//        if (player.hasLeveled()) {
//            setGameState(State.PAUSE);
//        } else {
//            setGameState(State.RUN);
//        }
    }*/
    public void testUpgradeScreen(float dt) {

        Graphics2D.drawFontSprite("LEVELED UP", AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2);

        //   --------------------------     TESTING UPGRADING PLAYER SKILL -----------------------


        //----------------------------------ITERATION 1 SKILL UPGRADING -------------------------


//        player.getSkill(0).setmaxCoolDownTime(
//                player.getSkill(0).getmaxCoolDownTime() -
//                        //percentage decrease of skill max cooldown
//                        player.getSkill(0).getmaxCoolDownTime() * 0.5f);
//
//        player.getSkill(0).setLevel(player.getSkill(0).getLevel() + 1);
//
//        //a flag to end leveling up and the pause state
//        player.setCurrentExp(0);


        //---------------------------------- ITERATION 2 SKILL UPGRADING ----------------------


        // -----------------------------------------------------------------------------------------------


        //-----------------------------------------------------------------------------------------------


        //------------------------------    TESTING UPGRADE GUI ------------------------------

//        Graphics2D.drawFontSprite("SELECT AN UPGRADE\n[A] +10 Size\n[S] +10 Speed\n[D] Change Color", AppManager.getLocalViewPortWidth() / 2, AppManager.getLocalViewPortHeight() / 2 - 100);
//        /**
//         * INPUT HANDLING FOR PLAYER UPGRADES
//         */
//        if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.A))) {
//            System.out.println("SIZE!");
//            player.getPhysics().setSpriteSize(player.getPhysics().getSpriteWidth() + 10, player.getPhysics().getSpriteHeight() + 10);
//            player.setCurrentExp(0);
//        } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.S))) {
//            System.out.println("SPEED!");
//            player.getPhysics().setMoveSpeed(player.getPhysics().getMoveSpeed() + 100);
//            player.setCurrentExp(0);
//
//        } else if ((GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.D))) {
//            System.out.println("COLOR!");
//            player.getGraphics().setColor(Color.PINK);
//            player.setCurrentExp(0);
//        }
        //--------------------------------------------------------------------------------

    }
    //    public void testGradualUpgrades() {
//        System.out.println("TIME BEFORE UPGRADE:" + testTimeBeforeUpgrade);
//        System.out.println("LEVELED UP:" + player.hasLeveled());
//        //level up every 5 seconds
//        testTimeBeforeUpgrade += deltaTime;
//        if (testTimeBeforeUpgrade > period) {
//            testTimeBeforeUpgrade -= period;
//            player.setHasLeveledUp(true);
//        }

//    }


    //------------------------------------------------------


    /**
     * Shoot at enemies
     * Detect enemies
     * Queue seen enemies
     * Handle bullet collision
     */
    public void testPlayerLevelUp(float dt) {

        if (player.hasLeveledUp()) {
            System.out.println("HAS LEVELED!");
            //will be used to scale out exp gaining later
            player.setLevel(player.getLevel() + 1);
            //wait time before game resumes
            setGameState(State.PAUSE);
        } else {
            setGameState(State.RUN);
        }
    }

    public void testEntityAndPlayerInteraction() {
        updateEnemyWave();
        for (int i = 0; i < enemyTypes.length; ++i) {
            entityManager.spawnEnemies(deltaTime, enemyTypes[i], 5, enemySpawnCoolDown);
        }

        for (Entity e : entityManager.getEnemies()) {
            //update all enemy objects
            e.update(deltaTime);
        }

        //DETECT ENEMY AND PUT INTO QUEUE
        for (Entity e : entityManager.getEnemies()) {
            //handle player and enemy collision
            player.addSeenEnemy(e);
        }
        //GET FIRST ENEMY IN DETECTION QUEUE
        Entity currentlySeenEnemy = player.getSeenEnemies().peek();


        if (currentlySeenEnemy != null) {

            //POPULATE NEW BULLET WITH CALCULATIONS FROM SEEN ENEMY DATA
            if (player.detectEntity(currentlySeenEnemy)) {
                //DETECTED SEEN ENEMY = GREEN
                currentlySeenEnemy.getGraphics().setColor(Color.GREEN);
                player.shoot(currentlySeenEnemy, deltaTime, entityManager);
            } else {
                //UNDETECTED SEEN ENEMY = RED
                currentlySeenEnemy.getGraphics().setColor(Color.RED);
                //CALCULATE WAIT TIME WHEN ENEMY OUT OF RANGE
                detectionWaitTime += deltaTime;
                //IF REACHED MAX WAIT TIME, ENEMY = RED
                if (detectionWaitTime > enemyDetectionPeriod) {
                    detectionWaitTime -= enemyDetectionPeriod;
                    currentlySeenEnemy.getGraphics().setColor(Color.RED);
                    //ERASE ALL CURRENTLY SEEN ENEMIES
                    player.getSeenEnemies().clear();
                    //ERASE ALL STORED BULLETS
                    GameObjectManager.getProjectiles().clear();
                }
                //-------------------------------------------------------------------
            }
        }


        //PROJECTILE SPRITE UPDATE AND COLLISION HANDLING
        for (Entity p : GameObjectManager.getProjectiles()) {
            p.update(deltaTime);
            /**
             * If bullet collides with enemy, remove bullet and remove enemy
             */
            for (Entity e : entityManager.getEnemies()) {
                if (p.getPhysics().hasCollided(e)) {
                    p.getPhysics().setMoveSpeed(0);
                    entityManager.getGarbageCollection().add(p);
                    entityManager.getGarbageCollection().add(e);
                    GameObjectManager.getExpDrops().add(new ExpDrop(e.getPhysics().getPosition()));
                }
            }
            if (p.getPhysics().hasCollided(currentlySeenEnemy)) {
                //SET BULLET SPEED = 0 WHEN COLLIDING
                p.getPhysics().setMoveSpeed(0);

                //PREPARE DESTROYED GAME ENTITIES FOR GARBAGE COLLECTION
                entityManager.getGarbageCollection().add(p);
                entityManager.getGarbageCollection().add(currentlySeenEnemy);

                //INSTANTIATE NEW EXP DROP WHEN ENEMY KILLED
                GameObjectManager.getExpDrops().add(new ExpDrop(currentlySeenEnemy.getPhysics().getPosition()));

                player.getSeenEnemies().remove(currentlySeenEnemy);
            }
            /**
             * If bullet does not hit enemy but keeps going, delete object after a certain period of time
             */
            else if (p.lifeSpanExpired(deltaTime)) {
                entityManager.getGarbageCollection().add(p);
            }
        }
        //handling on collision-effect handler


        //EXP DROP UPDATING AND COLLISION HANDLING
        for (Entity exp : GameObjectManager.getExpDrops()) {
            exp.update(deltaTime);
            //CHECK PLAYER COLLISION WITH EXP DROPS
            if (player.getPhysics().hasCollided(exp)) {
                entityManager.getGarbageCollection().add(exp);
                ExpDrop e = (ExpDrop) exp;
                //UPDATE THE PLAYER'S CURRENT NEEDED EXP
                player.setCurrentExp(e.getExpPoint() + player.getCurrentExp());
            }
        }

//        if (player.hasLeveledUp()) {
//            setGameState(State.PAUSE);
//        }

//        System.out.println("PLAYER EXP:" + player.getCurrentExp());

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
        handleInputs();
        //testing level up logic
        //level up => pause game => pick upgrade => persist stats in player object => print player data => dispose of font sprite =>  set game state to run => repeat if leveled up

        if (state == State.RUN) {

            testEntityAndPlayerInteraction();
//            System.out.println(player.getSkill(0).toString());


            //checks player exp
            player.update(deltaTime);
        }
        if (state == State.PAUSE) {
            /**
             * There is no rendering of objects when this state occurs.
             * @TODO implement rendering when pause state occurs. change the state of the game entities.
             */
            testUpgradeScreen(deltaTime);
        }


// checks if player hasLeveledUp. if true, level up.
        testPlayerLevelUp(deltaTime);


//        switch (state) {
//            case RUN:
//                System.out.println("RUN");
////                /**
//////                 * GAME States:
//////                 * -MENU/TITLE SCREEN
//////                 * -GAMEPLAY State
//////                 * -GAME OVER SCREEN State
//////                 */
//////
//////                // calculaton of time difference between previous and current frame
//////                //60 FPS  => 1 second / 60 frames = 0.0166 sec / 1 frame
//////
//////                //Update enemy waves
//////                //increments time seconds and resets time seconds after reached period of time
//////
//////
//////                //------------------------------------------------------------------------------
//////
//////                /**
//////                 * TITLE SCREEN State
//////                 * @ODO: implement title screen UI
//////                 */
//////
//////
//////                //----------------------------------------------
//////
//////                /**
//////                 * GAME PLAY State
//////                 * @ODO: move to gameplay screen
//////                 */
//////
//////
//////                /** PERFORM CREATE:
//////                 *
//////                 * C/CRUD - create enemy entities. add to arraylist
//////                 *
//////                 * spawning from a list of enemies should be randomized. currently, random function is TOO SLOW!
//////                 */
////
//
////
////
////                /**
////                 *
////                 *
////                 * Update game entities:
////                 *
////                 * common entity updates:
////                 * -collision
////                 * -entity removal
////                 * -status updates
////                 *
////                 * PLAYER - keyboard input
////                 *
////                 * ENEMIES
////                 *
////                 * PROJECTILES
////                 *
////                 * GARBAGE COLLECTION
////                 */
//
////
//                testEntityAndPlayerInteraction();
//////                testPlayerLevelUp();
//////                testUpgradeScreen();
////
////                //----------------------------------------------
////                /**
////                 * GAME OVER State
////                 * @ODO: implement game over screen UI
////                 */
////                //----------------------------------------------
////                player.setCurrentExp(player.getCurrentExp() + 10);
//                player.update(deltaTime);
//                handleMovementInputs();
//            case PAUSE:
//                /**
//                 * If has leveled up, display interactable gui or in-game user-input to select an ability
//                 */
//                System.out.println("IS PAUSED!");
//
//                break;
//            default:
//                break;
//        }


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


    private void handleInputs() {

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





