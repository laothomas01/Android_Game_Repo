package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerHealthBar {

//    Player player;  //connected player
//    Color cl;       //color of health bar
//    float currentHealth;  //current health points
//    int maxHealth = 500;
//    Sprite sprite;
//    Texture tex;
//
//    public PlayerHealthBar(Player player) {
//        this.player = player;
//        cl = Color.GREEN;
//        currentHealth = maxHealth;
//
//        tex = new Texture(Gdx.files.internal("square.png"));
//        sprite = new Sprite(tex, 0, 0, maxHealth, 50);
//    }
//
//    public void draw(Batch batch) {
//        batch.begin();
//        batch.setColor(cl);
//        sprite.setScale(maxHealth, 50);
//        batch.draw(tex, 250, 100, currentHealth, 50);
//        batch.end();
//    }
//
//    public void changeColor(Color newCL) {
//        this.cl = newCL;
//    }
//
//    public float getCurrentHealth() {
//        return currentHealth;
//    }
//
//    public void subtractHealth() {
//        float temp = currentHealth;
//        temp -= .5f;
//        setCurrentHealth(temp);
//    }
//
//    public void setCurrentHealth(float newHealth) {
//        if (newHealth < maxHealth && newHealth > 0) {
//            this.currentHealth = newHealth;
//            if (newHealth < (maxHealth / 4f)) {
//                this.cl = Color.RED;
//            } else if (newHealth < (maxHealth / 2f)) {
//                this.cl = Color.ORANGE;
//            } else if (newHealth < (maxHealth / 1.5f)) {
//                this.cl = Color.YELLOW;
//            } else {
//                this.cl = Color.GREEN;
//            }
//        } else if (newHealth <= 0) {
//            this.cl = Color.BLACK;
//            currentHealth = 0;
//        } else {
//        }
//
//    }
//
//    public void dispose() {
//        tex.dispose();
//    }


}
