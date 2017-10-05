from common.database import db
from flask_restful import fields

attendee_fields = {
    'id': fields.Integer,
    'firstName': fields.String,
    'lastName': fields.String,
    'email': fields.String,
    'privateRefNum': fields.Integer,
    'hasArrived': fields.Boolean,
    'arrivalTime': fields.DateTime(dt_format='iso8601'),
    'order_id': fields.Integer
}

class Attendee(db.Model):
    __tablename__ = 'attendees'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    firstName = db.Column('first_name', db.String(255))
    lastName = db.Column('last_name', db.String(255))
    email = db.Column('email', db.String(255))
    privateRefNum = db.Column('private_reference_number', db.Integer)
    hasArrived = db.Column('has_arrived', db.Boolean)
    arrivalTime = db.Column('arrival_time', db.DateTime)

    order_id = db.Column('order_id', db.ForeignKey("orders.id"))

    def __init__(self, firstName, lastName, email, privateRefNum, hasArrived, arrivalTime, order_id):
        self.firstName = firstName
        self.lastName = lastName
        self.email = email
        self.privateRefNum = privateRefNum
        self.hasArrived = hasArrived
        self.arrivalTime = arrivalTime
        self.order_id = order_id

    def __str__(self):
        return str(self.id) + " " + str(self.firstName) + " " + str(self.lastName) + " " + str(self.privateRefNum) + ". Encontrado com sucesso."