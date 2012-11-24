package base.test;

/**
 * @author Rodrigo Itursarry (itursarry@gmail.com)
 */
public class TestConstants {

	public static final String ADMIN_USER_NAME = "rodrigo";
	public static final String ADMIN_PASSWORD = "rodrigo";
	public final static String PROJECT_NAME = "A project name";
	public final static String USER_NAME = "new_User";
	public static final String USER_PASSWORD = "Password";

	public final static int MASSIVE_AMOUNT = 500;

	public static final String BASE_USERS_NAME_TO_SET_IN_COLLECTION = "user_name_for_collection_";
	public static final String UPDATED_PROJECT_NAME = "A project name UPDATED";

	public static final String TEAM_NAME = "Nuevo equipo creado";
	public static final int AMOUNT_OF_USERS_TO_SET = 5;

	public static final String TEAM_ITEM_TYPE = "Equipo Ignacio";
	public static final String WORKFLOW_NAME = "WF Ignacio";

	public static final String ITEM_TYPE_NAME = "Tipo Basico";
	public static final String ITEM_DESCRIPTION = "Descripcion de un item.";
	public static final Integer PRIORITY = 1;

	// States
	public static final String IN_DEVELOPMENT_STATE = "En desarrollo";
	public static final String FINAL_STATE = "Finalizado";
	public static final String CREATED_STATE = "Creado";
	public static final String IN_VALIDATION_STATE = "EnValidacion";
	public static final String FIXED_STATE = "Arreglado";
	public static final String PENDING_STATE = "Pendiente";

	// Transitions
	public static final String TRANSITION_IN_DEVELOPMENT = "PonerEnDesarrollo";
	public static final String TRANSITION_IN_VALIDATION = "PonerEnValidacion";
	public static final String TRANSITION_REJECT = "RechazarValidacion";
	public static final String TRANSITION_ACCEPT = "AcceptarValidacion";

}
