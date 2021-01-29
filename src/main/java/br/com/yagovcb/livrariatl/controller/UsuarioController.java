package br.com.yagovcb.livrariatl.controller;

import br.com.yagovcb.livrariatl.dto.RequisicaoNovoUsuario;
import br.com.yagovcb.livrariatl.model.Livro;
import br.com.yagovcb.livrariatl.model.Usuario;
import br.com.yagovcb.livrariatl.repository.LivroRepository;
import br.com.yagovcb.livrariatl.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LivroRepository repository;

    @Secured({"ROLE_ADM","ROLE_USUARIO"})
    @GetMapping("livros")
    public String home(Model model, Principal principal) {
        List<Livro> livros = repository.findAllByUsuario(principal.getName());
        model.addAttribute("livros", livros);
        return "usuario/home";
    }

    @GetMapping("/cadastro")
    public String cadastro(RequisicaoNovoUsuario requisicaoNovoUsuario, Model model){
        model.addAttribute("requisicaoNovoUsuario", requisicaoNovoUsuario);
        return "usuario/cadastro";
    }

    @PostMapping("/cadastro")
    public ModelAndView cadastroNovoUsuario(@ModelAttribute("requisicaoNovoUsuario")
                                                @Valid RequisicaoNovoUsuario requisicaoNovoUsuario, ModelAndView mav){
        try {
            Usuario registrado = usuarioService.registro(requisicaoNovoUsuario);
        }catch (UsernameNotFoundException uaeEx){
            mav.addObject("message", "Uma conta já existe.");
            return mav;
        }

        return new ModelAndView("usuario/home", "requisicaoNovoUsuario", requisicaoNovoUsuario);
    }
}
