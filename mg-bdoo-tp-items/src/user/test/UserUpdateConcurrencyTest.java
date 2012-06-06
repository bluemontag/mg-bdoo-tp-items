package user.test;

import org.springframework.orm.hibernate4.HibernateOptimisticLockingFailureException;

import user.dto.UserDTO;
import user.exception.UnknownUserException;
import user.service.UserServiceBI;
import base.exception.DTOConcurrencyException;

public class UserUpdateConcurrencyTest extends Thread {

	protected String testName;
	protected UserServiceBI userService;
	protected UserDTO anUserDTOToUpdate;
	protected boolean concurrencyError = false;
	protected boolean threadFinished = false;
	protected String sessionToken;

	public UserUpdateConcurrencyTest(String sessionToken, UserServiceBI userService, UserDTO anUserDTOToUpdate,
			String testName) {
		this.userService = userService;
		this.anUserDTOToUpdate = anUserDTOToUpdate;
		this.testName = testName;
		this.sessionToken = sessionToken;
	}

	@Override
	public void run() {
		try {
			userService.updateUser(this.sessionToken, anUserDTOToUpdate);
		} catch (UnknownUserException e) {
			System.out.println(this.testName + ": el usuario '" + this.anUserDTOToUpdate.getUserName()
					+ "' no existia!!!.");
		} catch (DTOConcurrencyException e) {
			System.out.println(this.testName + ": '" + this.anUserDTOToUpdate.getUserName()
					+ "' tuvo error de concurrencia pero de DTO.");
		} catch (HibernateOptimisticLockingFailureException hibernateLokingException) {
			System.out.println(this.testName + ": '" + this.anUserDTOToUpdate.getUserName()
					+ "' tuvo error de concurrencia de Hibernate!!.");
			this.concurrencyError = true;
		}
		this.threadFinished = true;
	}

	public boolean isConcurrencyError() {
		return concurrencyError;
	}

	public void setConcurrencyError(boolean concurrencyError) {
		this.concurrencyError = concurrencyError;
	}

	public boolean isThreadFinished() {
		return threadFinished;
	}

	public void setThreadFinished(boolean threadFinished) {
		this.threadFinished = threadFinished;
	}
}
