package com.example.mymovieapp.sqlite

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.model.TvShow
import com.example.mymovieapp.sqlite.dao.MovieDao
import com.example.mymovieapp.sqlite.dao.TvShowDao

@Database (
    entities = [Movie::class, TvShow::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao
    abstract fun tvShowDao() : TvShowDao

    companion object {
        private lateinit var instance : AppDatabase
        fun getInstance() : AppDatabase = instance
        fun createDatabase(context: Context) {
            instance = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "my_movie_app"
            )
                .allowMainThreadQueries()
                .build()
        }
    }
}