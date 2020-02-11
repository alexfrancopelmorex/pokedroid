package pokedroid

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import pokedroid.data.ApiHelper
import pokedroid.data.PokemonDataSourceFactory
import pokedroid.data.PokemonsQuery

class PokemonViewModel {

    companion object {
        private const val LOAD_COUNT = 10
    }

    private val config = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(LOAD_COUNT)
        .setPageSize(LOAD_COUNT)
        .build()

    val pokemons: Flowable<PagedList<PokemonsQuery.Pokemon>>

    init {
        pokemons = RxPagedListBuilder(PokemonDataSourceFactory(ApiHelper.apiFetcher), config)
            .buildFlowable(BackpressureStrategy.LATEST)
    }

}