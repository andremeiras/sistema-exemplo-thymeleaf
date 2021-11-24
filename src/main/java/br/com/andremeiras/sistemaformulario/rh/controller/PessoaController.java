package br.com.andremeiras.sistemaformulario.rh.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.andremeiras.sistemaformulario.rh.domain.Pessoa;
import br.com.andremeiras.sistemaformulario.rh.repository.PessoaRepository;

@Controller
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRepository;

	@GetMapping("/rh/pessoas")
	public String pessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepository.findAll());

		return "rh/pessoas/index";
	}

	@GetMapping("rh/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		return "rh/pessoas/form";
	}

	@GetMapping("/rh/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") long id, Model model) {

		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id); // Optional é utilizado para evitar problemas com
																	// NullPointerException ou valores nulos.
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida!");
		}

		model.addAttribute("pessoa", pessoaOpt.get());
		return "rh/pessoas/form";
	}

	@PostMapping("/rh/pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepository.save(pessoa);
		return "redirect:/rh/pessoas";
	}

	@GetMapping("/rh/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") long id) {
		Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id); // Optional é utilizado para evitar problemas com
																	// NullPointerException ou valores nulos.
		if (pessoaOpt.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida!");
		}

		pessoaRepository.delete(pessoaOpt.get());
		return "redirect:/rh/pessoas";
	}

}
