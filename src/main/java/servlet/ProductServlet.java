package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import product.Product;
import product.ProductService;

/*import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; */
import java.io.IOException;
import java.net.CookieHandler;
import java.util.Map;
import java.util.Random;

import static servlet.ServletHelper.HREF_INDEX_HTML;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {

    public static final String TEXT_HTML_CHARSET_UTF_8 = "text/html; charset=UTF-8";
    public static final String NAME = "name";
    public static final String CATEGORY = "category";


    public ProductServlet() {
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
        String product = request.getParameter(NAME);
        String category = request.getParameter(CATEGORY);
        if (category!=null&&category.length()>0){
            Cookie c= new Cookie("my_category",category);
            response.addCookie(c);
        }
        Product foundProduct = products.get(product);
        ServletHelper.populateHtmlBegin(response);
        if (foundProduct!=null){
            response.getWriter().append("  <p> Продукт " + foundProduct.toString() + "</p>");
            response.getWriter().append("<p><a href=\"./basket?name=" + foundProduct.getName() + "\"> Добавить в корзину </a></p>");

        }
        else {
            response.getWriter().append("   <p> Продукт неизвестен</p>");
        }
        ServletHelper.populatedEnd(response);
        System.out.println("servlet.ProductServlet is invoking");
       // System.out.println(">>>>>>>>>>>>"+request.getSession().getId());
       //  response.sendRedirect("https://www.youtube.com/watch?v=Irf1eIkcBkE&list=PLBheEHDcG7-mLhP_ZknHi7vjesmXVmk7q");

    }




    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.getWriter().append("Forma poluchena");


    }
}