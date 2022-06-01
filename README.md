# Imaginary Real Estate 

Application was created for university classes, subject basis of web application.

It uses a Java, Spring Boot, as engine for simple backend. Backend serves a web pages and host api and points.  

### Configuration 

To start server you need to set up some environment variables. 

For database use [PostgreSQL](https://www.postgresql.org). For dynamic file hosting use [Amazon Web Services](https://aws.amazon.com) S3 server.  
!!! IMPORTANT add public getObject privilege for whole bucket.
For email server use gmail.

config file

```shell
export AWS_ACCESS_KEY=
export AWS_PRIVATE_KEY=

export GMAIL_USER=
export GMAIL_PASSWORD=

export SPRING_DATASOURCE_URL=
export SPRING_DATASOURCE_USERNAME=
export SPRING_DATASOURCE_PASSWORD=
```

### Backend
The backend consists of three controllers. First of them is WebController. It consists all web calls where result is http document with dynamically generated data. Data was get from database via JPA and Hibernate.
To achieve that I prepare Model Classes for Offers, Front Image and Gallery Image.

#### Specific endpoints for WebController

` http  GET / `  
` http  GET /index `  
` http  GET /home `  
Returns index page.

` http GET /offers `  
Can consist request param ` searchPhrase `.
If param is empty it returns offers page with all available offers.
If param is not empty it returns search results.

` http GET /list `  
Returns listOffer page.

` http GET /details/{offerId} `  
Where offerId is long number.
It returns details page with dynamically generated offer details

#### Specific endpoints for ApiController

` http GET /api/v1/get/liked `  
Returns full list of offers with all details.

` http POST /api/v1/create `
It gets some request params
```
@RequestParam("title") Optional<String> title,
@RequestParam("email") Optional<String> email,
@RequestParam("address") Optional<String> address,
@RequestParam("price") Optional<Integer> price,
@RequestParam("area") Optional<Integer> area,
@RequestParam("bedrooms") Optional<Integer> bedrooms,
@RequestParam("bathrooms") Optional<Float> bathrooms,
@RequestParam("description") Optional<String> description,
@RequestParam("terms") Optional<Boolean> terms,

@RequestParam("front_photo") Optional<MultipartFile> frontPhoto,
@RequestParam("add_photo1") Optional<MultipartFile> addPhoto1,
@RequestParam("add_photo2") Optional<MultipartFile> addPhoto2,
@RequestParam("add_photo3") Optional<MultipartFile> addPhoto3,
@RequestParam("add_photo4") Optional<MultipartFile> addPhoto4,
@RequestParam("add_photo5") Optional<MultipartFile> addPhoto5
```
| Field name   | Is required? |      Type      | Description                   |
|:-------------|:-------------|:--------------:|:------------------------------|
| title        | true         | text (string)  | Is title of listed offer      |
| email        | true         |  text (email)  | Is offer owner email          | 
| address      | true         | text (string)  | Is address of listed property |




