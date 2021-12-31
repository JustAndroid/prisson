package com.goodsoft.prisson.main.list

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.goodsoft.prisson.BaseFragment
import com.goodsoft.prisson.R
import com.goodsoft.prisson.api.response.FightersListResponse
import com.goodsoft.prisson.utils.DataState
import com.goodsoft.prisson.utils.gone
import com.goodsoft.prisson.utils.show
import kotlinx.android.synthetic.main.fragment_fighters_list.*

class FightersListFragment: BaseFragment(R.layout.fragment_fighters_list){

    var viewModel: FightersViewModel? = null
    var adapter: FightersAdapter? = null

    override fun initUI() {
        viewModel = ViewModelProvider(this).get(FightersViewModel::class.java)

        fightersList.layoutManager = LinearLayoutManager(context)
        adapter =  FightersAdapter(this)
        fightersList.adapter = adapter

        observe()
    }

    private fun observe(){
        viewModel?.state?.observe(viewLifecycleOwner, Observer {
            when(it){
                is DataState.Data -> {
                    (it.data as FightersListResponse).data?.let { it1 -> adapter?.setList(it1) }
                    progressBar.gone()
                }
                is DataState.Loading -> {
                    progressBar.show()
                }
            }
        })
    }
}