package com.proto.shankara.data;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.log4j.Logger;

import com.proto.shankara.pojo.SFeed;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class FeedReader {
	private Logger readLog = Logger.getLogger(FeedReader.class);
	public SFeed readFeed(String urlString){
		SFeed sfeed = new SFeed();
		URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XmlReader reader = null;

		try {

			try {
				reader = new XmlReader(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SyndFeed feed = null;
			try {
				feed = new SyndFeedInput().build(reader);
			} catch (IllegalArgumentException | FeedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Feed Author: "+ feed.getAuthor());
			System.out.println(feed.getEntries().size());
			List<SyndEntry> entries = feed.getEntries();
			SyndEntry entry = entries.get(0);
			System.out.println(entry.getDescription().getValue());
			
			sfeed.setAuthor(feed.getAuthor());
			sfeed.setContent(entry.getDescription().getValue());
			sfeed.setTitle(entry.getTitle());
			sfeed.setTotalEntries(feed.getEntries().size());
			readLog.debug("************************");
			readLog.debug("SFEED"+sfeed);
			readLog.debug("************************");
		} finally {
			if (reader != null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return sfeed;
	}

}
