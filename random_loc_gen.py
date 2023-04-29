import random
import csv

# Define boundaries of Atlanta
min_lat = 33.647800
max_lat = 33.886940
min_lng = -84.551450
max_lng = -84.289880

# Generate 1000 random locations within Atlanta
locations = []
for i in range(1, 1001):
    lat = round(random.uniform(min_lat, max_lat), 8)
    lng = round(random.uniform(min_lng, max_lng), 8)
    name = ''.join(random.choice('abcdefghijklmnopqrstuvwxyz') for _ in range(10))
    description = ''.join(random.choice('abcdefghijklmnopqrstuvwxyz') for _ in range(50))
    picture = f"Picture URL for Location {i}"
    locations.append((i, name, description, picture, lat, lng))

# Write locations to CSV file
with open('locations.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile)
    writer.writerow(['id', 'name', 'description', 'picture', 'latitude', 'longitude'])
    for location in locations:
        writer.writerow(location)
