package com.hackslash.game.controller;

import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.UI_View;

import java.util.ArrayList;
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

public class Player_Controller extends Game_Object_Controller {
    UI_View joyStick;
    Player player;
    float max_cooldown = 1f;
    float current_cooldown = max_cooldown;
    Queue<Enemy> seen;
    ArrayList<Bullet> bullets;
    int max_bullets = 1;


    public Player_Controller(Player p, UI_View j) {
        player = p;
        joyStick = j;
//        seen = new LinkedList<>();
//        bullets = new ArrayList<>();
    }

    //
//
//    public void move(float dt) {
//        player.setDx(joyStick.get_touchpad_x_input() * player.getSpeed() * dt);
//        player.setDy(joyStick.get_touchpad_y_input() * player.getSpeed() * dt);
//        player.setVelocity(new Vector2(player.getDx(), player.getDy()));
//        player.setPosition(player.getPosition().add(player.getVelocity()));
//    }
//
//    public boolean detectEnemy(Enemy e) {
//        if (Vector2.dst(player.getPosition().x, player.getPosition().y, e.getPosition().x, e.getPosition().y) < 1000f) {
//            return true;
//        }
//        return false;
//    }
//
//    public void storeSeenEnemy(Enemy e) {
//
//        if (!seen.contains(e)) {
//            seen.add(e);
//        }
//    }
//
//
//    public void shoot(float dt) {
//
//
//        if (current_cooldown <= 0) {
//
//            if (bullets.size() < max_bullets) {
//                bullets.add(new Bullet(player.getPosition().x, player.getPosition().y, 10));
//            }
//            current_cooldown = max_cooldown;
//        } else {
//            current_cooldown -= dt;
//        }
//    }
//
//    public Queue<Enemy> get_Seen_Enemies() {
//        return seen;
//    }
//
//    public ArrayList<Bullet> getBullets() {
//        return bullets;
//    }
//
//    public float getCurrent_cooldown() {
//        return current_cooldown;
//    }
//
//    //
//    public void takeDamage(Game_Object e) {
//        if (player.getCurrent_health() <= 0) {
//            player.setCurrent_health(0);
//        } else {
//            player.setCurrent_health(player.getCurrent_health() - e.getCurrent_damage());
//        }
//
//    }
//
//
//    public void setCurrent_Cooldown(float percentage, float cd) {
//
//
//    }


}
