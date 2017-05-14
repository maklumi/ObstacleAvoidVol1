package com.obstacle.avoid.config


enum class DifficultyLevel(val obstacleSpeed: Float) {
    EASY(GameConfig.EASY_SPEED),
    MEDIUM(GameConfig.MEDIUM_SPEED),
    HARD(GameConfig.HARD_SPEED);

    fun isEasy() = this == EASY
    fun isMedium() = this == MEDIUM
    fun isHard() = this == HARD
}