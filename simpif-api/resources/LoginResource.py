from flask_restful import Resource, marshal_with, reqparse, current_app, abort
from flask import g, request
from common.auth import auth
from common.database import db
from sqlalchemy import exc
from flask import jsonify
from models.User import User, user_fields, token_fields
from werkzeug.security import generate_password_hash

class LoginResource(Resource):

    # POST /login
    #@marshal_with(token_fields)
    def post(self):
        try:
            dados = request.json
            usuario = User.query.filter_by(email=dados['email']).first()
            if usuario is None:
                print("Usuario não encontrado")
                abort(401)
            else:
                if usuario.verificar_senha(dados['senha']) == False:
                    print("Senha errada")
                    abort(401)
                else:
                    usuario.generate_auth_token()

        except exc.SQLAlchemyError:
            print("Exceção")
            return "", 404

        return usuario.token, 200