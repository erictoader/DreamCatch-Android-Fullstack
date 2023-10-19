package com.erictoader.event

sealed class DreamQueryEvent : QueryEvent {

    object GetAll : DreamQueryEvent()

    data class GetAllByUserId(val userId: Int): DreamQueryEvent()

    data class GetAllByUserIdGroupedByTag(val userId: Int): DreamQueryEvent()
}