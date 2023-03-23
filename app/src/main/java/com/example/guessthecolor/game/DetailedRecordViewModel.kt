package com.example.guessthecolor.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guessthecolor.database.TrackRecords
import com.example.guessthecolor.database.TrackRecordsDatabaseDao

class DetailedRecordViewModel (
    val database: TrackRecordsDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val _scores = MutableLiveData<List<TrackRecords>>()
    val scores: LiveData<List<TrackRecords>>
        get() = _scores

    init {
        _scores.value = database.getScores()
    }


}