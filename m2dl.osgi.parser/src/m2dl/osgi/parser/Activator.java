package m2dl.osgi.parser;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import m2dl.osgi.editor.serviceParser.ParserService;
import m2dl.osgi.parser.service.ParserServiceImpl;

public class Activator implements BundleActivator {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.
	 * BundleContext)
	 */
	public void start(BundleContext context) throws Exception {

		System.out.println("Parser start - 1");

		final ParserService parserService = new ParserServiceImpl();

		final Hashtable<String, String> dictionnary = new Hashtable<>();
		dictionnary.put("my.metadata.type", "my.metadata.value");

		context.registerService(ParserService.class.getName(), parserService, dictionnary);

		System.out.println("Parser start - 2");

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
