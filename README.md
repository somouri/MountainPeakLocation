# Mountain Peak API

Spring Boot + Hibernate Spatial + PostGIS +Docker

## How to run
   
#### using docker-compose

**REQUIRED: Docker is running**

```
mvn clean install -Dmaven.test.skip=true
docker-compose up
```

## Goals
- [ ] CRUD for peak data
    - [x] [POST] : `/peak`
    - [x] [GET] by ID : `/peak/{id}`
    - [x] [PUT] : `/peak/{id}`
    - [x] [DELETE] : `/peak/{id}`
    - [x] [GET] : `/peaks`
    - [x] [POST] Get peaks within Bounding box) : `/peaks/bbox`

### Sample Data for POST (`/peak`)
```
{
  "altitude": 5000,
  "id": 0,
  "lat": 10,
  "lon": 20,
  "name": "mountain Name"
}
```
### Data Format for getting peaks in Bounding box (`/peak`)
```
Xmin,Ymin,Xmax,Ymax
```
#### Example
```
-10.23,-20.3,10,20
```
