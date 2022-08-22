package com.jeromedusanter.utils.extensions

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeout

suspend inline fun <T> suspendCoroutineWithTimeout(
    timeout: Long,
    crossinline block: (CancellableContinuation<T>) -> Unit,
): T? {
    var finalValue: T?
    withTimeout(timeout) {
        finalValue = suspendCancellableCoroutine(block = block)
    }
    return finalValue
}

suspend fun <T> tryOrNull(
    coroutineScope: CoroutineScope,
    block: suspend CoroutineScope.() -> T,
): T? {
    return try {
        block.invoke(coroutineScope)
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }
}

suspend fun <T> tryCatch(
    coroutineScope: CoroutineScope,
    block: suspend CoroutineScope.() -> T,
) {
    try {
        block.invoke(coroutineScope)
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}