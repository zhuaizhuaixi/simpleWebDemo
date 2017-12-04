package com.fzu.demo;

import com.alibaba.fastjson.JSONObject;
import com.fzu.demo.common.AlgorithmUtils;
import com.fzu.demo.web.vo.CompanyInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebtestApplicationTests {

	@Test
	public void contextLoads() throws Exception{
		List<String> list1 = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		list1.add("恐怖");
		list2.add("恐怖");
		list2.add("解密");
		double similar =  AlgorithmUtils.recommendationAlgorithmBaseOnContent(list1,list2);
		System.out.println(similar);
	}

}
