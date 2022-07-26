package com.hackslash.game.controller;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.hackslash.game.model.GameObject;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.UserInterfaceView;

public class GameObjectController {
    UserInterfaceView joyStick;
    Player player;

    public GameObjectController() {

    }

    //obj1 = main object that will updated with the collected information
    //obj2 = the targeted object for this function
    public void moveTowardObject(float dt, GameObject obj1, GameObject obj2) {


        obj1.setRadians(MathUtils.atan2(obj2.getPosition().y - obj1.getPosition().y, obj2.getPosition().x - obj1.getPosition().x));
        obj1.setDx(MathUtils.cos(obj1.getRadians()));
        obj1.setDy(MathUtils.sin(obj1.getRadians()));
        obj1.setVelocity(obj1.getDx(), obj1.getDy());
        obj1.setPosition(obj1.getPosition().x + obj1.getDx() * obj1.getSpeed() * dt, obj1.getPosition().y + obj1.getDy() * obj1.getSpeed() * dt);

    }


    public boolean detectGameObject(GameObject obj1, GameObject obj2) {
        if (Vector2.dst(obj1.getPosition().x, obj1.getPosition().y, obj2.getPosition().x, obj2.getPosition().y) < 500f) {
            return true;
        }
        return false;
    }

    public boolean hasCollided(GameObject a, GameObject b) {

        //CIRCLE ON CIRCLE SHAPE DETECTION

        //checking which kind of object is being passed into this method.
        //should be able to check BULLETS,ENEMY,PLAYER, and EXP OBJECTS
        if ((a.getObjectType().equals("ENEMY") && b.getObjectType().equals("BULLET")) || (a.getObjectType().equals("BULLET") && b.getObjectType().equals("ENEMY"))) {

            float dist = Vector2.dst(a.getPosition().x, a.getPosition().y, b.getPosition().x, b.getPosition().y);
//            System.out.println("DISTANCE:" + dist);
            float total_radius = b.getCurrentSize() + a.getCurrentSize();
//            System.out.println("TOTAL RADIUS:" + total_radius);
            if (dist <= total_radius) {
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


    //    public boolean hasCollided(Game_Object a, Game_Object b) {
//
//        //CIRCLE ON CIRCLE SHAPE DETECTION
//
//        //checking which kind of object is being passed into this method.
//        //should be able to check BULLETS,ENEMY,PLAYER, and EXP OBJECTS
//        if ((a.getObjectType().equals("ENEMY") && b.getObjectType().equals("BULLET")) || (a.getObjectType().equals("BULLET") && b.getObjectType().equals("ENEMY"))) {
//
//            float dist = Vector2.dst(a.getPosition().x, a.getPosition().y, b.getPosition().x, b.getPosition().y);
////            System.out.println("DISTANCE:" + dist);
//            float total_radius = b.getCurrent_size() + a.getCurrent_size();
////            System.out.println("TOTAL RADIUS:" + total_radius);
//            if (dist < total_radius) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        //SQUARE OR RECTANGLE ON CIRCLE SHAPE DETECTION
//        if ((a.getObjectType().equals("PLAYER") && b.getObjectType().equals("ENEMY")) || (a.getObjectType().equals("ENEMY") && b.getObjectType().equals("PLAYER"))) {
//            //Edge Detection//
//            //Note*
//            // Make sure you understand this code/math
//            //Website reference: http://www.jeffreythompson.org/collision-detection/circle-rect.php
//
//            //------------------------------------------------------------
//            float testX = a.getPosition().x;
//            float testY = a.getPosition().y;
//            if (a.getPosition().x < b.getPosition().x) {
//                testX = b.getPosition().x; // left edge
//            } else if (a.getPosition().x > b.getPosition().x + b.getSprite().getWidth()) {
//                testX = b.getPosition().x + b.getSprite().getWidth(); // right edge
//            }
//
//            if (a.getPosition().y < b.getPosition().y) {
//                testY = b.getPosition().y; // top edge
//            } else if (a.getPosition().y > b.getPosition().y + b.getSprite().getHeight()) {
//                testY = b.getPosition().y + b.getSprite().getHeight(); // bottom edge
//            }
//            //------------------------------------------------------------
//
//            //  Collision Detection: use Pythagorean Theorem using circle's center
//
//            //  and the two edge we found above
//            float distX = a.getPosition().x - testX;
//            float distY = a.getPosition().y - testY;
//            //Pythagorean Theorem
//            double distance = Math.sqrt((distX * distX) + (distY * distY));
//            if (distance <= a.getSprite().getWidth()) {
//                return true;
//            } else {
//                return false;
//            }
//
//        }
//
//
//        return false;
//
//
//    }

//    public void moveTowardObject(GameObject obj1, GameObject obj2) {
//
//    }
}
