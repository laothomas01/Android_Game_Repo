package com.hackslash.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;

public class Bullet_Controller {

    public Bullet_Controller() {

    }

    public void moveTowardEnemy(float dt, Enemy e, Bullet b) {


//        if (b.hasCollided(e, b)) {
//            b.setCurrent_speed(0f);
//        } else {
        b.setRadians(MathUtils.atan2(e.getPosition().y - b.getPosition().y, e.getPosition().x - b.getPosition().x));
        b.set_dx(MathUtils.cos(b.getRadians()));
        b.set_dy(MathUtils.sin(b.getRadians()));
        b.set_Velocity(b.get_dx(), b.get_dy());
        b.setPosition(b.getPosition().add(b.getVelocity().scl(b.getCurrent_speed() * dt)));
//        }

    }


}
