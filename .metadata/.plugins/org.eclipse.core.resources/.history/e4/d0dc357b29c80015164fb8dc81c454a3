package m2dl.osgi.parser.service;

import java.util.ArrayList;
import java.util.List;

import m2dl.osgi.editor.service.ParserService;

public class ParserServiceImpl implements ParserService {

	@Override
	public String parser(String content) {

		System.out.println("la methode du parser est appelé");
		List<String> keywords = new ArrayList<String>();
		keywords.add("green");
		keywords.add("blue");
		keywords.add("red");

		for (String s : keywords) {
			content = content.replaceAll(":" + s + "\\{~(.*)~\\}", "<font color=\"" + s + "\">$1</font>");
		}

		return content;
	}

}
