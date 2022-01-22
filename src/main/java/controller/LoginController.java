package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;

import beans.Machine;
import beans.Salle;
import beans.User;
import connexion.Connexion;

import service.MachineService;
import service.SalleService;



/**
 * Servlet implementation class UserController
 */
@WebServlet("/UserController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SalleService us;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		us = new SalleService();
// TODO Auto-generated constructor stub
	}

	private static PrintWriter out;
	private static String message;
	private static Gson gson;
	private JSONObject simpeJO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		gson = new Gson();
		simpeJO = new JSONObject();
		String name = request.getParameter("name");
		String usern = request.getParameter("username");
		String pass = request.getParameter("password");
		response.setContentType("text/html;charset=UTF-8");
		out = response.getWriter();
		HttpSession session = request.getSession();
		boolean login = us.login(usern, pass);
		if (login) {

			session.setAttribute("username", usern);
			simpeJO.put("status", true);
			simpeJO.put("erreur", "Login Sucess");
		} else {
			simpeJO.put("status", false);
			simpeJO.put("erreur", "Login Failed");
		}
		message = gson.toJson(simpeJO);
		out.println(message);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
// TODO Auto-generated method stub
		doGet(request, response);
	}

}