package com.thiagocontelli.notes

import java.time.LocalDateTime

data class Note(
    val id: Int, val title: String, val content: String, val createdAt: LocalDateTime
)
