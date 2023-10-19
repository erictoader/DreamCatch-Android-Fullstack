package com.erictoader.handler.dream

import com.erictoader.event.DreamQueryEvent
import com.erictoader.handler.EventHandler
import com.erictoader.projection.DreamProjector
import com.erictoader.projection.exception.ProjectionException
import com.erictoader.repo.QueryDreamRepo
import com.erictoader.response.Response
import com.erictoader.response.ResponseStatus
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QueryGetAllDreamHandler @Autowired constructor(
    private val dreamRepo: QueryDreamRepo
) : EventHandler<DreamQueryEvent.GetAll> {

    private val dreamProjector = DreamProjector

    override fun handle(event: DreamQueryEvent.GetAll): Response<*> {
        val dreams = try {
            dreamProjector.get()
        } catch (e: ProjectionException) {
            dreamRepo.findAll().also {
                dreamProjector.update(it)
            }
        }

        return Response.CollectionResponse(
            ResponseStatus.SUCCESS,
            dreams.map { it.mapToModel() }
        )
    }
}