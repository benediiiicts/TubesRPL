# app/model/models.py
from flask_sqlalchemy import SQLAlchemy

# Do not import db here directly; instead, use it when needed
db = SQLAlchemy()

class Users(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(50), unique=True, nullable=False)
    password = db.Column(db.String(255), nullable=False)
    name = db.Column(db.String(100))
    role = db.Column(db.String(50), nullable=False)

    def __repr__(self):
        return f'<User {self.username}>'
