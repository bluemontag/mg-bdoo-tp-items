package base.security;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import base.exception.UserNotLoggedException;

public class ServiceSecurityPreInterceptor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		ItemTrackerSession itemTrackerSesion = ItemTrackerSession.getInstance();
		Object[] arguments = invocation.getArguments();
		String sessionToken = (String) arguments[0];
		if (itemTrackerSesion.isValidToken(sessionToken)) {
			// try {
			Object ret = invocation.proceed();
			return ret;
			// } catch (Throwable anyException) {
			//
			// if
			// (anyException.getClass().equals(HibernateOptimisticLockingFailureException.class))
			// {
			// throw new
			// StoreConcurrencyException("La entidad que desea guardar fue modificada por otro usuario.");
			// } else {
			// throw anyException;
			//
			// }
			// }
		} else {
			throw new UserNotLoggedException("El usuario no tiene acceso al sistema.");
		}
	}
}
