package com.maroqi.newsapplication.application.usecases

import com.maroqi.newsapplication.domain.requests.Request
import kotlinx.coroutines.*

abstract class UseCase<T> {
    abstract suspend fun execute(request: Request<T>)

    @OptIn(DelicateCoroutinesApi::class)
    fun run(request: Request<T>, scope: CoroutineScope = GlobalScope) {
        scope.launch(Dispatchers.Main) { execute(request) }
    }
}
