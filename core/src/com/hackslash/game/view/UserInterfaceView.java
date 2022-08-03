package com.hackslash.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.hackslash.game.model.Player;


//All inputs from joystick will be sent to the
//player controller which then
public class UserInterfaceView extends GameObjectView {

//
//    private Player player;
//    private SpriteBatch batch;
//
//
//    public UserInterfaceView(Player p) {
//
//        player = p;
//        batch = new SpriteBatch();
//    }
//
//
//    public void init_touchpad() {
//        //document how to do this later
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
//        touchpad.setPosition(100, 5);
//        stage = new Stage();
//        Gdx.input.setInputProcessor(stage);
//
//    }
//
//    public void initHealthBar() {
//
//        clr = Color.GREEN;
//        tex = new Texture(Gdx.files.internal("square.png"));
//    }
//
//    public void update_touchpad() {
//        stage.addActor(touchpad);
//        stage.act(Gdx.graphics.getDeltaTime());
//        stage.draw();
//    }
//
//
//    public void updatePlayerHealthBar(Batch batch) {
//        batch.begin();
//        batch.setColor(clr);
//        batch.draw(tex, player.getPosition().x, player.getPosition().y, player.getHealth(), 5);
//        batch.end();
//
//        //
////        player.getBatch().begin();
////        player.getHealthBarSprite().draw(player.getBatch());
////        player.getBatch().end();
////
////        player.getHealthBarSprite().setPosition(player.getPosition().x, player.getPosition().y - 10);
////        player.getHealthBarSprite().setScale(player.getHealth(), 1f);
//    }
//
//
////    public void updatePlayerHealthBar(Batch batch, Player p) {
//////        System.out.println("POSITION:" + p.getPosition().toString());
////        batch.begin();
////        batch.setColor(Color.GREEN);
////        batch.draw(getTex(), p.getPosition().x, p.getPosition().y - 10, p.getHealth(), 5);
//////        batch.setColor(Color.GREEN);
//////        batch.draw(tex, player.getPosition().x, player.getPosition().y - 10, player.getHealth(), 5);
////        batch.end();
////    }
//
//    //
////    public SpriteBatch getHealthBarBatch() {
//////        return healthBarBatch;
////    }
////
////    public Color getHealthBarColor() {
//////        return healthBarColor;
////    }
////
////
//    public SpriteBatch getSpriteBatch() {
//        return batch;
//    }
//
//    public float getTouchpadXInput() {
//        return touchpad.getKnobPercentX();
//    }
//
//    public float getTouchpadYInput() {
//        return touchpad.getKnobPercentY();
//    }


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

    public void init_healthbar() {
        clr = Color.GREEN;
        tex = new Texture(Gdx.files.internal("square.png"));
    }

    public void updatePlayerHealthBar(Batch batch, Player player, OrthographicCamera cam) {

        //pass in a spritebatch for the health bar and project that batch object onto the follow camera's position.

        batch.begin();
        batch.setColor(clr);
        batch.setProjectionMatrix(cam.combined);
        batch.draw(tex, player.getPosition().x, player.getPosition().y - 10, player.getHealth(), 5);
        batch.end();
    }

    public void update_touchpad() {
        stage.addActor(touchpad);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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



