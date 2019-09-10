package mvvm.sample.foods.ui.base

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bailm.vivychallenge.util.ImageLoader
import com.bumptech.glide.BuildConfig
import com.bumptech.glide.Glide
import com.vivy.doctorsearch.R
import com.vivy.doctorsearch.core.Config
object CustomBindingAdapter{
    @JvmStatic
    @BindingAdapter(value = ["userId", "imageId"])
    fun loadImage(imageView: ImageView,  userId: String, imageId: String?) {
        if (imageId != null) { // images without an image url
            val imageUrl: String = Config.BASE_URL + "api/users/$userId/files/$imageId"
            ImageLoader.load(imageView, imageUrl, R.drawable.ic_launcher_background)
        } else {
            ImageLoader.load(imageView, ImageLoader.EMPTY_IMAGE, R.drawable.ic_launcher_background)
        }
    }
}
