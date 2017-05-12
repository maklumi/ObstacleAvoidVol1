package com.obstacle.avoid.entity

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Circle
import com.badlogic.gdx.math.Vector2

abstract class ObjectBase(val radius: Float) {

    var position: Vector2 = Vector2.Zero
    var width = 1f
    var height = 1f

    val bounds: Circle
        get() {
            val halfWidth = width / 2
            val halfHeight = height / 2
            val properPosition = Vector2(position.x + halfWidth, position.y + halfHeight)
            return Circle(properPosition, radius)
        }

    fun drawDebug(renderer: ShapeRenderer) {
        renderer.circle(bounds.x, bounds.y, radius, 30)
    }

    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }
}

