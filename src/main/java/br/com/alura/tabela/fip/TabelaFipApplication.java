package br.com.alura.tabela.fip;

import br.com.alura.tabela.fip.principal.principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelaFipApplication implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {

		principal principal=new principal();
		principal.exiberMenu();


	}

	public static void main(String[] args) {
		SpringApplication.run(TabelaFipApplication.class, args);
	}


}
