package com.example.mymovieapp.app.utils.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.movie.ResponseState
import com.example.mymovieapp.app.utils.blur.BlurTransformation
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

fun RequestBuilder<Drawable>.doOnFinished(block: () -> Unit) =
    addListener(object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean,
        ): Boolean {
            block()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean,
        ): Boolean {
            block()
            return false
        }

    })

fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
    MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)


fun changeResponseState(page: Int, totalPage: Int): ResponseState =
    ResponseState(
        totalPage = totalPage,
        page = page,
        isHasNextPage = page < totalPage,
        isHasPreviousPage = page > 1
    )


fun Context.showBlurImage(
    blurSize: Float,
    imageUrl: String,
    imageView: ImageView
) {
    Glide.with(this)
        .load(imageUrl)
        .transform(BlurTransformation(blurSize))
        .into(imageView)
}


private fun shimmerDrawable(): ShimmerDrawable {
    val shimmer =
        Shimmer.AlphaHighlightBuilder()// The attributes for a ShimmerDrawable is set by this builder
            .setDuration(1800) // how long the shimmering animation takes to do one full sweep
            .setBaseAlpha(0.7f) //the alpha of the underlying children
            .setHighlightAlpha(0.6f) // the shimmer alpha amount
            .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
            .setAutoStart(true)
            .build()

    // This is the placeholder for the imageView
    return ShimmerDrawable().apply {
        setShimmer(shimmer)
    }
}


fun Context.showRoundedImage(
    roundedSize: Int = 8.toDp,
    imageUrl: String,
    imageView: ImageView,
) {
    val requestOptions = RequestOptions()
        .transforms(CenterCrop(), RoundedCorners(roundedSize))
        .timeout(3000)
        .placeholder(shimmerDrawable())
    Glide.with(this)
        .load(imageUrl)
        .apply(requestOptions)
        .into(imageView)
}

fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun View.startSlideInRightAnim() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.slide_in_right
        )
    )
}

fun View.downEffect(): View {
    PushDownAnim.setPushDownAnimTo(this)
    return this
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) {
        it?.let(observer)
    }
}


fun View.setOnDownEffectClick(onClickListener: View.OnClickListener): View {
    PushDownAnim.setPushDownAnimTo(this).setOnClickListener(onClickListener)
    return this
}

fun View.startSlideInLeftAnim() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.slide_in_left_anim
        )
    )
}


fun View.startItemAnim() {
    this.startAnimation(
        AnimationUtils.loadAnimation(
            this.context,
            R.anim.item_anim
        )
    )
}

class CoroutineScopeWrapper(
    val scope: CoroutineScope,
    var errorHandler: (Throwable) -> Unit = globalErrorHandler
) {
    fun <T> Flow<T>.observe(action: suspend (T) -> Unit) = this
        .onEach(action)
        .catch { errorHandler(it) }
        .launchIn(scope)

    companion object {
        var globalErrorHandler: (Throwable) -> Unit = { throw it }
    }
}

inline fun Fragment.launchWhenViewStarted(
    crossinline block: suspend CoroutineScopeWrapper.() -> Unit
) = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
    CoroutineScopeWrapper(this).block()
}

inline fun Fragment.launchWhenViewResumed(
    crossinline block: suspend CoroutineScopeWrapper.() -> Unit
) = viewLifecycleOwner.lifecycleScope.launchWhenResumed {
    CoroutineScopeWrapper(this).block()
}

val Int.toDp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
