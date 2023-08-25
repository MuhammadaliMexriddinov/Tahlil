package uz.catsi.tahlil.presenter.ui.screens

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenOfflineBinding
import uz.catsi.tahlil.presenter.viewmodel.OfflineViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.OfflineViewModelImpl

@AndroidEntryPoint
class OfflineScreen : Fragment(R.layout.screen_offline) {
    private val binding by viewBinding(ScreenOfflineBinding::bind)
    private val viewModel: OfflineViewModel by viewModels<OfflineViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
    }

    private fun clickEvents() = with(binding) {
        viewModel.apply {
            btnHome.setOnClickListener { home() }
            btnBack.setOnClickListener { back() }
            btnAgain.setOnClickListener { back() }
        }
    }
}