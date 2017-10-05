from common.database import db
from flask_restful import fields

user_fields = {}

class User(db.Model):
    __tablename__ = 'users'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    email = db.Column('email', db.String(255))
    senha = db.Column('password', db.String(255))