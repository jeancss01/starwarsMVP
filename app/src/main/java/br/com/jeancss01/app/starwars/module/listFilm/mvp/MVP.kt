package br.com.jeancss01.app.starwars.module.listFilm.mvp

import android.content.Context
import br.com.jeancss01.app.starwars.database.film.model.Film

interface MVP {

    interface PresenterImpl {
        val context: Context?
        fun showProgressBar(status: Boolean)
        fun setVisible(code: Int)
        fun setView(view: ViewImpl)
        fun getFilmsRequest()
        fun updateListRecycler(films: ArrayList<Film>)
    }

    interface ModelImpl {
        fun getFilmsRequest()
    }

    interface ViewImpl {

        companion object {
            val FILM_KEY = "film"
        }

        fun setVisibleNoNetwork(statusCode: Int)
        fun setVisibleListFilms(statusCode: Int)
        fun setVisibleLoading(statusCode: Int)
        fun updateListRecycler(films: ArrayList<Film>)
        fun clickListenerItemRecycler(film: Film)
        fun showProgressBar(status: Int)
    }
}