package com.example.androidhilttaskdemo.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.androidhilttaskdemo.R
import com.example.androidhilttaskdemo.data.localdb.UserEntity
import com.example.androidhilttaskdemo.data.shared_pref.SharedPref
import com.example.androidhilttaskdemo.databinding.FragmentSignupBinding
import com.example.androidhilttaskdemo.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class SignupFragment : Fragment() {

    @Inject
    lateinit var userViewModel: UserViewModel

    @Inject
    lateinit var preferences: SharedPref

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bind = FragmentSignupBinding.inflate(inflater)

        bind.signupButton.setOnClickListener {

            val enteredUsername = bind.signupUsernameTv.text.toString().trim();
            val enteredPassword = bind.signupPasswordTv.text.toString().trim();

            if (enteredUsername.isNotEmpty() && enteredPassword.isNotEmpty()) {

                if (enteredUsername.length >= 5 && enteredPassword.length >= 5) {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO) {
                            val responseValue = registerUser(enteredUsername, enteredPassword)
                            if (responseValue > 0) {
                                storeInSp(enteredUsername, enteredPassword)
                            }
                        }
                        withContext(Dispatchers.Main) {
                            bind.signupUsernameTv.text.clear()
                            bind.signupPasswordTv.text.clear()
                            requireActivity().supportFragmentManager.beginTransaction()
                                .replace(R.id.main_frame, LoginFragment()).commit()
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Not enough length", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Something empty", Toast.LENGTH_SHORT).show()
            }
        }

        bind.signupLoginScreenLink.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, LoginFragment()).commit()
        }
        return bind.root;
    }

    private fun registerUser(enteredUsername: String, enteredPassword: String): Long {
        return userViewModel.registerUser(UserEntity(enteredUsername, enteredPassword))
    }

    private fun storeInSp(enteredUsername: String, enteredPassword: String) {
        preferences.storeValue(enteredUsername, UserEntity(enteredUsername, enteredPassword))
    }
}