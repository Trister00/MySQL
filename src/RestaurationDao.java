import java.sql.*;
import java.util.ArrayList;

public class RestaurationDao implements DAO<Restauration> {
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
    public boolean add(Restauration r){
        String sql = "INSERT INTO restauration(type,details) VALUES(?,?)";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,r.getType());
            ps.setString(2,r.getDetails());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public boolean update(Restauration r){
        String sql = "UPDATE restauration SET details=? WHERE type=?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1,r.getDetails());
            ps.setString(2,r.getType());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public boolean delete(Restauration r){
        String sql = "DELETE FROM restauration WHERE type=?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1,r.getType());
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public Restauration get(int i){
        String sql = "SELECT * FROM restauration WHERE id =?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1,i);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String type = rs.getString("type");
                String details = rs.getString("details");
                Restauration r = new Restauration(type,details);
                return  r;
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
    public ArrayList<Restauration> getAll(){
        String sql = "SELECT type,details FROM restauration";
        ArrayList<Restauration> r = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String type = rs.getString("type");
                String details = rs.getString("details");
                Restauration tmp = new Restauration(type,details);
                r.add(tmp);
            }
            return r;

        }
        catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }
}
