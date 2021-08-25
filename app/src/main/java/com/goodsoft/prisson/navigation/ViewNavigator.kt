package online.kapowai.unexbank.navigation

import android.app.Activity
import androidx.navigation.Navigation

class ViewNavigator(private val activity: Activity) {

    fun navigate(action: NavigatorAction) {
        when (action) {
            is NavigatorAction.Forward -> {
                Navigation.findNavController(activity, action.frame)
                        .navigate(action.action, action.bundle, action.navOptions)
            }
            is NavigatorAction.Back -> {
                Navigation.findNavController(activity, action.frame)
                        .popBackStack()
            }
            is NavigatorAction.BackTo -> {
                Navigation.findNavController(activity, action.frame)
                        .popBackStack(action.destination, action.inclusive) // what does it do?
            }
        }
    }

    companion object {
        const val TAG = "ViewNavigator"
    }

}