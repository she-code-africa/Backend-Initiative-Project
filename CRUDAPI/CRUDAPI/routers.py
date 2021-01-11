from MusicApi.ViewSet import MovieViewset,  RentalsViewset, UsersViewset
from rest_framework import routers

router=routers.DefaultRouter()
router.register(r'movies', MovieViewset, basename='movies')
router.register(r'rentals', RentalsViewset, basename='rentals')
router.register(r'users', UsersViewset, basename='users')