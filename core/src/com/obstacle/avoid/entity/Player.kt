package com.obstacle.avoid.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.obstacle.avoid.config.GameConfig


class Player {

    private val BOUNDS_RADIUS = 0.4f // always think in world units
    private val SIZE = BOUNDS_RADIUS * 2
    private val MAX_X_SPEED = 0.25f

    var position = Vector2.Zero
    val bounds: Circle
        get() = Circle(position, BOUNDS_RADIUS)

    fun update() {
        var xSpeed = 0f

        when {
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> {
                xSpeed = MAX_X_SPEED
            }
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> {
                xSpeed = -MAX_X_SPEED
            }
        }
        position.x += xSpeed
    }

    fun drawDebug(renderer: ShapeRenderer) {
        renderer.circle(bounds.x, bounds.y, BOUNDS_RADIUS, 30)
    }

}