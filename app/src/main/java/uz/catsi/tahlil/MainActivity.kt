package uz.catsi.tahlil

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.prongbang.localization.LocalizationAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.catsi.tahlil.navigation.NavigationHandler
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : LocalizationAppCompatActivity() {
    @Inject
    lateinit var navigationHandler: NavigationHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigationHandler
            .navigationFlow
            .onEach {
                it.invoke(
                    findNavController(R.id.fragmentContainerView)
                )
            }
            .launchIn(lifecycleScope)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        block?.invoke()
        openPrepareLocalize()
        super.onConfigurationChanged(newConfig)
    }

    fun mySetLocate(locale: Locale, _block: (() -> Unit)) {
        block = _block
        setLocale(locale)
    }

    private var block: (() -> Unit)? = null
}