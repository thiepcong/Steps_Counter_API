package com.example.stepcounter;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.github.alexdlaird.ngrok.NgrokClient;
import com.github.alexdlaird.ngrok.protocol.CreateTunnel;
import com.github.alexdlaird.ngrok.protocol.Tunnel;

@SpringBootApplication
public class StepcounterApplication {
	@Bean
	 public RestTemplate getRestTemplate() {
	    return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(StepcounterApplication.class, args);
		final NgrokClient ngrokClient = new NgrokClient.Builder().build();
        final CreateTunnel createTunnel = new CreateTunnel.Builder()
                        .withAddr(8070)
                        .build();
        final Tunnel tunnel = ngrokClient.connect(createTunnel);
	}

}
