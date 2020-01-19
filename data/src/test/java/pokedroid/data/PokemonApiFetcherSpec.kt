package pokedroid.data

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class PokemonApiFetcherSpec : RxStringSpec({

    val mockApiClient = mockk<RxApolloClient>(relaxed = true)
    val fetcher = PokemonApiFetcher(mockApiClient)

    "it should fetch with expected param" {
        val expectedParam = 1
        fetcher.getPokemons(expectedParam)
        verify {
            mockApiClient.query(PokemonsQuery(expectedParam))
        }
    }

    "it should have valid response" {
        val expectedResult = PokemonsQuery.Data(listOf(PokemonsQuery.Pokemon("", mockk())))

        every {
            mockApiClient.query(any<PokemonsQuery>())
        } returns ApolloResponseHelper.success(expectedResult)

        val result = fetcher.getPokemons(1)

        result.test()
            .assertValue(expectedResult)
            .assertComplete()
    }

    "it should return error response" {
        every {
            mockApiClient.query(any<PokemonsQuery>())
        } returns ApolloResponseHelper.error(PokemonsQuery.Data::class.java)

        val result = fetcher.getPokemons(1)

        result.test()
            .assertError { true }
    }

})
