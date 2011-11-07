package base.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import base.test.Contact;

public class Test {
	public static void main(String[] args) {
  Session session = null;

  try{
  // This step will read hibernate.cfg.xml and prepare hibernate for use
  SessionFactory sessionFactory = new 

Configuration().configure().buildSessionFactory();
 session =sessionFactory.openSession();
  //Create new instance of Contact and set values in it by reading them from form object
 System.out.println("Inserting Record");
  Contact contact = new Contact();
  contact.setId(3);
  contact.setFirstName("Deepak");
  contact.setLastName("Kumar");
  contact.setEmail("deepak_38@yahoo.com");
  session.save(contact);
  System.out.println("Done");
  }catch(Exception e){
  System.out.println(e.getMessage());
  }finally{
  // Actual contact insertion will happen at this step
  session.flush();
  session.close();

  }
  
  }
}