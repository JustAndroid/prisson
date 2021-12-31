package com.goodsoft.prisson.main.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.goodsoft.prisson.R
import com.goodsoft.prisson.utils.Pref
import kotlinx.android.synthetic.main.fighter_item_view.view.*

class FightersAdapter(val fragment: FightersListFragment) : RecyclerView.Adapter<FightersAdapter.ViewHolder>() {

   private var list = listOf<String>()

    fun setList(list: List<String>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.fighter_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(position: Int) {
            itemView.loginText.text = list[position]
            itemView.setOnClickListener {
                Pref.getInstance(itemView.context)?.saveLogin2(list[position])
                NavHostFragment.findNavController(fragment).navigate(R.id.action_fightersListFragment_to_fightFragment)
            }
        }
    }

}