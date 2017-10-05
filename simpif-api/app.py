from flask import Flask, Blueprint
from flask_restful import Api
from flask_cors import CORS
from common.database import db
from resources.AttendeeResource import AttendeeResource
from resources.OrderResource import OrderResource, OrderAttendeesResource

app = Flask(__name__)

app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:ifpbinfo@localhost/attendize' # mysql://usuario:senha@localhost/nomedobanco
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['DEBUG'] = True

db.init_app(app)

api_bp = Blueprint('api', __name__)
api = Api(api_bp, prefix='/api')

api.add_resource(AttendeeResource, '/checkin/attendees/<string:codigo>')
api.add_resource(OrderResource, '/checkin/orders')
api.add_resource(OrderAttendeesResource, '/checkin/orders/references/<string:orderRef>')


app.register_blueprint(api_bp)

cors = CORS(app, resources={r"/api/*": {"origins": "*"}})

if __name__ == '__main__':
    app.run(host='0.0.0.0')