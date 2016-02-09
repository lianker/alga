package net.arkalis.alga.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.arkalis.alga.models.StatusTitulo;
import net.arkalis.alga.models.Titulo;
import net.arkalis.alga.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView("CadastroTitulo");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {
		titulos.save(titulo);
		ModelAndView modelAndView = new ModelAndView("CadastroTitulo");
		return modelAndView;
	}

	@ModelAttribute("allStatusTitulo")
	public List<StatusTitulo> allStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
