package online.kapowai.unexbank.navigation

import android.os.Bundle
import androidx.navigation.NavOptions

sealed class NavigatorAction {

    // TODO Questions left:
    // - Does we need Replace action, and does NacComp support it
    // - How to resolve deep links correctly (including ones that depends on user state, e.g. loggedIn boolean)

    data class Forward(
            val frame: Int,
            val action: Int,
            val bundle: Bundle? = null,
            val navOptions: NavOptions? = null
    ) : NavigatorAction()

    data class Back(
            val frame: Int
    ) : NavigatorAction()

    data class BackTo(
            val frame: Int,
            val destination: Int,
            val inclusive: Boolean = false // Also deletes specified fragment from stack
    ) : NavigatorAction()

}