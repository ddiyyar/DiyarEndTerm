import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
            String dbUser = "postgres";
            String dbPassword = "159159";
            Scanner scanner = new Scanner(System.in);
            DatabaseManager dbManager = DatabaseManager.getInstance(dbUrl, dbUser, dbPassword);
            UserRegistration registration = new UserRegistration(dbManager);
            BookManager bookManager = new BookManager(dbManager);
            boolean isAuthenticated = false;
            User currentUser = null;

            while (!isAuthenticated) {
                System.out.println("Выберите действие:");
                System.out.println("1. Register");
                System.out.println("2. Login");
                String choice = scanner.nextLine();
                if (choice.equals("1")) {
                    System.out.print("Введите имя пользователя: ");
                    String username = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String password = scanner.nextLine();
                    System.out.print("Вы будете добавлять/удалять книги (TRUE/FALSE): ");
                    boolean isAdmin = Boolean.parseBoolean(scanner.nextLine());
                    System.out.print("Введите email: ");
                    String email = scanner.nextLine();
                    boolean success = registration.registerUser(username, password, isAdmin, email);
                    if (success) {
                        System.out.println("Пользователь успешно зарегистрирован.");
                    } else {
                        System.out.println("Регистрация пользователя не удалась.");
                    }
                } else if (choice.equals("2")) {
                    System.out.print("Введите имя пользователя: ");
                    String username = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String password = scanner.nextLine();
                    currentUser = registration.loginUser(username, password);
                    if (currentUser != null) {
                        System.out.println("Логин успешен. Добро пожаловать, " + username + "!");
                        isAuthenticated = true;
                    } else {
                        System.out.println("Логин не удался. Проверьте имя пользователя и пароль.");
                    }
                }
            }

            while (true) {
                System.out.println("Выберите действие:");
                System.out.println("1. Добавить книгу");
                System.out.println("2. Найти книгу по ...");
                System.out.println("3. Показать все книги");
                System.out.println("4. Выйти");
                String option = scanner.nextLine();
                if (option.equals("1")) {
                    if (currentUser.isAdmin()) {
                        System.out.print("Введите ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Введите название книги: ");
                        String title = scanner.nextLine();
                        System.out.print("Введите автора: ");
                        String author = scanner.nextLine();
                        System.out.print("Введите дату публикации (YYYY-MM-DD): ");
                        String dateStr = scanner.nextLine();
                        Date publishedDate = java.sql.Date.valueOf(dateStr);
                        System.out.print("Введите ссылку на книгу (если есть): ");
                        String link = scanner.nextLine();
                        Book book = new Book(isbn, title, author, publishedDate, 0, 0, 0, link.isEmpty() ? null : link);
                        boolean success = bookManager.addBook(book);
                        if (success) {
                            System.out.println("Книга успешно добавлена.");
                        } else {
                            System.out.println("Не удалось добавить книгу.");
                        }
                    } else {
                        System.out.println("У вас нет прав для добавления книг.");
                    }
                } else if (option.equals("2")) {
                    System.out.println("1. Поиск книги по ISBN");
                    System.out.println("2. Поиск книги по названию");
                    System.out.println("3. Поиск книги по автору");
                    String temp = scanner.nextLine();
                    if (temp.equals("1")) {
                        System.out.print("Введите ISBN: ");
                        String isbn = scanner.nextLine();
                        Book book = bookManager.getBookByISBN(isbn);
                        if (book != null) {
                            System.out.println("Информация о книге:");
                            System.out.println("Название: " + book.getTitle());
                            System.out.println("Автор: " + book.getAuthor());
                            System.out.println("Дата публикации: " + String.valueOf(book.getPublishedDate()));
                            System.out.println("Количество прочтений: " + book.getReadCount());
                            System.out.println("Лайки: " + book.getLikes());
                            System.out.println("Дизлайки: " + book.getDislikes());
                            System.out.println("Ссылка: " + book.getLink());
                        } else {
                            System.out.println("Книга с ISBN " + isbn + " не найдена.");
                        }
                    } else if (temp.equals("2")) {
                        System.out.print("Введите название: ");
                        String title = scanner.nextLine();
                        Book book = bookManager.getBookByTitle(title);
                        if (book != null) {
                            System.out.println("Информация о книге:");
                            System.out.println("Название: " + book.getTitle());
                            System.out.println("Автор: " + book.getAuthor());
                            System.out.println("Дата публикации: " + String.valueOf(book.getPublishedDate()));
                            System.out.println("Количество прочтений: " + book.getReadCount());
                            System.out.println("Лайки: " + book.getLikes());
                            System.out.println("Дизлайки: " + book.getDislikes());
                            System.out.println("Ссылка: " + book.getLink());
                        } else {
                            System.out.println("Книга под названием \"" + title + "\" не найдена.");
                        }
                    } else if (temp.equals("3")) {
                        System.out.print("Введите автора: ");
                        String author = scanner.nextLine();
                        Book book = bookManager.getBookByAuthor(author);
                        if (book != null) {
                            System.out.println("Информация о книге:");
                            System.out.println("Название: " + book.getTitle());
                            System.out.println("Автор: " + book.getAuthor());
                            System.out.println("Дата публикации: " + String.valueOf(book.getPublishedDate()));
                            System.out.println("Количество прочтений: " + book.getReadCount());
                            System.out.println("Лайки: " + book.getLikes());
                            System.out.println("Дизлайки: " + book.getDislikes());
                            System.out.println("Ссылка: " + book.getLink());
                        } else {
                            System.out.println("Книги от " + author + " не найдены.");
                        }
                    }
                } else if (option.equals("3")) {
                    List<Book> books = bookManager.getAllBooks();
                    if (!books.isEmpty()) {
                        System.out.println("Список всех книг:");
                        for (Book book : books) {
                            System.out.println("ISBN: " + book.getIsbn());
                            System.out.println("Название: " + book.getTitle());
                            System.out.println("Автор: " + book.getAuthor());
                            System.out.println("Дата публикации: " + String.valueOf(book.getPublishedDate()));
                            System.out.println("Количество прочтений: " + book.getReadCount());
                            System.out.println("Лайки: " + book.getLikes());
                            System.out.println("Дизлайки: " + book.getDislikes());
                            System.out.println("Ссылка: " + book.getLink());
                            System.out.println();
                        }
                    } else {
                        System.out.println("Книги не найдены.");
                    }
                } else if (option.equals("4")) {
                    System.out.println("Выход...");
                    scanner.close();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
