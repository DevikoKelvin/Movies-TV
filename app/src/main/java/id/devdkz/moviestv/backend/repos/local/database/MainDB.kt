package id.devdkz.moviestv.backend.repos.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.devdkz.moviestv.backend.repos.local.entities.BookmarkEnt
import id.devdkz.moviestv.backend.repos.local.entities.MovieEnt
import id.devdkz.moviestv.backend.repos.local.entities.TVEnt

@Database(
    entities = [MovieEnt::class, TVEnt::class, BookmarkEnt::class],
    version = 1,
    exportSchema = false
)
abstract class MainDB : RoomDatabase()
{
    abstract fun dbQuery() : DBQuery

    companion object
    {
        @Volatile
        private var INSTANCE : MainDB? = null

        fun getInstance(context : Context) : MainDB =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MainDB::class.java,
                    "MovieTV.db"
                ).build()
            }
    }
}