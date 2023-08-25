package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.viewmodel.FaqViewModel
import javax.inject.Inject

@HiltViewModel
class FaqViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator
): ViewModel(), FaqViewModel {
    override fun back() {
        viewModelScope.launch {
            appNavigator.back()
        }
    }
}