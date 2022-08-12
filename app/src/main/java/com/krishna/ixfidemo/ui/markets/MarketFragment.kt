package com.krishna.ixfidemo.ui.markets

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_START
import com.google.android.material.tabs.TabLayoutMediator
import com.krishna.ixfidemo.R
import com.krishna.ixfidemo.databinding.FragmentMarketBinding
import com.krishna.ixfidemo.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MarketFragment : Fragment() {

    private var _binding: FragmentMarketBinding? = null
    private val marketViewModel: MarketsViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        val root: View = binding.root
        setupViewPager()
        return root
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setupViewPager() {
        val titles = arrayOf("Zone", "Spot")
        _binding?.vpMarketsType?.adapter = ZoneSpotAdapter(childFragmentManager, lifecycle)
        _binding?.tlMarkets?.let { tl ->
            _binding?.vpMarketsType?.let { vp ->
                TabLayoutMediator(tl, vp) { tab: TabLayout.Tab, position: Int ->
                    tab.text = titles[position]
                }.attach()
            }
        }
        _binding?.tlMarkets?.apply {
            tabGravity = GRAVITY_START
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}