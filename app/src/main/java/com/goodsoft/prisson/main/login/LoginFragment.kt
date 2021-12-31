package com.goodsoft.prisson.main.login

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.goodsoft.prisson.BaseFragment
import com.goodsoft.prisson.R
import com.goodsoft.prisson.api.response.AuthResponse
import com.goodsoft.prisson.main.fight.FightViewModel
import com.goodsoft.prisson.utils.DataState
import com.goodsoft.prisson.utils.Pref
import com.goodsoft.prisson.utils.gone
import com.goodsoft.prisson.utils.show
import kotlinx.android.synthetic.main.fragment_login_layout.*

/**
 * Created by Nikolay on 12/4/20.
 */
class LoginFragment : BaseFragment(R.layout.fragment_login_layout) {

    var viewModel: LoginViewModel? = null


    override fun initUI() {
        authButton.setOnClickListener {
            viewModel?.setLogin(loginEditText.text.toString())
            viewModel?.setPassword(passwordEditText.text.toString())
            viewModel?.auth()
        }
        loginEditText.addTextChangedListener {
            viewModel?.setLogin(it.toString())
        }
        passwordEditText.addTextChangedListener {
            viewModel?.setPassword(it.toString())
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        observe()
    }

    private fun observe() {
        viewModel?.state?.observe(viewLifecycleOwner, Observer {
            when (it) {
                is DataState.Data -> {
                    if (it.data is AuthResponse) {
                        when (it.data.status) {
                            AuthResponse.AuthStatus.INVALID_PASSWORD -> {

                            }
                            AuthResponse.AuthStatus.SUCCESS -> {
                                val pref = context?.let { it1 -> Pref.getInstance(it1) }
                                pref?.saveLogin(loginEditText.text.toString())
                                NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_fightersListFragment)
                            }
                            AuthResponse.AuthStatus.BANED_ACCOUNT -> {
                            }
                        }
                    }
                    progressBar.gone()
                }
                is DataState.Loading -> {
                    progressBar.show()
                }
            }

        })
    }


}