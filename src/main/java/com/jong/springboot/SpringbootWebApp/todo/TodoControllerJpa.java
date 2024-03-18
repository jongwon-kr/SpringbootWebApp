package com.jong.springboot.SpringbootWebApp.todo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
@SessionAttributes("name")
public class TodoControllerJpa {

    private TodoRepository todoRepository;

    @Autowired
    public TodoControllerJpa(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    // /list-todos
    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getloggedinusername();

        List<Todo> todos = todoRepository.findByUsername(username);
        model.addAttribute("todos", todos);

        return "listTodos";
    }

    private static String getloggedinusername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    // /list-todos
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getloggedinusername();
        Todo todo = new Todo(0, username, "Default Desc", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    // add todo
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodoPage(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String username = getloggedinusername();
        todo.setUsername(username);
        todoRepository.save(todo);

        return "redirect:list-todos";
    }

    // delete todo
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        // Delete todo
        todoRepository.deleteById(id);

        return "redirect:list-todos";
    }

    // update todo
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        // Update todo
        Todo todo = todoRepository.findById(id).get();
        model.addAttribute("todo", todo);
        return "todo";
    }

    // add todo
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String username = getloggedinusername();
        todo.setUsername(username);
        todoRepository.save(todo);
        return "redirect:list-todos";
    }
}
