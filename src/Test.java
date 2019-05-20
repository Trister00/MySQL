import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {

        Hebergement h = new Hebergement(22,"Appartement","Nord",4,300);
        DAO HebDao = new HebergementDao();
       // System.out.println(HebDao.add(h));
        Hebergement h2 = new Hebergement(33,"Bungalows","Nord",6,250);
        Hebergement h3 = new Hebergement(12,"Appartement","Nord",4,700);
        //HebDao.add(h2);
        //HebDao.add(h3);
        System.out.println(HebDao.get(3));
        //HebDao.delete(h2);
        System.out.println(HebDao.getAll());
    }
    private static Connection getConnection()
    {
        Connection connection = null;
        try
        {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test" ,"root","");
            return connection;
        }
        catch (SQLException e)
        {
            for (Throwable t : e)
                e.printStackTrace() ; return null;
        }
    }
}
