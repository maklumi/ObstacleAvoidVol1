package com.obstacle.avoid.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.ui.Skin


object AssetDescriptors {

    val FONT = AssetDescriptor<BitmapFont>(AssetPaths.UI_FONT, BitmapFont::class.java)
    val GAME_PLAY = AssetDescriptor<TextureAtlas>(AssetPaths.GAME_PLAY, TextureAtlas::class.java)
    val UI_SKIN = AssetDescriptor<Skin>(AssetPaths.UI_SKIN, Skin::class.java)
    val HIT_SOUND = AssetDescriptor<Sound>(AssetPaths.HIT, Sound::class.java)
}