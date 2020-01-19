package pokedroid.data

import com.apollographql.apollo.ApolloClient
import io.mockk.mockk
import io.mockk.verify

class RxApolloClientSpec : RxStringSpec({

    val mockApolloClient = mockk<ApolloClient>(relaxed = true)
    val client = RxApolloClient(mockApolloClient)

    "it should propagate apollo client" {
        val expectedQuery = PokemonsQuery(1)
        client.query(PokemonsQuery(1))

        verify {
            client.query(expectedQuery)
        }
    }
})