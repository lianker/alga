package net.arkalis.alga.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.arkalis.alga.models.StatusTitulo;
import net.arkalis.alga.models.Titulo;
import net.arkalis.alga.repository.Titulos;

@Controller
@RequestMapping("/titulos")
public class TituloController {

	private static final String CADASTRO_VIEW = "CadastroTitulo";

	@Autowired
	private Titulos titulos;

	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject(new Titulo());
		return modelAndView;
	}

	@RequestMapping("/pesquisa")
	public ModelAndView pesquisa() {
		ModelAndView modelAndView = new ModelAndView("pesquisaTitulo");
		List<Titulo> todosTitulos = titulos.findAll();
		modelAndView.addObject("AllTitulos", todosTitulos);
		return modelAndView;
	}

	/* 	public ModelAndView edicao(@PathVariable Long codigo) {
	 * 		Titulo titulo= titulos.findOne(codigo);>>> 
	 * Esse seria a assinatura convencional pegando o codigo e fazndo a busca,	
	 * para depois atrbuir ao objeto, o springo pode automatizaar conforme abaixo */
	
	@RequestMapping("{codigo}")
	public ModelAndView edicao(@PathVariable("codigo") Titulo titulo) {		
		ModelAndView modelAndView = new ModelAndView(CADASTRO_VIEW);
		modelAndView.addObject("titulo", titulo);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		//quando o spring percebe que o ID ja vem, ele atualiza em vez de salvar
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Os Títulos foram salvos com sucesso!");

		return "redirect:/titulos/novo";
	}
	
	@RequestMapping(value="{codigo}", method=RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		titulos.delete(codigo);
		attributes.addFlashAttribute("mensagem", "Titulo excluído com sucesso");
		return "redirect:/titulos/pesquisa";
	}

	@ModelAttribute("allStatusTitulo")
	public List<StatusTitulo> allStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}

}
