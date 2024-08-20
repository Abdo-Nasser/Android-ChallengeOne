package com.example.challengeone.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challengeone.UIModels.PlaceUIModel
import com.example.challengeone.R

class HomeMainCellRecyclerView(private val itemsList: List<PlaceUIModel>, private val isCardView: Boolean): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val card = 1
        const val carsouel = 2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (isCardView) {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_item_card_cell, parent, false)
            return CardViewHolder(itemView)
        } else {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.home_item_carsouel_cell, parent, false)
            return CarsoeulViewHolder(itemView)
        }
    }

    override fun getItemCount(): Int {
        return itemsList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (isCardView) {
            card
        } else {
            carsouel
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
       when (holder.itemViewType) {
           card -> {
               val viewHolder = holder as CardViewHolder
               viewHolder.setItemData(itemsList[position])
           }
           carsouel -> {
                val viewHolder = holder as CarsoeulViewHolder
               viewHolder.setItemData(itemsList[position])
           }
       }
    }

    class CardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.itemImageView)
        private val title = itemView.findViewById<TextView>(R.id.itemNameTextView)
        private val rateTextView = itemView.findViewById<TextView>(R.id.itemRateTextView)
        private val rateImageView = itemView.findViewById<ImageView>(R.id.itemStartImageView)
        private val favouriteCardView = itemView.findViewById<CardView>(R.id.favouriteCardView)

        fun setItemData(item: PlaceUIModel) {
            Glide.with(itemView)
                .load(item.imageULR)
                .into(imageView)
            title.text = item.title
            rateTextView.text = item.rate.toString()
            favouriteCardView.isVisible = item.isFavourite
        }
    }

    class CarsoeulViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val imageView = itemView.findViewById<ImageView>(R.id.itemCarsouelImageView)
        private val title = itemView.findViewById<TextView>(R.id.titleCarsouelTextView)
        private val hotDealImageView = itemView.findViewById<ImageView>(R.id.hotDealCarsouelImageView)
        private val hotDealTextView = itemView.findViewById<TextView>(R.id.hotDealTextView)

        fun setItemData(item: PlaceUIModel) {
            Glide.with(itemView)
                .load(item.imageULR)
                .into(imageView)
            title.text = item.title
            hotDealImageView.isVisible = item.isHotDeal
            hotDealTextView.isVisible = item.isHotDeal
        }
    }
}