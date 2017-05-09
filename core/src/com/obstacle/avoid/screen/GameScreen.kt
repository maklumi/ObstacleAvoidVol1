package com.obstacle.avoid.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class GameScreen : Screen {

    lateinit var batch : SpriteBatch

    override fun show() {
        batch = SpriteBatch()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 0f,0f,1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        batch.begin()

        batch.end()
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun hide() {
        dispose()
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
        batch.dispose()
    }
}