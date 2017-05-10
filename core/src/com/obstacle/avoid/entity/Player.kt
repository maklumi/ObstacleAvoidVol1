package com.obstacle.avoid.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.obstacle.avoid.config.GameConfig.MAX_PLAYER_X_SPEED


class Player : ObjectBase(BOUNDS_RADIUS) {

    companion object {
        private val BOUNDS_RADIUS = 0.4f
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