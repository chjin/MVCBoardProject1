package com.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DispatcherServlet extends HttpServlet{

	private Map classMap=new HashMap();

	@Override
	public void init(ServletConfig config) throws ServletException {
		try{
			String path=config.getInitParameter("contextConfigLocation");
			System.out.println(path);
			
			DocumentBuilderFactory documentBuilderFactory=
					DocumentBuilderFactory.newInstance();
			
			DocumentBuilder documentBuilder=documentBuilderFactory.newDocumentBuilder();
			
			//ÆÄ¼­±â
			Document document=documentBuilder.parse(new File(path));
			Element elementRoot=document.getDocumentElement();
			NodeList nodeList=elementRoot.getElementsByTagName("bean");
			
			for(int i=0;i<nodeList.getLength();i++){
				Element element=(Element)nodeList.item(i);
				String id=element.getAttribute("id");
				String clazz=element.getAttribute("class");
				
				Class className=Class.forName(clazz);
				Object object=className.newInstance();
				System.out.println(object);
				
				classMap.put(id, object);
			}
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}

	
}
































