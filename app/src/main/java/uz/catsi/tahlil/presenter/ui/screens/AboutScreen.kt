package uz.catsi.tahlil.presenter.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenAboutBinding
import uz.catsi.tahlil.presenter.viewmodel.AboutViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.AboutViewModelImpl

@AndroidEntryPoint
class AboutScreen : Fragment(R.layout.screen_about) {
    private val binding by viewBinding(ScreenAboutBinding::bind)
    private val viewModel: AboutViewModel by viewModels<AboutViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
    }

    private fun clickEvents() = with(binding) {
        btnBack.setOnClickListener { viewModel.back() }
    }
}