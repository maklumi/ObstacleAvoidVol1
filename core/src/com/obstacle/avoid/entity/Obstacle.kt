package com.obstacle.avoid.entity

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.utils.Pool
import com.obstacle.avoid.config.GameConfig


class Obstacle : ObjectBase(BOUNDS_RADIUS), Pool.Poolable {

    companion object {
        private val BOUNDS_RADIUS = 0.3f
        val SIZE = BOUNDS_RADIUS * 2
    }

    var ySpeed = GameConfig.EASY_SPEED

    var isAlreadyHit = false

    init {
        setSize(SIZE, SIZE)
    }

    fun update() {
        position.y -= ySpeed
    }

    fun hasCollidedWith(player: Player): Boolean {
        val overlaps = Intersector.overlaps(player.bounds, this.bounds)
        if (overlaps) isAlreadyHit = overlaps
        return overlaps
    }

    override fun reset() {
        isAlreadyHit = false
    }
}