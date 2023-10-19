package com.erictoader.handler.systemtag

import com.erictoader.event.SystemTagQueryEvent
import com.erictoader.handler.EventHandler
import com.erictoader.projection.SystemTagProjector
import com.erictoader.projection.exception.ProjectionException
import com.erictoader.repo.QuerySystemTagRepo
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QueryGetAllSystemTagHandler @Autowired constructor(
    private val systemTagRepo: QuerySystemTagRepo
) : EventHandler<SystemTagQueryEvent.GetAll> {


    private val projector = SystemTagProjector

    override fun handle(event: SystemTagQueryEvent.GetAll): Response<*> {
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