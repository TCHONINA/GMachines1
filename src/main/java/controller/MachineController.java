package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import beans.Machine;
import beans.Salle;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import service.MachineService;
import service.SalleService;

/**
 *
 * @author Lachgar
 */
@WebServlet(name = "MachineController", urlPatterns = { "/MachineController" })
public class MachineController extends HttpServlet {
	/**
	 * 
	 */
	private SalleService ss = new SalleService();
	private MachineService ms = new MachineService();

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("op") != null) {
			if (request.getParameter("op").equals("load")) {
				response.setContentType("application/json");
				List<Salle> salles = ss.findAll();
				List<Machine> machines = ms.findAll();
				Gson json = new Gson();
				String bothList = "[" + json.toJson(salles) + "," + json.toJson(machines) + "]";
				response.getWriter().write(bothList);
			}

			if (request.getParameter("op").equals("delete")) {
				int id = Integer.parseInt(request.getParameter("id"));
				ms.delete(ms.findById(id));
				response.setContentType("application/json");
				List<Machine> machines = ms.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(machines));
			}
		} else if (request.getParameter("id") != null) {
			if (!request.getParameter("id").equals("")) {
				String reference = request.getParameter("reference");
				String marque = request.getParameter("marque");
				double prix = Double.parseDouble(request.getParameter("prix"));
				Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
				int id = Integer.parseInt(request.getParameter("id"));
				Machine m = ms.findById(id);
				m.setReference(reference);
				m.setPrix(prix);
				m.setDateAchat(dateAchat);
				m.setMarque(marque);
				m.setSalle(ss.findById(Integer.parseInt(request.getParameter("salle"))));
				ms.update(m);
				response.setContentType("application/json");
				List<Machine> machines = ms.findAll();
				Gson json = new Gson();
				response.getWriter().write(json.toJson(machines));
			}
		} else {
			boolean test = false;
			String reference = request.getParameter("reference");
			double prix = Double.parseDouble(request.getParameter("prix"));
			String marque = request.getParameter("marque");
			Date dateAchat = new Date(request.getParameter("dateAchat").replace("-", "/"));
			Salle s = ss.findById(Integer.parseInt(request.getParameter("salle")));
			if (!ms.findMachineByReference(reference).isEmpty()) {
				test = true;
			}
			ms.create(new Machine(reference, dateAchat, prix, s,marque));
			response.setContentType("application/json");
			List<Machine> machines = ms.findAll();
			Gson json = new Gson();
			String both = "[" + json.toJson(test) + "," + json.toJson(machines) + "]";
			response.getWriter().write(both);
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

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>
}
