package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.catsi.tahlil.R
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.viewmodel.LanguageViewModel
import javax.inject.Inject

@HiltViewModel
class LanguageViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator,
) : ViewModel(), LanguageViewModel {

    override fun home() {
        viewModelScope.launch { appNavigator.backUntil(R.id.mainScreen) }
    }

    override fun back() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }
     override fun delaying() {
        viewModelScope.launch {
            delay(300)
            back()
        }
    }
}