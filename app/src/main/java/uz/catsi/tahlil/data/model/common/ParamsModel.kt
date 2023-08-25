package uz.catsi.tahlil.data.model.common

data class ParamsModel(
    val infectionRate: Float,
    val recoveryRate: Float,
    val population: Int,
    val initInfected: Int,
    val initRecovered: Int,
    val time: Int,
)
