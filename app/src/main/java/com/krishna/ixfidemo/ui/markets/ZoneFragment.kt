package com.krishna.ixfidemo.ui.markets

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.krishna.ixfidemo.R
import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem
import com.krishna.ixfidemo.databinding.FragmentZoneBinding
import com.krishna.ixfidemo.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ZoneFragment : Fragment(R.layout.fragment_zone) {
    private var _binding: FragmentZoneBinding? = null
    private var marketList = arrayListOf<BinanceMarketResponseModelItem>()
    private var zoneAdapter: ZoneAdapter = ZoneAdapter(marketList)
    private val marketViewModel: MarketsViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentZoneBinding.bind(view)
        _binding = binding
        initRecyclerview()
        marketViewModel.getPost()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launchWhenStarted {
            getAllMarketData()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @SuppressLint("NotifyDataSetChanged")
    private suspend fun getAllMarketData() {
        marketViewModel.tempPostStateFlow.collect { it ->
            when (it) {
                is ApiState.Loading -> {
                    _binding?.rvZone?.isVisible = false
                }
                is ApiState.Failure -> {
                    _binding?.rvZone?.isVisible = false
                }
                is ApiState.Success -> {
                    if (it.data.isNotEmpty()) {
                        marketList.clear()
                        marketList.addAll(it.data)
                        zoneAdapter.notifyDataSetChanged()
                    }
                    _binding?.rvZone?.isVisible = true
                }
                is ApiState.Empty -> {

                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerview() {
        marketList.clear()
        zoneAdapter.notifyDataSetChanged()
        _binding?.rvZone?.apply {
            adapter = zoneAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}