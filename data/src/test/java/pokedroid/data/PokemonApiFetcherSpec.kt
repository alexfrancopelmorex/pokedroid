package pokedroid.data

import com.apollographql.apollo.ApolloClient
import io.kotlintest.specs.StringSpec
import io.mockk.mockk
import io.mockk.verify

class PokemonApiFetcherSpec : StringSpec({

    val mockApiClient = mockk<ApolloClient>(relaxed = true)
    val fetcher = PokemonApiFetcher(mockApiClient)

    "it should fetch with first parameter" {
        val expectedFirst = 1
        val result = fetcher.getPokemons(expectedFirst)

        verify {
            mockApiClient.query(any<PokemonQuery>())
        }
    }

})