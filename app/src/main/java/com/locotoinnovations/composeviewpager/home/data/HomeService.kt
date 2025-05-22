package com.locotoinnovations.composeviewpager.home.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.exception.ApolloException
import com.locotoinnovations.composeviewpager.model.Post
import com.locototeam.graphqlapp.GetPostsQuery
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface IHomeService {
    suspend fun getPosts(): List<Post>
}

class HomeServiceImpl @Inject constructor(
    private val ioDispatcher: CoroutineDispatcher,
    private val apolloClient: ApolloClient,
) : IHomeService {
    override suspend fun getPosts(): List<Post> {
        return withContext(ioDispatcher) {
            try {
                val response = apolloClient.query(GetPostsQuery()).execute()

                if (response.hasErrors()) {
                    // Log or handle errors
                    throw Exception("GraphQL Error: ${response.errors}")
                }

                val posts = response.data?.posts?.data ?: emptyList()

                posts.mapNotNull {
                    if (it == null) return@mapNotNull null
                    Post(
                        id = it.id.orEmpty(),
                        title = it.title.orEmpty(),
                        body = it.body.orEmpty()
                    )
                }

            } catch (e: ApolloException) {
                // Network or parsing issues
                throw Exception("ApolloException: ${e.message}", e)
            } catch (e: Exception) {
                // General fallback
                throw Exception("Unknown error: ${e.message}", e)
            }
        }
    }
}
