package com.example.sneakersapp.hilt

import com.example.sneakersapp.repo.FakeCartRepository
import com.example.sneakersapp.repo.FakeHomeRepository
import com.example.sneakersapp.repository.CartRepositoryInterface
import com.example.sneakersapp.repository.HomeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [RepositoryModule::class]
)
@Module
class FakeDbModule {

    @Provides
    fun objectForHomeRepositoryInterface() =
        FakeHomeRepository() as HomeRepositoryInterface

    @Provides
    fun objectForCartRepositoryInterface() =
        FakeCartRepository() as CartRepositoryInterface
}