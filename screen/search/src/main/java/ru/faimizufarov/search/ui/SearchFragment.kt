package ru.faimizufarov.search.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.domain.models.Result
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.domain.repository.ResultRepository
import ru.faimizufarov.search.R
import ru.faimizufarov.search.adapter.OfferAdapter
import ru.faimizufarov.search.adapter.VacancyAdapter
import ru.faimizufarov.search.databinding.FragmentSearchBinding
import ru.faimizufarov.search.di.SearchComponentProvider
import javax.inject.Inject

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory
    private lateinit var searchViewModel: SearchViewModel

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

        val offerAdapter = OfferAdapter(onItemClick = ::updateOfferFeed)
        val vacancyAdapter = VacancyAdapter(onItemClick = ::updateVacancyFeed)

        binding.offersRecyclerView.adapter = offerAdapter
        binding.vacanciesRecyclerView.adapter = vacancyAdapter

        searchViewModel.result.observe(viewLifecycleOwner) { result ->
            offerAdapter.submitList(result.offers)
            vacancyAdapter.submitList(result.vacancies.take(3))

            binding.moreVacanciesButton.visibility = View.VISIBLE
            correctVacancyEnding(result)
        }
    }

    private fun updateOfferFeed(offer: Offer) {
        val link = offer.link
        val queryIntent = Intent(Intent.ACTION_VIEW)
        queryIntent.data = Uri.parse(link)
        startActivity(queryIntent)
    }

    private fun updateVacancyFeed(vacancy: Vacancy) {
        Toast.makeText(requireContext(), "clicked ${vacancy.title}", Toast.LENGTH_SHORT).show()
    }

    private fun correctVacancyEnding(result: Result) {
        val lastVacanciesNumber = result.vacancies.size
        binding.moreVacanciesButton.text = resources.getQuantityString(
            ru.faimizufarov.core.R.plurals.vacancies_count,
            lastVacanciesNumber,
            lastVacanciesNumber
        )
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}