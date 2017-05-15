package com.obstacle.avoid.screen.loading

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.screen.menu.MenuScreen
import com.obstacle.avoid.util.GdxUtils

class LoadingScreen(val game: ObstacleAvoidGame) : ScreenAdapter() {

    // == constants ==
    private val PROGRESS_BAR_WIDTH = GameConfig.HUD_WIDTH / 2f
    private val PROGRESS_BAR_HEIGHT = 60f

    // == attributes ==
    private val camera = OrthographicCamera()
    private val viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT, camera)
    private val renderer = ShapeRenderer()

    private var progress = 0f
    private var waitTime = 0.75f

    private val assetManager = game.assetManager
    private var shouldChangeScreen = false

    override fun show() {
        assetManager.load(AssetDescriptors.FONT)
        assetManager.load(AssetDescriptors.GAME_PLAY)
        assetManager.load(AssetDescriptors.UI_SKIN)
        assetManager.load(AssetDescriptors.HIT_SOUND)
    }

    override fun render(delta: Float) {
        update(delta)

        GdxUtils.clearScreen()

        viewport.apply() // not necessary
        renderer.projectionMatrix = camera.combined
        renderer.begin(ShapeRenderer.ShapeType.Filled)

        draw()

        renderer.end()

        if (shouldChangeScreen) game.screen = MenuScreen(game)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {
        dispose()
    }

    override fun pause() {
        super.pause()
    }

    override fun resume() {
        super.resume()
    }

    override fun dispose() {
        renderer.dispose()
    }

    // == private methods ==
    private fun update(delta: Float) {
        this.progress = assetManager.progress // 0..1

        if (assetManager.update()) {
            // true if assets loaded
            waitTime -= delta // 0.75 .. 0
            if (waitTime <= 0f) shouldChangeScreen = true
        }
    }

    private fun draw() {
        val barX = (GameConfig.HUD_WIDTH - PROGRESS_BAR_WIDTH) / 2f
        val barY = (GameConfig.HUD_HEIGHT - PROGRESS_BAR_HEIGHT) / 2f

        renderer.rect(barX, barY,
                progress * PROGRESS_BAR_WIDTH, PROGRESS_BAR_HEIGHT)
    }
}