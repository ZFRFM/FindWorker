package ru.faimizufarov.vacancy_page.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.search.databinding.FragmentVacancyPageBinding
import ru.faimizufarov.vacancy_page.di.VacancyPageComponentProvider
import javax.inject.Inject

class VacancyPageFragment(private val id: String) : Fragment() {
    private lateinit var binding: FragmentVacancyPageBinding

    @Inject
    lateinit var vacancyPageViewModelFactory: VacancyPageViewModelFactory
    private lateinit var vacancyPageViewModel: VacancyPageViewModel

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
        vacancyPageViewModel.filterResult(id)
        vacancyPageViewModel.vacancy.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.title.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(id: String) = VacancyPageFragment(id)
    }
}