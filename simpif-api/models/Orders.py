from common.database import db

class Orders(db.Model):
    __tablename__ = 'orders'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    order_reference = db.Column('order_reference', db.String(15))
