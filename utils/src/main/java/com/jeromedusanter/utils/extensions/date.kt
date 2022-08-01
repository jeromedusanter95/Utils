package com.jeromedusanter.utils.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toFormattedString(pattern: String): String {
    return this.format(DateTimeFormatter.ofPattern(pattern))
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toISOFormattedString(): String? {
    return tryOrNull { format(DateTimeFormatter.ISO_DATE_TIME) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toZonedString(): String? {
    return tryOrNull {
        ZonedDateTime.of(this, ZoneId.systemDefault())
            .format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toISOLocalDateTime(): LocalDateTime? {
    return tryOrNull { LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toISOLocalTime(): LocalTime? {
    return tryOrNull { LocalTime.parse(this, DateTimeFormatter.ISO_TIME) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalTime.toISOFormattedString(): String? {
    return tryOrNull { format(DateTimeFormatter.ISO_TIME) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalTime(pattern: String): LocalTime? {
    return tryOrNull { LocalTime.parse(this, DateTimeFormatter.ofPattern(pattern)) }
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime?.orNow(): LocalDateTime {
    return this ?: LocalDateTime.now()
}