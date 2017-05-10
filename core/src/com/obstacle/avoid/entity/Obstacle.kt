package com.obstacle.avoid.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2


class Obstacle {

    private val BOUNDS_RADIUS = 0.3f // always think in world units
    private val ySpeed = 0.01f

    var position: Vector2 = Vector2.Zero
    val bounds: Circle
        get() = Circle(position, BOUNDS_RADIUS)

    fun update() {
        position.y -= ySpeed
    }

    fun drawDebug(renderer: ShapeRenderer) {
        renderer.circle(bounds.x, bounds.y, BOUNDS_RADIUS, 30)
    }

}