package com.vivy.doctorfinder.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Doctor(
    @PrimaryKey

    @SerializedName("id") var id: String,
    @SerializedName("name") var name: String?,
    @SerializedName("photoId") var photoId: String?,
    @SerializedName("address") var address: String?,
    @SerializedName("email") var email: String?,
    @SerializedName("phoneNumber") var phoneNumber: String?,
    @SerializedName("rating") var rating: String?,
    @SerializedName("reviewCount") var reviewCount: String?

)