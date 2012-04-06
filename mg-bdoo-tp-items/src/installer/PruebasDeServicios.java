package installer;

import java.util.Collection;

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
import user.exception.UserAlreadyExistsException;
import user.repository.HibernetUserRepository;
import user.repository.MemoryUserRepository;
import user.service.UserServiceBI;
import base.repository.HibernateRepositoryFinder;
import base.repository.MemoryRepositoryFinder;
import base.service.ServiceFinder;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class PruebasDeServicios {
	final static String CONTEXT = "applicationContext.xml";

	public static void main(String[] args) {
	
		String[] contextPaths = new String[] { CONTEXT };
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext(contextPaths);

//		printBeans(ctx);
		prubeaDeServiciosUser(ctx);
		
	}
	private static void prubeaDeServiciosUser(AbstractApplicationContext ctx){
		
		UserServiceBI userService = (UserServiceBI) ctx.getBean("userService");
		
		//create
		UserDTO userDTO;
		try {
			userDTO = userService.createUser("test_to_remove","test_to_remove");
			System.out.print("Se creo el usuario: "+userDTO);
			System.out.print("\n");
		} catch (UserAlreadyExistsException userAlreadyExistsException) {
			System.out.print(userAlreadyExistsException.getMsj());
			System.out.print("\n");
		}
		
		// list
		System.out.print("\n Listando usuarios \n");		
		Collection<UserDTO> usersDTO = userService.listUsers();
		for(UserDTO userDTOindex: usersDTO){
			System.out.print(userDTOindex);
			System.out.print("\n");
		}
		
		// remove
		System.out.print("\n Eliminando: test_to_remove \n");
		userService.removeUserByName("test_to_remove");

		// list
		System.out.print("\n Listando usuarios \n");
		usersDTO = userService.listUsers();
		for(UserDTO userDTOindex: usersDTO){
			System.out.print(userDTOindex);
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	@SuppressWarnings("unused")
	private static void printBeans(AbstractApplicationContext ctx) {
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
		
		ItemTrackerServiceBI itemTrackerService = (ItemTrackerServiceBI) ctx.getBean("itemTrackerService");
		System.out.println("itemTrackerService: " + itemTrackerService);
		
		UserServiceBI userService = (UserServiceBI) ctx.getBean("userService");
		System.out.println("userService: " + userService);
	}

}
