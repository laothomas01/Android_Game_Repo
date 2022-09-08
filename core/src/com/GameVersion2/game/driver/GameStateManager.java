package com.GameVersion2.game.driver;

import com.GameVersion2.game.Entities.Enemy;
import com.GameVersion2.game.Managers.AppManager;
import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Entities.Player;
import com.GameVersion2.game.Managers.GameObjectManager;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.util.ArrayList;

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
    int enemyType;
    int maxEnemyWaves;
    int enemyCount;
    int[] enemyTypes;
    //
    //rendering time calculated between current and next frame
    float deltaTime;
    Player player;
    //handler for all game entities
    GameObjectManager entityManager;

    public void create() {
        enemyWaveNumber = 2;
        JsonValue json = AppManager.loadJsonFile("entityData.json").get("waves").get(enemyWaveNumber);
        enemyTypes = json.get("enemyTypes").asIntArray();


        //        System.out.println(json);
//        System.out.println(json.get("Ans"));

        //0 based indexing
//        enemyWave = AppManager.loadJsonFile("entityData.json").get("enemyWaves").get(enemyWaveNumber);


//        System.out.println("ENEMY WAVE:" + enemyWave);
//        enemyType = enemyWave.get(0).asInt();
//        System.out.println("TYPE:" + enemyType);
//        enemyCount = enemyWave.get(1).asInt();
//        System.out.println("ENEMY COUNT:" + enemyCount);
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

        //----------------------------------------------

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
         */
        for (int i : enemyTypes) {
            entityManager.spawnEnemies(deltaTime, i, 5);
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
        }

        player.Update(deltaTime);

        //----------------------------------------------
        /**
         * GAME OVER STATE
         * @TODO: implement game over screen UI
         */

        //----------------------------------------------

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





