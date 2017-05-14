package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.util.GdxUtils

abstract class MenuScreenBase(val game: ObstacleAvoidGame) : ScreenAdapter() {

    protected val viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    protected val assetManager = game.assetManager
    protected val stage = Stage(viewport, game.batch)

    abstract fun createUI(): Actor

    override fun show() {
        Gdx.input.inputProcessor = stage

        stage.addActor(createUI())
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }


    override fun hide() {
        dispose()
    }

    override fun dispose() {
        stage.dispose()
    }
}