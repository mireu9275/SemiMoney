package kr.eme.plugin.objects

import java.util.*

data class User(
    val uuid: UUID,
    val name: String = "",
    val money: Int = 0
)
