package br.com.jeancss01.app.starwars.network.film.model

import br.com.jeancss01.app.starwars.database.film.model.Film
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmList  (@Expose
                      @SerializedName("count")
                      val count: String,
                      @Expose
                      @SerializedName("next")
                      val next: String,
                      @Expose
                      @SerializedName("previous")
                      val previous: String,
                      @Expose
                      @SerializedName("results")
                      var films: ArrayList<Film>)