package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.Group
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface GroupRepository : ReactiveCrudRepository<Group,UUID> { }