package com.obstacle.avoid.screen.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Pool
import com.badlogic.gdx.utils.Pools
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.config.GameConfig.OBSTACLE_SIZE
import com.obstacle.avoid.entity.Background
import com.obstacle.avoid.entity.Obstacle
import com.obstacle.avoid.entity.Player


class GameController(game: ObstacleAvoidGame) {
//    private val log = Logger(GameController::class.java.simpleName, Logger.DEBUG)

    val player = Player().apply {
        position = Vector2(GameConfig.WORLD_CENTER_X - GameConfig.PLAYER_SIZE / 2,
                1f - GameConfig.PLAYER_SIZE / 2)
    }

    val obstacles = Array<Obstacle>()
    private var obstacleTimer = 0f
    var lives = GameConfig.LIVES_START

    private var scoreTimer = 0f
    private var score = 0
    var displayScore = 0

    var isGameOver = false
        get() = lives <= 0


    val obstaclePool: Pool<Obstacle> = Pools.get(Obstacle::class.java, 20)
    val background = Background().apply {
        setPosition(0f, 0f)
        setSize(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT)
    }

    private val hitSound = game.assetManager[AssetDescriptors.HIT_SOUND]

    fun update(delta: Float) {
        if (isGameOver) return

        updatePlayer()
        updateObstacles(delta)
        updateScore(delta)
        updateDisplayScore(delta)

        if (hasPlayerCollidedWithObstacle) {
            hitSound.play()
            lives--
            if (isGameOver) {
                GameManager.updateHighScore(score)
            } else {
                restart()

            }
        }
    }

    private fun restart() {
        obstaclePool.freeAll(obstacles)
        obstacles.clear()
        player.position.set(GameConfig.WORLD_CENTER_X - GameConfig.PLAYER_SIZE / 2,
                1f - GameConfig.PLAYER_SIZE / 2)
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
        if (first.position.y < -OBSTACLE_SIZE) {
            obstacles.removeValue(first, true)
            obstaclePool.free(first)
        }
    }

    private fun createNewObstacles(delta: Float) {
        obstacleTimer += delta
        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            val obstacle = obstaclePool.obtain()

            val min = obstacle.bounds.radius
            val max = GameConfig.WORLD_WIDTH - GameConfig.OBSTACLE_SIZE
            val x = MathUtils.random(min, max)
            val y = GameConfig.WORLD_HEIGHT

            val difficultyLevel = GameManager.difficultyLevel
            obstacle.position = Vector2(x, y)
            obstacle.ySpeed = difficultyLevel.obstacleSpeed
            obstacles.add(obstacle)
            obstacleTimer = 0f
        }
    }

    private fun updatePlayer() {
        var xSpeed = 0f

        when {
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> {
                xSpeed = GameConfig.MAX_PLAYER_X_SPEED
            }
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> {
                xSpeed = -GameConfig.MAX_PLAYER_X_SPEED
            }
        }
        player.position.x += xSpeed
        clampPlayerPosition()
    }

    private fun clampPlayerPosition() {
        val x = MathUtils.clamp(player.position.x,
                0f,
                GameConfig.WORLD_WIDTH - GameConfig.PLAYER_SIZE)

        player.position.x = x
    }

}