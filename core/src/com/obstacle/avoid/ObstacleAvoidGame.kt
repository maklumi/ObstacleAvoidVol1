package com.obstacle.avoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.assets.AssetManager
import com.badlogic.gdx.utils.Logger
import com.obstacle.avoid.screen.GameScreen

class ObstacleAvoidGame : Game() {

    val assetManager = AssetManager().apply {
        logger.level = Logger.DEBUG
    }

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        setScreen(GameScreen(this))
    }

    override fun dispose() {
        assetManager.dispose()
    }
}
