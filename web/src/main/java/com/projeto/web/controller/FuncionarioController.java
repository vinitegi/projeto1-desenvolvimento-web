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

import com.projeto.web.model.Funcionario;
import com.projeto.web.repository.FuncionarioRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioRepository repository;

    public FuncionarioController(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // Lista os registros e mostra o formulario vazio
    @GetMapping
    public String listar(Model model) {
        return tela(model, new Funcionario(), false);
    }

    // Carrega o registro no formulario para editar
    @GetMapping("/editar/{cpf}")
    public String editar(@PathVariable String cpf, Model model) {
        return repository.findById(cpf)
                .map(f -> tela(model, f, true))
                .orElse("redirect:/funcionarios");
    }

    // Salva (cadastro novo ou edicao)
    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("funcionario") Funcionario funcionario,
                         BindingResult result,
                         @RequestParam(defaultValue = "false") boolean editando,
                         Model model) {
        if (!editando && funcionario.getCpf() != null && repository.existsById(funcionario.getCpf())) {
            result.rejectValue("cpf", "duplicado", "CPF já cadastrado");
        }
        if (result.hasErrors()) {
            return tela(model, funcionario, editando);
        }
        repository.save(funcionario);
        return "redirect:/funcionarios";
    }

    @PostMapping("/excluir/{cpf}")
    public String excluir(@PathVariable String cpf) {
        repository.deleteById(cpf);
        return "redirect:/funcionarios";
    }

    private String tela(Model model, Funcionario funcionario, boolean editando) {
        model.addAttribute("funcionario", funcionario);
        model.addAttribute("editando", editando);
        model.addAttribute("lista", repository.findAll());
        return "funcionarios";
    }
}
