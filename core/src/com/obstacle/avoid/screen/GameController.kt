package com.obstacle.avoid.screen

import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pool
import com.badlogic.gdx.utils.Pools
import com.obstacle.avoid.config.DifficultyLevel
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.entity.Obstacle
import com.obstacle.avoid.entity.Player


class GameController {
//    private val log = Logger(GameController::class.java.simpleName, Logger.DEBUG)

    val player = Player().apply {
        position = Vector2(GameConfig.WORLD_CENTER_X, 1f)
    }

    val obstacles = Array<Obstacle>()
    private var obstacleTimer = 0f
    var lives = GameConfig.LIVES_START

    private var scoreTimer = 0f
    private var score = 0
    var displayScore = 0

    var isGameOver = false
        get() = lives <= 0

    var difficultyLevel = DifficultyLevel.EASY

    val obstaclePool: Pool<Obstacle> = Pools.get(Obstacle::class.java, 20)

    fun update(delta: Float) {
        if (isGameOver) return

        updatePlayer()
        updateObstacles(delta)
        updateScore(delta)
        updateDisplayScore(delta)

        if (hasPlayerCollidedWithObstacle) {
            lives--
        }
    }

    private fun updateScore(delta: Float) {
        scoreTimer += delta
        if (scoreTimer >= GameConfig.SCORE_INTERVAL) {
            score += MathUtils.random(1, 5)
            scoreTimer = 0f
        }
    }

    private fun updateDisplayScore(delta: Float) {
        if (displayScore < score) {
            displayScore = Math.min(score, displayScore + (60 * delta).toInt())
        }
    }

    private val hasPlayerCollidedWithObstacle: Boolean
        get() {
            return obstacles.any { !it.isAlreadyHit && it.hasCollidedWith(player) }
        }

    private fun updateObstacles(delta: Float) {
        for (obstacle in obstacles) {
            obstacle.update()
        }
        createNewObstacles(delta)
        removePassedObstacles()
    }

    private fun removePassedObstacles() {
        if (obstacles.size == 0) return
        val first = obstacles.first()
        if (first.position.y < -Obstacle.SIZE) {
            obstacles.removeValue(first, true)
            obstaclePool.free(first)
        }
    }

    private fun createNewObstacles(delta: Float) {
        obstacleTimer += delta
        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            val obstacle = obstaclePool.obtain()

            val min = obstacle.bounds.radius
            val max = GameConfig.WORLD_WIDTH - obstacle.bounds.radius
            val x = MathUtils.random(min, max)
            val y = GameConfig.WORLD_HEIGHT

            obstacle.position = Vector2(x, y)
            obstacle.ySpeed = difficultyLevel.obstacleSpeed
            obstacles.add(obstacle)
            obstacleTimer = 0f
        }
    }

    private fun updatePlayer() {
        player.update()
        clampPlayerPosition()
    }

    private fun clampPlayerPosition() {
        val x = MathUtils.clamp(player.position.x,
                player.bounds.radius,
                GameConfig.WORLD_WIDTH - player.bounds.radius)

        player.position.x = x
    }

}