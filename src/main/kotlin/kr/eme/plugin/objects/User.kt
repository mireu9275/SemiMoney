package kr.eme.plugin.objects

import java.util.UUID

data class User(
    val name: String,
    val uuid: UUID,
    val money: Int
)
