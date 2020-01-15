package me.rnita.pokedroid

import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PokemonViewModel : ViewModel() {

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
        pokemons = RxPagedListBuilder(PokemonDataSourceFactory(ApiClient()), config)
                .setFetchScheduler(AndroidSchedulers.mainThread())
                .buildFlowable(BackpressureStrategy.LATEST)
    }

}