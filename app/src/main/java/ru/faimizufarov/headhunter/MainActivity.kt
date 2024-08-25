package ru.faimizufarov.headhunter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.faimizufarov.auth.AuthFirstFragment
import ru.faimizufarov.auth.AuthSecondFragment
import ru.faimizufarov.favourites.FavouritesFragment
import ru.faimizufarov.headhunter.databinding.ActivityMainBinding
import ru.faimizufarov.messages.MessagesFragment
import ru.faimizufarov.profile.ProfileFragment
import ru.faimizufarov.responses.ResponsesFragment
import ru.faimizufarov.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.selectedItemId = R.id.action_search

        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> setCurrentFragment(SearchFragment.newInstance())
                R.id.action_favourites -> setCurrentFragment(FavouritesFragment.newInstance())
                R.id.action_responses -> setCurrentFragment(ResponsesFragment.newInstance())
                R.id.action_messages -> setCurrentFragment(MessagesFragment.newInstance())
                R.id.action_profile -> setCurrentFragment(ProfileFragment.newInstance())
            }
            true
        }

        observeBottomNavViewAvailability()
        listenerForNavigationToAuthSecondFragment()
        listenerForNavigationToSearchFragment()
    }

    private fun observeBottomNavViewAvailability() {
        mainViewModel.isBottomNavViewEnabled.observe(this) { isEnabled ->
            for (i in 0 until binding.bottomNavView.menu.size()) {
                binding.bottomNavView.menu.getItem(i).isEnabled = isEnabled
            }
        }
    }

    private fun listenerForNavigationToAuthSecondFragment() {
        supportFragmentManager.setFragmentResultListener(
            AuthFirstFragment.NAVIGATE_TO_AUTH_SECOND_FRAGMENT_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(AuthFirstFragment.NAVIGATE_TO_AUTH_SECOND_FRAGMENT)
            if (result) {
                setCurrentFragment(AuthSecondFragment.newInstance())
            }
        }
    }

    private fun listenerForNavigationToSearchFragment() {
        supportFragmentManager.setFragmentResultListener(
            AuthSecondFragment.NAVIGATE_TO_SEARCH_FRAGMENT_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(AuthSecondFragment.NAVIGATE_TO_SEARCH_FRAGMENT)
            if (result) {
                setCurrentFragment(SearchFragment.newInstance())
            }
            mainViewModel.setBottomNavViewAvailability(true)
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
        }.commit()
    }
}