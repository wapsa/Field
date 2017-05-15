import requests
import random

loginResponse = requests.post('http://localhost:8080/login', json={'username':'saurabh.pawar', 'password':'password'})
print(loginResponse)
print(loginResponse.text)

latitude = ['18.5958815','18.5958896','18.5958077','18.5959282', '18.5958928']
longitude = ['73.7840973','73.7840513','73.7839594','73.784213','73.7840513']

cameInResponse = requests.post('http://localhost:8080/coming-in', json={'qrValue':'nWorks Technologies (India) Pvt. Ltd., 206 Garden Plaza, Rahatani, Pune, Maharashtra, 411 017, INDIA', 'latitude':random.choice(latitude), 'longitude':random.choice(longitude), 'locationName':'Haveli Maharashtra India 411017', 'deviceId':'5DD7F175-7AB6-42B4-A25B-788DAB5FE60D'}, headers={'cookie':'JSESSIONID='+loginResponse.headers['Set-Cookie'].split(';')[0].split('=')[1]})
print(cameInResponse)
print(cameInResponse.text)
input("Press enter key to exit...")