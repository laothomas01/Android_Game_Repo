package com.hackslash.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Enemy extends GameObject {


    public Enemy(float x, float y, float size, float xSpeed, float ySpeed) {



    }

    public void update() {

        /**
         * collision detection 101:
         *
         *  if you want to have your ball's size act as a collider, think about how the ball size affects the current coordinate position
         *
         *  if ball starts at (0,0) and my ball size is at 1, the position i want my ball to bounce off of is the outer most edge of the object.
         *
         *  the ball size stops at 1.
         *
         *  so logically, you would update your checked coordinate to be at the edge of your ball instead of at (0,0).
         *
         *  when x = 1, ball size = 1, and my width = 2, add x + size = 2.
         *
         *  if(x + size > width): invert speed.
         *
         *  note: the bounce effect is just an update of both the x and y coordinates(like a vector).
         *
         *
         * when going left: make sure to do x - size
         * when going right: make sure to do x + size
         *
         * because.. directions.
         *
         * the 10 in my collision right now is just an offset.
         */


    }

    public void draw(ShapeRenderer shape) {
    }



}
