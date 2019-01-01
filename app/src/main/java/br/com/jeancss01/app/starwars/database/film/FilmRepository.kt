package br.com.jeancss01.app.starwars.database.film

import android.content.Context
import android.os.AsyncTask
import br.com.jeancss01.app.starwars.database.common.AppDatabase
import br.com.jeancss01.app.starwars.database.film.dao.FilmDAO
import br.com.jeancss01.app.starwars.database.film.model.Film
import br.com.jeancss01.app.starwars.network.film.model.FilmList

class FilmRepository(val context: Context) {

    lateinit var filmDAO: FilmDAO

    init {
        val db = AppDatabase.getAppDatabase(context)

        if (db != null) {
            filmDAO = db.filmDAO()
        }
    }

    fun getAllFilm(): List<Film> {
        return LoadSegmentTask(filmDAO).execute().get()
    }

    fun insert(filmList: FilmList): Boolean {
        return InsertTask(filmDAO).execute(filmList).get() > 0
    }

    fun delete() {
        DeleteTask(filmDAO).execute()
    }

    class InsertTask(private val filmDAO: FilmDAO): AsyncTask<FilmList, Void, Long>() {

        override fun doInBackground(vararg params: FilmList): Long {
            var row: Long = 0

            val listFilm = params[0]

            if (filmDAO.all.isNotEmpty()) {
                filmDAO.nukeTable()
            }

            listFilm.films.forEach { row = filmDAO.insertFilm(it) }
            return row
        }
    }

    class LoadSegmentTask(private val filmDAO: FilmDAO): AsyncTask<Void, Void, List<Film>>() {

        override fun doInBackground(vararg params: Void?): List<Film> {
            return filmDAO.all
        }
    }

    class DeleteTask(private val filmDAO: FilmDAO): AsyncTask<Void, Void, Void>() {

        override fun doInBackground(vararg params: Void?): Void? {
            filmDAO.nukeTable()

            return null
        }
    }
}