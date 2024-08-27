package ru.faimizufarov.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.faimizufarov.domain.models.Button

@Entity(tableName = "offer")
data class OfferEntity(
    @PrimaryKey(autoGenerate = true) val localId: Int = 0,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("button") val button: Button? = null,
    @ColumnInfo("link") val link: String? = null
)