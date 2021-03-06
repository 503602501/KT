package com.crawler.handler.open.impl;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.crawler.entity.DriverEntity;
import com.crawler.entity.OpenUnit;
import com.crawler.entity.Product;
import com.crawler.entity.Storage;
import com.crawler.handler.open.IOpen;

public class TaobaoListSearchUrl implements IOpen {

	private static final Logger logger =  Logger.getLogger(TaobaoListSearchUrl.class);
	
	@Override
	public void init(OpenUnit openUnit, Storage storage,DriverEntity driverEntity) throws Exception {
		Iterator<Product> iterator = storage.getInputUrlQueues().iterator();
		while ( iterator.hasNext()) {
			Product p = iterator.next();
			p.setUrl("https://s.taobao.com/search?q="+p.getUrl());
//			p.setUrl("https://m.newegg.com/products/"+p.getUrl());
		}
		
		
	}

}
