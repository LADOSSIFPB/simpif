#coding: utf-8

from flask_restful import Resource
from common.database import db
from models.Attendees import Attendees
import datetime

class AttendeesResource(Resource):

    def get(self, codigo):
        attendees = Attendees.query.filter_by(privateRefNum=codigo).first()
        print(attendees)
        if (attendees.hasArrived):
            return "Check-in ja foi realizado antes.", 200
        arrivalTime = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
        attendees.hasArrived = True
        attendees.arrivalTime = arrivalTime
        db.session.commit()
        print("Checkin realizado com sucesso.")

        return "Checkin realizado com sucesso.", 200