package com.obstacle.avoid.screen.game

import com.badlogic.gdx.Screen
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.screen.menu.MenuScreen

class GameScreen(val game: ObstacleAvoidGame) : Screen {

    private val controller = GameController(game)
    private val assetManager = game.assetManager
    private lateinit var renderer: GameRenderer

    override fun show() {
        assetManager.load(AssetDescriptors.FONT)
        assetManager.load(AssetDescriptors.GAME_PLAY)
        assetManager.finishLoading()

        renderer = GameRenderer(game.batch, assetManager, controller)
    }

    override fun render(delta: Float) {
        controller.update(delta)
        renderer.render(delta)

        if (controller.isGameOver) game.screen = MenuScreen(game) // switching screen should be last
    }

    override fun resize(width: Int, height: Int) {
        renderer.resize(width, height)
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
