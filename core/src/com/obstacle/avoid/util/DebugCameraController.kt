package com.obstacle.avoid.util

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2


object DebugCameraController {

    private val DEFAULT_KEY_LEFT = Input.Keys.A
    private val DEFAULT_KEY_RIGHT = Input.Keys.D
    private val DEFAULT_KEY_UP = Input.Keys.W
    private val DEFAULT_KEY_DOWN = Input.Keys.S

    private val DEFAULT_MOVE_SPEED = 20f

    private var position = Vector2()
    var startPosition = Vector2()
        set(value) {
            field.set(value.x, value.y)
            position.set(value.x, value.y)
        }

    fun applyPositionToCamera(camera: OrthographicCamera) {
        camera.position.set(position, 0f)
        camera.update()
    }

    fun handleDebugInput(delta: Float) {
        if (Gdx.app.type != Application.ApplicationType.Desktop) return

        val moveSpeed = DEFAULT_MOVE_SPEED * delta

        when {
            Gdx.input.isKeyPressed(DEFAULT_KEY_LEFT) -> {
                moveLeft(moveSpeed)
            }
            Gdx.input.isKeyPressed(DEFAULT_KEY_RIGHT) -> {
                moveRight(moveSpeed)
            }
            Gdx.input.isKeyPressed(DEFAULT_KEY_UP) -> {
                moveUp(moveSpeed)
            }
            Gdx.input.isKeyPressed(DEFAULT_KEY_DOWN) -> {
                moveDown(moveSpeed)
            }
        }
    }

    private fun moveLeft(speed: Float) {
        moveCamera(-speed, 0f)
    }

    private fun moveRight(speed: Float) {
        moveCamera(speed, 0f)
    }

    private fun moveUp(speed: Float) {
        moveCamera(0f, speed)
    }

    private fun moveDown(speed: Float) {
        moveCamera(0f, -speed)

    }

    private fun moveCamera(xSpeed: Float, ySpeed: Float) {
        position.set(position.x + xSpeed, position.y + ySpeed)
    }

}