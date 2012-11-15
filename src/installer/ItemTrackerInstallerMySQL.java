package installer;

import installer.exception.InstallingErrorException;
import itemTracker.domain.ItemTracker;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import user.domain.User;
import user.exception.UnknownUserException;
import base.contant.BaseConstants;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class ItemTrackerInstallerMySQL {
	public static void main(String[] args) throws InstallingErrorException {
		System.out.println("Begin: Instalando ItemTracker");
		try {
			cargarContexto();
			// installItemTracker();
			// setAdminUser();
			// } catch (UnknownUserException e) {
			// throw new
			// InstallingErrorException("No se pudo crear el usuario administrador. Se cancela la instalacion");
		} catch (Exception e) {
			throw new InstallingErrorException("Error desconocido. Se cancela la instalacion");
		}
		System.out.println("End: Instalando ItemTracker");

	}

	private static void setAdminUser() throws UnknownUserException {

		// UserServiceBI userService =
		// ServiceContainer.getInstance().getUserService();
		// UserDTO theFirstUser =
		// userService.getUserByUserName(BaseContants.DEFAULT_ADMIN_USER_NAME);
		// userService.setUserAsAdmin(theFirstUser);
	}

	protected static void installItemTracker() {

		SessionFactory sessionFactory = configureSessionFactory();

		Session session = sessionFactory.openSession();
		ItemTracker itemTracker = new ItemTracker();
		User theAdminUser = new User(BaseConstants.DEFAULT_ADMIN_USER_NAME, BaseConstants.DEFAULT_ADMIN_PASSWORD);
		itemTracker.addUser(theAdminUser);

		session.getTransaction().begin();
		session.persist(itemTracker);
		session.getTransaction().commit();

		session.flush();
		session.close();
	}

	private static void cargarContexto() {
		String[] contextPaths = new String[] { BaseConstants.CONTEXT_FILE };
		new ClassPathXmlApplicationContext(contextPaths);
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

}