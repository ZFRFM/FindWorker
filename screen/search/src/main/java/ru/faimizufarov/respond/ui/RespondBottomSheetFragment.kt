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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val questionText = arguments?.getString(QUESTION)
        if (questionText?.isNotEmpty() == true) {
            with(binding) {
                letterEditText.visibility = View.VISIBLE
                addLetterTextView.visibility = View.INVISIBLE
                letterEditText.setText(questionText)
            }
        }

        binding.addLetterTextView.setOnClickListener {
            binding.letterEditText.visibility = View.VISIBLE
            binding.addLetterTextView.visibility = View.INVISIBLE
        }

        binding.respondButton.setOnClickListener {
            dismiss()
        }
    }

    companion object {
        fun newInstance(questionText: String?): RespondBottomSheetFragment {
            val args = Bundle()
            args.putString(QUESTION, questionText)
            val respondBottomSheetFragment = RespondBottomSheetFragment()
            respondBottomSheetFragment.arguments = args
            return respondBottomSheetFragment
        }

        const val QUESTION = "QUESTION"
    }
}