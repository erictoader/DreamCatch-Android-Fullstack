package com.erictoader.event

sealed class SystemTagQueryEvent : QueryEvent {

    object GetAll : SystemTagQueryEvent()
}