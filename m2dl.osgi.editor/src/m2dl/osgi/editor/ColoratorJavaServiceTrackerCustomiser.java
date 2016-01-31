package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.editor.serviceColorJava.ColoratorJavaService;

public class ColoratorJavaServiceTrackerCustomiser
		implements ServiceTrackerCustomizer<ColoratorJavaService, ColoratorJavaService> {

	private final BundleContext context;

	public ColoratorJavaServiceTrackerCustomiser(BundleContext _context) {
		context = _context;
	}

	@Override
	public ColoratorJavaService addingService(ServiceReference<ColoratorJavaService> serviceReference) {

		final ColoratorJavaService service = context.getService(serviceReference);

		System.out.println("A new ColoratorService appeared with the extention type = "
				+ serviceReference.getProperty("my.metadata.type"));

		return service;
	}

	@Override
	public void modifiedService(ServiceReference<ColoratorJavaService> arg0, ColoratorJavaService arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<ColoratorJavaService> arg0, ColoratorJavaService arg1) {
		// TODO Auto-generated method stub

	}

}
