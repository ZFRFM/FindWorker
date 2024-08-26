package ru.faimizufarov.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import ru.faimizufarov.domain.models.Offer
import ru.faimizufarov.search.adapter.OfferAdapter
import ru.faimizufarov.search.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchViewModel.result.observe(viewLifecycleOwner) { result ->
            val offerAdapter = OfferAdapter(onItemClick = ::updateFeed)
            binding.offersRecyclerView.adapter = offerAdapter
            offerAdapter.submitList(result.offers)
        }
    }

    private fun updateFeed(offer: Offer) {
        Toast.makeText(requireContext(), "clicked ${offer.title}", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}