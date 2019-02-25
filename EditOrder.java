import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.io.*;

@WebServlet("/EditOrder")

/* EditOrder class is used to edit the order placed by the customer from the admin profile */

public class EditOrder extends HttpServlet {	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		Utilities utility = new Utilities(request, pw);

		//check if the user is logged in

		if(!utility.isLoggedin()){
			HttpSession session = request.getSession(true);				
			session.setAttribute("login_msg", "Please Login to View your Orders");
			response.sendRedirect("Login");
			return;
		}
		String username=request.getParameter("userName");
		String orderid = request.getParameter("orderId");
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Order</a>");
		pw.print("</h2><div class='entry'>");
		pw.print("<table class='gridtable'>");
		pw.print("<tr><td>UserName selected to add/cancel order:</td> <td>"+username+"</td></tr>");
		HashMap<Integer, ArrayList<OrderPayment>> orderPayments = new HashMap<Integer, ArrayList<OrderPayment>>();
		try
		{
			orderPayments=MySqlDataStoreUtilities.selectOrder();
		}
		catch(Exception e)
		{

		}
		pw.print("<tr><td></td>");
		pw.print("<td>OrderId:</td>");
		pw.print("<td>UserName:</td>");
		pw.print("<td>productOrdered:</td>");
		pw.print("<td>productPrice:</td></tr>");
		for(Map.Entry<Integer, ArrayList<OrderPayment>> entry : orderPayments.entrySet())
			{
				for(OrderPayment oi:entry.getValue()){
					if(oi.getUserName().equals(username)){
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
						pw.print("</tr>");
					}				
				}
			}
		pw.print("</table>");
		pw.print("</div></div></div>");		
		utility.printHtml("Footer.html");
	}
}


