package com.example.mymovieapp.app.utils.extensions

import androidx.navigation.NavController
import com.example.mymovieapp.app.utils.navigation.NavCommand

fun NavController.navigateTo(navCommand: NavCommand) {
    navigate(navCommand.resId, navCommand.args)
}
