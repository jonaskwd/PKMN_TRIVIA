package com.example.guessthecolor.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.guessthecolor.database.TrackRecordsDatabaseDao

class DetailedRecordsViewModelFactory (
    private val dataSource: TrackRecordsDatabaseDao,
    private val application: Application) : ViewModelProvider.Factory {

        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DetailedRecordViewModel::class.java)) {
                return DetailedRecordViewModel(dataSource, application) as T
            }
            throw java.lang.IllegalArgumentException("Unknown VielModel class")
        }
    }

