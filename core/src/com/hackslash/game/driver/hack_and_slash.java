package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.hackslash.game.controller.Bullet_Controller;
import com.hackslash.game.controller.Enemy_Controller;
import com.hackslash.game.model.Bullet;
import com.hackslash.game.model.Enemy;
import com.hackslash.game.model.Player;
import com.hackslash.game.view.Game_UI_View;
import com.hackslash.game.view.Game_Object_View;
import com.hackslash.game.controller.Player_Controller;

import java.util.ArrayList;

public class hack_and_slash extends ApplicationAdapter {
    Game_Object_View game_object_view;
    Game_UI_View game_ui_view;
    Player_Controller player_controller;
    Enemy_Controller enemy_controller;
    Bullet_Controller bullet_controller;
    Player player;
    Enemy e1;
    Enemy e2;
    Enemy e3;
    Enemy e4;
    Enemy e5;
    Enemy e6;
    Enemy e7;
    Enemy e8;
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> bulletsToRemove;
//    ArrayList<Bullet> bullets;

    //time passed between the last frame and the current frame
    float deltaTime;


//    private Stage stage;
//    ShapeRenderer sr;
//    Player player;
//    float player_x_Move;
//    float player_y_Move;
//    // Player's Health Bar
//    private PlayerHealthBar playerHB;

    //    OrthographicCamera cam;
//    OrthographicCamera UIcam;
//    float MAX_ENEMIES;
//    /**
//     * -------TOUCH PAD------
//     */
//    private Touchpad touchpad;
//    private Touchpad.TouchpadStyle touchpadStyle;
//    private Skin skin;
//    private Drawable touchBackground;
//    private Drawable touchKnob;
//    /**
//     * -----------------------
//     */
//
//    float deltaTime;
//    float spawnTimer;
//    float spawnTime;
//    float lerp;
//    float rageTime;
//    float rageTimer;
//    float currentGameTime;
//    float MAXIMUM_ENEMY_SIZE;
//
//    /**
//     * --------------Initialize Spawners---------
//     */
//    ArrayList<Enemy> enemies;
//
//    /**
//     * --------------------------------
//     */
//
//    enum Screen {
//        MENU, MAIN_GAME, GAME_OVER;
//    }
//
//    Screen currentScreen = Screen.MENU;
//    BitmapFont font;
//    boolean GAME_PAUSED;


