package com.example.cmp_mvi_template.core.data.local
import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cmp_mvi_template.core.data.local.dao.PokemonDao
import com.example.cmp_mvi_template.feature.pokemon.domain.entity.PokemonEntity


const val DB_Name = "pokemonDB"
const val DB_Version = 1
@Database(
    entities = [PokemonEntity::class],
    version = DB_Version,
    exportSchema = false
)
@ConstructedBy(PokemonDatabaseConstructor::class)
abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}