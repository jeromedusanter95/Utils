package com.jeromedusanter.utils.extensions

import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import java.util.concurrent.TimeUnit

/**
 * params [inputData]
 * params [networkType]
 * params [backoffPolicy]
 * params [durationBetweenEachRetryInSeconds] : If the [backoffPolicy] is EXPONENTIAL, this value will increase for each retry
 **/

inline fun <reified T : CoroutineWorker> createOneTimeWorkRequest(
    inputData: Data = Data.Builder().build(),
    networkType: NetworkType = NetworkType.CONNECTED,
    backoffPolicy: BackoffPolicy = BackoffPolicy.LINEAR,
    durationBetweenEachRetryInSeconds: Long = 300, //5min
    initialDelayInSeconds: Long = 0,
): OneTimeWorkRequest {
    return OneTimeWorkRequest.Builder(T::class.java)
        .addTag(T::class.java.name)
        .setInputData(inputData)
        .setInitialDelay(initialDelayInSeconds, TimeUnit.SECONDS)
        .setConstraints(Constraints.Builder().setRequiredNetworkType(networkType).build())
        .setBackoffCriteria(
            backoffPolicy,
            durationBetweenEachRetryInSeconds,
            TimeUnit.SECONDS
        )
        .build()
}