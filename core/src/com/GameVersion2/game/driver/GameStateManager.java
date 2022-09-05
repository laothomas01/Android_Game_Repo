package com.GameVersion2.game.driver;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.TimeUtils;

//maintain all current physics calculation


/**
 * CRUD operations on the state of the game
 * <p>
 * -initializations, closing the app, running the app
 */
public class GameStateManager extends ApplicationAdapter {


    //time calculated between current and next frame
    float deltaTime;

    Player player;

    public void create() {
        //---------------------------------------------------
        player = new Player();
        //Input Manager
        Gdx.input.setInputProcessor(new GameInputProcessor());

    }


    public void render() {

        /**
         * GAME STATES:
         *
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

        if ((GameKeys.isDown(GameKeys.UP) || (GameKeys.isDown(GameKeys.W)))) {
            if ((GameKeys.isDown(GameKeys.UP) && GameKeys.isDown(GameKeys.RIGHT)) || (GameKeys.isDown(GameKeys.W) && GameKeys.isDown(GameKeys.D))) {
                player.getPhysics().setDirectionVector(1, 1);

            } else if ((GameKeys.isDown(GameKeys.UP) && GameKeys.isDown(GameKeys.LEFT)) || (GameKeys.isDown(GameKeys.W) && GameKeys.isDown(GameKeys.A))) {
                player.getPhysics().setDirectionVector(-1, 1);
            } else {
                player.getPhysics().setDirectionVector(0, 1);
            }
        } else if ((GameKeys.isDown(GameKeys.DOWN) || (GameKeys.isDown(GameKeys.S)))) {
            if ((GameKeys.isDown(GameKeys.DOWN) && GameKeys.isDown(GameKeys.RIGHT)) || (GameKeys.isDown(GameKeys.S) && GameKeys.isDown(GameKeys.D))) {
                player.getPhysics().setDirectionVector(1, -1);
            } else if ((GameKeys.isDown(GameKeys.DOWN) && GameKeys.isDown(GameKeys.LEFT)) || (GameKeys.isDown(GameKeys.S) && GameKeys.isDown(GameKeys.A))) {
                player.getPhysics().setDirectionVector(-1, -1);
            } else {
                player.getPhysics().setDirectionVector(0, -1);
            }
        } else if (GameKeys.isDown(GameKeys.LEFT) || GameKeys.isDown(GameKeys.A)) {
            player.getPhysics().setDirectionVector(-1, 0);
        } else if (GameKeys.isDown(GameKeys.RIGHT) || GameKeys.isDown(GameKeys.D)) {
            player.getPhysics().setDirectionVector(1, 0);
        } else {
            player.getPhysics().setDirectionVector(0, 0);
        }

        GameKeys.update();
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





