from pydantic import BaseModel
from typing import Optional


class Users(BaseModel):
    userId: int
    firstName:  str
    lastName:  str
    phoneNumber:  Optional[str] = None
    emailAddress: str


class Movies (BaseModel):
    title:  str
    rank:  int
    movieId:  int


class Rentals(BaseModel):
    rentalId: int
    Owner:  str
    Leasee:  str
    phoneNumber:  Optional[str] = None
    emailAddress: str
