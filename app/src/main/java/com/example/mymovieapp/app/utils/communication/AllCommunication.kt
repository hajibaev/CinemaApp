package com.example.mymovieapp.app.utils.communication

import com.example.mymovieapp.app.utils.event.Event
import com.example.mymovieapp.app.utils.navigation.NavigationCommand

interface NavigationCommunication : Communication<Event<NavigationCommand>> {
    class Base : Communication.Base<Event<NavigationCommand>>(), NavigationCommunication
}