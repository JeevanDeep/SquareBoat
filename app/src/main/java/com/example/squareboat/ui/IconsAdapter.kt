package com.example.squareboat.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.squareboat.R
import com.example.squareboat.model.icon.IconsItem
import com.example.squareboat.model.icon.RasterSizesItem
import com.example.squareboat.utils.setGone
import com.example.squareboat.utils.setVisible
import com.example.squareboat.utils.toDollar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.icon_row.view.*
import kotlinx.android.synthetic.main.progress_layout.view.*

class IconsAdapter(private val listOfIcons: List<IconsItem>, private val iconListener: IconListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var showProgress = false
    fun isLastItem(position: Int): Boolean {
        return position == itemCount - 1 && showProgress
    }

    fun allowProgress(allowProgress: Boolean) {
        showProgress = allowProgress
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (showProgress && isLastItem(position)) PROGRESS_TYPE else ICON_TYPE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == PROGRESS_TYPE) {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.progress_layout, parent, false)
            return ProgressViewHolder(v)
        } else {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.icon_row, parent, false)
            return IconViewHolder(v)
        }
    }

    override fun getItemCount(): Int {
        if (showProgress)
            return listOfIcons.size + 1
        else return listOfIcons.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is IconViewHolder)
            holder.bind(listOfIcons[position])
        else if (holder is ProgressViewHolder)
            holder.bindLoadMore()
    }


    inner class IconViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val iconImage = itemView.iconImage
        val iconName = itemView.iconName
        val price = itemView.priceTextView
        val downloadButton = itemView.downloadIcon
        val premiumBanner = itemView.premiumBanner

        fun bind(iconsItem: IconsItem) {
            iconName.text = iconsItem.tags[0]

            val rasterList = iconsItem.rasterSizes.filter {
                it.size == 64
            }
            val rasterItem: RasterSizesItem
            if (rasterList.isEmpty())
                rasterItem = iconsItem.rasterSizes.get(iconsItem.rasterSizes.size - 1)
            else rasterItem = rasterList[0]

            val url = rasterItem.formats.get(0).previewUrl ?: ""
            Picasso.get().load(url).into(iconImage)

            downloadButton.setOnClickListener {
                iconListener.onDownloadClicked(
                    iconsItem.rasterSizes[iconsItem.rasterSizes.size - 1].formats[0].previewUrl.toString()
                )
            }

            if (iconsItem.isPremium)
                showPremiumViews(iconsItem)
            else showFreeViews()
        }

        private fun showPremiumViews(iconsItem: IconsItem) {
            premiumBanner.setVisible()
            price.setVisible()
            price.text = iconsItem.prices[0].price?.toInt().toString().toDollar()
            downloadButton.setGone()
        }

        private fun showFreeViews() {
            premiumBanner.setGone()
            price.setGone()
            downloadButton.setVisible()
        }

    }

    inner class ProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val loadMore = itemView.loadMore
        val progress = itemView.progress
        fun bindLoadMore() {

            progress.setGone()
            loadMore.setVisible()
            loadMore.setOnClickListener {
                progress.setVisible()
                loadMore.setGone()
                iconListener.onLoadMoreClicked()
            }
        }
    }

    companion object {
        const val PROGRESS_TYPE = 1
        const val ICON_TYPE = 2
    }

    interface IconListener {
        fun onDownloadClicked(url: String)
        fun onLoadMoreClicked()
    }

}