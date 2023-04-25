import psycopg2
from dotenv import load_dotenv
import os
import requests
from PIL import Image
import io
import random
import string
from datetime import datetime, timedelta
import numpy as np
import enum

class TypeObject(enum.Enum):
    ELECTRONIC = ["electronic", "electronic1", "electronic2", "electronic3", "electronic4", "electronic5", "electronic6", "electronic7"]
    JEWELRY = ["jewelry", "jewelry1", "jewelry2", "jewelry3"]
    BAG = ["bag", "bag1"]
    CAR = ["car", "car1", "car2", "car3", "car4", "car5"],
    CLOTHE = ["clothes", "clothes1", "clothes2", "clothes3", "clothes4", "clothes5", "clothes6", "clothes7"],
    CONSTRUCTION = ["construction", "construction1"],
    HOUSE = ["maison", "maison1", "maison2", "maison3", "maison4", "maison5", "maison6", "maison7"],
    PC = ["pc", "pc1", "pc2", "pc3"],
    PHONE = ["phone", "phone1", "phone2", "phone3"],
    WATCH = ["watch1", "watch1", "watch2", "watch3", ]

class Object:

    sizeObject = ["petit", "moyenne", "grand"]

    color = ["blanc", "noir", "pourpre", "rouge", "orange", "jaune", "vert", "bleu", "violet", "ivoire", "crème", "beige", "rose", "kaki", "brun", "marron", "bordeaux"]

    quality = ["neuf", "bon état", "état moyen"]

    object = ""

    def __init__(self, id, ownerid, img_url, title, date_dispo, prix):
        self.id = id
        self.ownerId = ownerid
        self.img_url = img_url
        self.title = title
        self.date_dispo = date_dispo
        self.prix = prix
    
    @classmethod
    def object(cls):
        return cls(None, None, None, None, None, None)

    def setTitle(self):
        self.object = random.choice(random.choice(list(TypeObject)).value)
        while(len(self.object) == 1 or isinstance(self.object, str))!=True: 
            self.object = random.choice(random.choice(list(TypeObject)).value)
        titleExtension = ''.join(random.choice(string.ascii_letters + string.digits) for _ in range(10))
        sizeIndex = random.randint(0, len(self.sizeObject) -1)
        colorIndex = random.randint(0, len(self.color) - 1)
        qualityIndex = random.randint(0, len(self.quality) - 1)
        size = self.sizeObject[sizeIndex]
        color = self.color[colorIndex]
        quality = self.quality[qualityIndex]
        self.title = size + " " + self.object + titleExtension + " " + color + ", " + quality

    def setDateDispo(self):
        today = datetime.now()
        future_date = today + timedelta(days=random.randint(10, 30))
        days = random.randint(20, 100)
        end_date = future_date + timedelta(days=days)
        start_date_str = future_date.strftime('%Y-%m-%d')
        end_date_str = end_date.strftime('%Y-%m-%d')
        time_period = f"{start_date_str} - {end_date_str}"
        self.date_dispo = time_period

    def setPrix(self):
        self.prix = round(random.uniform(10, 999), 2)

    def setImgUrl(self):
        self.img_url = "../../images/" + self.object + ".jpg"

# object = Object()
# object.setTitle()