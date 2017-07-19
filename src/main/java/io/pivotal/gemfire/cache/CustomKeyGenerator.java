package io.pivotal.gemfire.cache;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component(value="customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {

	private static final String PREFIX = "Pivotal_EmpId";

	@Override
	public String generate(Object target, Method method, Object... params) {


		StringBuilder sb = new StringBuilder();
		sb.append(PREFIX);
		sb.append("_");

		for (Object param : params) {
	          sb.append(param.toString());
		}
		return sb.toString();
	}

}
