package ru.faimizufarov.headhunter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.faimizufarov.favourites.FavouritesFragment
import ru.faimizufarov.headhunter.databinding.ActivityMainBinding
import ru.faimizufarov.messages.MessagesFragment
import ru.faimizufarov.profile.ProfileFragment
import ru.faimizufarov.responses.ResponsesFragment
import ru.faimizufarov.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
            commit()
        }
    }
}