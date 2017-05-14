package com.obstacle.avoid.common

import com.badlogic.gdx.Gdx
import com.obstacle.avoid.ObstacleAvoidGame

object GameManager {

    private val HIGH_SCORE_KEY = "highScore"
    private val pref = Gdx.app.getPreferences(ObstacleAvoidGame::class.java.simpleName)
    // preferences is saved in HomePC/.prefs
    var highScore = pref.getInteger(HIGH_SCORE_KEY, 0)

    fun updateHighScore(score: Int) {
        if (score < highScore) return
        highScore = score
        pref.putInteger(HIGH_SCORE_KEY, highScore)
        pref.flush() // commit to disk. ie flush from memory to disk
    }

}