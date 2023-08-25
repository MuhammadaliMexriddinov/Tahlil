package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.ui.screens.MainScreenDirections
import uz.catsi.tahlil.presenter.viewmodel.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel(), MainViewModel {

    override fun goTest() {
        viewModelScope.launch {
            appNavigator.navigationTo(
                MainScreenDirections.actionMainScreenToTestScreen()
            )
        }
    }
    override fun goAbout() {
        viewModelScope.launch {
            appNavigator.navigationTo(
                MainScreenDirections.actionMainScreenToAboutScreen()
            )
        }
    }
    override fun goFaq() {
        viewModelScope.launch {
            appNavigator.navigationTo(
                MainScreenDirections.actionMainScreenToFaqScreen()
            )
        }
    }
    override fun goApp() {
        viewModelScope.launch {
            appNavigator.navigationTo(
                MainScreenDirections.actionMainScreenToAppScreen()
            )
        }
    }
    override fun goLanguage() {
        viewModelScope.launch {
            appNavigator.navigationTo(
                MainScreenDirections.actionMainScreenToLanguageScreen()
            )
        }
    }
}