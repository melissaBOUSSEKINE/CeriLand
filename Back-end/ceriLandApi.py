from flask import Flask

import db

app = Flask(__name__)

db = db.DB()

@app.route('/')
def hello():
    return 'Hello, world!'

@app.route('/resetAllData')
def resetAllData():
    db.deleteAllComments()
    db.deleteAllPaniers()
    db.deleteAllCommands()
    db.deleteAllObjects()
    db.deleteAllUsers()
    return "All data has been reseted !"

@app.route('/initAllData')
def initializeAllData():
    nbUser = 1
    nbObjects = 1
    nbCommands = 1
    nbPanier = 1
    while (db.insertUserInitialize() and nbUser<501):
        nbUser += 1
    while (db.insertObjectInitialize() and nbObjects<10001):
        nbObjects += 1
    while (db.insertCommandInitialize() and nbCommands<231):
        nbCommands += 1
    while (db.insertPanierInitialize() and nbPanier<131):
        nbPanier += 1
    return "Add 100 data in users successfully ! \n Add 10000 data in objects successfully ! \n Add 230 data in command successfully ! \n Add 130 data in panier successfully ! \n"

@app.route('/users')
def getAllUser():
    result = db.getAllUser()
    return result

@app.route('/objects')
def getAllObjects():
    result = db.getAllObjects()
    return result

# @app.route('/user', methods=['POST'])
# def insertUser():

if __name__ == '__main__':
    app.run(debug=True)

