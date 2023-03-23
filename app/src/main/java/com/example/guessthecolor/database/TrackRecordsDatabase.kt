package com.example.guessthecolor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.guessthecolor.game.TrackRecordFragment

@Database(entities = [TrackRecords::class], version = 1, exportSchema = false)
abstract class TrackRecordsDatabase : RoomDatabase() {

    abstract val trackRecordsDatabaseDao : TrackRecordsDatabaseDao

    companion object {

        @Volatile
        private var INSTANCE: TrackRecordsDatabase? = null

        fun getInstance(context: Context) : TrackRecordsDatabase {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TrackRecordsDatabase::class.java,
                        "track_records_database"
                    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
                    INSTANCE = instance
                }


                return instance
            }
        }

    }

}
