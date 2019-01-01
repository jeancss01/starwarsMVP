package br.com.jeancss01.app.starwars.database.film.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
data class Film(
    @Expose
    @SerializedName("title")
    var title: String = "",
    @PrimaryKey()
    @Expose
    @SerializedName("episode_id")
    var episode_id: String = "",
    @Expose
    @SerializedName("opening_crawl")
    var opening_crawl: String = "",
    @Expose
    @SerializedName("director")
    var director: String = "",
    @Expose
    @SerializedName("producer")
    var producer: String = "",
    @Expose
    @SerializedName("release_date")
    var release_date: String = "",
    @Expose
    @SerializedName("characters")
    @Ignore
    var characters: List<String>? = null,
    @Expose
    @SerializedName("planets")
    @Ignore
    var planets: List<String>? = null,
    @Expose
    @SerializedName("starships")
    @Ignore
    var starships: List<String>? = null,
    @Expose
    @SerializedName("vehicles")
    @Ignore
    var vehicles: List<String>? = null,
    @Expose
    @SerializedName("species")
    @Ignore
    var species: List<String>? = null,
    @Expose
    @SerializedName("created")
    var created: String = "",
    @Expose
    @SerializedName("edited")
    var edited: String = "",
    @Expose
    @SerializedName("url")
    var url: String = ""): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(episode_id)
        parcel.writeString(opening_crawl)
        parcel.writeString(director)
        parcel.writeString(producer)
        parcel.writeString(release_date)
        parcel.writeStringList(characters)
        parcel.writeStringList(planets)
        parcel.writeStringList(starships)
        parcel.writeStringList(vehicles)
        parcel.writeStringList(species)
        parcel.writeString(created)
        parcel.writeString(edited)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Film> {
        override fun createFromParcel(parcel: Parcel): Film {
            return Film(parcel)
        }

        override fun newArray(size: Int): Array<Film?> {
            return arrayOfNulls(size)
        }
    }
}