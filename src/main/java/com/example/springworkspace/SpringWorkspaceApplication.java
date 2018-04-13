package com.example.springworkspace;

import com.example.springworkspace.trash.PacketPid;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.springworkspace."})
public class SpringWorkspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringWorkspaceApplication.class, args);
	}
}
