package com.peliculas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.dao.implement.impRepoTrabajoActor;
import com.repositoriosJPA.intRepoJPAactor;
import com.repositoriosJPA.intRepoJPAfilm;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories({"com.repositoriosJPA"})
@EntityScan("com.entidades")
@ComponentScan("com.*")
public class PeliculasApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(PeliculasApplication.class, args);
	}

	@Autowired
	intRepoJPAactor repoJPAactor;
	
	@Autowired
	intRepoJPAfilm repoJPAfilm;
	
	@Autowired
	impRepoTrabajoActor repoActor;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Imprimiendo con conexión manual HIKARI incluida en el método: ");
		//repoActor.metodoResultSetHIKARI();
		System.out.println("Imprimiendo con conexion manual MySQLDataSource incluida en el mpropio método: ");
		//repoActor.metodoResultSetDATASOURCE();
		System.out.println("Imprimiendo con plantilla auto JDBCTemplate");
		repoActor.metodoResultSetJDBCTemplate();
	}
}
