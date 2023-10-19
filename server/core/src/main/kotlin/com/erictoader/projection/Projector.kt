package com.erictoader.projection

import com.erictoader.entity.EntityMapper
import com.erictoader.projection.exception.OutdatedProjectionAccess
import com.erictoader.projection.exception.ProjectionEmptyException
import java.util.concurrent.atomic.AtomicBoolean

abstract class Projector<D : EntityMapper<*>> {

    private val projection = HashMap<Long, List<D>>()

    private val isUpToDate = AtomicBoolean(false)

    fun get(): List<D> {
        if (!isUpToDate.get()) throw OutdatedProjectionAccess()
        if (projection.isEmpty()) throw ProjectionEmptyException()
        return projection.values.last()
    }

    fun update(data: List<D>) {
        projection[System.currentTimeMillis()] = data
        isUpToDate.set(true)
    }

    /**
     * Only to be called when data has been modified
     * Will invalidate the most recent projection and force queries to fetch from the database
     */
    fun deprecate() = isUpToDate.set(false)
}