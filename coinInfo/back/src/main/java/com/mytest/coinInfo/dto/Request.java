package com.mytest.coinInfo.dto;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Request {
	@JsonProperty("searchParams")
	private Map<String, String> searchParams;
	
	@JsonProperty("upsertParams")
	private Map<String, String> upsertParams;

	public Map<String, Integer> getPage() {
		Map<String, Integer> paging = new HashMap<>();
		int page = Integer.parseInt(searchParams.get("page"));
		int pageSize = Integer.parseInt(searchParams.get("pageSize"));
		
		paging.put("page", (page-1)*pageSize);
		paging.put("pageSize", pageSize);
		return paging;
	}
}
