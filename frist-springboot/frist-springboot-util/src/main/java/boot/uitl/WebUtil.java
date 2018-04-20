package boot.uitl;

import boot.exception.JsontoObjException;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * 常用方法工具类
 * @author xuchun&cqd
 * @date 2017-07-12
 *
 */
public class WebUtil {

	private final static Logger logger = LoggerFactory.getLogger(WebUtil.class);

	/**
	 * 把request里的参数转换成map
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> initParams(HttpServletRequest request) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		return map;
	}

	/**
	 * 使用 Map按key进行排序
	 * @param map
	 * @return
	 */
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, Object> sortMap = new TreeMap<String, Object>(new MapValueComparator());
		sortMap.putAll(map);
		return sortMap;
	}


	/**
	 * fitlers中解决返回中文乱码
	 * @param obj
	 * @return
	 */
	public static String getStringJsonISO_8859_1(Object obj){
		try {
			return new String(WebUtil.parseFastJson(obj).getBytes(),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	/**
	 * 返回微众json字符串
	 * @param webankResult
	 * @return
	 * @throws Exception
	 */
	public static String webankCodyData(String webankResult) throws Exception{
		Map<String,Object> res=parseObject(webankResult);
		res.remove("code");
		res.remove("msg");
		res.remove("transactionTime");
		res.remove("bizSeqNo");
		res.remove("success");
		return WebUtil.parseFastJson(res);
	}
	
	public static String getGuid(){
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
	}

	/**
	 * gson对象转json字符串
	 * @param object
	 * @return
	 */
	/* 此方法会转义某些字符串，废弃使用
	public static String parseJson(Object object){
		return new GsonBuilder()
		.serializeNulls()
		.create()
		.toJson(object, 
				new TypeToken<Map<String, Object>>(){}.getType()
				);
	}*/
	
	/**
     * object转json字符
     * xuchun
     * 2016-07-07
     * @param object
     * @return
     * @throws Exception
     */
	public static String parseFastJson(Object object){
		return JSON.toJSONString(object);
	}
	/**
     * json字符转map
     * xuchun
     * 2016-07-07
     * @param json
     * @return
     * @throws Exception
     */
    public static Map<String,Object> parseObject(String json)  throws JsontoObjException {
		try {
			return (Map<String,Object>)JSON.parseObject(json);
		} catch (Exception e) {
			throw new JsontoObjException(e);
		}
	}
	/**
	 * gson json字符串转对象
	 * @param json
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static <T> T parseObject(String json,Class<T> cls) throws JsontoObjException {

		try {
			return new GsonBuilder()
            .serializeNulls()
            .create()
            .fromJson(json, cls);
		} catch (JsonSyntaxException e) {
			throw new JsontoObjException(e);
		}
	}
	
	  /**
     * map转实体类
     * xuchun
     * 2015-12-24
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, ? extends  Object> map, Object obj) {
        if (map == null || obj == null) {  
            return;  
        }  
        try {  
            BeanUtils.populate(obj, map);
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
        }  
    }

	/**
	 * map转实体类
	 * xuchun
	 * 2015-12-24
	 * @param map
	 * @param obj
	 */
	public static void transMap2BeanStr(Map<String, String> map, Object obj) {
		if (map == null || obj == null) {
			return;
		}
		try {
			BeanUtils.populate(obj, map);
		} catch (Exception e) {
			System.out.println("transMap2Bean2 Error " + e);
		}
	}

	/**
     * 实体类转map
     * xuchun
     * 2015-12-24
     * @param obj
     */
    public static Map<String, String> transObj2Map(Object obj) {  
        if (obj == null) {  
            return null;  
        }  
        try {  
        	 return  BeanUtils.describe(obj); 
        } catch (Exception e) {  
            System.out.println("transMap2Bean2 Error " + e);  
            return null;
        }  
    }

    /**
     * 实体类属性拷贝到map；类属性必须有默认值
     * xuchun
     * 2017-09-20
     * @param obj
     * @return
     */
    public static Map<String, Object> beanToMap(Object obj) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mappedObject = objectMapper.convertValue(obj, Map.class);
        return mappedObject;
    }
	/**
	 * 实体类属性拷贝到map；类属性必须有默认值
	 * xuchun
	 * 2017-09-20
	 * @param obj
	 * @return
	 */

	public static Map<String, String> beanToStrMap(Object obj) {
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, String> mappedObject = objectMapper.convertValue(obj, Map.class);
		return mappedObject;
	}

	/**
	 * xml转map
	 * xuchun
	 * 2017-09-20
	 * @param doc
	 * @return
	 */
	public static Map<String, Object> Dom2Map(Document doc){
		Map<String, Object> map = new HashMap<String, Object>();
		if(doc == null)
			return map;
		Element root = doc.getRootElement();
		for (Iterator iterator = root.elementIterator(); iterator.hasNext();) {
			Element e = (Element) iterator.next();
			List list = e.elements();
			if(list.size() > 0){
				map.put(e.getName(), Dom2Map(e));
			}else
				map.put(e.getName(), e.getText());
		}
		return map;
	}


	public static Map Dom2Map(Element e){
		Map map = new HashMap();
		List list = e.elements();
		if(list.size() > 0){
			for (int i = 0;i < list.size(); i++) {
				Element iter = (Element) list.get(i);
				List mapList = new ArrayList();

				if(iter.elements().size() > 0){
					Map m = Dom2Map(iter);
					if(map.get(iter.getName()) != null){
						Object obj = map.get(iter.getName());
						if(!obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(m);
						}
						if(obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = (List) obj;
							mapList.add(m);
						}
						map.put(iter.getName(), mapList);
					}else
						map.put(iter.getName(), m);
				}
				else{
					if(map.get(iter.getName()) != null){
						Object obj = map.get(iter.getName());
						if(!obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = new ArrayList();
							mapList.add(obj);
							mapList.add(iter.getText());
						}
						if(obj.getClass().getName().equals("java.util.ArrayList")){
							mapList = (List) obj;
							mapList.add(iter.getText());
						}
						map.put(iter.getName(), mapList);
					}else
						map.put(iter.getName(), iter.getText());
				}
			}
		}else
			map.put(e.getName(), e.getText());
		return map;
	}

	/**
	 * 获取异常错误信息打印格式化
	 * xuchun
	 * 2016-09-01
	 * @param e
	 * @return
	 */
	public static  String getErrorInfo(Exception e){
		StringBuilder sb=new StringBuilder();
		//记录到错误日志表
		for(StackTraceElement ste:e.getStackTrace()){
			sb.append(";");
			sb.append("\n");
			sb.append(ste);
		}
		sb.insert(0, e==null?"":e.toString());
		 return sb.toString();
	}
	
	// 生成唯一的随机数
	public static String generate() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmss");
		String date = dateformat.format(new Date());
		Long xx = Math.round(Math.random() * 1000000);

		while (xx < 100000) {

			xx = Math.round(Math.random() * 1000000);

		}
		String id = date + xx;
		return id;
	}

	/**
     * 格式化12位固定位数
     * xuchun
     * 2016-02-01
     * @param cost
     * @return
     */
    public static String en12Number(String cost){
    	//DecimalFormat df1 = new DecimalFormat("+###, ###, ##0.00#;-###, ###, ##0.00#");
		BigDecimal bg=new BigDecimal(cost);
		bg=new BigDecimal(bg.movePointRight(2).toString());
		NumberFormat nft6=new DecimalFormat("000000000000");//12位
		return nft6.format(bg);
	}
    /**
     * 格式化固定位数
     * xuchun
     * 2016-02-01
     * @param cost
     * @return
     */
    public static String enNumber(String cost){
    	//DecimalFormat df1 = new DecimalFormat("+###, ###, ##0.00#;-###, ###, ##0.00#");
    	BigDecimal bg=new BigDecimal(cost);
    	return bg.movePointRight(2).toString();
    }
    /**
     * 解码格式化固定位数
     * xuchun
     * 2016-02-01
     * @param cost
     * @return
     */
    public static BigDecimal de12Number(String cost){
		BigDecimal bg=new BigDecimal(cost);
		return bg.movePointLeft(2);
	}

}
