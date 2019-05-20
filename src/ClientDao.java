import java.sql.*;
import java.util.ArrayList;

public class ClientDao implements DAO<Client> {
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
    public boolean add(Client c){
        String sql = "INSERT INTO client(nom,prenom,civilite,mail) VALUES(?,?,?,?)";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,c.getNom());
            ps.setString(2,c.getPrenom());
            ps.setString(3,c.getCivilite());
            ps.setString(4,c.getMail());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public boolean update(Client c){
        String sql = "UPDATE client SET nom=?,prenom=?,civilite=?,mail=? WHERE nom=? AND prenom=?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,c.getNom());
            ps.setString(2,c.getPrenom());
            ps.setString(3,c.getCivilite());
            ps.setString(4,c.getMail());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public boolean delete(Client c){
        String sql = "DELETE FROM client WHERE nom=? AND prenom=?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1,c.getNom());
            ps.setString(2,c.getPrenom());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public Client get(int i){
        String sql = "SELECT * FROM client WHERE id =?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,i);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String civil = rs.getString("civilite");
                String mail = rs.getString("mail");
                Client c = new Client(nom,prenom,civil,mail);
                return c;
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
    }
    public ArrayList<Client> getAll(){
        String sql = "SELECT * FROM client";
        ArrayList<Client> c = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                String civil = rs.getString("civilite");
                String mail = rs.getString("mail");
                Client tmp = new Client(nom,prenom,civil,mail);
                c.add(tmp);
            }
            return c;

        }
        catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
