package installer;

import itemTracker.repository.HibernetItemTrackerRepository;
import itemTracker.repository.MemoryItemTrackerRepository;
import itemTracker.service.ItemTrackerServiceBI;

import org.hibernate.impl.SessionFactoryImpl;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.LocalSessionFactoryBean;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import user.domain.User;
import user.dto.UserDTO;
import user.repository.HibernetUserRepository;
import user.repository.MemoryUserRepository;
import user.service.UserServiceBI;
import base.repository.HibernateRepositoryFinder;
import base.repository.MemoryRepositoryFinder;
import base.service.ServiceFinder;

public class PruebasDeServicios {
	final static String CONTEXT = "applicationContext.xml";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		String[] contextPaths = new String[] { CONTEXT };
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);

		PropertyPlaceholderConfigurer configurer = (PropertyPlaceholderConfigurer) ctx.getBean("propertyConfigurer");
		System.out.println("configurer: " + configurer);
		
		ComboPooledDataSource dataSource = (ComboPooledDataSource) ctx.getBean("dataSource");
		System.out.println("dataSource: " + dataSource);
		
		// REPOSITORISO FINDER
		
		HibernateRepositoryFinder hibernateRepositoryFinder = (HibernateRepositoryFinder) ctx.getBean("hibernateRepositoryFinder");
		System.out.println("hibernateRepositoryFinder: " + hibernateRepositoryFinder);
		
		MemoryRepositoryFinder memoryRepositoryFinder = (MemoryRepositoryFinder) ctx.getBean("memoryRepositoryFinder");
		System.out.println("memoryRepositoryFinder: " + memoryRepositoryFinder);

		// SEASSION FACTORY

		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ctx.getBean("sessionFactory");
		System.out.println("sessionFactory: " + sessionFactory);

		// REPOSITORIOS HIBERNATE
		
		HibernetItemTrackerRepository hibernateItemTrackerRepository = (HibernetItemTrackerRepository) ctx.getBean("hibernateItemTrackerRepository");
		System.out.println("hibernateItemTrackerRepository: " + hibernateItemTrackerRepository);
		
		HibernetUserRepository hibernateUserRepository = (HibernetUserRepository) ctx.getBean("hibernateUserRepository");
		System.out.println("hibernateUserRepository: " + hibernateUserRepository);

		// REPOSITORIOS MEMORIA
		
		MemoryItemTrackerRepository memoryItemTrackerRepository = (MemoryItemTrackerRepository) ctx.getBean("memoryItemTrackerRepository");
		System.out.println("memoryItemTrackerRepository: " + memoryItemTrackerRepository);
		
		MemoryUserRepository memoryUserRepository = (MemoryUserRepository) ctx.getBean("memoryUserRepository");
		System.out.println("memoryUserRepository: " + memoryUserRepository);
		
		// SERVICIOS
		
		UserServiceBI userService = (UserServiceBI) ctx.getBean("userService");
		System.out.println("userService: " + userService);
		
		ItemTrackerServiceBI itemTrackerService = (ItemTrackerServiceBI) ctx.getBean("itemTrackerService");
		System.out.println("itemTrackerService: " + itemTrackerService);
		
		UserServiceBI userServiceOb = ServiceFinder.getInstance().getUserService();
		UserDTO userDTO = userService.createUser("asd","asd");
	}

}
