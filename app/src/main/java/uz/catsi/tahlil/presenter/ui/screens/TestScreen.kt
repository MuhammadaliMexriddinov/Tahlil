package uz.catsi.tahlil.presenter.ui.screens

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenTestBinding
import uz.catsi.tahlil.presenter.viewmodel.TestViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.TestViewModelImpl
import uz.catsi.tahlil.utils.erase
import uz.catsi.tahlil.utils.replaceOr
import uz.catsi.tahlil.utils.setOnClickDelayed
import uz.catsi.tahlil.utils.visible

@AndroidEntryPoint
class TestScreen : Fragment(R.layout.screen_test) {
    private val binding by viewBinding(ScreenTestBinding::bind)
    private val viewModel: TestViewModel by viewModels<TestViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
        subscribers()
        editTexts()
    }

    private fun editTexts() = with(binding) {
        viewModel.apply {
            param1.addTextChangedListener { setInfectionRate(it) }
            param2.addTextChangedListener { setRecoveryRate(it) }
            param3.addTextChangedListener { setPopulation(it) }
            param4.addTextChangedListener { setInitInfected(it) }
            param5.addTextChangedListener { setInitRecovered(it) }
            param6.addTextChangedListener { setTime(it) }
        }
    }

    private fun clickEvents() = with(binding) {
        btnCheck.setOnClickDelayed { viewModel.check() }
        btnBack.setOnClickListener { viewModel.back() }
        btnErase.setOnClickListener { viewModel.eraseAll() }
    }

    private fun subscribers() = with(viewModel) {
        binding.apply {
            loading.onEach {
                btnCheck.visible(!it)
                progress.visible(it)
                btnMask.visible(it)
            }.launchIn(lifecycleScope)

            btnState
                .onEach { btnCheck.isEnabled = it }
                .launchIn(lifecycleScope)

            message
                .onEach {
                    it?.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                }
                .launchIn(lifecycleScope)

            infectionRate
                .onEach {
                    it?.let {
                        param1.replaceOr(it)
                        return@onEach
                    }
                    param1.erase()
                }
                .launchIn(lifecycleScope)

            recoveryRate
                .onEach {
                    it?.let {
                        param2.replaceOr(it)
                        return@onEach
                    }
                    param2.erase()
                }
                .launchIn(lifecycleScope)

            population
                .onEach {
                    it?.let {
                        param3.replaceOr(it)
                        return@onEach
                    }
                    param3.erase()
                }
                .launchIn(lifecycleScope)

            initInfected
                .onEach {
                    it?.let {
                        param4.replaceOr(it)
                        return@onEach
                    }
                    param4.erase()
                }
                .launchIn(lifecycleScope)

            initRecovered
                .onEach {
                    it?.let {
                        param5.replaceOr(it)
                        return@onEach
                    }
                    param5.erase()
                }
                .launchIn(lifecycleScope)

            time
                .onEach {
                    it?.let {
                        param6.replaceOr(it)
                        return@onEach
                    }
                    param6.erase()
                }
                .launchIn(lifecycleScope)
        }
    }
}