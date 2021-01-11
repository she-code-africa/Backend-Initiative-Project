from django.db import models
from django.contrib.auth.models import User

# Create your models here.
class Rentals(models.Model):
	rental=models.PositiveIntegerField(null=True, blank=True)

class Movies(models.Model):
	Title=models.CharField(max_length=200)
	Producer=models.CharField(max_length=200)
	Genre=models.CharField(max_length=200)
	Rental=models.ForeignKey(Rentals, on_delete=models.SET('No information'), null=True, blank=True)



