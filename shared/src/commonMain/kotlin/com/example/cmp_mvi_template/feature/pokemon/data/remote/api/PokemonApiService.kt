package com.example.cmp_mvi_template.feature.pokemon.data.remote.api

import io.ktor.client.statement.HttpResponse

interface PokemonApiService {
    suspend fun getPokemonList(limit: Int, offset: Int): HttpResponse
    suspend fun getPokemonById(id: Int): HttpResponse
}