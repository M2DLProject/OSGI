package m2dl.osgi.editor;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import m2dl.osgi.editor.serviceColorCss.ColoratorCssService;

public class ColoratorCssServiceTrackerCustomiser
		implements ServiceTrackerCustomizer<ColoratorCssService, ColoratorCssService> {

	private final BundleContext context;

	public ColoratorCssServiceTrackerCustomiser(BundleContext _context) {
		context = _context;
	}

	@Override
	public ColoratorCssService addingService(ServiceReference<ColoratorCssService> reference) {
		final ColoratorCssService service = context.getService(reference);

		System.out.println(
				"A new CssService appeared with the extention type = " + reference.getProperty("my.metadata.type"));

		return service;
	}

	@Override
	public void modifiedService(ServiceReference<ColoratorCssService> reference, ColoratorCssService service) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removedService(ServiceReference<ColoratorCssService> reference, ColoratorCssService service) {
		// TODO Auto-generated method stub

	}

}
