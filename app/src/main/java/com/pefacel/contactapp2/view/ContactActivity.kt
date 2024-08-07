package com.pefacel.contactapp2.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pefacel.contactapp2.data.database.ContactEntity
import com.pefacel.contactapp2.databinding.ActivityContactBinding
import com.pefacel.contactapp2.view.contactDetailFragment.ContactDetailFragment
import com.pefacel.contactapp2.view.contactList.ContactListFragment
import com.pefacel.contactapp2.viewModel.ContactViewModel

class ContactActivity : AppCompatActivity() {


    private lateinit var binding: ActivityContactBinding
    private val contactListFragment = ContactListFragment()
    private val contactDetailFragment = ContactDetailFragment()

    private val contactViewModel: ContactViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
    }


    private fun initUI() {
        // Inicializar los estados iniciales de la Activty
        initUIState()
        // Inicializar los observables o listener ( observables que estáran escuchando los eventos de la aplicación. Por ejemplo: un click en un botón)
        // Esto hará cambiar el estado inicial
        initUIListener()
    }


    // Estado inicial
    private fun initUIState() {

        // Lo primero es hacer un llamado a los contactos en la base de datos interna con Room
        // Si no hay contactos, hace un fetch y los guarda en caché
        contactViewModel.getContacts()

        // Como Fragmento inicial, será el de Contact list
        startContactListFragment()

    }


    private fun initUIListener() {
        // 1. Nuestro primer Listener será el de apretar la lupa, o en otras palabra, nuestro image view con el vector de search
        binding.imageViewSearch.setOnClickListener {
            binding.editTextSearch.isVisible = true
            binding.textViewTitle.isVisible = false
        }
        // 2. Nuestro segundo observable va a dar cuenta de los cambios del currentContact
        contactViewModel.currentContact.observe(this, Observer { currentContact ->
            // 2.1 Checkear que se imprime desde el contact activity
            println("Current contact desde el contact Activity")
            println(currentContact)

            // 2.2 Cada vez que cambie el currentContact y este no sea null, que me envíe al DetailContactFragment
            if (currentContact != null) {
                startDetailContactFragment(currentContact)
            }
        })
    }

    private fun startDetailContactFragment(currentContact:ContactEntity) {

        setCurrentFragment(contactDetailFragment)

        binding.textViewTitle.text=currentContact.name

    }

    private fun startContactListFragment() {

        // 1. Inicar el fragment con las lista de contactos
        setCurrentFragment(contactListFragment)

        // 2. Mostrar la barra del título de contactos
        binding.textViewTitle.isVisible = true

        // 3. Esconde la barra de búsqueda
        binding.editTextSearch.isVisible = false

        // 4. Inicializar la barra del título con "Contacts"
        binding.textViewTitle.text = "Contacts"

    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(binding.frameLayoutFragment.id, fragment)
            commit()
        }
    }

}

// TAREA 1 (hacerlo a conciencia)
// 1. Crear un nuevo imageview con el vector de flecha atrás
// 2. Si se está en ContactLisFragment, que el estado inicial de la flecha esté oculto
// 3. Si se está en ContactDetailsFragment, que el estado inicial de la flecha esté visible
// 4. Agregarle un listener de tipo click que vuelva el currentContact a su estado original (null)
// 5. Observar dicho cambio desde el ContactActivty y cambiar el estado del FrameLayout al que corresponda.
// 6. Asegurarse de que la barra del título vuelva a su estado original.


// TAREA 2 (OPCIONAL) filtrar los contactos
// 1 Crear en viewModel una nueva de lista de contactos que va a ser nuestra lista de contactos inicial
// 2. Crear una funcion en Viewmodel que permita filtrar la query que se está ingrando en el EditTextView
// 3. Actualizar la lista de contactos por los contactos filtrados
// 4. Crear una función que resetee y vuelva a su estado original la lista de contactos
// 5. Ya sea que el EditText quede vacío o que se vuelva desde el ContactDetails al ContactList, que se resetee la lista de contactos.




