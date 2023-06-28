package com.thiagocontelli.notes.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagocontelli.notes.data.db.NoteDao
import com.thiagocontelli.notes.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {

    fun getAllNotes(callback: (List<Note>) -> Unit) {
        viewModelScope.launch {
            val notes = noteDao.listAll()
            callback(notes)
        }
    }
}