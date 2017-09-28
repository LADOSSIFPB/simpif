# -*- coding: utf-8 -*-

from flask_restful import Resource
from flask import Response
from common.database import db
from models.Orders import Orders
from models.Attendees import Attendees
import datetime

class OrdersResource(Resource):

    def get(self, orderRef):
        try:
            orderRef = orderRef.upper()
            orders = Orders.query.filter_by(order_reference=orderRef).first()
            attendees = Attendees.query.filter_by(order_id=orders.id).first()
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