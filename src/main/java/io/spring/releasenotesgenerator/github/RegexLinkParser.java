package io.spring.releasenotesgenerator.github;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

/**
 * A {@code LinkParser} that uses a regular expression to parse the header.
 *
 * @author Madhura Bhave
 */
public class RegexLinkParser implements LinkParser {

	private static final Pattern LINK_PATTERN = Pattern.compile("<(.+)>; rel=\"(.+)\"");

	@Override
	public Map<String, String> parse(String input) {
		Map<String, String> links = new HashMap<>();
		for (String link : StringUtils.commaDelimitedListToStringArray(input)) {
			Matcher matcher = LINK_PATTERN.matcher(link.trim());
			if (matcher.matches()) {
				links.put(matcher.group(2), matcher.group(1));
			}
		}
		return links;
	}

}
