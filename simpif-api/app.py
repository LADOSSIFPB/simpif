from flask import Flask, Blueprint
from flask_restful import Api
from flask_cors import CORS
from common.database import db
from common.logging import *
from resources.AttendeeResource import AttendeeResource
from resources.OrderResource import OrderResource, OrderAttendeesResource
from resources.LoginResource import LoginResource

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:ifpbinfo@localhost/attendize' # mysql://usuario:senha@localhost/nomedobanco
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['DEBUG'] = True

# Configure logging
handler = logging.FileHandler(LOGGING_LOCATION)
handler.setLevel(LOGGING_LEVEL)
formatter = logging.Formatter(LOGGING_FORMAT)
handler.setFormatter(formatter)
app.logger.addHandler(handler)

db.init_app(app)

api_bp = Blueprint('api', __name__)
api = Api(api_bp, prefix='/api')

api.add_resource(AttendeeResource, '/checkin/attendees/<string:codigo>')
api.add_resource(OrderResource, '/checkin/orders')
api.add_resource(OrderAttendeesResource, '/checkin/orders/references/<string:orderRef>')
api.add_resource(LoginResource, '/checkin/login')


app.register_blueprint(api_bp)

cors = CORS(app, resources={r"/api/*": {"origins": "*"}})

if __name__ == '__main__':
    app.run(host='0.0.0.0')