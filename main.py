from flask import Flask, request, jsonify, session
from datetime import timedelta


app = Flask(__name__)
code = open("key.txt", "r")
app.secret_key = code.readlines()[0]
app.permanent_session_lifetime = timedelta(hours=2)
movies = []
rental = []
users = []


@app.route("/")
def home():
    return "Welcome to CRUD API WITH ARRAY"

@app.route("/user/<name>", methods=["GET", "PUT", "DELETE"])
def crud_user(name):
    if request.method == "PUT":
        user = request.form["Full Name"]
        update_user = request.form["New User"]
        for index, item in enumerate(users):
            if item == user:
                item = update_user
                session["user"] = update_user
                return jsonify({"status": "Success", "user": "Welcome {}".format(users[index])})
            else:
                return jsonify({"status": "Failure", "ERROR": "User not found"})
    elif request.method == "DELETE":
        user = request.form["Full Name"]
        users.remove(user)
        return jsonify({"status": "Success")})
    else:
        for item in users:
            if item == name:
                return jsonify({"status": "Success", "user": "Welcome Back {}".format(name)})
        return jsonify({"status": "Failure", "ERROR": "User not found"})

@app.route("/user/", methods=["POST")
def user():
    if request.method == "POST":
        session.permanent = True 
        user = request.form["Full Name"]
        users.append(user)
        session["user"] = user
        return jsonify({"status": "Success", "user": "Welcome {}".format(user)})


@app.route("/movie/", methods=["POST", "PUT", "DELETE"])
def movie():
    if request.method == "POST":
        session[]
    elif request.method == "PUT":
        pass
    elif request.method == "DELETE":
        pass
    else:
        if "user" in session:
            user = session["user"]
            return jsonify({"status": "Success", "user": "Welcome Back {}".format(user)})
        else:
            jsonify({"status": "Failure", "ERROR": "User not found"})


@app.route("/rental/", methods=["POST", "PUT", "DELETE"])
def rental():
    if request.method == "POST":
        session[]
    elif request.method == "PUT":
        pass
    elif request.method == "DELETE":
        pass
    else:
        if "user" in session:
            user = session["user"]
            return jsonify({"status": "Success", "user": "Welcome Back {}".format(user)})
        else:
            jsonify({"status": "Failure", "ERROR": "User not found"})


if __name__ == "__main__":
    app.run()
