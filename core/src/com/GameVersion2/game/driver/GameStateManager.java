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


//for experiment purposes

//class Person {
//    private String name;
//    private int age;
//    private ArrayList numbers;
//
//    public Person() {
//
//    }
//
//    public void setName(String s) {
//        name = s;
//    }
//
//    public void setAge(int i) {
//        age = i;
//    }
//
//    public void setNumbers(ArrayList n) {
//        numbers = n;
//    }
//}
//
//class PhoneNumber {
//    private String name;
//    private String number;
//
//    public PhoneNumber() {
//
//    }
//
//    public PhoneNumber(String n, String num) {
//        name = n;
//        number = num;
//    }
//
//}

public class GameStateManager extends ApplicationAdapter {

    enum GAMESTATE {START, GAMEPLAY, END}

    GAMESTATE state = null;

    public String getState() {
        return state.name();
    }

    //time calculated between current and next frame
    float deltaTime;
    Player player;

    FileHandle file;
    GameObjectManager objectManager;

    public void create() {
        objectManager = new GameObjectManager();
//        //---------------------------------------------------
////        file = Gdx.files.local("test.json");
        player = new Player();
//        //
//        System.out.println(
//                //filename
//                AppManager.loadJsonFile("test.json").
//                        //get the object
//                                get("waves")
//                        //enemy wave count
//                        .get(0)
//                        //number of components for an enemy wave
//                        .size
//                //get component
//
//
//        );
//
//        Vector2 test = new Vector2(1, 1);
//        //position + (test + direction vector * speed * time)???
//        System.out.println(test.mulAdd(new Vector2(2, 2), 2));
        //wave component;
        //Input Manager
        Gdx.input.setInputProcessor(new GameInputProcessor());
/**
 * TESTING PURPOSES
 */

//        Person person = new Person();
//        person.setName("Nate");
//        person.setAge(31);
//        //field numbers of Person class containing Phone Number objects
//        ArrayList numbers = new ArrayList();
//        numbers.add(new PhoneNumber("Home", "206-555-1234"));
//        numbers.add(new PhoneNumber("Work", "425-555-4321"));
//        person.setNumbers(numbers);
//
//        /*
//            deserializing objects from JSON
//         */
//        JsonReader json = new JsonReader();
//        JsonValue base = json.parse(Gdx.files.internal("test.json"));
//        JsonValue components = base.get("name");
////        System.out.println(components.get(0).getString("class"));
//        System.out.println(base);
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
//        testingSpawning.getEnemies().get(0).getGraphics().drawSprite();
//        testingSpawning.getEnemies().get(0).getPhysics().getPosition().toString();
        objectManager.spawnEnemies(deltaTime, 0, 5, 100);

        for (Enemy e : objectManager.getEnemies()) {
            e.Update(deltaTime);

        }

//        for (Enemy e : testingSpawning.getEnemies()) {
//            e.Update(deltaTime);
//        }
//        System.out.println(testingSpawning.getEnemies().get(0).hashCode());
//        //test game keys
//        handleKeyBoardInput();
////        player.getPhysics().move(deltaTime);
//        testingSpawning.getEnemies().get(0).update(deltaTime);

        player.Update(deltaTime);


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

        GameInputProcessor.GameKeys.update();
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





