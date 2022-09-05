package com.hackslash.GAME_VERSION1.controller;

import com.badlogic.gdx.math.MathUtils;
import com.hackslash.GAME_VERSION1.model.Bullet;
import com.hackslash.GAME_VERSION1.model.Enemy;

public class BulletController extends GameObjectController {

    public BulletController() {

    }

    //bullets follow and fly towards enemies
    public void homingAbility(float dt, Enemy e, Bullet b) {

        b.setRadians(MathUtils.atan2(e.getPosition().y - b.getPosition().y, e.getPosition().x - b.getPosition().x));
        b.set_dx(MathUtils.cos(b.getRadians()));
        b.set_dy(MathUtils.sin(b.getRadians()));
        b.set_Velocity(b.get_dx(), b.get_dy());
        b.setPosition(b.getPosition().add(b.getVelocity().scl(b.getSpeed() * dt)));

    }

    public void move(float dt, Bullet b) {

        b.set_Velocity(b.get_dx() * b.getSpeed() * dt, b.get_dy() * b.getSpeed() * dt);
        b.setPosition(
                b.getPosition().add(
                        b.getVelocity()
                )
        );

    }
}
