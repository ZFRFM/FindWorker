package ru.faimizufarov.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [OfferEntity::class, VacancyEntity::class], version = 1)
@TypeConverters(LocalTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun offerDao(): OfferDao

    abstract fun vacancyDao(): VacancyDao

    companion object {
        @Volatile
        private var databaseInstance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return databaseInstance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database",
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { databaseInstance = it }
            }
        }
    }
}
