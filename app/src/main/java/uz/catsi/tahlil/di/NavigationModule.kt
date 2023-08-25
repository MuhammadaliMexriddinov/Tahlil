package uz.catsi.tahlil.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.catsi.tahlil.navigation.AppNavigationManager
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.navigation.NavigationHandler

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {
    @Binds
    fun bindNavigationHandler(impl: AppNavigationManager): NavigationHandler

    @Binds
    fun bindAppNavigator(impl: AppNavigationManager): AppNavigator
}