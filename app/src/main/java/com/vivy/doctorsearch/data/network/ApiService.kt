package com.vivy.doctorfinder.data.network

import com.vivy.doctorfinder.data.model.LoginModel
import com.vivy.doctorfinder.data.model.SearchModel
import io.reactivex.Observable
import retrofit2.http.*


interface ApiService{

    @GET("/api/users/me/doctors")
    fun search(@QueryMap(encoded = true) query: Map<String,
            @JvmSuppressWildcards Any>,
               @HeaderMap headers:Map<String, String>):
            Observable<SearchModel>


    @FormUrlEncoded
    @POST("https://auth.staging.vivy.com/oauth/token?grant_type=password")
    fun login(@Field("username") username: String,
              @Field("password") password: String,
              @HeaderMap header: Map<String, @JvmSuppressWildcards Any>):
            Observable<LoginModel>
}