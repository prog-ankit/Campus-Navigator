# Campus Navigator: University-College API Suite

This project, **Campus Navigator: University-College API Suite**, is a Spring Boot application designed to manage universities and colleges. It allows users to add, update, view, and delete universities and their associated colleges. The project features ORM mappings, follows a structured MVC pattern, and employs custom validators to ensure data integrity. MySQL is used as the database for storing the information, and the application leverages JPA repositories for database interactions.

## Features
- **Add University and College**: Add new universities and the colleges that fall under them.
- **View Universities and Colleges**: Retrieve all universities and their colleges, or specific university/college details.
- **Update University and College**: Update information of specific or multiple universities and colleges.
- **Delete University and College**: Remove universities or colleges from the database.
- **Bulk Operations**: Perform bulk updates on universities and colleges.

## Tech Stack
- **Backend**: Spring Boot, Spring MVC
- **ORM**: JPA (One-to-Many and Many-to-One mappings)
- **Mapping**: MapStruct (for DTO to Entity and vice versa mapping)
- **Database**: MySQL, JPA Repository
- **Validation**: Custom Validators
- **Architecture**: MVC (Model-View-Controller)
- **Security**: JWT Authentication using Spring Security and Role-Based Authentication

## How to Use this Repository

1. **Clone the Repository**:
  <pre><code>
   git clone https://github.com/your-username/campus-navigator-api.git
   cd campus-navigator-api
  </code></pre>
2. **Set Up Database:**
   <ul>  
     <li>Ensure that MySQL is running and create a database for this project.</li>
     <li>Update the database credentials in the application.properties file.</li>
  </ul>
  Example:
   <pre><code>
       spring.datasource.url=jdbc:mysql://localhost:3306/campus_navigator_db
       spring.datasource.username=root
       spring.datasource.password=yourpassword
   </code></pre>
   
3. **Run the Application:**
 <ul>  
    <li>Use the following command to build and run the project:</li><br />
    <pre><code>./mvnw spring-boot:run</code></pre>
 </ul>

 4. **API Testing:**
 <ul><li>You can use Postman or any API testing tool to interact with the available endpoints.</li></ul>
  API Endpoints
<h2>API Endpoints</h2> <h3>University API</h3> <ul> <li><code>POST /api/university/add</code>: Add a new university</li> <li><code>GET /api/university/</code>: Get all universities</li> <li><code>GET /api/university/{university_id}</code>: Get a specific university</li> <li><code>DELETE /api/university/{university_id}</code>: Delete a university</li> <li><code>PUT /api/university/{university_id}</code>: Update a university</li> <li><code>PUT /api/university/updatebulk</code>: Update multiple universities</li> </ul> <h3>College API</h3> <ul> <li><code>POST /api/university/{university_id}/college/add</code>: Add a new college to a university</li> <li><code>GET /api/university/{university_id}/colleges</code>: Get all colleges for a university</li> <li><code>DELETE /api/university/{university_id}/college/delete/{college_id}</code>: Delete a college</li> <li><code>PUT /api/university/{university_id}/college/update</code>: Update a college</li> <li><code>PUT /api/university/{university_id}/colleges/update</code>: Update multiple colleges</li> </ul>  <p>This README file provides a brief overview of the project. For more information, please explore the codebase and try hitting API. </p>
