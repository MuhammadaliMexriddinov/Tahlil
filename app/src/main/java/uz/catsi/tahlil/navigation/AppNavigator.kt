package uz.catsi.tahlil.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface AppNavigator {
    suspend fun navigationTo(direction: NavDirections)
    suspend fun back()
    suspend fun backUntil(@IdRes destinationId: Int)
}

