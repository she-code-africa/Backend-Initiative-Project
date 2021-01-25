from fastapi import FastAPI
import json
from fastapi.encoders import jsonable_encoder
import uvicorn
from models.model import Rentals, Users, Movies
# Init
app = FastAPI()


# read the files:
with open("data/movies.json") as movies_content:
    moviesContent = json.load(movies_content)

with open("data/users.json") as users_content:
    usersContent = json.load(users_content)

with open("data/rentals.json") as rentals_content:
    rentalsContent = json.load(rentals_content)


# 1. GET ALL - READ
@app.get('/movies')
async def allMovies():
    return [movie for movie in moviesContent]


@app.get('/users')
async def allUsers():
    return [user for user in usersContent]


@app.get('/rentals')
async def allRentals():
    return [rental for rental in rentalsContent]

# 2  CREATE


def write_json(filename, value, req_body):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        # appending data to emp_details
        temp.append(req_body.dict())

    with open(filename, 'w') as f:
        json.dump(data, f, indent=4)


@app.post('/addRentals')
async def create_rental(rental: Rentals):
    write_json('data/rentals.json', 'rentals', rental)
    return rental.dict()


@app.post('/addMovies')
async def create_movies(movie: Movies):
    write_json('data/movies.json', 'movies', movie)
    return movie.dict()


@app.post('/addUsers')
async def create_users(user: Users):
    write_json('data/users.json', 'users', user)
    return user.dict()

# 3. PUT


def update_json1(filename, value, req_body, check_id, json_id):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        for item in temp:
            if item[json_id] == check_id:
                item = req_body.dict()
                return(item)
        # appending data to emp_detail
        temp.append(item)
    with open(filename, 'w') as f:
        json.dump(data, f, indent=4)

def update (item, data ):
    pass
    


# 5 . GET A USER
def get_json(filename, value, check_id,json_id):
    with open(filename) as json_file:
        data = json.load(json_file)
        temp = data[str(value)]
        for item in temp:
            if item[json_id] == check_id:
                return item


@app.get('/getUser/{userid}')
async def get_user(userid: int):
    return get_json('data/users.json', 'users', userid, 'userId')

@app.get('/getMovie/{movieid}')
async def get_movie(movieid: int):
    return get_json('data/movies.json', 'movies', movieid, 'movieId')

@app.get('/getRental/{rentalid}')
async def get_rental(rentalid: int):
    return get_json('data/rentals.json', 'rentals', rentalid, 'rentalId')


@app.put('/editUsers/{userid}' , response_model = Users)
async def update_user(userid: int, user: Users):
    item = get_json('data/users.json', 'users', userid, 'userId')
    user =  jsonable_encoder(item)
    
    return user
    