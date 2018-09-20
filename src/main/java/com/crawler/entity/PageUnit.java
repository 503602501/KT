package com.crawler.entity;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crawler.util.DriverUtil;
import com.crawler.util.StringUtils;



public class PageUnit  extends UnitAdapter{
	

	private Integer scroll ; 
	private String script ; 
	private String spare ; 
	private boolean focus  ;  //全局
	private static final String TYPE = "page";
	private static final Logger logger =  Logger.getLogger(PageUnit.class);
	
	public PageUnit() {
		super(TYPE);
	}
	
	@Override
	protected void handler(WebDriver webDriver, final String xpath,Storage storage) throws Exception {

		if(storage.getEnv().isStop()){
			return ;
		}
		
		//设置当前的分页数量
		if(this.getCount()!=null && storage.getPageCount()==null){
			storage.setPageCount(this.getCount());
		}
		
		//分页到了最后一页，退出
		if( this.getCount()!=null && storage.getPageCount()==0){  
			return ; 
		}
		
		if(super.getWait()!=null){
			Thread.sleep(super.getWait());
		}
		if(this.getScroll()!=null){
			DriverUtil.scrollScreen(webDriver, this.getScroll(),storage.getEnv());
		}
		
		if(this.getScript()!=null){
			((JavascriptExecutor)webDriver).executeScript(this.getScript());
		}

		for (Unit  unit: getChildUnit()) {
			UnitAdapter adapter = (UnitAdapter) unit;
			adapter.adapterHandler(webDriver,unit.getXpath(), storage);
		}
		

		//运行次数减一
		if(this.getCount()!=null){
			storage.setPageCount(storage.getPageCount()-1);
		} 
		
		long start = System.currentTimeMillis() ;
	    WebElement webElement = findPageElement(webDriver, xpath, start);
	    
	    if(webElement==null){
	    	return ;
	    }
	        
		/*try {
			webElement =DriverUtil.parseXpath(webDriver, super.getXpath());
		} catch (Exception e) {
			
			logger.info("分页到底部或者分页异常"+e.getMessage());
//			return ;
		}*/
		
	/*	if(webElement!=null){ //滑动到下一页
			Point point = webElement.getLocation() ;
			Integer height = point.getY()-200;
			((JavascriptExecutor) webDriver).executeScript("window.scrollTo(0,"+height+")"); 
		}*/

	  //分页到了最后一页，退出
  		if( this.getCount()!=null && storage.getPageCount()==0){  
  			return ; 
  		}
	  		
	    try {
			 
	    	if(isFocus()){ //聚焦到元素上
				 String js = String.format("window.scroll(0, %s)", webElement.getLocation().getY()-100);  
				 ((JavascriptExecutor)webDriver).executeScript(js);  
				 
				Thread.sleep(100);
				
				Actions actions = new Actions(webDriver);
				actions.moveToElement(webElement).build().perform();
				Thread.sleep(50);
			}
	    	
	    	
	    	webElement.click();  //点击成下一页
		} catch (Exception e) {
			logger.error("点击下一页异常："+e.getMessage());
		}
	    
		Thread.sleep(500);
		
	    this.handler(webDriver,super.getXpath(), storage);  //递归算法
	    
	}

	private WebElement findPageElement(WebDriver webDriver, final String xpath,long start) {
		WebDriverWait wait = new WebDriverWait(webDriver, 2); // 最多等2秒
	    WebElement webElement =  null;
	    try {
	    	   webElement = wait.until(new ExpectedCondition<WebElement>() {
	    		@Override
	    		public WebElement apply(WebDriver d) {
	    			return d.findElement(By.xpath(xpath));
	    		}
	    	});
			
		}catch(NoSuchElementException e ){
			logger.error("分页到了底部，不存在下一页"+(System.currentTimeMillis()-start)/1000 );
			if(StringUtils.isEmpty(this.getSpare())){
				return null;
			}
			
			try {
				webElement = webDriver.findElement(By.xpath(this.getSpare()));
			} catch (Exception e2) {
				logger.error("备用分页"+e.getMessage());
			}
			
			return webElement;
			
		}catch (TimeoutException te) {
			logger.error("分页到了底部，不存在下一页"+(System.currentTimeMillis()-start)/1000 );
			if(StringUtils.isEmpty(this.getSpare())){
				return null;
			}
			
			try {
				webElement = webDriver.findElement(By.xpath(this.getSpare()));
			} catch (Exception e2) {
				logger.error("备用分页"+e2.getMessage());
			}
			
			return webElement;
		}
	    
		return webElement;
	}

	public Integer getScroll() {
		return scroll;
	}

	public void setScroll(Integer scroll) {
		this.scroll = scroll;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public boolean isFocus() {
		return focus;
	}

	public void setFocus(boolean focus) {
		this.focus = focus;
	}

	public String getSpare() {
		return spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}
}
