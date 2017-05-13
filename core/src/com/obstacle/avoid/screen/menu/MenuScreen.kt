package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.util.GdxUtils

class MenuScreen(game: ObstacleAvoidGame) : ScreenAdapter() {

    private val viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    private val assetManager = game.assetManager
    private val stage = Stage(viewport, game.batch)

    override fun show() {
        Gdx.input.inputProcessor = stage

        initUI()
    }

    private fun initUI() {
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY]
        val uiAtlas = assetManager[AssetDescriptors.UI]

        val bgRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND)
        val panelRegion = uiAtlas.findRegion(RegionNames.PANEL)

        val playButton = createButton(uiAtlas, RegionNames.PLAY, RegionNames.PLAY_PRESSED)
        playButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                play()
            }
        })

        val highScoreButton = createButton(uiAtlas, RegionNames.HIGH_SCORE, RegionNames.HIGH_SCORE_PRESSED)
        highScoreButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                showHighScore()
            }
        })

        val optionsButton = createButton(uiAtlas, RegionNames.OPTIONS, RegionNames.OPTIONS_PRESSED)
        optionsButton.addListener(object : ChangeListener() {
            override fun changed(event: ChangeEvent?, actor: Actor?) {
                showOptions()
            }
        })


        val buttonTable = Table().apply {
            defaults().pad(20f)
            background = TextureRegionDrawable(panelRegion)
            add(playButton).row()
            add(highScoreButton).row()
            add(optionsButton).row()
            center()
        }

        val table = Table().apply {
            background = TextureRegionDrawable(bgRegion)
        }

        //setup
        table.apply {
            add(buttonTable)
            center()
            setFillParent(true)
            pack()
        }

        stage.addActor(table)

    }

    private fun play() {

    }

    private fun showHighScore() {

    }

    private fun showOptions() {

    }

    private fun createButton(atlas: TextureAtlas, upRegionName: String, downRegionName: String): ImageButton {
        val upRegion = atlas.findRegion(upRegionName)
        val downRegion = atlas.findRegion(downRegionName)

        return ImageButton(TextureRegionDrawable(upRegion), TextureRegionDrawable(downRegion))
    }

    override fun render(delta: Float) {
        GdxUtils.clearScreen()

        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
        dispose()
    }

    override fun dispose() {
        stage.dispose()
    }
}