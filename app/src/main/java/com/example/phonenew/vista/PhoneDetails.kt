package com.example.phonenew.vista

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import coil.load
import com.example.phonenew.databinding.FragmentPhoneDetailsBinding

private const val ARG_PARAM1 = "id"

class PhoneDetails() : Fragment(), Parcelable {
    lateinit var binding: FragmentPhoneDetailsBinding
    private val phoneViewModel: PhoneViewModel by activityViewModels()
    private var param1: Int = 0

    constructor(parcel: Parcel) : this() {
        param1 = parcel.readInt()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(param1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PhoneDetails> {
        override fun createFromParcel(parcel: Parcel): PhoneDetails {
            return PhoneDetails(parcel)
        }

        override fun newArray(size: Int): Array<PhoneDetails?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhoneDetailsBinding.inflate(layoutInflater, container, false)
        phoneViewModel.getDetailsVM(param1)
        phoneViewModel.detailsLiveData(param1).observe(viewLifecycleOwner) {
            if (it != null) {
                binding.imageViewDetails.load(it.image)
                binding.textViewNameDetails.text = it.name
            }

        }
        return binding.root
    }
}