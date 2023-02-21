package com.example.mymovieapp.app.utils.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.navigation.NavDirections
import kotlinx.android.parcel.Parcelize

sealed class NavigationCommand {

    data class ToDirection(val directions: NavDirections) : NavigationCommand()

    object Back : NavigationCommand()
}

@Parcelize
data class NavCommand(
    @IdRes
    val resId: Int,
    val args: Bundle
) : Parcelable

val EMPTY_NAV_COMMAND = NavCommand(-1, Bundle.EMPTY)

fun NavDirections.toNavCommand(): NavCommand {
    return NavCommand(actionId, arguments)
}