package com.example.demo.controller;
import com.example.demo.model.Task;
import com.example.demo.model.Usuario;
import com.example.demo.service.TaskService;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
@Path("/task")
public class TaskController {
    @Inject
    private TaskService taskService;

    @GET
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response listarTasks() {
        return Response.ok(taskService.listarTasks()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response buscarTask(@PathParam("id") Integer id) {
        Task task = taskService.buscarTaskPorID(id);
        if (task != null) {
            return Response.ok(task).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Tarefa não encontrada").build();
        }
    }

    @POST
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response cadastrarTask(Task task) {
        try {
            taskService.cadastrarTask(task);
            return Response.status(Response.Status.CREATED).entity("Tarefa cadastrada com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response atualizarTask(@PathParam("id") Integer id, Task task) {
        task.setId(id);
        try {
            taskService.atualizarTask(task);
            return Response.ok("Tarefa atualizada com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response excluirTask(@PathParam("id") Integer id) {
        try {
            taskService.excluirTask(id);
            return Response.ok("Tarefa excluída com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        }
    }
    @PUT
    @Path("/{taskId}/assign/{userId}")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response atribuirUsuario(@PathParam("taskId") Integer taskId, @PathParam("userId") Integer userId) {
        try {
            if (taskId <= 0 || userId <= 0) {
                throw new IllegalArgumentException("ID da tarefa e do usuário devem ser números positivos");
            }
            taskService.atribuirUsuario(taskId, userId);
            return Response.ok("Usuário atribuído à tarefa com sucesso").build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ocorreu um erro ao atribuir o usuário à tarefa").build();
        }
    }

    @PUT
    @Path("/{taskId}/unassign")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response removerUsuario(@PathParam("taskId") Integer taskId) {
        try {
            if (taskId <= 0) {
                throw new IllegalArgumentException("ID da tarefa deve ser um número positivo");
            }
            taskService.removerUsuario(taskId);
            return Response.ok("Usuário removido da tarefa com sucesso").build();
        } catch (IllegalArgumentException ex) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ocorreu um erro ao remover o usuário da tarefa").build();
        }
    }
    @PUT
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response atualizarTask(Task task, @Context HttpServletRequest request) {
        // Recupere o usuário atual da sessão ou do token JWT, se necessário
        Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
        try {
            taskService.atualizarTask(task, usuario);
            return Response.ok("Tarefa atualizada com sucesso").build();
        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Ocorreu um erro ao atualizar a tarefa").build();
        }
    }

}
