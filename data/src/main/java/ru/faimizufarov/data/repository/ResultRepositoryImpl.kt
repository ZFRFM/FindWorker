package ru.faimizufarov.data.repository

import android.content.Context
import ru.faimizufarov.data.local.AppDatabase
import ru.faimizufarov.data.local.OfferEntity
import ru.faimizufarov.data.local.VacancyEntity
import ru.faimizufarov.network.AppApi
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy

class ResultRepositoryImpl(
    context: Context
): ResultRepository {
    private val database = AppDatabase.getDatabase(context)

    override suspend fun requestResult() = getResultFromApi()

    private suspend fun getResultFromApi() =
        if (isResultCached()) {
            loadResultFromDatabase()
        } else {
            loadResultFromNetwork()
        }

    private suspend fun isResultCached() =
        database.offerDao().checkOffersCount() != 0 &&
        database.vacancyDao().checkVacanciesCount() != 0

    private suspend fun loadResultFromNetwork(): Result {
        val serverResult = AppApi.retrofitService.getResult()
        database.offerDao().insertOffers(serverResult.offers.map { it.toOfferEntity() })
        database.vacancyDao().insertVacancies(serverResult.vacancies.map { it.toVacancyEntity() })
        return serverResult
    }

    private suspend fun loadResultFromDatabase(): Result {
        val offers = database.offerDao().getAllOffers().map { it.toOffer() }
        val vacancies = database.vacancyDao().getAllVacancies().map { it.toVacancy() }
        val databaseResult = Result(offers, vacancies)
        return databaseResult
    }

    override suspend fun requestVacancy(id: String): Vacancy {
        val result = requestResult()
        val vacancy = result.vacancies.first { vacancy ->
            vacancy.id == id
        }
        return vacancy
    }

    override suspend fun requestFavouriteVacancies(): List<Vacancy> {
        val result = requestResult()
        val favouriteVacanciesList = result.vacancies.filter { vacancy ->
            vacancy.isFavorite
        }
        return favouriteVacanciesList
    }

    override suspend fun setBadgeCounterValue(): Int {
        return requestFavouriteVacancies().size
    }

    override suspend fun updateVacancy(vacancy: Vacancy) {
        database.vacancyDao().updateVacancy(vacancy.toVacancyEntity())
    }


    private fun Offer.toOfferEntity() = OfferEntity(
        localId?: 0, id, title, button, link
    )

    private fun OfferEntity.toOffer() = Offer(
        localId, id, title, button, link
    )

    private fun Vacancy.toVacancyEntity() = VacancyEntity(
        id, lookingNumber, title, address, company, experience,
        publishedDate, isFavorite, salary, schedules, appliedNumber,
        description, responsibilities, questions
    )

    private fun VacancyEntity.toVacancy() = Vacancy(
        id, lookingNumber, title, address, company, experience,
        publishedDate, isFavorite, salary, schedules, appliedNumber,
        description, responsibilities, questions
    )
}