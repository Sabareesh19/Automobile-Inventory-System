import java.util.*;
import java.util.Map;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Backlight")

/* 
	Backlight class contains class variables id,name,price,image,retailer,condition,discount,quantity and Manufacturerrebate.

	Backlight class constructor with Arguments name,price,image,retailer,condition,discount,quantity and Manufacturerrebate .
	  
	Backlight class contains getters and setters for name,price,image,retailer,condition,discount,quantity and Manufacturerrebate .

*/

public class Backlight extends HttpServlet{
	private String id;
	private String name;
	private double price;
	private String image;
	private String retailer;
	private String condition;
	private double discount;
	private double quantity;
	private String manufacturerRebate;
	public Backlight(String name, double price, String image, String retailer, String condition,double discount){
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount;
		this.retailer = retailer;
	}

	public Backlight(String name, double price, String image, String retailer, String condition,double discount,double quantity,String manufacturerRebate){
		this.name=name;
		this.price=price;
		this.image=image;
		this.condition=condition;
		this.discount = discount;
		this.retailer = retailer;
		this.quantity=quantity;
		this.manufacturerRebate=manufacturerRebate;
	}

	public Backlight(){
		
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRetailer() {
		return retailer;
	}
	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}

	public String getCondition() {
		return condition;
	}	
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getManufacturerRebate() {
		return manufacturerRebate;
	}
	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}
}
