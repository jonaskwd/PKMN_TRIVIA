package com.example.guessthecolor.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Insert as Insert

@Dao
interface TrackRecordsDatabaseDao {

    @Insert
    fun insert(track:TrackRecords)

    @Update
    fun update(track:TrackRecords)

    @Query("SELECT * FROM track_records_table ORDER BY game_score DESC LIMIT 1")
    fun getBestScore(): TrackRecords?

    @Query("SELECT * FROM track_records_table ORDER BY date_time DESC LIMIT 1")
    fun getLastScore(): TrackRecords?

    @Query("SELECT * FROM track_records_table ORDER BY date_time DESC")
    fun getScores(): List<TrackRecords>

    @Query("DELETE FROM track_records_table")
    fun deleteData()

}