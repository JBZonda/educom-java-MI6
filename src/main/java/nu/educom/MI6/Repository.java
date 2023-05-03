package nu.educom.MI6;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    Connection conn;
    private Connection connect(){

        Connection conn = null;
        try{

        // db parameters
        String url       = "jdbc:mysql://localhost:3306/MI6";
        String user      = "root";
        String password  = "zgl9/nfe1CIA77b@";

        conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public List<Agent> getAgents(int serviceNumber) {
        return getAgents(serviceNumber, true);
    }

    public List<Agent> getAgents(int serviceNumber, boolean active){
        List<Agent> agents = new ArrayList<Agent>();
        try{
            conn = connect();
            System.out.println("connected");

            String sql = "SELECT * from agents WHERE serviceNumber = ? and active= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,serviceNumber);
            pstmt.setBoolean(2,active);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                agents.add(
                        new Agent(rs.getInt("id"),
                                    rs.getInt("serviceNumber"),
                                rs.getString("passPhrase"),
                                rs.getBoolean("active"),
                                rs.getBoolean("licence_to_kill"),
                                rs.getDate("licence_to_kill_end").toLocalDate()));
            }
            rs.close();
            pstmt.close();
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(conn != null) {
                    conn.close();
                }
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }

        return agents;
    }

    public Agent getAgent(int serviceNumber) {
        return getAgent(serviceNumber, true);
    }
    public Agent getAgent(int serviceNumber, boolean active){
        List<Agent> result = getAgents(serviceNumber, active);
        if (result.size() != 1){
            return null;
        }
        return result.get(0);
    }

    public boolean authenticateAgent(int sN,String pP){
        Agent agent = getAgent(sN);
        if (agent==null){
            return false;
        }
        return agent.getPassPhrase().equals(pP);

    }


}
