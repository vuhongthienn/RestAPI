# RESTful Api CRUD member management.

#### Technologies:

- Java 8
- Spring Boot
- MySQL



#### The project include :
#### 1) designing Table with types:
- a. String
- b. BigDecimal
- c. Date
- d. Integer
- e. use Long as the type of ID, auto increment
#### 2. The Query functions must have Dynamic Condition Query.
`    @Query(value = "SELECT *\n" +
"FROM tbl_member\n" +
"ORDER BY member_id\n" +
"LIMIT :itemsPerPage OFFSET :startIndex", nativeQuery = true)
List<Member> findAll(@Param("itemsPerPage") int itemsPerPage, @Param("startIndex") int startIndex);`
#### 3. Paging query is required
`    @Query(value = "SELECT * FROM tbl_member WHERE member_id =:memberId AND block =:block", nativeQuery = true)
Optional<Member> findByIdAndBlock(@Param("memberId") Long memberId, @Param("block") boolean block);`
## Guide run project.
## List api:
- (GET) http://localhost:8080/api/v1/members/{memberId}?enable={enable}
- (PUT) http://localhost:8080/api/v1/members/{memberId}
- (DELETE) http://localhost:8080/api/v1/members/{memberId}
- (GET) http://localhost:8080/api/v1/members
- (POST) http://localhost:8080/api/v1/members



- **change config in file `application-dev.properties` with your config database when start project.**

## Use this link to test API.
- http://localhost:8080/swagger-ui/index.html





