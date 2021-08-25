package com.goodsoft.prisson.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.goodsoft.prisson.R

/**
 * Created by Nikolay on 6/15/21.
 */
class LoadingDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.loading_dialog, container)
    }

    companion object{
        const val TAG = "LoadingDialog"
    }
}