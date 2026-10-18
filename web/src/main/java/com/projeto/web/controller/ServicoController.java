package com.projeto.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projeto.web.dto.Servico;
import com.projeto.web.service.ApiClient;

import jakarta.validation.Valid;

/**
 * Tela de Servicos: os dados ficam na API, aqui so chamamos o ApiClient.
 */
@Controller
@RequestMapping("/servicos")
public class ServicoController {

    private static final String ERRO_API =
            "Não foi possível acessar a API. Verifique se o projeto API está rodando na porta 8080.";

    private final ApiClient api;

    public ServicoController(ApiClient api) {
        this.api = api;
    }

    @GetMapping
    public String listar(Model model) {
        return tela(model, new Servico(), false);
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Integer id, Model model, RedirectAttributes redirect) {
        try {
            return tela(model, api.buscarServico(id), true);
        } catch (RestClientException e) {
            redirect.addFlashAttribute("erro", ERRO_API);
            return "redirect:/servicos";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("servico") Servico servico,
                         BindingResult result,
                         @RequestParam(defaultValue = "false") boolean editando,
                         Model model) {
        if (result.hasErrors()) {
            return tela(model, servico, editando);
        }
        try {
            if (editando) {
                api.atualizarServico(servico.getId(), servico);
            } else {
                servico.setId(null); // o id e gerado pela API
                api.criarServico(servico);
            }
        } catch (RestClientException e) {
            model.addAttribute("erro", "Não foi possível salvar o serviço. " + ERRO_API);
            return tela(model, servico, editando);
        }
        return "redirect:/servicos";
    }

    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id, RedirectAttributes redirect) {
        try {
            api.excluirServico(id);
        } catch (RestClientException e) {
            redirect.addFlashAttribute("erro", ERRO_API);
        }
        return "redirect:/servicos";
    }

    private String tela(Model model, Servico servico, boolean editando) {
        model.addAttribute("servico", servico);
        model.addAttribute("editando", editando);
        try {
            model.addAttribute("lista", api.listarServicos());
        } catch (RestClientException e) {
            model.addAttribute("lista", List.of());
            model.addAttribute("erro", ERRO_API);
        }
        return "servicos";
    }
}
