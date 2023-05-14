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
        object.setDateDispoStart()
        object.setDateDispoEnd()
        object.setPrix()
        object.setImgUrl()
        object.setResStatus()
        object_data = (ownerid, object.img_url, object.title, object.date_dispo_start, object.date_dispo_end, object.prix, object.res_status, object.res_status)
        cur.execute("INSERT INTO objects (ownerid, img_url, title, date_dispo_start, date_dispo_end, prix, res_status, res_by) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)", object_data)
        self.conn.commit()
        cur.close()
        return True

    def insertCommandInitialize(self):
        cur = self.conn.cursor()
        objectId = self.getRandomObjectId()
        # print(objectId[0])
        # if objectId != None:
        ownerId = self.getOwnerIdByObjectId(str(objectId[0]))[0][0]
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

    def insertToPanier(self, objectId, userId):
        checkIsExisted = testDB.checkObjectInPanier(objectId, userId)[0][0]
        if checkIsExisted == 0:
            cur = self.conn.cursor()
            panier_data = (objectId, userId)
            cur.execute("INSERT INTO panier (objectid, userid) VALUES (%s, %s)", panier_data)
            self.conn.commit()
            cur.close()
            return {
                "error_code": 0,
                "res_message": "Ajouter succèss! "
            }
        return {
            "error_code": 1,
            "res_message": "L'object exist déjà dans le panier de user" + userId + "! "
        }

    def removeFromPanier(self, objectId, userId):
        checkIsExisted = testDB.checkObjectInPanier(objectId, userId)[0][0]
        if checkIsExisted == 1:
            cur = self.conn.cursor()
            cur.execute("DELETE FROM panier WHERE objectid='" + str(objectId) + "' AND userid='" + str(userId) + "'")
            self.conn.commit()
            cur.close()
            return {
                "error_code": 0,
                "res_message": "Remove from panier succèss! "
            }
        return {
            "error_code": 1,
            "res_message": "L'object n'exist pas dans le panier de user" + userId + "! "
        }
        
    def removeAllObjectsFromPanier(self, userId):
            cur = self.conn.cursor()
            cur.execute("DELETE FROM panier WHERE userid='" + str(userId) + "'")
            self.conn.commit()
            cur.close()
            return {
                "error_code": 0,
                "res_message": "Remove from panier succèss! "
            }
       
        

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
        cur.execute("SELECT * FROM objects WHERE NOT res_status='0' AND NOT ownerid IS NULL")
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

    def checkObjectInPanier(self, objectid, userid):
        cur = self.conn.cursor()
        cur.execute("select count(*) from panier where objectid='" + str(objectid) + "' AND userid='" + str(userid) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getCommandsReceivedByOwnerId(self, ownerId):
        cur = self.conn.cursor()
        cur.execute("select * from command where ownerid='" + str(ownerId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getCommandsSentByCommanderId(self, commanderId):
        cur = self.conn.cursor()
        cur.execute("select * from command where commanderid='" + str(commanderId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def deleteCommand(self, objectId, commanderId):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM command WHERE objectid='" + str(objectId) + "' AND commanderid='" + str(commanderId) + "'")
        self.conn.commit()
        cur.close()
        return True

    def checkUserAlreadyCommandObject(self, objectId, commanderId):
        cur = self.conn.cursor()
        cur.execute("select count(*) from command where objectid='" + str(objectId) + "' and commanderid='" + str(commanderId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def addIntoCommand(self, objectId, commanderId):
        cur = self.conn.cursor()
        ownerId = self.getOwnerIdByObjectId(objectId)[0][0]
        command_data = (objectId, commanderId, ownerId)
        cur.execute("INSERT INTO command (objectid, commanderid, ownerid) VALUES (%s, %s, %s)", command_data)
        self.conn.commit()
        cur.close()
        return True
    
    def getCommanderbyObjectId(self, objectId):
        cur = self.conn.cursor()
        cur.execute("SELECT c.* FROM command c INNER JOIN objects o ON c.objectId=o.id WHERE o.id='"+ str(objectId)+ "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getCommandsValidedByCommandertId(self, userId):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE res_status='0' AND res_by='" + str(userId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def updateObjectResStatus(self, ownerid, objectId, commanderId):
        print(ownerid)
        print(self.getOwnerIdByObjectId(objectId)[0][0])
        if str(ownerid) == str(self.getOwnerIdByObjectId(objectId)[0][0]):
            cur = self.conn.cursor()
            cur.execute("DELETE FROM command WHERE objectid='" + str(objectId) + "'")
            cur.execute("UPDATE objects SET res_status='0',res_by='" + str(commanderId) + "' WHERE id='" + str(objectId) + "'")
            self.conn.commit()
            cur.close()
            return True
        return False

    def getObjectByObjectId(self, objectId):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE id='" + str(objectId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows
    
    def getAllObjectById(self, ownerId):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE ownerid='" + str(ownerId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getObjectsByTitle(self, title):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE title ILIKE '%" + title + "%'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def getObjectsByDateDispo(self, date):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM objects WHERE CAST(date_dispo_start AS float)<=" + str(date) + " AND CAST(date_dispo_end AS float)>=" + str(date) + "")
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

    def addCommentToObject(self, objectId, userId, comment):
        cur = self.conn.cursor()
        comment_data = (objectId, userId, comment)
        cur.execute("INSERT INTO comments (objectid, userid, comment) VALUES (%s, %s, %s)", comment_data)
        self.conn.commit()
        cur.close()
        return True

    def deleteCommentToObject(self, commentId):
        cur = self.conn.cursor()
        cur.execute("DELETE FROM comments WHERE id='" + commentId + "'")
        self.conn.commit()
        cur.close()
        return True
        
    def getOwnerIdByObjectId(self, objectId):
        cur = self.conn.cursor()
        cur.execute("SELECT ownerid FROM objects WHERE id='" + str(objectId) + "'")
        rows = cur.fetchall()
        cur.close()
        return rows

    def closeDB(self):
        self.conn.close






testDB = DB()
# testDB.deleteAllObjects()
# testDB.insertObjectInitialize()
# testDB.insertUserInitialize()
# testDB.insertCommandInitialize()
# testDB.insertPanierInitialize()
# print(testDB.checkObjectInPanier(93323, 43113)[0][0])
# print(testDB.getOwnerIdByObjectId(98936)[0][0])


# ceriland_objects: id, image, nom, date(dispo), prix, id_owner
# ceriland_users: id, role('owner', 'user'), nom, password, addresses
# ceriland_command: id, id_object, id_commander, id_owner
# ceriland_panier: id_object, id_user
# ceriland_comments: is_object, id_user


