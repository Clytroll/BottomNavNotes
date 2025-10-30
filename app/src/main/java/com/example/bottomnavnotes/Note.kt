package com.example.bottomnavnotes

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val title: String,
    val content: String
)
