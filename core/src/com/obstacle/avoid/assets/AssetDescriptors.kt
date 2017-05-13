package com.obstacle.avoid.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas


object AssetDescriptors {

    val FONT = AssetDescriptor<BitmapFont>(AssetPaths.UI_FONT, BitmapFont::class.java)
    val GAME_PLAY = AssetDescriptor<TextureAtlas>(AssetPaths.GAME_PLAY, TextureAtlas::class.java)
    val UI = AssetDescriptor<TextureAtlas>(AssetPaths.UI, TextureAtlas::class.java)

}