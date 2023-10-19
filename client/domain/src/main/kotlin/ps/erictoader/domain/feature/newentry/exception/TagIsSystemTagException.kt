package ps.erictoader.domain.feature.newentry.exception

class TagIsSystemTagException(systemTagName: String) :
    Exception("Attempted to delete System Tag $systemTagName")
