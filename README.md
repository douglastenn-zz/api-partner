API Partner
------------

## About

A simple REST API to handle partners and associate with campaigns

## Dependencies 

- Docker
- Docker Compose
- Spring Boot
- Spring Data
- MongoDB
- Maven
- [API Campaign](https://github.com/douglastenn/api-campaign)

## API Paths

| HTTP METHOD | PATH                      | ACTION        | DESCRIPTION                               |
|-------------|---------------------------|---------------|-------------------------------------------|
| POST        | /api/partner              | create        | Create a new partner                      |
| POST        | /api/partner/:campaignId  | associate     | Associate partner with active campaigns   |
| GET         | /api/partner/:partnerId   | getAssociate  | Display all associated campaigns          |


## Running

You will need a mongo running locally on port 27017 and the API Campaign on port 8080.

To run the application you can execute the **Main Class (PartnerApplication)**.

This application will run on port 8081.

## Examples

### Creating a new partner

To create a new partner, you must run as shown below:

**POST:** /api/partner

**Body:**
```
{
	"name": "Douglas Tenório",
	"email": "douglas.develop@gmail.com",
	"birthDate": "1991-09-07",
	"teamId": 10
}
```

Example:

```
curl -H "Content-Type: application/json" \
    -X POST -d '{"name": "Douglas Tenório", "email": "douglas.develop@gmail.com", "birthDate": "1991-09-07", "teamId": 10}' \
    "http://localhost:8081/api/partner"
```

Result:

```
{
    "id": "5971600f5cb92c1d510c60bd",
    "name": "Douglas Tenório",
    "email": "douglas.develop@gmail.com",
    "birthDate": "1991-09-07",
    "teamId": 10
}
```

### Associate partner with campaigns

To create a new partner, you must run as shown below:

**POST:** /api/partner/associate

**Body:**
```
{
	"partnerId": "5971600f5cb92c1d510c60bd"
}
```

Example:

```
curl -H "Content-Type: application/json" \
    -X POST -d '{"partnerId": "5971600f5cb92c1d510c60bd" }' \
    "http://localhost:8081/api/partner/associate"
```

Result:

```
[
    {
        "id": "5971607e5cb92c1d510c60bf",
        "partnerId": "5971600f5cb92c1d510c60bd",
        "teamId": 10,
        "campaignId": "597117195cb92c0a9b6bded3"
    },
    {
        "id": "5971607e5cb92c1d510c60c0",
        "partnerId": "5971600f5cb92c1d510c60bd",
        "teamId": 10,
        "campaignId": "5971171d5cb92c0a9b6bded4"
    }
]
```

### Getting a list of associated campaigns

To get a list of associated campaigns, you must run as shown below:

**GET:** /api/partner/:campaignId

Examples:

```
curl -H "Content-Type: application/json" \
    -X GET "http://localhost:8081/api/partner/associate/59715ae85cb92c1ca0cd1f3f"
```

Result:

```
[{
	"id": "59715aef5cb92c1ca0cd1f40",
	"partnerId": "59715ae85cb92c1ca0cd1f3f",
	"teamId": 10,
	"campaignId": "597117195cb92c0a9b6bded3"
}, {
	"id": "59715aef5cb92c1ca0cd1f41",
	"partnerId": "59715ae85cb92c1ca0cd1f3f",
	"teamId": 10,
	"campaignId": "5971171d5cb92c0a9b6bded4"
}]
```

