from common.database import db
from flask_restful import fields, current_app
from werkzeug.security import generate_password_hash, check_password_hash
import hashlib
import base64
from itsdangerous import (TimedJSONWebSignatureSerializer
                          as Serializer, BadSignature, SignatureExpired)

user_fields = {
    'id': fields.Integer,
    'email': fields.String,
    'senha': fields.String
}

token_fields = {
    'token': fields.String
}

class User(db.Model):
    __tablename__ = 'users'

    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    email = db.Column('email', db.String(255))
    senha = db.Column('password_android', db.String(255))

    def __init__(self, email, senha, id, token = None ):
        self.email = email
        self.senha = senha
        self.id = id
        self.token = token
    '''
    def set_senha(self, senha):
        self.senha = generate_password_hash(senha)
    '''

    def verificar_senha(self, password):
        pass_hash = base64.b64encode(password.encode('utf-8'))
        print(pass_hash.decode('utf-8'))
        print(self.senha)
        if pass_hash.decode('utf-8') == self.senha:
            return True
        else:
            return False

    def generate_auth_token(self, expiration=None):
        s = Serializer('123456', expires_in=expiration)
        dumps = s.dumps({'id': self.id})
        print(self.id)
        self.token = dumps.decode('ascii')

    @staticmethod
    def verify_auth_token(token):
        current_app.logger.info("Token: %s" % (token))
        s = Serializer('123456')
        try:
            data = s.loads(token)
        except SignatureExpired:
            return None  # valid token, but expired
        except BadSignature:
            return None  # invalid token
        user = User.query.get(data['id'])
        return user

    '''def __str__(self):
        return '<Usuario %r>' % self.email'''
