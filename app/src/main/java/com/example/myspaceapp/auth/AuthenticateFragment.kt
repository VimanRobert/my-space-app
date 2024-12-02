package com.example.myspaceapp.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.myspaceapp.R
import com.example.myspaceapp.databinding.AuthenticateFragmentBinding
import com.example.myspaceapp.databinding.SettingsFragmentBinding

class AuthenticateFragment : Fragment() {
    private lateinit var binding: AuthenticateFragmentBinding
    private lateinit var userHelper: UserHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthenticateFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userHelper = UserHelper(requireContext().applicationContext)
        binding.apply {
            button.setOnClickListener {
                if (userHelper.confirmPassword(
                        passwordConfirmationText.text.toString(),
                        passwordText.text.toString()
                    )
                ) {
                    userHelper.createAccount(
                        emailText.text.toString(),
                        passwordText.text.toString()
                    )
                    Log.e("TAG", "$emailText, $passwordText")
                    findNavController().navigateUp()
                } else {
                    Log.e("TAG", "Unable to create account")
                    Log.e("TAG", "${emailText.text}, ${passwordText.text}, ${passwordConfirmationText.text}")
                }
            }
        }
    }
}
