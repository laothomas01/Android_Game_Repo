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
import com.badlogic.gdx.utils.ShortArray;
import com.hackslash.game.driver.HackAndSlash;

public class Player extends GameObject {

    float maxSpeed;


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


        maxSpeed = 300;


    }

    public void update(float dt) {


        //set shape
        setShape();


    }


    public void draw(ShapeRenderer sr) {
        sr.setColor(1, 1, 1, 1);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(0, 1, 0, 1);
        sr.polygon(vertices.toArray());
        setShape();


        /**
         * not sure how this code works. still trying to implement it but this loop draws the shape of our player
         */
//        for (int i = 0, j = shape_x.length - 1; i < shape_x.length;
//             j = i++) {
//            sr.line(shape_x[i], shape_y[i], shape_x[j], shape_y[j]);
//        }

        sr.end();
    }

    void setShape() {


        center = new Vector2(x, y);
        vertices = new FloatArray(new float[]{center.x, center.y + 50, center.x + 50, center.y, center.x, center.y - 50, center.x - 50, center.y});



        /**
         * ----------------------------------------------------------------------------
         */

        /**
         * all this code just to fill in a polygon. F..M...L... :( !
         */
        polyBatch = new PolygonSpriteBatch();
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(1, 1, 1, 1);
        pix.fill();
        texture = new Texture(pix);
        TextureRegion textureRegion = new TextureRegion(texture);
        EarClippingTriangulator triangulator = new EarClippingTriangulator();
        ShortArray triangleIndices = triangulator.computeTriangles(vertices);
        PolygonRegion polyReg = new PolygonRegion(textureRegion, vertices.toArray(), triangleIndices.toArray());
        polySprite = new PolygonSprite(polyReg);
        polyBatch.begin();
        polySprite.draw(polyBatch);
        polyBatch.end();


        /**
         * ----------------------------------------------------------------------------
         */
    }

}
