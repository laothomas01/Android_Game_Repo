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
import com.hackslash.game.view.Bullet_View;
import com.hackslash.game.view.Enemy_View;
import com.hackslash.game.view.Player_View;
import com.hackslash.game.view.UI_View;
import com.hackslash.game.view.Game_Object_View;
import com.hackslash.game.controller.Player_Controller;

import java.util.ArrayList;

public class hack_and_slash extends ApplicationAdapter {
    Player_View playerView;
    Enemy_View enemyView;
    Bullet_View bulletView;
    UI_View ui_view;
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
    ArrayList<Enemy> enemiesToRemove;
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
        e2 = new Enemy(player.getPosition().x + 100, player.getPosition().y, 100f, 1, 10, 1);
        e3 = new Enemy(player.getPosition().x - 200, player.getPosition().y, 100f, 1, 10, 1);
        e4 = new Enemy(player.getPosition().x + 200, player.getPosition().y, 100f, 1, 10, 1);
        e5 = new Enemy(player.getPosition().x - 300, player.getPosition().y, 100f, 1, 10, 1);
        e6 = new Enemy(player.getPosition().x + 300, player.getPosition().y, 100f, 1, 10, 1);
        e7 = new Enemy(player.getPosition().x - 400, player.getPosition().y, 100f, 1, 10, 1);
        e8 = new Enemy(player.getPosition().x + 400, player.getPosition().y, 100f, 1, 10, 1);

//        bullets = new ArrayList<>();

        enemies = new ArrayList<>();
        enemies.add(e1);
        enemies.add(e2);
        enemies.add(e3);
        enemies.add(e4);
        enemies.add(e5);
        enemies.add(e6);
        enemies.add(e7);
        enemies.add(e8);
        playerView = new Player_View();
        enemyView = new Enemy_View();
        bulletView = new Bullet_View();
        ui_view = new UI_View(player);
        ui_view.init_touchpad();
        ui_view.init_healthbar();
        ui_view.init_cameras();
        player_controller = new Player_Controller(player, ui_view);
        enemy_controller = new Enemy_Controller();
        bullet_controller = new Bullet_Controller();
        bulletsToRemove = new ArrayList<>();
        enemiesToRemove = new ArrayList<>();
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
        //HANDLE JOY STICK INPUT
        ui_view.update_touchpad();
        ui_view.updatePlayerHealthBar(ui_view.getSpriteBatch());
        ui_view.update_cams(deltaTime, ui_view.getSpriteBatch());
        playerView.draw_player(player.getSpriteBatch(), player);

        //DRAW ENEMIES
        //HANDLE PLAYER AND ENEMY INTERACTIONS
        for (Enemy e : enemies) {
            //display enemies
            enemyView.draw_enemy(e.getSpriteBatch(), e);
            enemy_controller.moveToPlayer(deltaTime, e, player);
            if (player_controller.detectEnemy(e)) {
                player_controller.storeSeenEnemy(e);
            }
            e.getSpriteBatch().begin();
            if (enemy_controller.hasCollided(e, player)) {
                e.getSpriteBatch().setColor(Color.GREEN);
//                player_controller.takeDamage(e);
            } else {
                e.getSpriteBatch().setColor(Color.WHITE);
            }
            e.getSpriteBatch().end();

        }


        Enemy current_Seen_Enemy = player_controller.get_Seen_Enemies().peek();

        if (current_Seen_Enemy != null) {
            //if currently seen enemy is detected,shoot
            if (player_controller.detectEnemy(current_Seen_Enemy)) {
                player_controller.shoot(deltaTime);
            }
        }
        System.out.println("BULLETS:" + player_controller.getBullets().toString());
        System.out.println("QUEUE:" + player_controller.get_Seen_Enemies().toString());
        //DRAW BULLETS
        //HANDLE BULLET AND ENEMY INTERACTION
        for (Bullet b : player_controller.getBullets()) {
            if (bullet_controller.hasCollided(b, current_Seen_Enemy)) {
                b.setCurrent_speed(0);
                bulletsToRemove.add(b);
                player_controller.get_Seen_Enemies().remove(current_Seen_Enemy);
                enemiesToRemove.add(current_Seen_Enemy);
            }
            bulletView.draw_bullets(b.getSpriteBatch(), b);
            bullet_controller.moveTowardEnemy(deltaTime, current_Seen_Enemy, b);
        }

        //GARBAGE COLLECTION
        enemies.removeAll(enemiesToRemove);
        player_controller.getBullets().removeAll(bulletsToRemove);
        player_controller.move(deltaTime);

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

    }

    public void Handle_Seen_Enemies() {
        //using a Queue to maintain the insertion order of my seen enemies

    }

    public void Handle_Unseen_Enemies() {

    }

    public void Player_Bullet_Enemy_Interaction(Enemy curr) {

    }


}
