package com.example.cmp_mvi_template.feature.pokemon.domain.entity

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val baseExperience: Int,
    val sprites: PokemonSprites,
    val types: List<PokemonType>,
    val stats: List<PokemonStat>,
    val abilities: List<PokemonAbility>
)

data class PokemonSprites(
    val frontDefault: String?,
    val frontShiny: String?,
    val other: PokemonOtherSprites?
)

data class PokemonOtherSprites(
    val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    val frontDefault: String?
)

data class PokemonType(
    val slot: Int,
    val type: Type
)

data class Type(
    val name: String,
    val url: String
)

data class PokemonStat(
    val baseStat: Int,
    val effort: Int,
    val stat: Stat
)

data class Stat(
    val name: String,
    val url: String
)

data class PokemonAbility(
    val isHidden: Boolean,
    val slot: Int,
    val ability: Ability
)

data class Ability(
    val name: String,
    val url: String
)

// Pokemon List Response
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonListItem>
)

data class PokemonListItem(
    val name: String,
    val url: String
) {
    val id: Int
        get() = url.split("/").dropLast(1).last().toInt()
}