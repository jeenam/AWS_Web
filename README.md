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
    
    import yaml

with open("host_data.yaml",'r') as stream :
  data_loaded = yaml.load(stream)

for element in data_loaded:
  address=element['gms']['localhost1']['address']
  username=element['gms']['localhost1']['username']
  password=element['gms']['localhost1']['password']
  hostname=element['gms']['localhost1']['hostname']
