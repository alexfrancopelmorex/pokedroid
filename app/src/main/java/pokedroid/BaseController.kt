package pokedroid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RestoreViewOnCreateController
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.*

abstract class BaseController : RestoreViewOnCreateController, LayoutContainer {
    constructor() : super()
    constructor(args: Bundle?) : super(args)

    abstract val layoutRes: Int

    private var _containerView: View? = null
    override val containerView: View? get() = _containerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedViewState: Bundle?
    ): View {
        return inflater.inflate(layoutRes, container, false).also {
            _containerView = it
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        clearFindViewByIdCache()
        _containerView = null
    }
}