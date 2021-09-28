package com.sangji0729.web.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sangji0729.web.common.CommandMap;
import com.sangji0729.web.service.TestService;
import com.sangji0729.web.service.TestServiceImpl;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



@Controller
public class TestController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name = "testService")
	private TestServiceImpl testService;

	@RequestMapping(value = "/main.do")
	public ModelAndView main(CommandMap commandMap) {
		ModelAndView mv = new ModelAndView("main");
		List<Map<String, Object>> list = testService.gList(commandMap);
		mv.addObject("list", list);

		return mv;
	}

	@GetMapping("/gDreamCard")
	public ModelAndView gDreamCard(CommandMap commandMap) throws Exception {
		ModelAndView mv = new ModelAndView("gDreamCard");
		String serviceKey = "D6l9xTSFpmLXdKoOwhrKYkx3i3R3BMzNJ4HlaP61AwqZlKxAcD%2FLE9pgnIWqkKLqeeKdj4sWrGHkgWoVVJQvpQ%3D%3D";

		StringBuilder sb = new StringBuilder();
		sb.append("http://api.data.go.kr/openapi/tn_pubr_public_chil_wlfare_mlsv_api");
		sb.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
		sb.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=1");//페이지 번호
		sb.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=510");//한 페이지 결과 수
		sb.append("&" + URLEncoder.encode("type", "UTF-8") + "=json"); //xml/json 여부
		/*
		 * sb.append("&" + URLEncoder.encode("mrhstNm", "UTF-8")); //가맹점명 sb.append("&"
		 * + URLEncoder.encode("mrhstCode", "UTF-8")); //가맹점 유형코드
		 */		sb.append("&" + URLEncoder.encode("ctprvnNm", "UTF-8") + "=서울특별시");//시도명
		 		sb.append("&" + URLEncoder.encode("signguNm", "UTF-8") + "=강동구");//시군구명
		/*
		 *  sb.append("&"
		 * + URLEncoder.encode("signguCode", "UTF-8"));//시군구코드 sb.append("&" +
		 * URLEncoder.encode("rdnmadr", "UTF-8"));//소재지도로명주소 sb.append("&" +
		 * URLEncoder.encode("lnmadr", "UTF-8"));//소재지지번주소 sb.append("&" +
		 * URLEncoder.encode("latitude", "UTF-8"));//위도 sb.append("&" +
		 * URLEncoder.encode("longitude", "UTF-8"));//경도 sb.append("&" +
		 * URLEncoder.encode("phoneNumber", "UTF-8"));//전화번호 sb.append("&" +
		 * URLEncoder.encode("weekdayOperOpenHhmm", "UTF-8"));//평일운영시작시간 sb.append("&" +
		 * URLEncoder.encode("weekdayOperColseHhmm", "UTF-8"));//평일운영종료시각 sb.append("&"
		 * + URLEncoder.encode("satOperOperOpenHhmm", "UTF-8"));//토요일운영시작시각
		 * sb.append("&" + URLEncoder.encode("satOperCloseHhmm", "UTF-8"));//토요일운영종료시각
		 * sb.append("&" + URLEncoder.encode("holidayOperOpenHhmm",
		 * "UTF-8"));//공휴일운영시작시각 sb.append("&" +
		 * URLEncoder.encode("holidayCloseOpenHhmm", "UTF-8"));//공휴일운영종료시각 sb.append("&"
		 * + URLEncoder.encode("dlvrOperOpenHhmm", "UTF-8"));//배달시작시각 sb.append("&" +
		 * URLEncoder.encode("dlvrCloseOpenHhmm", "UTF-8"));//배달종료시각 sb.append("&" +
		 * URLEncoder.encode("institutionNm", "UTF-8"));//관리기관명 sb.append("&" +
		 * URLEncoder.encode("institutionPhoneNumber", "UTF-8"));//관리기관전화번호
		 * sb.append("&" + URLEncoder.encode("referenceDate", "UTF-8"));//데이터기준일자
		 * sb.append("&" + URLEncoder.encode("instt_code", "UTF-8"));//제공기관코드
		 * sb.append("&" + URLEncoder.encode("instt_nm", "UTF-8"));//제공기관명
		 */		
		URL url = new URL(sb.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb2 = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb2.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb2.toString());
        System.out.println(sb.toString());
    
        JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
        
        Map<String, Object> map = (Map<String, Object>)jsonObject.get("response");
		map = (Map<String, Object>) map.get("body");
		//map = (Map<String, Object>) map.get("items");//이거 에러
		ArrayList<Map<String, Object>> list = (ArrayList<Map<String, Object>>) map.get("items");
		//System.out.println(map);
		//for (int i = 0; i < list.size(); i++) {
		//	Map<String, Object> get = list.get(i);
		//	testService.insertDb(get);
		//}
		//map = testSevice.insertDb();
		
		//System.out.println(list);
		mv.addObject("list", list);
		
		return mv;
	}
}
