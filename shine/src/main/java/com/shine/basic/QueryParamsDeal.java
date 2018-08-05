package com.shine.basic;

import java.util.*;

/**
 * 查询处理类
 * @author jun.zeng
 *
 */
public class QueryParamsDeal {
	private String params = " 1 = 1 ";
	private boolean isAllSearch = false;
	private Set<String> set ;
	
	public QueryParamsDeal(QueryParams qp){
		set = new HashSet<String>();
		set.add("created");
		set.add("lastUpdated");
		deal(qp);
	}
	
	public QueryParamsDeal(QueryParams qp, Set set){
		this.set = new HashSet<String>();
		this.set.add("created");
		this.set.add("lastUpdated");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			this.set.add(it.next());
		}
		deal(qp);
	}
	
	public void deal(QueryParams qp){
		qp.parseFromRaw(); 
		dealAdvancedQuery(qp);
		List<Filter> filters = qp.getFilters();
		if(filters != null){
			for (Filter filter : filters) {
				String value = filter.getValue();
				if("all".equals(filter.getProperty())){
					if(isAllSearch){
						
					}
				}else if("offerResult".equals(filter.getProperty())){   // offerResult状态为未发时判断。。。
					if("未发".equals(filter.getValue())){
						
						String s = QueryUtil.formatToDataBase(filter.getProperty()) + " = '" + value + "' or " 
								+ QueryUtil.formatToDataBase(filter.getProperty()) + " IS NULL";
						params += " and (" + s + ")";
					}else if("已发".equals(filter.getValue())){
						String s = QueryUtil.formatToDataBase(filter.getProperty()) + " in ('已发','已接受','已拒绝')";
						params += " and (" + s + ")";
					}else{
						if(value.contains("%")){
							value = value + "'";
							String s = QueryUtil.formatToDataBase(filter.getProperty()) + " like '" + value;
							params += " and " + s ;
						}else{
							String s = QueryUtil.formatToDataBase(filter.getProperty()) + " = '" + value + "'";
							params += " and " + s ;
						}
					}
				}else if(set.contains(filter.getProperty())){
					if(value.charAt(0) == '%'){
						value = value.replace("%","");
					}
					String s = QueryUtil.formatToDataBase(filter.getProperty()) + " >= '" + value + "'";
					params += " and " + s ;
				}else if("=".equals(filter.getOperator())){
//					if(value.charAt(0) == '%'){
//						value = value + "%'";
//						String s = QueryUtil.formatToDataBase(filter.getProperty()) + " like '" + value;
//						params += " and " + s ;
					if(value.contains("%")){
						value = value + "'";
						String s = QueryUtil.formatToDataBase(filter.getProperty()) + " like '" + value;
						params += " and " + s ;
					}else{
						String s = QueryUtil.formatToDataBase(filter.getProperty()) + " = '" + value + "'";
						params += " and " + s ;
					}
				}else{
					if(filter.getOperator() != null){
						String s = QueryUtil.formatToDataBase(filter.getProperty()) +  " " + filter.getOperator() + " '" + value + "'";
						params += " and (" + s + ")" ;
					}
				}
			}
		}
	}
	/**
	 * 高级查询处理   -- 此方法现只对简历的高级查询起作用
	 * 常用查询
	 * @param qp
	 */
	public void dealAdvancedQuery(QueryParams qp){
		List<Map<String, String>> advance = qp.getAdvancedQuery();
		if(advance != null){
			if(advance.size() > 0){
				List<Filter> filters = qp.getFilters();  // 得到查询过滤条件
				if(filters == null){
					filters = new ArrayList<Filter>();
				}
				for (Map<String, String> map : advance) {
					String value = map.get("value") ;
					String group = map.get("group");
					if(value != null && !"".equals(value.trim()) && group != null && !"".equals(group.trim())){
						if("englishGrade".equals(group) || "englishScore".equals(group) || "japaneseScore".equals(group)){   // 若查询条件中获得的
							Filter filter = new Filter(group,Filter.OP_GREAT_EQUAL,value);
							if(!filters.contains(filter)) filters.add(filter);
						}else if("japaneseGrade".equals(group)){  //日语等级
							Filter filter = new Filter(group,Filter.OP_LESS_EQUAL,value);
							if(!filters.contains(filter)) filters.add(filter);
						}else{
							Filter filter = new Filter(group,"%"+ value);
							if(!filters.contains(filter)) filters.add(filter);
						}
					}
				}
				qp.setFilters(filters);
			}
		}
		// 简历表常用查询参数  
		List<Map<String, String>> common = qp.getCommonQuery();
		if(common != null){
			if(common.size() > 0){
				for (Map<String, String> map : common) {
					String value = map.get("value") ;
					String name = map.get("name");
					String group = map.get("group");
					if(value != null && !"".equals(value.trim()) && group != null && !"".equals(group.trim())){
						if("positionId".equals(group)){
							params += " and (POSITION_ID = " + value +") ";
						}else if("interviewPlace".equals(group)){
							params += " and (INTERVIEW_PLACE = '" + value +"') ";
						}else if("collage".equals(group)){
							String s = "(BACHELOR_COLLEGE_ID_1 = '" + value +"') or (BACHELOR_COLLEGE_ID_2 = '" + value
									+ "') or (MASTER_COLLEGE_ID = '" +  value  +"') or (DOCTORAL_COLLEGE_ID = '" + value + "')";
							params += " and (" + s +")";
						}else if("province".equals(group)){   // 本科院校所在省
							String s = "HIGHEST_DEGREE IN (select SCHOOL_NAME from SCHOOL_INFO WHERE SCHOOL_POSITION = " + value +")";
							if(value.contains(",")){
								s = "HIGHEST_DEGREE IN (select SCHOOL_NAME from SCHOOL_INFO WHERE SCHOOL_POSITION IN (" + value +"))";
							}
							params += " and (" + s + ")";
						}
					}
				}
			}
		}
	}
	
	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public boolean isAllSearch() {
		return isAllSearch;
	}
	/**
	 * boolean 默认为false
	 * @param isAllSearch 是否开启全字段查询
	 */
	public void setAllSearch(boolean isAllSearch) {
		this.isAllSearch = isAllSearch;
	}

	public String getParams() {
		return params;
	}
	
	public void setParams(String params) {
		this.params = params;
	}
}
