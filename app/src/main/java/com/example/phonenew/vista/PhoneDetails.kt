package com.example.phonenew.vista

import android.content.Intent
import android.net.Uri
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
            param1 = it.getInt("id") ?: 0

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
                binding.textViewPriceDetails.text = "Precio Oferta: $" + it.price.toString()
                binding.textViewLastPrice.text = "Precio Normal: $" + it.lastPrice.toString()
                if (!it.credit) {
                    binding.textViewCredit.text = "Solo Efectivo"
                } else {
                    binding.textViewCredit.text = "Acepta Crédito"

                    initListeners()
                }
            }

        }
        phoneViewModel.getDetailsVM(param1)
        return binding.root
    }

    private fun initListeners() {
        phoneViewModel.detailsLiveData(param1.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val asunto = "Consulta ${it.name} id ${it.id}"
                    val message =
                        "Hola, \nVi el teléfono ${it.name} de código ${it.id} y me gustaría que me contactaran a este correo o al siguiente número ____________. \nQuedo atento."

                    binding.floatingActionButton.setOnClickListener {
                        val mail = "info@novaera.cl"
                        val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(mail))
                        intentMail.type = "text/plain"
                        intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                        intentMail.putExtra(Intent.EXTRA_SUBJECT, asunto)
                        intentMail.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(intentMail, "Send Mail"))
                    }
                }
            }
    }
}



