from rest_framework import  serializers
from .models import Movies, Rentals
from django.contrib.auth.models import User

class MoviesSerializer(serializers.ModelSerializer):
	class Meta:
		model=Movies
		fields='__all__'
class RentalsSerializer(serializers.ModelSerializer):
	class Meta:
		model=Rentals
		fields='__all__'

class UserSerializer(serializers.ModelSerializer):
		class Meta:
				model=User
				fields=('first_name', 'last_name', 'username', 'email')