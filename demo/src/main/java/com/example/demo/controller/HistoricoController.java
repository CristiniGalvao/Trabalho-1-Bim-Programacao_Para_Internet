package com.example.demo.controller;
import com.example.demo.model.Historico;
import com.example.demo.service.HistoricoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/historico")
public class HistoricoController {
    @Inject
    private HistoricoService historicoService;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarHistoricos() {
        return Response.ok(historicoService.listar()).build();
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response cadastrarHistorico(Historico historico) {
        historicoService.cadastrar(historico);
        return Response.status(201)
                .entity("Histórico cadastrado com sucesso")
                .build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response atualizarHistorico(@PathParam("id") Integer id, Historico historico) {
        Historico historicoExistente = historicoService.buscarPorID(id);
        if (historicoExistente != null) {
            historico.setId(id);
            historicoService.atualizar(historico);
            return Response.ok(historico).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Histórico não encontrado")
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response excluirHistorico(@PathParam("id") Integer id) {
        Historico historicoExistente = historicoService.buscarPorID(id);
        if (historicoExistente != null) {
            historicoService.excluir(historicoExistente);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Histórico não encontrado")
                    .build();
        }
    }
}
