package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class PlayerHealthBar {

    Player player;  //connected player
    Color cl;       //color of health bar
    int currentHealth;  //current health points
    int maxHealth = 500;
    Sprite sprite;
    Texture tex;

    public PlayerHealthBar(Player player){
        this.player = player;
        cl = Color.GREEN;
        currentHealth = maxHealth;

        tex = new Texture(Gdx.files.internal("square.png"));
        sprite = new Sprite(tex, 0, 0, maxHealth, 50);
    }

    public void draw(Batch batch) {
        batch.begin();
        batch.setColor(cl);
        sprite.setScale(maxHealth, 50);
        batch.draw(tex, 250,100, currentHealth, 50);
        batch.end();
    }

    public void changeColor(Color newCL){
        this.cl = newCL;
    }
    
    public int getCurrentHealth()
    {
        currentHealth = player.getPlayerHealth();
        return currentHealth;
    }

    public void setCurrentHealth(int newHealth)
    {
        if(newHealth < maxHealth)
        {
            this.currentHealth = newHealth;
        }
        else
        {
            this.currentHealth = maxHealth;
        }

    }

    public void dispose(){
        tex.dispose();
    }


}
