package com.erictoader.service

import com.erictoader.TAG_SEPARATOR
import com.erictoader.projection.DreamProjector
import com.erictoader.projection.SystemTagProjector
import com.erictoader.projection.exception.ProjectionException
import com.erictoader.repo.QueryDreamRepo
import com.erictoader.repo.QuerySystemTagRepo
import com.erictoader.response.Response
import com.erictoader.response.Response.CollectionResponse
import com.erictoader.response.ResponseStatus.SUCCESS
import com.erictoader.response.model.DreamModel
import com.erictoader.response.model.DreamModuleModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class QueryDreamService @Autowired constructor(
    private val dreamRepo: QueryDreamRepo,
    private val systemTagRepo: QuerySystemTagRepo
) {
    private val dreamProjector = DreamProjector
    private val systemTagProjector = SystemTagProjector

    fun getAll(): Response<List<DreamModel>> {
        val dreams = try {
            dreamProjector.get()
        } catch (e: ProjectionException) {
            dreamRepo.findAll().also {
                dreamProjector.update(it)
            }
        }

        return CollectionResponse(
            SUCCESS,
            dreams.map { it.mapToModel() }
        )
    }

    fun getAllByUserId(userId: Int): Response<List<DreamModel>> {
        val dreams = try {
            dreamProjector.get().filter { it.userId == userId }
        } catch (e: ProjectionException) {
            dreamProjector.update(dreamRepo.findAll())
            dreamRepo.findAllByUserId(userId)
        }

        return CollectionResponse(
            SUCCESS,
            dreams.map { it.mapToModel() }
        )
    }

    fun getAllByUserIdGroupedByTag(userId: Int): Response<List<DreamModuleModel>> {
        val systemTagsStringSet = try {
            systemTagProjector.get()
        } catch (e: ProjectionException) {
            systemTagRepo.findAll()
        }.map { it.name }.toSet()

        val dreams = try {
            dreamProjector.get().filter { it.userId == userId }
        } catch (e: ProjectionException) {
            dreamProjector.update(dreamRepo.findAll())
            dreamRepo.findAllByUserId(userId)
        }

        val modulesMap = mutableMapOf<String, MutableList<DreamModel>>()

        dreams.forEach { dream ->
            unpackTags(dream.tags).forEach { tag ->
                val module = modulesMap.getOrPut(tag) { mutableListOf() }
                module.add(dream.mapToModel())
            }
        }

        return CollectionResponse(
            SUCCESS,
            modulesMap
                .map { (tag, items) ->
                    DreamModuleModel(tag, items)
                }
                .sortedWith(
                    systemTagsFirstOthersSorted(
                        systemTagsStringSet
                    )
                )
        )
    }

    private fun systemTagsFirstOthersSorted(systemTags: Set<String>): Comparator<DreamModuleModel> =
        compareBy(
            { !systemTags.contains(it.tag) },
            { systemTags.indexOf(it.tag) },
            { it.tag.lowercase() }
        )


    private fun unpackTags(packedTags: String): List<String> = packedTags.split(TAG_SEPARATOR)
}