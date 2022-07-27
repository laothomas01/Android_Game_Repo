package com.hackslash.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.GameObject;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.UserInterfaceView;

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

//    float max_cooldown = 0.05f;
//    float current_cooldown = max_cooldown;
//    Queue<Enemy> spottedEnemies;
//    ArrayList<Bullet> bullets;
//    int maxBullets = 1;
//    float detectionRadius;
//
//
//    public PlayerController(Player p, UserInterfaceView j) {
//        player = p;
//        joyStick = j;
//        spottedEnemies = new LinkedList<>();
//        detectionRadius = 1000f;
//        bullets = new ArrayList<>();
//    }
//
//    //
////
//    public void move(float dt) {
//
//        player.setDx(joyStick.getTouchpadXInput() * player.getSpeed() * dt);
//        player.setDx(joyStick.getTouchpadXInput() * player.getSpeed() * dt);
//        player.setDy(joyStick.getTouchpadYInput() * player.getSpeed() * dt);
//        player.setVelocity(new Vector2(player.getDx(), player.getDy()));
//        player.setPosition(player.getPosition().add(player.getVelocity()));
//    }
//
//    //
////    public boolean detectEnemy(Enemy e) {
////        if (Vector2.dst(player.getPosition().x, player.getPosition().y, e.getPosition().x, e.getPosition().y) < detectionRadius) {
////            return true;
////        }
////        return false;
////    }
//
//    //
//    public void storeSeenEnemy(Enemy e) {
//
//        if (!spottedEnemies.contains(e)) {
//            spottedEnemies.add(e);
//        }
//    }
//
//    //
////
//    public void shoot(float dt) {
//
//
//        if (current_cooldown <= 0) {
//            if (bullets.size() < maxBullets) {
//                bullets.add(new Bullet(player));
//            }
//            current_cooldown = max_cooldown;
//        } else {
//            current_cooldown -= dt;
//        }
//    }
//
//    //
//    public Queue<Enemy> getSeenEnemies() {
//        return spottedEnemies;
//    }
//
//    //
//    public ArrayList<Bullet> getBullets() {
//        return bullets;
//    }
//
//    //
//    public float getCurrentCooldown() {
//        return current_cooldown;
//    }
////
////    //
////    public void takeDamage(Game_Object e) {
////        if (player.getCurrent_health() <= 0) {
////            player.setCurrent_health(0);
////        } else {
////            player.setCurrent_health(player.getCurrent_health() - e.getCurrent_damage());
////        }
////
////    }
////
////
////    public void setCurrent_Cooldown(float percentage, float cd) {
////
////
////    }

    UserInterfaceView joyStick;
    Player player;
    float max_cooldown = 0.05f;
    float current_cooldown = max_cooldown;
    Queue<Enemy> seen;
    float detectionRange;
    ArrayList<Bullet> bullets;
    int max_bullets = 1;

    public PlayerController(Player p, UserInterfaceView j) {
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

    public void shoot(float dt) {


        if (current_cooldown <= 0) {

            if (bullets.size() < max_bullets) {
                bullets.add(new Bullet(player.getPosition().x, player.getPosition().y, 10));
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

    public float getCurrent_cooldown() {
        return current_cooldown;
    }

    //
    public void takeDamage(GameObject e) {
        if (player.getHealth() <= 0) {
            player.setHealth(0);
        } else {
            player.setHealth(player.getHealth() - e.getDamage());
        }

    }


}
