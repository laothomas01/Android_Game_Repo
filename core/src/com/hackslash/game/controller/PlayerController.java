package com.hackslash.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.GameObject;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.UserInterfaceView;
import org.graalvm.compiler.loop.MathUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * all updates to the player will occur
 */

/**
 * [] enemy detection :
 * how to think about this:
 * 1) iterate through collection of enemies
 * 2) use a linked list/queue to store the order of enemies being seen
 * 3) look at the top of linked list to see first seen enemy
 * 4) if seen enemy is not dead, keep shooting at it.
 * 5) if seen enemy is dead, set top of the queue as an enemy object that will be removed
 */

public class PlayerController extends GameObjectController {


    UserInterfaceView joyStick;
    Player player;
    float max_cooldown = 0.05f;
    float current_cooldown = max_cooldown;
    Queue<Enemy> seen;
    float detectionRange;
    ArrayList<Bullet> bullets;
    int max_bullets = 1;

    //    float radians;
    float shoot_dx;
    float shoot_dy;

    Vector2 shootVelocity;

    public PlayerController(Player p, UserInterfaceView j) {
        shoot_dx = 0;
        shoot_dy = 0;
        shootVelocity = new Vector2(shoot_dx, shoot_dy);
        player = p;
        joyStick = j;
        seen = new LinkedList<>();
        bullets = new ArrayList<>();
        detectionRange = 500f;
    }

    public void move(float dt) {
        player.set_dx(joyStick.get_touchpad_x_input() * player.getSpeed() * dt);
        player.set_dy(joyStick.get_touchpad_y_input() * player.getSpeed() * dt);
        player.set_Velocity(player.getDx(), player.getDy());
        player.setPosition(player.getPosition().add(player.getVelocity()));
    }

    public boolean detectEnemy(Enemy e) {
        if (Vector2.dst(player.getPosition().x, player.getPosition().y, e.getPosition().x, e.getPosition().y) < detectionRange) {
            return true;
        }

        return false;
    }

    public void storeSeenEnemy(Enemy e) {

        if (!seen.contains(e)) {
            seen.add(e);
        }
    }

    public void shoot(float dt, Enemy e) {

        //before shooting, calculate angles and velocity of bullet trajectory
        player.setRadians(MathUtils.atan2(e.getPosition().y - player.getPosition().y, e.getPosition().x - player.getPosition().x));
        shoot_dx = MathUtils.cos(player.getRadians());
        shoot_dy = MathUtils.sin(player.getRadians());
        shootVelocity = new Vector2(shoot_dx, shoot_dy);
        if (current_cooldown <= 0) {

            if (bullets.size() < max_bullets) {
                bullets.add(new Bullet(player.getPosition().x, player.getPosition().y, 10, shoot_dx, shoot_dy, shootVelocity));
            }
            current_cooldown = max_cooldown;
        } else {
            current_cooldown -= dt;
        }
    }

    public Queue<Enemy> get_Seen_Enemies() {
        return seen;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public float getCurrentCooldown() {
        return current_cooldown;
    }


    //this should be a reusable function for all objects with health
//    public void takeDamage(GameObject e) {
//        if (player.getHealth() <= 0) {
//            player.setHealth(0);
//        } else {
//            player.setHealth(player.getHealth() - e.getDamage());
//        }
//    }


}
