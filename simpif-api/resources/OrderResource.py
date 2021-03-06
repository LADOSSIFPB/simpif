# -*- coding: utf-8 -*-

from flask_restful import Resource, marshal_with
from flask import Response, current_app
from common.auth import auth
from common.database import db
from models.Order import Order, order_fields
from models.Attendee import Attendee, attendee_fields
import datetime


class OrderResource(Resource):
    # GET /orders
    @auth.login_required
    @marshal_with(order_fields)
    def get(self):
        orders = Order.query.all()
        return orders
        
class OrderAttendeesResource(Resource):
    # GET /orders/references/<orderRef>
    @auth.login_required
    @marshal_with(attendee_fields)
    def get(self, orderRef):
        current_app.logger.info("Get - Attendees - OrderReference: %s" % orderRef)
        # Verificar Order Reference
        attendees = (db.session.query(Attendee)
                         .join(Order, Order.id == Attendee.order_id )
                         .filter(Order.order_reference == orderRef)
                         .all())

        if len(attendees) == 0:
            current_app.logger.info("Código de compra não existente - Código: %s" % orderRef)
            return attendees, 200

        current_app.logger.info("Encontrado %i attendees" % len(attendees))
        return attendees, 200
