package m2dl.osgi.coloratorjava.activator;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import m2dl.osgi.coloratorjava.service.ColoratorJavaService;
import m2dl.osgi.coloratorjava.serviceimpl.ColoratorJavaServiceImpl;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Lets go java!!");

		final ColoratorJavaService coloratorJavaService = new ColoratorJavaServiceImpl();

		final Hashtable<String, String> dictionnary = new Hashtable<>();
		dictionnary.put("my.metadata.type", "my.metadata.value");

		context.registerService(ColoratorJavaService.class.getName(), coloratorJavaService, dictionnary);
		System.out.println("My bundle is started and registered");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
	}

}