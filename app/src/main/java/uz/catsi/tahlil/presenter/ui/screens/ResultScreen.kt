package uz.catsi.tahlil.presenter.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenResultBinding
import uz.catsi.tahlil.presenter.viewmodel.ResultViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.ResultViewModelImpl

@AndroidEntryPoint
class ResultScreen : Fragment(R.layout.screen_result) {
    private val binding by viewBinding(ScreenResultBinding::bind)
    private val viewModel: ResultViewModel by viewModels<ResultViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        graph()
        clickEvents()
        subscribe()
    }

    private fun subscribe() = with(viewModel) {
        binding.apply {
            data
                .onEach {
                    if (it == null) return@onEach
                    lineChart.apply {
                        data = it
                        invalidate()
                        animateX(1200)
                    }
                }
                .launchIn(lifecycleScope)
            clearGraph
                .onEach {
                    lineChart.clearAllViewportJobs()
                    lineChart.clear()
                }.launchIn(lifecycleScope)
        }
    }

    private fun graph() {
        binding.lineChart.apply {
            isDragEnabled = true
            setScaleEnabled(false)
            description = null
        }
//
//        val points1: ArrayList<Entry> = ArrayList()
//        points1.add(Entry(1f, 1f))
//        points1.add(Entry(2f, 3f))
//        points1.add(Entry(3f, 5f))
//        points1.add(Entry(4f, 3f))
//        points1.add(Entry(5f, 1f))
//        points1.add(Entry(6f, 4f))
//        points1.add(Entry(7f, 3f))
//
//        val points2: ArrayList<Entry> = ArrayList()
//        points2.add(Entry(1f, 5f))
//        points2.add(Entry(2f, 6f))
//        points2.add(Entry(3f, 7f))
//        points2.add(Entry(4f, 2f))
//        points2.add(Entry(5f, 8f))
//        points2.add(Entry(6f, 5f))
//        points2.add(Entry(7f, 5f))
//
//        val points3: ArrayList<Entry> = ArrayList()
//        points3.add(Entry(1f, 6f))
//        points3.add(Entry(2f, 2f))
//        points3.add(Entry(3f, 5f))
//        points3.add(Entry(7f, 8f))
//
//        val set1: LineDataSet = LineDataSet(points1, requireContext().getString(R.string.potentialInfected))
//        val set2: LineDataSet = LineDataSet(points2, requireContext().getString(R.string.infected))
//        val set3: LineDataSet = LineDataSet(points3, requireContext().getString(R.string.recovered))
//        set1.form = Legend.LegendForm.CIRCLE
//        set2.form = Legend.LegendForm.CIRCLE
//        set3.form = Legend.LegendForm.CIRCLE
//        set1.circleRadius = 1f
//        set2.circleRadius = 1f
//        set3.circleRadius = 1f
//
//        set1.setCircleColor(parseColor("#F07427"))
//        set2.setCircleColor(GREEN)
//        set3.setCircleColor(BLUE)
//        set1.mode = LineDataSet.Mode.CUBIC_BEZIER
//        set2.mode = LineDataSet.Mode.CUBIC_BEZIER
//        set3.mode = LineDataSet.Mode.CUBIC_BEZIER
//
//        set1.color = parseColor("#F07427")
//        set2.color = GREEN
//        set3.color = BLUE
//        set1.fillAlpha = 110
//        set1.lineWidth = 4f
//        set2.lineWidth = 4f
//        set3.lineWidth = 4f
//        val dataSet: ArrayList<ILineDataSet> = ArrayList()
//        dataSet.add(set2)
////        dataSet.add(set3)
//        binding.apply {
//            btnCheck1.setOnClickListener {
//                if (btnCheck1.isChecked)
//                    dataSet.add(set1)
//                else dataSet.remove(set1)
//                addData(dataSet)
//            }
//            btnCheck2.setOnClickListener {
//                if (btnCheck2.isChecked)
//                    dataSet.add(set2)
//                else dataSet.remove(set2)
//                addData(dataSet)
//            }
//            btnCheck3.setOnClickListener {
//                if (btnCheck3.isChecked)
//                    dataSet.add(set3)
//                else dataSet.remove(set3)
//                addData(dataSet)
//            }
//        }
//        addData(dataSet)
//    }
//
//    private fun addData(dataSet: ArrayList<ILineDataSet>) {
//        binding.lineChart.apply {
//            data = LineData(dataSet)
//            invalidate()
//            animateX(2500)
//        }
    }

    private fun clickEvents() = with(binding) {
        viewModel.apply {
            btnCheck1.setOnClickListener { checkBox(0, btnCheck1.isChecked) }
            btnCheck2.setOnClickListener { checkBox(1, btnCheck2.isChecked) }
            btnCheck3.setOnClickListener { checkBox(2, btnCheck3.isChecked) }
            btnBack.setOnClickListener { back() }
            btnHome.setOnClickListener { home() }
            btnAgain.setOnClickListener { again() }
        }
    }
}