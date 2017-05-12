package com.obstacle.avoid.entity

import com.obstacle.avoid.config.GameConfig.PLAYER_BOUNDS_RADIUS
import com.obstacle.avoid.config.GameConfig.PLAYER_SIZE


class Player : ObjectBase(PLAYER_BOUNDS_RADIUS) {

    init {
        setSize(PLAYER_SIZE, PLAYER_SIZE)
    }

}