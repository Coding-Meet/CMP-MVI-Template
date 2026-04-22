package com.example.cmp_mvi_template.core.data.local.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    
    @Query("SELECT * FROM favorite_pokemon ORDER BY name ASC")
    fun getAllFavoritesPokemon(): Flow<List<PokemonEntity>>
    
    @Query("SELECT * FROM favorite_pokemon WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity)
    
    @Delete
    suspend fun deletePokemon(pokemon: PokemonEntity)
    
    @Query("DELETE FROM favorite_pokemon WHERE id = :id")
    suspend fun deletePokemonById(id: Int)
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_pokemon WHERE id = :id)")
    suspend fun isPokemonFavorite(id: Int): Boolean
}