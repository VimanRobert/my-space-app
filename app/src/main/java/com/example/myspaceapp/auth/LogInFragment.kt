package com.example.myspaceapp.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myspaceapp.R
import com.example.myspaceapp.databinding.LogInFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : Fragment() {
    private lateinit var binding: LogInFragmentBinding
    private lateinit var userHelper: UserHelper
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LogInFragmentBinding.inflate(layoutInflater, container, false)
        firebaseAuth = FirebaseAuth.getInstance()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userHelper = UserHelper(requireContext().applicationContext)
        binding.buttonLogin.setOnClickListener {
            userHelper.login(
                binding.emailText.text.toString(),
                binding.passwordText.text.toString()
            ) { isSuccessful ->
                if (isSuccessful) {
                    findNavController().navigate(R.id.action_logInFragment_to_homeFragment)
                } else {
                    Log.e("TAG", "credentials don't match")
                }
            }
        }
        binding.buttonAuthenticate.setOnClickListener {
            findNavController().navigate(R.id.action_logInFragment_to_authenticateFragment)
        }
    }
}
