package com.example.guessthecolor.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guessthecolor.database.TrackRecordsDatabase
import com.example.guessthecolor.database.TrackRecordsDatabaseDao
import kotlinx.coroutines.*

class TrackRecordViewModel (
    val database: TrackRecordsDatabaseDao,
    application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _bestScore = MutableLiveData<Long>()
    val bestScore: LiveData<Long>
        get() = _bestScore
    private val _lastScore = MutableLiveData<Long>()
    val lastScore: LiveData<Long>
        get() = _lastScore

    init {
        getRecordsCoroutine()
    }

    fun getRecordsCoroutine() {
        uiScope.launch {
            _bestScore.value = getBestRecord()
            _lastScore.value = getLastRecord()
        }
    }

    private suspend fun getBestRecord(): Long? {
        return withContext(Dispatchers.IO) {database.getBestScore()?.score}
    }

    private suspend fun getLastRecord(): Long? {
        return withContext(Dispatchers.IO) {database.getLastScore()?.score}
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}