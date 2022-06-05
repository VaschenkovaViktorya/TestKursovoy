package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.Product;
import product.ProductService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/basket")
public class AddBasketProductServlet extends HttpServlet {

    public static final String TEXT_HTML_CHARSET_UTF_8 = "text/html; charset=UTF-8";
    public static final String NAME = "name";

    public AddBasketProductServlet() {
        System.out.println("Running");
    }

    private static final long serialVersionUID = 1L;
    private Map<String, Product> products = null;

    @Override
    public void init() throws ServletException {
        super.init();
        products = ProductService.getAll();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType(TEXT_HTML_CHARSET_UTF_8);
        String pName = request.getParameter(NAME);
        System.out.println(">>>>>>>"+ pName);
        Product selectedProduct = products.get(pName);
        Object myBasket = request.getSession().getAttribute("myBasket");
        if (myBasket!=null){
            List<Product> list =(List<Product>)myBasket;
            list.add(selectedProduct);
        } else{
            List<Product> list = new ArrayList<>();
            list.add(selectedProduct);
            request.getSession().setAttribute("myBasket",list);

        }

        ServletHelper.populateHtmlBegin(response);

        response.getWriter().append("  <p> Продукт добавлен</p>");
        ServletHelper.populatedEnd(response);


        System.out.println("servlet.AddBasketProductServlet is invoking");
        System.out.println("servlet.AddBasketProductServlet is invoking");
        // System.out.println(">>>>>>>>>>>>"+request.getSession().getId());
        //  response.sendRedirect("https://www.youtube.com/watch?v=Irf1eIkcBkE&list=PLBheEHDcG7-mLhP_ZknHi7vjesmXVmk7q");

    }


}