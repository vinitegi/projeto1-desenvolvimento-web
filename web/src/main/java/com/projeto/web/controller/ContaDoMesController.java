package com.projeto.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.web.model.ContaDoMes;
import com.projeto.web.repository.ContaDoMesRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/contas")
public class ContaDoMesController {

    private final ContaDoMesRepository repository;

    public ContaDoMesController(ContaDoMesRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listar(Model model) {
        return tela(model, new ContaDoMes(), false);
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model) {
        return repository.findById(id)
                .map(c -> tela(model, c, true))
                .orElse("redirect:/contas");
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("conta") ContaDoMes conta,
                         BindingResult result,
                         @RequestParam(defaultValue = "false") boolean editando,
                         Model model) {
        if (result.hasErrors()) {
            return tela(model, conta, editando);
        }
        if (!editando) {
            conta.setId(null); // cadastro novo: o id e gerado pelo banco
        }
        repository.save(conta);
        return "redirect:/contas";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/contas";
    }

    private String tela(Model model, ContaDoMes conta, boolean editando) {
        model.addAttribute("conta", conta);
        model.addAttribute("editando", editando);
        model.addAttribute("lista", repository.findAll());
        return "contas";
    }
}
