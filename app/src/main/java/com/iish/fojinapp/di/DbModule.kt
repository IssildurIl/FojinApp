package com.iish.fojinapp.di

import android.content.Context
import androidx.room.Room
import com.iish.fojinapp.App
import com.iish.fojinapp.repository.cache.abstraction.ReviewDaoService
import com.iish.fojinapp.repository.cache.implementation.ReviewDaoServiceImpl
import com.iish.fojinapp.repository.cache.mapper.CacheMapper
import com.iish.fojinapp.repository.database.FojinDao
import com.iish.fojinapp.repository.database.FojinDatabase
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
    fun provideReviewDb(@ApplicationContext appContext: Context): FojinDatabase {
        return Room
            .databaseBuilder(appContext, FojinDatabase::class.java, FojinDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFojinDAO(fojinDatabase: FojinDatabase): FojinDao {
        return fojinDatabase.fojinDao()
    }

    @Provides
    @Singleton
    fun provideReviewDaoService(
        fojinDao: FojinDao,
        reviewsMapper: CacheMapper,
    ): ReviewDaoService {
        return ReviewDaoServiceImpl(fojinDao, reviewsMapper)
    }


}