package com.allendowney.thinkdast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiPhilosophy {

    final static List<String> visited = new ArrayList<String>();
    final static WikiFetcher wf = new WikiFetcher();

    /**
     * Tests a conjecture about Wikipedia and Philosophy.
     *
     * https://en.wikipedia.org/wiki/Wikipedia:Getting_to_Philosophy
     *
     * 1. Clicking on the first non-parenthesized, non-italicized link
     * 2. Ignoring external links, links to the current page, or red links
     * 3. Stopping when reaching "Philosophy", a page with no links or a page
     *    that does not exist, or when a loop occurs
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        String destination = "https://en.wikipedia.org/wiki/Philosophy";
        String source = "https://en.wikipedia.org/wiki/Java_(programming_language)";

        testConjecture(destination, source, 10);
    }

    /**
     * Starts from given URL and follows first link until it finds the destination or exceeds the limit.
     *
     * @param destination
     * @param source
     * @throws IOException
     * 내 코드와 정답 코드 모두 Computer language 링크를 따라 가게 되고 유효한 다음 링크를 발견하지 못한다.
     */
    public static void testConjecture(String destination, String source, int limit) throws IOException {
    	String url = source;
    	for(int i = 0; i < limit; i++) {
    		if(visited.contains(url)) {
    			System.err.println("We're in a loop, existing.");
    			return;
    		} else {
    			visited.add(url);
    		}
    		Element elt = getFirstValidLink(url);
    		if(elt == null) {
    			System.err.println("Got to a page with no valid links.");
    			return;
    		}
    		
    		System.out.println("**" + elt.text() + "**");
    		url = elt.attr("abs:href");
    		
    		if(url.equals(destination)) {
    			System.out.println("Eureka!");
    			break;
    		}
    	}
        // TODO: FILL THIS IN!
//    	int count = 0;
//    	String url = source;
//    	System.out.println("WikiPhilosophy:");
//    	
//    	loop:
//    	while(count < limit) {
//    		System.out.println("\t[java] Fetching " + url);
//    		Elements paragraphs = wf.fetchWikipedia(url);
//    		for(Element paragraph : paragraphs) {
//    			List<Element> elements = paragraph.children();
//    			if(elements.size() > 0) {
//    				for(Element element : elements) {
//    					String converted = element.toString();
//    					if(!converted.contains("<i>") && !converted.contains("<em>") && converted.contains("<a href")) {
//    						String des = extractDestination(element);
////    						System.out.println(des);
////    						if(des.contains(" "))
////    							continue;
//    						if(!visited.contains(des)) {
//    							visited.add(des);
//    							System.out.println("\t[java] **" + des + "**");
//    							url = "https://en.wikipedia.org/wiki/" + des;
//    							count++;
//    							continue loop;
//    						}
//    					}
//    				}
//    				System.out.println();
//    			}
//    		}
//    		count++;
//    	}
    }
    
    public static Element getFirstValidLink(String url) throws IOException {
		print("Fetching %s...", url);
		Elements paragraphs = wf.fetchWikipedia(url);
		WikiParser wp = new WikiParser(paragraphs);
		Element elt = wp.findFirstLink();
		return elt;
	}
    
    private static void print(String msg, Object... args) {
		System.out.println(String.format(msg, args));
	}
    
//    private static String extractDestination(Node nodes) {
//    	String converted = nodes.toString();
//    	if(converted.startsWith("<a href")) {
//    		return makeNewUrl(converted);
//    	}
//    	String result = "";
//    	for(Node node : nodes.childNodes()) 
//    		result = extractDestination(node);
//    	return result;
//    }
//    
//    private static String makeNewUrl(String str) {
////    	System.out.println(str);
//    	int start = str.indexOf("title");
//    	if(start == -1)
//    		return " ";
//    	StringBuilder sb = new StringBuilder();
//    	for(int i = start + 7; str.charAt(i) != '\"'; i++) {
//    		sb.append(str.charAt(i));
//    	}
//    	return sb.toString();
//    }
 
}
