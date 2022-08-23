package com.GameVersion2.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.math.Vector2;

class Physics2D {

    float COLLISION_COEF = 1.0f;
    Vector2 normal;
    Vector2 temp;
    Vector2 newVelocity;
    Vector2 position;
    Vector2 velocity;
    Vector2 acceleration;
    Vector2 angularVelocity;
    float radians;
    float speed;
    float rotateSpeed;
    float width;
    float height;
    float mass;
    float distance;
    float impactDistance;


    public Physics2D() {
        position = new Vector2(0, 0);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 0);
        angularVelocity = new Vector2(0, 0);
        width = 0;
        height = 0;
        mass = 1.0f;
        radians = 0f;
    }

    public boolean hasCollided() {
        return false;
    }
}

public class main extends ApplicationAdapter {

}
