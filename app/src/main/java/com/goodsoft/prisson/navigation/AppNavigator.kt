package com.goodsoft.prisson.navigation

import online.kapowai.unexbank.navigation.NavigatorAction
import online.kapowai.unexbank.navigation.ViewNavigator
import java.util.concurrent.LinkedBlockingQueue

class AppNavigator {

    private var viewNavigator: ViewNavigator? = null
    private var actions = LinkedBlockingQueue<NavigatorAction>()

    fun bindViewNavigator(newViewNavigator: ViewNavigator) {
        viewNavigator = newViewNavigator
        tryToMakeMoves()
    }

    fun unbindViewNavigator() {
        viewNavigator = null
    }

    fun navigate(action: NavigatorAction) {
        actions.offer(action)
        tryToMakeMoves()
    }

    private fun tryToMakeMoves() {
        viewNavigator?.let {
            while (actions.isNotEmpty()) {
                it.navigate(actions.take())
            }
        }
    }

    companion object {
        const val TAG = "AppNavigator"
    }

}