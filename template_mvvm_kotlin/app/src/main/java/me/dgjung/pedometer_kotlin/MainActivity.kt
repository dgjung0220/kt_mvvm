package me.dgjung.pedometer_kotlin

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.dgjung.pedometer_kotlin.DAO.ContactViewModel
import me.dgjung.pedometer_kotlin.DTO.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var adapter = ContactAdapter({ contact ->}, { contact -> deleteDialog(contact)})

        var lm = LinearLayoutManager(this)
        main_recyclerview.adapter = adapter
        main_recyclerview.layoutManager = lm
        main_recyclerview.setHasFixedSize(true)

        contactViewModel = ViewModelProviders.of(this).get(ContactViewModel::class.java)
        contactViewModel.getAll().observe(this, Observer<List<Contact>> {
                contacts -> adapter.setContacts(contacts!!)
        })

        main_button.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Delete selected contact?")
            .setNegativeButton("No") {_, _ -> }
            .setPositiveButton("Yes") {_, _ -> contactViewModel.delete(contact)}

        builder.show()
    }
}