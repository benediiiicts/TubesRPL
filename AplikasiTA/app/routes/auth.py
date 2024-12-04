from flask import Blueprint, render_template, request, redirect, url_for
from app.model.models import Users
from werkzeug.security import check_password_hash

auth_blueprint = Blueprint('auth', __name__)

@auth_blueprint.route('/login', methods=['GET', 'POST'])
def login():
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        
        # Query user dari database
        user = Users.query.filter_by(username=username).first()  # query ke database menggunakan sqlalchemy
        
        # cek jika user ada dan password sesuai
        if user and check_password_hash(user.password, password):  # cek password
            return redirect('successLogin.html')  # Redirect jika berhasil login
        else:
            return render_template('login.html', error="Invalid username or password")
    
    return render_template('login.html', error=None)
@auth_blueprint.route('/test')
def show_users():
    # Query all users from the database
    users = Users.query.all()
    return render_template('test.html', users=users)
