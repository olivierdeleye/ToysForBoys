package be.vdab.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Order;
import be.vdab.entities.Product;
import be.vdab.enums.Status;
import be.vdab.exceptions.OrderNietGevondenException;
import be.vdab.exceptions.RecordNietAangepastException;
import be.vdab.services.OrderService;
import be.vdab.valueobjects.OrderDetail;

/**
 * Servlet implementation class UnShippedOrdersServlet
 */
//@WebServlet("/unshippedorders.htm")
public class UnShippedOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/unshippedorders.jsp";
	private static final String REDIRECT_URL = "/unshippedorders.htm";
	private static final String NOT_CHANGED_VIEW = "/WEB-INF/JSP/orderstatusnotchanged.jsp";
	private final OrderService orderService = new OrderService();
	private static final int AANTAL_RIJEN = 20;
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String>fouten = new ArrayList<>();
		int vanafRij = request.getParameter("vanafRij") == null ? 0 : Integer.parseInt(request.getParameter("vanafRij"));
		
        request.setAttribute("vanafRij", vanafRij);
        request.setAttribute("aantalRijen", AANTAL_RIJEN);
        List<Status> statussen=Arrays.asList(Status.SHIPPED, Status.CANCELLED);        
		Iterable<Order> orders = orderService.findUnshippedNotCancelled(statussen,vanafRij,AANTAL_RIJEN + 1);
    	if (! orders.iterator().hasNext()) {
    		fouten.add("Geen orders gevonden");
    		request.setAttribute("fouten", fouten);
    	}
    	else {
    		List<Order> orderList = (List<Order>) orders;
    		//indien er maar 20 of minder records zijn
			 if (orderList.size() <= AANTAL_RIJEN) {
			   request.setAttribute("laatstePagina", true);//geef aan dat dit de enige en laaste pagina is
			 }
			 else {
				 orderList.remove(AANTAL_RIJEN); //verwijder 21ste record , index van List begint bij 0
			 }
    	    request.setAttribute("orders", orders);
    	}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}

	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<String> fouten = new ArrayList<>();
		long orderID=0;
		if(! request.getParameterMap().isEmpty()){
		  
		  try {
		    for(String orderId : request.getParameterValues("setAsShipped")){
			  orderID = Long.parseLong(orderId);
			  Order order = orderService.findOrder(orderID);// lees het order 
			  Set<OrderDetail> orderdetails = order.getOrderdetails();
			  for(OrderDetail orderdetail : orderdetails){
			    int quantity = orderdetail.getQuantityOrdered(); // het orderdetail en het aantal in order
			    Product product = orderdetail.getProduct();
			    product.verminderQuantityInOrder(quantity); 
			    product.verminderQuantityInStock(quantity);
			  }
		      orderService.setOrderAsShipped(orderID);
		    }
		  }
		  catch (OrderNietGevondenException ex) {
			 fouten.add("Order Id: "+ orderID +" niet gevonden");
		  }
		  catch (RecordNietAangepastException ex) {
			 fouten.add("Een andere gebuiker heeft order met Id: "+ orderID +" gewijzigd");
		  }
		}
		if (! fouten.isEmpty()){  // indien er een van bovenstaande fouten is opgetreden , naar foutmeldingservlet/jsp pagina
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(NOT_CHANGED_VIEW).forward(request, response);
		}
		else{
		  response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + REDIRECT_URL));
		}
 }
		
}
