package owl.my.firend.bussiness.interceptor;

import java.io.Serializable;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import owl.my.firend.bussiness.annotation.security.HasPerm;

@HasPerm
@Interceptor
public class SecurityInterceptor implements Serializable {

	private static final long serialVersionUID = -1712004594431232086L;

	@AroundInvoke
	public Object logMethod(InvocationContext ic) throws Exception {
		final Method method = ic.getMethod();
		boolean isEnable = method.getAnnotation(HasPerm.class) != null ? method.getAnnotation(HasPerm.class).isEnable()
				: method.getDeclaringClass().getAnnotation(HasPerm.class).isEnable();

		System.out.println("isEnable => " + isEnable);

		if (isEnable) {
			System.out.println(isEnable);
		}

		return ic;
	}
}