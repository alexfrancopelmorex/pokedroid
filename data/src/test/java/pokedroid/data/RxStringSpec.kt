package pokedroid.data

import io.kotlintest.Spec
import io.kotlintest.specs.AbstractStringSpec
import io.kotlintest.specs.StringSpec
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers

abstract class RxStringSpec(body: AbstractStringSpec.() -> Unit) : StringSpec(body) {
    override fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setSingleSchedulerHandler { Schedulers.trampoline() }
    }
}