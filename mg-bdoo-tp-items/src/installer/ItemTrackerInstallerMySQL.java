package installer;

import itemTracker.domain.ItemTracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import user.domain.User;

public class ItemTrackerInstallerMySQL {
	public static void main(String[] args) {
		Session session = null;

		try {
			// This step will read hibernate.cfg.xml and prepare hibernate for
			// use
			SessionFactory sessionFactory = new

			Configuration().configure().buildSessionFactory();
			session = sessionFactory.openSession();
			// Create new instance of Contact and set values in it by reading
			// them from form object
			System.out.println("Inserting Record");
			User aUser = new User("rodrigo", "rodrigo");
			ItemTracker itemTracker = new ItemTracker();
			session.getTransaction().begin();
			itemTracker.addUser(aUser);
			session.save(itemTracker);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			// Actual contact insertion will happen at this step
			session.flush();
			session.close();
			System.out.println("Done");
		}

	}
}