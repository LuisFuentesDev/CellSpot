package com.example.phonenew.vista

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.phonenew.R
import com.example.phonenew.databinding.FragmentPhoneDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "id"

class PhoneDetails : Fragment() {
    lateinit var binding: FragmentPhoneDetailsBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    private var param1: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt("id")?: 0

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneDetailsBinding.inflate(layoutInflater, container, false)
        phoneViewModel.detailsLiveData(param1).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.imageViewDetails.load(it.image)
                binding.textViewNameDetails.text = it.name
                binding.textViewDescription.text = it.description
                binding.textViewCredit.text = it.credit.toString()
                binding.textViewPriceDetails.text = it.price.toString()
                if (!it.credit) {
                    binding.textViewCredit.text = "Efectivo"
                } else {
                    binding.textViewCredit.text = "Crédito"
                }
            }
        }
        phoneViewModel.getDetailsVM(param1)
        return binding.root
    }
}



