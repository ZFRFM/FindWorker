package ru.faimizufarov.favourite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.core.adapters.VacancyAdapter
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.favourite.di.FavouriteComponentProvider
import ru.faimizufarov.favourites.R
import ru.faimizufarov.favourites.databinding.FragmentFavouritesBinding
import javax.inject.Inject

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding

    @Inject
    lateinit var favouriteViewModelFactory: FavouriteViewModelFactory
    private lateinit var favouriteViewModel: FavouriteViewModel

    private val vacancyAdapter = VacancyAdapter(onItemClick = ::updateVacancyFeed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as FavouriteComponentProvider)
            .provideFavouriteComponent()
            .injectFavouriteFragment(this)

        favouriteViewModel =
            ViewModelProvider(
                this,
                favouriteViewModelFactory,
            )[FavouriteViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.favouriteVacancyRecyclerView.adapter = vacancyAdapter

        favouriteViewModel.favouriteVacanciesLiveData.observe(viewLifecycleOwner) { favouriteVacancies ->
            observeViewModel(favouriteVacancies)
        }
    }

    private fun observeViewModel(favouriteVacancies: List<Vacancy>) {
        with(binding) {
            favouriteVacancyNumberTextView.text = resources.getQuantityString(
                ru.faimizufarov.core.R.plurals.only_vacancies_count,
                favouriteVacancies.size,
                favouriteVacancies.size
            )
            favouriteVacancyNumberTextView.visibility = View.VISIBLE
        }

        vacancyAdapter.submitList(favouriteVacancies)
    }

    private fun updateVacancyFeed(vacancy: Vacancy) {
        val vacancyIdBundle = bundleOf(
            FAVOURITE_VACANCY_ID to vacancy.id,
            NAV_TO_VACANCY_PAGE_FROM_FAVOURITES to true
        )
        setFragmentResult(NAV_TO_VACANCY_PAGE_FROM_FAVOURITES_RESULT, vacancyIdBundle)
    }

    companion object {
        fun newInstance() = FavouriteFragment()

        const val FAVOURITE_FRAGMENT = "FAVOURITE_FRAGMENT"

        const val FAVOURITE_VACANCY_ID = "FAVOURITE_VACANCY_ID"
        const val NAV_TO_VACANCY_PAGE_FROM_FAVOURITES = "NAV_TO_VACANCY_PAGE_FROM_FAVOURITES"
        const val NAV_TO_VACANCY_PAGE_FROM_FAVOURITES_RESULT = "NAV_TO_VACANCY_PAGE_FROM_FAVOURITES_RESULT"
    }
}