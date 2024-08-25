package com.uvg.lab07

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.Date
import java.util.Locale

val SpaceCadet = Color(0xFF2E2D4D)
val DarkSpringGreen = Color(0xFF337357)
val Asparagus = Color(0xFF6D9F71)
val Eggshell = Color(0xFFE4E3D3)

val InvertedSpaceCadet = Color(0xFFFFFFFF)
val InvertedDarkSpringGreen = Color(0xFFFFFDD0)
val InvertedAsparagus = Color(0xFF5B3A29)
val InvertedEggshell = Color(0xFF333333)


fun NotificationType.getIcon(): ImageVector {
    return when (this) {
        NotificationType.GENERAL -> Icons.Default.Notifications
        NotificationType.NEW_POST -> Icons.Default.DateRange
        NotificationType.NEW_MESSAGE -> Icons.Default.Info
        NotificationType.NEW_LIKE -> Icons.Default.ThumbUp
    }
}

fun NotificationType.getColor(): Color {
    return when (this) {
        NotificationType.GENERAL -> SpaceCadet
        NotificationType.NEW_POST -> DarkSpringGreen
        NotificationType.NEW_MESSAGE -> Asparagus
        NotificationType.NEW_LIKE -> Eggshell
    }
}

fun NotificationType.getOnColor(): Color {
    return when (this) {
        NotificationType.GENERAL -> InvertedSpaceCadet
        NotificationType.NEW_POST -> InvertedDarkSpringGreen
        NotificationType.NEW_MESSAGE -> InvertedAsparagus
        NotificationType.NEW_LIKE -> InvertedEggshell
    }
}


enum class NotificationType {
    GENERAL,
    NEW_POST,
    NEW_MESSAGE,
    NEW_LIKE
}

data class Notification(
    val id: Int,
    val title: String,
    val body: String,
    val sendAt: String,
    val type: NotificationType
)

fun LocalDateTime.toDate(): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale("es", "ES")) // Spanish locale
    return dateFormat.format(Date())}

fun generateFakeNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()
    val titles = listOf(
        "Nueva versión disponible",
        "Nuevo post de Juan",
        "Mensaje de Maria",
        "Te ha gustado una publicación"
    )
    val bodies = listOf(
        "La aplicación ha sido actualizada a v1.0.2. Ve a la PlayStore y actualízala!",
        "Te han etiquetado en un nuevo post. ¡Míralo ahora!",
        "No te olvides de asistir a esta capacitación mañana, a las 6pm, en el Intecap.",
        "A Juan le ha gustado tu publicación. ¡Revisa tu perfil!"
    )
    val types = NotificationType.entries.toTypedArray()

    val currentDate = LocalDate.now()
    for (i in 1..50) {
        val daysAgo = (0..10).random()
        val hoursAgo = (0..23).random()
        val minutesAgo = (0..59).random()
        val sendAt = LocalDateTime.of(currentDate.minusDays(daysAgo.toLong()), LocalTime.of(hoursAgo, minutesAgo)).toDate()
        notifications.add(
            Notification(
                id = i,
                title = titles.random(),
                body = bodies.random(),
                sendAt = sendAt,
                type = types.random()
            )
        )
    }
    return notifications
}
