package br.com.jeancss01.app.starwars.database.common

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.jeancss01.app.starwars.database.film.dao.FilmDAO
import br.com.jeancss01.app.starwars.database.film.model.Film

@Database(entities = arrayOf(Film::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun filmDAO(): FilmDAO

    companion object {

        private var INSTANCE: AppDatabase? = null
        fun getAppDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase::class.java, "starwarsDB")
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

    }
}
