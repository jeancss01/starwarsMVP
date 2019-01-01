package br.com.jeancss01.app.starwars.common

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.support.v7.app.AlertDialog
import android.text.Html
import android.text.Spanned
import android.widget.Toast
import br.com.jeancss01.app.starwars.R

object Utils {

    fun isConnected(mContext: Context): Boolean {
        val cm = mContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else {
            Toast.makeText(mContext, mContext.getString(R.string.erro_conexao_indisponivel), Toast.LENGTH_LONG).show()

            false
        }
    }

    fun getImage( url : String):String{

        return "https://starwars-visualguide.com/assets/img/"+ splitString(url) +".jpg"
    }

    fun splitString(s: String): String {

        val separated = s.split("/")
        return separated[separated.size - 3]+"/"+separated[separated.size- 2]
    }


    fun spliStringDateYear(s: String): String {

        val separated = s.split("-")
        return separated[0]
    }

    fun spliStringDate(s: String): String {

        val separated = s.split("-")
        return separated[2]+"/"+separated[1]+"/"+separated[0]
    }


    fun strEpisode(s: String): String {
        return "Episode: $s"
    }
}