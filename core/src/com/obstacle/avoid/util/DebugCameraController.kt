package com.obstacle.avoid.util

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Logger


object DebugCameraController {

    private val log = Logger(DebugCameraController::class.java.simpleName, Logger.DEBUG)

    private val DEFAULT_KEY_LEFT = Input.Keys.A
    private val DEFAULT_KEY_RIGHT = Input.Keys.D
    private val DEFAULT_KEY_UP = Input.Keys.W
    private val DEFAULT_KEY_DOWN = Input.Keys.S

    private val DEFAULT_KEY_ZOOM_IN = Input.Keys.COMMA
    private val DEFAULT_KEY_ZOOM_OUT = Input.Keys.PERIOD
    private val DEFAULT_KEY_RESET = Input.Keys.BACKSPACE
    private val DEFAULT_KEY_LOG = Input.Keys.ENTER

    private val DEFAULT_ZOOM_SPEED = 2f
    private val DEFAULT_MAX_ZOOM_IN = 0.2f
    private val DEFAULT_MAX_ZOOM_OUT = 20f
    private val DEFAULT_MOVE_SPEED = 20f

    private var zoom = 1f
        set(value) {
            field = MathUtils.clamp(value, DEFAULT_MAX_ZOOM_IN, DEFAULT_MAX_ZOOM_OUT)
        }

    private var position = Vector2()
    var startPosition = Vector2()
        set(value) {
            field.set(value.x, value.y)
            position.set(value.x, value.y)
        }

    fun applyPositionToCamera(camera: OrthographicCamera) {
        camera.position.set(position, 0f)
        camera.zoom = zoom
        camera.update()
    }

    fun handleDebugInput(delta: Float) {
        if (Gdx.app.type != Application.ApplicationType.Desktop) return

        val moveSpeed = DEFAULT_MOVE_SPEED * delta
        val zoomSpeed = DEFAULT_ZOOM_SPEED * delta
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
            Gdx.input.isKeyPressed(DEFAULT_KEY_ZOOM_IN) -> zoomIn(zoomSpeed)
            Gdx.input.isKeyPressed(DEFAULT_KEY_ZOOM_OUT) -> zoomOut(zoomSpeed)
            Gdx.input.isKeyPressed(DEFAULT_KEY_RESET) -> reset()
            Gdx.input.isKeyPressed(DEFAULT_KEY_LOG) -> logDebug()
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


    private fun zoomIn(zoomSpeed: Float) {
        zoom += zoomSpeed
    }

    private fun zoomOut(zoomSpeed: Float) {
        zoom -= zoomSpeed
    }

    private fun reset() {
        position.set(startPosition)
        zoom = 1f
    }

    private fun logDebug() {
        log.debug("positin= $position zoom= $zoom")
    }
}