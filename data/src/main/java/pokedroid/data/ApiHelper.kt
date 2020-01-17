package pokedroid.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import okhttp3.OkHttpClient

object ApiHelper {
    val client: ApolloClient by lazy {
        ApolloClient.builder()
            .okHttpClient(OkHttpClient.Builder().build())
            .defaultHttpCachePolicy(HttpCachePolicy.CACHE_FIRST)
            .serverUrl("https://graphql-pokemon.now.sh")
            .build()
    }

    val apiFetcher by lazy {
        PokemonApiFetcher(client)
    }
}