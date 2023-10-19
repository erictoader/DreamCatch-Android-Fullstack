package ps.erictoader.data.model

interface ModelMapper<M> {

    fun mapToDomainModel(): M
}
