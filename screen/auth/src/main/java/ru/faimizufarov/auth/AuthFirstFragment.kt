package ru.faimizufarov.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import ru.faimizufarov.authorization.R
import ru.faimizufarov.authorization.databinding.FragmentAuthFirstBinding

class AuthFirstFragment : Fragment() {
    private lateinit var binding: FragmentAuthFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthFirstBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkEmailEditTextDrawableVisibility()
    }

    private fun checkEmailEditTextDrawableVisibility() {
        binding.emailEditText.addTextChangedListener( object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val icon = if (s.isNullOrEmpty()) {
                    ContextCompat.getDrawable(
                        requireContext(),
                        ru.faimizufarov.core.R.drawable.action_responses
                    )
                } else {
                    null
                }
                binding.emailEditText.setCompoundDrawablesWithIntrinsicBounds(
                    icon, null, null, null
                )
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }
        )
    }

    companion object {
        fun newInstance() = AuthFirstFragment()
    }
}