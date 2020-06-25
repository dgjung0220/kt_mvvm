package me.dgjung.pedometer_kotlin

import android.app.Application
import androidx.lifecycle.LiveData
import me.dgjung.pedometer_kotlin.DAO.ContactDAO
import me.dgjung.pedometer_kotlin.DTO.Contact
import me.dgjung.pedometer_kotlin.Database.ContactDatabase

class ContactRepository(application: Application) {

    private val contactDatabase = ContactDatabase.getInstance(application)!!
    private val contactDao: ContactDAO = contactDatabase.contactDao()
    private val contacts: LiveData<List<Contact>> = contactDao.getAll()

    fun getAll(): LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.insert(contact)
            })
            thread.start()
        } catch(e: Exception) {

        }
    }

    fun delete(contact: Contact) {
        try {
            val thread = Thread(Runnable {
                contactDao.delete(contact)
            })
            thread.start()
        } catch(e : Exception) {

        }
    }
}