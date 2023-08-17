package com.example.phonenew.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.phonenew.databinding.FragmentPhoneDetailsBinding

private const val ARG_PARAM1 = "id"

class PhoneDetails : Fragment() {
    lateinit var binding: FragmentPhoneDetailsBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    private var param1: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneDetailsBinding.inflate(layoutInflater, container, false)
        initAdapter()
        phoneViewModel.getDetailsVM(param1)
        return binding.root
    }

    private fun initAdapter() {
        phoneViewModel.detailsLiveData(param1).observe(viewLifecycleOwner) {

        }
    }

}