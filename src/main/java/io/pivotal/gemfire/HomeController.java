package io.pivotal.gemfire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gemstone.gemfire.cache.Region;

import io.pivotal.gemfire.sdg.domain.Account;

@RestController
public class HomeController {

	@Autowired
	Region accountRegion;

	@RequestMapping("/")
	public String home() {
		return "Customer Search Service -- Available APIs: <br/>" + "<br/>"
				+ "GET /load  - load customer info <br/>"
				+ "GET /get   - query customer info <br/>";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/load")
	@ResponseBody
	public String loadCache() throws Exception {

		Account account = new Account(101, "Alice", "Wonderland");
		accountRegion.put(account.getId(), account);

		return "Account data successfully saved into cache";
	}

	@RequestMapping(method = RequestMethod.GET, path = "/get")
	@ResponseBody
	public String retrieveValue(@RequestParam(name = "id", required = true) Integer id) throws Exception {

		return accountRegion.get(id).toString();
	}
}
