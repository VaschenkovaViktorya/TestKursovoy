package servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletHelper {
    public static final String HTML_BEGIN = "<html><head><title>Test shop</title></head><body> ";
    public static final String END_HTML = "</body></html>";
    public static final String HREF_INDEX_HTML = "<p><a href=\"./\"> вернуться на главную </a></p>";

    public static void populateHtmlBegin(HttpServletResponse response) throws IOException {
        response.getWriter().append(HTML_BEGIN);
    }

    public static void populatedEnd(HttpServletResponse response) throws IOException {
        response.getWriter().append(HREF_INDEX_HTML);
        response.getWriter().append(END_HTML);
    }
}
