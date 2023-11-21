package com.example.androidhilttaskdemo.screen

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.androidhilttaskdemo.R
import com.example.androidhilttaskdemo.data.localdb.UserEntity
import com.example.androidhilttaskdemo.data.repository.UserRepository
import com.example.androidhilttaskdemo.data.shared_pref.SharedPref
import com.example.androidhilttaskdemo.databinding.FragmentLoginBinding
import com.example.androidhilttaskdemo.viewmodel.UserViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var userViewModel: UserViewModel

    private var isValidated: Boolean = false;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        val bind = FragmentLoginBinding.inflate(layoutInflater);

        bind.loginButton.setOnClickListener {
            val enteredUsername = bind.loginUsernameTv.text.toString().trim();
            val enteredPassword = bind.loginPasswordTv.text.toString().trim()
            if (enteredUsername.isNotEmpty() && enteredPassword.isNotEmpty() && enteredUsername.length >= 5 && enteredPassword.length >= 5) {

                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        isValidated = userViewModel.validateUser(enteredUsername, enteredPassword)
                    }

                    withContext(Dispatchers.Main) {
                        if (isValidated) {
                            val intent = Intent(requireContext(), ListingActivity::class.java)
                            intent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY
                            startActivity(intent)
                            userViewModel.setLoginState(true)
                            requireActivity().finish()
                        } else {
                            Snackbar.make(it, "User not found", Snackbar.LENGTH_LONG).show()
                        }
                    }
                }
            } else {
                Snackbar.make(it, "Enter 5 digit or character", Snackbar.LENGTH_LONG).show()
            }
        }

        bind.loginSignupScreenLink.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_frame, SignupFragment()).commit()
        }
        return bind.root;
    }
}