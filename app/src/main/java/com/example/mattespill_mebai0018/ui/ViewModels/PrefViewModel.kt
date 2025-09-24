package com.example.mattespill_mebai0018.ui.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

private const val PREFS_NAME = "matte_prefs"
private const val KEY_TOTAL_QUESTIONS = "total_questions"
private const val KEY_LANGUAGE = "language"
private const val KEY_DIFFICULTY = "difficulty"

class PrefViewModel(application: Application) : AndroidViewModel(application) {

    private val prefs = application.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    // 👇 State-variabler
    private val _totalQuestions = mutableStateOf(prefs.getInt(KEY_TOTAL_QUESTIONS, 5))
    val totalQuestions: State<Int> = _totalQuestions

    private val _languageCode = mutableStateOf(prefs.getString(KEY_LANGUAGE, "no") ?: "no")
    val languageCode: State<String> = _languageCode

    private val _difficulty = mutableStateOf(prefs.getString(KEY_DIFFICULTY, "lett") ?: "lett")
    val difficulty: State<String> = _difficulty

    // 👇 Oppdateringsmetoder
    fun setTotalQuestions(value: Int) {
        _totalQuestions.value = value
        prefs.edit().putInt(KEY_TOTAL_QUESTIONS, value).apply()
    }

    fun setLanguage(code: String) {
        _languageCode.value = code
        prefs.edit().putString(KEY_LANGUAGE, code).apply()
    }

    fun setDifficulty(level: String) {
        _difficulty.value = level
        prefs.edit().putString(KEY_DIFFICULTY, level).apply()
    }
}



