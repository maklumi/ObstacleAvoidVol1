package com.obstacle.avoid.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.utils.JsonReader
import com.badlogic.gdx.utils.JsonValue
import com.badlogic.gdx.utils.Logger


object DebugCameraConfig {

    private val DEFAULT_KEY_LEFT = Input.Keys.A
    private val DEFAULT_KEY_RIGHT = Input.Keys.D
    private val DEFAULT_KEY_UP = Input.Keys.W
    private val DEFAULT_KEY_DOWN = Input.Keys.S
    private val DEFAULT_KEY_QUIT = Input.Keys.Q

    private val DEFAULT_KEY_ZOOM_IN = Input.Keys.COMMA
    private val DEFAULT_KEY_ZOOM_OUT = Input.Keys.PERIOD
    private val DEFAULT_KEY_RESET = Input.Keys.BACKSPACE
    private val DEFAULT_KEY_LOG = Input.Keys.ENTER

    private val DEFAULT_ZOOM_SPEED = 2f
    private val DEFAULT_MAX_ZOOM_IN = 0.2f
    private val DEFAULT_MAX_ZOOM_OUT = 20f
    private val DEFAULT_MOVE_SPEED = 20f

    private val FILE_PATH = "debug/debugCameraConfig.json"
    private val fileHandle = Gdx.files.internal(FILE_PATH)

    private var leftKey: Int = DEFAULT_KEY_LEFT
    private var rightKey: Int = DEFAULT_KEY_RIGHT
    private var upKey: Int = DEFAULT_KEY_UP
    private var downKey: Int = DEFAULT_KEY_DOWN
    private var zoomInKey: Int = DEFAULT_KEY_ZOOM_IN
    private var zoomOutKey: Int = DEFAULT_KEY_ZOOM_OUT
    private var resetKey: Int = DEFAULT_KEY_RESET
    private var logKey: Int = DEFAULT_KEY_LOG
    private var quitKey: Int = DEFAULT_KEY_QUIT

    var maxZoomIn: Float = DEFAULT_MAX_ZOOM_IN
    var maxZoomOut: Float = DEFAULT_MAX_ZOOM_OUT
    var moveSpeed: Float = DEFAULT_MOVE_SPEED
    var zoomSpeed: Float = DEFAULT_ZOOM_SPEED

    val isLeftPressed: Boolean
        get() = Gdx.input.isKeyPressed(leftKey)
    val isRightPressed: Boolean
        get() = Gdx.input.isKeyPressed(rightKey)
    val isUpPressed: Boolean
        get() = Gdx.input.isKeyPressed(upKey)
    val isDownPressed: Boolean
        get() = Gdx.input.isKeyPressed(downKey)

    val isZoomInPressed: Boolean
        get() = Gdx.input.isKeyPressed(zoomInKey)
    val isZoomOutPressed: Boolean
        get() = Gdx.input.isKeyPressed(zoomOutKey)
    val isResetPressed: Boolean
        get() = Gdx.input.isKeyPressed(resetKey)
    val isLogPressed: Boolean
        get() = Gdx.input.isKeyPressed(logKey)
    val isQuitPressed: Boolean
        get() = Gdx.input.isKeyPressed(quitKey)

    private val log = Logger(DebugCameraConfig::class.java.simpleName, Logger.INFO)

    init {
        if (fileHandle.exists()) {
            load()
        } else {
            log.info("debugCameraConfig.json not found, using defaults key mapping")
        }
    }

    fun load() {
        val reader = JsonReader()
        val root = reader.parse(fileHandle)
        leftKey = getInputKeyValue(root, "leftKey", DEFAULT_KEY_LEFT)
        rightKey = getInputKeyValue(root, "rightKey", DEFAULT_KEY_RIGHT)
        upKey = getInputKeyValue(root, "upKey", DEFAULT_KEY_UP)
        downKey = getInputKeyValue(root, "downKey", DEFAULT_KEY_DOWN)
        quitKey = getInputKeyValue(root, "quit", DEFAULT_KEY_QUIT)
        zoomInKey = getInputKeyValue(root, "zoomInKey", DEFAULT_KEY_ZOOM_IN)
        zoomOutKey = getInputKeyValue(root, "zoomOutKey", DEFAULT_KEY_ZOOM_OUT)
        resetKey = getInputKeyValue(root, "resetKey", DEFAULT_KEY_RESET)
        logKey = getInputKeyValue(root, "logKey", DEFAULT_KEY_LOG)
        maxZoomIn = root.getFloat("maxZoomIn")
        maxZoomOut = root.getFloat("maxZoomOut")
        moveSpeed = root.getFloat("moveSpeed")
        zoomSpeed = root.getFloat("zoomSpeed")
    }


    private fun getInputKeyValue(root: JsonValue, name: String, default: Int): Int {
        val keyString = root.getString(name, Input.Keys.toString(default))
        return Input.Keys.valueOf(keyString)
    }

    override fun toString(): String {
        val LS = System.getProperty("line.separator")
        return "DebugCameraConfig { " + LS +
                "maxZoomIn= " + maxZoomIn + LS +
                "maxZoomOut= " + maxZoomOut + LS +
                "moveSpeed= " + moveSpeed + LS +
                "zoomSpeed= " + zoomSpeed + LS +
                "leftKey= " + Input.Keys.toString(leftKey) + LS +
                "rightKey= " + Input.Keys.toString(rightKey) + LS +
                "upKey= " + Input.Keys.toString(upKey) + LS +
                "downKey= " + Input.Keys.toString(downKey) + LS +
                "zoomInKey= " + Input.Keys.toString(zoomInKey) + LS +
                "zoomOutKey= " + Input.Keys.toString(zoomOutKey) + LS +
                "resetKey= " + Input.Keys.toString(resetKey) + LS +
                "logKey= " + Input.Keys.toString(logKey) + LS +
                "QuitKey= " + Input.Keys.toString(quitKey) + LS +
                "}"
    }
}