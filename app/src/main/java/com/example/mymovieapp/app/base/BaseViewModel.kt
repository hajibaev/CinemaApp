package com.example.mymovieapp.app.base

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import com.example.mymovieapp.app.utils.communication.NavigationCommunication
import com.example.mymovieapp.app.utils.event.Event
import com.example.mymovieapp.app.utils.navigation.NavCommand
import com.example.mymovieapp.app.utils.navigation.NavigationCommand
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


abstract class BaseViewModel : ViewModel() {

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> get() = _navigation


    private var navigationCommunication = NavigationCommunication.Base()


    private var _navCommand = createMutableSharedFlowAsSingleLiveEvent<NavCommand>()
    val navCommand: SharedFlow<NavCommand> get() = _navCommand.asSharedFlow()


    fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
        MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

    fun collectNavigation(owner: LifecycleOwner, observer: Observer<Event<NavigationCommand>>) =
        navigationCommunication.observe(owner = owner, observer = observer)


//
//    fun navigateToReadBookFragment(savedBook: BookThatRead) {
//        val savedBookPath = booksSaveToFileRepository.fetchSavedBookFilePath(savedBook.bookId)
//        if (savedBookPath == null) emitToErrorMessageFlow(IdResourceString(R.string.book_is_not_ready))
//        else navigate(router.navigateToReadBookFragment(patch = savedBookPath, book = savedBook))
//    }


    fun navigate(navCommand: NavCommand) = _navCommand.tryEmit(navCommand)

    fun navigate(navDirections: NavDirections) {
        _navigation.value = Event(NavigationCommand.ToDirection(navDirections))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

}
