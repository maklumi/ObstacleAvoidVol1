package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.FitViewport
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.util.GdxUtils

class OptionsScreen(val game: ObstacleAvoidGame) : ScreenAdapter() {

    private val viewport = FitViewport(GameConfig.HUD_WIDTH, GameConfig.HUD_HEIGHT)
    private val assetManager = game.assetManager
    private val stage = Stage(viewport, game.batch)

    private lateinit var checkMark: Image

    override fun show() {
        Gdx.input.inputProcessor = stage

        createUI()
    }

    private fun createUI() {
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY]
        val uiAtlas = assetManager[AssetDescriptors.UI]
        val font = assetManager[AssetDescriptors.FONT]
        val labelStyle = Label.LabelStyle(font, Color.GOLDENROD)

        val checkMarkRegion = uiAtlas.findRegion(RegionNames.CHECK_MARK)

        val label = Label("DIFFICULTY", labelStyle)
        label.setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 + 180, Align.center)

        val easyButton = createButton(uiAtlas, RegionNames.EASY).apply {
            setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 + 90, Align.center)
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    checkMark.y = this@apply.y + 25
                }
            })
        }

        val mediumButton = createButton(uiAtlas, RegionNames.MEDIUM).apply {
            setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2, Align.center)
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    checkMark.y = this@apply.y + 25
                }
            })
        }

        val hardButton = createButton(uiAtlas, RegionNames.HARD).apply {
            setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 - 90, Align.center)
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    checkMark.y = this@apply.y + 25
                }
            })
        }

        checkMark = Image(TextureRegionDrawable(checkMarkRegion))
        checkMark.setPosition(mediumButton.x + 50, mediumButton.y + 40, Align.center)


        val bgRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND)
        val background = Image(TextureRegionDrawable(bgRegion))

        val backRegion = uiAtlas.findRegion(RegionNames.BACK)
        val backPressedRegion = uiAtlas.findRegion(RegionNames.BACK_PRESSED)

        val backButton = ImageButton(TextureRegionDrawable(backRegion), TextureRegionDrawable(backPressedRegion)).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
            setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 - 180, Align.center)
        }

        stage.apply {
            addActor(background)
            addActor(label)
            addActor(easyButton)
            addActor(mediumButton)
            addActor(hardButton)
            addActor(checkMark)
            addActor(backButton)
        }
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

    private fun createButton(atlas: TextureAtlas, regionName: String): ImageButton {
        val region = atlas.findRegion(regionName)

        return ImageButton(TextureRegionDrawable(region))
    }
}