package com.obstacle.avoid.entity

import com.badlogic.gdx.math.Intersector


class Obstacle : ObjectBase(BOUNDS_RADIUS) {

    companion object {
        private val BOUNDS_RADIUS = 0.3f
    }

    private val ySpeed = 0.01f

    fun update() {
        position.y -= ySpeed
    }

    fun hasCollidedWith(player: Player): Boolean {
        return Intersector.overlaps(player.bounds, this.bounds)
    }

}