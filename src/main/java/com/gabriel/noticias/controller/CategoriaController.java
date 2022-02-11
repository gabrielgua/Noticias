package com.gabriel.noticias.controller;

import com.gabriel.noticias.dao.CategoriaDao;
import com.gabriel.noticias.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("categoria")
public class CategoriaController {


    @Autowired
    private CategoriaDao dao;

    @GetMapping("/todos")
    public String categorias(ModelMap model) {
        model.addAttribute("listaCategorias", dao.findAll());
        model.addAttribute("titulo", "Categorias");
        model.addAttribute("conteudo", "/categories/todos");
        return "layout";
    }

    @GetMapping("/cadastro")
    public String cadastrar(ModelMap model) {
        model.addAttribute("categoria", new Categoria());
        model.addAttribute("titulo", "Cadastro de Categoria");
        model.addAttribute("conteudo", "/categories/addCategoria");
        return "layout";
    }

    @PostMapping("/save")
    public String salvar(@ModelAttribute("categoria") Categoria categoria, RedirectAttributes attributes) {
        dao.save(categoria);
        attributes.addFlashAttribute("message", "Categoria cadastrada com sucesso.");
        return "redirect:/categoria/todos";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        Categoria categoria = dao.getById(id);
        model.addAttribute("categoria", categoria);
        model.addAttribute("titulo", "Editar Categoria");
        model.addAttribute("conteudo", "/categories/addCategoria");
        return "layout";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("categoria") Categoria categoria, RedirectAttributes attributes) {
        dao.save(categoria);
        attributes.addFlashAttribute("message", "Categoria alterada com sucesso.");
        return "redirect:/categoria/todos";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attributes) {
        dao.deleteById(id);
        attributes.addFlashAttribute("message", "Categoria removida com sucesso.");
        return "redirect:/categoria/todos";
    }
}
