package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.common.GameManager

class HighScoreScreen(game: ObstacleAvoidGame) : MenuScreenBase(game) {


    override fun createUI(): Actor {
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY]
        val uiSkin = assetManager[AssetDescriptors.UI_SKIN]

        val bgRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND)

        val highScoreText = Label("HIGHSCORE", uiSkin)
        val highScoreString = GameManager.highScore.toString()
        val highScoreLabel = Label(highScoreString, uiSkin)

        val backButton = TextButton("BACK", uiSkin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
        }

        val contentTable = Table(uiSkin).apply {
            defaults().pad(20f)
            setBackground(RegionNames.PANEL)
            add(highScoreText).row()
            add(highScoreLabel).row()
            add(backButton)
            center()
        }

        // background -> high score text ->label -> back button -> setup table
        val table = Table().apply {
            background = TextureRegionDrawable(bgRegion)
            add(contentTable)
            center()
            setFillParent(true)
            pack()
        }

        return table
    }

    private fun back() {
        game.screen = MenuScreen(game)
    }

}