    public void create() {
        player = new Player();
        //Testing player's enemy detection
        e1 = new Enemy(player.getPosition().x - 100, player.getPosition().y, 100f, 0.5f, 10, 10);
//        e2 = new Enemy(player.getPosition().x + 100, player.getPosition().y, 100f, 1, 10, 1);
//        e3 = new Enemy(player.getPosition().x - 200, player.getPosition().y, 100f, 1, 10, 1);
//        e4 = new Enemy(player.getPosition().x + 200, player.getPosition().y, 100f, 1, 10, 1);
//        e5 = new Enemy(player.getPosition().x - 300, player.getPosition().y, 100f, 1, 10, 1);
//        e6 = new Enemy(player.getPosition().x + 300, player.getPosition().y, 100f, 1, 10, 1);
//        e7 = new Enemy(player.getPosition().x - 400, player.getPosition().y, 100f, 1, 10, 1);
//        e8 = new Enemy(player.getPosition().x + 400, player.getPosition().y, 100f, 1, 10, 1);

//        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        enemies.add(e1);
//        enemies.add(e2);
//        enemies.add(e3);
//        enemies.add(e4);
//        enemies.add(e5);
//        enemies.add(e6);
//        enemies.add(e7);
//        enemies.add(e8);
        game_object_view = new Game_Object_View();
        game_ui_view = new Game_UI_View(player);
        game_ui_view.init_touchpad();
        game_ui_view.init_healthbar();
        player_controller = new Player_Controller(player, game_ui_view);
        enemy_controller = new Enemy_Controller();
        bullet_controller = new Bullet_Controller();
        bulletsToRemove = new ArrayList<>();
//        game_ui_view = new game_UI_view();
//        game_ui_view.init_game_UI_View();


//        player_controller = new Player_Controller();
//        MAXIMUM_ENEMY_SIZE = 500;
//        lerp = 0.1f;
//        rageTime = 10;
//        rageTimer = 0;
//        spawnTime = 3f;
//        spawnTimer = 0;
//        currentGameTime = 0;
//        MAX_ENEMIES = 1000;
//        sr = new ShapeRenderer();
//        player = new Player();
//        playerHB = new PlayerHealthBar(player);

//        font = new BitmapFont();
//
//        skin = new Skin();
//        skin.add("touchBackground", new Texture("touchBackground.png"));
//        skin.add("touchKnob", new Texture("touchKnob.png"));
//        /**
//         //         * --------Create a Stage and add TouchPad------------
//         //         */
//        touchBackground = skin.getDrawable("touchBackground");
//        touchKnob = skin.getDrawable("touchKnob");
//
//        touchpadStyle = new Touchpad.TouchpadStyle();
//        touchpadStyle.background = touchBackground;
//        touchpadStyle.knob = touchKnob;
//
//        touchpad = new Touchpad(10, touchpadStyle);
//        touchpad.setBounds(15, 15, 200, 200);
//
//
//        stage = new Stage();
////        /**
////         * -----------------------------------------------------
////         */
//        Gdx.input.setInputProcessor(stage);
//
//
//        enemies = new ArrayList();
//
//        /**
//         * Use one camera to follow the player
//         * -set the camera's projection before drawing the object you want the camera to focus on
//         */
//        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//
//
//        /**
//         * Use another camera to focus on the UI
//         * -set the camera's projection before drawing the object you want the camera to focus on
//         */
//        UIcam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//        UIcam.position.set(UIcam.viewportWidth / 2f, UIcam.viewportHeight / 2f, 0);
//        UIcam.update();
    }

    public void restartGame() {
//        player = new Player();
//        playerHB = new PlayerHealthBar(player);
//
//        enemies = new ArrayList();
    }


    /**
     * Method called to check that none of the enemies in the array list of enemies are
     * touching the player character. if none are touching- do nothing. if an enemy
     * is touching the player character, subtract health from the player.
     */
//    public void check_Player_Enemy_Overlap(ArrayList<Enemy> enemyList) {
////        for (Enemy e : enemyList) {
////
////            if (player.getSprite().getBoundingRectangle().overlaps(e.getSprite().getBoundingRectangle())) {
////                playerHB.subtractHealth();
////            }
////
////        }
//    }
//
//    public void check_Bullet_Enemy_Overlap(ArrayList<Bullet> bullets) {
//        for (Bullet b : bullets) {
//            for (int i = 0; i < enemies.size(); i++) {
//                if (b.intersect(enemies.get(i))) {
//                    enemies.get(i).takeDamage(b.getDamage());
//                }
//            }
//        }
//    }

    /**
     * Method called by the game loop from the application every time rendering should be performed. Game logic updates are usually also performed in this method.
     */

    //Note to self:
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        //Refresh the screen every frame
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //handle user input via joystick UI
        game_ui_view.update_touchpad();
        game_ui_view.updatePlayerHealthBar(game_ui_view.getSpriteBatch());

        game_object_view.draw_player(player.getSpriteBatch(), player);
        //display enemies and check if player detects enemy object
        for (Enemy e : enemies) {
            game_object_view.draw_enemy(e.getSpriteBatch(), e);
            enemy_controller.moveToPlayer(deltaTime, e, player);
            //for each seen enemy, player will store seen enemy into a queue
            if (player_controller.detectEnemy(e)) {
                player_controller.storeSeenEnemy(e);
            }

        }
//
//        //front of the queue
        Enemy current_Seen_Enemy = player_controller.get_Seen_Enemies().peek();
//
        if (current_Seen_Enemy != null) {
            //if currently seen enemy is detected,shoot
            if (player_controller.detectEnemy(current_Seen_Enemy)) {
                player_controller.shoot(deltaTime);
            }
        }

        //testing enemy collision with player
        System.out.println("CURRENT HEALTH:" + player.getCurrent_health());

