from pydantic import BaseModel
from typing import Optional


class User(BaseModel):
    userId: int
    firstName:  str
    lastName:  str
    phoneNumber:  Optional[str] = None
    emailAddress: str


class Movies (BaseModel):
    title:  str
    rank:  int
    id:  str


class Rental(BaseModel):
    rentId: int
    Owner:  str
    Leaser:  str
    phoneNumber:  Optional[str] = None
    emailAddress: str
