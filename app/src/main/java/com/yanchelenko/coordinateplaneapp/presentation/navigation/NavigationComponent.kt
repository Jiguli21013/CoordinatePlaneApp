package com.yanchelenko.coordinateplaneapp.presentation.navigation

import androidx.fragment.app.FragmentManager
import com.yanchelenko.coordinateplaneapp.R
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.GraphFragment
import com.yanchelenko.coordinateplaneapp.presentation.graphscreen.GraphFragment.Companion.GRAPH_FRAGMENT_TAG
import com.yanchelenko.coordinateplaneapp.presentation.homescreen.HomeFragment

object Navigation {
    var supportFragmentManager: FragmentManager? = null

    fun goToGraphFragment(pointsNumber: Int) {
        supportFragmentManager?.also {
            it.beginTransaction()
                .replace(R.id.activityMainFL, GraphFragment.newInstance(pointsNumber))
                .addToBackStack(GRAPH_FRAGMENT_TAG)
                .commit()
        }
    }

    fun goToMainFragment() {
        supportFragmentManager?.also {
            it.beginTransaction()
                .replace(R.id.activityMainFL, HomeFragment.newInstance())
                .commit()
        }
    }
}
