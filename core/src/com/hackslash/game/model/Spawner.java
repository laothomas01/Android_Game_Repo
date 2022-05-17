package com.hackslash.game.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Spawner extends GameObject {
    float wait_time = 5;
    float time_aux = 0;

    public Spawner() {

    }

    public Spawner(int posX, int posY) {
        x = posX;
        y = posY;
    }

    public void spawnEnemies(ArrayList<Enemy> e, float deltaTime, Player player, SpriteBatch batch) {
        /**
         * Every 5 seconds, the
         */
        if (time_aux >= wait_time) {
//            for (int i = 0; i < 1f; i++) {
//            e.add(new Enemy(get_X_Spawn_Position(), get_Y_Spawn_Position(), (int) Math.floor(Math.random() * (200 - 10 + 1) + 10), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), ));
//            }


            time_aux = 0;
        } else {
            time_aux += deltaTime;
        }

    }


    public float get_X_Spawn_Position() {
        return x;
    }

    public float get_Y_Spawn_Position() {
        return y;
    }


}
