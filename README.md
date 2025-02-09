# Project Overview

Проект представляет собой систему управления книгами с регистрацией пользователей и авторизации. Используются следующие классы:

## Классы

### Main

Класс Main является точкой входа в приложение. Он обеспечивает взаимодействие пользователя с системой, включая в себя меню для регистрации, входа в систему, добавления книг, поиска книг(по ISBN, по автору, по названию) и с возможностью просмотра всех книг.

### DatabaseManager

DatabaseManager класс отвечающий за управление подключением к базе данных. Использует design pattern 'Singleton' для обеспечения единственного подключения к базе данных.

#### Поля
- dbUrl: URL базы данных
- dbUser: Пользователь базы данных
- dbPassword: Пароль базы данных

#### Методы
- getInstance(String dbUrl, String dbUser, String dbPassword): Возвращает единственный экземпляр "DatabaseManager"
- getDbUrl(): Возвращает URL базы данных
- getDbUser(): Возвращает имя базы данных
- getDbPassword(): Возвращает пароль базы данных

### Book

Класс Book представляет собой модель книги. Он содержит информацию о книге, такую как ISBN, название, автор, дата публикации и ссылка.

#### Поля
- isbn: ISBN книги
- title: Название книги
- author: Автор книги
- publishedDate: Дата публикации
- readCount: Количество прочтений
- link: Ссылка на книгу

#### Методы
- Геттеры и сеттеры для всех полей

### User

Абстрактный класс User, коротко говоря общее представление о пользователе.

#### Поля

* username: Username
* password: User password
* email: User email

#### Методы

* Геттеры и сеттеры
* Абстрактный метод isAdmin(): возвращает «True», если пользователь является администратором, и «False» в противном случае.

### AdminUser

Класс AdminUser расширяет класс User и представляет администратора системы.

#### Методы

* isAdmin(): Возвращает "true"

### RegularUser

Класс RegularUser наследует класс "User" и представляет обычного пользователя системы.

#### Методы
- isAdmin(): Возвращает "false"

### UserRegistration

Класс "UserRegistration" отвечает за регистрацию и авторизацию пользователей в системе.

#### Методы
- registerUser(String username, String password, boolean isAdmin, String email): Регистрирует нового пользователя в базе данных
- loginUser(String username, String password): Выполняет авторизацию пользователя

### BookManager

Класс "BookManager" отвечает за управление книгами в системе.

#### Методы
- addBook(Book book): Добавляет книгу в базу данных
- getBookByISBN(String isbn): Получает книгу по ISBN
- getBookByTitle(String title): Gets book by title
- getBookByAuthor(String author): Gets book by author
- getAllBooks(): Gives the list of all books

## Дизайн-паттерны

В проекте используются следующие дизайн-паттерны:

### Singleton (Одиночка)


cd /c/Users/diyar/IdeaProjects/YessetDiyarEndTerm
git add .
git commit -m "TEXT"
git push