from fastapi import FastAPI
import json
import uvicorn
from models.model import Rentals, Users, Movies
# Init
app = FastAPI()


# read the files:
with open("data/movies.json") as movies_content:
    movies = json.load(movies_content)

with open("data/users.json") as users_content:
    users = json.load(users_content)

with open("data/rentals.json") as rentals_content:
    rentals = json.load(rentals_content)

# 1. GET ALL - READ


@app.get('/movies')
async def allMovies():
    return [movie for movie in movies]


@app.get('/users')
async def allUsers():
    return [user for user in users]


@app.get('/rentals')
async def allRentals():
    return [rental for rental in rentals]

# 2  CREATE


@app.post('/addRentals/')
async def create_rental(rental: Rentals):
    json_object = json.dumps(rental, indent=4)

# Writing to sample.json
    with open("rentals.json", "w") as outfile:
        outfile.write(json_object)
    return rental


@app.post('/addUsers/')
async def create_user(user: Users):
    json_object = json.dumps(user, indent=4)

# Writing to sample.json
    with open("users.json", "w") as outfile:
        outfile.write(json_object)
    return user


@app.post('/addMovies/')
async def create_movie(movie: Movies):
    json_object = json.dumps(movie, indent=4)

# Writing to sample.json
    with open("movies.json", "w") as outfile:
        outfile.write(json_object)
    return movie

# 3. UPDATE


@app.put('/updateUsers')
async def update_user(parameter_list):
    pass


@app.put('/updateRentals')
async def update_rental(parameter_list):
    pass


@app.put('/updateMovies')
async def update_movies(parameter_list):
    pass
