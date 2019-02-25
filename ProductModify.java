import java.io.*;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ProductModify")

/* ProductModify java file holds the UI page for add,delete and update functionality emabeled for Storemanager */

public class ProductModify extends HttpServlet {
	protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		String action = request.getParameter("button");
		Utilities utility = new Utilities(request, pw);
		utility.printHtml("Header.html");
		utility.printHtml("LeftNavigationBar.html");
		// Ui for Add Product
		if(action.equals("Addproduct"))
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Add Product</a></h2>"
					+ "<div class='entry'>");			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='mercedes' selected>Mercedes</option><option value='bmw'>Bmw</option><option value='audi'>Audi</option><option value='jeep'>Jeep</option><option value='hyundai'>Hyundai</option><option value='toyota'>Toyota</option><option value='porsche'>Porsche</option><option value='lincoln'>Lincoln</option><option value='headlight'>Headlights</option><option value='rims'>Rims</option><option value='windowstints'>Window Stints</option><option value='backlights'>Backlights</option><option value='exhaust'>Exhaust</option><option value='accessories'>Accessory</option></select>"
					+ "</td></tr><tr><td>"
					+"<h3>Product</h3></td><td><input type='text' name='product' placeholder='Please mention product if adding accessories' value='' class='input'></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Price</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Discount</h3></td><td><input type='number' step='any' placeholder='please enter numeric data' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='add' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"
					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");		
		}
		// Ui for Update Product
		else if (action.equals("Updateproduct"))
		{
		    pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Update Product</a></h2>"
					+ "<div class='entry'>");			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table id='bestseller'><tr><td>"
					+ "<h3>Product Type</h3></td><td><select name='producttype' class='input'><option value='mercedes' selected>Mercedes</option><option value='bmw'>Bmw</option><option value='audi'>Audi</option><option value='jeep'>Jeep</option><option value='hyundai'>Hyundai</option><option value='toyota'>Toyota</option><option value='porsche'>Porsche</option><option value='lincoln'>Lincoln</option><option value='headlight'>Headlights</option><option value='rims'>Rims</option><option value='windowstints'>Window Stints</option><option value='backlights'>Backlights</option><option value='exhaust'>Exhaust</option><option value='accessories'>Accessory</option></select>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Id</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Name</h3></td><td><input type='text' name='productName' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Price</h3></td><td><input type='number' step='any' name='productPrice' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Image</h3></td><td><input type='text' name='productImage' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Manufacturer</h3></td><td><input type='text' name='productManufacturer' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Condition</h3></td><td><input type='text' name='productCondition' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<h3>Product Discount</h3></td><td><input type='number' step='any' name='productDiscount' value='' class='input' required></input>"
					+ "</td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='update' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr><tr><td></td><td>"					
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");	
		}
		// Ui for Delete Product
		else
		{
			pw.print("<div id='content'><div class='post'>");
			pw.print("<h2 class='title meta'><a style='font-size: 24px;'>Delete Product</a></h2>"
					+ "<div class='entry'>");			
			pw.print("<form method='get' action='ProductCrud'>"
					+ "<table style='width:100%'><tr><td>"
					+ "<h3>ProductId</h3></td><td><input type='text' name='productId' value='' class='input' required></input>"
					+ "</td><td style='width: 35%'></td></tr><tr><td>"
					+ "<input type='submit' class='btnbuy' name='button' value='delete' style='float: right;height: 20px margin: 20px; margin-right: 10px;'></input>"
					+ "</td></tr></table>"
					+ "</form>" + "</div></div></div>");
		}
		utility.printHtml("Footer.html");
	}
}