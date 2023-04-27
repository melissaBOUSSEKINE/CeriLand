from flask import Flask, request
import json

import db
from object import Object
from user import User
from panier import Panier
from command import Command
from comment import Comment

app = Flask(__name__)

db = db.DB()

@app.route('/')
def hello():
    return 'Hello, world!'

# !!!!!! Faire attention de l'utiliser! Sinon tu vas attentre au moins une demi heur pour initialiser tous les données! !!!!!!!!!!! #
@app.route('/resetAllData')
def resetAllData():
    db.deleteAllPaniers()
    db.deleteAllCommands()
    db.deleteAllComments()
    db.deleteAllObjects()
    db.deleteAllUsers()
    return "All data has been reseted !"

@app.route('/initAllData')
def initializeAllData():
    nbUser = 1
    nbObjects = 1
    nbCommands = 1
    nbPanier = 1
    while (db.insertUserInitialize() and nbUser<500):
        nbUser += 1
    while (db.insertObjectInitialize() and nbObjects<10000):
        nbObjects += 1
    while (db.insertCommandInitialize() and nbCommands<230):
        nbCommands += 1
    while (db.insertPanierInitialize() and nbPanier<130):
        nbPanier += 1
    return "Add 100 data in users successfully ! \n Add 10000 data in objects successfully ! \n Add 230 data in command successfully ! \n Add 130 data in panier successfully ! \n"

@app.route('/users')
def getAllUser():
    users_result = db.getAllUser()
    listUser = []
    for user in users_result:
        userObj = User(user[0], user[1], user[2], user[3], user[4])
        listUser.append(userObj)
    json_users = json.dumps(listUser, default=lambda obj: obj.__dict__, indent=4)
    return json_users

@app.route('/objects')
def getAllObjects():
    objects_result = db.getAllObjects()
    listObjects = []
    for object in objects_result:
        objectObj = Object(object[0], object[1], object[2], object[3], object[4], object[5])
        listObjects.append(objectObj)
    json_objects = json.dumps(listObjects, default=lambda obj: obj.__dict__, indent=4)
    return json_objects

@app.route('/login', methods=['POST'])
def login():
    username = request.form.get('username')
    password = request.form.get('password')
    user_result = db.login(username, password)
    user = User(user_result[0][0], user_result[0][1], user_result[0][2], user_result[0][3], user_result[0][4])
    json_user = json.dumps(user, default=lambda obj: obj.__dict__, indent=4)
    return json_user

@app.route('/user/panier/<int:userId>')
def getPanierByUserId(userId):
    userPanier_result = db.getPanierByUserId(userId)
    listUserPanier = []
    for item in userPanier_result:
        userPanier = Panier(item[0], item[1], item[2])
        listUserPanier.append(userPanier)
    json_userPanier = json.dumps(listUserPanier, default=lambda obj: obj.__dict__, indent=4)
    return json_userPanier

@app.route('/user/commands_received/<int:ownerId>')
def getCommandsReceivedByOwnerId(ownerId):
    ownerCommandeReceive_result = db.getCommandsReceivedByOwnerId(ownerId)
    listownerCommandeReceive = []
    for item in ownerCommandeReceive_result:
        ownerCommandeReceive = Command(item[0], item[1], item[2], item[3])
        listownerCommandeReceive.append(ownerCommandeReceive)
    json_ownerCommandeReceive = json.dumps(listownerCommandeReceive, default=lambda obj: obj.__dict__, indent=4)
    return json_ownerCommandeReceive

@app.route('/user/<int:userId>')
def getUserByUserId(userId):
    user_result = db.getUserByUserId(userId)
    print(user_result)
    user = User(user_result[0][0], user_result[0][1], user_result[0][2], user_result[0][3], user_result[0][4])
    json_user = json.dumps(user, default=lambda obj: obj.__dict__, indent=4)
    return json_user

@app.route('/users/<string:username>')
def getUserByUsername(username):
    users_result = db.getUserByUsername(username)
    listUserCloset = []
    for item in users_result:
        user = User(item[0], item[1], item[2], item[3], item[4])
        listUserCloset.append(user)
    json_users = json.dumps(listUserCloset, default=lambda obj: obj.__dict__, indent=4)
    return json_users

@app.route('/object/<int:objectId>')
def getObjectByObjectId(objectId):
    object_result = db.getObjectByObjectId(objectId)
    object = Object(object_result[0][0], object_result[0][1], object_result[0][2], object_result[0][3], object_result[0][4], object_result[0][5])
    json_object = json.dumps(object, default=lambda obj: obj.__dict__, indent=4)
    return json_object

@app.route('/objects/<string:title>')
def getObjectsByTitle(title):
    objects_result = db.getObjectsByTitle(title)
    listObjectsCloset = []
    for item in objects_result:
        object = Object(item[0], item[1], item[2], item[3], item[4], item[5])
        listObjectsCloset.append(object)
    json_users = json.dumps(listObjectsCloset, default=lambda obj: obj.__dict__, indent=4)
    return json_users

@app.route('/object/comments/<int:objectId>')
def getCommentByObjectId(objectId):
    comments_result = db.getCommentsByObjectId(objectId)
    commentsOfObject = []
    for item in comments_result:
        comment = Comment(item[0], item[1], item[2], item[3])
        commentsOfObject.append(comment)
    json_commentOfObject = json.dumps(commentsOfObject, default=lambda obj: obj.__dict__, indent=4)
    return json_commentOfObject


if __name__ == '__main__':
    app.run(debug=True)

