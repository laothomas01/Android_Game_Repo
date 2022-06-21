package com.hackslash.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.game_UI_view;

/**
 * all updates to the player will occur
 */
public class Player_Controller {
    game_UI_view joyStick;
    Player player;


    public Player_Controller(Player p, game_UI_view j) {
        player = p;
        joyStick = j;
    }


    public void update(float dt) {
        move(dt);
    }

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


}
