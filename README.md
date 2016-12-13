# webcrawler

This is a simple, recursive Java Web-Crawler for internal and external links which excludes facebook and twitter and images on a specific website or same domain, which creates a simple XML-file including the found pages and the returned status-code.
While it attempts to crawl through any website and find new links, it won't crawl a site multiple times or try to crawl a downloadable file.

## Download
* [**GitHub Releases**](https://github.com/sureshinuguru/WebCrawler/releases)

## Run

mvn clean dependency:copy-dependencies package


### GUI
Double-click the downloaded file or use the console:
```
java -jar WebCrawler-1.0.jar
```

### Console
```
java -jar WebCrawler-1.0.jar http://wiprodigital.com
```

## Example Output

### GUI
![GUI]

### Console
```
INTERNAL LINKS:
[1] [200] http://wiprodigital.com
[2] [200] http://wiprodigital.com/who-we-are
[3] [200] http://wiprodigital.com/what-we-do
[4] [200] http://wiprodigital.com/what-we-think
[5] [XXX] ...

EXTERNAL LINKS:

[200] https://designit.com/happening/news/create-the-future-together

[200] http://www.un.org/sustainabledevelopment/sustainable-development-goals/

INTERNAL / EXTERNAL IMAGES:

[200] http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/themes/wiprodigital/images/wdlogo.png

[200] http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/themes/wiprodigital/images/designit_logo.png

[200] http://17776-presscdn-0-6.pagely.netdna-cdn.com/wp-content/uploads/2016/05/designit-logo.jpeg

### XML-File


## License