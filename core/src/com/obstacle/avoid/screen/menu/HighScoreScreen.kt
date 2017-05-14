package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.util.GdxUtils

class HighScoreScreen(val game: ObstacleAvoidGame) : ScreenAdapter() {

    private val viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    private val assetManager = game.assetManager
    private val stage = Stage(viewport, game.batch)

    override fun show() {
        Gdx.input.inputProcessor = stage

        createUI()
    }

    private fun createUI() {
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY]
        val uiAtlas = assetManager[AssetDescriptors.UI]
        val font = assetManager[AssetDescriptors.FONT]

        val bgRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND)
        val panelRegion = uiAtlas.findRegion(RegionNames.PANEL)

        val backRegion = uiAtlas.findRegion(RegionNames.BACK)
        val backPressedRegion = uiAtlas.findRegion(RegionNames.BACK_PRESSED)

        val labelStyle = Label.LabelStyle(font, Color.GOLDENROD)
        val highScoreText = Label("HIGHSCORE", labelStyle)
        val highScoreString = GameManager.highScore.toString()
        val highScoreLabel = Label(highScoreString, labelStyle)
        val backButton = ImageButton(TextureRegionDrawable(backRegion), TextureRegionDrawable(backPressedRegion)).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
        }

        val contentTable = Table().apply {
            defaults().pad(20f)
            background = TextureRegionDrawable(panelRegion)
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

        stage.addActor(table)
    }

    private fun back() {
        game.screen = MenuScreen(game)
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