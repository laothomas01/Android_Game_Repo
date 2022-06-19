package com.hackslash.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.game_UI_view;

public class Player_Controller {
    game_UI_view joyStick;
    Player player;

    float dx = 0;
    float dy = 0;

    public Player_Controller(Player p, game_UI_view j) {
        player = p;
        joyStick = j;

    }


    public void update(float dt) {
        move(dt);
    }

    //

    public void move(float dt) {
        /**
         * velocity = delta-position  * magnitude of velocity / delta-time
         * => (player-x-position - joystick_x_position) / (player-y-position - joystick_y_position)  * speed * (1 sec / 60 frames)

         x direction: x joystick input * velocity magnitude * delta-time
         y direction: y stick input * magnitude * velocity magnitude * delta-time

         velocity: (x direction,y direction)

         player_position = current_position + player_velocity
         */
        dx = joyStick.get_touchpad_x_input() * player.getSpeed() * dt;
        dy = joyStick.get_touchpad_y_input() * player.getSpeed() * dt;
//        player.set_Velocity(dx, dy);
//        player.set_X_Position(player.get_X_Velocity());
//        player.set_Y_Position(player.get_Y_Velocity());
//

        player.set_Velocity(dx, dy);
        player.setPosition(player.getPosition().add(player.getVelocity()));
//        player.set_X_Position(player.get_X_Velocity());
//        player.set_Y_Position(player.get_Y_Velocity());
//        player.set_X_Position(dx);
//        player.set_Y_Position(dy);
//        float new_dy = (joyStick.get_touchpad_y_input() + player.getPosition().y) * player.getSpeed() * dt;
//        player.setPosition(new Vector2(new_dx, new_dy));
//        System.out.println("DIRECTION X:" + new_dx + "DIRECTION Y:" + new_dy);
//        System.out.println("JOYSTICK X:" + joyStick.get_touchpad_x_input());
//        System.out.println("JOYSTICK Y:" + joyStick.get_touchpad_y_input());
//        player.setDx(new_dx);
//        player.setDy(new_dy);
//        player.set

//        System.out.println("X:" + player.getX());
//        System.out.println("JOY STICK X:" + joyStick.get_touchpad_x_input());
//        System.out.println("SPEED:" + player.getSpeed());
//        player.set_X_Position(player.getX() + joyStick.get_touchpad_x_input() * player.getSpeed());

//        player.set_X_Position(player.getX() + joyStick.get_touchpad_x_input() * player.getSpeed() * dt);
//        System.out.println(player.getX());
//        System.out.println("OLD:" + player.getDx());
//        float new_dx = player.get_X_Velocity() + joyStick.get_touchpad_x_input() * player.getSpeed();
//        player.set_Velocity(new_dx, player.get_Y_Velocity());
//        player.set_X_Position(player.getDx());
        //        player.set_dx(new_dx);
//        System.out.println("NEW:" + new_dx);

//
////        float new_dy = player.get_Y_Velocity() + joyStick.get_touchpad_y_input() * player.getSpeed() * dt;
////        player.set_dx(new_dx);
////        player.set_dy(new_dy);
//        player.set_X_Position(new_dx);
//        player.set_Y_Position(player.getDy());
    }


}
