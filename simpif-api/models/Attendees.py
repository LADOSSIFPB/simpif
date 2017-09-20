from common.database import db

class Attendees(db.Model):
    __tablename__ = 'attendees'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    firstName = db.Column('first_name', db.String(255))
    lastName = db.Column('last_name', db.String(255))
    email = db.Column('email', db.String(255))
    privateRefNum = db.Column('private_reference_number', db.Integer)
    hasArrived = db.Column('has_arrived', db.Boolean)
    arrivalTime = db.Column('arrival_time', db.DateTime)

    def __str__(self):
        return str(self.id) +"  " + str(self.firstName) + " " + str(self.lastName)+ "" +str(self.privateRefNum) +  "  !!!encontrado com sucesso!!" + str(self.hasArrived)
