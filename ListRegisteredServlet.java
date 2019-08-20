package asg3servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import dblib.Dblib;

@WebServlet(name = "ListRegisteredleServlet", urlPatterns = {"/ListRegistered"},
        initParams = {  @WebInitParam(name = "uid", value = "ism6236"),
                @WebInitParam(name = "pass", value = "ism6236bo")}
)

public class ListRegisteredServlet extends HttpServlet {

        private Dblib mdb;
        @Override
        public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String uid = config.getInitParameter("uid");
        String pass = config.getInitParameter("pass");
        mdb = new Dblib(uid, pass);
        }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String headerstuff = "<meta charset=\"UTF-8\">\n " +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "          <link rel=\"stylesheet\" type =\"text/css\" href =\"servlet.css\" /> ";

        String semester = request.getParameter("semester");

        int stuNo = request.getIntHeader("stuNo");

        int year = request.getIntHeader("year");

        List<String> ListRegistered = mdb.List(stuNo,semester,year);

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List Registered</title>");
            out.println(headerstuff);
            out.println("</head>");
            out.println("<body>");

            out.println("<form ACTION=\"ListRegistered\" METHOD=\"GET\">");
            out.println("<fieldset id = \"info\">");
            out.println("<legend> List Registered </legend>");
            String line = String.format("<label for=\"stuNo\"> Student No: </label><Input id=\"stuNo\" type=\"text\" size=\"4\" name=\"stuNo\"> <br>", stuNo);

            out.println("</body>");
            out.println("</html>");
        }


    }
}
