from flask import Flask

import db

app = Flask(__name__)

db = db.DB()

@app.route('/')
def hello():
    return 'Hello, world!'

@app.route('/users')
def getAllUser():
    results = db.searchAllUser()
    # print(results)
    return results

# @app.route('/user', methods=['POST'])
# def insertUser():

if __name__ == '__main__':
    app.run(debug=True)

