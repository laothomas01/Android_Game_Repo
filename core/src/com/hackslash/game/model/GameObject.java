package com.hackslash.game.model;

import com.badlogic.gdx.math.Vector2;

public class GameObject {
    /**
     * MOVEMENT
     * x: x coordinate
     * y: y coordinate
     * dx: x traveling direction
     * dy: y traveling direction
     * <p>
     * Enemy Shape: circles
     * Player Shape: square
     */

    float x;
    float y;

    float dx;
    float dy;

    float speed;
    float size;
    float damage;
    float health;

    Vector2 position;


}
