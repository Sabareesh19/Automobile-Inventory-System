import java.io.*;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;
import java.util.*;
import java.text.*;
import java.sql.*;
import java.io.IOException;
import java.io.*;

/* This is a helper class to implement autocomplete feature */

public class AjaxUtility {
	StringBuffer sb = new StringBuffer();
	boolean namesAdded = false;
	static Connection conn = null;
    static String message;
	public static String getConnection()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartdatabase","root","Vavinash1994@");							
			message="Successfull";
			return message;
		}
		catch(SQLException e)
		{
			 message="unsuccessful";
		     return message;
		}
		catch(Exception e)
		{
			 message="unsuccessful";
		     return message;
		}
	}
	
	/* Searches products in the Inventory for the entered name in the search box and appends to the string buffer */

	public  StringBuffer readdata(String searchId)
	{	
		HashMap<String,Product> data;
		data=getData();		
 	    Iterator it = data.entrySet().iterator();	
        while (it.hasNext()) 
	    {
            Map.Entry pi = (Map.Entry)it.next();
			if(pi!=null)
			{
				Product p=(Product)pi.getValue();                   
                if (p.getName().toLowerCase().startsWith(searchId))
                {
                        sb.append("<product>");
                        sb.append("<id>" + p.getId() + "</id>");
                        sb.append("<productName>" + p.getName() + "</productName>");
                        sb.append("</product>");
                }
			}
       }
	   
	   return sb;
	}
	
	/* Gets all the product Details from the MySQL database */

	public static HashMap<String,Product> getData()
	{
		HashMap<String,Product> hm=new HashMap<String,Product>();
		try
		{
			getConnection();			
		    String selectproduct="select * from  Productdetails";
		    PreparedStatement pst = conn.prepareStatement(selectproduct);
			ResultSet rs = pst.executeQuery();			
			while(rs.next())
			{	Product p = new Product(rs.getString("Id"),rs.getString("productName"),rs.getDouble("productPrice"),rs.getString("productImage"),rs.getString("productManufacturer"),rs.getString("productCondition"),rs.getString("ProductType"),rs.getDouble("productDiscount"));
				hm.put(rs.getString("Id"), p);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hm;			
	}
}
