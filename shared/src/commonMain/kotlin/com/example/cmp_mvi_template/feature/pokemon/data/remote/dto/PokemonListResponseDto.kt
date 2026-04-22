package com.example.cmp_mvi_template.feature.pokemon.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResponseDto(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListItemDto>
)

@Serializable
data class PokemonListItemDto(
    val name: String,
    val url: String
)

@Serializable
data class PokemonDto(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    @SerialName("base_experience") val baseExperience: Int?,
    val sprites: PokemonSpritesDto,
    val types: List<PokemonTypeDto>,
    val stats: List<PokemonStatDto>,
    val abilities: List<PokemonAbilityDto>
)

@Serializable
data class PokemonSpritesDto(
    @SerialName("front_default") val frontDefault: String?,
    @SerialName("front_shiny") val frontShiny: String?,
    val other: PokemonOtherSpritesDto?
)

@Serializable
data class PokemonOtherSpritesDto(
    @SerialName("official-artwork") val officialArtwork: OfficialArtworkDto?
)

@Serializable
data class OfficialArtworkDto(
    @SerialName("front_default") val frontDefault: String?
)

@Serializable
data class PokemonTypeDto(
    val slot: Int,
    val type: TypeDto
)

@Serializable
data class TypeDto(
    val name: String,
    val url: String
)

@Serializable
data class PokemonStatDto(
    @SerialName("base_stat") val baseStat: Int,
    val effort: Int,
    val stat: StatDto
)

@Serializable
data class StatDto(
    val name: String,
    val url: String
)

@Serializable
data class PokemonAbilityDto(
    @SerialName("is_hidden") val isHidden: Boolean,
    val slot: Int,
    val ability: AbilityDto
)

@Serializable
data class AbilityDto(
    val name: String,
    val url: String
)