package com.tasks.tasks.service;

import com.tasks.tasks.model.TaskStatus;
import com.tasks.tasks.model.Tasks;
import com.tasks.tasks.model.TasksPriority;
import com.tasks.tasks.repository.TasksRepository;
import org.apache.tomcat.util.http.parser.Priority;
import org.hibernate.engine.spi.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TasksService {


    @Autowired
    private TasksRepository tasksRepository;

    public List<Tasks> listarTodasTarefas() {
        return tasksRepository.findAll();
    }

    public List<Tasks> listarPrioridadeAlta() {
        List<Tasks> l = new ArrayList<>();
        for (Tasks t : listarTodasTarefas()) {
            if (t.getPriority() == TasksPriority.Alta) {
                l.add(t);
            }
        }
        return l;
    }

    public List<Tasks> listarPrioridadeMedia() {
        List<Tasks> l = new ArrayList<>();
        for (Tasks t : listarTodasTarefas()) {
            if (t.getPriority() == TasksPriority.Media) {
                l.add(t);
            }
        }
        return l;
    }

    public List<Tasks> listarPrioridadeBaixa() {
        List<Tasks> l = new ArrayList<>();
        for (Tasks t : listarTodasTarefas()) {
            if (t.getPriority() == TasksPriority.Baixa) {
                l.add(t);
            }
        }
        return l;
    }

    public List<Tasks> listarFazer() {
        List<Tasks> l = new ArrayList<>();
        for (Tasks t : listarTodasTarefas()) {
            if (t.getStatus() == TaskStatus.Fazer) {
                l.add(t);
            }
        }
        return l;
    }

    public List<Tasks> listarFazendo() {
        List<Tasks> l = new ArrayList<>();
        for (Tasks t : listarTodasTarefas()) {
            if (t.getStatus() == TaskStatus.Fazendo) {
                l.add(t);
            }
        }
        return l;
    }

    public List<Tasks> listarConcluida() {
        List<Tasks> l = new ArrayList<>();
        for (Tasks t : listarTodasTarefas()) {
            if (t.getStatus() == TaskStatus.Concluida) {
                l.add(t);
            }
        }
        return l;
    }

    public Tasks criarTarefa(Tasks tasks) {
        tasks.setStatus(TaskStatus.Fazer);
        return tasksRepository.save(tasks);
    }

    public Tasks listarTarefaId(Long id) {
        return tasksRepository.findById(id).orElse(null);
    }

    public Tasks moverTarefa(Long id) {
        Tasks tasks = tasksRepository.findById(id).orElse(null);

        if (tasks.getStatus() == TaskStatus.Fazer) {
            tasks.setStatus(TaskStatus.Fazendo);
        } else if (tasks.getStatus() == TaskStatus.Fazendo) {
            tasks.setStatus(TaskStatus.Concluida);
        }

        return tasksRepository.save(tasks);
    }

    public Tasks modficarTarefa(Long id, Tasks tasksDetails) {
        Tasks tasks = tasksRepository.findById(id).orElse(null);

        tasks.setTitle(tasksDetails.getTitle());
        tasks.setDescription(tasksDetails.getDescription());
        tasks.setPriority(tasksDetails.getPriority());
        tasks.setDueDate(tasksDetails.getDueDate());

        return tasksRepository.save(tasks);
    }

    public List<Tasks> filtrarTarefas(Priority prioridade, Date dateLimit) {
        List<Tasks> tarefas = tasksRepository.findAll();
        List<Tasks> tarefasFiltradas = new ArrayList<>();

        for (Tasks tarefa : tarefas) {
            boolean prioridadeMatch = (prioridade == null || tarefa.getPriority().equals(prioridade));
            boolean dataMatch = (dateLimit == null || tarefa.getCreatedAt().equals(dateLimit));
            if (prioridadeMatch && dataMatch) {
                tarefasFiltradas.add(tarefa);
            }
        }

        return tarefasFiltradas;
    }


    public Tasks deletarTarefa(Long id) {
        Tasks tasks = tasksRepository.findById(id).orElse(null);
        tasksRepository.delete(tasks);
        return tasks;
    }


}
