package com.example.cmp_mvi_template.feature.pokemon.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_pokemon")
data class PokemonEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val imageUrl: String?,
    val types: String, // JSON string of types
    val height: Int,
    val weight: Int,
    val baseExperience: Int
)