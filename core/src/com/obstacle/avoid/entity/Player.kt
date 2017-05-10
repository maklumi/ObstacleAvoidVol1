package com.obstacle.avoid.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2


class Player {

    private val BOUNDS_RADIUS = 0.4f // always think in world units
    private val SIZE = BOUNDS_RADIUS * 2

    var position = Vector2.Zero
    val bounds: Circle
        get() = Circle(position, BOUNDS_RADIUS)

    fun drawDebug(renderer: ShapeRenderer) {
        renderer.circle(bounds.x, bounds.y, BOUNDS_RADIUS, 30)
    }

}