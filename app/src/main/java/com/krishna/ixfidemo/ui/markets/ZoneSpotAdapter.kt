package com.krishna.ixfidemo.ui.markets

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.krishna.ixfidemo.ui.home.HomeFragment
import com.krishna.ixfidemo.ui.trade.TradeFragment

class ZoneSpotAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> return ZoneFragment()
            1 -> return SpotFragment()
        }
        return TradeFragment()
    }
}