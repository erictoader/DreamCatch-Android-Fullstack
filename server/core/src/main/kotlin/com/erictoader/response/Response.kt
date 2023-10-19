package com.erictoader.response

import com.erictoader.response.model.Model

abstract class Response<T>(
    open val code: Int,
    open val message: String,
    open val data: T?
) {
    data class SingleResponse<M : Model>(
        override val code: Int,
        override val message: String,
        override val data: M? = null
    ) : Response<M>(
        code = code,
        message = message,
        data = data
    ) {
        constructor(status: Pair<Int, String>, data: M? = null) : this(status.first, status.second, data)
    }

    data class CollectionResponse<M : Model>(
        override val code: Int,
        override val message: String,
        override val data: List<M>? = null
    ) : Response<List<M>>(
        code = code,
        message = message,
        data = data
    ) {
        constructor(status: Pair<Int, String>, data: List<M>? = null) : this(status.first, status.second, data)
    }
}

