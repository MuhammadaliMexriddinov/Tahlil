package uz.catsi.tahlil.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.catsi.tahlil.data.model.common.ParamsModel
import uz.catsi.tahlil.data.model.common.ResultModel
import uz.catsi.tahlil.data.remote.api.TestApi
import uz.catsi.tahlil.utils.BASE_URL_MOBILE
import uz.catsi.tahlil.utils.BASE_URL_WIFI
import uz.catsi.tahlil.utils.convertToResult
import uz.catsi.tahlil.utils.toRequestMedia
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TestRepositoryImpl @Inject constructor(
    private val api: TestApi,
) : TestRepository {
    private var _result: ResultModel? = null
    override val result: ResultModel?
        get() = _result

    override fun doTest(isMobile: Boolean, data: ParamsModel) = flow {
        val response = api.test(
            if (isMobile) BASE_URL_MOBILE else BASE_URL_WIFI,
            data.infectionRate.toRequestMedia(),
            data.recoveryRate.toRequestMedia(),
            data.population.toRequestMedia(),
            data.initInfected.toRequestMedia(),
            data.initRecovered.toRequestMedia(),
            data.time.toRequestMedia()
        )
        val result: ResultModel? = response?.convertToResult()
        _result = result
        emit(Result.success(Unit))
    }
        .catch {
            emit(Result.failure(Exception(it.message)))
        }
        .flowOn(Dispatchers.IO)

    override fun clearResult() {
        _result = null
    }
}

