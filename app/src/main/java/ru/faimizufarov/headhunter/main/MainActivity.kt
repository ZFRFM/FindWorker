package ru.faimizufarov.headhunter.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.auth.AuthFirstFragment
import ru.faimizufarov.auth.AuthSecondFragment
import ru.faimizufarov.favourite.ui.FavouriteFragment
import ru.faimizufarov.headhunter.App
import ru.faimizufarov.headhunter.R
import ru.faimizufarov.headhunter.databinding.ActivityMainBinding
import ru.faimizufarov.messages.MessagesFragment
import ru.faimizufarov.profile.ProfileFragment
import ru.faimizufarov.respond.ui.RespondBottomSheetFragment
import ru.faimizufarov.responses.ResponsesFragment
import ru.faimizufarov.search.ui.SearchFragment
import ru.faimizufarov.vacancy_page.ui.VacancyPageFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavView.selectedItemId = R.id.action_search

        (applicationContext as App).appComponent.injectMainActivity(this)
        mainViewModel =
            ViewModelProvider(
                this,
                mainViewModelFactory,
            )[MainViewModel::class]

        binding.bottomNavView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> setCurrentFragment(SearchFragment.newInstance())
                R.id.action_favourites -> setCurrentFragment(FavouriteFragment.newInstance())
                R.id.action_responses -> setCurrentFragment(ResponsesFragment.newInstance())
                R.id.action_messages -> setCurrentFragment(MessagesFragment.newInstance())
                R.id.action_profile -> setCurrentFragment(ProfileFragment.newInstance())
            }
            true
        }

        observeViewModel()
        listenerForLoadedData()
        listenerForNavigationToAuthSecondFragment()
        listenerForNavigationToSearchFragment()
        listenerForNavigationToVacancyPageFragment()
        listenerForNavigationToBottomSheetFragmentWithRespond()
        listenerForNavigationToBottomSheetFragmentWithQuestion()
        listenerForNavigationToFavouriteFragment()
    }

    private fun observeViewModel() {
        mainViewModel.isBottomNavViewEnabled.observe(this) { isEnabled ->
            for (i in 0 until binding.bottomNavView.menu.size()) {
                binding.bottomNavView.menu.getItem(i).isEnabled = isEnabled
            }
        }
    }

    private fun updateBadgeCount(favouriteVacancies: Int) {
        val badge = binding.bottomNavView.getOrCreateBadge(R.id.action_favourites)
        val isFavouriteVacancy = favouriteVacancies > 0
        if (isFavouriteVacancy) {
            badge.number = favouriteVacancies
        }
        badge.isVisible = isFavouriteVacancy
    }

    private fun listenerForLoadedData() {
        supportFragmentManager.setFragmentResultListener(
            SearchFragment.LOADED_DATA_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(SearchFragment.LOADED_DATA)
            if (result) {
                mainViewModel.badgeCounterValue.observe(this) { favouriteVacancies ->
                    updateBadgeCount(favouriteVacancies)
                }
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
            mainViewModel.setBottomNavViewAvailability(true)
            val result = bundle.getBoolean(AuthSecondFragment.NAVIGATE_TO_SEARCH_FRAGMENT)
            if (result) {
                setCurrentFragment(SearchFragment.newInstance())
            }
        }

        supportFragmentManager.setFragmentResultListener(
            VacancyPageFragment.NAVIGATE_TO_SEARCH_FRAGMENT_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(VacancyPageFragment.NAVIGATE_TO_SEARCH_FRAGMENT)
            if (result) {
                setCurrentFragment(SearchFragment.newInstance())
            }
        }
    }

    private fun listenerForNavigationToVacancyPageFragment() {
        supportFragmentManager.setFragmentResultListener(
            SearchFragment.VACANCY_ID_AND_NAVIGATE_RESULT, this
        ) { _, bundle ->
            val vacancyId = bundle.getString(SearchFragment.VACANCY_ID)
                ?: error("vacancyId was not accepted")
            val result = bundle.getBoolean(SearchFragment.NAVIGATE_TO_VACANCY_PAGE_FRAGMENT)
            if (result) {
                setCurrentFragment(VacancyPageFragment.newInstance(
                    vacancyId,
                    SearchFragment.SEARCH_FRAGMENT
                ))
            }
        }

        supportFragmentManager.setFragmentResultListener(
            FavouriteFragment.NAV_TO_VACANCY_PAGE_FROM_FAVOURITES_RESULT, this
        ) { _, bundle ->
            val vacancyId = bundle.getString(FavouriteFragment.FAVOURITE_VACANCY_ID)
                ?: error("vacancyId was not accepted")
            val result = bundle.getBoolean(FavouriteFragment.NAV_TO_VACANCY_PAGE_FROM_FAVOURITES)
            if (result) {
                setCurrentFragment(VacancyPageFragment.newInstance(
                    vacancyId,
                    FavouriteFragment.FAVOURITE_FRAGMENT
                ))
            }
        }
    }

    private fun listenerForNavigationToBottomSheetFragmentWithRespond() {
        supportFragmentManager.setFragmentResultListener(
            VacancyPageFragment.NAVIGATE_TO_RESPOND_BOTTOM_SHEET_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(VacancyPageFragment.NAVIGATE_TO_RESPOND_BOTTOM_SHEET)
            if (result) {
                val bottomSheet = RespondBottomSheetFragment.newInstance(null)
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)
            }
        }
    }

    private fun listenerForNavigationToBottomSheetFragmentWithQuestion() {
        supportFragmentManager.setFragmentResultListener(
            VacancyPageFragment.NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(
                VacancyPageFragment.NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION
            )
            val questionText = bundle.getString(VacancyPageFragment.QUESTION_TEXT)
            if (result) {
                val bottomSheet = RespondBottomSheetFragment.newInstance(questionText)
                bottomSheet.show(supportFragmentManager, bottomSheet.tag)
            }
        }
    }

    private fun listenerForNavigationToFavouriteFragment() {
        supportFragmentManager.setFragmentResultListener(
            VacancyPageFragment.NAVIGATE_TO_FAVOURITE_FRAGMENT_RESULT, this
        ) { _, bundle ->
            val result = bundle.getBoolean(VacancyPageFragment.NAVIGATE_TO_FAVOURITE_FRAGMENT)
            if (result) {
                setCurrentFragment(FavouriteFragment.newInstance())
            }
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainerView, fragment)
        }.commit()
    }
}