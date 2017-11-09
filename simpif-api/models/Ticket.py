from common.database import db

class Ticket(db.Model):
    __tablename__ = 'tickets'
    id = db.Column('id', db.Integer, primary_key=True, autoincrement=True)
    title = db.Column('title', db.String)
    description = db.Column('description', db.String)