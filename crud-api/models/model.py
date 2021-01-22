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
    titleId:  int


class Rentals(BaseModel):
    rentId: int
    Owner:  str
    Leasee:  str
    phoneNumber:  Optional[str] = None
    emailAddress: str
