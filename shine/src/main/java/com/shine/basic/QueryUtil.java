package com.shine.basic;

/**
 *  @author bang.chen
 *   @since 2018-08-4
 */
public class QueryUtil {
	/**
	 * 组装排序语句
	 * @param sort
	 * @param order
	 * @return
	 */
	public static String formatOrderBy(String sort,String order){
		String orderByClause = formatToDataBase(sort);
		if("asc".equals(order)){
			return orderByClause + " ASC";
		}else{
			return orderByClause + " DESC";
		}
	}
	/**
	 * 将java bean的字段转换为数据库字段  （需保持标准格式）
	 * @param field
	 * @return
	 */
	public static String formatToDataBase(String field){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < field.length(); i++) {
			if(field.charAt(i) >= 'a' && field.charAt(i) <= 'z'){
				sb.append((char)(field.charAt(i) - 32));
			}else if(field.charAt(i) >= 'A' && field.charAt(i) <= 'Z'){
				sb.append("_");
				sb.append(field.charAt(i));
			}else if(field.charAt(i) >= '0' && field.charAt(i) <= '9'){
				if(i != 0 && field.charAt(i-1) <= '0'|| field.charAt(i-1) >= '9'){
					sb.append("_");
					sb.append(field.charAt(i));
				}else{
					sb.append(field.charAt(i));
				}
			}else{
				sb.append(field.charAt(i));
			}
		}
		return sb.toString();
	}
}
