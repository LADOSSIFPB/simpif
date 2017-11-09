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
        current_app.logger.info("Get - Attendee - %s" % codigo)
        try:
            attendee = Attendee.query.filter_by(privateRefNum=codigo).first()
            if (attendee.hasArrived):

                current_app.logger.info("Checkin já foi realizado antes - Código: %s" % codigo)

                return Response("Check-in já foi realizado antes.", 200, mimetype='text/plain')
            if (attendee.isCancelled):

                current_app.logger.info("Inscrição cancelada - Código: %s" % codigo)

                return Response("Inscrição cancelada.", 404, mimetype='text/plain')

            arrivalTime = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            attendee.hasArrived = True
            attendee.arrivalTime = arrivalTime
            db.session.commit()

            current_app.logger.info("Checkin realizado - Código: %s" % codigo)

            return Response("Checkin realizado.", 200, mimetype='text/plain')

        except AttributeError:
            current_app.logger.info("Código invalido - Código: %s" % codigo)
            return Response("Código inválido.", 404, mimetype="text/plain")