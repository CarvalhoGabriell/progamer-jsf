package br.com.fiap.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;

@Path("/setups")
@Produces(MediaType.APPLICATION_JSON)
public class SetupEndPoint {
	
	SetupDAO dao = new SetupDAO();
	
	@GET
	public List<Setup> setupEndpoint() {
		
		return dao.getAll();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createSetup(Setup setup) {
		
		if (setup == null) {
			
			return Response
					.status(Response.Status.BAD_REQUEST)
					.build(); //400
		}
		try {
			dao.save(setup);
		} catch (Exception e) {
			
			return Response.status(
					Response.Status.INTERNAL_SERVER_ERROR)
					.entity(setup)
					.build(); //500
		}
		return Response.status(Response.Status.CREATED)
				.entity(setup)
				.build(); //201
	}
	
	/*
	 * Nome dado no Path deve ser igual ao colocado no PATHPARAM dentro do metodo
	 */
	@GET
	@Path("{id}")
	public Response showAll(@PathParam("id") Long id) {
		
		Setup setup = dao.findById(id);
		if (setup == null) {
			
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
		}
		return Response
				.status(Response.Status.OK)
				.entity(setup)
				.build();
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Setup setup) {
		
		if (setup == null) {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(setup)
					.build();
		}
		
		setup.setId(id);
		if (dao.findById(id) == null) {
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
		}
		try {
			dao.updateSetup(setup);
		} catch (Exception e) {
			return Response.status(
					Response.Status.INTERNAL_SERVER_ERROR)
					.entity(setup)
					.build(); //500
		}

		return Response
				.status(Response.Status.OK)
				.entity(setup)
				.build();
	}
	
	
	@DELETE
	@Path("{id}")
	public Response destroy(@PathParam("id")Long id, Setup setup) {
		setup = dao.findById(id);
		if (setup == null) {
			
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
		}
		
		try {
			dao.deleteSetup(setup);
		} catch (Exception e) {
			
			return Response.status(
					Response.Status.INTERNAL_SERVER_ERROR)
					.entity(setup)
					.build(); //500
		}
		
		return Response
				.status(Response.Status.OK)
				.entity(setup)
				.build();
		
	}
}
