package com.gabriel.noticias.controller;

import com.gabriel.noticias.dao.CategoriaDao;
import com.gabriel.noticias.dao.NoticiaDao;
import com.gabriel.noticias.model.Categoria;
import com.gabriel.noticias.model.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("noticia")
public class NoticiaController {

    @Autowired
    private NoticiaDao dao;

    @Autowired
    private CategoriaDao categoriaDao;

    @GetMapping("/todos")
    public String principal(@ModelAttribute("busca") String string, ModelMap model) {
        List<Noticia> noticias = dao.buscar(string);
        String msg = null;
        if (noticias.size() == 0) {
            if (string.equals("")) {
                msg = "Nenhuma notícia encontrada.";
            } else {
                msg = "Nenhuma notícia encontrada para a pesquisa '"+string+"'";
            }
        } else if (noticias.size() >= 1 && !string.equals("")) {
            msg = "Notícias encontradas para a pesquisa '" +string+"'";
        }

        model.addAttribute("message", msg);
        model.addAttribute("listaNoticias", noticias);
        model.addAttribute("listaCategorias", categoriaDao.findAll());
        model.addAttribute("titulo", "Bem-Vindo");
        model.addAttribute("conteudo", "/main/principal");
        return "layout";
    }

    @GetMapping("/buscar/{id}")
    public String buscar(@PathVariable("id") Long id, ModelMap model) {
        Optional<Categoria> categoria = categoriaDao.findById(id);
        List<Noticia> noticias = dao.buscarPorCategoria(id);
        String msg = null;

        if (noticias.size() == 0) {
            msg = "Nenhuma notícia encontrada em " + categoria.get().getNome();
        } else {
            msg = "Notícias em "+ categoria.get().getNome();
        }

        model.addAttribute("message", msg);
        model.addAttribute("listaNoticias", noticias);
        model.addAttribute("listaCategorias", categoriaDao.findAll());
        model.addAttribute("titulo", "Bem-Vindo");
        model.addAttribute("conteudo", "/main/principal");
        return "layout";
    }


    @GetMapping("/cadastro")
    public String cadastro(ModelMap model) {
        model.addAttribute("listaCategoria", categoriaDao.findAll());
        model.addAttribute("noticia", new Noticia());
        model.addAttribute("titulo", "Cadastro de Notícias");
        model.addAttribute("conteudo", "/news/addNoticia");
        return "layout";
    }

    @PostMapping("/save")
    public String salvar(@ModelAttribute("noticia") Noticia noticia, RedirectAttributes attributes) {
        dao.save(noticia);
        attributes.addFlashAttribute("msg", "Notícia cadastrada com sucesso.");
        return "redirect:/noticia/todos";
    }

    @GetMapping("/editar/{id}")
    public String preEditar(@PathVariable("id") Long id, ModelMap model) {
        Noticia noticia = dao.getById(id);
        model.addAttribute("listaCategoria", categoriaDao.findAll());
        model.addAttribute("noticia", noticia);
        model.addAttribute("titulo", "Editar Notícia");
        model.addAttribute("conteudo", "/news/addNoticia");
        return "layout";
    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("noticia") Noticia noticia, RedirectAttributes attributes) {
        dao.save(noticia);
        attributes.addFlashAttribute("msg", "Notícia alterada com sucesso.");
        return "redirect:/noticia/todos";
    }

    @GetMapping("/remover/{id}")
    public String remover(@PathVariable("id") Long id, RedirectAttributes attributes) {
        dao.deleteById(id);
        attributes.addFlashAttribute("msg", "Notícia removida com sucesso.");
        return "redirect:/noticia/todos";
    }

}
