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
    @ColumnInfo("looking_number") val lookingNumber: Int?,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("address") val address: Address,
    @ColumnInfo("company") val company: String,
    @ColumnInfo("experience") val experience: Experience,
    @ColumnInfo("published_date") val publishedDate: String,
    @ColumnInfo("is_favorite") val isFavorite: Boolean,
    @ColumnInfo("salary") val salary: Salary,
    @ColumnInfo("schedules") val schedules: List<String>,
    @ColumnInfo("applied_number") val appliedNumber: Int?,
    @ColumnInfo("description") val description: String?,
    @ColumnInfo("responsibilities") val responsibilities: String,
    @ColumnInfo("questions") val questions: List<String>
)
