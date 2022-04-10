package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

import javax.xml.validation.ValidatorHandler;

public class Camera {
    OrthographicCamera cam;

    public Camera() {
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void update(Player p, float dt) {
        cam.update();
        followPlayer(p, dt);
    }

    public void followPlayer(Player p, float dt) {
        cam.translate(p.getXPosition() * p.getPlayerSpeed() * dt, p.getYPosition() * dt);
    }


//    public Matrix4 getCameraCombined() {
//        return cam.combined;
//    }



}
