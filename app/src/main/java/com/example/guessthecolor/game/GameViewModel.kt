package com.example.guessthecolor.game

import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation

class GameViewModel : ViewModel() {

    init {
        Log.i("GameViewModel", "GVM created!")
        object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentTime.value = (millisUntilFinished / 1000)
            }

            override fun onFinish() {
                // do something after countdown is done ie. enable button, change color
                    currentTime.value = 0L
                    _eventGameFinishTime.value = true

            }
        }.start()
    }

    data class Question(
        val text: String,
        val answer: List<String>,
        val img: String
    )

    var questions: MutableList<Question> = mutableListOf(
        Question(
            text = "How many arms does MACHAMP have?",
            answer = listOf("4", "four"),
            img = "@drawable/_0200905_machamp_pokemon_go_1200x675_1"
        ),
        Question(
            text = "Name one pure POISON TYPE pokemon",
            answer = listOf(
                "ekans",
                "arbok",
                "nidoran",
                "nidorina",
                "nidorino",
                "grimer",
                "muk",
                "koffing",
                "weezing",
                "gulping",
                "swalot",
                "seviper",
                "trubbish",
                "garbodor",
                "poipole"
            ),
            img = "@drawable/tumblr_42d99e6626f9175af95e2d34a19e147a_1d2e7502_1280"
        ),
        Question(
            text = "How many eeveelutions are there?",
            answer = listOf("8", "eight"),
            img = "@drawable/pok_mon_eevee_pok_dex"
        ),
        Question(
            text = "What is the name of the only WATER/FIRE type pokemon?",
            answer = listOf("volcanion"),
            img = "@drawable/maxresdefault"
        ),
        Question(
            text = "What is the only pokemon that can devolve?",
            answer = listOf("slowbro"),
            img = "@drawable/question_mark_003_pokemon_question_by_the1maguswriter_d6g7lwb_fullview"
        )
    )

    var questionsShuffled = questions.shuffled().toMutableList()

    var totalCorrect = 0
    val questionIndex = MutableLiveData<Int>().apply{value = 0}
    var currentQuestion = MutableLiveData<Question>().apply{value = questionsShuffled[questionIndex.value!!]}
    val currentTime = MutableLiveData<Long>()
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish
    private val _eventGameFinishTime = MutableLiveData<Boolean>()
    val eventGameFinishTime: LiveData<Boolean>
        get() = _eventGameFinishTime

    fun nextQuestion() {
        if (questionIndex.value != 4) {
            questionIndex.value = (questionIndex.value)?.plus(1)
            currentQuestion.value = questionsShuffled[questionIndex.value!!]
        } else {
            _eventGameFinish.value = true
            totalCorrect = 5
        }
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GVM Destroyed")
    }

}