package com.obstacle.avoid.assets

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont


object AssetDescriptors {

    val FONT = AssetDescriptor<BitmapFont>(AssetPaths.UI_FONT, BitmapFont::class.java)
    val BACKGROUND = AssetDescriptor<Texture>(AssetPaths.BACKGROUND, Texture::class.java)
    val OBSTACLE = AssetDescriptor<Texture>(AssetPaths.OBSTACLE, Texture::class.java)
    val PLAYER = AssetDescriptor<Texture>(AssetPaths.PLAYER, Texture::class.java)

}