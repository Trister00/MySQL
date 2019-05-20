import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao implements DAO<Reservation> {
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
    public boolean add(Reservation r){
        String sql = "INSERT INTO reservation(id_client,id_hebergement,date_depart,date_fin) VALUES(?,?,?,?)";
        String sqlClient = "SELECT * FROM client WHERE nom=? AND prenom=?";
        int id_client =0;
        String sqlHebergement ="SELECT * FROM hebergement WHERE numero=? AND type=?";
        int id_hebergement =0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement psC = connection.prepareStatement(sqlClient);
            PreparedStatement psH = connection.prepareStatement(sqlHebergement)) {

            //PSC : Client;
            psC.setString(1,r.getClient().getNom());
            psC.setString(2,r.getClient().getPrenom());
            ResultSet rsC = ps.executeQuery();
            if(rsC.next()) {
               id_client = rsC.getInt("id");

            }
            //PSH : Hebergement
            psH.setInt(1,r.getHebergement().getNumero());
            psH.setString(2,r.getHebergement().getType());
            ResultSet rsH = psH.executeQuery();
            if(rsH.next()){
                id_hebergement = rsH.getInt("id");
            }

            //PS : Reservation
            ps.setInt(1,id_client);
            ps.setInt(2,id_hebergement);
            ps.setDate(3, Date.valueOf(r.getDate_debut()));
            ps.setDate(4,Date.valueOf(r.getDate_fin()));
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public boolean update(Reservation r){
        String sql = "UPDATE reservation SET id_hebergement=? WHERE id=?";
        String sqlClient = "SELECT * FROM client WHERE nom=? AND prenom=?";
        int client =0;
        String sqlHebergement ="SELECT * FROM hebergement WHERE numero=? AND type=?";
        int hebergement =0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement psC = connection.prepareStatement(sqlClient);
            PreparedStatement psH = connection.prepareStatement(sqlHebergement)) {

            //PSC : Client;
            psC.setString(1,r.getClient().getNom());
            psC.setString(2,r.getClient().getPrenom());
            ResultSet rsC = ps.executeQuery();
            if(rsC.next()) {
                client = rsC.getInt("id");

            }

            //PSH : Hebergement
            psH.setInt(1,r.getHebergement().getNumero());
            psH.setString(2,r.getHebergement().getType());
            ResultSet rsH = psH.executeQuery();
            if(rsH.next()){
                hebergement = rsH.getInt("id");
            }


            ps.setInt(1,hebergement);
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public boolean delete(Reservation r){
        String sql = "DELETE FROM restauration WHERE id_client=? AND id_hebergement=?";
        String sqlClient = "SELECT * FROM client WHERE nom=? AND prenom=?";
        int client =0;
        String sqlHebergement ="SELECT * FROM hebergement WHERE numero=? AND type=?";
        int hebergement =0;
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement psC = connection.prepareStatement(sqlClient);
            PreparedStatement psH = connection.prepareStatement(sqlHebergement)) {

            //PSC : Client;
            psC.setString(1,r.getClient().getNom());
            psC.setString(2,r.getClient().getPrenom());
            ResultSet rsC = ps.executeQuery();
            if(rsC.next()) {
                client = rsC.getInt("id");

            }

            //PSH : Hebergement
            psH.setInt(1,r.getHebergement().getNumero());
            psH.setString(2,r.getHebergement().getType());
            ResultSet rsH = psH.executeQuery();
            if(rsH.next()){
                hebergement = rsH.getInt("id");
            }


            ps.setInt(1,client);
            ps.setInt(2,hebergement);
            ps.executeUpdate();
            return true;

        }
        catch (SQLException e) {
            System.err.println(e);
            return false;
        }
    }
    public Reservation get(int i){
        String sql = "SELECT * FROM reservation WHERE id =?";
        String sqlClient = "SELECT * FROM client WHERE id =?";
        String sqlHebergement = "SELECT * FROM hebergement WHERE id =?";
        try(Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            PreparedStatement psC = connection.prepareStatement(sqlClient);
            PreparedStatement psH = connection.prepareStatement(sqlHebergement)) {
            ps.setInt(1,i);
            ResultSet rs = ps.executeQuery();
            Hebergement h = null;
            Client c = null;
            if(rs.next()) {
                int id_client = rs.getInt("id_client");
                int id_hebergement = rs.getInt("id_hebergement");
                Date date_debut = rs.getDate("date_debut");
                Date date_fin = rs.getDate("date_fin");
                //Hebergement
                ps.setInt(1,i);
                ResultSet rsH = psH.executeQuery();
                if(rs.next()) {
                    int num = rs.getInt("numero");
                    String type = rs.getString("type");
                    String localisation = rs.getString("localisation");
                    int nb_max = rs.getInt("nb_max");
                    int prix = rs.getInt("prix_nuit");
                    h = new Hebergement(num,type,localisation,nb_max,prix);

                }
                else {
                    rs.close();
                    return null;
                }
                //Client
                ps.setInt(1,i);
                ResultSet rsC = psC.executeQuery();
                if(rs.next()) {
                    String nom = rs.getString("nom");
                    String prenom = rs.getString("prenom");
                    String civil = rs.getString("civilite");
                    String mail = rs.getString("mail");
                    c = new Client(nom,prenom,civil,mail);

                }
                else {
                    rs.close();
                    return null;
                }
                //Reservation
                Reservation r = new Reservation(c,h, LocalDate.parse((CharSequence) date_debut),LocalDate.parse((CharSequence) date_fin));
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
    public ArrayList<Reservation> getAll(){
       return null;
    }
}
