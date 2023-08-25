package uz.catsi.tahlil.presenter.viewmodel.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.catsi.tahlil.R
import uz.catsi.tahlil.domain.repository.GraphRepository
import uz.catsi.tahlil.domain.repository.TestRepository
import uz.catsi.tahlil.navigation.AppNavigator
import uz.catsi.tahlil.presenter.viewmodel.ResultViewModel
import javax.inject.Inject

@HiltViewModel
class ResultViewModelImpl @Inject constructor(
    private val appNavigator: AppNavigator,
    private val repository: GraphRepository,
    private val testRepository: TestRepository,
) : ViewModel(), ResultViewModel {
    override val data = repository.graphLineData
    override val clearGraph = MutableSharedFlow<Unit>()

    init {
        repository.redraw()
    }

    override fun again() {
        back()
        testRepository.clearResult()
    }

    override fun back() {
        viewModelScope.launch {
            repository.clearAllData()
            testRepository.clearResult()
            clearGraph.emit(Unit)
            appNavigator.back()
        }
    }

    override fun home() {
        viewModelScope.launch { appNavigator.backUntil(R.id.mainScreen) }

    }

    override fun checkBox(index: Int, checked: Boolean) {
        viewModelScope.launch {
            repository.check(index, checked)
        }
    }
}