package com.example.avitotechweather.presentation.ui.main

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.avitotechweather.databinding.MainFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding:MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.numberLiveData.observe(viewLifecycleOwner) {
            binding.number.text = it.toString()
        }

        viewModel.cityNameLiveData.observe(viewLifecycleOwner) {
            binding.coordinates.text = it.toString()
        }

        binding.number.setOnClickListener {
            viewModel.increaseNumber()
        }
    }


    companion object {
        fun newInstance() = MainFragment()
    }

}