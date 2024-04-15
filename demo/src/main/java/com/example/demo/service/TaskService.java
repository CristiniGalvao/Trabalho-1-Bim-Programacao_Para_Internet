package com.example.demo.service;
import com.example.demo.model.Task;
import com.example.demo.model.Usuario;
import com.example.demo.model.Historico;
import com.example.demo.repository.TaskRepository;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.Date;
import java.util.List;
@Stateless
public class TaskService {
    @Inject
    private TaskRepository taskRepository;

    @Inject
    private HistoricoService historicoService;

    public List<Task> listarTasks() {
        return taskRepository.listarTodos();
    }

    public Task buscarTaskPorID(Integer id) {
        return taskRepository.buscarPorID(id);
    }

    public void cadastrarTask(Task task) throws Exception {
        taskRepository.cadastrar(task);
    }

    public void atualizarTask(Task task) throws Exception {
        taskRepository.atualizar(task);
    }

    public void excluirTask(Integer id) throws Exception {
        Task task = taskRepository.buscarPorID(id);
        if (task != null) {
            taskRepository.excluir(task);
        } else {
            throw new Exception("Tarefa não encontrada com o ID fornecido");
        }
    }

    public void atribuirUsuario(Integer taskId, Integer userId) throws Exception {
        Task task = taskRepository.buscarPorID(taskId);
        if (task == null) {
            throw new Exception("Tarefa não encontrada com o ID fornecido");
        }

        task.setUsuarioId(userId);
        taskRepository.atualizar(task);
    }

    public void removerUsuario(Integer taskId) throws Exception {
        Task task = taskRepository.buscarPorID(taskId);
        if (task == null) {
            throw new Exception("Tarefa não encontrada com o ID fornecido");
        }
        task.setUsuarioId(null);
        taskRepository.atualizar(task);
    }


    public void atualizarTask(Task task, Usuario usuario) throws Exception {
        
        Historico historico = new Historico();
        historico.setDescricao("Tarefa atualizada");
        historico.setDataAlteracao(new Date());
        historico.setObservacao("Tarefa atualizada por " + usuario.getNome());
        historico.setPrioridade(task.getPrioridade());
        historico.setStatus(task.getStatus());
        historico.setUsuario(usuario);
        historico.setTask(task);
        historicoService.salvarHistorico(historico);


        taskRepository.atualizar(task);
    }
}
