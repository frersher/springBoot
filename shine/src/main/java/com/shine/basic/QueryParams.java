package com.shine.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QueryParams {
	
	private static final Logger logger = LogManager.getLogger(QueryParams.class);
	
	public final String OR = " OR ";
	public final String AND = " AND ";
	public final String WHERE = " WHERE ";
	
	private Integer page ;
	private Integer rows ;
	private String sort ;
	private String order ;
	private String filtersRaw ;
	
	private List<Map<String, String>> advancedQuery ;
	private List<Map<String, String>> commonQuery ;
	
	public List<Map<String, String>> getCommonQuery() {
		return commonQuery;
	}

	public void setCommonQuery(List<Map<String, String>> commonQuery) {
		this.commonQuery = commonQuery;
	}

	public List<Map<String, String>> getAdvancedQuery() {
		return advancedQuery;
	}

	public void setAdvancedQuery(List<Map<String, String>> advancedQuery) {
		this.advancedQuery = advancedQuery;
	}

	private List<Filter> filters = new ArrayList<Filter>();

	/**
	 * 获取Filter
	 * @param property filter property
	 * @param property filter operator
	 * @return filter 对象
	 */
	public Filter getFilter(String property,String operator){
		Filter filterExample = new Filter();
		filterExample.setProperty(property);
		filterExample.setOperator(operator);
		
		Filter filter;
		for(int i = 0; i < filters.size(); i ++){
			filter = filters.get(i);
			if(filter != null && filter.equalsWithoutValue(filterExample)) {
				return filter;
			}
		}
		
		return null;
	}
	
	/**
	 * 获取Filter
	 * @param property filter property
	 * @return filter 对象
	 */
	public List<Filter> getFilterByProperty(String property){
		List<Filter> matchFilters = new ArrayList<Filter>();
		Filter filter = null;
		for(int i = 0; i < filters.size(); i ++){
			filter = filters.get(i);
			if(filter != null && filter.getProperty()!=null && filter.getProperty().equalsIgnoreCase(property)) {
				matchFilters.add(filter);
			}
		}
		
		return matchFilters;
	}
	
	/**
	 * 新增Filter
	 * @param filter
	 * @param filter filter对象
	 */
	public void addFilter(Filter filter){
		if(filters == null) filters = new ArrayList<Filter>();
			
		Filter filterTmp = null;
		for(int i = 0; i < filters.size(); i ++){
			filterTmp = filters.get(i);
			if(filterTmp != null && filterTmp.equalsWithoutValue(filter)){
				filters.set(i, filter);
			}	
		}
		filters.add(filter);
	}
	
	/**
	 * 删除Filter
	 * @param  toRemove
	 * @return 被删除的Filter对象
	 */
	public Filter removeFilter(Filter toRemove){
		Filter filter = null;
		for(int i = 0; i < filters.size(); i ++){
			filter = filters.get(i);
			if(filter != null && filter.equalsWithoutValue(toRemove)){
				filters.remove(i);
				return filter;
			}	
		}
		
		return null;
	}
	
	public List<Filter> getFilters() {
		return filters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}

	public QueryParams() {
	}
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getOrderByClause(){
		if(sort == null || "".equals(sort) || "nosort".equals(sort)){  // 当前端传来的参数为nosort时表示  不排序。
			return null;
		}else{
			return QueryUtil.formatOrderBy(sort, order);
		}
	}
	public String getPagesMysql(){
		if(page == null || page <= 0 || rows == null || rows < 0){
			return null;
		}
		return String.valueOf((page - 1)*rows) + "," + String.valueOf(rows) ;
	}
	
	public void parseFromRaw(){
		try{
			JsonFactory  jsonFacotry = new JsonFactory();
			CustomObjectMapper objectMapper = new CustomObjectMapper();
			//从json字符串转换为 Filter对象
			if(filtersRaw !=null) {
				if(filters == null) filters = new ArrayList<Filter>();
				JsonParser jsonParse = jsonFacotry.createJsonParser(filtersRaw);
				jsonParse.nextToken();
				while (jsonParse.nextToken() == JsonToken.START_OBJECT) {
					Filter filter = objectMapper.readValue(jsonParse, Filter.class);
					if(!filters.contains(filter)) filters.add(filter);
				}
				jsonParse.close();
			}
			
			
		}catch(Exception e){
			logger.error("json parse error!", e);
			filters = null;
		}
	}
	
	public String getFiltersRaw() {
		return filtersRaw;
	}

	public void setFiltersRaw(String filtersRaw) {
		this.filtersRaw = filtersRaw;
	}

	public void warpToRaw(){
		try{
			CustomObjectMapper objectMapper = new CustomObjectMapper();
			//从 Filter对象转换为json字符串
			if(filters !=null) {
				filtersRaw = objectMapper.writeValueAsString(filters);
			}else{
				filtersRaw = null;
			}
			
		}catch(Exception e){
			logger.error("json wrap error!", e);
			filtersRaw = null;
		}
	}
	
	
}
