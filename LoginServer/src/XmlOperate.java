import java.io.IOException; 
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory; 
import javax.xml.parsers.ParserConfigurationException; 
import javax.xml.transform.Transformer; 
import javax.xml.transform.TransformerConfigurationException; 
import javax.xml.transform.TransformerException; 
import javax.xml.transform.TransformerFactory; 
import javax.xml.transform.dom.DOMSource; 
import javax.xml.transform.stream.StreamResult; 
import org.w3c.dom.Document; 
import org.w3c.dom.Element; 
import org.w3c.dom.NodeList; 
import org.w3c.dom.Text; 
import org.xml.sax.SAXException; 
public class XmlOperate { 
Document doc; 
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); 
DocumentBuilder builder; 
NodeList users; 
String path; 
public NodeList getusers() { 
return users; 
} 
public void setUser(NodeList user) { 
this.users = user; 
} 
/** 
* 构造方法 
* @param path:xml文件的路径 
* @param nodes：要解析的xml节点名称 
*/ 
public XmlOperate(String path) { 
super(); 
this.path = path; 
System.out.println(System.getProperty("user.dir")); 
} 
/** 
* 解析XML 
* @param path 
*/ 
@SuppressWarnings("unused")
public boolean check(String id,String pwd){ 
try { 
builder = factory.newDocumentBuilder(); 
String uid=new String();
String p=new String();
Document doc=builder.parse(path); 
doc.normalize(); 
NodeList users =doc.getElementsByTagName("user"); 
this.setUser(users); 
for (int i=0;i<users.getLength();i++){ 
Element link=(Element) users.item(i); 
uid=link.getElementsByTagName("id").item(0).getFirstChild().getNodeValue(); 
p=link.getElementsByTagName("pwd").item(0).getFirstChild().getNodeValue();
if(uid.equals(id)&&p.equals(pwd))
return true;

} 
}catch (ParserConfigurationException e) { 
e.printStackTrace(); 
} catch (SAXException e) { 
e.printStackTrace(); 
} catch (IOException e) { 
e.printStackTrace(); 
}
return false;
} 
/** 
* addCode 
* @param path 
*/ 
public void addXmlCode(String id,String pwd){ 
try { 
builder = factory.newDocumentBuilder(); 
Document doc=builder.parse(path); 
doc.normalize(); 
Text textseg; 
Element user=doc.createElement("user"); 
Element linkuser=doc.createElement("id"); 
textseg=doc.createTextNode(id); 
linkuser.appendChild(textseg); 
user.appendChild(linkuser); 
Element linktitle=doc.createElement("pwd"); 
textseg=doc.createTextNode(pwd); 
linktitle.appendChild(textseg); 
user.appendChild(linktitle); 

doc.getDocumentElement().appendChild(user); 
TransformerFactory tFactory =TransformerFactory.newInstance(); 
Transformer transformer; 
transformer = tFactory.newTransformer(); 
DOMSource source = new DOMSource(doc); 
StreamResult result = new StreamResult(new java.io.File(path)); 
transformer.transform(source, result); 
}catch(Exception e){ 
} 
} 
/** 
* delete xml code 
* @param path 
*/ 
public boolean Exist(String id)
{
	try { 
		builder = factory.newDocumentBuilder(); 
		String uid=new String();
		String p=new String();
		Document doc=builder.parse(path); 
		doc.normalize(); 
		NodeList users =doc.getElementsByTagName("user"); 
		this.setUser(users); 
		for (int i=0;i<users.getLength();i++){ 
		Element link=(Element) users.item(i); 
		uid=link.getElementsByTagName("id").item(0).getFirstChild().getNodeValue(); 
		
		if(uid.equals(id))
		return true;

		} 
		}catch (ParserConfigurationException e) { 
		e.printStackTrace(); 
		} catch (SAXException e) { 
		e.printStackTrace(); 
		} catch (IOException e) { 
		e.printStackTrace(); 
		}
		return false;
		} 

public void delXmlCode(){ 
try { 
builder = factory.newDocumentBuilder(); 
doc=builder.parse(path); 
doc.normalize(); 
NodeList user =doc.getElementsByTagName("user"); 
Element elink=(Element) user.item(0); 
elink.removeChild(elink.getElementsByTagName("id").item(0)); 
elink.removeChild(elink.getElementsByTagName("pwd").item(0)); 

doc.getFirstChild().removeChild(elink); 
TransformerFactory tFactory =TransformerFactory.newInstance(); 
Transformer transformer = tFactory.newTransformer(); 
DOMSource source = new DOMSource(doc); 
StreamResult result = new StreamResult(new java.io.File(path)); 
transformer.transform(source, result); 
} catch (ParserConfigurationException e) { 
e.printStackTrace(); 
} catch (SAXException e) { 
e.printStackTrace(); 
} catch (IOException e) { 
e.printStackTrace(); 
} catch (TransformerConfigurationException e) { 
e.printStackTrace(); 
} catch (TransformerException e) { 
e.printStackTrace(); 
} 
} 
} 