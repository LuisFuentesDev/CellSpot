package com.example.phonenew.vista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.phonenew.R
import com.example.phonenew.databinding.FragmentPhoneListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PhoneList.newInstance] factory method to
 * create an instance of this fragment.
 */
class PhoneList : Fragment() {
    lateinit var binding: FragmentPhoneListBinding
    private val pokemonViewModel: PhoneViewModel by activityViewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneListBinding.inflate(layoutInflater, container, false)
        initAdapter()
        pokemonViewModel.getAllPhones()
        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterPhone()
        binding.rvPhoneList.adapter = adapter
        pokemonViewModel.phoneLiveData().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

    }
}