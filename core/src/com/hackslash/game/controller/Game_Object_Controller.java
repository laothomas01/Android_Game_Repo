package com.hackslash.game.controller;

import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.Game_Object;

public class Game_Object_Controller {

    public Game_Object_Controller() {

    }

    public boolean hasCollided(Game_Object a, Game_Object b) {

        //CIRCLE ON CIRCLE SHAPE DETECTION

        //checking which kind of object is being passed into this method.
        //should be able to check BULLETS,ENEMY,PLAYER, and EXP OBJECTS
        if ((a.getObjectType().equals("ENEMY") && b.getObjectType().equals("BULLET")) || (a.getObjectType().equals("BULLET") && b.getObjectType().equals("ENEMY"))) {

            float dist = Vector2.dst(a.getPosition().x, a.getPosition().y, b.getPosition().x, b.getPosition().y);
//            System.out.println("DISTANCE:" + dist);
            float total_radius = b.getCurrent_size() + a.getCurrent_size();
//            System.out.println("TOTAL RADIUS:" + total_radius);
            if (dist < total_radius) {
                return true;
            } else {
                return false;
            }
        }
        //SQUARE OR RECTANGLE ON CIRCLE SHAPE DETECTION
        if ((a.getObjectType().equals("PLAYER") && b.getObjectType().equals("ENEMY")) || (a.getObjectType().equals("ENEMY") && b.getObjectType().equals("PLAYER"))) {
            //Edge Detection//
            //Note*
            // Make sure you understand this code/math
            //Website reference: http://www.jeffreythompson.org/collision-detection/circle-rect.php

            //------------------------------------------------------------
            float testX = a.getPosition().x;
            float testY = a.getPosition().y;
            if (a.getPosition().x < b.getPosition().x) {
                testX = b.getPosition().x; // left edge
            } else if (a.getPosition().x > b.getPosition().x + b.getSprite().getWidth()) {
                testX = b.getPosition().x + b.getSprite().getWidth(); // right edge
            }

            if (a.getPosition().y < b.getPosition().y) {
                testY = b.getPosition().y; // top edge
            } else if (a.getPosition().y > b.getPosition().y + b.getSprite().getHeight()) {
                testY = b.getPosition().y + b.getSprite().getHeight(); // bottom edge
            }
            //------------------------------------------------------------

            //  Collision Detection: use Pythagorean Theorem using circle's center

            //  and the two edge we found above
            float distX = a.getPosition().x - testX;
            float distY = a.getPosition().y - testY;
            //Pythagorean Theorem
            double distance = Math.sqrt((distX * distX) + (distY * distY));
            if (distance <= a.getSprite().getWidth()) {
                return true;
            } else {
                return false;
            }

        }


        return false;


    }
}
