package com.bailm.vivychallenge.util

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.vivy.doctorfinder.data.repository.AppRepository

class ImageLoader {

    companion object {
        const val EMPTY_IMAGE = "";
        fun load(imageView: ImageView, imageUrl: String, @DrawableRes holderId: Int) {
            if (imageUrl != EMPTY_IMAGE) {
                Glide.with(imageView.context)
                        .load(GlideUrl(
                            imageUrl, LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer ${AppRepository.token}")
                                .build()))
                        .placeholder(holderId)
                        .error(holderId)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView)
            } else {
                Glide.with(imageView.context)
                        .load(holderId)
                        .placeholder(holderId)
                        .error(holderId)
                        .centerCrop()
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageView)
            }
        }

        fun clear(imageView: ImageView) {
            Glide.with(imageView.context).clear(imageView)
        }
    }


}