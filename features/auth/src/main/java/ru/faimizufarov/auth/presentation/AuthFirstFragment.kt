package ru.faimizufarov.auth.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.disposables.CompositeDisposable
import ru.faimizufarov.auth.R
import ru.faimizufarov.auth.databinding.FragmentAuthFirstBinding

class AuthFirstFragment : Fragment() {
    private lateinit var binding: FragmentAuthFirstBinding

    private val disposables = CompositeDisposable()
    private lateinit var authSharedViewModel: AuthSharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        authSharedViewModel = ViewModelProvider(requireActivity())[AuthSharedViewModel::class.java]
        binding = FragmentAuthFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkEmailEditTextDrawableVisibility()
        setAuthData()
        observeViewModel()
        clickOkOnKeyboard()

        binding.continueButton.setOnClickListener {
            clickContinueButton()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }

    private fun observeViewModel() {
        with(authSharedViewModel) {
            isAuthEnabledLiveData.observe(viewLifecycleOwner) { isEnabled ->
                binding.continueButton.isEnabled = isEnabled
                if (isEnabled) {
                    binding.continueButton.setBackgroundDrawable(
                        resources.getDrawable(
                            ru.faimizufarov.core.R.drawable.button_enabled_background
                        )
                    )
                } else {
                    binding.continueButton.setBackgroundDrawable(
                        resources.getDrawable(R.drawable.button_not_enabled_background)
                    )
                }
            }

            emailLiveData.observe(viewLifecycleOwner) { emailText ->
                if (binding.emailEditText.text.toString() != emailText) {
                    binding.emailEditText.setText(emailText)
                }
            }
        }
    }

    private fun clickContinueButton() {
        if (binding.emailEditText.text.endsWith("@mail.ru") ||
            binding.emailEditText.text.endsWith("@gmail.com")
        ) {
            val navigateToAuthSecondFragment =
                bundleOf(NAVIGATE_TO_AUTH_SECOND_FRAGMENT to true)
            parentFragmentManager.setFragmentResult(
                NAVIGATE_TO_AUTH_SECOND_FRAGMENT_RESULT,
                navigateToAuthSecondFragment
            )
        } else {
            binding.emailInputLayout.error = getString(R.string.incorrect_email)
            binding.emailInputLayout.isErrorEnabled = true
            binding.emailEditText.background =
                resources.getDrawable(R.drawable.login_edit_text_stroke_background)
            binding.emailEditText.setPadding(12,32,12,32)
        }
    }

    private fun clickOkOnKeyboard() {
        binding.emailEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.keyCode == KeyEvent.KEYCODE_ENTER)) {
                clickContinueButton()
                true
            } else {
                false
            }
        }
    }

    private fun setAuthData() {
        binding.emailEditText
            .textChanges()
            .subscribe {
                authSharedViewModel.setEmailText(it.toString())
            }.let { disposables.add(it) }
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun checkEmailEditTextDrawableVisibility() {
        binding.emailEditText.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(inputText: Editable?) {
                binding.emailInputLayout.hint = if (inputText.isNullOrEmpty()) {
                    getString(R.string.email)
                } else {
                    null
                }

                binding.emailInputLayout.error = null
                binding.emailInputLayout.isErrorEnabled = false
                binding.emailEditText.background =
                    resources.getDrawable(ru.faimizufarov.core.R.drawable.grey_two_background)
                binding.emailEditText.setPadding(12,32,12,32)

                val clearIcon = if (inputText.isNullOrEmpty()) {
                    null
                } else {
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.action_big_close
                    )
                }
                val emailIcon = if (inputText.isNullOrEmpty()) {
                    ContextCompat.getDrawable(
                        requireContext(),
                        ru.faimizufarov.core.R.drawable.action_responses
                    )
                } else {
                    null
                }
                binding.emailEditText.setCompoundDrawablesWithIntrinsicBounds(
                    emailIcon, null, clearIcon, null
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )

        binding.emailEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                val drawableEnd = binding.emailEditText.compoundDrawables[2]
                if (drawableEnd != null && event.rawX >=
                    (binding.emailEditText.right - drawableEnd.bounds.width())) {
                    binding.emailEditText.text.clear()
                    return@setOnTouchListener true
                }
            }
            false
        }
    }

    companion object {
        const val NAVIGATE_TO_AUTH_SECOND_FRAGMENT = "NAVIGATE_TO_AUTH_SECOND_FRAGMENT"
        const val NAVIGATE_TO_AUTH_SECOND_FRAGMENT_RESULT = "NAVIGATE_TO_AUTH_SECOND_FRAGMENT_RESULT"
    }
}