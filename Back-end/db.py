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

    def __init__(self):
        load_dotenv()
        # 建立数据库连接
        self.conn = psycopg2.connect(
            host=os.getenv('DB_HOST'),
            database=os.getenv('DB_DATABASE'),
            user=os.getenv('DB_USER'),
            password=os.getenv('DB_PASSWORD')
        )

    def getRandomUserId(self):
        cur = self.conn.cursor()
        cur.execute("SELECT id FROM users ORDER BY RANDOM() LIMIT 1")
        result = cur.fetchone()
        cur.close()
        return result

    def getRandomOwnerId(self):
        cur = self.conn.cursor()
        cur.execute("SELECT id FROM users WHERE role='owner' ORDER BY RANDOM() LIMIT 1")
        result = cur.fetchone()
        cur.close()
        return result

    def getRandomObjectId(self):
        cur = self.conn.cursor()
        cur.execute("SELECT id FROM objects ORDER BY RANDOM() LIMIT 1")
        result = cur.fetchone()
        cur.close()
        return result

    def getOwnerIdByObjectId(self, objectId):
        cur = self.conn.cursor()
        cur.execute("SELECT ownerid FROM objects WHERE id='" + objectId + "'")
        result = cur.fetchone()
        cur.close()
        return result

    def getRandomObjectIdForCommand(self):
        cur = self.conn.cursor()
        cur.execute("SELECT id FROM objects WHERE id NOT IN (SELECT objectid as id FROM command) ORDER BY RANDOM() LIMIT 1")
        result = cur.fetchone()
        cur.close()
        return result

    def insertUserInitialize(self):
        cur = self.conn.cursor()
        user = User.user()
        user.setRole()
        user.setUsername()
        user.setPassword()
        user.setAddr()
        user_data = (user.role, user.username, user.password, user.addr)
        cur.execute("INSERT INTO users (role, username, password, addr) VALUES (%s, %s, %s, %s)", user_data)
        self.conn.commit()
        cur.close()
        return True

    def insertObjectInitialize(self):
        cur = self.conn.cursor()
        ownerid = self.getRandomOwnerId()
        object = Object.object()
        object.setTitle()
        object.setDateDispo()
        object.setPrix()
        object.setImgUrl()
        object_data = (ownerid, object.img_url, object.title, object.date_dispo, object.prix)
        cur.execute("INSERT INTO objects (ownerid, img_url, title, date_dispo, prix) VALUES (%s, %s, %s, %s, %s)", object_data)
        self.conn.commit()
        cur.close()
        return True

    def insertCommandInitialize(self):
        cur = self.conn.cursor()
        objectId = self.getRandomObjectId()
        # print(objectId[0])
        ownerId = self.getOwnerIdByObjectId(str(objectId[0]))
        commanderId = self.getRandomUserId()
        while(commanderId == ownerId) : commanderId = self.getRandomUserId()
        command_data = (objectId, commanderId, ownerId)
        cur.execute("INSERT INTO command (objectid, commanderid, ownerid) VALUES (%s, %s, %s)", command_data)
        self.conn.commit()
        cur.close()
        return True

    def insertPanierInitialize(self):
        cur = self.conn.cursor()
        objectId = self.getRandomObjectId()
        userId = self.getRandomUserId()
        panier_data = (objectId, userId)
        cur.execute("INSERT INTO panier (objectid, userid) VALUES (%s, %s)", panier_data)
        self.conn.commit()
        cur.close()
        return True

    def deleteAllUsers(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM users")
        self.conn.commit()
        cur.close()

    def deleteAllObjects(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM objects")
        self.conn.commit()
        cur.close()

    def deleteAllCommands(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM command")
        self.conn.commit()
        cur.close()

    def deleteAllPaniers(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM panier")
        self.conn.commit()
        cur.close()

    def deleteAllComments(self):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM comments")
        self.conn.commit()
        cur.close()

    def getAllUser(self):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getAllObjects(self):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects")
        rows = cur.fetchall()
        cur.close()
        return rows

    def login(self, username, password):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getPanierByUserId(self, userId):
        cur = self.conn.cursor()
        cur.execute("select * from panier where userid='" + str(userId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getCommandsReceivedByOwnerId(self, ownerId):
        cur = self.conn.cursor()
        cur.execute("select * from command where ownerid='" + str(ownerId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getObjectByObjectId(self, objectId):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE id='" + str(objectId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getObjectsByTitle(self, title):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE title ILIKE '%" + title + "%'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getUserByUserId(self, userId):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users WHERE id='" + str(userId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getUserByUsername(self, username):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users WHERE username ILIKE '%" + str(username) + "%'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getCommentsByObjectId(self, objectId):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM comments WHERE objectid='" + str(objectId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows
        
    def closeDB(self):
        self.conn.close






testDB = DB()
# testDB.deleteAllObjects()
# testDB.insertObjectInitialize()
# testDB.insertUserInitialize()
testDB.insertCommandInitialize()
# testDB.insertPanierInitialize()


# ceriland_objects: id, image, nom, date(dispo), prix, id_owner
# ceriland_users: id, role('owner', 'user'), nom, password, addresses
# ceriland_command: id, id_object, id_commander, id_owner
# ceriland_panier: id_object, id_user
# ceriland_comments: is_object, id_user


