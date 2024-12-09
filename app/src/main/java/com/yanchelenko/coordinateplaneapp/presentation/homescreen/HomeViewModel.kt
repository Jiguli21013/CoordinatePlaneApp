package com.yanchelenko.coordinateplaneapp.presentation.homescreen

import androidx.lifecycle.ViewModel
import com.yanchelenko.coordinateplaneapp.presentation.navigation.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(

): ViewModel() {

    fun onUserClickedGoButton(enteredPoints: Int) {
        Navigation.goToGraphFragment(enteredPoints)
    }
}
