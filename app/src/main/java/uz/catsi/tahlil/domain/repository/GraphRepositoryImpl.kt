package uz.catsi.tahlil.domain.repository

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import uz.catsi.tahlil.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GraphRepositoryImpl @Inject constructor(
    private val testRepository: TestRepository,
    @ApplicationContext private val context: Context
) : GraphRepository {
    private var dataSet: ArrayList<ILineDataSet> = ArrayList()
    private val sets: ArrayList<LineDataSet> = ArrayList(3)

    override fun redraw() {
        val points1: ArrayList<Entry> = ArrayList()
        val points2: ArrayList<Entry> = ArrayList()
        val points3: ArrayList<Entry> = ArrayList()
        testRepository.result?.apply {
            for (i in 1..infected.size) {
                points1.add(Entry(i.toFloat(), potentialInfected[i - 1]))
                points2.add(Entry(i.toFloat(), infected[i - 1]))
                points3.add(Entry(i.toFloat(), recovered[i - 1]))
            }
        }
        sets.add(LineDataSet(points1, context.getString(R.string.potentialInfected)))
        sets.add(LineDataSet(points2, context.getString(R.string.infected)))
        sets.add(LineDataSet(points3, context.getString(R.string.recovered)))
        sets.forEach {
//            it.valueTextColor = Color.WHITE
            it.mode = LineDataSet.Mode.CUBIC_BEZIER
            it.lineWidth = 4f
            it.form = Legend.LegendForm.CIRCLE
            it.circleRadius = 1f
        }
        sets[0].setCircleColor(Color.BLUE)
        sets[1].setCircleColor(Color.GREEN)
        sets[2].setCircleColor(Color.parseColor("#F07427"))
        sets[0].color = Color.BLUE
        sets[1].color = Color.GREEN
        sets[2].color = Color.parseColor("#F07427")
        sets[0].fillAlpha = 110
        dataSet.add(sets[1])
        graphLineData.value = LineData(dataSet)
    }

    override val graphLineData: MutableStateFlow<LineData?> = MutableStateFlow(null)

    override suspend fun check(index: Int, state: Boolean) {
        if (state) dataSet.add(sets[index])
        else dataSet.remove(sets[index])
        graphLineData.emit(LineData(dataSet))
    }

    override fun clearAllData() {
        sets.clear()
        dataSet.clear()
    }
}