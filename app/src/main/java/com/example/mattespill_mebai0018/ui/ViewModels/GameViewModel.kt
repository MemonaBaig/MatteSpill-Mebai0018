package com.example.mattespill_mebai0018.ui.ViewModels

import android.app.Application
import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.mattespill_mebai0018.R

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application.applicationContext
    private val prefs = context.getSharedPreferences("matte_prefs", Context.MODE_PRIVATE)

    private val difficulty = prefs.getString("difficulty", "lett")
    private val totalQuestions = prefs.getInt("total_questions", 5)
    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    private val tasks: List<String>
    private val answers: List<String>
    private val questionList: List<Pair<String, String>>

    init {
        tasks = when (difficulty) {
            "vanskelig" -> context.resources.getStringArray(R.array.tasks_medium).toList()
            "veldig vanskelig" -> context.resources.getStringArray(R.array.tasks_hard).toList()
            else -> context.resources.getStringArray(R.array.tasks_easy).toList()
        }

        answers = when (difficulty) {
            "vanskelig" -> context.resources.getStringArray(R.array.answers_medium).toList()
            "veldig vanskelig" -> context.resources.getStringArray(R.array.answers_hard).toList()
            else -> context.resources.getStringArray(R.array.answers_easy).toList()
        }

        if (totalQuestions > tasks.size) {
            _errorMessage.value = context.getString(R.string.not_enough_tasks)
            questionList = emptyList()
        } else {
            questionList = tasks.zip(answers).shuffled().take(totalQuestions)
        }
    }

    // 🔹 State-variabler
    private val _currentIndex = mutableIntStateOf(0)
    val currentIndex: State<Int> = _currentIndex

    private val _userInput = mutableStateOf("")
    val userInput: State<String> = _userInput

    private val _feedback = mutableStateOf("")
    val feedback: State<String> = _feedback

    private val _score = mutableIntStateOf(0)
    val score: State<Int> = _score

    private val _finished = mutableStateOf(false)
    val finished: State<Boolean> = _finished

    private val _answered = mutableStateOf(false)
    val answered: State<Boolean> = _answered

    private val _showExitDialog = mutableStateOf(false)
    val showExitDialog: State<Boolean> = _showExitDialog

    private val _newHighscore = mutableStateOf(false)
    val newHighscore: State<Boolean> = _newHighscore

    // 🔹 Hent gjeldende oppgave
    fun currentTask(): Pair<String, String> = questionList[_currentIndex.value]

    // 🔹 Oppdater brukerinput
    fun addDigit(digit: String) {
        _userInput.value += digit
    }

    fun deleteLastDigit() {
        if (_userInput.value.isNotEmpty()) {
            _userInput.value = _userInput.value.dropLast(1)
        }
    }

    // 🔹 Sjekk svar
    fun checkAnswer() {
        val (task, correctAnswer) = currentTask()
        _answered.value = true
        if (_userInput.value == correctAnswer) {
            _feedback.value = context.getString(R.string.correct_answer, task, correctAnswer)
            _score.value++
        } else {
            _feedback.value = context.getString(R.string.wrong_answer, correctAnswer)
        }
    }

    // 🔹 Neste spørsmål eller fullfør
    fun nextQuestion() {
        if (_currentIndex.value + 1 < totalQuestions) {
            _currentIndex.value++
            _userInput.value = ""
            _feedback.value = ""
            _answered.value = false
        } else {
            finishGame()
        }
    }

    // 🔹 Fullfør spill
    private fun finishGame() {
        val currentHighscore = prefs.getInt("highscore", 0)
        if (_score.value > currentHighscore) {
            prefs.edit().putInt("highscore", _score.value).apply()
            _newHighscore.value = true
        }
        _finished.value = true
    }

    // 🔹 Exit-dialog
    fun showExitDialog() { _showExitDialog.value = true }
    fun dismissExitDialog() { _showExitDialog.value = false }
}