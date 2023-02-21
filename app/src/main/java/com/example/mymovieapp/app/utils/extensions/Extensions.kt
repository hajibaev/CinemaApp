package com.example.mymovieapp.app.utils.extensions

import android.content.Context
import android.content.res.Resources
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import com.example.mymovieapp.R
import com.example.mymovieapp.app.models.movie.ResponseState
import com.thekhaeng.pushdownanim.PushDownAnim
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*


fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
    MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)


fun changeResponseState(page: Int, totalPage: Int): ResponseState =
    ResponseState(
        totalPage = totalPage,
        page = page,
        isHasNextPage = page < totalPage,
        isHasPreviousPage = page > 1
    )

fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) {
        it?.let(observer)
    }
}


fun View.setOnDownEffectClickListener(onClickListener: View.OnClickListener): View {
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
