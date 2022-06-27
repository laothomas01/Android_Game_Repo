package com.hackslash.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;


public class Enemy_Controller {
    /**
     * We will make multiple enemies so let's not
     * pass in the enemy as a parameter to this constructor
     */
    public Enemy_Controller() {

    }

    public void update(float dt, Enemy e) {

    }

    //This function can be generalized to all game objects that move
    //Refactor later
    public void moveToPlayer(float dt, Enemy e, Player p) {
        e.setRadians(MathUtils.atan2(p.getPosition().y - e.getPosition().y, p.getPosition().x - e.getPosition().x));
        e.set_dx(MathUtils.cos(e.getRadians()));
        e.set_dy(MathUtils.sin(e.getRadians()));
        e.set_Velocity(e.getDx(), e.getDy());
        e.setPosition(e.getPosition().add(e.getVelocity().scl(e.getCurrent_speed() * dt)));
    }


}
