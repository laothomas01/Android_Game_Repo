package com.GameVersion2.game.driver;

import com.GameVersion2.game.Managers.GameInputProcessor;
import com.GameVersion2.game.Entities.Player;
import com.badlogic.gdx.*;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Json;

import java.util.ArrayList;

//maintain all current physics calculation


/**
 * CRUD operations on the state of the game
 * <p>
 * -initializations, closing the app, running the app
 */


//for experiment purposes

class Person {
    private String name;
    private int age;
    private ArrayList numbers;

    public Person() {

    }

    public void setName(String s) {
        name = s;
    }

    public void setAge(int i) {
        age = i;
    }

    public void setNumbers(ArrayList n) {
        numbers = n;
    }
}

class PhoneNumber {
    private String name;
    private String number;

    public PhoneNumber(String home, String s) {

    }
}

public class GameStateManager extends ApplicationAdapter {


    //time calculated between current and next frame
    float deltaTime;

    Player player;

    FileHandle file;


    public void create() {
        //---------------------------------------------------
        file = Gdx.files.local("test.json");
        player = new Player();

        //Input Manager
        Gdx.input.setInputProcessor(new GameInputProcessor());
/**
 * TESTING PURPOSES
 */
        Person person = new Person();
        person.setName("Nate");
        person.setAge(31);
        ArrayList numbers = new ArrayList();
        numbers.add(new PhoneNumber("Home", "206-555-1234"));
        numbers.add(new PhoneNumber("Work", "425-555-4321"));
        person.setNumbers(numbers);

        Json json = new Json();
        System.out.println(json.prettyPrint(person));

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

        //test game keys
        handleKeyBoardInput();
//        player.getPhysics().move(deltaTime);
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





