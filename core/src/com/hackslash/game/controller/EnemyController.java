package com.hackslash.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;


public class EnemyController extends GameObjectController {
    public EnemyController() {

    }

    public void update(float dt, Enemy e) {

    }

    public void moveToPlayer(float dt, Enemy e, Player p) {
        e.setRadians(MathUtils.atan2(p.getPosition().y - e.getPosition().y, p.getPosition().x - e.getPosition().x));
        e.set_dx(MathUtils.cos(e.getRadians()));
        e.set_dy(MathUtils.sin(e.getRadians()));
        e.set_Velocity(e.getDx(), e.getDy());
        e.setPosition(e.getPosition().add(e.getVelocity().scl(e.getSpeed() * dt)));
    }
}
