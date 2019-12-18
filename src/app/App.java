package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import app.Article;

public class App {
    // Nom du logiciel permettant de se connecter à MariaDB
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    // Adresse de la base de données à laquelle se connecter (remplacer "test"
    // par le nom de votre base de données)
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/test";
    // Nom d'utilisateur/mot de passe permettant de s'authentifier auprès de MariaDB
    static final String USERNAME = "root";
    static final String PASSWORD = "root";

    public static void main(String[] args) throws Exception {
        // Enregistre le pilote de la base de données
        System.out.println("Registering MariaDB driver...");
        Class.forName(JDBC_DRIVER);
        
        // Etablit la connexion avec la base de données
        System.out.println("Connecting to database...");
        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        
        // Crée une nouvelle requête
        System.out.println("Creating new request...");
        Statement statement = connection.createStatement();
        
        // Exécute une requête SQL
        System.out.println("Executing SQL query...");
        ResultSet resultSet = statement.executeQuery(
            "SELECT * FROM `article` WHERE `author` = 1 ORDER BY `created_at` DESC"
        );

        // Traite chaque résultat de la requête, jusqu'à ce que les résultats
        // soient épuisés
        List<Article> articlesList = new ArrayList<>();

        while (resultSet.next()) {
            // Crée un nouvel objet de type Article
            Article article = new Article();
            // Peuple l'objet avec les données tirées de la base
            article.id = resultSet.getInt(1);
            article.title = resultSet.getString(2);
            article.created_at = resultSet.getDate(3);
            article.updated_at = resultSet.getDate(4);
            article.content = resultSet.getString(5);
            article.author = resultSet.getInt(6);
            // Ajoute l'article à une liste
            articlesList.add(article);
        }

        // Pour chaque article enregistré dans la liste
        for (Article item: articlesList) {
            // Affiche les propriétés de l'article
            item.inspect();
        }
    }
}