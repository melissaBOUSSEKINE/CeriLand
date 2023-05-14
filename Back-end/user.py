import random
import string

class User:

    listFirstName = [
        "Jennifer",
        "Natalie",
        "John",
        "Samuel",
        "Jasmine",
        "Patricia",
        "Michael",
        "Elijah",
        "Justin",
        "Paula",
        "Ella",
        "Eryn",
        "Heidi",
        "Daniel",
        "Omari",
        "Chloe",
        "Jamal",
        "Elise",
        "Renee",
        "Edith",
    ]

    listLastName = [
        " Johnson",
        " Robinson",
        " Ferguson",
        " Patterson",
        " James",
        " Hamilton",
        " Hensley",
        " Fisher",
        " Wright",
        " Reyes",
        " Durham",
        " Armstrong",
        " Mason",
        " Mooney",
        " Jimenez",
        " Watson",
        " Mcneil",
        " Beck",
        " Burnett",
        " Mack",
    ]

    villesList = [
        "Agen","Aix-en-Provence","Ajaccio","Albi","Ales",
        "Alfortville","Amiens","Angers","Anglet","Angouleme",
        "Annecy","Annemasse","Antibes","Antony","Argenteuil",
        "Arles","Arras","Asnieres-sur-Seine","Athis-Mons","Aubagne",
        "Aubervilliers","Aulnay-sous-Bois","Auxerre","Avignon","Bagneux",
        "Bagnolet","Baie-Mahault","Bastia","Bayonne","Beauvais",
        "Belfort","Besancon","Beziers","Blois","Bobigny",
        "Bondy","Bordeaux","Boulogne-Billancourt","Boulogne-sur-Mer","Bourg-en-Bresse",
        "Bourges","Brest","Brive-la-Gaillarde","Bron","Cachan",
        "Caen","Cagnes-sur-Mer","Calais","Caluire-et-Cuire","Cambrai",
        "Cannes","Carcassonne","Castres","Cayenne"
    ]
        
    wayList = [
        "chemin","route","rue","avenue","impasse"
    ]
        
    wayNameList = [
        "de la forge","de la mairie","du pont neuf","Thiers","Champs Elysées"
    ]

    def __init__(self, id, role, username, password, addr):
        self.id = id
        self.role = role
        self.username = username
        self.password = password
        self.addr = addr

    @classmethod
    def user(cls):
        return cls(None, None, None, None, None)

    def setRole(self):
        self.role = random.choice(['user', 'owner'])

    def setUsername(self):
        firstName = random.choice(self.listFirstName)
        lastName = random.choice(self.listLastName)
        self.username = firstName + lastName

    def setPassword(self):
        self.password = ''.join(random.choice(string.ascii_letters + string.digits) for _ in range(15))

    def setAddr(self):
        num = random.randint(0, 99)
        wayType = random.choice(self.wayList)
        wayName = random.choice(self.wayNameList)
        city = random.choice(self.villesList)
        self.addr = str(num) + " " + wayType + " " + wayName + ", " + city