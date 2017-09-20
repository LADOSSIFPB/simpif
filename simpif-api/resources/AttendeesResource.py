from flask_restful import Resource
from common.database import db
from models.Attendees import Attendees

class AttendeesResource(Resource):

    def get(self, codigo):
        attendees = Attendees.query.filter_by(privateRefNum=codigo).first()
        print(attendees)
        attendees.hasArrived = True
        db.session.commit()

        return "teste" + str(attendees)