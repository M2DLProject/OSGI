package m2dl.osgi.coloratorjava.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import m2dl.osgi.editor.serviceColorJava.ColoratorJavaService;

public class ColoratorJavaServiceImpl implements ColoratorJavaService {

	@Override
	public String colorer(String parsedText) {
		System.out.println("la methode du java est appelé");

		Map<String, String> keywords = new HashMap<String, String>();
		keywords.put("class", "green");
		keywords.put("public", "blue");

		for (Entry<String, String> e : keywords.entrySet()) {
			parsedText = parsedText.replaceAll(e.getKey(), ":" + e.getValue() + "{~" + e.getKey() + "~}");
		}

		return parsedText;
	}

}
