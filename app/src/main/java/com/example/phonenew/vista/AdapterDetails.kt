package com.example.phonenew.vista

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.phonenew.data.local.PhoneDetailsEntity
import com.example.phonenew.databinding.FragmentPhoneDetailsBinding

class AdapterDetails : RecyclerView.Adapter<AdapterDetails.ItemDetailsViewHolder>() {

    lateinit var binding: FragmentPhoneDetailsBinding
    private val listDetailsPhones = mutableListOf<PhoneDetailsEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ): AdapterDetails.ItemDetailsViewHolder {

        binding = FragmentPhoneDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemDetailsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listDetailsPhones.size
    }

    override fun onBindViewHolder(holder: ItemDetailsViewHolder, position: Int) {
        val phone = listDetailsPhones[position]
        holder.bind(phone)
    }

    fun setDataDetails(phoneDetailsEntity: PhoneDetailsEntity) {
        this.listDetailsPhones.clear()
        this.listDetailsPhones.addAll(listOf(phoneDetailsEntity))
        notifyDataSetChanged()

    }

    class ItemDetailsViewHolder(val phoneDetailsVista: FragmentPhoneDetailsBinding) :
        RecyclerView.ViewHolder(phoneDetailsVista.root) {
        fun bind(phoneDetails: PhoneDetailsEntity) {
            phoneDetailsVista.imageViewDetails.load(phoneDetails.image)
            phoneDetailsVista.textViewNameDetails.text = phoneDetails.name
            phoneDetailsVista.textViewPriceDetails.text = phoneDetails.price.toString()
            phoneDetailsVista.textViewDescription.text = phoneDetails.description
            phoneDetailsVista.textViewCredit.text = phoneDetails.credit.toString()
        }
    }
}


