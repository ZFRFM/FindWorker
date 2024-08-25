package ru.faimizufarov.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.faimizufarov.authorization.R
import ru.faimizufarov.authorization.databinding.FragmentAuthSecondBinding

class AuthSecondFragment : Fragment() {
    private lateinit var binding: FragmentAuthSecondBinding

    private val disposables = CompositeDisposable()
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
        observeEmailLiveData()
        observePasswordLiveData()
        switchEditTextToNext()

        binding.confirmButton.setOnClickListener {
            clickConfirmButton()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun clickConfirmButton() {
        val navigateToSearchFragment =
            bundleOf(NAVIGATE_TO_SEARCH_FRAGMENT to true)
        parentFragmentManager.setFragmentResult(
            NAVIGATE_TO_SEARCH_FRAGMENT_RESULT,
            navigateToSearchFragment
        )
    }

    private fun observePasswordLiveData() {
        with(binding) {
            firstSymbolEditText.addCustomTextChangedListener()
            secondSymbolEditText.addCustomTextChangedListener()
            thirdSymbolEditText.addCustomTextChangedListener()
            fourthSymbolEditText.addCustomTextChangedListener()
        }

        authSharedViewModel.isPasswordEnabledLiveData.observe(viewLifecycleOwner) { isEnabled ->
            binding.confirmButton.isEnabled = isEnabled

            if (isEnabled) {
                binding.confirmButton.setBackgroundDrawable(
                    resources.getDrawable(R.drawable.button_enabled_background)
                )
            } else {
                binding.confirmButton.setBackgroundDrawable(
                    resources.getDrawable(R.drawable.button_not_enabled_background)
                )
            }
        }
    }

    private fun EditText.addCustomTextChangedListener() {

        addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(inputText: Editable?) {
                val isEnabled = with(binding) {
                    firstSymbolEditText.text.isNotEmpty() &&
                    secondSymbolEditText.text.isNotEmpty() &&
                    thirdSymbolEditText.text.isNotEmpty() &&
                    fourthSymbolEditText.text.isNotEmpty()
                }

                authSharedViewModel.setPasswordAvailability(isEnabled)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
    }

    private fun switchEditTextToNext() {
        setupEditText(binding.firstSymbolEditText, binding.secondSymbolEditText)
        setupEditText(binding.secondSymbolEditText, binding.thirdSymbolEditText)
        setupEditText(binding.thirdSymbolEditText, binding.fourthSymbolEditText)
        setupEditText(binding.fourthSymbolEditText, null)
    }

    private fun setupEditText(currentEditText: EditText, nextEditText: EditText?) {
        currentEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(inputSymbol: Editable?) {
                if (inputSymbol?.length == 1) {
                    nextEditText?.requestFocus()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun observeEmailLiveData() {
        authSharedViewModel.emailLiveData.observe(viewLifecycleOwner) { email ->
            binding.sentCodeOnTextView.text = getString(R.string.sent_code_on, email)
        }
    }

    companion object {
        fun newInstance() = AuthSecondFragment()

        const val NAVIGATE_TO_SEARCH_FRAGMENT = "NAVIGATE_TO_SEARCH_FRAGMENT"
        const val NAVIGATE_TO_SEARCH_FRAGMENT_RESULT = "NAVIGATE_TO_SEARCH_FRAGMENT_RESULT"

    }
}