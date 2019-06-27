package it.capgemini.web.hello.listeners;

import java.io.InputStreamReader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import it.capgemini.SportsClub.model.FileDataSource;

@WebListener
public class ApplicationListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent sce) {

	}

	public void contextInitialized(ServletContextEvent sce) {
		InputStreamReader isr = new InputStreamReader(
				sce.getServletContext().getResourceAsStream("/WEB-INF/reservations.txt"));
		FileDataSource.setReader(isr);
	}

}
