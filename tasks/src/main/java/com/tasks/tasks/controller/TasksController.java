package com.tasks.tasks.controller;

import com.tasks.tasks.model.Tasks;
import com.tasks.tasks.service.TasksService;
import org.apache.tomcat.util.http.parser.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

//Criar filtro pra data atrasada
@RestController
@RequestMapping("/tasks")
public class TasksController {

    @Autowired
    private TasksService tasksService;

    @GetMapping
    public List<Tasks> listarTodasTarefas() {
        return tasksService.listarTodasTarefas();
    }

    @GetMapping("/priority/Alta")
    public List<Tasks> listarPorPrioridadeAlta() {
        return tasksService.listarPrioridadeAlta();
    }

    @GetMapping("/priority/Media")
    public List<Tasks> listarPorPrioridadeMedia() {
        return tasksService.listarPrioridadeMedia();
    }

    @GetMapping("/priority/Baixa")
    public List<Tasks> listarPorPrioridadeBaixa() {
        return tasksService.listarPrioridadeBaixa();
    }

    @GetMapping("/status/Fazer")
    public List<Tasks> listarTarefasFazer() {
        return tasksService.listarFazer();
    }

    @GetMapping("/status/Fazendo")
    public List<Tasks> listarTarefasFazendo() {
        return tasksService.listarFazendo();
    }

    @GetMapping("/status/Concluida")
    public List<Tasks> listarTarefasConcluida() {
        return tasksService.listarConcluida();
    }

    @PostMapping
    public Tasks criarTarefa(@Validated @RequestBody Tasks tasks) {
        return tasksService.criarTarefa(tasks);
    }

    @PutMapping("/{id}/move")
    @ResponseBody
    public Tasks moverTarefa(@PathVariable Long id) {
        return tasksService.moverTarefa(id);
    }


    @PutMapping("/{id}")
    public Tasks atualizarTearefa(@PathVariable Long id, @RequestBody Tasks tasksDetails) {
        return tasksService.modficarTarefa(id, tasksDetails);
    }

    @DeleteMapping("/{id}")
    public Tasks deletarTarefa(@PathVariable Long id) {
        return tasksService.deletarTarefa(id);
    }

    @GetMapping("/filter")
    public List<Tasks> filtrarTarefas(
            @RequestParam(required = false) Priority priority,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dueData) {
        return tasksService.filtrarTarefas(priority,dueData);
    }
}

