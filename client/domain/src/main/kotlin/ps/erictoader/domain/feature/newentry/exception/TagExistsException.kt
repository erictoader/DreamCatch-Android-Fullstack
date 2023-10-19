package ps.erictoader.domain.feature.newentry.exception

class TagExistsException(val existingTagName: String) :
    Exception("Tag $existingTagName already exists")
