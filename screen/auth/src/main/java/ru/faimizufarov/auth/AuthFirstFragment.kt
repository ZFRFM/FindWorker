package ru.faimizufarov.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.faimizufarov.authorization.R

class AuthFirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth_first, container, false)
    }

    companion object {
        fun newInstance() = AuthFirstFragment()
    }
}