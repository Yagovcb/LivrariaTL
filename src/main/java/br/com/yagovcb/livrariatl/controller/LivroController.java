package br.com.yagovcb.livrariatl.controller;

import br.com.yagovcb.livrariatl.dto.RequisicaoNovoLivro;
import br.com.yagovcb.livrariatl.model.Livro;
import br.com.yagovcb.livrariatl.repository.LivroRepository;
import br.com.yagovcb.livrariatl.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @GetMapping("formulario")
    public String formulario(RequisicaoNovoLivro requisicaoNovoLivro, Model model) {
        model.addAttribute("requisicaoNovoLivro", requisicaoNovoLivro);
        return "livro/formulario";
    }

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @PostMapping("novo")
    public ModelAndView novo(@ModelAttribute("requisicaoNovoLivro")@Valid RequisicaoNovoLivro requisicaoNovoLivro, ModelAndView mav) {
        Livro registrado = new Livro();
        try {
            registrado = livroService.registrar(requisicaoNovoLivro);
        } catch (Exception e){
            mav.addObject("message", "Uma conta já existe.");
            return mav;
        }
        return new ModelAndView("usuario/home", "requisicaoNovoLivro", requisicaoNovoLivro);
    }

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @GetMapping("/{id}")
    public ModelAndView getDetalhamentoLivro (@PathVariable("id") long id){
        Livro livro = livroService.buscaLivro(id);
        return new ModelAndView("livro/detalhamentoLivro", "livro", livro);
    }

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @PostMapping("/{id:[0-9]+}/editar")
    public ModelAndView Editar(@PathVariable("id") long id) {
        Livro registrado = livroService.editar(id);
        return new ModelAndView("usuario/home", "requisicaoNovoLivro", registrado);
    }

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @GetMapping("/{id}/editar")
    public ModelAndView getEditar (@PathVariable("id") long id){
        Livro livro = livroService.buscaLivro(id);
        return new ModelAndView("livro/editar", "livro", livro);
    }

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @GetMapping("/{id}/delete")
    public ModelAndView getDeleteLivro (@PathVariable("id") long id){
        Livro livro = livroService.buscaLivro(id);
        return new ModelAndView("livro/excluir", "livro", livro);
    }

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @PostMapping("/{id:[0-9]+}/delete")
    public ModelAndView DeleteLivro (@PathVariable("id") long id, Model model, Principal principal) throws Exception{
        List<Livro> livros = livroRepository.findAllByUsuario(principal.getName());
        try {
            livroService.delete(id);
        } catch (Exception e){
            throw  new Exception("Não foi possivel deletar o livro");
        }
        return new ModelAndView("usuario/home", "livros", livros);
    }
}
