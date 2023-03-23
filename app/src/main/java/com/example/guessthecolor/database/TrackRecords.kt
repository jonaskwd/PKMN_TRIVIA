package com.example.guessthecolor.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.format.DateTimeFormatter
import java.util.Date

@Entity(tableName = "track_records_table")
data class TrackRecords (

    @ColumnInfo(name = "date_time")
    var dateTime: Long,

    @ColumnInfo(name = "game_score")
    var score: Long

){

    @PrimaryKey(autoGenerate = true)
    var trackId: Long = 0L
}