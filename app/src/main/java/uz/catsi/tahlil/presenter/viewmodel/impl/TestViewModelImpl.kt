package uz.catsi.tahlil.presenter.viewmodel.impl

import android.text.Editable
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.catsi.tahlil.data.model.common.ParamsModel
import uz.catsi.tahlil.domain.repository.TestRepository
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.ui.screens.TestScreenDirections
import uz.catsi.tahlil.presenter.viewmodel.TestViewModel
import uz.catsi.tahlil.utils.ConnectionUtil
import uz.catsi.tahlil.utils.isNotEmpty
import uz.catsi.tahlil.utils.textToFloat
import uz.catsi.tahlil.utils.textToInt
import javax.inject.Inject

@HiltViewModel
class TestViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator,
    private val repository: TestRepository,
    private val connectionUtil: ConnectionUtil,
) : ViewModel(), TestViewModel {
    override val infectionRate: MutableStateFlow<Float?> = MutableStateFlow(null)
    override val recoveryRate: MutableStateFlow<Float?> = MutableStateFlow(null)
    override val population: MutableStateFlow<Int?> = MutableStateFlow(null)
    override val initInfected: MutableStateFlow<Int?> = MutableStateFlow(null)
    override val initRecovered: MutableStateFlow<Int?> = MutableStateFlow(null)
    override val time: MutableStateFlow<Int?> = MutableStateFlow(null)
    override val message: MutableStateFlow<String?> = MutableStateFlow(null)

    override val btnState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    override val loading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    override fun check() {
        when (val online = connectionUtil.isConnected()) {
            false -> {
                toOfflineScreen()
                return
            }
            else -> {
                checkData(online != null)
            }
        }
    }

    private fun checkData(isMobileConnection: Boolean) {
        viewModelScope.launch { loading.emit(true) }
        val data = ParamsModel(
            infectionRate.value ?: 0.5f,
            recoveryRate.value ?: 1f,
            population.value ?: 1,
            initInfected.value ?: 1,
            initRecovered.value ?: 1,
            time.value ?: 3,
        )
        repository.doTest(isMobileConnection, data)
            .onEach {
                it.onFailure { e ->
                    Log.d("TTT", "Check failure: ${e.message}")
                    message.emit(e.message)
                    loading.emit(false)
                }
                it.onSuccess {
                    delay(100)
                    appNavigator.navigationTo(
                        TestScreenDirections.actionTestScreenToResultScreen()
                    )
                    loading.emit(false)
                }
            }.launchIn(viewModelScope)
    }

    override fun back() {
        viewModelScope.launch { appNavigator.back() }
    }

    override fun eraseAll() {
        viewModelScope.launch {
            infectionRate.emit(null)
            recoveryRate.emit(null)
            population.emit(null)
            initInfected.emit(null)
            initRecovered.emit(null)
            time.emit(null)
        }
    }

    override fun setInfectionRate(data: Editable?) {
        viewModelScope.launch { infectionRate.emit(data.textToFloat()) }
        checkAllEntered()
    }

    override fun setRecoveryRate(data: Editable?) {
        viewModelScope.launch { recoveryRate.emit(data.textToFloat()) }
        checkAllEntered()
    }

    override fun setPopulation(data: Editable?) {
        viewModelScope.launch { population.emit(data.textToInt()) }
        checkAllEntered()
    }

    override fun setInitInfected(data: Editable?) {
        viewModelScope.launch { initInfected.emit(data.textToInt()) }
        checkAllEntered()
    }

    override fun setInitRecovered(data: Editable?) {
        viewModelScope.launch { initRecovered.emit(data.textToInt()) }
        checkAllEntered()
    }

    override fun setTime(data: Editable?) {
        viewModelScope.launch { time.emit(data.textToInt()) }
        checkAllEntered()
    }

    private fun checkAllEntered() {
        viewModelScope.launch {
            btnState.emit(
                infectionRate.isNotEmpty &&
                    recoveryRate.isNotEmpty &&
                    population.isNotEmpty &&
                    initInfected.isNotEmpty &&
                    initRecovered.isNotEmpty &&
                    time.isNotEmpty
            )
        }
    }

    private fun toOfflineScreen() {
        Log.d("TTT", "toOfflineScreen: ")
        viewModelScope.launch {
            appNavigator.navigationTo(
                TestScreenDirections.actionTestScreenToOfflineScreen()
            )
        }
    }
}