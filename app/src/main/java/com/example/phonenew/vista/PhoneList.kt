package com.example.phonenew.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.phonenew.R
import com.example.phonenew.databinding.FragmentPhoneListBinding


class PhoneList : Fragment() {
    lateinit var binding: FragmentPhoneListBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        phoneViewModel.getAllPhones()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterPhone()
        binding.rvPhoneList.adapter = adapter
        phoneViewModel.phoneLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }
}