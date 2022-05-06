package com.hackslash.game.model;

<<<<<<< HEAD
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Spawner extends GameObject {
    float wait_time = 8;
=======
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Spawner extends GameObject {
    float wait_time = 3;
>>>>>>> working_on_bullet_shooting_branch
    float time_aux = 0;

    public Spawner() {

    }

    public Spawner(int posX, int posY) {
        x = posX;
        y = posY;
    }

<<<<<<< HEAD
    public void spawnEnemies(ArrayList<Enemy> e, float deltaTime, Player player, ShapeRenderer sr) {
=======
    public void spawnEnemies(ArrayList<Enemy> e, float deltaTime, Player player, SpriteBatch batch) {
>>>>>>> working_on_bullet_shooting_branch
        /**
         * Every 5 seconds, the
         */
        if (time_aux >= wait_time) {
            for (int i = 0; i < 1f; i++) {
<<<<<<< HEAD
                e.add(new Enemy(get_X_Spawn_Position(), get_Y_Spawn_Position(), (int) Math.floor(Math.random() * (200 - 10 + 1) + 10), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), 1));
=======
                e.add(new Enemy(get_X_Spawn_Position(), get_Y_Spawn_Position(), (int) Math.floor(Math.random() * (200 - 10 + 1) + 10), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), 3));
>>>>>>> working_on_bullet_shooting_branch
            }


            time_aux = 0;
        } else {
            time_aux += deltaTime;
        }
        for (Enemy enemies : e) {
<<<<<<< HEAD
            enemies.draw(sr);
=======
            enemies.draw(batch);
>>>>>>> working_on_bullet_shooting_branch
            enemies.update(deltaTime, player);
        }
    }

<<<<<<< HEAD
=======

>>>>>>> working_on_bullet_shooting_branch
    public float get_X_Spawn_Position() {
        return x;
    }

    public float get_Y_Spawn_Position() {
        return y;
    }


<<<<<<< HEAD
=======

>>>>>>> working_on_bullet_shooting_branch
}
