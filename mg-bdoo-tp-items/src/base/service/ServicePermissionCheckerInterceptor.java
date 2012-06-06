package base.service;

import java.io.Serializable;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ServicePermissionCheckerInterceptor implements MethodInterceptor, Serializable {

	private static final long serialVersionUID = -1128191839982523642L;

	public ServicePermissionCheckerInterceptor() {

	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		return null;
	}

}
