from fastapi import FastAPI
import json
import uvicorn
from models.model import Rentals, Users, Movies

# Init
app = FastAPI()

# read the files:
def getAll(filename, value):
    with open(filename) as json_file:
            data = json.load(json_file)
            temp = data[str(value)]
            return temp

# 1. GET ALL -  READ  API
@app.get("/api/v1/allmovies")
async def allMovies():
    return getAll("data/movies.json", "movies")

@app.get("/api/v1/allusers")
async def allUsers():
    return getAll("data/users.json", "users")

@app.get("/api/v1/allrentals")
async def allRentals():
    return getAll("data/rentals.json", "rentals")

# 2  CREATE API

def write_json(filename, value, req_body):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        # appending data to emp_details
        temp.append(req_body.dict())

    with open(filename, "w") as f:
        json.dump(data, f, indent=4)

@app.post("/api/v1/addRentals")
async def create_rental(rental: Rentals):
    write_json("data/rentals.json", "rentals", rental)
    return rental.dict()

@app.post("/api/v1/addMovies")
async def create_movies(movie: Movies):
    write_json("data/movies.json", "movies", movie)
    return movie.dict()

@app.post("/api/v1/addUsers")
async def create_users(user: Users):
    write_json("data/users.json", "users", user)
    return user.dict()

# 3 . GET A {} API
def get_json(filename, value, check_id, json_id):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        for item in temp:
            if item[json_id] == check_id:
                return item
            else:
                return "Id {} does not exist in {}.json".format(check_id, value)

@app.get("/api/v1/getUser/{userid}")
async def get_user(userid: int):
    return get_json("data/users.json", "users", userid, "userId")

@app.get("/api/v1/getMovie/{movieid}")
async def get_movie(movieid: int):
    return get_json("data/movies.json", "movies", movieid, "movieId")

@app.get("/api/v1/getRental/{rentalid}")
async def get_rental(rentalid: int):
    return get_json("data/rentals.json", "rentals", rentalid, "rentalId")

# 4. PUT API

def update_json(filename, value, json_id, value_id, updates):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        for item in temp:
            if item[str(json_id)] == value_id:
                temp[temp.index(item)] = updates
            else:
                return "{}  does not exist in {} ".format(value_id, value)
    with open(filename, "w") as f:
        json.dump(data, f, indent=4)
    return updates

@app.put("/api/v1/editMovies/{movieid}", response_model=Movies)
async def update_movie(movieid: int, movie: Movies):
    return update_json("data/movies.json", "movies", "movieId", movieid, movie.dict())

@app.put("/api/v1/editUsers/{userid}", response_model=Users)
async def update_user(userid: int, user: Users):
    return update_json("data/users.json", "users", "userId", userid, user.dict())

@app.put("/api/v1/editRentals/{rentalid}", response_model=Rentals)
async def update_rental(rentalid: int, rental: Rentals):
    return update_json(
        "data/rentals.json", "rentals", "rentalId", rentalid, rental.dict()
    )


# 5. DELETE API

def delete_json(filename, value, json_id, value_id):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        for item in temp:
            if item[str(json_id)] == value_id:
                del temp[temp.index(item)]
            else:
                return "{}  does not exist in {} ".format(value_id, value)
    with open(filename, "w") as f:
        json.dump(data, f, indent=4)
    return "deleted"

@app.delete("/api/v1/delMovies/{movieid}")
async def delete_movie(movieid: int):
    return delete_json("data/movies.json", "movies", "movieId", movieid)

@app.delete("/api/v1/delUsers/{userid}")
async def delete_user(userid: int):
    return delete_json("data/users.json", "users", "userId", userid)

@app.delete("/api/v1/delRentals/{rentalid}")
async def delete_rental(rentalid: int):
    return delete_json("data/rentals.json", "rentals", "rentalId", rentalid)
