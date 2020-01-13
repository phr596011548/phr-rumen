package cn.p.entity.vo;

import java.util.List;

public class DataTablesData<T> {
	
	private Integer draw;
	private Integer recordsTotal;
	private Integer recordsFiltered;
	private List<T> data;
	public Integer getDraw() {
		return draw;
	}
	
	public DataTablesData(){
		
		this.data=data;
		this.draw=draw;
		this.recordsFiltered=recordsFiltered;
		this.recordsTotal=recordsTotal;
		
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
	public Integer getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(Integer recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public Integer getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(Integer recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	

}
