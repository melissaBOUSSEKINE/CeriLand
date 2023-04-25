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

from object import Object
from user import User


class DB:

    # typeObject = {
    #     "electronic": ["electronic", "electronic1", "electronic2", "electronic3", "electronic4", "electronic5", "electronic6", "electronic7"], 
    #     "jewelry": ["jewelry", "jewelry1", "jewelry2", "jewelry3"], 
    #     "bag": ["bag", "bag1"],
    #     "car": ["car", "car1", "car2", "car3", "car4", "car5"],
    #     "clothes": ["clothes", "clothes1", "clothes2", "clothes3", "clothes4", "clothes5", "clothes6", "clothes7"],
    #     "construction": ["construction", "construction1"],
    #     "maison": ["maison", "maison1", "maison2", "maison3", "maison4", "maison5", "maison6", "maison7"],
    #     "pc": ["pc", "pc1", "pc2", "pc3"],
    #     "phone": ["phone", "phone1", "phone2", "phone3"],
    #     "watch": ["watch1", "watch1", "watch2", "watch3", ]
    # }

    def __init__(self):
        load_dotenv()
        # 建立数据库连接
        self.conn = psycopg2.connect(
            host=os.getenv('DB_HOST'),
            database=os.getenv('DB_DATABASE'),
            user=os.getenv('DB_USER'),
            password=os.getenv('DB_PASSWORD')
        )

    # def __init__(self):

    def searchAllUser(self):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users")
        rows = cur.fetchall()
        cur.close()
        return rows

    def insertUserInitialize(self):
        cur = self.conn.cursor()
        user = User()
        user.setRole()
        user.setUsername()
        user.setPassword()
        user.setAddr()
        user_data = (user.role, user.username, user.password, user.addr)
        cur.execute("INSERT INTO users (role, username, password, addr) VALUES (%s, %s, %s, %s)", user_data)
        self.conn.commit()
        cur.close()
        return "Add user seccessfully ! "

    def deleteAllUsers(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM users")
        self.conn.commit()
        cur.close()

    def searchAllObjects(self):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects")
        rows = cur.fetchall()
        cur.close()
        print(rows)
        return rows

    def getRandomUserId(self):
        cur = self.conn.cursor()
        cur.execute("SELECT id FROM users WHERE role='owner' ORDER BY RANDOM() LIMIT 1")
        result = cur.fetchone()
        cur.close()
        return result

    def insertObjectInitialize(self):
        cur = self.conn.cursor()
        ownerid = self.getRandomUserId()
        object = Object()
        object.setTitle()
        object.setDateDispo()
        object.setPrix()
        object.setImgUrl()
        object_data = (ownerid, object.img_url, object.title, object.date_dispo, object.prix)
        cur.execute("INSERT INTO objects (ownerid, img_url, title, date_dispo, prix) VALUES (%s, %s, %s, %s, %s)", object_data)
        self.conn.commit()
        cur.close()
        return "Add user seccessfully ! "

    def deleteAllObjects(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM objects")
        self.conn.commit()
        cur.close()
        
    def closeDB(self):
        self.conn.close






testDB = DB()
# # testDB.insertUser("user", "test555", "test555", "666 rue 666")
# testDB.insertObject()
testDB.insertUserInitialize()



# ceriland_objects: id, image, nom, date(dispo), prix, id_owner
# ceriland_users: id, role('owner', 'user'), nom, password, addresses
# ceriland_command: id, id_object, id_commander, id_owner
# ceriland_panier: id_object, id_user
# ceriland_comments: is_object, id_user


