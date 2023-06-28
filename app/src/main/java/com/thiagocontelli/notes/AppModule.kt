package com.thiagocontelli.notes

import android.content.Context
import androidx.room.Room
import com.thiagocontelli.notes.data.db.AppDatabase
import com.thiagocontelli.notes.data.db.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, "notes.db"
        ).build()
    }

    @Provides
    fun providesNoteDao(appDatabase: AppDatabase): NoteDao {
        return appDatabase.noteDao()
    }
}