package asg3servlets;

import javax.servlet.Registration;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dblib.Dblib;


@WebServlet(name = "ListAvailableServlet", urlPatterns = {"/ListAvailable"},
        initParams = {  @WebInitParam(name = "uid", value = "ism6236"),
                        @WebInitParam(name = "pass", value = "ism6236bo")}
)
public class ListAvailableServlet extends HttpServlet {

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
        //String year = request.getParameter("year");
        //Integer year1 = Integer.parseInt(year);

        int year = request.getIntHeader("year");

        List<String> List = mdb.List(year,semester);

        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>List Available</title>");
            out.println(headerstuff);
            out.println("</head>");
            out.println("<body>");

            out.println("<form ACTION=\"ListAvailable\" METHOD=\"GET\"> ");
            out.println("<fieldset id = \"info\">");
            out.println("<legend> List Available </legend>");
            String line = String.format("<label for=\"year\"> Year:  </label><Input id=\"year\" type=\"text\" size=\"4\" name=\"year\"> <br>", year);
            out.println(line);
            line = String.format("<label for=\"semester\"> Semester: </label><input id=\"semester\" type=\"text\" size=\"10\" name=\"semester\"> <br>", semester);
            out.println(line);
            out.println("<label for=\"classlist\"> Classes: </label> <br> ");
            out.println("<select id = \"classes\" name =\"classlist\" size=\"10\"> ");

            List<String> l = mdb.List(year, semester);
            
                for (String lista: l) {
                    line = String.format("<option> %s </option>", lista);
                    out.println(line);
                }

            out.println("</select> <br>");
            out.println("<input type=\"submit\" value=\"Get List Available\"> ");
            out.println("<Input TYPE =\"submit\" formaction=\"index.jsp\" value=\"Main Menu\"> ");
            out.println("</fieldset>");
            out.println("</form>");

            out.println("</body>");
            out.println("</html>");
        }
    }
}
