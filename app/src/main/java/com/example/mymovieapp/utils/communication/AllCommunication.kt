package com.example.mymovieapp.utils.communication

import com.example.mymovieapp.utils.event.Event
import com.example.mymovieapp.utils.navigation.NavigationCommand

interface NavigationCommunication : Communication<Event<NavigationCommand>> {
    class Base : Communication.Base<Event<NavigationCommand>>(), NavigationCommunication
}