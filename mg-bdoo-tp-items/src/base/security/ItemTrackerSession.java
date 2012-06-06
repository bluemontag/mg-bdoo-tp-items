package base.security;

import java.util.HashMap;

import user.dto.UserDTO;

public class ItemTrackerSession {

	private final HashMap<String, UserDTO> loggedUsersDTOs = new HashMap<String, UserDTO>();
	private static ItemTrackerSession instance = null;

	public static ItemTrackerSession getInstance() {
		if (instance == null) {
			instance = new ItemTrackerSession();
		}
		return instance;
	}

	private ItemTrackerSession() {

	}

	public HashMap<String, UserDTO> getLoggedUsersDTOs() {
		return this.loggedUsersDTOs;
	}

	public void removeUserDTOLogged(UserDTO userDTOToRemove) {
		this.loggedUsersDTOs.remove(userDTOToRemove);
	}

	public void addUserDTOLogged(String sessionToken, UserDTO userDTOToAdd) {
		this.loggedUsersDTOs.put(sessionToken, userDTOToAdd);
	}

	public boolean isValidToken(String sessionToken) {
		return this.loggedUsersDTOs.containsKey(sessionToken);
	}

	public UserDTO getUserDTOLogged(String sessionToken) {
		return this.loggedUsersDTOs.get(sessionToken);
	}
}
