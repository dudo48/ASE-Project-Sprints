import java.sql.*;
import java.util.ArrayList;

//import com.sun.tools.javap.TryBlockWriter;
public class Sql_connec extends DBConnec {
    
    Connection conn = null;
    
    
    public Sql_connec(String url, String username, String pass) {
        super(url, username, pass);
        // TODO Auto-generated constructor stub
        try {// to connect to the database .
            conn = DriverManager.getConnection(url, username, pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    //  try (Connection connection = DriverManager
//            .getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "root", "");
//  
    public void createNotification(Notification notification)
            throws SQLException {
        String INSERT_NOTIFICATION = "INSERT INTO notification" +
                "  (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone,subject,language) VALUES " +
                " (?, ?, ?, ?, ?, ? , ?, ?);" ;
        try {
            //Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
            PreparedStatement preparedStatment= conn.prepareStatement(INSERT_NOTIFICATION);
            String classType = notification.getClassType();
    
            preparedStatment.setString(1, notification.getClassType());
            preparedStatment.setString(2, notification.getMsgText());
            preparedStatment.setInt(3, notification.getId());
            preparedStatment.setString(4, notification.getFailureReason());
            preparedStatment.setBoolean(5, notification.isSuccess());
            if(classType.equals("forgetPass")) {
                preparedStatment.setString(6, ((ForgetPassNotify) notification).getMessageTemplate());
            } else if(classType.equals("activeNotify")) {
                preparedStatment.setString(6, ((ActiveNotify) notification).getMessageTemplate());
            } else if(classType.equals("confirm")) {
                preparedStatment.setString(6, ((ConfirmBooking) notification).getMessageTemplate());
            }
            preparedStatment.setString(7, notification.getEmail());
            preparedStatment.setString(8, notification.getPhoneNum());
            preparedStatment.setString(9, notification.getSubject());
            preparedStatment.setString(10, notification.getLanguage());
        
            
            if( preparedStatment.executeUpdate()<1 ) {
                System.out.println("error in inserting ");
            }
            else System.out.println("1 row inserted");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("connection failed");
            e.printStackTrace();
        }
        
    }
    
    public void updateNotification(Notification notification ,int id) throws SQLException{
        String UPDATE_NOTIFICATION="update notification set classType = ? ,messageParts = ?,"
                + "id = ? , failureReason = ? ,isSuccess = ? , messageTemplate = ?, email = ? ,phone = ?, subject = ?, language = ?  where id = ? ;";
        
        try {
            PreparedStatement preparedStatment= conn.prepareStatement(UPDATE_NOTIFICATION);
            // (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone)
            String classType = notification.getClassType();
            preparedStatment.setString(1, notification.getClassType());
            preparedStatment.setString(2,notification.getMsgText() );
            preparedStatment.setInt(3, notification.getId());
            preparedStatment.setString(4, notification.getFailureReason());
            preparedStatment.setBoolean(5, notification.isSuccess());
            if(classType.equals("forgetPass")) {
                preparedStatment.setString(6, ((ForgetPassNotify) notification).getMessageTemplate());
            } else if(classType.equals("activeNotify")) {
                preparedStatment.setString(6, ((ActiveNotify) notification).getMessageTemplate());
            } else if(classType.equals("confirm")) {
                preparedStatment.setString(6, ((ConfirmBooking) notification).getMessageTemplate());
            }
            preparedStatment.setString(7, notification.getEmail());
            preparedStatment.setString(8, notification.getPhoneNum());
            preparedStatment.setString(9, notification.getSubject());
            preparedStatment.setString(10, notification.getLanguage());
            preparedStatment.setInt(11, notification.getId());
            System.out.println(preparedStatment);
            
            if (preparedStatment.executeUpdate()<1) {
                System.out.println("this id "+ id +" is not exist \n update failed");
            }
            else System.out.println("1 row updated");
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
              e.printStackTrace();
        }
    }
    
    public void deleteNotification (int id) throws SQLException {
        String DELETE_NOTIFICATION ="delete from notification where id = ?;";
        try {
            PreparedStatement preparedStatment = conn.prepareStatement(DELETE_NOTIFICATION);
            preparedStatment.setInt(1, id);
            
        int x=  preparedStatment.executeUpdate();
        if (x<1) { 
            System.out.println("this id ["+ id +"] is not exist \n Deletion failed");
            
        }else 
            System.out.println("1 row deleted");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    
    public Notification readNotification (int id )throws SQLException {
        String SELECT_NOTIFICATION = "select * from notification where id = ?";
        Notification returnedObj= null;
        try {
            
            PreparedStatement preparedStatment = conn.prepareStatement(SELECT_NOTIFICATION);
            preparedStatment.setInt(1, id);
 
                // execute the query or update the query  
                ResultSet rs = preparedStatment.executeQuery(); 
                //
                //process the result set to get the notification .
                while(rs.next()) {
                    // to get the Attrubites of the notification into  notification object.     
                        
                        //  (classType, messageParts, id, failureReason, isSuccess,messageTemplate , email,phone)
                     String classType = rs.getString("classType");
                     if(classType.equals("forgetPass")) {
                        returnedObj = new ForgetPassNotify("forgetPass");
                        ((ForgetPassNotify) returnedObj).setMessageTemplate(rs.getString("messageTemplate"));
                    } else if(classType.equals("activeNotify")) {
                        returnedObj = new ActiveNotify("activeNotify");
                        ((ActiveNotify) returnedObj).setMessageTemplate(rs.getString("messageTemplate"));
                    } else if(classType.equals("confirm")) {
                        returnedObj = new ConfirmBooking("confirm");
                        ((ConfirmBooking) returnedObj).setMessageTemplate(rs.getString("messageTemplate"));
                    }
                    String sendingWay = "";
                    if(rs.getString("email") == null) {
                        returnedObj.setSendingWay(new BySMS(rs.getString("phone")));
                    } else if(rs.getString("phone") != null){
                        returnedObj.setSendingWay(new BySMS(rs.getString("phone")));
                    }
                    
                     returnedObj = new Notification(rs.getString("messageParts").split(" "),
                                rs.getString("failureReason"),
                                 rs.getInt("id"), rs.getBoolean("isSuccess") );
                }
             
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (returnedObj==null) {
            System.out.println("this id doesn not exist NULL returned ");
        }
        return returnedObj;
        
    }
    
    public ArrayList<Notification> selectAllNotifications() throws SQLException{
        String SELECT_ALL_NOTIFICATIONS="select * from notification ";
        ArrayList<Notification> notifications= new ArrayList<>();
        try {
            Statement statment = conn.createStatement();
            ResultSet rs =statment.executeQuery(SELECT_ALL_NOTIFICATIONS) ;
            while (rs.next()) {
                String classType = rs.getString("classType");
                Notification returnedObj= null;
                if(classType.equals("forgetPass")) {
                   returnedObj = new ForgetPassNotify("forgetPass");
                   ((ForgetPassNotify) returnedObj).setMessageTemplate(rs.getString("messageTemplate"));
               } else if(classType.equals("activeNotify")) {
                   returnedObj = new ActiveNotify("activeNotify");
                   ((ActiveNotify) returnedObj).setMessageTemplate(rs.getString("messageTemplate"));
               } else if(classType.equals("confirm")) {
                   returnedObj = new ConfirmBooking("confirm");
                   ((ConfirmBooking) returnedObj).setMessageTemplate(rs.getString("messageTemplate"));
               }
               String sendingWay = "";
               if(rs.getString("email") == null) {
                   returnedObj.setSendingWay(new BySMS(rs.getString("phone")));
               } else if(rs.getString("phone") != null){
                   returnedObj.setSendingWay(new BySMS(rs.getString("phone")));
               }
               
                returnedObj = new Notification(rs.getString("messageParts").split(" "),
                           rs.getString("failureReason"),
                            rs.getInt("id"), rs.getBoolean("isSuccess") );
             notifications.add(returnedObj);
                
            }
            
            for (Notification n:notifications) {
                System.out.println(n.toString()+"\n\n");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return notifications;
        
    }
    
    public static void main(String[] args) {
        Sql_connec conn = new Sql_connec("jdbc:mysql://localhost:3306/test", "root", "");
        Notification notification = new Notification("mahmoud street".split(" "), null, 1,true);
        Notification notification2 = new Notification("moaz code".split(" "), null, 2,true);
        
        try {
            conn.createNotification(notification);
            ArrayList<Notification> arr = conn.selectAllNotifications();
            for(Notification x:arr) {
                System.out.println(x.getMsgText());
            }
            
            
            conn.updateNotification(1, notification2);
            arr = conn.selectAllNotifications();
            for(Notification x:arr) {
                System.out.println(x.getMsgText());
            }
            
            
            Notification not = conn.readNotification(1);
            System.out.println(not.getMsgText());
            
            
            conn.deleteNotification(1);
            arr = conn.selectAllNotifications();
            for(Notification x:arr) {
                System.out.println(x.getMsgText());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}