package m2dl.osgi.editor;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.util.tracker.ServiceTracker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import m2dl.osgi.editor.serviceColorCss.ColoratorCssService;
import m2dl.osgi.editor.serviceColorJava.ColoratorJavaService;
import m2dl.osgi.editor.serviceParser.ParserService;

public class CodeViewerController {

	// private ColoratorJavaService coloratorJavaService;

	private Bundle bundleParser;
	private Bundle bundleColorator;
	private Bundle bundleCssColorator;
	private BundleContext bundleContext;
	private String contentFile;
	private String option = "<style>font{font-weight: bold;}</style>";

	private ServiceTracker<ParserService, ParserService> serviceTracker;

	private ServiceTracker<ColoratorJavaService, ColoratorJavaService> coloratorJavaService;
	private ServiceTracker<ColoratorCssService, ColoratorCssService> coloratorCssService;

	public BundleContext getBundleContext() {
		return bundleContext;
	}

	public ServiceTracker<ColoratorCssService, ColoratorCssService> getColoratorCssService() {
		return coloratorCssService;
	}

	public void setColoratorCssService(ServiceTracker<ColoratorCssService, ColoratorCssService> coloratorCssService) {
		this.coloratorCssService = coloratorCssService;
	}

	public void setBundleContext(BundleContext bundleContext) {
		this.bundleContext = bundleContext;
	}

	/**
	 * The main window of the application.
	 */
	private Stage primaryStage;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	/**
	 * Radio button: indicate if the html bundle is started.
	 */
	@FXML
	private RadioMenuItem radioMenuJava;

	/**
	 * Radio button: indicate if the decorator bundle is started.
	 */
	@FXML
	private RadioMenuItem radioMenuDecorator;

	/**
	 * The viewer to display the content of the opened file.
	 */
	@FXML
	private WebView webViewer;

	/**
	 * The radio button: indicate if the css bundle is started.
	 */
	@FXML
	private RadioMenuItem radioMenuCSS;

	/**
	 * The button "À propos" have been clicked.
	 *
	 * @param event
	 */
	@FXML
	void fireMenuAPropos(ActionEvent event) {
		final Stage dialog = new Stage();
		dialog.initModality(Modality.APPLICATION_MODAL);
		dialog.initOwner(primaryStage);
		final VBox dialogVbox = new VBox(50);
		dialogVbox.setAlignment(Pos.CENTER);
		dialogVbox.getChildren().add(new Text("implemented by Steve MAGRAS and Marc GAYOUT"));
		final Scene dialogScene = new Scene(dialogVbox, 300, 80);
		dialog.setScene(dialogScene);
		dialog.show();
	}

	@FXML
	void fireMenuCloseFile(ActionEvent event) {
		webViewer.getEngine().load("");
	}

	@FXML
	void fireMenuExit(ActionEvent event) {
		primaryStage.close();
		System.exit(0);
	}

