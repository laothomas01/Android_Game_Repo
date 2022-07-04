package com.hackslash.game.driver;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
        e1 = new Enemy(player.getPosition().x - 100, player.getPosition().y, 100f, 0, 10, 1);
        e2 = new Enemy(player.getPosition().x + 100, player.getPosition().y, 100f, 0, 10, 1);
        e3 = new Enemy(player.getPosition().x - 200, player.getPosition().y, 100f, 0, 10, 1);
        e4 = new Enemy(player.getPosition().x + 200, player.getPosition().y, 100f, 0, 10, 1);
        e5 = new Enemy(player.getPosition().x - 300, player.getPosition().y, 100f, 0, 10, 1);
        e6 = new Enemy(player.getPosition().x + 300, player.getPosition().y, 100f, 0, 10, 1);
        e7 = new Enemy(player.getPosition().x - 400, player.getPosition().y, 100f, 0, 10, 1);
        e8 = new Enemy(player.getPosition().x + 400, player.getPosition().y, 100f, 0, 10, 1);

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
        game_object_view = new Game_Object_View();
        game_ui_view = new Game_UI_View();
        game_ui_view.init_game_UI_View();
        player_controller = new Player_Controller(player, game_ui_view);
        enemy_controller = new Enemy_Controller();
        bullet_controller = new Bullet_Controller();
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
    public void render() {
        deltaTime = Gdx.graphics.getDeltaTime();
        //Refresh the screen every frame
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game_ui_view.update_touchpad();
        game_object_view.draw_player(game_object_view.getSpriteBatch(), player);

        for (Enemy e : enemies) {
            game_object_view.draw_enemy(game_object_view.getSpriteBatch(), e);
            if (player_controller.detectEnemy(e)) {
                player_controller.storeSeenEnemy(e);
            }
//            enemy_controller.moveToPlayer(deltaTime, e, player);
        }
//        System.out.println("SPRITE WIDTH:" + enemies.get(0).getSprite().getWidth());
//        System.out.println("TEXTURE WIDTH:" + enemies.get(0).getSprite().getTexture().getWidth());


        if (player_controller.get_Seen_Enemies().peek() != null) {
//            //Front of the queue
            Enemy curr = player_controller.get_Seen_Enemies().peek();

            if (player_controller.detectEnemy(curr)) {
//                System.out.println("DETECTED:" + curr.hashCode());
                player_controller.shoot(deltaTime);
                System.out.println("BULLETS:" + player_controller.getBullets().toString());
                for (Bullet b : player_controller.getBullets()) {
                    game_object_view.draw_bullets(game_object_view.getSpriteBatch(), b);
                    if (b.hasCollided(curr, b)) {
                        player_controller.getBullets().remove(b);
                    }
                    bullet_controller.moveTowardEnemy(deltaTime, curr, b);
                }
            }
//
//
//            if (player_controller.detectEnemy(curr)) {
////                //this actually just loads in the bullets into an arraylist that will be instantiated later
//                player_controller.shoot(deltaTime, bullets);
////                //for each bullet loaded, draw them, check their collision, move them towards their respectful objects
////                for (Bullet b : bullets) {
//                if (!bullets.isEmpty()) {
//                    for (Bullet b : bullets) {
//                        game_object_view.draw_bullets(game_object_view.getSpriteBatch(), b);
//                        if (b.hasCollided(curr, b)) {
////                        if (curr.getCurrent_health() <= 0) {
////                            enemies.remove(curr);
////                            player_controller.get_Seen_Enemies().remove(curr);
////                        } else {
//                            System.out.println("PLAYER DAMAGE:" + player.getCurrent_damage());
////                        System.out.println("ENEMY HEALTH:" + curr.getCurrent_health());
////                        curr.setCurrent_health(curr.getCurrent_health() - player.getCurrent_damage());
////                        }
//
//////                        enemies.remove(curr);
//////                        player_controller.get_Seen_Enemies().remove(curr);
//
//                            bullets.remove(b);
////                        b.setSpeed(0f);
//                        } else {
//                            bullet_controller.moveTowardEnemy(deltaTime, curr, b);
//                        }
//                    }
//
//                }
////                }
//            }
        }

//        Handle_Unseen_Enemies();
//        Handle_Seen_Enemies();

        player_controller.move(deltaTime);


//Loop here when drawing enemies


//        game_object_view.draw_enemy(game_object_view.getSpriteBatch(), e1);
//        game_object_view.draw_enemy(game_object_view.getSpriteBatch(), e2);

//        if (player_controller.detectEnemy(e1)) {
//            player_controller.storeSeenEnemy(e1);
//        } else {
//            if (player_controller.get_Seen_Enemies().contains(e1)) {
//                player_controller.get_Seen_Enemies().remove(e1);
//            }
//        }
//
//
//        if (player_controller.detectEnemy(e2)) {
//            player_controller.storeSeenEnemy(e2);
//        } else {
//            if (player_controller.get_Seen_Enemies().contains(e2)) {
//                player_controller.get_Seen_Enemies().remove(e2);
//            }
//        }


//input handling


//        game_object_view.confirm_detection(game_object_view.getSpriteBatch(), player, e);

//        if (player_controller.detectEnemy(e)) {
//            player_controller.shoot(deltaTime, bullets);
//        }


//        enemy_controller.moveToPlayer(1 / 60f, e, player);
//        enemy_controller.moveToPlayer(1 / 60f, e2, player);
        /**
         *
         * GAME DATA
         */
//        if (player_controller.detectEnemy(e)) {
//            player_controller.shoot(deltaTime, bullets);
//            for (Bullet b : bullets) {
//
//                bullet_controller.moveTowardEnemy(deltaTime, e, b);
//                game_object_view.draw_bullets(game_object_view.getSpriteBatch(), b);
//            }
//        }


//        player_controller.detectEnemy(e);
//        for (Bullet b : bullets) {
////            System.out.println("BULLET:" + b.hashCode() + "POSITION:" + b.getPosition().toString());
////            b.setRadians(MathUtils.atan2(b.getPosition().y - 100, b.getPosition().x - 100));
////            b.set_dx(MathUtils.cos(b.getRadians()));
////            b.set_dy(MathUtils.sin(b.getRadians()));
////            b.set_Velocity(100f, 100f);
////            b.setPosition(b.getPosition().add(10, 10));
////            b.setPosition(b.getPosition().add(b.getVelocity().scl(b.getCurrent_speed() * deltaTime)));
////            if (player_controller.detectEnemy(e)) {
////                System.out.println("E:" + e.hashCode() + "DETECTED:" + player_controller.detectEnemy(e));
//////                b.setRadians(MathUtils.atan2(b.getPosition().y - e.getPosition().y, b.getPosition().x - e.getPosition().x));
//////                b.set_dx(MathUtils.cos(b.getRadians()));
//////                b.set_dy(MathUtils.sin(b.getRadians()));
//////                b.set_Velocity(b.get_dx(), b.get_dy());
//////                b.setPosition(b.getPosition().add(b.getVelocity().scl(b.getCurrent_speed() * deltaTime)));
////                game_object_view.draw_bullets(game_object_view.getSpriteBatch(), b);
////            }
//
//        }

//        for (Bullet b : bullets) {
//            game_object_view.draw_bullets(game_object_view.getSpriteBatch(), b);
//        }

//        System.out.println("CURRENT HEALTH:" + player.getCurrent_health());
//        System.out.println("CURRENT SIZE:" + player.getCurrent_size());
//        System.out.println("CURRENT DAMAGE:" + player.getCurrent_damage());
//        System.out.println("CURRENT SPEED:" + player.getCurrent_speed());
//        System.out.println("CURRENT POSITION:" + player.getPosition().toString());


//        System.out.println("Player HEALTH:" + play

//        player_controller.update(1 / 60);


        //        player_controller.update(1 / 60f);

//
//        if (currentScreen == Screen.MENU) {
//            batch.begin();
//            batch.draw(new Texture("logo.png"), Gdx.graphics.getWidth() * .10f, Gdx.graphics.getHeight() * .5f, 900, 210);
//            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//            textButtonStyle.font = font;
//            textButtonStyle.fontColor = Color.WHITE;
//            textButtonStyle.font.getData().setScale(4.0f);
//            final TextButton playBtn = new TextButton("Play", textButtonStyle);
//            playBtn.setPosition((Gdx.graphics.getWidth() / 2 - playBtn.getWidth() / 2), (Gdx.graphics.getHeight() / 2 - playBtn.getHeight() / 2));
//            stage.addActor(playBtn);
//            playBtn.addListener(new ChangeListener() {
//                @Override
//                public void changed(ChangeEvent event, Actor actor) {
//                    stage.clear();
//                    currentScreen = Screen.MAIN_GAME;
//
//                }
//            });
//
//            batch.end();
//        } else if (currentScreen == Screen.MAIN_GAME) {
//
//        stage.addActor(touchpad);
//            batch.begin();
//            /**
//             * draw the health bar
//             */
//            batch.setProjectionMatrix(UIcam.combined);
//            batch.end();
//            playerHB.draw(batch);
//
//            /**
//             * make game's frame rate independent
//             */
//            deltaTime = Gdx.graphics.getDeltaTime();
//            /**
//             * Update Player Movement
//             */
//            player_x_Move = player.getXPosition() + touchpad.getKnobPercentX() * player.getPlayerSpeed() * deltaTime;
//            player_y_Move = player.getYPosition() + touchpad.getKnobPercentY() * player.getPlayerSpeed() * deltaTime;
//            player.setXPosition(player_x_Move);
//            player.setYPosition(player_y_Move);
//
//            batch.setProjectionMatrix(cam.combined);
//            player.draw(batch);
//
//
//            if (spawnTimer > spawnTime) {
//                spawnTimer = 0;
//                loadEnemies();
//            } else {
//                spawnTimer += deltaTime;
//            }
//
//            for (int i = 0; i < enemies.size(); i++) {
//                if (enemies.get(i).shouldRemove()) {
//                    enemies.remove(i);
//                    i--;
//                } else {
//                    player.update(deltaTime, batch, enemies.get(i));
//                    enemies.get(i).update(deltaTime, player, batch);
//                }
//
//            }
//            check_Bullet_Enemy_Overlap(player.getBullets());
//            check_Player_Enemy_Overlap(enemies);
//
//            if (playerHB.getCurrentHealth() <= 0) {
//                stage.clear();
//                currentScreen = Screen.GAME_OVER;
//            }
//
//            /**
//             ** Set cam Position
//             */
//            Vector3 position = cam.position;
//            position.x = cam.position.x + (player.getXPosition() * 1 - cam.position.x) * deltaTime;
//            position.y = cam.position.y + (player.getYPosition() * 1 - cam.position.y) * deltaTime;
//            cam.position.set(position);
//            cam.update();
//
//        } else if (currentScreen == Screen.GAME_OVER) {
//            batch.begin();
//            batch.setProjectionMatrix(UIcam.combined);
//            batch.draw(new Texture("gameover.png"), Gdx.graphics.getWidth() * .10f, Gdx.graphics.getHeight() * .5f, 900, 210);
//
//            TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//            textButtonStyle.font = font;
//            textButtonStyle.fontColor = Color.WHITE;
//            textButtonStyle.font.getData().setScale(4.0f);
//            TextButton againBtn = new TextButton("Try Again", textButtonStyle);
//            againBtn.setPosition((Gdx.graphics.getWidth() / 2 - againBtn.getWidth() / 2), (Gdx.graphics.getHeight() / 2 - againBtn.getHeight() / 2));
//            stage.addActor(againBtn);
//            againBtn.addListener(new ChangeListener() {
//                @Override
//                public void changed(ChangeEvent event, Actor actor) {
//                    stage.clear();
//                    currentScreen = Screen.MENU;
//                    restartGame();
//                }
//            });
//            batch.end();
//
//        }
//        stage.act(1 / 60);
//        stage.draw();
//
//
//
//        /**
//         * lerp: linear interpolation:
//         *  -smooth camera motion that is not too rigid.
//         *  -want to decrease jittery movement of the camera
//         * camera_position  + (target_position - camera_position) * lerp
//         *
//         */

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
