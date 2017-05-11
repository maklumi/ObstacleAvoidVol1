package com.obstacle.avoid.screen

import com.badlogic.gdx.Screen

class GameScreen : Screen {

    private val controller = GameController()
    private val renderer = GameRenderer(controller)

    override fun show() {
    }

    override fun render(delta: Float) {
        controller.update(delta)
        renderer.render(delta)
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
