package com.uvg.lab08.util

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable


@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)