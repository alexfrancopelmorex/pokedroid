package me.rnita.pokedroid

import androidx.paging.DataSource

class PokemonDataSourceFactory constructor(private val apiClient: ApiClient) : DataSource.Factory<Int, PokemonsQuery.Pokemon>() {
    override fun create(): DataSource<Int, PokemonsQuery.Pokemon> = PositionalPokemonDataSource(apiClient)
}