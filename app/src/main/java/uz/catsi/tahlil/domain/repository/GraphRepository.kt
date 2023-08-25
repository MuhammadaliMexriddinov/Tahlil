package uz.catsi.tahlil.domain.repository

import com.github.mikephil.charting.data.LineData
import kotlinx.coroutines.flow.StateFlow

interface GraphRepository {
    val graphLineData: StateFlow<LineData?>

    suspend fun check(index: Int, state: Boolean)
    fun redraw()
    fun clearAllData()
}