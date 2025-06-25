package com.example.chatin.Customs

import java.util.UUID

class RandomId {
    val existingUserIds = setOf("abc-123", "def-456") // Replace with real IDs from DB or state
    val uniqueId = generateUniqueId(existingUserIds)



    fun generateUniqueId(existingIds: Set<String>): String {


    var newId: String
    do {
        newId = UUID.randomUUID().toString()
    } while (existingIds.contains(newId))
    return newId
}
}