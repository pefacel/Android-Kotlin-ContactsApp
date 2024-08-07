package com.pefacel.contactapp2.view.contactList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pefacel.contactapp2.R
import com.pefacel.contactapp2.databinding.FragmentContactListBinding
import com.pefacel.contactapp2.viewModel.ContactViewModel


class ContactListFragment : Fragment() {

    private lateinit var binding: FragmentContactListBinding

    private val contactViewModel: ContactViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactListBinding.inflate(layoutInflater)

        initUI()

        return binding.root
    }


    private fun initUI() {
        initUIState()
        initUIListener()
    }

    private fun initUIState() {

        contactViewModel.contacts.observe(viewLifecycleOwner, Observer { contacts ->
            println("Contacts from ContactListFragment")
            println(contacts)
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            binding.recyclerView.adapter = ContactListAdapter(contacts) { contact ->
                contactViewModel.setCurrentContact(contact)
            }
        })


    }

    private fun initUIListener() {


    }


}