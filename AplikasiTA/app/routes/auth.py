from flask import Blueprint, render_template, request, redirect, url_for
from app.model.models import Users
from werkzeug.security import check_password_hash
import time

auth_blueprint = Blueprint('auth', __name__)

@auth_blueprint.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        
        # Query user dari database
        user = Users.query.filter_by(username=username).first()
        
          # query ke database menggunakan sqlalchemy
        
        # cek jika user ada dan password sesuai 
        # if user and check_password_hash(user.password, password):  # cek password
        if user and password == user.password :  # cek password
            return redirect(url_for('auth.login_success'))  # Redirect jika berhasil login
        else:
            return render_template('Login/login.html', error=user.password) 
    
    return render_template('Login/login.html', error=None)

@auth_blueprint.route('/login_success') 
def login_success():
    render_template('successLogin.html')
    time.sleep(1)
    return redirect(url_for('koor.home'))

@auth_blueprint.route('/logout')
def logout():
    return redirect(url_for('auth.login'))


@auth_blueprint.route('/test')
def show_users():
    # Query all users from the database
    users = Users.query.all()
    return render_template('test.html', users=users)
