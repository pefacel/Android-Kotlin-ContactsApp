package com.pefacel.contactapp2.view.contactList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pefacel.contactapp2.data.database.ContactEntity
import com.pefacel.contactapp2.databinding.ItemContactListBinding

class ContactListAdapter(
    private var contacts: List<ContactEntity>,
    var itemClickListener: (ContactEntity) -> Unit
) : RecyclerView.Adapter<ContactListAdapter.ContactHolder>() {

    inner class ContactHolder(val binding: ItemContactListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(contact: ContactEntity) {
            binding.textViewName.text = contact.name
            binding.textViewEmail.text = contact.email
            binding.itemContact.setOnClickListener {
                itemClickListener(contact)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val binding =
            ItemContactListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactHolder(binding)
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.render(contacts.get(position))

    }


}