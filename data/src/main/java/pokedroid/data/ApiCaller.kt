package pokedroid.data

import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import io.reactivex.Observable

interface ApiCaller {
    fun <D : Operation.Data?, T, V : Operation.Variables?> query(query: Query<D, T, V>): Observable<Response<T>>
    fun <D : Operation.Data?, T, V : Operation.Variables?> mutate(mutation: Mutation<D, T, V>): Observable<Response<T>>
}