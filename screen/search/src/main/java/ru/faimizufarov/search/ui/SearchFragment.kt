package ru.faimizufarov.search.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.search.adapter.OfferAdapter
import ru.faimizufarov.core.adapters.VacancyAdapter
import ru.faimizufarov.search.databinding.FragmentSearchBinding
import ru.faimizufarov.search.di.SearchComponentProvider
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory
    private lateinit var searchViewModel: SearchViewModel

    private val offerAdapter = OfferAdapter(onItemClick = ::updateOfferFeed)
    private val vacancyAdapter = VacancyAdapter(
        onItemClick = ::updateVacancyFeed,
        onHeartClick = ::updateVacancyHeart
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().applicationContext as SearchComponentProvider)
            .provideSearchComponent()
            .injectSearchFragment(this)

        searchViewModel =
            ViewModelProvider(
                this,
                searchViewModelFactory,
            )[SearchViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.offersRecyclerView.adapter = offerAdapter
        binding.vacanciesRecyclerView.adapter = vacancyAdapter
        observeViewModel()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun observeViewModel() {
        searchViewModel.result.observe(viewLifecycleOwner) { result ->
            offerAdapter.submitList(result.offers)
            vacancyAdapter.submitList(result.vacancies.take(3))

            binding.moreVacanciesButton.visibility = View.VISIBLE
            correctVacancyEnding(result)

            binding.moreVacanciesButton.setOnClickListener {
                vacancyAdapter.submitList(result.vacancies)
                with(binding) {
                    queryEditText.setCompoundDrawablesWithIntrinsicBounds(
                        resources.getDrawable(ru.faimizufarov.core.R.drawable.back_arrow_icon),
                        null,
                        null,
                        null
                    )

                    vacancyNumberTextView.visibility = View.VISIBLE
                    sortTextView.visibility = View.VISIBLE
                    sortImageView.visibility = View.VISIBLE
                    it.visibility = View.GONE

                    vacancyNumberTextView.text = resources.getQuantityString(
                        ru.faimizufarov.core.R.plurals.only_vacancies_count,
                        result.vacancies.size,
                        result.vacancies.size
                    )
                }
            }

            binding.queryEditText.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val drawableBounds = binding.queryEditText.compoundDrawables[0]?.bounds
                    val drawableWidth = drawableBounds?.width() ?: 0

                    if (event.x <= (binding.queryEditText.paddingLeft + drawableWidth)) {
                        onDrawableStartClick(result)
                        return@setOnTouchListener true
                    }
                }
                false
            }

            noticeBadgeCounter()
        }
    }

    private fun onDrawableStartClick(result: Result) {
        with(binding) {
            sortImageView.visibility = View.GONE
            sortTextView.visibility = View.GONE
            vacancyNumberTextView.visibility = View.GONE
            moreVacanciesButton.visibility = View.VISIBLE
            queryEditText.setCompoundDrawablesWithIntrinsicBounds(
                resources.getDrawable(ru.faimizufarov.core.R.drawable.action_search),
                null,
                null,
                null
            )
            vacancyAdapter.submitList(result.vacancies.take(3))
        }
    }

    private fun updateOfferFeed(offer: Offer) {
        val link = offer.link
        val queryIntent = Intent(Intent.ACTION_VIEW)
        queryIntent.data = Uri.parse(link)
        startActivity(queryIntent)
    }

    private fun updateVacancyFeed(vacancy: Vacancy) {
        val vacancyIdBundle = bundleOf(
            VACANCY_ID to vacancy.id,
            NAVIGATE_TO_VACANCY_PAGE_FRAGMENT to true
        )
        setFragmentResult(VACANCY_ID_AND_NAVIGATE_RESULT, vacancyIdBundle)
    }

    private fun updateVacancyHeart(vacancy: Vacancy) {
        val updatedVacancy = vacancy.copy(isFavorite = !vacancy.isFavorite)
        searchViewModel.updateFavouriteVacancy(updatedVacancy)

        val noticeBadgeCounter = bundleOf(NOTICE_MAIN_ACT_BADGE_COUNT to true)
        setFragmentResult(NOTICE_MAIN_ACT_BADGE_COUNT_RESULT, noticeBadgeCounter)
    }

    private fun correctVacancyEnding(result: Result) {
        val lastVacanciesNumber = result.vacancies.size - 3
        binding.moreVacanciesButton.text = resources.getQuantityString(
            ru.faimizufarov.core.R.plurals.vacancies_count,
            lastVacanciesNumber,
            lastVacanciesNumber
        )
    }

    private fun noticeBadgeCounter() {
        val isDataLoaded = bundleOf(LOADED_DATA to true)
        setFragmentResult(LOADED_DATA_RESULT, isDataLoaded)
    }

    companion object {
        fun newInstance() = SearchFragment()

        const val SEARCH_FRAGMENT = "SEARCH_FRAGMENT"

        const val NOTICE_MAIN_ACT_BADGE_COUNT = "NOTICE_MAIN_ACT_BADGE_COUNT"
        const val NOTICE_MAIN_ACT_BADGE_COUNT_RESULT = "NOTICE_MAIN_ACT_BADGE_COUNT_RESULT"

        const val LOADED_DATA = "LOADED_DATA"
        const val LOADED_DATA_RESULT = "LOADED_DATA_RESULT"

        const val NAVIGATE_TO_VACANCY_PAGE_FRAGMENT = "NAVIGATE_TO_VACANCY_PAGE_FRAGMENT"
        const val VACANCY_ID = "VACANCY_ID"
        const val VACANCY_ID_AND_NAVIGATE_RESULT = "VACANCY_ID_AND_NAVIGATE_RESULT"
    }
}