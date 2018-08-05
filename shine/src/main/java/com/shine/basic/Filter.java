package com.shine.basic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

/**
 * 过滤条件
 * @author bang.chen
 * @since 2018-08-4
 */
public class Filter implements Serializable{
	private static final Logger logger = LogManager.getLogger(Filter.class);
	
	private static final long serialVersionUID = 8255601515844154042L;
	public static String OP_EQUAL = "=";
	public static String OP_GREAT_THEN = ">";
	public static String OP_GREAT_EQUAL = ">=";
	public static String OP_LESS_THEN = "<";
	public static String OP_LESS_EQUAL = "<=";
	public static String OP_LIKE = "LIKE";
	public static String OP_NOT_EQUAL = "<>";
	public static String OP_IN = "in";
	public static String OP_NOT_IN = "not in";
	
	
	private String id;
	private String property;
	private String operator = OP_EQUAL;
	private String value;
	
	public Filter(){
			
	}
	
	public Filter(String id , String property, String operator, String value){
		this.id = id;
		this.property = property;
		this.operator = operator;
		this.value = value;
	}
	
	public Filter(String property, String operator, String value){
		this.property = property;
		this.operator = operator;
		this.value = value;
	}
	
	public Filter(String property, String value){
		this.property = property;
		this.operator = OP_EQUAL;
		this.value = value;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
		
	}
	
	@Override
    public int hashCode() {
        int hash = 17;
        hash = hash * 31 + (id == null ? 0 : id.hashCode());
        hash = hash * 31 + (property == null ? 0 : property.hashCode());
        hash = hash * 31 + (operator == null ? 0 : operator.hashCode());
        hash = hash * 31 + (value == null ? 0 : value.hashCode());
        return hash;
    }
	
	@Override
	public boolean equals(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}else{
			//如果id一致，则相等否则  property 和 operator 一致 Filter则相等
			Filter filterObj = (Filter)obj;
			if(filterObj.getId() != null && filterObj.getId().equals(this.id)) return true;
			if(filterObj.getProperty()!=null && filterObj.getProperty().equalsIgnoreCase(this.property)){
				if(filterObj.getOperator()!=null && filterObj.getOperator().equalsIgnoreCase(this.operator)){
					if(filterObj.getValue()!=null && filterObj.getValue().equalsIgnoreCase(this.value)){
						return true;
					}else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
	}
	
	public boolean equalsWithoutValue(Object obj){
		if(obj == null || obj.getClass() != this.getClass()){
			return false;
		}else{
			//如果id一致，则相等否则  property 和 operator 一致 Filter则相等
			Filter filterObj = (Filter)obj;
			if(filterObj.getId() != null && filterObj.getId().equals(this.id)) return true;
			if(filterObj.getProperty()!=null && filterObj.getProperty().equalsIgnoreCase(this.property)){
				if(filterObj.getOperator()!=null && filterObj.getOperator().equalsIgnoreCase(this.operator)){
					return true;
				}else{
					return false;
				}
			}else{
				return false;
			}
		}
	}
	
	@Override
	public Filter clone(){
		Filter filter = new Filter(id,property,operator,value);
		return filter;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder("{");
		sb.append("id : "+id).append(",");
		sb.append("property : "+property).append(",");
		sb.append("operator : "+operator).append(",");
		sb.append("value : "+value.toString());
		sb.append("}");
		return sb.toString();
	}
}
