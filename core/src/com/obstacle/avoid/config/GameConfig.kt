package com.obstacle.avoid.config


object GameConfig {

    // in pixels
    val WIDTH = 480f
    val HEIGHT = 800f

    // in Units
    val HUD_WIDTH = 480f
    val HUD_HEIGHT = 800f
    val WORLD_WIDTH = 6f
    val WORLD_HEIGHT = 10f
    val WORLD_CENTER_X = WORLD_WIDTH / 2f
    val WORLD_CENTER_Y = WORLD_HEIGHT / 2f

    val LIVES_START = 3
    val MAX_PLAYER_X_SPEED = 0.25f

    val OBSTACLE_SPAWN_TIME = 0.25f

    val SCORE_INTERVAL = 1.25f

    // difficulty level
    val EASY_SPEED = 0.08f
    val MEDIUM_SPEED = 0.12f
    val HARD_SPEED = 0.16f
}