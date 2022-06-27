package com.hackslash.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.Game_UI_View;

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

public class Player_Controller {
    Game_UI_View joyStick;
    Player player;
    float max_cooldown = 2f;
    float current_cooldown = max_cooldown;
    Queue<Enemy> seen;

    public Player_Controller(Player p, Game_UI_View j) {
        player = p;
        joyStick = j;
        seen = new LinkedList<>();
    }


//    public void update(float dt) {
//        move(dt);
////    }

    public void move(float dt) {
        /**
         velocity_x = x_direction * speed * delta_time
         velocity_y = y_direction * speed * delta_time
         velocity = vector2(velocity_x,velocity_y)
         player_new_position = old_position + velocity
         */
        player.set_dx(joyStick.get_touchpad_x_input() * player.getSpeed() * dt);
        player.set_dy(joyStick.get_touchpad_y_input() * player.getSpeed() * dt);
        player.set_Velocity(player.getDx(), player.getDy());
        player.setPosition(player.getPosition().add(player.getVelocity()));
    }

    public boolean detectEnemy(Enemy e) {
        if (Vector2.dst(player.getPosition().x, player.getPosition().y, e.getPosition().x, e.getPosition().y) < 500f) {
            return true;
        }
        return false;
    }

    public void storeSeenEnemy(Enemy e) {

        if (!seen.contains(e)) {
            seen.add(e);
        }
    }


    public void shoot(float dt, ArrayList<Bullet> bullets) {

        if (current_cooldown <= 0) {

            bullets.add(new Bullet(player.getPosition().x, player.getPosition().y));
            current_cooldown = max_cooldown;
        } else {
//            }
            current_cooldown -= dt;
        }
    }

    public Queue<Enemy> get_Seen_Enemies() {
        return seen;
    }


}
