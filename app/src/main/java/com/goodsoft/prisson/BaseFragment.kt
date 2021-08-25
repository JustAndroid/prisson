package com.goodsoft.prisson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.goodsoft.prisson.view.LoadingDialog

/**
 * Created by Nikolay on 12/4/20.
 */
 abstract class BaseFragment(private val layout: Int) : Fragment() {

    private var loadingDialog: LoadingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    abstract fun initUI()

    fun showLoading(){
        if(loadingDialog == null) {
            loadingDialog = LoadingDialog()
        }

        loadingDialog?.show(childFragmentManager, LoadingDialog.TAG)
    }
}