package com.example.sneakersapp.hilt

import android.content.Context
import androidx.room.Room
import com.example.sneakersapp.dao.CartDataBase
import com.example.sneakersapp.repository.HomeRepository
import com.example.sneakersapp.repository.HomeRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    @Singleton
    fun provide(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, CartDataBase::class.java, "sneakerCart"
    )
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideDao(db: CartDataBase) = db.getCartDao()

    @Provides
    fun objectForHomeRepositoryInterface(@ApplicationContext context: Context) = HomeRepository(context) as HomeRepositoryInterface
}