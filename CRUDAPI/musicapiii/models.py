from django.db import models
from django.contrib.auth.models import User
# Create your models here.
class Movies(models.Model):
	Title=models.CharField(max_length=200)
	Producer=models.CharField(max_length=200)
	Genre=models.CharField(max_length=200)
	#Insert Cast
	Cast=models.CharField(max_length=200)
	#Insert Director
	Director=models.CharField(max_length=200)
	#Insert year of production
	Year_of_production=models.DecimalField(max_digits=4, decimal_places=0)


class Rentals(models.Model):
	Purchase_price=models.PositiveIntegerField()
	Ordered_by=models.ForeignKey(User, on_delete=models.CASCADE)
	#insert datefield
	Order_date=models.DateField(auto_now_add=True)
	#insert movie rented
	Ordered_Movie=models.ForeignKey('Movies', on_delete=models.CASCADE)