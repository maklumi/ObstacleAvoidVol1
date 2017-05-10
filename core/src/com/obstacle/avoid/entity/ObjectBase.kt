package com.obstacle.avoid.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2

abstract class ObjectBase(val radius: Float) {

    var position: Vector2 = Vector2.Zero

    val bounds: Circle
        get() = Circle(position, radius)

    fun drawDebug(renderer: ShapeRenderer) {
        renderer.circle(bounds.x, bounds.y, radius, 30)
    }
}

