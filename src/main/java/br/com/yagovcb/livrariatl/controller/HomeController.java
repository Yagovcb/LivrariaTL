package br.com.yagovcb.livrariatl.controller;

import br.com.yagovcb.livrariatl.model.Livro;
import br.com.yagovcb.livrariatl.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private LivroRepository repository;

    @GetMapping()
    public String home(Model model) {
        //Sort sort = Sort.by("dataCadastro").descending();
        //PageRequest paginacao = PageRequest.of(0, 2, sort);
        List<Livro> livros = repository.findAll();
        model.addAttribute("livros", livros);
        return "home";
    }
}
