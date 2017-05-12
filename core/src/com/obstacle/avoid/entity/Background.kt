package com.obstacle.avoid.entity


class Background {
    var x = 0f
    var y = 0f
    var width = 0f
    var height = 0f

    fun setPosition(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }
}