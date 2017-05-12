package com.obstacle.avoid.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.obstacle.avoid.config.GameConfig.MAX_PLAYER_X_SPEED
import com.obstacle.avoid.config.GameConfig.PLAYER_BOUNDS_RADIUS
import com.obstacle.avoid.config.GameConfig.PLAYER_SIZE


class Player : ObjectBase(PLAYER_BOUNDS_RADIUS) {

    init {
        setSize(PLAYER_SIZE, PLAYER_SIZE)
    }

    fun update() {
        var xSpeed = 0f

        when {
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> {
                xSpeed = MAX_PLAYER_X_SPEED
            }
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> {
                xSpeed = -MAX_PLAYER_X_SPEED
            }
        }
        position.x += xSpeed
    }


}