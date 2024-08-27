package ru.faimizufarov.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.faimizufarov.domain.models.Address
import ru.faimizufarov.domain.models.Experience
import ru.faimizufarov.domain.models.Salary

@Entity(tableName = "vacancy")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true) val localId: Int = 0,
    @ColumnInfo("id") val id: String,
    @ColumnInfo("looking_number") val lookingNumber: Int? = null,
    @ColumnInfo("title") val title: String? = null,
    @ColumnInfo("address") val address: Address? = null,
    @ColumnInfo("company") val company: String? = null,
    @ColumnInfo("experience") val experience: Experience? = null,
    @ColumnInfo("published_date") val publishedDate: String? = null,
    @ColumnInfo("is_favorite") val isFavorite: Boolean? = null,
    @ColumnInfo("salary") val salary: Salary? = null,
    @ColumnInfo("schedules") val schedules: List<String>? = null,
    @ColumnInfo("applied_number") val appliedNumber: Int? = null,
    @ColumnInfo("description") val description: String? = null,
    @ColumnInfo("responsibilities") val responsibilities: String? = null,
    @ColumnInfo("questions") val questions: List<String>? = null
)
