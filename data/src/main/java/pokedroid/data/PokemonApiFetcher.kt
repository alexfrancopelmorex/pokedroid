package pokedroid.data

import com.apollographql.apollo.api.Input
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class PokemonApiFetcher(
    private val apolloClient: RxApolloClient
) {
    fun getPokemons(first: Int): Single<PokemonsQuery.Data> =
        apolloClient.query(PokemonsQuery(first))
            .subscribeOn(Schedulers.io())
            .map { it.toData() }
            .singleOrError()

    fun getPokemon(id: String, name: String): Single<PokemonQuery.Data> =
        apolloClient.query(PokemonQuery(Input.fromNullable(id), Input.fromNullable(name)))
            .subscribeOn(Schedulers.io())
            .map { it.toData() }
            .singleOrError()

    private fun <T> com.apollographql.apollo.api.Response<T>.toData(): T =
        if (hasErrors()) throw ResponseException(errors())
        else data() ?: throw DataNullPointerException()

    class DataNullPointerException : Exception("Data is must not be null")

    class ResponseException(errors: List<com.apollographql.apollo.api.Error>) : Exception(errors.toString())
}