package com.krishna.ixfidemo.ui.markets

import android.graphics.Color
import android.graphics.drawable.RotateDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem
import com.krishna.ixfidemo.databinding.EachRowSpotBinding
import com.krishna.ixfidemo.databinding.EachRowZoneBinding
import java.math.BigDecimal


class SpotAdapter(private var marketList: ArrayList<BinanceMarketResponseModelItem>) :
    RecyclerView.Adapter<SpotAdapter.PostViewHolder>() {
    private lateinit var binding: EachRowSpotBinding


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowSpotBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.apply {
            tvCoin.text = marketList[position].symbol
            marketList[position].priceChangePercent?.let {
                tvPercent.text = "${it.toPlainString()} %"
//                if (it > BigDecimal(0)) {
//                    tvPercent.setTextColor(Color.parseColor("#01A308"))
//                    ivMarketUp.setColorFilter(Color.parseColor("#01A308"))
//
//                } else {
//                    tvPercent.setTextColor(Color.parseColor("#E4080B"))
//                    ivMarketUp.setColorFilter(Color.parseColor("#E4080B"))
//                }
            }

            tvPrice.text = marketList[position].priceChange?.toPlainString()
            tvChangePrice.text = marketList[position].highPrice?.toPlainString()
        }

    }

    override fun getItemCount(): Int = marketList.size

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData() {
        notifyDataSetChanged()
    }

}