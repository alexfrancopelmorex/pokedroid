package pokedroid.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Mutation
import com.apollographql.apollo.api.Operation
import com.apollographql.apollo.api.Query
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.rx2.Rx2Apollo
import io.reactivex.Observable

class RxApolloClient(private val apolloClient: ApolloClient) : ApiCaller {

    override fun <D : Operation.Data?, T, V : Operation.Variables?> query(
        query: Query<D, T, V>
    ): Observable<Response<T>> {
        return Rx2Apollo.from(apolloClient.query(query))
    }

    override fun <D : Operation.Data?, T, V : Operation.Variables?> mutate(
        mutation: Mutation<D, T, V>
    ): Observable<Response<T>> {
        return Rx2Apollo.from(apolloClient.mutate(mutation))
    }
}