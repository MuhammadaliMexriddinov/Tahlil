package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.catsi.tahlil.R
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.viewmodel.OfflineViewModel
import uz.catsi.tahlil.utils.ConnectionUtil
import javax.inject.Inject

@HiltViewModel
class OfflineViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator,
    connectionUtil: ConnectionUtil
) : ViewModel(), OfflineViewModel {

    init {
        connectionUtil.isOnline {
            if (it) back()
        }
    }

    override fun home() {
        viewModelScope.launch { appNavigator.backUntil(R.id.mainScreen) }
    }

    override fun back() {
        viewModelScope.launch { appNavigator.back() }
    }
}