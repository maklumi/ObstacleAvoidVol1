package com.obstacle.avoid.desktop

import com.badlogic.gdx.tools.texturepacker.TexturePacker

object AssetPacker {

    private val DRAW_DEBUG_OUTLINE = false
    private val ASSET_INPUT_PATH = "desktop/assets-raw"
    private val ASSET_OUTPUT_PATH = "android/assets"

    @JvmStatic fun main(arg: Array<String>) {
        val settings = TexturePacker.Settings().apply {
            debug = DRAW_DEBUG_OUTLINE
//            pot = false
//            maxWidth = 484
//            maxHeight = 804
        }

//        TexturePacker.process(settings,
//                ASSET_INPUT_PATH + "/gameplay",
//                ASSET_OUTPUT_PATH + "/gameplay",
//                "gameplay"
//        )

//        TexturePacker.process(settings,
//                ASSET_INPUT_PATH + "/ui",
//                ASSET_OUTPUT_PATH + "/ui",
//                "ui")
        TexturePacker.process(settings,
                ASSET_INPUT_PATH + "/skin",
                ASSET_OUTPUT_PATH + "/ui",
                "uiskin")
    }
}