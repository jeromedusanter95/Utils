package com.jeromedusanter.utils.extensions

inline fun <reified T> Any.cast(): T? {
    if (this is T) return this
    return null
}

fun <T> tryCatch(block: () -> T) {
    try {
        block()
    } catch (t: Throwable) {
        t.printStackTrace()
    }
}

fun <T> tryOrNull(block: () -> T): T? {
    return try {
        block.invoke()
    } catch (t: Throwable) {
        t.printStackTrace()
        null
    }
}
