package com.goodsoft.prisson.main.registration

import androidx.core.widget.addTextChangedListener
import com.goodsoft.prisson.BaseFragment
import com.goodsoft.prisson.R
import kotlinx.android.synthetic.main.fragment_registration_layout.*

/**
 * Created by Nikolay on 12/4/20.
 */
class RegistrationFragment: BaseFragment(R.layout.fragment_registration_layout) {



    override fun initUI() {
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        loginEditText.addTextChangedListener {
        }
        passwordEditText.addTextChangedListener {
        }
    }
}