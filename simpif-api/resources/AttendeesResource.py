# -*- coding: utf-8 -*-

from flask_restful import Resource
from flask import Response
from common.database import db
from models.Attendees import Attendees
import datetime

class AttendeesResource(Resource):

    def get(self, codigo):
        try:
            attendees = Attendees.query.filter_by(privateRefNum=codigo).first()
            print(attendees)
            if (attendees.hasArrived):
                return Response("Check-in já foi realizado antes.", 200, mimetype='text/plain')
            arrivalTime = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            attendees.hasArrived = True
            attendees.arrivalTime = arrivalTime
            db.session.commit()
            print("Checkin realizado com sucesso.")

            return Response("Checkin realizado com sucesso.", 200, mimetype='text/plain')

        except AttributeError:
            return Response("Código inválido.", 404, mimetype="text/plain")