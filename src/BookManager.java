import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookManager {
    private DatabaseManager dbManager;

    public BookManager(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public boolean addBook(Book book) {
        String sql = "INSERT INTO books (isbn, title, author, published_date, read_count, likes, dislikes, link) VALUES (?, ?, ?, ?, 0, 0, 0, ?)";

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, book.getIsbn());
            pst.setString(2, book.getTitle());
            pst.setString(3, book.getAuthor());
            pst.setDate(4, new Date(book.getPublishedDate().getTime()));
            pst.setString(5, book.getLink());
            int affectedRows = pst.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public Book getBookByISBN(String isbn) {
        String sql = "SELECT * FROM books WHERE isbn = ?";

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, isbn);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setIsbn(rs.getString("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublishedDate(rs.getDate("published_date"));
                    book.setReadCount(rs.getInt("read_count"));
                    book.setLikes(rs.getInt("likes"));
                    book.setDislikes(rs.getInt("dislikes"));
                    book.setLink(rs.getString("link"));
                    return book;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Book getBookByTitle(String title) {
        String sql = "SELECT * FROM books WHERE title = ?";

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, title);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setIsbn(rs.getString("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublishedDate(rs.getDate("published_date"));
                    book.setReadCount(rs.getInt("read_count"));
                    book.setLikes(rs.getInt("likes"));
                    book.setDislikes(rs.getInt("dislikes"));
                    book.setLink(rs.getString("link"));
                    return book;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Book getBookByAuthor(String author) {
        String sql = "SELECT * FROM books WHERE author = ?";

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, author);

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    Book book = new Book();
                    book.setIsbn(rs.getString("isbn"));
                    book.setTitle(rs.getString("title"));
                    book.setAuthor(rs.getString("author"));
                    book.setPublishedDate(rs.getDate("published_date"));
                    book.setReadCount(rs.getInt("read_count"));
                    book.setLikes(rs.getInt("likes"));
                    book.setDislikes(rs.getInt("dislikes"));
                    book.setLink(rs.getString("link"));
                    return book;
                } else {
                    return null;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<Book> getAllBooks() {
        String sql = "SELECT * FROM books";
        List<Book> books = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(dbManager.getDbUrl(), dbManager.getDbUser(), dbManager.getDbPassword());
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                Book book = new Book();
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublishedDate(rs.getDate("published_date"));
                book.setReadCount(rs.getInt("read_count"));
                book.setLikes(rs.getInt("likes"));
                book.setDislikes(rs.getInt("dislikes"));
                book.setLink(rs.getString("link"));
                books.add(book);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return books;
    }
}
