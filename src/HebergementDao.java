

import java.sql.*;
import java.util.ArrayList;

public class HebergementDao implements DAO<Hebergement> {

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
    public ArrayList<Hebergement> getAll() {
       String sql = "SELECT numero,type,localisation,nb_max,prix_nuit FROM hebergement";
       ArrayList<Hebergement> h = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int num = rs.getInt("numero");
                String type = rs.getString("type");
                String localisation = rs.getString("localisation");
                int nb_max = rs.getInt("nb_max");
                int prix = rs.getInt("prix_nuit");
                Hebergement tmp = new Hebergement(num,type,localisation,nb_max,prix);
                h.add(tmp);
            }
            return h;

        }
        catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public boolean add(Hebergement h){
       String sql = "INSERT INTO hebergement(numero,type,localisation,nb_max,prix_nuit) VALUES(?,?,?,?,?)";
       try(Connection connection = getConnection();
           PreparedStatement ps = connection.prepareStatement(sql)) {

           ps.setInt(1,h.getNumero());
           ps.setString(2,h.getType());
           ps.setString(3,h.getLocalisation());
           ps.setInt(4,h.getNb_max());
           ps.setInt(5,h.getPrix_nuit());
           ps.executeUpdate();
           return true;

       }
       catch (SQLException e) {
           System.err.println(e);
           return false;
       }
    }
     public boolean update(Hebergement h) {
         String sql = "UPDATE hebergement SET nb_max=?,prix_nuit=? WHERE numero=? AND type=?";
         try(Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

             ps.setInt(1,h.getNb_max());
             ps.setInt(2,h.getPrix_nuit());
             ps.setInt(3,h.getNumero());
             ps.setString(4,h.getType());
             ps.executeUpdate();
             return true;

         }
         catch (SQLException e) {
             System.err.println(e);
             return false;
         }
    };
    public boolean delete(Hebergement h){
        String sql = "DELETE FROM hebergement WHERE numero=? AND type=?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setInt(1,h.getNumero());
            ps.setString(2,h.getType());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }

    };
    public Hebergement get(int i){
        String sql = "SELECT * FROM hebergement WHERE id =?";
        try(Connection connection = getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,i);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                int num = rs.getInt("numero");
                String type = rs.getString("type");
                String localisation = rs.getString("localisation");
                int nb_max = rs.getInt("nb_max");
                int prix = rs.getInt("prix_nuit");
                Hebergement h = new Hebergement(num,type,localisation,nb_max,prix);
                return h;
            }
            else {
                rs.close();
                return null;
            }
        }
        catch (SQLException e){
            System.err.println(e);
            return null;
        }
    };

}
