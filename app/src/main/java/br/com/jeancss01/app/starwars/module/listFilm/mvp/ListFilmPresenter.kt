package br.com.jeancss01.app.starwars.module.listFilm.mvp

import android.content.Context
import android.view.View
import br.com.jeancss01.app.starwars.database.film.model.Film

class ListFilmPresenter(context: Context) : MVP.PresenterImpl {

    private val model: MVP.ModelImpl
    private var view: MVP.ViewImpl? = null

    init {
        this.model = ListFilmModel(context, this)
    }

    override val context: Context?
        get() = (view as Context?)

    override fun showProgressBar(status: Boolean) {

        if (status) {
            view?.showProgressBar( View.VISIBLE)
            view.let { it?.setVisibleNoNetwork(View.GONE) }
            view.let { it?.setVisibleListFilms(View.VISIBLE) }
        } else {
            view?.showProgressBar(View.GONE)
        }
    }

    override fun setView(view: MVP.ViewImpl) {
        this.view = view
    }

    override fun setVisible(code: Int) {

        if (code == View.VISIBLE) {
            view.let { it?.setVisibleListFilms(View.VISIBLE) }
            view.let { it?.setVisibleNoNetwork(View.GONE) }
        } else {
            view.let { it?.setVisibleListFilms(View.GONE) }
            view.let { it?.setVisibleNoNetwork(View.VISIBLE) }
        }
    }

    override fun updateListRecycler(films: ArrayList<Film>) {
        view.let { it?.updateListRecycler(films) }
    }

    override fun getFilmsRequest() = model.getFilmsRequest()
}