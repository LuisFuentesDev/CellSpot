package com.example.phonenew.vista

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.phonenew.R
import com.example.phonenew.data.local.PhoneEntity
import com.example.phonenew.databinding.ItemPhoneBinding

class AdapterPhone : RecyclerView.Adapter<AdapterPhone.ItemPhoneViewHolder>() {

    lateinit var binding: ItemPhoneBinding
    private val listItemPhones = mutableListOf<PhoneEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int

    ): AdapterPhone.ItemPhoneViewHolder {

        binding = ItemPhoneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemPhoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AdapterPhone.ItemPhoneViewHolder, position: Int) {

        val phone = listItemPhones[position]
        holder.bind(phone)
    }

    fun setData(phones: List<PhoneEntity>) {
        this.listItemPhones.clear()
        this.listItemPhones.addAll(phones)
        notifyDataSetChanged()

    }

    override fun getItemCount(): Int {
        return listItemPhones.size
    }

    class ItemPhoneViewHolder(val phoneVista: ItemPhoneBinding) :
        RecyclerView.ViewHolder(phoneVista.root) {

        fun bind(phone: PhoneEntity) {
            phoneVista.imageViewPhone.load(phone.image)
            phoneVista.textViewName.text = phone.name
            phoneVista.textViewPrice.text = phone.price.toString()

            phoneVista.cardViewImage.setOnClickListener {
                val bundle = Bundle()
                bundle.putInt("id", phone.id)
                Navigation.findNavController(phoneVista.root)
                    .navigate(R.id.action_phoneList_to_phoneDetails, bundle)

            }

        }
    }
}