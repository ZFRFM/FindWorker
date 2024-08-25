package ru.faimizufarov.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ru.faimizufarov.authorization.R
import ru.faimizufarov.authorization.databinding.FragmentAuthSecondBinding

class AuthSecondFragment : Fragment() {
    private lateinit var binding: FragmentAuthSecondBinding

    private lateinit var authSharedViewModel: AuthSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authSharedViewModel = ViewModelProvider(requireActivity())[AuthSharedViewModel::class.java]
        binding = FragmentAuthSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authSharedViewModel.emailLiveData.observe(viewLifecycleOwner) { email ->
            binding.sentCodeOnTextView.text = getString(R.string.sent_code_on, email)
        }
    }

    companion object {
        fun newInstance() = AuthSecondFragment()
    }
}