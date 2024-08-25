package ru.faimizufarov.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.faimizufarov.authorization.databinding.FragmentAuthSecondBinding

class AuthSecondFragment : Fragment() {
    private lateinit var binding: FragmentAuthSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthSecondBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() = AuthSecondFragment()
    }
}