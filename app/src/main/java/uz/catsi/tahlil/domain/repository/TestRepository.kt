package uz.catsi.tahlil.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.catsi.tahlil.data.model.common.ParamsModel
import uz.catsi.tahlil.data.model.common.ResultModel

interface TestRepository {
    val result: ResultModel?

    fun clearResult()
    fun doTest(isMobile: Boolean, data: ParamsModel): Flow<Result<Unit>>
}