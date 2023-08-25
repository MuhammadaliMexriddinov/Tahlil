package uz.catsi.tahlil.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppNavigationManager @Inject constructor() : NavigationHandler, AppNavigator {
    override val navigationFlow = MutableSharedFlow<NavController.() -> Unit>()

    private suspend fun navigate(block: NavController.() -> Unit) {
        navigationFlow.emit(block)
    }

    override suspend fun navigationTo(direction: NavDirections) = navigate { navigate(direction) }

    override suspend fun back() = navigate { popBackStack() }

    override suspend fun backUntil(@IdRes destinationId: Int)
        = navigate { popBackStack(destinationId,false) }
}

