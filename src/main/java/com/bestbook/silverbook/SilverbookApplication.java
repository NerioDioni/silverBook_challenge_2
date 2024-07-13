package com.bestbook.silverbook;
import com.bestbook.silverbook.principal.Principal;
import com.bestbook.silverbook.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SilverbookApplication implements CommandLineRunner {
	@Autowired
	private AutorRepository autorR;

	public static void main(String[] args) {
		SpringApplication.run(SilverbookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(autorR);
		principal.muestraElMenu();

	}
}

