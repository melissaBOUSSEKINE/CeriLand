from dotenv import load_dotenv
import random
import string
from datetime import datetime, timedelta
import enum
import time
import datetime
import random

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

    def __init__(self, id, ownerid, img_url, title, date_dispo_start, date_dispo_end, prix, res_status, res_by):
        self.id = id
        self.ownerId = ownerid
        self.img_url = img_url
        self.title = title
        self.date_dispo_start = date_dispo_start
        self.date_dispo_end = date_dispo_end
        self.prix = prix
        self.res_status = res_status
        self.res_by = res_by
    
    @classmethod
    def object(cls):
        return cls(None, None, None, None, None, None, None, None, None)

    def setResStatus(self):
        self.res_status = "1"

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

    def setDateDispoStart(self):
        today = datetime.date.today()
        days = random.randint(15, 30)
        delta = datetime.timedelta(days=days)
        future_date = today + delta
        future_datetime = datetime.datetime.combine(future_date, datetime.time.min)
        future_timestamp = time.mktime(future_datetime.timetuple())
        print(future_timestamp)
        self.date_dispo_start = future_timestamp

    def setDateDispoEnd(self):
        today = datetime.date.today()
        days = random.randint(30, 300)
        delta = datetime.timedelta(days=days)
        future_date = today + delta
        future_datetime = datetime.datetime.combine(future_date, datetime.time.min)
        future_timestamp = time.mktime(future_datetime.timetuple())
        print(future_timestamp)
        self.date_dispo_end = future_timestamp

    def setPrix(self):
        self.prix = round(random.uniform(10, 999), 2)

    def setImgUrl(self):
        self.img_url = "..\\..\\images\\" + self.object + ".jpg"

object = Object.object()
object.setDateDispoStart()
object.setDateDispoEnd()