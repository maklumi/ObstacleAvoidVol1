package com.obstacle.avoid.util

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Logger


object DebugCameraController {

    private val log = Logger(DebugCameraController::class.java.simpleName, Logger.DEBUG)
    private val config = DebugCameraConfig

    private var zoom = 1f
        set(value) {
            field = MathUtils.clamp(value, config.maxZoomIn, config.maxZoomOut)
        }

    private var position = Vector2()
    var startPosition = Vector2()
        set(value) {
            field.set(value.x, value.y)
            position.set(value.x, value.y)
        }

    init {
        log.debug(config.toString())
    }

    fun applyPositionToCamera(camera: OrthographicCamera) {
        camera.position.set(position, 0f)
        camera.zoom = zoom
        camera.update()
    }

    fun handleDebugInput(delta: Float) {
        if (Gdx.app.type != Application.ApplicationType.Desktop) return

        val moveSpeed = config.moveSpeed * delta
        val zoomSpeed = config.zoomSpeed * delta
        when {
            config.isLeftPressed -> {
                moveLeft(moveSpeed)
            }
            config.isRightPressed -> {
                moveRight(moveSpeed)
            }
            config.isUpPressed -> {
                moveUp(moveSpeed)
            }
            config.isDownPressed -> {
                moveDown(moveSpeed)
            }
            config.isZoomInPressed -> zoomIn(zoomSpeed)
            config.isZoomOutPressed -> zoomOut(zoomSpeed)
            config.isResetPressed -> reset()
            config.isLogPressed -> logDebug()
            config.isQuitPressed -> Gdx.app.exit()
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