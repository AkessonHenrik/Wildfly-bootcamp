package ch.heigvd.amt.web.controllers.auth;

import ch.heigvd.amt.models.User;
import ch.heigvd.amt.services.dao.UserManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = {"auth/register"})
public class RegisterServlet extends HttpServlet {

    @EJB
    private UserManagerLocal userManager;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (userManager.addUser(new User(request.getParameter("email"), request.getParameter("username"), request.getParameter("password"), request.getSession()))) {
//            request.setAttribute("isLogged", userManager.isLogged(request.getSession()));
            request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(request, response);
    }
}