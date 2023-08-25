package uz.catsi.tahlil.navigation

import androidx.navigation.NavController
import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navigationFlow: Flow<NavController.() -> Unit>
}