package com.obstacle.avoid.entity

import com.badlogic.gdx.math.Intersector
import com.badlogic.gdx.utils.Pool
import com.obstacle.avoid.config.GameConfig
import com.obstacle.avoid.config.GameConfig.OBSTACLE_BOUNDS_RADIUS
import com.obstacle.avoid.config.GameConfig.OBSTACLE_SIZE


class Obstacle : ObjectBase(OBSTACLE_BOUNDS_RADIUS), Pool.Poolable {

    var ySpeed = GameConfig.EASY_SPEED

    var isAlreadyHit = false

    init {
        setSize(OBSTACLE_SIZE, OBSTACLE_SIZE)
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