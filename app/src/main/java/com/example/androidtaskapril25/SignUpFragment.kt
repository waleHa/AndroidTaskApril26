package com.example.androidtaskapril25

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.commit
import com.example.androidtaskapril25.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    private var emailValidate = false
    private var passwordValidate = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonTopSignIn.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                replace(R.id.fragment_container, SignInFragment())
                addToBackStack(null)

            }
        }
        binding.buttonToSubmit.setOnClickListener {
            Log.i("Main Tag", "buttonToSubmit Clicked")

            validation()
        }

    }

    private fun validation() {
        val submittedEmail: String = binding.textInputEditTextUserName.text.toString()
        val submittedPassword: String = binding.textInputEditTextPassword.text.toString()
        val confirmedPassword: String = binding.textInputEditTextConfirm.text.toString()

        emailValidate =
            submittedEmail.contains('@')
                    && submittedEmail.substringAfter('@').contains('.')

        passwordValidate = validatePassword((submittedPassword))

        if (!emailValidate) {

            binding.textViewErrorMessage.text = getString(R.string.text_view_error_message_email)
            Log.i("Main Tag", "emailValidate ${binding.textViewErrorMessage.text}")
        } else if (!passwordValidate) {
            binding.textViewErrorMessage.text = getString(R.string.text_view_error_message_passwrod)
        } else if(confirmedPassword != submittedPassword){
            binding.textViewErrorMessage.text = getString(R.string.text_view_error_message_passwrod_Confirmation)
        }
        else {
            binding.textViewErrorMessage.text = getString(R.string.signed_up_successfully)
        }
    }

    fun validatePassword(pass: String): Boolean {
        val myPassword = pass.trim()
        var validateCapital = false
        var validateSmallLetter = false
        var specialCar = false

        if (myPassword.length < 8) {
            return false
        }
        pass.forEach {
            if (it.isLetter() && it.isLowerCase()) {
                validateSmallLetter = true
            } else if (it.isLetter() && it.isUpperCase()) {
                validateCapital = true
            } else if (!it.isLetterOrDigit() && !it.isWhitespace()) {
                specialCar = true
            }
        }
        return validateCapital && validateSmallLetter && specialCar
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Clean up the binding when the view is destroyed
    }
}


