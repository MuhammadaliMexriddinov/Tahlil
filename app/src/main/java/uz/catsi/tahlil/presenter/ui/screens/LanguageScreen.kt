package uz.catsi.tahlil.presenter.ui.screens

import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.catsi.tahlil.MainActivity
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenLanguageBinding
import uz.catsi.tahlil.presenter.viewmodel.LanguageViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.LanguageViewModelImpl
import java.util.*

@AndroidEntryPoint
class LanguageScreen : Fragment(R.layout.screen_language) {
    private val binding by viewBinding(ScreenLanguageBinding::bind)
    private val viewModel: LanguageViewModel by viewModels<LanguageViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        boldLangText()
        clickEvents()
    }

    private fun boldLangText(locale: Locale = resources.configuration.locale) = with(binding) {
        uzTxt.setTypeface(null, NORMAL)
        ruTxt.setTypeface(null, NORMAL)
        usTxt.setTypeface(null, NORMAL)
        when (locale.toLanguageTag()) {
            "uz" -> { uzTxt.setTypeface(null, BOLD) }
            "ru" -> { ruTxt.setTypeface(null, BOLD) }
            "en" -> { usTxt.setTypeface(null, BOLD) }
        }
    }

    private fun clickEvents() = with(binding) {
        btnBack.setOnClickListener { viewModel.back() }
        uzBtn.setOnClickListener { setLang(Locale("uz")) }
        enBtn.setOnClickListener { setLang(Locale.ENGLISH) }
        ruBtn.setOnClickListener { setLang(Locale.forLanguageTag("ru")) }
    }

    private fun setLang(locale: Locale) {
        boldLangText(locale)
        val activity = (requireActivity() as MainActivity)
        activity.mySetLocate(locale) { viewModel.delaying() }
    }
}