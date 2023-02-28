import java.sql.*;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) {
		
		String url = "jdbc:sqlserver://localhost:1433;" +
                "databaseName=javaDatabase;" +
                "encrypt=true;" +
                "trustServerCertificate=true";
		 String user = "sa";
	     String pass = "root";
	     
	     Scanner scanner = new Scanner(System.in);
	     
	     	System.out.println("enter employee name");
	        String emp_name = scanner.next();
	     
	     	System.out.println("enter employee id");
	        Integer emp_id = scanner.nextInt();

	        System.out.println("enter job name");
	        String job_name = scanner.next();


	        Connection con = null;

	        try {

	            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
	            DriverManager.registerDriver(driver);

	            con = DriverManager.getConnection(url, user, pass);

	            Statement st = con.createStatement();

	            String sql = "insert into employees values('" + emp_name
	                    + "'," + emp_id + ",'" + job_name + "')";

	            Integer m = st.executeUpdate(sql);
	            if (m >= 1) {
	                System.out.println("inserted successfully : " + sql);
	            } else {
	                System.out.println("insertion failed");
	            }

	            String sql1 = "Select * from employees";
	            ResultSet resultSet = st.executeQuery(sql1);


	            while (resultSet.next()) {	          
	                System.out.println(resultSet.getString("emp_name"));
	                System.out.println(resultSet.getString("emp_id"));
	                System.out.println(resultSet.getString("job_name"));
	            }
	            con.close();
	        } catch (Exception ex) {
	            System.err.println(ex);
	        }
	}

}
