package com.obstacle.avoid.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.entity.Obstacle
import com.obstacle.avoid.entity.Player
import com.obstacle.avoid.util.DebugCameraController
import com.obstacle.avoid.util.GdxUtils
import com.obstacle.avoid.util.ViewportUtils


class GameScreen : Screen {
    private val log = Logger(GameScreen::class.java.simpleName, Logger.DEBUG)

    val camera = OrthographicCamera()
    val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    val renderer = ShapeRenderer()

    val player = Player().apply {
        position = Vector2(GameConfig.WORLD_CENTER_X, 1f)
    }

    private val obstacles = Array<Obstacle>()
    private var obstacleTimer = 0f
    private var isAlive = true

    override fun show() {
        // setup debug camera controller to start at center of world
        DebugCameraController.startPosition = Vector2(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y)
    }

    override fun render(delta: Float) {
        DebugCameraController.handleDebugInput(delta)
        DebugCameraController.applyPositionToCamera(camera)
        // update world
        if (isAlive) update(delta)

        GdxUtils.clearScreen()

        renderDebug()
    }

    private fun update(delta: Float) {
        updatePlayer()
        updateObstacles(delta)

        if (hasPlayerCollidedWithObstacle) isAlive = false
    }

    private val hasPlayerCollidedWithObstacle: Boolean
        get() {
            return obstacles.any { it.hasCollidedWith(player) }
        }

    private fun updateObstacles(delta: Float) {
        for (obstacle in obstacles) {
            obstacle.update()
        }
        createNewObstacles(delta)
    }

    private fun createNewObstacles(delta: Float) {
        obstacleTimer += delta
        if (obstacleTimer >= GameConfig.OBSTACLE_SPAWN_TIME) {
            val obstacle = Obstacle()

            val min = obstacle.bounds.radius
            val max = GameConfig.WORLD_WIDTH - obstacle.bounds.radius
            val x = MathUtils.random(min, max)
            val y = GameConfig.WORLD_HEIGHT

            obstacle.position = Vector2(x, y)
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

    private fun renderDebug() {
        ViewportUtils.drawGrid(viewport, renderer)

        renderer.projectionMatrix = camera.combined
        renderer.begin(ShapeRenderer.ShapeType.Line)

        drawDebug()

        renderer.end()
    }

    private fun drawDebug() {
        player.drawDebug(renderer)

        for (it in obstacles) {
            it.drawDebug(renderer)
        }
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        ViewportUtils.debugPixelPerUnit(viewport)
    }

    override fun hide() {
        dispose()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        renderer.dispose()
    }
}