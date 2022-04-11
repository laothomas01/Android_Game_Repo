package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.FloatArray;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.ShortArray;
import com.hackslash.game.driver.HackAndSlash;

public class Player extends GameObject {

    public float maxSpeed;


    Vector2 player_position;
    Vector2 center;
    FloatArray vertices;

    /**
     * Filling in the polygon
     */
    Texture texture;
    PolygonSprite polySprite;
    PolygonSpriteBatch polyBatch;

    public Player() {
        x = Gdx.graphics.getWidth() / 2;
        y = Gdx.graphics.getHeight() / 2;
        size = 25;
        //will update this over time for movement.
        /**
         * useful for vector functions
         */
        player_position = new Vector2(x, y);

        speed = 10;
        maxSpeed = 300;


    }

    public void update(float dt) {


    }


    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.rect(player_position.x, player_position.y, 20, 20);
        sr.end();
//        sr.begin(ShapeRenderer.ShapeType.Line);
//        sr.setColor(0, 1, 0, 1);
//        setShape();
//        sr.polygon(vertices.toArray());
//
//
//        sr.end();
    }

    void setShape() {


//        center = new Vector2(player_position.x, player_position.y);
//        vertices = new FloatArray(new float[]{center.x, center.y + size, center.x + size, center.y, center.x, center.y - size, center.x - size, center.y});
//
//
//        /**
//         * ----------------------------------------------------------------------------
//         */
//
//        /**
//         * all this code just to fill in a polygon. F..M...L... :( !
//         */
//        polyBatch = new PolygonSpriteBatch();
//        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
//        pix.setColor(1, 1, 1, 1);
//        pix.fill();
//        texture = new Texture(pix);
//        TextureRegion textureRegion = new TextureRegion(texture);
//        EarClippingTriangulator triangulator = new EarClippingTriangulator();
//        ShortArray triangleIndices = triangulator.computeTriangles(vertices);
//        PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices.toArray(), triangleIndices.toArray());
//        polySprite = new PolygonSprite(polyReg);
//        polyBatch.begin();
//        polySprite.draw(polyBatch);
//        polyBatch.end();
//

        /**
         * ----------------------------------------------------------------------------
         */
    }

    public float getXPosition() {
        return player_position.x;
    }

    public void setXPosition(float dt) {
        player_position.x = dt;
    }

    public void setYPosition(float dt) {
        player_position.y = dt;
    }


    public float getPlayerSpeed() {
        return speed;
    }

    public float getYPosition() {
        return player_position.y;
    }

    public Vector2 getPlayerPosition() {
        return player_position;
    }

    public void handleInputs(float dt) {
        
    }

}
