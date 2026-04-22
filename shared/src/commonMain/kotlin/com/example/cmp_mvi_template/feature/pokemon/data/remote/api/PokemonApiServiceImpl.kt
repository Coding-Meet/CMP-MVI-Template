package com.example.cmp_mvi_template.feature.pokemon.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class PokemonApiServiceImpl(
    private val httpClient: HttpClient
) : PokemonApiService {
    
    override suspend fun getPokemonList(limit: Int, offset: Int): HttpResponse {
        return httpClient.get("pokemon") {
            url {
                parameters.append("limit", limit.toString())
                parameters.append("offset", offset.toString())
            }
        }
    }
    
    override suspend fun getPokemonById(id: Int): HttpResponse {
        return httpClient.get("pokemon/$id")
    }
}