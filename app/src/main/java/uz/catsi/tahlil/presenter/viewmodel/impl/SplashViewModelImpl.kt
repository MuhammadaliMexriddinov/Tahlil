package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.ui.screens.SplashScreenDirections
import uz.catsi.tahlil.presenter.viewmodel.SplashViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), SplashViewModel {
    init {
        viewModelScope.launch {
            delay(2600)
            appNavigator.navigationTo(
                SplashScreenDirections.actionSplashScreenToMainScreen()
            )
        }
    }
}