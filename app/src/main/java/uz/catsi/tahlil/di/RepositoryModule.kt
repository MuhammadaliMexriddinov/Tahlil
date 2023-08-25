package uz.catsi.tahlil.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.catsi.tahlil.domain.repository.*

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun getTestRepository(impl : TestRepositoryImpl): TestRepository

    @Binds
    fun getGraphRepository(impl : GraphRepositoryImpl): GraphRepository
}