package uz.catsi.tahlil.presenter.ui.screens

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenFaqBinding
import uz.catsi.tahlil.presenter.viewmodel.FaqViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.FaqViewModelImpl


@AndroidEntryPoint
class FaqScreen : Fragment(R.layout.screen_faq) {
    private val binding by viewBinding(ScreenFaqBinding::bind)
    private val viewModel: FaqViewModel by viewModels<FaqViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
    }

    private fun clickEvents() = with(binding) {
        btnBack.setOnClickListener { viewModel.back() }
        buttonSend.setOnClickListener {
            val emailSend = "ic@tashpmi.uz"
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:$emailSend")
            startActivity(intent)
        }
    }
}