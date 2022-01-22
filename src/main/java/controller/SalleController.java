package controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import beans.Machine;
import beans.Salle;
import service.SalleService;

@WebServlet(name = "SalleController", urlPatterns = { "/SalleController" })
public class SalleController extends HttpServlet {
	private SalleService ss = new SalleService();

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {
				response.setContentType("application/json");
				List<Salle> salles = ss.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(salles));
			}

			if (request.getParameter("op").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ss.delete(ss.findById(id));
				response.setContentType("application/json");
				List<Salle> salles = ss.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(salles));
			}
		} else if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("")) {
				String code = request.getParameter("code");
				String type = request.getParameter("type");
				int id = Integer.parseInt(request.getParameter("id"));
				Salle s = ss.findById(id);
				s.setCode(code);
				s.setType(type);
				ss.update(s);
				response.setContentType("application/json");
				List<Salle> salles = ss.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(salles));
			}

		} else {
			String code = request.getParameter("code");
			String type = request.getParameter("type");
			ss.create(new Salle(code, type));
			response.setContentType("application/json");
			List<Salle> salles = ss.findAll();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(salles));
		}
	}
	 @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        processRequest(request, response);
	    }
}
