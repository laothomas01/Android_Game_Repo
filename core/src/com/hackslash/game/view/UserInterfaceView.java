package com.hackslash.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.hackslash.game.model.Player;


//All inputs from joystick will be sent to the
//player controller which then
public class UserInterfaceView extends GameObjectView {

    private Player player;

    public UserInterfaceView(Player p) {
        player = p;
    }


    public void init_touchpad() {
        //document how to do this later
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
        touchpad.setPosition(100,5);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

    }

    public void update_touchpad() {
        stage.addActor(touchpad);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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
    public float getTouchpadXInput() {
        return touchpad.getKnobPercentX();
    }

    public float getTouchpadYInput() {
        return touchpad.getKnobPercentY();
    }


}



