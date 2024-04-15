package com.example.demo.controller;
import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/usuario")
public class UsuarioController {
    @Inject
    private UsuarioService usuarioService;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarUsuarios() {
        return Response.ok(usuarioService.listar()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response buscarUsuario(@PathParam("id") Integer id) {
        Usuario usuario = usuarioService.buscarPorID(id);
        if (usuario != null) {
            return Response.ok(usuario).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuário não encontrado").build();
        }
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response cadastrarUsuario(Usuario usuario) {
        try {
            usuarioService.cadastrar(usuario);
            return Response.status(Response.Status.CREATED).entity("Usuário cadastrado com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response atualizarUsuario(@PathParam("id") Integer id, Usuario usuario) {
        usuario.setId(id);
        try {
            usuarioService.atualizar(usuario);
            return Response.ok("Usuário atualizado com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response excluirUsuario(@PathParam("id") Integer id) {
        try {
            usuarioService.excluir(id);
            return Response.ok("Usuário excluído com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
}
