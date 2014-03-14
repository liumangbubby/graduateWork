package cn.gov.jyw.service.web;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService(endpointInterface = "cn.gov.jyw.service.web.Hello",serviceName="HelloImpl")
public class HelloImpl implements Hello {

	@Override
	public String sayHi(String name) {
		return "Hello," + name;
	}

}
