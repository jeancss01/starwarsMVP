package br.com.jeancss01.app.starwars.module.listFilm.mvp

import android.content.Context
import android.util.Log
import android.view.View
import br.com.jeancss01.app.starwars.common.Utils
import br.com.jeancss01.app.starwars.database.common.AppDatabase
import br.com.jeancss01.app.starwars.database.film.FilmRepository
import br.com.jeancss01.app.starwars.network.common.StarWarsNetworking
import br.com.jeancss01.app.starwars.network.film.model.FilmList
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class ListFilmModel(val context: Context, private val presenter: MVP.PresenterImpl): MVP.ModelImpl {

    private val mFilmRepository = FilmRepository(context)

    private val request = StarWarsNetworking.shared

    private val filmObservable: Observable<FilmList>
        get() = request
            .service
            .films
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    private val filmObserver: DisposableObserver<FilmList>
        get() {

            presenter.showProgressBar(true)

            return object : DisposableObserver<FilmList>() {

                override fun onNext(filmList: FilmList) {
                    mFilmRepository.insert(filmList)
                    presenter.updateListRecycler(ArrayList(filmList.films))
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()

                    presenter.showProgressBar(false)
                    presenter.setVisible(View.GONE)
                }

                override fun onComplete() {
                    presenter.showProgressBar(false)
                    presenter.setVisible(View.VISIBLE)
                }
            }
        }

    override fun getFilmsRequest() {

        if (Utils.isConnected(context)) {
            filmObservable.subscribeWith<DisposableObserver<FilmList>>(filmObserver)
        } else{

            val filmList = mFilmRepository.getAllFilm()

            if(filmList.size > 1){
                presenter.showProgressBar(false)
                presenter.updateListRecycler(ArrayList(filmList))
            } else {
                presenter.setVisible(View.GONE)
            }
        }
    }
}