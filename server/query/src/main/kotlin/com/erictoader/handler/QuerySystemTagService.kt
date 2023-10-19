package com.erictoader.service

import com.erictoader.projection.SystemTagProjector
import com.erictoader.projection.exception.ProjectionException
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import com.erictoader.response.model.SystemTagModel
import com.erictoader.repo.QuerySystemTagRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QuerySystemTagService @Autowired constructor(
    private val systemTagRepo: QuerySystemTagRepo
) {
    private val projector = SystemTagProjector

    fun getAll(): Response<List<SystemTagModel>> {
        val systemTags = try {
            projector.get()
        } catch (e: ProjectionException) {
            systemTagRepo.findAll().also {
                projector.update(it)
            }
        }
        return Response.CollectionResponse(
            ResponseStatus.SUCCESS,
            systemTags.map { it.mapToModel() }
        )
    }
}