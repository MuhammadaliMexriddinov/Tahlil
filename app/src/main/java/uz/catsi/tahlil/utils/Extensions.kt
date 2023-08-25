package uz.catsi.tahlil.utils

import android.text.Editable
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import uz.catsi.tahlil.data.model.common.ResultModel


fun AppCompatEditText.erase() = this.text?.clear()

fun AppCompatEditText.replaceOr(e: Any) {
    text?.let {
        if (text!!.isEmpty())
            setText(e.toString())
    }
}

fun Number.toRequestMedia(): RequestBody = this.toString().toRequestBody("text/plain".toMediaType())

fun ResponseBody?.convertToResult(): ResultModel {
    this?.let {
        val data = this.source().readUtf8()
        val result = ResultModel(
            potentialInfected = ArrayList(3),
            infected = ArrayList(3),
            recovered = ArrayList(3),
        )
        val sb: StringBuilder = StringBuilder()
        var index = -1
        var start = false
        data.forEach {
            when (it) {
                '[' -> {
                    index++
                    start = true
                }
                ']' -> {
                    start = false
                    result.add(index, sb)
                }
                ',' -> {
                    if (start) result.add(index, sb)
                }
                ' ' -> {}
                else -> if (start) sb.append(it)
            }
        }
        return result
    }
    return ResultModel(
        potentialInfected = arrayListOf(),
        infected = arrayListOf(),
        recovered = arrayListOf(),
    )
}

private fun ResultModel.add(index: Int, sb: StringBuilder) {
    if (sb.last() == '.')
        sb.dropLast(0)
    val number = sb.toString().toFloat()
    when (index) {
        0 -> this.potentialInfected.add(number)
        1 -> this.infected.add(number)
        2 -> this.recovered.add(number)
    }
    sb.clear()
}

fun View.visible(state: Boolean?) {
    this.visibility =
        when (state) {
            null -> View.GONE
            true -> View.VISIBLE
            false -> View.INVISIBLE
        }
}

val <T> StateFlow<T>.isNotEmpty: Boolean
    get() = this.value != null

fun Editable?.textToInt(): Int? =
    if (this == null || this.isEmpty()) null
    else this.toString().toInt()

fun Editable?.textToFloat(): Float? =
    if (this == null || this.isEmpty()) null
    else this.checkDecimal().toFloat()

private fun Editable.checkDecimal(): String {
    val data = this.toString()
    if (data.first() == '.') return "0$data"
    return data
}

fun View.setOnClickDelayed(delayMillis: Long = 800, block: () -> Unit) {
    this.setOnClickListener {
        it.postDelayed({
            block.invoke()
        }, delayMillis)
    }
}