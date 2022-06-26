package com.hackslash.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.Game_UI_View;

import java.util.ArrayList;

/**
 * all updates to the player will occur
 */

public class Player_Controller {
    Game_UI_View joyStick;
    Player player;
    float max_cooldown = 2f;
    float current_cooldown = max_cooldown;

    public Player_Controller(Player p, Game_UI_View j) {
        player = p;
        joyStick = j;

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
        if (Vector2.dst(player.getPosition().x, player.getPosition().y, e.getPosition().x, e.getPosition().y) < 1000f) {
            return true;
        }
        return false;
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


}
