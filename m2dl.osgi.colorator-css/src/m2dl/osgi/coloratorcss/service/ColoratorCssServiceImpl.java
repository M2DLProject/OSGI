package m2dl.osgi.coloratorcss.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import m2dl.osgi.editor.serviceColorCss.ColoratorCssService;

public class ColoratorCssServiceImpl implements ColoratorCssService {

	@Override
	public String colorCss(String parsedText) {
		System.out.println("la methode du css est appelé");

		Map<String, String> keywords = new HashMap<String, String>();
		keywords.put("margin", "green");
		keywords.put("padding", "blue");

		for (Entry<String, String> e : keywords.entrySet()) {
			parsedText = parsedText.replaceAll(e.getKey(), ":" + e.getValue() + "{~" + e.getKey() + "~}");
		}
		return parsedText;
	}

}
