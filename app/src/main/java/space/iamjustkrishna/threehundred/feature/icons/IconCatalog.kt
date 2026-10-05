package space.iamjustkrishna.threehundred.feature.icons

data class IconGlyph(
    val id: String,
    val family: String,
    val title: String = id.substringAfter('_').replace('_', ' ').replaceFirstChar { it.uppercase() }
)

private val iconFamilies = mapOf(
    "core" to listOf("phone", "messages", "mail", "camera", "gallery", "browser", "maps", "contacts", "clock", "calendar", "calculator", "files", "settings", "security", "play", "music"),
    "focus" to listOf("fitness", "health", "timer", "notes", "tasks", "journal", "wallet", "weather", "travel", "home", "work", "study", "book", "podcast", "photo", "voice"),
    "system" to listOf("wifi", "bluetooth", "battery", "flashlight", "volume", "brightness", "airplane", "location", "lock", "unlock", "shield", "sync", "download", "upload", "share", "search"),
    "forge" to listOf("lambda", "helmet", "spear", "shield", "laurel", "torch", "column", "crest", "anvil", "hammer", "standard", "gate", "compass", "orbit", "medallion", "vault"),
    "social" to listOf("people", "profile", "community", "event", "chat", "call", "video", "stream", "follow", "heart", "bookmark", "comment", "send", "link", "qr", "inbox"),
    "commerce" to listOf("store", "cart", "receipt", "card", "bank", "chart", "coin", "gift", "ticket", "package", "delivery", "support", "star", "crown", "vanguard", "restore")
)

val IconCatalog: List<IconGlyph> = iconFamilies.flatMap { (family, names) ->
    names.map { IconGlyph("${family}_$it", family) }
}
