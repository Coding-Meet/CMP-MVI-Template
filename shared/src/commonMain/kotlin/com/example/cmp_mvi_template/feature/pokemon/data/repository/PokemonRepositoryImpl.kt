package com.example.cmp_mvi_template.feature.pokemon.data.repository

import androidx.sqlite.SQLiteException
import com.example.cmp_mvi_template.core.data.network.safeCall
import com.example.cmp_mvi_template.core.domain.DataError
import com.example.cmp_mvi_template.core.domain.EmptyResult
import com.example.cmp_mvi_template.core.domain.map
import com.example.cmp_mvi_template.core.data.local.dao.PokemonDao
import com.example.cmp_mvi_template.feature.pokemon.data.mapper.toDomain
import com.example.cmp_mvi_template.feature.pokemon.data.remote.api.PokemonApiService
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonDto
import com.example.cmp_mvi_template.feature.pokemon.data.remote.dto.PokemonListResponseDto
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Pokemon
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonListResponse
import com.example.cmp_mvi_template.feature.pokemon.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import com.example.cmp_mvi_template.core.domain.ResultWrapper
import com.example.cmp_mvi_template.feature.pokemon.data.mapper.toEntity
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Ability
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.OfficialArtwork
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonAbility
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonOtherSprites
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonSprites
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonStat
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonType
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Stat
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.Type
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class PokemonRepositoryImpl(
    private val apiService: PokemonApiService,
    private val pokemonDao: PokemonDao
) : PokemonRepository {

    override suspend fun getPokemonList(
        limit: Int,
        offset: Int
    ): ResultWrapper<PokemonListResponse, DataError.Remote> {
        // return getDummyPokemonList()

        return safeCall<PokemonListResponseDto> {
            apiService.getPokemonList(limit, offset)
        }.map { it.toDomain() }
    }

    override suspend fun getPokemonById(id: Int): ResultWrapper<Pokemon, DataError.Remote> {
        // return getDummyPokemon(id)

        return safeCall<PokemonDto> {
            apiService.getPokemonById(id)
        }.map { it.toDomain() }
    }

    override fun getFavoritesPokemon(): Flow<List<Pokemon>> {
        return pokemonDao.getAllFavoritesPokemon()
            .map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun addToFavorites(pokemon: Pokemon): EmptyResult<DataError.Local> {
        return try {
            pokemonDao.insertPokemon(pokemon.toEntity())
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun removeFromFavorites(pokemonId: Int): EmptyResult<DataError.Local> {
        return try {
            pokemonDao.deletePokemonById(pokemonId)
            ResultWrapper.Success(Unit)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun isPokemonFavorite(pokemonId: Int): ResultWrapper<Boolean, DataError.Local> {
        return try {
            val result = pokemonDao.isPokemonFavorite(pokemonId)
            ResultWrapper.Success(result)
        } catch (e: SQLiteException) {
            ResultWrapper.Error(DataError.Local.DISK_FULL)
        } catch (e: Exception) {
            ResultWrapper.Error(DataError.Local.UNKNOWN)
        }
    }

    private fun getDummyPokemonList(): ResultWrapper<PokemonListResponse, DataError.Remote> {
        val pokemonJson = """{"count":1302,"next":null,"previous":null,"results":[{"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon/1/"},{"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon/2/"},{"name":"venusaur","url":"https://pokeapi.co/api/v2/pokemon/3/"},{"name":"charmander","url":"https://pokeapi.co/api/v2/pokemon/4/"},{"name":"charmeleon","url":"https://pokeapi.co/api/v2/pokemon/5/"},{"name":"charizard","url":"https://pokeapi.co/api/v2/pokemon/6/"},{"name":"squirtle","url":"https://pokeapi.co/api/v2/pokemon/7/"},{"name":"wartortle","url":"https://pokeapi.co/api/v2/pokemon/8/"},{"name":"blastoise","url":"https://pokeapi.co/api/v2/pokemon/9/"},{"name":"caterpie","url":"https://pokeapi.co/api/v2/pokemon/10/"}]}""".trimIndent()
        val pokemonList = Json.decodeFromString<PokemonListResponseDto>(pokemonJson)
        return ResultWrapper.Success(pokemonList.toDomain())
    }

    private fun getDummyPokemon(pokemonId: Int): ResultWrapper<Pokemon, DataError.Remote> {
        val dummyPokemonList = listOf(
            // Bulbasaur
            Pokemon(
                id = 1,
                name = "bulbasaur",
                height = 7,
                weight = 69,
                baseExperience = 64,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/1.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")),
                    PokemonType(slot = 2, type = Type(name = "poison", url = "https://pokeapi.co/api/v2/type/4/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 45, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 49, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 49, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 65, effort = 1, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 65, effort = 0, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 45, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "overgrow", url = "https://pokeapi.co/api/v2/ability/65/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "chlorophyll", url = "https://pokeapi.co/api/v2/ability/34/"))
                )
            ),

            // Ivysaur
            Pokemon(
                id = 2,
                name = "ivysaur",
                height = 10,
                weight = 130,
                baseExperience = 142,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/2.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/2.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/2.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")),
                    PokemonType(slot = 2, type = Type(name = "poison", url = "https://pokeapi.co/api/v2/type/4/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 60, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 62, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 63, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 80, effort = 1, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 80, effort = 1, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 60, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "overgrow", url = "https://pokeapi.co/api/v2/ability/65/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "chlorophyll", url = "https://pokeapi.co/api/v2/ability/34/"))
                )
            ),

            // Venusaur
            Pokemon(
                id = 3,
                name = "venusaur",
                height = 20,
                weight = 1000,
                baseExperience = 236,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/3.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/3.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/3.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "grass", url = "https://pokeapi.co/api/v2/type/12/")),
                    PokemonType(slot = 2, type = Type(name = "poison", url = "https://pokeapi.co/api/v2/type/4/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 80, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 82, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 83, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 100, effort = 2, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 100, effort = 1, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 80, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "overgrow", url = "https://pokeapi.co/api/v2/ability/65/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "chlorophyll", url = "https://pokeapi.co/api/v2/ability/34/"))
                )
            ),

            // Charmander
            Pokemon(
                id = 4,
                name = "charmander",
                height = 6,
                weight = 85,
                baseExperience = 62,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/4.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/4.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/4.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "fire", url = "https://pokeapi.co/api/v2/type/10/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 39, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 52, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 43, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 60, effort = 0, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 50, effort = 0, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 65, effort = 1, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "blaze", url = "https://pokeapi.co/api/v2/ability/66/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "solar-power", url = "https://pokeapi.co/api/v2/ability/94/"))
                )
            ),

            // Charmeleon
            Pokemon(
                id = 5,
                name = "charmeleon",
                height = 11,
                weight = 190,
                baseExperience = 142,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/5.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/5.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/5.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "fire", url = "https://pokeapi.co/api/v2/type/10/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 58, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 64, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 58, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 80, effort = 1, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 65, effort = 0, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 80, effort = 1, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "blaze", url = "https://pokeapi.co/api/v2/ability/66/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "solar-power", url = "https://pokeapi.co/api/v2/ability/94/"))
                )
            ),

            // Charizard
            Pokemon(
                id = 6,
                name = "charizard",
                height = 17,
                weight = 905,
                baseExperience = 240,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/6.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/6.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "fire", url = "https://pokeapi.co/api/v2/type/10/")),
                    PokemonType(slot = 2, type = Type(name = "flying", url = "https://pokeapi.co/api/v2/type/3/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 78, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 84, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 78, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 109, effort = 3, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 85, effort = 0, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 100, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "blaze", url = "https://pokeapi.co/api/v2/ability/66/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "solar-power", url = "https://pokeapi.co/api/v2/ability/94/"))
                )
            ),

            // Squirtle
            Pokemon(
                id = 7,
                name = "squirtle",
                height = 5,
                weight = 90,
                baseExperience = 63,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/7.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/7.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/7.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "water", url = "https://pokeapi.co/api/v2/type/11/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 44, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 48, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 65, effort = 1, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 50, effort = 0, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 64, effort = 0, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 43, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "torrent", url = "https://pokeapi.co/api/v2/ability/67/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "rain-dish", url = "https://pokeapi.co/api/v2/ability/44/"))
                )
            ),

            // Wartortle
            Pokemon(
                id = 8,
                name = "wartortle",
                height = 10,
                weight = 225,
                baseExperience = 142,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/8.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/8.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/8.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "water", url = "https://pokeapi.co/api/v2/type/11/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 59, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 63, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 80, effort = 1, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 65, effort = 0, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 80, effort = 1, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 58, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "torrent", url = "https://pokeapi.co/api/v2/ability/67/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "rain-dish", url = "https://pokeapi.co/api/v2/ability/44/"))
                )
            ),

            // Blastoise
            Pokemon(
                id = 9,
                name = "blastoise",
                height = 16,
                weight = 855,
                baseExperience = 239,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/9.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/9.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/9.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "water", url = "https://pokeapi.co/api/v2/type/11/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 79, effort = 0, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 83, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 100, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 85, effort = 0, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 105, effort = 3, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 78, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "torrent", url = "https://pokeapi.co/api/v2/ability/67/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "rain-dish", url = "https://pokeapi.co/api/v2/ability/44/"))
                )
            ),

            // Caterpie
            Pokemon(
                id = 10,
                name = "caterpie",
                height = 3,
                weight = 29,
                baseExperience = 39,
                sprites = PokemonSprites(
                    frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/10.png",
                    frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/10.png",
                    other = PokemonOtherSprites(
                        officialArtwork = OfficialArtwork(
                            frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/10.png"
                        )
                    )
                ),
                types = listOf(
                    PokemonType(slot = 1, type = Type(name = "bug", url = "https://pokeapi.co/api/v2/type/7/"))
                ),
                stats = listOf(
                    PokemonStat(baseStat = 45, effort = 1, stat = Stat(name = "hp", url = "https://pokeapi.co/api/v2/stat/1/")),
                    PokemonStat(baseStat = 30, effort = 0, stat = Stat(name = "attack", url = "https://pokeapi.co/api/v2/stat/2/")),
                    PokemonStat(baseStat = 35, effort = 0, stat = Stat(name = "defense", url = "https://pokeapi.co/api/v2/stat/3/")),
                    PokemonStat(baseStat = 20, effort = 0, stat = Stat(name = "special-attack", url = "https://pokeapi.co/api/v2/stat/4/")),
                    PokemonStat(baseStat = 20, effort = 0, stat = Stat(name = "special-defense", url = "https://pokeapi.co/api/v2/stat/5/")),
                    PokemonStat(baseStat = 45, effort = 0, stat = Stat(name = "speed", url = "https://pokeapi.co/api/v2/stat/6/"))
                ),
                abilities = listOf(
                    PokemonAbility(isHidden = false, slot = 1, ability = Ability(name = "shield-dust", url = "https://pokeapi.co/api/v2/ability/19/")),
                    PokemonAbility(isHidden = true, slot = 3, ability = Ability(name = "run-away", url = "https://pokeapi.co/api/v2/ability/50/"))
                )
            )
        )

        val pokemon = dummyPokemonList.find { it.id == pokemonId }
        return if (pokemon != null) {
            ResultWrapper.Success(pokemon)
        } else {
            ResultWrapper.Error(DataError.Remote.UNKNOWN)
        }
    }
}