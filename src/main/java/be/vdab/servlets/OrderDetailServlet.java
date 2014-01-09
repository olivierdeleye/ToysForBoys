package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.services.OrderService;
import be.vdab.valueobjects.OrderDetail;

/**
 * Servlet implementation class OrderDetailServlet
 */
//@WebServlet("/orderdetail.htm")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/orderdetail.jsp";
	private final OrderService orderService = new OrderService();


   
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("orderId") != null){
		    long orderID = Long.parseLong(request.getParameter("orderId"));
			
			List<String>fouten = new ArrayList<>();
			
			Order order = orderService.findOrder(orderID); //(fetch join) lees het order met bijhorende producten a.d.v. orderID
			if (order == null) {
	    			fouten.add("Order niet gevonden");
	    	}
			else{
			   Set<OrderDetail >orderdetails = order.getOrderdetails();
			   if(!orderdetails.isEmpty()){
				 request.setAttribute("orderdetails", orderdetails);
			   }
		    }
		   if(fouten.isEmpty()){
			 request.setAttribute("order", order);
		   }
		   else{
			 request.setAttribute("fouten", fouten);
		   }
		}
	   request.getRequestDispatcher(VIEW).forward(request, response);
	}

}
