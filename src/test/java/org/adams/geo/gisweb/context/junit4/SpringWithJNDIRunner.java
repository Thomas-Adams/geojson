package org.adams.geo.gisweb.context.junit4;

import javax.naming.NamingException;

import org.junit.runners.model.InitializationError;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

public class SpringWithJNDIRunner extends SpringJUnit4ClassRunner {

	public static boolean isJNDIactive;

	private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test-datasource.xml");

	/**
	 * JNDI is activated with this constructor.
	 *
	 * @param klass
	 * @throws InitializationError
	 * @throws NamingException
	 * @throws IllegalStateException
	 */
	public SpringWithJNDIRunner(final Class<?> klass)
			throws InitializationError, IllegalStateException, NamingException {
		super(klass);
		synchronized (SpringWithJNDIRunner.class) {
			if (!SpringWithJNDIRunner.isJNDIactive) {
				SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
				builder.bind("java:comp/env/jdbc/geoweb", applicationContext.getBean("dataSource"));
				builder.bind("java:comp/jdbc/geoweb", applicationContext.getBean("dataSource"));
				builder.bind("java:jdbc/geoweb", applicationContext.getBean("dataSource"));
				builder.bind("jdbc/geoweb", applicationContext.getBean("dataSource"));
				builder.activate();
				SpringWithJNDIRunner.isJNDIactive = true;
			}
		}
	}
}
