package com.thiagocontelli.notes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagocontelli.notes.data.db.NoteDao
import com.thiagocontelli.notes.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {

    fun deleteNote(note: Note, onSuccess: () -> Unit) {
        viewModelScope.launch {
            noteDao.delete(note)
            onSuccess()
        }
    }
}