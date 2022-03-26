package com.hackslash.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class HackSlash extends ApplicationAdapter {

    ShapeRenderer shape;
    Enemy enemy;

    @Override
    public void create() {
        shape = new ShapeRenderer();
        enemy = new Enemy(150, 200, 70, 20, 10);
    }

    @Override
    //renders the application 60 frames per second.

    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        enemy.update();
        shape.begin(ShapeRenderer.ShapeType.Filled);
        enemy.draw(shape);
        shape.end();
    }


}
