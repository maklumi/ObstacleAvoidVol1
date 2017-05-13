package com.obstacle.avoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Logger
import com.obstacle.avoid.screen.loading.LoadingScreen

class ObstacleAvoidGame : Game() {

    val assetManager = AssetManager().apply {
        logger.level = Logger.DEBUG
    }

    lateinit var batch: SpriteBatch

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        batch = SpriteBatch()
        setScreen(LoadingScreen(this))
    }

    override fun dispose() {
        assetManager.dispose()
        batch.dispose()
    }
}
