package com.obstacle.avoid.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.config.GameConfig

object DesktopLauncher {
    @JvmStatic fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration()
        config.width = GameConfig.WIDTH.toInt()
        config.height = GameConfig.HEIGHT.toInt()
        LwjglApplication(ObstacleAvoidGame(), config)
    }
}
