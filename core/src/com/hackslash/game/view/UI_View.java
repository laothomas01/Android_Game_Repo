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

    private Player player;
    OrthographicCamera followCam;
    OrthographicCamera uiCam;


    public UI_View(Player p) {
        batch = new SpriteBatch();
        player = p;
    }

    public void init_cameras() {
        //this camera will follow the player
        followCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        followCam.position.set(followCam.viewportWidth / 2f, followCam.viewportHeight / 2f, 0);
        uiCam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        uiCam.position.set(uiCam.viewportWidth / 2f, uiCam.viewportHeight / 2f, 0);
        uiCam.update();
//        followCam.update();
    }


    public void init_touchpad() {
        skin = new Skin();
        skin.add("touchBackground", new Texture("touchBackground.png"));
        skin.add("touchKnob", new Texture("touchKnob.png"));
        touchBackground = skin.getDrawable("touchBackground");
        touchKnob = skin.getDrawable("touchKnob");

        touchpadStyle = new Touchpad.TouchpadStyle();
        touchpadStyle.background = touchBackground;
        touchpadStyle.knob = touchKnob;

        touchpad = new Touchpad(10, touchpadStyle);
        touchpad.setBounds(15, 15, 200, 200);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    public void update_touchpad() {
        stage.addActor(touchpad);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void init_healthbar() {

        clr = Color.GREEN;
        tex = new Texture(Gdx.files.internal("square.png"));
//        sprite = new Sprite(tex, 0, 0, 10, 50);
    }


    public void update_cams(float dt, Batch batch) {

        System.out.println("PLAYER POSITION:" + player.getPosition().toString());
        batch.begin();
        batch.setProjectionMatrix(uiCam.combined);
        batch.end();

        batch.setProjectionMatrix(followCam.combined);

////        batch.begin();
////
////        batch.setProjectionMatrix(uiCam.combined);
////
////        batch.end();
////        batch.begin();
////        batch.setProjectionMatrix(uiCam.combined);
//        batch.setProjectionMatrix(followCam.combined);
////
////        batch.end();
////
////        set the follow cam position
        Vector3 followCamPosition = followCam.position;
        followCamPosition.x = followCam.position.x + (player.getPosition().x * 1 - followCam.position.x) * dt;
        followCamPosition.y = followCam.position.y + (player.getPosition().y * 1 - followCam.position.y) * dt;
        followCam.position.set(followCamPosition);
        followCam.update();
//        uiCam.update();

    }


    public void updatePlayerHealthBar(Batch batch) {
        batch.begin();
        batch.setColor(clr);
        batch.draw(tex, player.getPosition().x, player.getPosition().y - 10, player.getCurrent_health(), 5);
        batch.end();
    }

    public SpriteBatch getSpriteBatch() {
        return batch;
    }

    public float get_touchpad_x_input() {
        return touchpad.getKnobPercentX();
    }

    public float get_touchpad_y_input() {
        return touchpad.getKnobPercentY();
    }


}



