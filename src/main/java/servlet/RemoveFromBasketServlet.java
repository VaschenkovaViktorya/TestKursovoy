package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.Product;

import java.io.IOException;
import java.util.List;

import static servlet.ProductServlet.TEXT_HTML_CHARSET_UTF_8;
@WebServlet(urlPatterns = "/remove")
public class RemoveFromBasketServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType(TEXT_HTML_CHARSET_UTF_8);
        Integer id = Integer.valueOf(request.getParameter("id"));
        Object myBasket = request.getSession().getAttribute("myBasket");
        if (myBasket!=null){
            List<Product> basket = (List<Product>)myBasket;
            basket.remove((int)id);
        }

/*
        RequestDispatcher rd = request.getRequestDispatcher("./viewBasket");
        rd.forward(request,response);*/

        response.sendRedirect("./viewBasket");
    }
}
