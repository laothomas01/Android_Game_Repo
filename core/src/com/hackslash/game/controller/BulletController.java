package com.hackslash.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;

public class BulletController extends GameObjectController {

//    public BulletController() {
//
//    }
//
//    //This function can be generalized to all game objects that move
//    //Refactor this later
//    //I want the bullet to fly in the direction of the spotted enemy but not FOLLOW the enemy
////    public void moveTowardEnemy(float dt, Enemy e, Bullet b) {
////
////        b.setRadians(MathUtils.atan2(e.getPosition().y - b.getPosition().y, e.getPosition().x - b.getPosition().x));
////        b.set_dx(MathUtils.cos(b.getRadians()));
////        b.set_dy(MathUtils.sin(b.getRadians()));
////        b.set_Velocity(b.get_dx(), b.get_dy());
////        b.setPosition(b.getPosition().add(b.getVelocity().scl(b.getCurrent_speed() * dt)));
////
////    }

    public void moveTowardEnemy(float dt, Enemy e, Bullet b) {

        b.setRadians(MathUtils.atan2(e.getPosition().y - b.getPosition().y, e.getPosition().x - b.getPosition().x));
        b.set_dx(MathUtils.cos(b.getRadians()));
        b.set_dy(MathUtils.sin(b.getRadians()));
        b.set_Velocity(b.get_dx(), b.get_dy());
        b.setPosition(b.getPosition().add(b.getVelocity().scl(b.getSpeed() * dt)));

    }
}
