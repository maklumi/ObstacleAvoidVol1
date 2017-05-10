package com.obstacle.avoid.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Logger
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.util.GdxUtils
import com.obstacle.avoid.util.ViewportUtils


class GameScreen : Screen {
    private val log = Logger(GameScreen::class.java.simpleName, Logger.DEBUG)

    val camera = OrthographicCamera()
    val viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    val renderer = ShapeRenderer()

    override fun show() {
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()

        drawDebug()
    }

    private fun drawDebug() {
        ViewportUtils.drawGrid(viewport, renderer)
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height)
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