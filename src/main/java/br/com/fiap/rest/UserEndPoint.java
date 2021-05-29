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

import br.com.fiap.dao.UsuarioDAO;
import br.com.fiap.model.Usuario;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserEndPoint {
	
	UsuarioDAO dao = new UsuarioDAO();
	
	@GET
	public List<Usuario> indexUser() {
		
		System.out.println("Chamando o endpoint do usuario");
		return dao.getAllUsers();
	}
	
	@GET
	@Path("{id}")
	public Response showAllUser(@PathParam("id") Long id) {
		
		Usuario user = dao.findById(id);
		if (user == null) {
			
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
		}
		
		return Response
				.status(Response.Status.OK)
				.entity(user)
				.build();
		
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(Usuario user) {
		
		if (user == null) {
			
			return Response
					.status(Response.Status.BAD_REQUEST)
					.build(); //400
		}
		
		try {
			dao.saveUser(user);
		} catch (Exception e) {
			
			return Response.status(
					Response.Status.INTERNAL_SERVER_ERROR)
					.entity(user)
					.build(); //500
		}
		
		return Response
				.status(Response.Status.CREATED)
				.entity(user)
				.build(); //201
	}
	
	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Usuario user) {
		
		if (user == null) {
			return Response
					.status(Response.Status.BAD_REQUEST)
					.entity(user)
					.build();
			
		}
		user.setId(id);		
		if (dao.findById(id) == null) {
			
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
			
		}
			
		try {
			dao.updateUser(user);
		} catch (Exception e) {
			
			return Response.status(
					Response.Status.INTERNAL_SERVER_ERROR)
					.entity(user)
					.build(); //500
		}
	
		return Response
				.status(Response.Status.OK)
				.entity(user)
				.build();
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteUser(@PathParam("id") Long id,Usuario user) {
		user = dao.findById(id);
		if (user == null) {
			
			return Response
					.status(Response.Status.NOT_FOUND)
					.build();
		}
		
		try {
			dao.delete(id);
		} catch (Exception e) {
			
			return Response.status(
					Response.Status.INTERNAL_SERVER_ERROR)
					.entity(user)
					.build(); //500
		}
		
		return Response
				.status(Response.Status.OK)
				.entity(user)
				.build();
		
	}
}
