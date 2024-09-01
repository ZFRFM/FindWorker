package ru.faimizufarov.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OfferDao {
    @Query("SELECT * FROM  offer")
    suspend fun getAllOffers(): List<OfferEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOffers(offers: List<OfferEntity>)

    @Query("SELECT COUNT(*) FROM offer")
    suspend fun checkOffersCount(): Int
}