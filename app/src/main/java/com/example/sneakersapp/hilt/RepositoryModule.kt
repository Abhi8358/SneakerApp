package com.example.sneakersapp.hilt

import android.content.Context
import com.example.sneakersapp.dao.CartDataAccessObject
import com.example.sneakersapp.repository.CartRepository
import com.example.sneakersapp.repository.CartRepositoryInterface
import com.example.sneakersapp.repository.HomeRepository
import com.example.sneakersapp.repository.HomeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {

    @Provides
    fun objectForHomeRepositoryInterface(@ApplicationContext context: Context) =
        HomeRepository(context) as HomeRepositoryInterface

    @Provides
    fun objectForCartRepositoryInterface(@Singleton cartDataAccessObject: CartDataAccessObject): CartRepositoryInterface {
        return CartRepository(cartDataAccessObject)
    }
}