package com.maroqi.newsapplication.core.application.usecases

import com.maroqi.newsapplication.core.network.Request
import kotlinx.coroutines.*

abstract class UseCase<T> {
    abstract suspend fun execute(request: Request<T>)

    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke(
        request: Request<T>,
        scope: CoroutineScope = GlobalScope,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        scope.launch(dispatcher) { execute(request) }
    }
}
