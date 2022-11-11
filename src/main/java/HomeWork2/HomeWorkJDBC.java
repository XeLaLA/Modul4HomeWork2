package HomeWork2;

public class HomeWorkJDBC {
    public static void main(String[] args) {
        ManagerBD.createTable();
        ManagerBD.insertUser("Albert", "Albert123", "2012.10.10 23:00:30");
        ManagerBD.insertUser("Vadim", "Vadim123", "2015.05.15 15:15:20");
        ManagerBD.insertUser("Petr", "Petr123", "2016.06.16 16:16:25");
        ManagerBD.insertUser("Denis", "Denis123", "2018.08.18 18:18:25");
        ManagerBD.insertUser("Anton", "Anton123", "2013.07.15 10:17:25");
        ManagerBD.insertUser("Denis", "Denis123", "2014.06.12 16:22:25");

        ManagerBD.insertPost("Мой первый пост", "2012.10.11 08:00:30", "1");
        ManagerBD.insertPost("Java Maven, JDBC ", "2015.10.10 23:00:20", "2");
        ManagerBD.insertPost("Как сделать вино в домашних условиях", "2016.12.12 20:00:25","3");
        ManagerBD.insertPost("Первые шаги в JAVA", "2016.10.11 08:00:30", "1");

        ManagerBD.insertComment("Первый пост. Почитаем.", "1", "6", "2012.10.12 11:10:25");
        ManagerBD.insertComment("Урааа!! Java", "2", "4", "2018.09.05 10:10:25");
        ManagerBD.insertComment("ООО!!! Винишко!!", "3", "5", "2018.10.05 11:10:25");
        ManagerBD.insertComment("Учим JAVA!!!", "2", "1", "2018.10.05 12:10:25");

        ManagerBD.insertLike("4","1", "null");
        ManagerBD.insertLike("5","null", "3");
        ManagerBD.insertLike("5","2", "null");
        ManagerBD.insertLike("6","2", "null");
        ManagerBD.insertLike("6","null", "2");
        ManagerBD.insertLike("6","null", "null");

        ManagerBD.printStatistic();
        ManagerBD.printUser("1");
        ManagerBD.printUser("7");
        ManagerBD.dropTable();
    }
}
