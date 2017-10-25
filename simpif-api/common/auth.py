from flask import g
from flask_httpauth import HTTPBasicAuth
from flask_restful import current_app
from models.User import User

auth = HTTPBasicAuth()


@auth.verify_password
def verificar_senha(token, a):
    current_app.logger.info("Auth - Login ou Token: %s" % (token))
    # Verificar token para usuários já autenticados.
    usuario = User.verify_auth_token(token)
    if not usuario:
        return False
    # Usuário autenticado
    g.user = usuario
    return True