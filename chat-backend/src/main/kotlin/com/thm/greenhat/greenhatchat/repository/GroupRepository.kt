package com.thm.greenhat.greenhatchat.repository

import com.thm.greenhat.greenhatchat.model.GroupRequest
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : ReactiveCrudRepository<GroupRequest,String> { }