package com.vivy.doctorfinder.data.model

import com.google.gson.annotations.SerializedName

data class LoginModel(

    @field:SerializedName("access_token")
    val accessToken: String?

)
