package pokedroid.data

import androidx.paging.DataSource

class PokemonDataSourceFactory(
    private val apiClient: PokemonApiFetcher
) : DataSource.Factory<Int, PokemonsQuery.Pokemon>() {
    override fun create(): DataSource<Int, PokemonsQuery.Pokemon> = PositionalPokemonDataSource(apiClient)
}