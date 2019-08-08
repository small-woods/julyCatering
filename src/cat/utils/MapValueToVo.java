package cat.utils;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MapValueToVo {

	/**
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static <E> E requestMapValueToVo(Class<E> clss,Map<String,String[]> source) throws InstantiationException, IllegalAccessException {
		Map<String,Object> converMap = new HashMap<String, Object>();

		for(Map.Entry<String, String[]> entry:source.entrySet()){
			if(entry.getValue()!=null && entry.getValue().length==1){
				converMap.put(entry.getKey(), entry.getValue()[0]);
			}

		}
		System.out.println("ת�����Map:"+converMap);
		return mapValueToVo(clss, converMap);

	}

	public static <E> E mapValueToVo(Class<E> clss,Map<String,Object> source) throws InstantiationException, IllegalAccessException{
		E  vo = clss.newInstance();
		Method[] mList = clss.getMethods();
		for (int i = 0; i < mList.length; i++) {
			Method m = mList[i];
			String methodName =  m.getName();
			if (methodName.startsWith("set")){
				Class[] paramTypes =  m.getParameterTypes();
				if (paramTypes.length==1){
					String paramType = paramTypes[0].getName();
					methodName = methodName.substring(3);
					methodName = methodName.substring(0, 1).toLowerCase()+methodName.substring(1);
					Object mapValues =  source.get(methodName);
					String mapValuesType = "";
					if (mapValues!=null){
						mapValuesType = mapValues.getClass().getName();
					}

					try {
						if (mapValues!=null){
							if (paramType.equals("java.lang.String")){
								m.invoke(vo, String.valueOf(mapValues));
								//m.invoke(vo, mapValues);
							}else if (paramType.equals("java.util.Date")){
								if (mapValues instanceof Date){
									m.invoke(vo, mapValues);
								}else if (mapValues instanceof String){
									SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									Date d = sdf.parse(String.valueOf(mapValues));
									m.invoke(vo, d);
								}else if (mapValues instanceof Timestamp){
									m.invoke(vo, mapValues);
								}

							}else if (paramType.equals("java.lang.Double")|| paramType.equals("double")){
								if (mapValues instanceof String){
									if (String.valueOf(mapValues).matches("[0-9]+[.]?[0-9]*")){
										m.invoke(vo, Double.parseDouble(String.valueOf(mapValues)));
									}
								}else if (mapValues instanceof Double){
									m.invoke(vo, mapValues);
								}else{
									m.invoke(vo, Double.parseDouble(String.valueOf(mapValues)));
								}
							}else if (paramType.equals("java.lang.Integer")|| paramType.equals("int")){
								m.invoke(vo, Integer.parseInt(String.valueOf(mapValues)));
							}

						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		return vo;
	}


}