class Command:
    def __init__(self, id, objectId, commanderId, ownerId) -> None:
        self.id = id
        self.objectId = objectId
        self.commanderId = commanderId
        self.ownerId = ownerId