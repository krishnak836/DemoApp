package com.krishna.ixfidemo.ui.markets

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.krishna.ixfidemo.R
import com.krishna.ixfidemo.data.models.BinanceMarketResponseModelItem
import com.krishna.ixfidemo.databinding.FragmentSpotBinding
import com.krishna.ixfidemo.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SpotFragment : Fragment(R.layout.fragment_spot) {
    private var _binding: FragmentSpotBinding? = null
    private var marketList = arrayListOf<BinanceMarketResponseModelItem>()
    private var spotAdapter: SpotAdapter = SpotAdapter(marketList)
    private val marketViewModel: MarketsViewModel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentSpotBinding.bind(view)
        _binding = binding
        initRecyclerview()
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
                    _binding?.rvSpot?.isVisible = false
                }
                is ApiState.Failure -> {
                    _binding?.rvSpot?.isVisible = false
                }
                is ApiState.Success -> {
                    if (it.data.isNotEmpty()) {
                        marketList.clear()
                        marketList.addAll(it.data)
                        spotAdapter.notifyDataSetChanged()
                    }
                    _binding?.rvSpot?.isVisible = true
                }
                is ApiState.Empty -> {

                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerview() {
        marketList.clear()
        spotAdapter.notifyDataSetChanged()
        _binding?.rvSpot?.apply {
            adapter = spotAdapter
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}