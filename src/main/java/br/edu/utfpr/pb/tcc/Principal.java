package br.edu.utfpr.pb.tcc;

import br.edu.utfpr.pb.tcc.model.JdbcConnection;
import br.edu.utfpr.pb.tcc.service.JasperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class Principal {

	public static void main(String[] args) throws SQLException {

		SpringApplication.run(Principal.class, args);
	}
	@Bean
	public LayoutDialect layoutDialect() {
		return new LayoutDialect();
	}


	private static void abrirPontoJasper(String numero) throws SQLException {
		Connection connection = JdbcConnection.connection();
		JasperService service = new JasperService();
		service.abrirPontoJasper("jasper/produtos-" +numero+ ".jasper",connection);
		connection.close();
	}

	private static void exportarParaPDF(String numero, String saida) throws SQLException {
		Connection connection = JdbcConnection.connection();
		JasperService service = new JasperService();
		service.exportarParaPDF("relatorios/jrxml/produtos-"+numero+ ".jrxml",connection, saida);
		connection.close();

	}

	private static void abrirJrxml(String numero) throws SQLException {
		Connection connection = JdbcConnection.connection();
		JasperService service = new JasperService();
		service.abrirJasperViewer("relatorios/jrxml/funcionarios-"+numero+ ".jrxml",connection);
		connection.close();
	}
}


