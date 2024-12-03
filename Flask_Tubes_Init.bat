@echo off
REM Create project root directory
mkdir my_flask_project
cd my_flask_project

REM Create subdirectories for static, templates, and others
mkdir static
mkdir static\css
mkdir static\js
mkdir static\images
mkdir templates
mkdir venv

REM Create basic files in the root project folder
echo from flask import Flask, render_template > app.py
echo # Main Flask app file (entry point) >> app.py
echo. >> app.py
echo app = Flask(__name__) >> app.py
echo. >> app.py
echo REM Route for the actor's page (this will render the HTML from your frontend) >> app.py
echo @app.route("/actor/<actor_name>") >> app.py
echo def actor_page(actor_name): >> app.py
echo     return render_template("actor_page.html", actor_name=actor_name) >> app.py
echo. >> app.py
echo if __name__ == "__main__": >> app.py
echo     app.run(debug=True) >> app.py

REM Create requirements.txt file with Flask dependency
echo Flask > requirements.txt

REM Create a base HTML template (base.html)
echo <!DOCTYPE html> > templates\base.html
echo <html lang="en"> >> templates\base.html
echo <head> >> templates\base.html
echo     <meta charset="UTF-8"> >> templates\base.html
echo     <meta name="viewport" content="width=device-width, initial-scale=1.0"> >> templates\base.html
echo     <title>Base Template</title> >> templates\base.html
echo     <link rel="stylesheet" href="{{ url_for('static', filename='css/styles.css') }}"> >> templates\base.html
echo </head> >> templates\base.html
echo <body> >> templates\base.html
echo     <h1>{{ actor_name }}</h1> >> templates\base.html
echo     <div>{% block content %}{% endblock %}</div> >> templates\base.html
echo </body> >> templates\base.html
echo </html> >> templates\base.html

REM Create the actor page template (actor_page.html)
echo <!DOCTYPE html> > templates\actor_page.html
echo <html lang="en"> >> templates\actor_page.html
echo <head> >> templates\actor_page.html
echo     <meta charset="UTF-8"> >> templates\actor_page.html
echo     <meta name="viewport" content="width=device-width, initial-scale=1.0"> >> templates\actor_page.html
echo     <title>{{ actor_name }} - Actor Page</title> >> templates\actor_page.html
echo     <link rel="stylesheet" href="{{ url_for('static', filename='css/styles.css') }}"> >> templates\actor_page.html
echo </head> >> templates\actor_page.html
echo <body> >> templates\actor_page.html
echo     <h1>Welcome to the page of {{ actor_name }}</h1> >> templates\actor_page.html
echo     <img src="{{ url_for('static', filename='images/actor.jpg') }}" alt="{{ actor_name }}"> >> templates\actor_page.html
echo     <script src="{{ url_for('static', filename='js/script.js') }}"></script> >> templates\actor_page.html
echo </body> >> templates\actor_page.html
echo </html> >> templates\actor_page.html

REM Done! Notify user.
echo Project structure for Flask app created successfully!
