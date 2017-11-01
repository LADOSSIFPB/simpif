# -*- coding: utf-8 -*-

from flask_restful import Resource
from flask import Response, current_app
from common.database import db
from common.auth import auth
from models.Attendee import Attendee
import datetime

class AttendeeResource(Resource):
    @auth.login_required
    def get(self, codigo):
        current_app.logger.info("Get - Attendees - %s" % codigo)
        try:
            attendees = Attendee.query.filter_by(privateRefNum=codigo).first()
            if (attendees.hasArrived):
                return Response("Check-in já foi realizado antes.", 200, mimetype='text/plain')
            arrivalTime = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            attendees.hasArrived = True
            attendees.arrivalTime = arrivalTime
            db.session.commit()

            current_app.logger.info("Checkin realizado com sucesso")

            return Response("Checkin realizado com sucesso.", 200, mimetype='text/plain')

        except AttributeError:
            current_app.logger.info("Código invalido")
            return Response("Código inválido.", 404, mimetype="text/plain")