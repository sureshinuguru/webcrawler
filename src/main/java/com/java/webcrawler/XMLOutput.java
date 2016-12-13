package eu.menzerath.webcrawler;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Map;

public class XMLOutput {

    /**
     * Build a XML-file containing every crawled link
     * @param internalLinks every internal link
     * @param externalLinks every external link
     * @param images every internal and external image
     * @param outputFile file to write to
     */
    public static void createXmlOutput(Map<String, Integer> internalLinks, Map<String, Integer> externalLinks, Map<String, Integer> images, File outputFile) {
        // Create DocumentBuilder
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Unable to create DocumentBuilder: " + e.getMessage());
            return;
        }

        // new XML-file
        Document doc = docBuilder.newDocument();

        if (!internalLinks.isEmpty() || !externalLinks.isEmpty() || !images.isEmpty()) {
            // urlset (contains every url)
            Element urls = doc.createElement("urlset");
            doc.appendChild(urls);

            // internal urls
            if (!internalLinks.isEmpty()) {
                Element internalUrls = doc.createElement("internal");
                urls.appendChild(internalUrls);

                // add every internal url + its statuscode
                for (Map.Entry<String, Integer> entry : internalLinks.entrySet()) {
                    Element link = doc.createElement("link");
                    Element url = doc.createElement("url");
                    Element code = doc.createElement("code");

                    url.appendChild(doc.createTextNode(entry.getKey()));
                    code.appendChild(doc.createTextNode(String.valueOf(entry.getValue())));

                    link.appendChild(url);
                    link.appendChild(code);
                    internalUrls.appendChild(link);
                }
            }

            // external urls
            if (!externalLinks.isEmpty()) {
                Element externalUrls = doc.createElement("external");
                urls.appendChild(externalUrls);

                // add every external url + its statuscode
                for (Map.Entry<String, Integer> entry : externalLinks.entrySet()) {
                    Element link = doc.createElement("link");
                    Element url = doc.createElement("url");
                    Element code = doc.createElement("code");

                    url.appendChild(doc.createTextNode(entry.getKey()));
                    code.appendChild(doc.createTextNode(String.valueOf(entry.getValue())));

                    link.appendChild(url);
                    link.appendChild(code);
                    externalUrls.appendChild(link);
                }
            }

            // internal / external images
            if (!images.isEmpty()) {
                Element imageUrls = doc.createElement("images");
                urls.appendChild(imageUrls);

                // add every image + its statuscode
                for (Map.Entry<String, Integer> entry : images.entrySet()) {
                    Element link = doc.createElement("link");
                    Element url = doc.createElement("url");
                    Element code = doc.createElement("code");

                    url.appendChild(doc.createTextNode(entry.getKey()));
                    code.appendChild(doc.createTextNode(String.valueOf(entry.getValue())));

                    link.appendChild(url);
                    link.appendChild(code);
                    imageUrls.appendChild(link);
                }
            }
        }

        // write content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            System.out.println("Unable to create Transformer: " + e.getMessage());
            return;
        }
        // use indentation
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(outputFile);

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            System.out.println("Unable to transform: " + e.getMessage());
        }
    }
}
