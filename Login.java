import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")

/*  Login .java file is used to login into the Automobile Inventory application */

public class Login extends HttpServlet {
	protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();

		/* 
			User Information(username,password,usertype) is obtained from HttpServletRequest,
			Based on the Type of user(customer,manager) respective hashmap is called and the username and 
			password are validated and added to session variable and display Login Function is called 
		*/

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String usertype = request.getParameter("usertype");
		HashMap<String, User> hm=new HashMap<String, User>();
		//user details are validated using already registered users which are stored in the MySQL database. 
		//if the file containts username and passoword user entered user will be directed to home page
		//else error message will be shown
		try
		{		
		  hm=MySqlDataStoreUtilities.selectUser();		  
		}
		catch(Exception e)
		{
				
		}
		User user = hm.get(username);
		if(user!=null)
		{
		 String user_password = user.getPassword();
		 String user_typed = user.getUsertype();
		 if (password.equals(user_password)) 
			{
			HttpSession session = request.getSession(true);
			session.setAttribute("username", user.getName());
			session.setAttribute("usertype", user.getUsertype());
			if(!usertype.equals(user_typed)){
				session.setAttribute("usertype", user_typed);
				String login_msg = "Please select the correct user type";
				session.setAttribute("login_msg",login_msg);
				session.setAttribute("username",null);
				response.sendRedirect("Login");
				return;
			}
			response.sendRedirect("Home");
			return;
			}
		}
		displayLogin(request, response, pw, true);
	}
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		displayLogin(request, response, pw, false);
	}

	/*  Login Screen is Displayed, Registered User specifies credentials and logins into the Automobile Inventory Application. */

	protected void displayLogin(HttpServletRequest request,
		HttpServletResponse response, PrintWriter pw, boolean error)
		throws ServletException, IOException {
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		pw.print("<div class='post' style='float: none; width: 100%'>");
		pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Login</a></h2>"
				+ "<div class='entry'>"
				+ "<div style='width:400px; margin:25px; margin-left: auto;margin-right: auto;'>");
		if (error)
			pw.print("<h4 style='color:red'>Please check your username, password and user type!</h4>");
		HttpSession session = request.getSession(true);
		if(session.getAttribute("login_msg")!=null){			
			pw.print("<h4 style='color:red'>"+session.getAttribute("login_msg")+"</h4>");
			session.removeAttribute("login_msg");
		}

		/* UI for Login page is displayed below */
		
		pw.print("<form method='post' action='Login'>"
				+ "<table style='width:100%'><tr><td>"
				+ "<h3>Username</h3></td><td><input type='text' name='username' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>Password</h3></td><td><input type='password' name='password' value='' class='input' required></input>"
				+ "</td></tr><tr><td>"
				+ "<h3>User Type</h3></td><td><select name='usertype' class='input'><option value='customer' selected>Customer</option><option value='manager'>Store Manager</option></select>"
				+ "</td></tr><tr><td></td><td>"
				+ "<input type='submit' class='btnbuy' value='Login' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
				+ "</td></tr><tr><td></td><td>"
				+ "<strong><a class='' href='Registration' style='float: right;height: 20px margin: 20px;'>New User? Register here!</a></strong>"
				+ "</td></tr></table>"
				+ "</form>" + "</div></div></div>");
		utility.printHtml("Footer.html");
	}
}
