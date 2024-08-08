package com.pefacel.contactapp2.view.contactDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.pefacel.contactapp2.R
import com.pefacel.contactapp2.databinding.FragmentContactDetailBinding
import com.pefacel.contactapp2.viewModel.ContactViewModel


class ContactDetailFragment : Fragment() {

    // 1. Pasar este fragment a viewBinding
    // 2. Instanciar el View Model correspondiente
    // 3. Crear nuestras funciones de inicialización


    private lateinit var binding: FragmentContactDetailBinding

    private val contactViewModel: ContactViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContactDetailBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }

    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIListener() {

        // Actualiza nuestro contacto

        binding.imageViewFavorite.setOnClickListener{
            contactViewModel.updateContact()
        }

        binding.imageViewNotFavorite.setOnClickListener{
            contactViewModel.updateContact()
        }


    }

    private fun initUIState() {

        contactViewModel.currentContact.observe(viewLifecycleOwner, Observer { currentContact ->
            if (currentContact != null) {
                binding.textViewName.text = currentContact.name
                binding.textViewEmail.text = currentContact.email

                if (currentContact.favorite) {
                    binding.imageViewFavorite.isVisible = true
                    binding.imageViewNotFavorite.isVisible = false
                }else{
                    binding.imageViewFavorite.isVisible = false
                    binding.imageViewNotFavorite.isVisible = true
                }


            }
        })

    }

}