import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.sql.*;

@WebServlet("/Account")

public class Account extends HttpServlet {
	private String error_msg;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayAccount(request, response);
	}

	/* Display Account Details of the Customer (Name and Usertype) and the orders placed by the customer */

	protected void displayAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);
		try
         {  
           response.setContentType("text/html");
			if(!utility.isLoggedin())
			{
				HttpSession session = request.getSession(true);				
				session.setAttribute("login_msg", "Please Login to add items to cart");
				response.sendRedirect("Login");
				return;
			}
			HttpSession session=request.getSession(); 	
			utility.printHtml("Header.html");
			utility.printHtml("LeftNavigationBar.html");
			pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
			pw.print("<a style='font-size: 24px;'>Account</a>");
			pw.print("</h2><div class='entry'>");
			User user=utility.getUser();
			pw.print("<table class='gridtable' style='margin-left: -15px;'>");
			pw.print("<tr>");
			pw.print("<td> User Name: </td>");
			pw.print("<td>" +user.getName()+ "</td>");
			pw.print("</tr>");
			pw.print("<tr>");
			pw.print("<td> User Type: </td>");
			pw.print("<td>" +user.getUsertype()+ "</td>");
			pw.print("</tr>");
			HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();

			/* Retrieves all the orders placed  by the user */
			try
				{
					orderPayments=MySqlDataStoreUtilities.selectOrder();
				}
			catch(Exception e)
				{
			
				}
			int size=0;
			if(user.getUsertype().equals("manager")){
				for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())	
					size= size+1;
					System.out.print("I am inside manager code");
				}
			}else{
				for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
				{
					for(OrderPayment od:entry.getValue())	
					if(od.getUserName().equals(user.getName()))
					size= size+1;
				}
			}
			
				
			if(size>0)
				{	
					
					pw.print("<tr><td></td>");
					pw.print("<td>OrderId:</td>");
					pw.print("<td>UserName:</td>");
					pw.print("<td>productOrdered:</td>");
					pw.print("<td>productPrice:</td></tr>");

					/* List of orders placed by the user */

					for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
					{


						for(OrderPayment oi:entry.getValue()){	
							if(user.getUsertype().equals("manager")){
								
								pw.print("<tr>");	
								pw.print("<form method='get' action='ViewOrder'>");		
								pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
								pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
								pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
								
								pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
								pw.print("</form>");
								pw.print("<form method='get' action='AddOrder'>");
								pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
								pw.print("<input type='hidden' name='userName' value='"+oi.getUserName()+"'>");
								pw.print("<td><input type='submit' name='Order' value='AddOrder' class='btnbuy'></td>");
								pw.print("</form>");
								pw.print("<form method='get' action='EditOrder'>");
								pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
								pw.print("<input type='hidden' name='userName' value='"+oi.getUserName()+"'>");
								pw.print("<td><input type='submit' name='Order' value='EditOrder' class='btnbuy'></td>");
								pw.print("</form>");
								pw.print("</tr>");
								
							}
							else{
									if(oi.getUserName().equals(user.getName())) 
									{
										pw.print("<form method='get' action='ViewOrder'>");
										pw.print("<tr>");			
										pw.print("<td><input type='radio' name='orderName' value='"+oi.getOrderName()+"'></td>");			
										pw.print("<td>"+oi.getOrderId()+".</td><td>"+oi.getUserName()+"</td><td>"+oi.getOrderName()+"</td><td>Price: "+oi.getOrderPrice()+"</td>");
										pw.print("<input type='hidden' name='orderId' value='"+oi.getOrderId()+"'>");
										pw.print("<td><input type='submit' name='Order' value='CancelOrder' class='btnbuy'></td>");
										pw.print("</tr>");
										pw.print("</form>");
									
									}

							}
							
						}
					
					}
					
					pw.print("</table>");
				}

				/* Error message if no order is placed by the user */

				else
				{
					pw.print("<h4 style='color:red'>You have not placed any order with this order id</h4>");
				}
			pw.print("</table>");		
			pw.print("</h2></div></div></div>");		
			utility.printHtml("Footer.html");	        	
		}
		catch(Exception e)
		{
		}		
	}
}
