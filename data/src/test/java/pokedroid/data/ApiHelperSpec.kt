package pokedroid.data

import com.apollographql.apollo.ApolloClient
import io.kotlintest.matchers.types.shouldBeTypeOf
import io.kotlintest.specs.StringSpec

class ApiHelperSpec : StringSpec({
    "it should return without error" {
        ApiHelper.apiFetcher.shouldBeTypeOf<PokemonApiFetcher>()
        ApiHelper.client.shouldBeTypeOf<ApolloClient>()
    }
})