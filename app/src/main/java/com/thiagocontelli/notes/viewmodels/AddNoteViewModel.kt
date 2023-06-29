package com.thiagocontelli.notes.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thiagocontelli.notes.data.db.NoteDao
import com.thiagocontelli.notes.models.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class AddNoteViewModel @Inject constructor(private val noteDao: NoteDao) : ViewModel() {
    fun addNote(title: String, content: String, onSuccess: () -> Unit) {
        val note = Note(title = title, content = content)

        viewModelScope.launch {
            noteDao.insert(note)
            onSuccess()
        }
    }

    fun editNote(id: Int, title: String, content: String, createdAt: String, onSuccess: () -> Unit) {
        val note = Note(id, title, content, LocalDateTime.parse(createdAt))

        viewModelScope.launch {
            noteDao.update(note)
            onSuccess()
        }
    }
}