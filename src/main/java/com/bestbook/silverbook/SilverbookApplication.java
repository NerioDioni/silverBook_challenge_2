package com.bestbook.silverbook;
import com.bestbook.silverbook.service.ConsumoApi;
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
		var consumoApi=new ConsumoApi();
		var json =consumoApi.obtenerDatos("https://gutendex.com/books/?id=996");
		System.out.println(json);


	}
}

