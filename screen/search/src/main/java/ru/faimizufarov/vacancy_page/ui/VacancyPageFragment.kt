package ru.faimizufarov.vacancy_page.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.domain.models.Question
import ru.faimizufarov.domain.models.Vacancy
import ru.faimizufarov.search.R
import ru.faimizufarov.search.databinding.FragmentVacancyPageBinding
import ru.faimizufarov.vacancy_page.adapter.QuestionAdapter
import ru.faimizufarov.vacancy_page.di.VacancyPageComponentProvider
import javax.inject.Inject

class VacancyPageFragment(private val id: String) : Fragment() {
    private lateinit var binding: FragmentVacancyPageBinding

    @Inject
    lateinit var vacancyPageViewModelFactory: VacancyPageViewModelFactory
    private lateinit var vacancyPageViewModel: VacancyPageViewModel

    private val questionAdapter = QuestionAdapter(onItemClick = ::updateQuestionFeed)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as VacancyPageComponentProvider)
            .provideVacancyPageComponent()
            .injectVacancyPageFragment(this)

        vacancyPageViewModel =
            ViewModelProvider(
                this,
                vacancyPageViewModelFactory,
            )[VacancyPageViewModel::class]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVacancyPageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vacancyPageConstraint.visibility = View.GONE
        vacancyPageViewModel.filterResult(id)
        vacancyPageViewModel.vacancy.observe(viewLifecycleOwner) { vacancy ->
            observeViewModel(vacancy)
        }

        binding.respondButton.setOnClickListener {
            val navigateToRespondBottomSheetFragment =
                bundleOf(NAVIGATE_TO_RESPOND_BOTTOM_SHEET to true)
            setFragmentResult(
                NAVIGATE_TO_RESPOND_BOTTOM_SHEET_RESULT,
                navigateToRespondBottomSheetFragment
            )
        }

        binding.arrowBackImageView.setOnClickListener {
            val navigateToSearchFragment = bundleOf(NAVIGATE_TO_SEARCH_FRAGMENT to true)
            setFragmentResult(NAVIGATE_TO_SEARCH_FRAGMENT_RESULT, navigateToSearchFragment)
        }
    }

    private fun observeViewModel(vacancy: Vacancy) {
        binding.vacancyPageConstraint.visibility = View.VISIBLE
        binding.vacancyTitleTextView.text = vacancy.title

        binding.salaryTextView.text = vacancy.salary?.full

        binding.experienceTextView.text = vacancy.experience?.text


        val firstSchedule = vacancy.schedules?.first()?.replaceFirstChar { it.uppercase() }
        val finalSchedules: MutableList<String> =
            vacancy.schedules?.toMutableList()?: mutableListOf()

        finalSchedules[0] = firstSchedule?: ""
        binding.schedulesTextView.text = finalSchedules.joinToString(separator = ", ")


        if (vacancy.appliedNumber != null) {
            binding.respondedPersonCountTextView.text = resources.getQuantityString(
                ru.faimizufarov.core.R.plurals.responded_people_count,
                vacancy.appliedNumber?: 0,
                vacancy.appliedNumber?: 0
            )
        } else {
            with(binding) {
                respondedBackground.visibility = View.GONE
                respondedPersonCountTextView.visibility = View.GONE
                respondedPersonIcon.visibility = View.GONE
            }
        }

        if (vacancy.lookingNumber != null) {
            binding.lookingTextView.text = resources.getQuantityString(
                ru.faimizufarov.core.R.plurals.looking_number_count,
                vacancy.lookingNumber?: 0,
                vacancy.lookingNumber?: 0
            )
        } else {
            with(binding) {
                lookingBackground.visibility = View.GONE
                lookingTextView.visibility = View.GONE
                lookingPersonIcon.visibility = View.GONE
            }
        }

        binding.companyTextView.text = vacancy.company

        binding.addressTextView.text = getString(
            ru.faimizufarov.core.R.string.address,
            vacancy.address?.town,
            vacancy.address?.street,
            vacancy.address?.house
        )

        if (vacancy.description != null) {
            binding.descriptionTextView.text = vacancy.description
        } else {
            binding.descriptionTextView.visibility = View.GONE
        }

        if (vacancy.responsibilities != null) {
            binding.responsibilitiesTextView.text = vacancy.responsibilities
        } else {
            binding.responsibilitiesTextView.visibility = View.GONE
        }

        binding.questionRecyclerView.adapter = questionAdapter
        val questions = vacancy.questions?.map { questionText ->
            Question(
                questionText = questionText
            )
        }
        questionAdapter.submitList(questions)

        with(binding) {
            if (vacancy.isFavorite == true) {
                favouriteImageView.setImageResource(
                    ru.faimizufarov.core.R.drawable.action_full_heart
                )
                favouriteImageView.setOnClickListener {
                    favouriteImageView.setImageResource(
                        ru.faimizufarov.core.R.drawable.action_favourites
                    )
                }
            } else {
                favouriteImageView.setImageResource(
                    ru.faimizufarov.core.R.drawable.action_favourites
                )
                favouriteImageView.setOnClickListener {
                    favouriteImageView.setImageResource(
                        ru.faimizufarov.core.R.drawable.action_full_heart
                    )
                }
            }
        }
    }

    private fun updateQuestionFeed(question: Question) {
        val navigateToRespondBottomSheetWithQuestion = bundleOf(
            NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION to true
        )
        navigateToRespondBottomSheetWithQuestion.putString(
            QUESTION_TEXT,
            question.questionText
        )
        setFragmentResult(
            NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION_RESULT,
            navigateToRespondBottomSheetWithQuestion
        )
    }

    companion object {
        fun newInstance(id: String) = VacancyPageFragment(id)

        const val QUESTION_TEXT = "QUESTION_TEXT"
        const val NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION = "NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION"
        const val NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION_RESULT = "NAVIGATE_TO_RESPOND_BOTTOM_SHEET_WITH_QUESTION_RESULT"

        const val NAVIGATE_TO_RESPOND_BOTTOM_SHEET = "NAVIGATE_TO_RESPOND_BOTTOM_SHEET"
        const val NAVIGATE_TO_RESPOND_BOTTOM_SHEET_RESULT = "NAVIGATE_TO_RESPOND_BOTTOM_SHEET_RESULT"

        const val NAVIGATE_TO_SEARCH_FRAGMENT = "NAVIGATE_TO_SEARCH_FRAGMENT_FROM_VACANCY_PAGE"
        const val NAVIGATE_TO_SEARCH_FRAGMENT_RESULT = "NAVIGATE_TO_SEARCH_FRAGMENT_RESULT_FROM_VACANCY_PAGE"
    }
}