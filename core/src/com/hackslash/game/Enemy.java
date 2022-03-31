package com.hackslash.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Enemy {
    //x position
    int x;
    //y position
    int y;


    int size;
    int xSpeed;
    int ySpeed;


    public Enemy(int x, int y, int size, int xSpeed, int ySpeed) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

    }

    //updating the movement of our object
    public void update() {
        x += xSpeed;
        y += ySpeed;
        //if object goes far off to the left or far off to the right side of the screen

        //reverse the speed
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
        if (x - size - 10 <= 0 || x + 10 + size > Gdx.graphics.getWidth()) {
            //invert the speed
            xSpeed = -xSpeed;
        }

        if (y - size - 10 <= 0 || y+ 10 + size > Gdx.graphics.getHeight()) {
            ySpeed = -ySpeed;
        }

    }

    public void setSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     * based on the position being updated by the speed 60 frames per second, the draw function redraws the circle at the new position
     */

    public void draw(ShapeRenderer shape) {
        shape.circle(x, y, size);
    }

}
