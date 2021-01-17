from fastapi import FastAPI
import json
import uvicorn

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

# CREATE
@app.post('/rentals/{id}')
async def createRentals():
     pass