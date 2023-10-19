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
class QueryGetAllByUserIdDreamHandler @Autowired constructor(
    private val dreamRepo: QueryDreamRepo
) : EventHandler<DreamQueryEvent.GetAllByUserId> {

    private val dreamProjector = DreamProjector

    override fun handle(event: DreamQueryEvent.GetAllByUserId): Response<*> {
        val dreams = try {
            dreamProjector.get().filter { it.userId == event.userId }
        } catch (e: ProjectionException) {
            dreamProjector.update(dreamRepo.findAll())
            dreamRepo.findAllByUserId(event.userId)
        }

        return Response.CollectionResponse(
            ResponseStatus.SUCCESS,
            dreams.map { it.mapToModel() }
        )
    }
}