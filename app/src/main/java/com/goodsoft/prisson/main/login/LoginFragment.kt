package com.goodsoft.prisson.main.login

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodsoft.prisson.BaseFragment
import com.goodsoft.prisson.R
import com.goodsoft.prisson.main.fight.FightViewModel
import kotlinx.android.synthetic.main.fragment_login_layout.*

/**
 * Created by Nikolay on 12/4/20.
 */
class LoginFragment: BaseFragment(R.layout.fragment_login_layout) {

    var viewModel: LoginViewModel? = null


    override fun initUI() {
        authButton.setOnClickListener { viewModel?.auth() }
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

        })
    }


}