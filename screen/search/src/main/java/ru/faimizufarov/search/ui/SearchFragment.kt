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
import ru.faimizufarov.search.adapter.OfferAdapter
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
        val offerAdapter = OfferAdapter(onItemClick = ::updateFeed)
        binding.offersRecyclerView.adapter = offerAdapter
        searchViewModel.result.observe(viewLifecycleOwner) { result ->
            offerAdapter.submitList(result.offers)
            Toast.makeText(requireContext(), "loaded $result", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateFeed(offer: Offer) {
        val link = offer.link
        val queryIntent = Intent(Intent.ACTION_VIEW)
        queryIntent.data = Uri.parse(link)
        startActivity(queryIntent)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}