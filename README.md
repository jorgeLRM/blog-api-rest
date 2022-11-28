# Blog API REST

### Introduction
Blog API is a REST API that allow to do and comment posts.

### Blog API Features
* Create new post
* List all posts
* Update post
* Delete post
* Create comment
* List all comment by post

### Installation Guide
* Clone this repository [here](https://github.com/jorgeLRM/blog-api-rest.git).
* cd blog-api-rest
* ./mvn package
* java -jar target/*.jar

### API Endpoints
| HTTP Verbs | Endpoints | Action |
| --- | -- | --- |
| POST | /api/auth/login | Login to generate Token |
| POST | /api/auth/register | Create new user |
| GET | /api/posts | Find all posts |
| GET | /api/posts/{id} | Find post by id |
| POST | /api/posts | Create new post |
| PUT | /api/posts | Update post |
| DELETE | /api/posts/{id} | Delete post by id |
| GET | /api/posts/{postId}/comments | Find comments by post id |
| GET | /api/posts/{postId}/comments/{commentId} | Find comment by id |
| POST | /api/posts/{postId}/comments | Create new comment |
| PUT | /api/posts/{postId}/comments/{commentId} | Update comment by id |
| DELETE | /api/posts/{postId}/comments/{commentId} | Delete comment by id |

### Technologies Used
* [Spring boot](https://spring.io/projects/spring-boot)
* [MySQL](https://www.mysql.com/)
* [Hibernate](https://hibernate.org/)