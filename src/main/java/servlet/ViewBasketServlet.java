package servlet;

import groovy.servlet.AbstractHttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static servlet.ProductServlet.NAME;

@WebServlet(urlPatterns = "/viewBasket")
public class ViewBasketServlet extends HttpServlet {
    public static final String TEXT_HTML_CHARSET_UTF_8 = "text/html; charset=UTF-8";
    public static final String TABLE_BEGIN = "<table>  <tr>\n" +
            "    <th>Название</th>\n" +
            "    <th>Категория</th>\n" +
            "    <th>Цена</th>\n" +
            "  </tr>";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType(TEXT_HTML_CHARSET_UTF_8);
        ServletHelper.populateHtmlBegin(response);
        HttpSession httpSession = request.getSession();
        List<Product> mybasket = (List<Product>) httpSession.getAttribute("myBasket");
        if (mybasket != null && mybasket.size()!=0) {
            response.getWriter().append(TABLE_BEGIN);
            for (int i=0;i< mybasket.size(); i++){
                Product p = mybasket.get(i);
                response.getWriter().append("  <tr>\n" +
                        "    <td>"+ p.getName()+"</td>\n" +
                        "    <td>"+p.getCategory() +"</td>\n" +
                        "    <td>"+p.getPrice() +"</td>\n" +
                        "    <td> <p><a href=\"./remove?id="+i +"\">"+i+"</a></p></td>\n" +
                        "  </tr>");
//                response.getWriter().append("<p><a href=\"./basket?name=" + foundProduct.getName() + "\"> Добавить в корзину </a></p>");

            }
            for (Product p :
                    mybasket) {

                System.out.println(">>>>>>>>>>>>>печать корзины"+p);

            }
            response.getWriter().append("</table>");
        } else {
            response.getWriter().append("Корзина пустая");
        }

        ServletHelper.populatedEnd(response);

    }
}
