from rest_framework import viewsets
from .models import Rentals, Movies
from .serializers import MoviesSerializer, RentalsSerializer, UserSerializer
from django.contrib.auth.models import User


class MovieViewset(viewsets.ModelViewSet):
	queryset=Movies.objects.all()
	serializer_class=MoviesSerializer
class RentalsViewset(viewsets.ModelViewSet):
	queryset=Rentals.objects.all()
	serializer_class=RentalsSerializer
class UsersViewset(viewsets.ModelViewSet):
	queryset=User.objects.all()
	serializer_class=UserSerializer