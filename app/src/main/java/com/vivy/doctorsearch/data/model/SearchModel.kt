package com.vivy.doctorfinder.data.model

import androidx.room.Dao
import com.google.gson.annotations.SerializedName

data class SearchModel(

    @SerializedName("lastKey") var lastKey: String,
    @field:SerializedName("doctors")
    val items:MutableList<Doctor>
)


