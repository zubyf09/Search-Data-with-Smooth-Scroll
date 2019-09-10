package codenevisha.ir.mvvmwithdagger.di.module

import android.app.Application
import androidx.room.Room
import codenevisha.ir.mvvmwithdagger.data.db.AppDatabase
import codenevisha.ir.mvvmwithdagger.data.db.DoctorDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    fun provideArticleDao(appDatabase: AppDatabase):DoctorDao {
        return appDatabase.searchDao()
    }
}
