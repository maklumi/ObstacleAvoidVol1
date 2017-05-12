package com.obstacle.avoid.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.assets.AssetPaths
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.config.GameConfig.OBSTACLE_SIZE
import com.obstacle.avoid.config.GameConfig.PLAYER_SIZE
import com.obstacle.avoid.util.DebugCameraController
import com.obstacle.avoid.util.GdxUtils
import com.obstacle.avoid.util.ViewportUtils


class GameRenderer(val controller: GameController) : Disposable {

    val camera = OrthographicCamera()
    val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    val renderer = ShapeRenderer()
    val hudCamera = OrthographicCamera()
    val hudViewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, hudCamera)
    val batch = SpriteBatch()
    val font = BitmapFont(Gdx.files.internal(AssetPaths.UI_FONT))
    val layout = GlyphLayout()

    private val playerTexture = Texture(Gdx.files.internal("gameplay/player.png"))
    private val obstacleTexture = Texture(Gdx.files.internal("gameplay/obstacle.png"))
    private val bgTexture = Texture(Gdx.files.internal("gameplay/background.png"))

    init {
        DebugCameraController.startPosition = Vector2(GameConfig.WORLD_CENTER_X, GameConfig.WORLD_CENTER_Y)
    }

    fun render(delta: Float) {
        DebugCameraController.handleDebugInput(delta)
        DebugCameraController.applyPositionToCamera(camera)

        GdxUtils.clearScreen()

        renderGamePlay()

        renderUI()

        renderDebug()
    }

    fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
        hudViewport.update(width, height, true)
        ViewportUtils.debugPixelPerUnit(viewport)
    }

    override fun dispose() {
        renderer.dispose()
        batch.dispose()
        font.dispose()
        playerTexture.dispose()
        obstacleTexture.dispose()
        bgTexture.dispose()
    }

    private fun renderGamePlay() {
        viewport.apply() // size is 10 x 6
        batch.apply {
            projectionMatrix = camera.combined // not hudCamera
            begin()
        }

        draw()

        batch.end()
    }

    private fun draw() {
        val bg = controller.background
        batch.draw(bgTexture, bg.x, bg.y, bg.width, bg.height)

        val player = controller.player
        batch.draw(playerTexture, player.position.x, player.position.y, PLAYER_SIZE, PLAYER_SIZE)

        val obstacles = controller.obstacles
        obstacles.forEach { batch.draw(obstacleTexture, it.position.x, it.position.y, OBSTACLE_SIZE, OBSTACLE_SIZE) }

    }

    private fun renderUI() {
        hudViewport.apply()
        batch.apply {
            projectionMatrix = hudCamera.combined
            begin()
        }

        val livesText = "LIVES: ${controller.lives}"
        layout.setText(font, livesText)
        font.draw(batch, livesText, 20f, GameConfig.HUD_HEIGHT - layout.height)

        val scoreText = "SCORE: ${controller.displayScore}"
        layout.setText(font, scoreText)
        font.draw(batch, scoreText, GameConfig.HUD_WIDTH - layout.width - 20f,
                GameConfig.HUD_HEIGHT - layout.height)

        batch.end()
    }

    private fun renderDebug() {
        viewport.apply()
        ViewportUtils.drawGrid(viewport, renderer)

        renderer.projectionMatrix = camera.combined
        renderer.begin(ShapeRenderer.ShapeType.Line)

        drawDebug()

        renderer.end()
    }

    private fun drawDebug() {
        val player = controller.player
        player.drawDebug(renderer)

        val obstacles = controller.obstacles
        for (it in obstacles) {
            it.drawDebug(renderer)
        }
    }
}
