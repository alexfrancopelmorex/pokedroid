package pokedroid.data

import io.kotlintest.shouldBe
import io.mockk.mockk

class PokemonDataSourceFactorySpec : RxStringSpec({
    "it should create new data source" {
        val factory = PokemonDataSourceFactory(mockk())
        val isTrue = factory.create() is PositionalPokemonDataSource
        isTrue shouldBe true
    }
})