package installer;

import itemTracker.repository.HibernateItemTrackerRepository;
import itemTracker.repository.MemoryItemTrackerRepository;
import itemTracker.service.ItemTrackerServiceBI;

import java.util.Collection;

import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.dto.UserDTO;
import user.dto.UserDTOForLists;
import user.exception.IncorrectPasswordException;
import user.exception.UnknownUserException;
import user.exception.UserAlreadyExistsException;
import user.repository.HibernateUserRepository;
import user.repository.MemoryUserRepository;
import user.service.UserServiceBI;
import base.exception.DTOConcurrencyException;
import base.repository.HibernateRepositoryFinder;
import base.repository.MemoryRepositoryFinder;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class PruebasDeServicios {
	private final static String CONTEXT = "applicationContext.xml";
	private static String sessionToken;
	private static AbstractApplicationContext ctx;

	public static void main(String[] args) {

		String[] contextPaths = new String[] { CONTEXT };
		ctx = new ClassPathXmlApplicationContext(contextPaths);
		sessionToken = login("rodrigo", "rodrigo");
		// printBeans(ctx);
		prubeaDeServiciosUser();

	}

	private static String login(String anUserName, String aPassword) {
		ItemTrackerServiceBI itemTrackerService = (ItemTrackerServiceBI) ctx.getBean("itemTrackerService");
		try {
			return itemTrackerService.loginUser(anUserName, aPassword);
		} catch (UnknownUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IncorrectPasswordException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static void prubeaDeServiciosUser() {

		UserServiceBI userService = (UserServiceBI) ctx.getBean("userService");

		// create
		UserDTO userDTO;
		try {
			userDTO = userService.createUser(sessionToken, "test_to_remove", "test_to_remove");
			System.out.print("Se creo el usuario: " + userDTO);
			System.out.print("\n");
		} catch (UserAlreadyExistsException userAlreadyExistsException) {
			System.out.print(userAlreadyExistsException.getMsj());
			System.out.print("\n");
		}

		listUsers(userService);

		// remove
		System.out.print("\n Eliminando: test_to_remove \n");
		try {
			userService.logicalRemoveUserByUserName(sessionToken, "test_to_remove");
		} catch (UnknownUserException unknownUserException) {
			System.out.print("\n El usuarios no se puede eliminar porque no existe.\n");
		}

		listUsers(userService);

		// Editar con concurrencia.
		System.out.print("\n Editando usuario con concurrencia: test_to_remove \n");
		UserDTO userToEditDTO = null;
		UserDTO userToEditDTO2 = null;
		try {
			userToEditDTO = userService.getUserByUserName(sessionToken, "test_to_remove");
			userToEditDTO2 = userService.getUserByUserName(sessionToken, "test_to_remove");
		} catch (UnknownUserException e) {
			e.printStackTrace();
		}
		userToEditDTO.setPassword("el_nuevo_password3");
		try {
			userService.updateUser(sessionToken, userToEditDTO);
		} catch (UnknownUserException unknownUserException) {
			System.out.print("\n" + userToEditDTO2.getUserName() + ": "
					+ "El usuarios no se puede actualizar porque no existe.\n");
		} catch (DTOConcurrencyException dtoConcurrencyExecption) {
			System.out.print("\n" + userToEditDTO.getUserName() + ": " + dtoConcurrencyExecption.getMsj());
		}
		userToEditDTO2.setPassword("el_nuevo_password2");
		try {
			userService.updateUser(sessionToken, userToEditDTO2);
		} catch (UnknownUserException unknownUserException) {
			System.out.print("\n" + userToEditDTO2.getUserName() + ": "
					+ "El usuarios no se puede actualizar porque no existe.\n");
		} catch (DTOConcurrencyException dtoConcurrencyExecption) {
			System.out.print("\n" + userToEditDTO2.getUserName() + ": " + dtoConcurrencyExecption.getMsj());
		}

		listUsers(userService);

	}

	private static void listUsers(UserServiceBI userService) {
		Collection<UserDTOForLists> usersDTOForLists;
		// list
		System.out.print("\n Listando usuarios \n");
		usersDTOForLists = userService.listUsers(sessionToken);
		for (UserDTOForLists userDTOindex : usersDTOForLists) {
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

		HibernateRepositoryFinder hibernateRepositoryFinder = (HibernateRepositoryFinder) ctx
				.getBean("hibernateRepositoryFinder");
		System.out.println("hibernateRepositoryFinder: " + hibernateRepositoryFinder);

		MemoryRepositoryFinder memoryRepositoryFinder = (MemoryRepositoryFinder) ctx.getBean("memoryRepositoryFinder");
		System.out.println("memoryRepositoryFinder: " + memoryRepositoryFinder);

		// SEASSION FACTORY

		SessionFactoryImpl sessionFactory = (SessionFactoryImpl) ctx.getBean("sessionFactory");
		System.out.println("sessionFactory: " + sessionFactory);

		// REPOSITORIOS HIBERNATE

		HibernateItemTrackerRepository hibernateItemTrackerRepository = (HibernateItemTrackerRepository) ctx
				.getBean("hibernateItemTrackerRepository");
		System.out.println("hibernateItemTrackerRepository: " + hibernateItemTrackerRepository);

		HibernateUserRepository hibernateUserRepository = (HibernateUserRepository) ctx
				.getBean("hibernateUserRepository");
		System.out.println("hibernateUserRepository: " + hibernateUserRepository);

		// REPOSITORIOS MEMORIA

		MemoryItemTrackerRepository memoryItemTrackerRepository = (MemoryItemTrackerRepository) ctx
				.getBean("memoryItemTrackerRepository");
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
