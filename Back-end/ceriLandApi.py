from flask import Flask

import db

class CeriLandApi:

    def __init__(self) -> None:
        self.db = db.DB()

app = Flask(__name__)

api = CeriLandApi()

@app.route('/')
def hello():
    return 'Hello, world!'

@app.route('/users')
def getAllUser():
    results = api.db.searchAllUser()
    print(results)
    return results

if __name__ == '__main__':
    app.run(debug=True)

