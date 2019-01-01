package br.com.jeancss01.app.starwars.network.common

import br.com.jeancss01.app.starwars.network.film.model.FilmList
import io.reactivex.Observable
import retrofit2.http.GET


interface INetworkTyper {

    @get:GET("films")
    val films: Observable<FilmList>
}