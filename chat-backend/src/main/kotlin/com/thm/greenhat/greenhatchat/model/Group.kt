package com.thm.greenhat.greenhatchat.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document("group")
data class Group(
        @Id var id:Int,
        var name:String,
        var admin:UUID,
        var users:List<UUID>
)