**Spring Boot Secrets**

```yaml



We use @AllArgsConstructor  to generate a constructor for all of your 
class's fields and use @RequiredArgsConstructor to generate 
a constructor for all class's fields that are marked as final.


It is good practice to separate your model entities and dto(s).

(Data transfer Objects)

```


**Alternative to Mongo URI**

```yaml


spring.data.mongodb.authentication-database=admin

spring.data.mongodb.username=admin

spring.data.mongodb.password=password

spring.data.mongodb.database=product-service

spring.data.mongodb.port=27017

spring.data.mongodb.host=localhost

```

**Dependency Management Container**

```yaml

It helps us in managing our dependencies.

Eg you can specify a specific version for grouped dependencies
in the dependency container.


```

**Testing secrets**

```
When writing tests we use a dummy database, we cannot use the
production level DB


@DynamicPropertySource

Will replace the local mongo db uri to the one i am using to test
once i start running my tests


```

**POJO Object **

```yaml

Stands for a Plain Old Java Object

Object Mapper package from Jackson, convers a POJO to a Json Object(String Format)
and Vice Versa.


```

**Assignment**

```yaml

Implement getaLL TESTS For the product microservice

as well as the other services
```