package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.viewmodel.AboutViewModel
import javax.inject.Inject

@HiltViewModel
class AboutViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
): ViewModel(), AboutViewModel {
    override fun back() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }
}