        //check collision detection
        for (Enemy e : enemies) {
            e.getSpriteBatch().begin();

            if (e.hasCollided(e, player)) {
                e.getSpriteBatch().setColor(Color.GREEN);
//                player_controller.takeDamage(e);

            } else {
                e.getSpriteBatch().setColor(Color.WHITE);
            }
            e.getSpriteBatch().end();
        }
//        player.getSpriteBatch().begin();
//        if (player.hasCollided(enemies.get(0), player)) {
//            player.getSpriteBatch().setColor(Color.RED);
//        } else {
//            player.getSpriteBatch().setColor(Color.WHITE);
//        }
//        player.getSpriteBatch().end();
////        game_object_view.confirm_detection(game_object_view., player, current_Seen_Enemy);


        //looping through list of bullets, if bullets collided, remove bullet object
        for (Bullet b : player_controller.getBullets()) {
            //if the player has not detected the enemy for a while, take detected enemy off the
            if (b.hasCollided(current_Seen_Enemy, b)) {
//                enemies.remove(current_Seen_Enemy);
                player_controller.get_Seen_Enemies().remove(current_Seen_Enemy);
                b.setCurrent_speed(0);
                bulletsToRemove.add(b);
            }
//
//            game_object_view.draw_bullets(game_object_view.getSpriteBatch(), b);
//            //move bullets towards enemy
//            bullet_controller.moveTowardEnemy(deltaTime, current_Seen_Enemy, b);
//        }


        //we do not want to delete bullet objects while looping through the array
        player_controller.getBullets().removeAll(bulletsToRemove);
        player_controller.move(deltaTime);
//        System.out.println("SIZE:" + player_controller.getBullets().size());
        /**
         * Sliders for game testing
         */
    }

    /**
     * This method is called every time the game screen is re-sized and the game is not in the paused state. It is also called once just after the create() method.
     * The parameters are the new width and height the screen has been resized to in pixels.
     */
    public void resize(int width, int height) {
//        stage.getViewport().update(width, height, true);
    }

    /**
     * On Android this method is called when the Home button is pressed or an incoming call is received. On desktop this is called when the window is minimized and just before dispose() when exiting the application.
     * A good place to save the game state.
     */
    public void pause() {
//        GAME_PAUSED = !GAME_PAUSED;

    }

    /**
     * This method is called on Android, when the application resumes from a paused state, and on desktop when unminimized.
     */
    public void resume() {

    }

    /**
     * Called when the application is destroyed. It is preceded by a call to pause().
     */


    public void dispose() {

//        sr.dispose();
//        stage.dispose();
//        skin.dispose();
//        batch.dispose();
//        player.dispose();
//        playerHB.dispose();
//        enemyTex(enemies);
    }


    public void loadEnemies() {
//        /**
//         * CREATE NEW ENEMIES
//         *
//         * 4 QUADRANTS
//         *
//         * 4 INTERCEPTS
//         *
//         */
//        /**
//         * -------------------------------------------------
//         */
//
//        if (enemies.size() > MAXIMUM_ENEMY_SIZE) {
//            return;
//        } else {
//            enemies.add(new Enemy(2000, 2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(-2000, 2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(-2000, -2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(2000, -2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(0, 2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(0, -2000, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(2000, 0, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//            enemies.add(new Enemy(-2000, 0, MathUtils.random(50, 100), 1, (int) Math.floor(Math.random() * (20 - 5 + 1) + 5), MathUtils.random(1, 3)));
//
//        }

    }

    public void Handle_Seen_Enemies() {
        //using a Queue to maintain the insertion order of my seen enemies

    }

    public void Handle_Unseen_Enemies() {

    }

    public void Player_Bullet_Enemy_Interaction(Enemy curr) {

    }

    /**
     * method used to cycle through enemy arraylist to dispose of textures
     *
     * @param enemyList an arraylist of enemy
     */
//    public void enemyTex(ArrayList<enemy> enemyList) {
//        for (enemy e : enemyList) {
//            e.getTex().dispose();
//        }
//    }


}
