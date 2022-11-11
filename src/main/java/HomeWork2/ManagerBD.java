package HomeWork2;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.stream.Collectors;
@UtilityClass
public class ManagerBD {
    private final String url = "jdbc:postgresql://localhost:5432/postgres";
    private final String user = "postgres";
    private final String password = "1";
    private Connection connection = null;

    //Подключение к БД
    private Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, user, password);
            }
        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.err.println("Не удалось подключиться к БД");
        }
        return connection;
    }
    //Чтение из файла из папки Resources
    private String readSqlFile(String filename) {
        InputStream resource = ManagerBD.class.getClassLoader().getResourceAsStream(filename);
        return new BufferedReader(new InputStreamReader(resource)).lines().collect(Collectors.joining(""));
    }

    //Метод создания таблиц из файла CreateTable.sql
    public void createTable() {
        Connection connection = getConnection();
        if (connection == null) return;

        try (Statement statement = connection.createStatement()) {
            statement.execute(readSqlFile("CreateTable.sql"));
//            if(tableExists(connection,"user_table") && tableExists(connection,"post") &&
//                tableExists(connection,"comment") && tableExists(connection,"like_table"))
//                System.out.println("Таблицы: user_table, post, comment, like_table созданы.");
            connection.close();
        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.out.println("Ошибка: Таблицы: user_table, post, comment, like_table не созданы.");
        }
    }

    public void dropTable() {
        Connection connection = getConnection();
        if (connection == null) return;

        try (Statement statement = connection.createStatement()) {
            statement.execute(readSqlFile("DropTable.sql"));
//            if(!tableExists(connection,"user_table") && !tableExists(connection,"post") &&
//                    !tableExists(connection,"comment") && !tableExists(connection,"like_table"))
//                System.out.println("Таблицы: user_table, post, comment, like_table удалены.");
            connection.close();
        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.out.println("Ошибка: Таблицы: user_table, post, comment, like_table не удалены.");
        }
    }
    //Запись данных в таблицу user_table
    //INSERT INTO user_table (name, password, created_at) values ('Albert','Albert123','2012.10.10 23:00:30');

    public void insertUser(String name, String password, String date)
    {
        Connection connection = getConnection();
        if (connection == null) return;

        try (Statement statement = connection.createStatement()) {

           int insertRow = statement.executeUpdate("INSERT INTO user_table (name, password, created_at) values ('" + name + "','"
                    + password + "','" + date + "');");
//            if(insertRow == 1) {
//                System.out.printf("Данные: %s - %s - %s в таблицу user_table вставлены.", name, password, date);
//                System.out.println();
//            }
//            else {
//                System.out.printf("Ошибка: Данные: %s - %s - %s в таблицу user_table не вставлены.", name, password, date);
//                System.out.println();
//            }
            connection.close();

        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.out.printf("Ошибка: Данные: %s - %s - %s в таблицу user_table не вставлены.", name, password, date);
            System.out.println();
        }
    }
    //insert into post (text, create_at, user_id) values ('albert einstein Post', '2022.11.09',1);
    public void insertPost(String text, String date, String user_id)
    {
        Connection connection = getConnection();
        if (connection == null) return;

        try (Statement statement = connection.createStatement()) {

            int insertRow = statement.executeUpdate("INSERT INTO post (text, created_at, user_id) values ('" + text + "','"
                     + date + "'," + user_id+");");

//            if(insertRow == 1) {
//                System.out.printf("Данные: %s - %s - %s в таблицу post вставлены.", text, date, user_id);
//                System.out.println();
//            }
//            else {
//                System.out.printf("Ошибка: Данные: %s - %s - %s в таблицу post не вставлены.", text, date, user_id);
//                System.out.println();
//            }
            connection.close();

        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.out.printf("Ошибка: Данные: %s - %s - %s в таблицу post не вставлены.", text, date, user_id);
            System.out.println();
        }
    }
    //insert into comment (text, post_id, user_id, created_at) values ('dred sudarr Comment',1,3 ,'2022.11.12');

    public void insertComment(String text, String post_id, String user_id, String date)
    {
        Connection connection = getConnection();
        if (connection == null) return;

        try (Statement statement = connection.createStatement()) {

            int insertRow = statement.executeUpdate("INSERT INTO comment (text, post_id, user_id, created_at) values ('" + text + "',"
                    + post_id + "," + user_id + ",'" + date + "');");

//            if(insertRow == 1) {
//                System.out.printf("Данные: %s - %s - %s - %s в таблицу comment вставлены.", text, post_id, user_id, date);
//                System.out.println();
//            }
//            else {
//                System.out.printf("Ошибка: Данные: %s - %s - %s - %s в таблицу comment вставлены.", text, post_id, user_id, date);
//                System.out.println();
//            }
            connection.close();

        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.out.printf("Ошибка: Данные: %s - %s - %s - %s в таблицу comment вставлены.", text, post_id, user_id, date);
            System.out.println();
        }
    }
    //insert into like_table (user_id, post_id, comment_id) values (1,2, null);
    public void insertLike(String user_id, String post_id, String comment_id)
    {
        Connection connection = getConnection();
        if (connection == null) return;


        try (Statement statement = connection.createStatement()) {

            int insertRow = statement.executeUpdate("INSERT INTO like_table (user_id, post_id, comment_id) " +
                        "values (" + user_id + "," + post_id + ","+ comment_id+");");

//            if(insertRow == 1) {
//                System.out.printf("Данные: %s - %s - %s в таблицу like_table вставлены.", user_id, post_id, comment_id);
//                System.out.println();
//            }
//            else {
//                System.out.printf("Ошибка: Данные: %s - %s - %s в таблицу like_table не вставлены.", user_id, post_id, comment_id);
//                System.out.println();
//            }
            connection.close();

        } catch (SQLException exception) {
            //System.err.println(exception.getMessage());
            System.out.printf("Ошибка: Данные: %s - %s - %s в таблицу like_table не вставлены.", user_id, post_id, comment_id);
            System.out.println();
        }
    }

    public void printStatistic(){
            System.out.println("______________________________");
            System.out.println("| User | Post | Comment| Like|");
            System.out.printf("|  %s   |   %s  |    %s   |  %s  |", countRowsTable("user_table"), countRowsTable("post"),
                    countRowsTable("comment"), countRowsTable("like_table"));
            System.out.println();
            System.out.println("-------------------------------");
            System.out.println();
    }

    private void printColumnNames(ResultSet resultSet) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount()-1;
        for (int i = 1; i <= columnCount; i++) {
            System.out.printf("%s   | ", resultSet.getMetaData().getColumnName(i));
        }
        System.out.println("\n________________________________________________________");
    }

    private void printDataFromResultUser(ResultSet resultSet) throws SQLException {
        printColumnNames(resultSet);
            int columnCount = resultSet.getMetaData().getColumnCount()-1;
            for (int i = 1; i <= columnCount ; i++)
                System.out.printf("%s      | ", resultSet.getString(i));
            System.out.println();
    }
    public void printUser(String user_id)
    {
        Connection connection = getConnection();
        if (connection == null) return;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select data.name as Пользователь, " +
                            "data.created_at as Дата_создания, data.text as Самый_первый_пост, count(C.id) as Количество_комментов, data.post_id " +
                            "from (select UT.id as user_id, P.id as post_id, name, UT.created_at, P.text " +
                            "from user_table UT inner join post P on UT.id=P.user_id where UT.id="+ user_id +") data " +
                            "inner join comment C on data.user_id= C.user_id where data.user_id="+ user_id +
                            "group by data.name, data.created_at, data.text, data.post_id ORDER BY data.post_id ASC " + "Limit 1;");
            connection.close();
            if(resultSet.next()) {
                printDataFromResultUser(resultSet);
                System.out.println();
            }
            else {
                System.out.println("Пользователь не найден");
                System.out.println();
            }
        } catch (SQLException exception) {
//            System.err.println(exception.getMessage());
              System.out.println("Пользователь не найден");
              System.out.println();
        }
    }
    private int countRowsTable(String table)
    {
        Connection connection = getConnection();
        if (connection == null) return -1;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select count(id) from " + table + ";");
            connection.close();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException exception) {
            System.err.println(exception.getMessage());
            return -1;
        }
    }

    private boolean tableExists(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, "public", tableName, new String[]{"TABLE"});
        return tables.next();
    }
}