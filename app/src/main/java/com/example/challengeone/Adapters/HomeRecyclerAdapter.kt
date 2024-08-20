package com.example.challengeone.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challengeone.UIModels.HomeSectionUIModel
import com.example.challengeone.R

class HomeRecyclerAdapter(private val itemsList: List<HomeSectionUIModel>): RecyclerView.Adapter<HomeRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.home_main_cell, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setItemData(itemsList[position])
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val title = itemView.findViewById<TextView>(R.id.titleTextView)
        private val seeAllTextView = itemView.findViewById<TextView>(R.id.seeAllTextView)
        private val recyclerView = itemView.findViewById<RecyclerView>(R.id.mainCellRecyclerView)

        fun setItemData(item: HomeSectionUIModel) {
            recyclerView.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.adapter = HomeMainCellRecyclerView(item.items, item.isCardView)
            title.text = item.title
            seeAllTextView.isVisible = item.hasSeeAll
        }
    }
}