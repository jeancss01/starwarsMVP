package br.com.jeancss01.app.starwars.module.DetailFilm

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import br.com.jeancss01.app.starwars.R
import br.com.jeancss01.app.starwars.common.Utils
import br.com.jeancss01.app.starwars.database.film.model.Film
import br.com.jeancss01.app.starwars.module.listFilm.mvp.MVP
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_film_detail.*

class FilmDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)
        setContentView(R.layout.activity_film_detail)

        setToolbar()
        getExtras()
    }

    private fun getExtras() {

        val bundle = intent

        if (bundle != null) {

            val film = intent.getParcelableExtra(MVP.ViewImpl.FILM_KEY) as Film

            tv_title?.text = film.title

            tv_director?.text = film.director

            tv_opening_crawl?.text = film.opening_crawl

            tv_release_date?.text = Utils.spliStringDate(film.release_date)

            tv_producer?.text = film.producer

            tv_episode?.text = film.episode_id

            Picasso.get()
                .load(Utils.getImage(film.url))
                .into(iv_people)

            title = film.title
        }
    }

    private fun setToolbar() {

        //setting toolbar
        setSupportActionBar(ui_film_detail_toolbar)

        //home navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Now get the support action bar
        val actionBar = supportActionBar

        // Set toolbar title/app title
        actionBar?.title = getString(R.string.label_toolbar_film_detail)

    }

    // actions on click menu items
    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            finish()
            overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
    }
}