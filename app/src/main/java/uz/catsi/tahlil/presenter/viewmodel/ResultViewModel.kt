package uz.catsi.tahlil.presenter.viewmodel

import com.github.mikephil.charting.data.LineData
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import uz.catsi.tahlil.data.model.common.ResultModel

interface ResultViewModel {
    fun again()
    fun back()
    fun home()
    fun checkBox(index: Int, checked: Boolean)

    val data: StateFlow<LineData?>
    val clearGraph: SharedFlow<Unit>
}