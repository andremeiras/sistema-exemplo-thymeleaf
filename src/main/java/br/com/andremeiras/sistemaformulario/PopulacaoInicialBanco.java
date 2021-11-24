package br.com.andremeiras.sistemaformulario;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.andremeiras.sistemaformulario.rh.domain.Pessoa;
import br.com.andremeiras.sistemaformulario.rh.repository.PessoaRepository;

@Component // para o Spring Boot reconhecer como um Bean do projeto
@Transactional // para persistir em uma transação. Caso houver erro, faz rollback
public class PopulacaoInicialBanco implements CommandLineRunner {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Override
	public void run(String... args) throws Exception {

		Pessoa p1 = new Pessoa("João");
		p1.setDataNascimento(LocalDate.of(1990, 2, 2));
		p1.setEmail("joao@joao.com");
		p1.setCpf("98765335-0");
		p1.setTelefone("(41) 99987-4532");

		Pessoa p2 = new Pessoa("Maria");
		p2.setDataNascimento(LocalDate.of(1992, 7, 12));
		p2.setEmail("maria@maria.com");
		p2.setCpf("76533321-98");
		p2.setTelefone("(41) 88967-8442");

		pessoaRepository.save(p1);
		pessoaRepository.save(p2);

	}

}
