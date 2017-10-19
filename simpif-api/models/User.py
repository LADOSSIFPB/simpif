from common.database import db
from flask_restful import fields
import hashlib

user_fields = {
    'id': fields.Integer,
    'email': fields.String,
    'senha': fields.String
}

class User(db.Model):
    __tablename__ = 'users'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    email = db.Column('email', db.String(255))
    senha = db.Column('password', db.String(255))

    def verify_password(self, password):
        password = hashlib.md5(password.encode())
        if password.digest() == self.senha.digest():
            return True