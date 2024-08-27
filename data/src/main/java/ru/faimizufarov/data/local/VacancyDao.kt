package ru.faimizufarov.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ru.faimizufarov.domain.models.Vacancy

@Dao
interface VacancyDao {
    @Query("SELECT * FROM  vacancy")
    suspend fun getAllVacancies(): List<VacancyEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVacancies(vacancies: List<VacancyEntity>)

    @Update
    suspend fun updateVacancy(vacancyEntity: VacancyEntity)

    @Query("SELECT COUNT(*) FROM vacancy")
    suspend fun checkVacanciesCount(): Int
}