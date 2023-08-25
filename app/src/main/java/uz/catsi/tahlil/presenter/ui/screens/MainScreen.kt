package uz.catsi.tahlil.presenter.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenMainBinding
import uz.catsi.tahlil.presenter.viewmodel.MainViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.MainViewModelImpl

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
    }

    private fun clickEvents() = with(binding) {
        viewModel.apply {
            btnTest.setOnClickListener { goTest() }
            btnAbout.setOnClickListener { goAbout() }
            btnFaq.setOnClickListener { goFaq() }
            btnApp.setOnClickListener { goApp() }
            btnLang.setOnClickListener { goLanguage() }
        }
        btnExit.setOnClickListener { requireActivity().finish() }
    }
}