package com.hackslash.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.hackslash.game.model.Player;


//All inputs from joystick will be sent to the
//player controller which then
public class UI_View extends Game_Object_View {

//    private Player player;
//    OrthographicCamera followCam;
//    OrthographicCamera uiCam;
//    SpriteBatch healthBarBatch;
//    Color healthBarColor;

    public UI_View(Player p) {
//        batch = new SpriteBatch();
//        player = p;
    }

    public void init_cameras() {
        //this camera will follow the player
//        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        followCam.position.set(followCam.viewportWidth / 2f, followCam.viewportHeight / 2f, 0);
//        uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        uiCam.position.set(uiCam.viewportWidth / 2f, uiCam.viewportHeight / 2f, 0);
//        uiCam.update();
//        followCam.update();
    }


    public void init_touchpad() {
//        skin = new Skin();
//        skin.add("touchBackground", new Texture("touchBackground.png"));
//        skin.add("touchKnob", new Texture("touchKnob.png"));
//        touchBackground = skin.getDrawable("touchBackground");
//        touchKnob = skin.getDrawable("touchKnob");
//
//        touchpadStyle = new Touchpad.TouchpadStyle();
//        touchpadStyle.background = touchBackground;
//        touchpadStyle.knob = touchKnob;
//
//        touchpad = new Touchpad(10, touchpadStyle);
//        touchpad.setBounds(15, 15, 200, 200);
//        stage = new Stage();
//        Gdx.input.setInputProcessor(stage);

    }

    public void update_touchpad() {
//        stage.addActor(touchpad);
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
    }

    public void init_healthbar() {

//        healthBarColor = Color.RED;
//        tex = new Texture(Gdx.files.internal("square.png"));
//        healthBarBatch = batch;

//        sprite = new Sprite(tex, 0, 0, 10, 50);
    }


    public void updateCamera(float dt, SpriteBatch batch1, SpriteBatch batch2, OrthographicCamera cam1, OrthographicCamera cam2) {



        /*
         *
         *
         *
         */
//        batch1.begin();
//        batch1.setProjectionMatrix(cam1.combined);
//        batch2.setProjectionMatrix(cam2.combined);
//
//        batch1.end();
//
////        batch.begin();
////        batch.setProjectionMatrix(cam.combined);
////        batch.end();
//
//
//        Vector3 camPosition = cam1.position;
////        camPosition.x = camPosition.x + (player.getPosition().x * 0.5f - cam.position.x) * dt;
//        camPosition.x = camPosition.x + (player.getPosition().x * 1 - cam1.position.x) * dt;
////        camPosition.y = camPosition.y + (player.getPosition().y * 0.5f - cam.position.y) * dt;
//        camPosition.y = camPosition.y + (player.getPosition().y * 1 - cam1.position.y) * dt;
//        cam1.position.set(camPosition);
//        cam1.update();
//
//
//        Vector3 camPosition2 = cam2.position;
////        camPosition.x = camPosition.x + (player.getPosition().x * 0.5f - cam.position.x) * dt;
//        camPosition2.x = camPosition2.x + (player.getPosition().x * 1 - cam2.position.x) * dt;
////        camPosition.y = camPosition.y + (player.getPosition().y * 0.5f - cam.position.y) * dt;
//        camPosition2.y = camPosition2.y + (player.getPosition().y * 1 - cam2.position.y) * dt;
//        cam2.position.set(camPosition2);
//        cam2.update();
//
////        System.out.println("PLAYER POSITION:" + player.getPosition().toString());
////        batch.begin();
////        batch.setProjectionMatrix(uiCam.combined);
////        batch.end();
////
////        batch.setProjectionMatrix(followCam.combined);
////
////
////        Vector3 followCamPosition = followCam.position;
////        followCamPosition.x = followCam.position.x + (player.getPosition().x * 1 - followCam.position.x) * dt;
////        followCamPosition.y = followCam.position.y + (player.getPosition().y * 1 - followCam.position.y) * dt;
////        followCam.position.set(followCamPosition);
////        followCam.update();


    }


//    public void updatePlayerHealthBar(Batch batch) {
////        batch.begin();
////        batch.setColor(getHealthBarColor());
//////        batch.draw(tex, player.getPosition().x, player.getPosition().y - 10, player.getCurrent_health(), 5);
////        batch.draw(tex, 250, 100, player.getCurrent_health(), 50);
////        batch.end();
//    }
//
//    public SpriteBatch getHealthBarBatch() {
////        return healthBarBatch;
//    }
//
//    public Color getHealthBarColor() {
////        return healthBarColor;
//    }
//
//
//    public float get_touchpad_x_input() {
////        return touchpad.getKnobPercentX();
//    }
//
//    public float get_touchpad_y_input() {
////        return touchpad.getKnobPercentY();
//    }
//
//    public OrthographicCamera getFollowCam() {
////        return followCam;
//    }
//
//    public OrthographicCamera getUiCam() {
////        return uiCam;
//    }
//
//    public SpriteBatch getSpriteBatch() {
////        return batch;
//    }


}



