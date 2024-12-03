# app.py
from flask import Flask
from flask import Flask, render_template 
from config import Config
from flask_sqlalchemy import SQLAlchemy
import os

app = Flask(__name__)
# load config dari config.py
app.config.from_object(Config)

# Periksa load env terbaca atau tidak
print("Database Username:", os.getenv("DB_USERNAME"))
print("Database Password:", os.getenv("DB_PASSWORD"))
print("Database URL:", app.config["SQLALCHEMY_DATABASE_URI"])

# membuat objek database
db = SQLAlchemy(app)

with app.app_context():
    db.create_all()

# Membuat model user
class Users(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    username = db.Column(db.String(50), unique=True, nullable=False)
    password = db.Column(db.String(255), nullable=False)
    name = db.Column(db.String(100))
    role = db.Column(db.String(50), nullable=False)



 #Routing
@app.route("/") 
def actor_page(): 
    # return render_template("./Login/login.html") 
    return render_template("test.html") 

@app.route("/mainPage")
def mainPage(actor):
    return render_template("./MainPage/mainPage.html", actor=actor)

@app.route('/users')
def show_users():
    # Query all users from the database
    users = Users.query.all()
    return render_template('test.html', users=users)


if __name__ == "__main__":
    app.run(debug=True)


