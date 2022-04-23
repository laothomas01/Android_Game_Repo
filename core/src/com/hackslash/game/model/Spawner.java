package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Spawner extends GameObject {
    Random rand = new Random();
    float width = Gdx.graphics.getWidth();
    float height = Gdx.graphics.getHeight();
    float NUMBER_OF_ENEMIES = 5;
    float wait_time = 5;
    float time_aux = 0;

    public Spawner() {

    }
//
//    ArrayList<Enemy> enemies;
//
//    public ArrayList<Enemy> init_enemy_spawner() {
//        enemies = new ArrayList<>();
////        for (int i = 0; i < 10; i++) {
//        Enemy e = new Enemy(rand.nextInt((int) width), rand.nextInt((int) height), rand.nextInt(100), 1, 20, 1);
//        enemies.add(e);
////        }
//        return enemies;
//    }
//
////    public void spawn_enemies(ArrayList<Enemy> e, ShapeRenderer sr) {
////        for (Enemy enemy : e) {
////            enemy.draw();
////        }
////        Iterator<Enemy> iter = enemies.iterator();
////        while (iter.hasNext()) {
////            Enemy e = iter.next();
////
////        }
//    }
//

}
