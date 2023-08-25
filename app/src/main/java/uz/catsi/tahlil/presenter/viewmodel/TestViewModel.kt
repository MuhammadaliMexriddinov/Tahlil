package uz.catsi.tahlil.presenter.viewmodel

import android.text.Editable
import kotlinx.coroutines.flow.StateFlow

interface TestViewModel {
    val infectionRate: StateFlow<Float?>
    val recoveryRate: StateFlow<Float?>
    val population: StateFlow<Int?>
    val initInfected: StateFlow<Int?>
    val initRecovered: StateFlow<Int?>
    val time: StateFlow<Int?>

    val message: StateFlow<String?>
    val btnState: StateFlow<Boolean>
    val loading: StateFlow<Boolean>

    fun check()
    fun back()
    fun eraseAll()

    fun setInfectionRate(data: Editable?)
    fun setRecoveryRate(data: Editable?)
    fun setPopulation(data: Editable?)
    fun setInitInfected(data: Editable?)
    fun setInitRecovered(data: Editable?)
    fun setTime(data: Editable?)
}