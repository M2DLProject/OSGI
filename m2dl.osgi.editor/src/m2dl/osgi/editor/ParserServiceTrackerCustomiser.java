package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.editor.service.ParserService;

public class ParserServiceTrackerCustomiser implements ServiceTrackerCustomizer<ParserService, ParserService> {

	private final BundleContext context;

	public ParserServiceTrackerCustomiser(BundleContext _context) {
		context = _context;
	}

	@Override
	public ParserService addingService(ServiceReference<ParserService> serviceReference) {

		final ParserService service = context.getService(serviceReference);

		System.out.println("A new \"MyService\" appeared with the extention type = "
				+ serviceReference.getProperty("my.metadata.type"));

		return service;
	}

	@Override
	public void modifiedService(ServiceReference<ParserService> arg0, ParserService arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<ParserService> arg0, ParserService arg1) {
		// TODO Auto-generated method stub

	}

}
