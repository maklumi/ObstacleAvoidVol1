package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.DifficultyLevel
import com.obstacle.avoid.config.GameConfig

class OptionsScreen(game: ObstacleAvoidGame) : MenuScreenBase(game) {


    private lateinit var checkMark: Image


    override fun createUI(): Actor {
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
                    GameManager.updateDifficulty(DifficultyLevel.EASY)
                }
            })
        }

        val mediumButton = createButton(uiAtlas, RegionNames.MEDIUM).apply {
            setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2, Align.center)
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    checkMark.y = this@apply.y + 25
                    GameManager.updateDifficulty(DifficultyLevel.MEDIUM)
                }
            })
        }

        val hardButton = createButton(uiAtlas, RegionNames.HARD).apply {
            setPosition(GameConfig.HUD_WIDTH / 2, GameConfig.HUD_HEIGHT / 2 - 90, Align.center)
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    checkMark.y = this@apply.y + 25
                    GameManager.updateDifficulty(DifficultyLevel.HARD)

                }

            })
        }

        checkMark = Image(TextureRegionDrawable(checkMarkRegion))
        checkMark.setPosition(mediumButton.x + 50, mediumButton.y + 40, Align.center)
        val difficultyLevel = GameManager.difficultyLevel
        if (difficultyLevel.isEasy()) {
            checkMark.y = easyButton.y + 25
        } else if (difficultyLevel.isHard()) {
            checkMark.y = hardButton.y + 25
        }


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

        val table = Table()
        table.defaults().pad(15f)
        table.apply {
            addActor(background)
            addActor(label)
            addActor(easyButton)
            addActor(mediumButton)
            addActor(hardButton)
            addActor(checkMark)
            addActor(backButton)
        }
        return table
    }

    private fun back() {
        game.screen = MenuScreen(game)
    }

    private fun createButton(atlas: TextureAtlas, regionName: String): ImageButton {
        val region = atlas.findRegion(regionName)

        return ImageButton(TextureRegionDrawable(region))
    }
}