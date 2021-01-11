from flask import Flask, request, jsonify, session, redirect, url_for
from datetime import timedelta


app = Flask(__name__)
code = open("key.txt", "r")
app.secret_key = code.readlines()[0]
app.permanent_session_lifetime = timedelta(hours=1)
movies = []
rentals = []
users = []


@app.route("/")
def home():
    return "Welcome to CRUD API WITH ARRAY"


@app.route("/login", methods=["POST"])
def login():
    session.permanent = True
    user = request.form["fullname"]
    for find_user in users:
        if find_user == user:
            session["user"] = find_user
            return redirect(url_for("user", usr=user)), 200
    users.append(user)
    session["user"] = user
    return jsonify({"status": "Success", "user": "Welcome {}".format(user)})


@app.route("/user", methods=["GET", "PUT", "DELETE"])
def user():
    if "user" in session:
        user = session["user"]
        if request.method == "PUT":
            update_user = request.form["updateuser"]
            for index, item in enumerate(users):
                if item == user:
                    users[index] = update_user
                    session["user"] = update_user
                    return jsonify({"status": "Success", "user": "Updated {}".format(users[index])})
            return jsonify({"status": "Failure", "ERROR": "User not found"})
        elif request.method == "DELETE":
            try:
                users.remove(user)
                return jsonify({"status": "Success"})
            except:
                return jsonify({"status": "Failure", "ERROR": "User not found"}), 400
        return jsonify({"status": "Success", "user": "Welcome Back {}".format(user)})
    else:
        return jsonify({"status": "Failure", "ERROR": "User not found"})


@app.route("/movie/", methods=["POST", "GET", "PUT", "DELETE"])
def movie():
    if request.method == "POST":
        session.permanent = True
        movie = request.form["movie"]
        for find_movie in movies:
            if find_movie == movie:
                session["movie"] = find_movie
                return redirect(url_for("movie", mov=movie)), 200
        movies.append(movie)
        session["movie"] = movie
        return jsonify({"status": "Success", "data": "{} Saved".format(movies)})
    else:
        if "movie" in session:
            movie = session["movie"]
            if request.method == "GET":
                return jsonify({"status": "Success", "movie": movie})
            elif request.method == "PUT":
                update_movie = request.form["updatemovie"]
                for index, movie_name in enumerate(movies):
                    if movie_name == movie:
                        movies[index] = update_movie
                        session["movie"] = update_movie
                        return jsonify({"status": "Success", "movie Updated": movies[index]})
                return jsonify({"status": "Failure", "ERROR": "Movie is Unavailable"}), 400
            elif request.method == "DELETE":
                movies.remove(movie)
                return jsonify({"status": "Success", "data": movies})
        else:
            return jsonify({"status": "Welcome", "Movies available": movies}), 200


@app.route("/rental/", methods=["POST", "GET", "PUT", "DELETE"])
def rental():
    if request.method == "POST":
        session.permanent = True
        rental = request.form["rental"]
        for find_rental in rentals:
            if find_rental == rental:
                session["rental"] = find_rental
                return redirect(url_for("rental", rent=rental)), 200
        rentals.append(rental)
        session["rental"] = rental
        return jsonify({"status": "Success", "data": "{} Saved".format(rentals)})
    else:
        if "rental" in session:
            rental = session["rental"]
            if request.method == "GET":
                return jsonify({"status": "Success", "Rental Service": rental})
            elif request.method == "PUT":
                update_rental = request.form["updaterental"]
                for index, rental_name in enumerate(rentals):
                    if rental_name == rental:
                        rentals[index] = update_rental
                        session["rental"] = update_rental
                        return jsonify({"status": "Success", "movie Updated": rentals[index]})
                return jsonify({"status": "Failure", "ERROR": "Rental Service is Unavailable"}), 400
            elif request.method == "DELETE":
                rentals.remove(rental)
                return jsonify({"status": "Success", "data": rentals})
        else:
            return jsonify({"status": "Welcome", "Rentals available": rentals}), 200


if __name__ == "__main__":
    app.run()
