package com.hackslash.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;


public class game_UI_view {
    private Stage stage;
    private Touchpad touchpad;
    private Touchpad.TouchpadStyle touchpadStyle;
    private Skin skin;
    private Drawable touchBackground;
    private Drawable touchKnob;


    public game_UI_view() {

    }

    public void init_game_UI_View() {
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
        stage.act(1 / 60);
        stage.draw();
    }

    public float get_touchpad_x_input() {
        return touchpad.getKnobPercentX();
    }

    public float get_touchpad_y_input() {
        return touchpad.getKnobPercentY();
    }


}



