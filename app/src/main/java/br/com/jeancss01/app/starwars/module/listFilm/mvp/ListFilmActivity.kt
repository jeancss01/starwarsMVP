package br.com.jeancss01.app.starwars.module.listFilm.mvp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import br.com.jeancss01.app.starwars.R
import br.com.jeancss01.app.starwars.database.film.model.Film
import br.com.jeancss01.app.starwars.module.DetailFilm.FilmDetailActivity
import br.com.jeancss01.app.starwars.module.listFilm.adapter.FilmAdapter
import kotlinx.android.synthetic.main.activity_list_films.*

class ListFilmActivity : AppCompatActivity(), MVP.ViewImpl, SwipeRefreshLayout.OnRefreshListener {

    private var filmAdapter: FilmAdapter? = null
    private var presenter: MVP.PresenterImpl? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_films)

        if (presenter == null) {
            presenter = ListFilmPresenter(this)
        }

        initRecyclerView()
        presenter?.setView(this)
        presenter?.getFilmsRequest()

    }

    private fun initRecyclerView() {
        sr_film.setOnRefreshListener(this)
        rv_film.layoutManager = LinearLayoutManager(this)

        filmAdapter = FilmAdapter(this ,arrayListOf<Film>() )
        rv_film.adapter = filmAdapter
    }

    override fun onRefresh() {
        presenter?.getFilmsRequest()
    }

    override fun showProgressBar(status: Int) {
        sr_film?.isRefreshing = false
        view_loading.visibility = status
    }


    override fun setVisibleNoNetwork(statusCode: Int) {
        sr_film?.isRefreshing = false
        view_no_network.visibility = statusCode
    }

    override fun setVisibleListFilms(statusCode: Int) {
        rv_film.visibility = statusCode
    }

    override fun setVisibleLoading(statusCode: Int) {
        view_loading.visibility = statusCode
    }


    override fun updateListRecycler(films: ArrayList<Film>) {
        filmAdapter?.getFilmsList(ArrayList(films))
        filmAdapter?.notifyDataSetChanged()
    }

    override fun clickListenerItemRecycler(film: Film) {
        val intent = Intent(this, FilmDetailActivity::class.java)
        intent.putExtra(MVP.ViewImpl.FILM_KEY, film)
        startActivity(intent)
    }

}
