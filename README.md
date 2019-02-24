# AWS_Web
import urllib
import sys
import requests
import zipfile
import io
url = 'https://www2.census.gov/geo/tiger/GENZ2017/shp/cb_2017_02_tract_500k.zip'
target_path = 'alaska.zip'

r = requests.get(url, stream =True)
check = zipfile.is_zipfile(io.BytesIO(r.content))
while not check:
    r = requests.get(url, stream =True)
    check = zipfile.is_zipfile(io.BytesIO(r.content))
else:
    z = zipfile.ZipFile(io.BytesIO(r.content))
    z.extractall()
