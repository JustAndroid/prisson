package com.goodsoft.prisson.main.start

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.goodsoft.prisson.BaseFragment
import com.goodsoft.prisson.R
import kotlinx.android.synthetic.main.fragment_start_screen.*

/**
 * Created by Nikolay on 12/4/20.
 */
class StartScreenFragment: BaseFragment(R.layout.fragment_start_screen) {

    override fun initUI() {
        loginButton.setOnClickListener {
            findNavController(this).navigate(R.id.action_startScreenFragment_to_loginFragment)
        }
    }

}