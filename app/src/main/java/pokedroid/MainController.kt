package pokedroid

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.controller_main.*
import timber.log.Timber

class MainController : BaseController() {

    private val adapter = PokemonAdapter()
    private val disposables = CompositeDisposable()

    override val layoutRes: Int
        get() = R.layout.controller_main

    override fun onAttach(view: View) {
        super.onAttach(view)

        recycler.layoutManager = LinearLayoutManager(view.context)
        recycler.setHasFixedSize(true)
        recycler.adapter = adapter

        val viewModel = PokemonViewModel()
        viewModel.pokemons.subscribeBy(
            onNext = {
                adapter.submitList(it)
            },
            onError = {
                Timber.d(it)
            }
        ).addTo(disposables)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        disposables.clear()
    }
}