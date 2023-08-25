package uz.catsi.tahlil.presenter.ui.screens

import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.catsi.tahlil.R
import uz.catsi.tahlil.databinding.ScreenAppinfoBinding
import uz.catsi.tahlil.presenter.viewmodel.AppViewModel
import uz.catsi.tahlil.presenter.viewmodel.impl.AppViewModelImpl
import uz.catsi.tahlil.utils.*

@AndroidEntryPoint
class AppScreen : Fragment(R.layout.screen_appinfo) {
    private val binding by viewBinding(ScreenAppinfoBinding::bind)
    private val viewModel: AppViewModel by viewModels<AppViewModelImpl>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickEvents()
    }

    private fun clickEvents() = with(binding) {
        btnBack.setOnClickListener { viewModel.back() }
        web.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(WEB))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                toast()
                e.printStackTrace()
            }
        }
        fb.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(FACEBOOK))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                toast()
                e.printStackTrace()
            }
        }
        insta.setOnClickListener {
            try {
                val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(INSTAGRAM))
                startActivity(myIntent)
            } catch (e: ActivityNotFoundException) {
                toast()
                e.printStackTrace()
            }
        }
        tg.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(TELEGRAM))
                val pm: PackageManager = requireActivity().packageManager
                if (intent.resolveActivity(pm) != null) {
                    startActivity(intent)
                } else {
                    try {
                        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(TELEGRAM))
                        startActivity(myIntent)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(requireContext(), ERROR_MESSAGE, Toast.LENGTH_LONG).show()
                        e.printStackTrace()
                    }
                }
            } catch (ignored: Exception) {
            }
        }
    }

    private fun toast() {
        Toast.makeText(
            requireContext(),
            ERROR_MESSAGE + ERROR_BROWSER,
            Toast.LENGTH_LONG
        ).show()
    }
}