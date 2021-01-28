from fastapi import FastAPI
import json
import uvicorn
from models.model import Rentals, Users, Movies

# Init
app = FastAPI()

# read the files:
def getAll(filename):
    with open(filename) as json_file:
        data = json.load(json_file)
    return data


# 1. GET ALL -  READ  API
@app.get("/api/v1/allmovies")
async def allMovies():
    return getAll("data/movies.json")


@app.get("/api/v1/allusers")
async def allUsers():
    return getAll("data/users.json")


@app.get("/api/v1/allrentals")
async def allRentals():
    return getAll("data/rentals.json")


# 2  CREATE API

def write_json(filename, req_body, model):
    with open(filename) as json_file:
        data = json.load(json_file)

        # appending data 
        data.append(req_body.dict())

    with open(filename, "w") as f:
        json.dump(data, f, indent=4)
    return {model:req_body.dict()}


@app.post("/api/v1/addRentals")
async def create_rental(rental: Rentals):
    return write_json("data/rentals.json", rental, 'Rental')
    # return rental.dict()


@app.post("/api/v1/addMovies")
async def create_movies(movie: Movies):
    return write_json("data/movies.json", movie, 'Movie')
    # return movie.dict()


@app.post("/api/v1/addUsers")
async def create_users(user: Users):
    return write_json("data/users.json", user, 'User')
    # return user.dict()


# 3 . GET A {} API
def get_json(filename, check_id, json_id):
    with open(filename) as json_file:
        data = json.load(json_file)

        for item in data:
            if item[json_id] == check_id:
                return {"msg":"Succesfully retrieved {}".format(check_id), "Retrieved Data":item}
    return {"Error Message":"Id {} does not exist in json".format(check_id)}


@app.get("/api/v1/getUser/{userid}")
async def get_user(userid: int):
    return get_json("data/users.json", userid, "userId")


@app.get("/api/v1/getMovie/{movieid}")
async def get_movie(movieid: int):
    return get_json("data/movies.json", movieid, "movieId")


@app.get("/api/v1/getRental/{rentalid}")
async def get_rental(rentalid: int):
    return get_json("data/rentals.json", rentalid, "rentalId")


# 4. PUT API

def update_json(filename, json_id, check_id, updates):
    with open(filename) as json_file:
      
        data = json.load(json_file)
        for item in data:
            if item[json_id] == check_id:
                data[data.index(item)] = updates
                
                respon = {"msg":"Updated from Id {}".format(check_id), "Update":updates}
            elif item[json_id] != check_id:
                respon = {"Error Message":"Id {} does not exist in .json ".format(check_id)}
    with open(filename, "w") as f:
        json.dump(data, f, indent=4)

    return respon


@app.put("/api/v1/editMovies/{movieid}")
async def update_movie(movieid: int, movie: Movies):
    return update_json("data/movies.json", "movieId", movieid, movie.dict())
    


@app.put("/api/v1/editUsers/{userid}")
async def update_user(userid: int, user: Users):
    return update_json("data/users.json", "userId", userid, user.dict())


@app.put("/api/v1/editRentals/{rentalid}")
async def update_rental(rentalid: int, rental: Rentals):
    return update_json("data/rentals.json", "rentalId", rentalid, rental.dict())

# 5. DELETE API

def delete_json(filename, json_id, value_id):
    with open(filename) as json_file:
        data = json.load(json_file)
        for item in data:
            if item[str(json_id)] == value_id:
                del data[data.index(item)]
                display = {'msg':'deleted', 'Deleted': item}
            elif item[str(json_id)] != value_id:
                display = {'msg':'not deleted', 'Does not Exist': value_id}
    with open(filename, "w") as f:
        json.dump(data, f, indent=4)
    return display

@app.delete("/api/v1/delMovies/{movieid}")
async def delete_movie(movieid: int):
    return delete_json("data/movies.json", "movieId", movieid)


@app.delete("/api/v1/delUsers/{userid}")
async def delete_user(userid: int):
    return delete_json("data/users.json", "userId", userid)


@app.delete("/api/v1/delRentals/{rentalid}")
async def delete_rental(rentalid: int):
    return delete_json("data/rentals.json", "rentalId", rentalid)
