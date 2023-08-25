package uz.catsi.tahlil.data.model.common

data class ResultModel(
    val potentialInfected: ArrayList<Float>,
    val infected: ArrayList<Float>,
    val recovered: ArrayList<Float>,
)
