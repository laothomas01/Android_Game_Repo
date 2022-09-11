package com.GameVersion2.game.driver;

import com.GameVersion2.game.Entities.Enemy;
import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Entities.Player;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;
import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.SplittableRandom;

//maintain all current physics calculation


/**
 * CRUD operations on the state of the game
 * <p>
 * -initializations, closing the app, running the app
 */


public class GameStateManager extends ApplicationAdapter {

    //-----------FOR LATER IMPLEMENTATION------------------
    enum State {PAUSE, RUN, STOPPED}

    State state = State.RUN;

    public String getGameState() {
        return state.name();
    }

    //------------------------------------------------------

    //Enemy related data
    int enemyWaveNumber;
    int maxEnemyWaves;
    int[] enemyTypes;
    float spawnCoolDown;
    //rendering time calculated between current and next frame
    float deltaTime;
    Player player;
    //handler for all game entities
    GameObjectManager entityManager;
    JsonValue jsonWaves;
    JsonValue jsonWave;

    //game timer
    private float eventTimeInSeconds = 0f;
    private float periodOfTimeSeconds = 10f;


    //upgrade template file
    JsonValue upgrades;

    //load base stats
    JsonValue loadPlayerStats;

    public void create() {
        /**
         * PLACE THESE VALUES INTO THE RENDER:
         * -increment enemy wave number after N amount of time passed.
         * -check when enemyWaveNumber passes maxNumber of waves. if passes, keep wave number at max
         * -make sure to check how much time has passed during runtime.
         */

        //------------------------------update in render-----------------------

        enemyWaveNumber = 0;
        jsonWaves = AppManager.loadJsonFile("entityData.json").get("waves");
        maxEnemyWaves = jsonWaves.size;
        //----------------------------------------------------------------------
        entityManager = new GameObjectManager();
        /**
         * Have player add in a basic skill when starting up the game
         */
        //on create, load up player's base stats

        loadPlayerStats = AppManager.loadJsonFile("BasicEntityStats.json").get("playerBaseStats");

        player = new Player();
        player.setLevel(loadPlayerStats.get("level").asInt());
        player.getPhysics().setSpriteSize(loadPlayerStats.get("size").get("width").asFloat(), loadPlayerStats.get("size").get("height").asFloat());
        player.getPhysics().setMoveSpeed(loadPlayerStats.get("speed").asFloat());
        //adding of skill
        upgrades = AppManager.loadJsonFile("upgradeComponentTemplate.json").get("attackUpgrade");

        Gdx.input.setInputProcessor(new GameInputProcessor());

    }


    public void render() {


        switch (state) {
            case RUN:
                /**
                 * GAME StateS:
                 * -MENU/TITLE SCREEN
                 * -GAMEPLAY State
                 * -GAME OVER SCREEN State
                 */


                deltaTime = Gdx.graphics.getDeltaTime();
                Gdx.gl.glClearColor(0, 0, 0, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                //Update enemy waves
                //increments time seconds and resets time seconds after reached period of time

                updateEnemyWave();

                //------------------------------------------------------------------------------

                /**
                 * TITLE SCREEN State
                 * @TODO: implement title screen UI
                 */


                //----------------------------------------------

                /**
                 * GAME PLAY State
                 * @TODO: move to gameplay screen
                 */


                /** PERFORM CREATE:
                 *
                 * C/CRUD - create enemy entities. add to arraylist
                 *
                 * spawning from a list of enemies should be randomized. currently, random function is TOO SLOW!
                 */
                for (int i = 0; i < enemyTypes.length; ++i) {
                    entityManager.spawnEnemies(deltaTime, enemyTypes[i], 5, spawnCoolDown);
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
                    e.getPhysics().setMoveSpeed(0);
                    e.getPhysics().performImpulseCollision(player);
                    e.Update(deltaTime);
                    player.shoot(e, deltaTime);
                }

                player.Update(deltaTime);
                handleKeyBoardInput();
                //----------------------------------------------
                /**
                 * GAME OVER State
                 * @TODO: implement game over screen UI
                 */

                //----------------------------------------------


            case PAUSE:
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
        spawnCoolDown = jsonWave.get("spawnCoolDown").asFloat();
    }


    public void pause() {

        //pause when player levels up and has to choose an upgrade

        //pause when player swaps the application window but has not closed the project

        //called first when dispose() is called
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


    private void handleKeyBoardInput() {

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
        if (GameInputProcessor.GameKeys.isDown(GameInputProcessor.GameKeys.ESC)) {
            setGameState(State.PAUSE);
        }



        GameInputProcessor.GameKeys.update();
    }

}