	/**
	 * The button to load a bundle have been clicked.
	 *
	 * @param event
	 */
	@FXML
	void fireMenuLoadBundle(ActionEvent event) {
		final FileChooser fileChooser = new FileChooser();
		final File selectedFile = fileChooser.showOpenDialog(primaryStage);

		/*
		 * TODO complete this section to load the selected bundle.
		 */
		if (selectedFile != null) {
			Activator.logger.info("File selected: " + selectedFile.getName());

			if (bundleContext != null) {
				try {
					if (selectedFile.getName().contains("parser")) {
						bundleParser = bundleContext.installBundle(selectedFile.toURI().toString());
						Activator.logger.info("Bundle parser is loaded");
					} else if (selectedFile.getName().contains("java")) {
						bundleColorator = bundleContext.installBundle(selectedFile.toURI().toString());
						Activator.logger.info("Bundle Java is loaded");
					} else if (selectedFile.getName().contains("css")) {
						bundleCssColorator = bundleContext.installBundle(selectedFile.toURI().toString());
						Activator.logger.info("Bundle css is started");

					}

				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} else {
			Activator.logger.info("File selection cancelled.");
		}
	}

	/**
	 * The button to open a file have been clicked.
	 *
	 * @param event
	 * @throws FileNotFoundException
	 */
	@FXML
	void fireMenuOpenFile(ActionEvent event) throws FileNotFoundException {
		final FileChooser fileChooser = new FileChooser();
		final File selectedFile = fileChooser.showOpenDialog(primaryStage);

		/*
		 * TODO complete this section to display the content of the file into
		 * the webViewer.
		 */
		if (selectedFile != null) {

			Scanner s = new Scanner(selectedFile).useDelimiter("\\Z");

			contentFile = "";
			while (s.hasNext()) {
				contentFile += s.next() + "<br />";
			}

			update();

			Activator.logger.info("File selected: " + selectedFile.getName());
		} else {
			Activator.logger.info("File selection cancelled.");
		}
	}

	@FXML
	void fireRadioMenuCSS(ActionEvent event) {
		/*
		 * If the css bundle is stated -> stop it otherwise start it (if it has
		 * been loaded before)
		 */
		if (bundleCssColorator != null) {

			if (bundleCssColorator.getState() == Bundle.STARTING || bundleCssColorator.getState() == Bundle.ACTIVE) {
				try {
					bundleCssColorator.stop();
					update();
				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					bundleCssColorator.start();

					update();

				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Activator.logger.info("Bundle CSS started or stopped");
			}
		}

	}

	@FXML
	void fireRadioMenuDecorator(ActionEvent event) {
		/*
		 * If the decorator bundle is stated -> stop it otherwise start it (if
		 * it has been loaded before)
		 */

		if (bundleParser != null) {

			if (bundleParser.getState() == Bundle.STARTING || bundleParser.getState() == Bundle.ACTIVE) {
				try {
					bundleParser.stop();
					update();
				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					bundleParser.start();

					update();

				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Activator.logger.info("Decorator started or stopped");
			}
		}

	}

	@FXML
	void fireRadioMenuJava(ActionEvent event) {
		/*
		 * If the Java bundle is stated -> stop it otherwise start it (if it has
		 * been loaded before)
		 */

		if (bundleColorator != null) {

			if (bundleColorator.getState() == Bundle.STARTING || bundleColorator.getState() == Bundle.ACTIVE) {
				try {
					bundleColorator.stop();
					update();
				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					bundleColorator.start();

					update();

				} catch (BundleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Activator.logger.info("Bundle java started or stopped");
			}
		}

	}

	@FXML
	void initialize() {
		assert radioMenuJava != null : "fx:id=\"radioMenuJava\" was not injected: check your FXML file 'main-window-exercice.fxml'.";
		assert radioMenuDecorator != null : "fx:id=\"radioMenuDecorator\" was not injected: check your FXML file 'main-window-exercice.fxml'.";
		assert webViewer != null : "fx:id=\"webViewer\" was not injected: check your FXML file 'main-window-exercice.fxml'.";
		assert radioMenuCSS != null : "fx:id=\"radioMenuCSS\" was not injected: check your FXML file 'main-window-exercice.fxml'.";

	}

	void update() {
		String result = contentFile;
		Activator.logger.info("On est dans le update");
		if (this.getColoratorCssService().getService() != null) {
			Activator.logger.info("On va appeler le service css");
			result = this.getColoratorCssService().getService().colorCss(result);
		}
		if (this.getColoratorJavaService().getService() != null) {
			result = this.getColoratorJavaService().getService().colorer(result);
		}
		if (this.getServiceTracker().getService() != null) {
			result = this.getServiceTracker().getService().parser(result);
		}

		webViewer.getEngine().loadContent(option + result);
	}

	public void setPrimaryStage(final Stage _stage) {
		primaryStage = _stage;
	}

	public ServiceTracker<ParserService, ParserService> getServiceTracker() {
		return serviceTracker;
	}

	public void setServiceTracker(ServiceTracker<ParserService, ParserService> serviceTracker) {
		this.serviceTracker = serviceTracker;
	}

	public String getContentFile() {
		return contentFile;
	}

	public void setContentFile(String contentFile) {
		this.contentFile = contentFile;
	}

	public ServiceTracker<ColoratorJavaService, ColoratorJavaService> getColoratorJavaService() {
		return coloratorJavaService;
	}

	public void setColoratorJavaService(
			ServiceTracker<ColoratorJavaService, ColoratorJavaService> coloratorJavaService) {
		this.coloratorJavaService = coloratorJavaService;
	}

}
