package ru.faimizufarov.favourite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.favourite.di.FavouriteComponentProvider
import ru.faimizufarov.favourites.databinding.FragmentFavouritesBinding
import javax.inject.Inject

class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouritesBinding

    @Inject
    lateinit var favouriteViewModelFactory: FavouriteViewModelFactory
    private lateinit var favouriteViewModel: FavouriteViewModel

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
        favouriteViewModel.favouriteVacanciesLiveData.observe(viewLifecycleOwner) { favouriteVacancies ->

        }
    }

    companion object {
        fun newInstance() = FavouriteFragment()
    }
}