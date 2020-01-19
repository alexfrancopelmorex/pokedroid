package pokedroid.data

import androidx.paging.PositionalDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.Single

class PositionalPokemonDataSourceSpec : RxStringSpec({
    val mockApiFetcher = mockk<PokemonApiFetcher>(relaxed = true)
    val dataSource = PositionalPokemonDataSource(mockApiFetcher)

    "it should load with request load size" {
        // Given
        val mockCallback = mockk<PositionalDataSource.LoadInitialCallback<PokemonsQuery.Pokemon>>(
            relaxed = true
        )
        val expectedResult = listOf(PokemonsQuery.Pokemon("", mockk()))

        every {
            mockApiFetcher.getPokemons(any())
        } returns Single.just(PokemonsQuery.Data(expectedResult))

        // When
        dataSource.loadInitial(PositionalDataSource.LoadInitialParams(
            1, 1, 1, false
        ), mockCallback)

        verify {
            mockApiFetcher.getPokemons(1)
            mockCallback.onResult(expectedResult, 1)
        }
    }

    "it should load with initial multiply by load size" {
        val mockCallback = mockk<PositionalDataSource.LoadRangeCallback<PokemonsQuery.Pokemon>>(
            relaxed = true
        )
        val expectedResult = listOf(PokemonsQuery.Pokemon("", mockk()))

        every {
            mockApiFetcher.getPokemons(any())
        } returns Single.just(PokemonsQuery.Data(expectedResult))

        dataSource.loadRange(
            PositionalDataSource.LoadRangeParams(1, 10),
            mockCallback
        )

        verify {
            mockApiFetcher.getPokemons(11)
            mockCallback.onResult(expectedResult)
        }
    }
})