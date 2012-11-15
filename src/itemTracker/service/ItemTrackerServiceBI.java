package itemTracker.service;

import user.exception.IncorrectPasswordException;
import user.exception.UnknownUserException;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public interface ItemTrackerServiceBI {

	String loginUser(String anUserName, String aPassword) throws UnknownUserException, IncorrectPasswordException;

}
