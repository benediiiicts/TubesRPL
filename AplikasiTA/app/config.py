# config.py

import os
from dotenv import load_dotenv

# Load environment variables from the .env file
load_dotenv()

class Config:
    # General configuration
    SECRET_KEY = os.environ.get('SECRET_KEY', 'your_default_secret_key')  # Use environment variable or fallback

    # Database configuration (separate components)
    DB_USERNAME = os.environ.get('DB_USERNAME', 'default_username')
    DB_PASSWORD = os.environ.get('DB_PASSWORD', 'default_password')
    DB_HOST = os.environ.get('DB_HOST', 'localhost')
    DB_PORT = os.environ.get('DB_PORT', '5432')  # Default port for PostgreSQL
    DB_NAME = os.environ.get('DB_NAME', 'my_database')
    # FLASK_APP = run.py

    # Construct the full database URL using these components
    SQLALCHEMY_DATABASE_URI = (
        f"postgresql://{DB_USERNAME}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_NAME}"
    )

    SQLALCHEMY_TRACK_MODIFICATIONS = False  # Optional, to disable unnecessary tracking of modifications
