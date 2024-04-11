
package modelos;

public class Database {
     private String url;
    private String database;
    private String pass;
    private String user;
    private String driver;
    
    public Database(){
       this.url="jdbc:mysql://localhost:3306/";
       this.database="piapw1";
       this.user="root";
       this.pass="root";
       this.driver="com.mysql.jdbc.Driver";
    }

    public String getUrl() {
        return url;
    }

    public String getDatabase() {
        return database;
    }

    public String getPass() {
        return pass;
    }

    public String getUser() {
        return user;
    }

    public String getDriver() {
        return driver;
    }
    
    
}
