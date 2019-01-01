package br.com.jeancss01.app.starwars.database.film.dao

import android.arch.persistence.room.*
import br.com.jeancss01.app.starwars.database.film.model.Film

@Dao
interface FilmDAO {

    @get:Query("SELECT * FROM film")
    val all: List<Film>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(Film: Film): Long

    @Insert
    fun insertListFilm(films: List<Film>)

    @Update
    fun updateFilm(vararg film: Film)

    @Delete
    fun deleteFilm(vararg film: Film)

    @Query("DELETE FROM film")
    fun nukeTable()
}