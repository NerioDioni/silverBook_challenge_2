package com.bestbook.silverbook;
import com.bestbook.silverbook.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SilverbookApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SilverbookApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestraElMenu();

	}
}

