# app/__init__.py
from flask import Flask, redirect, url_for
from app.config import Config
from app.routes.auth import auth_blueprint
from app.routes.koor import koor_blueprint
from app.model.models import db  # Import db here, but it will be initialized later

def create_app(config_class=Config):
    # Inisialisasi aplikasi Flask
    app = Flask(__name__)
    
    # Memuat konfigurasi dari kelas yang diberikan (misalnya, DevelopmentConfig, TestingConfig, dll)
    app.config.from_object(config_class)
    
    # Initialize the database with the app
    db.init_app(app)
    
    # Register the blueprint for auth
    app.register_blueprint(auth_blueprint, url_prefix='/auth')
    app.register_blueprint(koor_blueprint, url_prefix='/koor')

    @app.route('/')
    def main():
        return redirect(url_for('auth.login'))
    
    return app
