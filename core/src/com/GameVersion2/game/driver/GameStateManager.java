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
    enum GAMESTATE {START, GAMEPLAY, END}

    GAMESTATE state = null;

    public String getState() {
        return state.name();
    }

    //------------------------------------------------------

    //Enemy related data
    int enemyWaveNumber;
    int maxEnemyWaves;
    int[] enemyTypes;
    float spawnCoolDown;
    //
    //rendering time calculated between current and next frame
    float deltaTime;
    Player player;
    //handler for all game entities
    GameObjectManager entityManager;
    JsonValue jsonWaves;
    JsonValue jsonWave;

    //game timer
    private float timeSeconds = 0f;
    private float period = 10f;

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
        player = new Player();
    }


    public void render() {

        /**
         * GAME STATES:
         * -MENU/TITLE SCREEN
         * -GAMEPLAY STATE
         * -GAME OVER SCREEN STATE
         */


        deltaTime = Gdx.graphics.getDeltaTime();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Update enemy waves
        //increments time seconds and resets time seconds after reached period of time

        updateEnemyWave();

        //------------------------------------------------------------------------------

        /**
         * TITLE SCREEN STATE
         * @TODO: implement title screen UI
         */


        //----------------------------------------------

        /**
         * GAME PLAY STATE
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
            e.Update(deltaTime);
            e.getPhysics().moveTowards(player, deltaTime);
        }

        player.Update(deltaTime);

        //----------------------------------------------
        /**
         * GAME OVER STATE
         * @TODO: implement game over screen UI
         */

        //----------------------------------------------

    }

    public void updateEnemyWave() {
        timeSeconds += deltaTime;
        if (timeSeconds > period) {
            timeSeconds -= period;
            //check if wave number passed max enemy waves
            //if not, change wave number
            //if, keep at max number of waves
            if (enemyWaveNumber < maxEnemyWaves - 1) {
                enemyWaveNumber++;
            } else {
                enemyWaveNumber = maxEnemyWaves - 1;
            }
        }

        System.out.println(enemyWaveNumber);
        jsonWave = jsonWaves.get(enemyWaveNumber);
        enemyTypes = jsonWave.get("enemyTypes").asIntArray();
        spawnCoolDown = jsonWave.get("spawnCoolDown").asFloat();
    }

    @Override
    public void resize(int width, int height) {

    }


    public void pause() {

        //pause when player levels up and has to choose an upgrade

        //pause when player swaps the application window but has not closed the project

        //called first when dispose() is called
    }


    //called when the Application is resumed from a paused state.
    @Override
    public void resume() {


    }

    public void dispose() {


        /**
         * WIll BE WRITING DATA TO FILE HERE
         */


    }


}





