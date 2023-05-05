package nu.educom.MI6;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Repository {
    Connection conn;
    private void connect(){

        try{

        // db parameters
        String url       = "jdbc:mysql://localhost:3306/MI6";
        String user      = "jeroens_webshop_user";
        String password  = "p@TL!Cz7m2qes7V!";

        conn = DriverManager.getConnection(url, user, password);
        } catch(SQLException e) {
            throw new NullPointerException(e.getMessage());
        }
    }

    private void disconnect(){
        try{
            if(conn != null) {
                conn.close();
            }
        }catch(SQLException e){
            throw new NullPointerException(e.getMessage());
        }
    }

    public List<Agent> getAgents(int serviceNumber) {
        return getAgents(serviceNumber, true);
    }

    public List<Agent> getAgents(int serviceNumber, boolean active){
        List<Agent> agents = new ArrayList<>();
        try{
            connect();
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
            throw new NullPointerException(e.getMessage());
        } finally {
            disconnect();
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

    public List<LogInAttempt> getLastLogInAttempts(int serviceNumber) {
        return getLastLogInAttempts(serviceNumber, false);
    }

    public List<LogInAttempt> getLastLogInAttempts(int serviceNumber, boolean onlyLastLogIn){

        List<LogInAttempt> lastLogInAttempts = new ArrayList<>();
        try{
            connect();
            String sql;
            if (onlyLastLogIn){
                sql = "SELECT * from login_attempts WHERE serviceNumber = ? ORDER BY loginTime DESC LIMIT 1";
            } else {
                sql = "SELECT * from login_attempts WHERE serviceNumber = ? ORDER BY loginTime DESC";
            }
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,serviceNumber);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                lastLogInAttempts.add(
                        new LogInAttempt(rs.getInt("id"),
                                rs.getInt("serviceNumber"),
                                rs.getObject("loginTime", LocalDateTime.class),
                                rs.getBoolean("success")));
            }
            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            disconnect();
        }
        return lastLogInAttempts;
    }

    public void createLogInAttempt(int serviceNumber, boolean success){
        try{
            connect();
            String sql = "INSERT INTO `login_attempts` (`serviceNumber`, `success`) VALUES (?, ?); ";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,serviceNumber);
            pstmt.setBoolean(2,success);
            pstmt.execute();

            pstmt.close();
        } catch (SQLException e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            disconnect();
        }

    }


}
