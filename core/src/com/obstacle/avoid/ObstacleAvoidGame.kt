package com.obstacle.avoid

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.obstacle.avoid.screen.GameScreenOld

class ObstacleAvoidGame : Game() {

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        setScreen(GameScreenOld())
    }
}
