package com.example.cmp_mvi_template.feature.pokemon.data.mapper

import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.*
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonDto
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonListItemDto
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonListResponseDto
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonOtherSpritesDto
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonSpritesDto
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.*
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonListItem
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonListResponse
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonOtherSprites
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonSprites

fun PokemonListResponseDto.toDomain(): PokemonListResponse {
    return PokemonListResponse(
        count = count,
        next = next,
        previous = previous,
        results = results.map { it.toDomain() }
    )
}

fun PokemonListItemDto.toDomain(): PokemonListItem {
    return PokemonListItem(
        name = name,
        url = url
    )
}

fun PokemonDto.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        baseExperience = baseExperience ?: 0,
        sprites = sprites.toDomain(),
        types = types.map { it.toDomain() },
        stats = stats.map { it.toDomain() },
        abilities = abilities.map { it.toDomain() }
    )
}

fun PokemonSpritesDto.toDomain(): PokemonSprites {
    return PokemonSprites(
        frontDefault = frontDefault,
        frontShiny = frontShiny,
        other = other?.toDomain()
    )
}

fun PokemonOtherSpritesDto.toDomain(): PokemonOtherSprites {
    return PokemonOtherSprites(
        officialArtwork = officialArtwork?.toDomain()
    )
}

fun OfficialArtworkDto.toDomain(): OfficialArtwork {
    return OfficialArtwork(
        frontDefault = frontDefault
    )
}

fun PokemonTypeDto.toDomain(): PokemonType {
    return PokemonType(
        slot = slot,
        type = type.toDomain()
    )
}

fun TypeDto.toDomain(): Type {
    return Type(
        name = name,
        url = url
    )
}

fun PokemonStatDto.toDomain(): PokemonStat {
    return PokemonStat(
        baseStat = baseStat,
        effort = effort,
        stat = stat.toDomain()
    )
}

fun StatDto.toDomain(): Stat {
    return Stat(
        name = name,
        url = url
    )
}

fun PokemonAbilityDto.toDomain(): PokemonAbility {
    return PokemonAbility(
        isHidden = isHidden,
        slot = slot,
        ability = ability.toDomain()
    )
}

fun AbilityDto.toDomain(): Ability {
    return Ability(
        name = name,
        url = url
    )
}

// Domain to Entity mappers
fun Pokemon.toEntity(): PokemonEntity {
    return PokemonEntity(
        id = id,
        name = name,
        imageUrl = sprites.other?.officialArtwork?.frontDefault ?: sprites.frontDefault,
        types = types.joinToString(",") { it.type.name },
        height = height,
        weight = weight,
        baseExperience = baseExperience
    )
}

fun PokemonEntity.toDomain(): Pokemon {
    return Pokemon(
        id = id,
        name = name,
        height = height,
        weight = weight,
        baseExperience = baseExperience,
        sprites = PokemonSprites(
            frontDefault = imageUrl,
            frontShiny = null,
            other = PokemonOtherSprites(
                officialArtwork = OfficialArtwork(frontDefault = imageUrl)
            )
        ),
        types = types.split(",").mapIndexed { index, typeName ->
            PokemonType(
                slot = index + 1,
                type = Type(name = typeName, url = "")
            )
        },
        stats = emptyList(),
        abilities = emptyList()
    )
}