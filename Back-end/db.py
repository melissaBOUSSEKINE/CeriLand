import psycopg2
from dotenv import load_dotenv
import os

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

    # def __init__(self):

    def searchAllUser(self):
        cur = self.conn.cursor()
        cur.execute("SELECT * FROM users")
        rows = cur.fetchall()
        cur.close()
        return rows

    # def insertUser()
        
    def closeDB(self):
        self.conn.close








# ceriland_objects: id, image, nom, date(dispo), prix, id_owner
# ceriland_users: id, role('owner', 'user'), nom, password, addresses
# ceriland_command: id, id_object, id_commander, id_owner
# ceriland_panier: id_object, id_user
# ceriland_comments: is_object, id_user


