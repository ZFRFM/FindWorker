package ru.faimizufarov.respond.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.faimizufarov.search.databinding.FragmentRespondBottomSheetBinding

class RespondBottomSheetFragment: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentRespondBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRespondBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = RespondBottomSheetFragment()
    }
}