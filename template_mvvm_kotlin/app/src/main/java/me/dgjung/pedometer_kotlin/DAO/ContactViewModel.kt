package me.dgjung.pedometer_kotlin.DAO

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import me.dgjung.pedometer_kotlin.ContactRepository
import me.dgjung.pedometer_kotlin.DTO.Contact

class ContactViewModel(application: Application) : AndroidViewModel(application){

    private val repository = ContactRepository(application)
    private val contacts = repository.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return this.contacts
    }

    fun insert(contact: Contact) {
        repository.insert(contact)
    }

    fun delete(contact: Contact) {
        repository.delete(contact)
    }
}