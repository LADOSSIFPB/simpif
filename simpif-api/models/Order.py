from common.database import db
from flask_restful import fields

order_fields = {
    'id': fields.Integer,
    'order_reference': fields.String
}

class Order(db.Model):
    __tablename__ = 'orders'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    order_reference = db.Column('order_reference', db.String(15))
