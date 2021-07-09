package com.example.sample.views.model

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(

    //@SerializedName("cast")
    val cast: ArrayList<String>,
    //@SerializedName("genres")
    val genres: ArrayList<String>,
    //@SerializedName("rating")
    val rating: Int,
    //@SerializedName("title")
    val title: String,
    //@SerializedName("year")
    val year: Int
):Parcelable

