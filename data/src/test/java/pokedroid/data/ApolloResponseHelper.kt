package pokedroid.data

import com.apollographql.apollo.api.Error
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Response
import io.mockk.mockk
import io.reactivex.Observable

val FAKE_OPERATION = mockk<Operation<*, *, *>>()

object ApolloResponseHelper {
    fun <T> success(data: T): Observable<Response<T>> {
        val res = Response.builder<T>(FAKE_OPERATION)
            .data(data)
            .build()

        return Observable.just(res)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> error(
        @Suppress("UNUSED_PARAMETER")
        unused: Class<T>,
        error: Error = Error(null, null, null)
    ): Observable<Response<T>> {
        val res = Response.builder<T>(FAKE_OPERATION)
            .errors(mutableListOf(error) as List<Error>?)
            .build()

        return Observable.just(res)
    }
}