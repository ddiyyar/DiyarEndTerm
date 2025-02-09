Overview of Project

This project represents a book manager with user registration and authentication. It includes the following classes:

## Classes

### Main

The class `Main` is the entry point of this application. It provides the interaction of users with the system - menus for registration, login, adding books, searching for books (by ISBN, author, title), and the ability to view all books.

### DatabaseManager

DatabaseManager class is responsible for managing the database connection. It uses the Singleton Design Pattern to ensure a single database connection.

#### Fields
- `dbUrl`: Database URL
- `dbUser`: Database user
- `dbPassword`: Database password

#### Methods
- `getInstance(String dbUrl, String dbUser, String dbPassword)`: Returns the singleton instance of `DatabaseManager`
- `getDbUrl()`: Returns the database URL
- `getDbUser()`: Returns the database user
- `getDbPassword()`: Returns the database password

### Book

The `Book` class is a model that represents a book. It includes ISBN, title, author, publication date, and link.

#### Fields

* `isbn`: Book ISBN
* `title`: Book title
* `author`: Book author
* `publishedDate`: Publish date
* `readCount`: Read count
* `link`: Link to the book

#### Methods

* Getters and setters for all fields

### User

The abstract class of `User` represents the general user.

#### Fields

* `username`: Username
* `password`: User password
* `email`: User email

#### Methods

* Getters and setters
* Abstract method `isAdmin()`: returns `true` if the user is an admin, and `false` otherwise.

### AdminUser

The `AdminUser` class extends the class `User`, and it should represent a system administrator.

#### Methods

* `isAdmin()`: Returns `true`

### RegularUser

The `RegularUser` class extends the class `User` and should represent a regular system user.

#### Methods

- `isAdmin()`: Returns `false`

### UserRegistration

The class `UserRegistration` is responsible for user registration and authentication in the system.

#### Methods
- `registerUser(String username, String password, boolean isAdmin, String email)`: Signs up a new user in the database
- `loginUser(String username, String password)`: Logs in a user

### BookManager

The `BookManager` class is responsible for managing books in the system.

#### Methods

- `addBook(Book book)`: Adds a book to the database
- `getBookByISBN(String isbn)`: Retrieves a book by ISBN
- `getBookByTitle(String title)`: Retrieves a book by title
- `getBookByAuthor(String author)`: Retrieves a book by author
- `getAllBooks()`: Returns the list of all books

## Design Patterns

The following design patterns have been implemented in the project:

### Singleton

The class `DatabaseManager` uses the Singleton pattern for providing one single instance of database connection; that way, there cannot be several instances of this class.

### Factory Method

The `UserRegistration` class uses a Factory Method for instantiating either an `AdminUser` or a `RegularUser` based on the field `isAdmin`.


cd /c/Users/diyar/IdeaProjects/YessetDiyarEndTerm
git add .
git commit -m "TEXT"
git push