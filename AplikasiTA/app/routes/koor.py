from flask import Blueprint, render_template, request, redirect, url_for
from app.model.models import Users
from werkzeug.security import check_password_hash
import time

koor_blueprint = Blueprint('koor', __name__)

@koor_blueprint.route('/home', methods=['GET', 'POST'])
def home():
    return render_template('MainPage/Koordinator/home.html')

@koor_blueprint.route('/overview', methods=['GET', 'POST'])
def overview():
    return render_template('TAMainPage_sebelumTA/Koordinator/TApageOverview.html')