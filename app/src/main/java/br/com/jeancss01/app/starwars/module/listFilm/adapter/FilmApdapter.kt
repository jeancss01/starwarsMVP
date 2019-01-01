package br.com.jeancss01.app.starwars.module.listFilm.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.jeancss01.app.starwars.R
import br.com.jeancss01.app.starwars.common.Utils
import br.com.jeancss01.app.starwars.database.film.model.Film
import br.com.jeancss01.app.starwars.module.listFilm.mvp.ListFilmActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_film.view.*

class FilmAdapter(val activity: ListFilmActivity, var films: ArrayList<Film>) : RecyclerView.Adapter<FilmAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_film, parent, false)

        val viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(films[position])

        holder.itemView.cv_proximo.setOnClickListener {
            val film = films[position]
            activity.clickListenerItemRecycler(film)
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setData(film: Film) {

            itemView.tv_title?.text = film.title
            itemView.tv_director?.text = film.director
            itemView.tv_launch_year?.text = Utils.spliStringDateYear(film.release_date)
            itemView.tv_title_episode?.text = Utils.strEpisode(film.episode_id)

            Picasso
                .get()
                .load(Utils.getImage(film.url))
                .placeholder(activity.getDrawable(R.drawable.no_image))
                .into(itemView.iv_people)
        }
    }

    fun getFilmsList(films: ArrayList<Film>) {
        this.films.clear()
        this.films.addAll(films)
        this.notifyDataSetChanged()
    }
}