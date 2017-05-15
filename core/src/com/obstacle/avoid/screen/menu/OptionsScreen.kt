package com.obstacle.avoid.screen.menu

import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.obstacle.avoid.ObstacleAvoidGame
import com.obstacle.avoid.assets.AssetDescriptors
import com.obstacle.avoid.assets.RegionNames
import com.obstacle.avoid.common.GameManager
import com.obstacle.avoid.config.DifficultyLevel

class OptionsScreen(game: ObstacleAvoidGame) : MenuScreenBase(game) {


    private lateinit var checkBoxGroup: ButtonGroup<CheckBox>
    private lateinit var easy: CheckBox
    private lateinit var medium: CheckBox
    private lateinit var hard: CheckBox

    override fun createUI(): Actor {
        val gamePlayAtlas = assetManager[AssetDescriptors.GAME_PLAY]
        val uiSkin = assetManager[AssetDescriptors.UI_SKIN]

        val bgRegion = gamePlayAtlas.findRegion(RegionNames.BACKGROUND)

        val label = Label("DIFFICULTY", uiSkin)

        easy = checkBox(DifficultyLevel.EASY.name, uiSkin)
//        easy.debug = true
        medium = checkBox(DifficultyLevel.MEDIUM.name, uiSkin)
        hard = checkBox(DifficultyLevel.HARD.name, uiSkin)

        checkBoxGroup = ButtonGroup(easy, medium, hard)

        val difficultyLevel = GameManager.difficultyLevel
        checkBoxGroup.setChecked(difficultyLevel.name)

        val backButton = TextButton("BACK", uiSkin).apply {
            addListener(object : ChangeListener() {
                override fun changed(event: ChangeEvent?, actor: Actor?) {
                    back()
                }
            })
        }

        val listener = object : ChangeListener() {
            override fun changed(event: ChangeListener.ChangeEvent?, actor: Actor?) {
                difficultyChanged()
            }
        }

        easy.addListener(listener)
        medium.addListener(listener)
        hard.addListener(listener)

        val contentTable = Table(uiSkin).apply {
            defaults().pad(10f)
            setBackground(RegionNames.PANEL)
            add(label).row()
            add(easy).row()
            add(medium).row()
            add(hard).row()
            add(backButton)
        }

        val table = Table()
        table.defaults().pad(15f)
        table.apply {
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

    private fun difficultyChanged() {
        val checked = checkBoxGroup.checked
        when (checked) {
            easy -> GameManager.updateDifficulty(DifficultyLevel.EASY)
            medium -> GameManager.updateDifficulty(DifficultyLevel.MEDIUM)
            hard -> GameManager.updateDifficulty(DifficultyLevel.HARD)
        }
    }

    private fun checkBox(text: String, skin: Skin): CheckBox {
        val checkBox = CheckBox(text, skin)
        checkBox.left().pad(8f)
        checkBox.labelCell.pad(8f)
        return checkBox
    }
}