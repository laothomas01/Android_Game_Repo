package com.GameVersion2.game.driver;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Graphics2D {
    Sprite sprite;
    Texture texture;
    Color color;
    SpriteBatch spriteBatch;
    private SpriteBatch fontSpriteBatch;
    private BitmapFont font;

    public Graphics2D() {
        font = new BitmapFont();
        fontSpriteBatch = new SpriteBatch();
        texture = new Texture("circle.png");
        sprite = new Sprite(texture);
        color = new Color();
        spriteBatch = new SpriteBatch();
    }

    public Sprite getSprite() {
        return this.sprite;
    }

    public Texture getTexture() {
        return this.texture;
    }

    public void setTexture(String fileName) {
        this.texture = new Texture(fileName);
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public Color getColor() {
        return this.color;
    }

    public SpriteBatch getSpriteBatch() {
        return this.spriteBatch;
    }

    public void drawSprite() {
        this.getSpriteBatch().begin();
        this.getSprite().draw(this.getSpriteBatch());
        this.getSpriteBatch().end();
    }

    public BitmapFont getBitMapFont() {
        return this.font;
    }

    public SpriteBatch getFontSpriteBatch() {
        return this.fontSpriteBatch;
    }


    public void drawFontSprite(String message, float posX, float posY) {
        fontSpriteBatch.begin();

        this.getBitMapFont().draw(this.getFontSpriteBatch(), message, posX, posY);

        fontSpriteBatch.end();
    }


}
