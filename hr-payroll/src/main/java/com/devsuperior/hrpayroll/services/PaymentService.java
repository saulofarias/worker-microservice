package com.devsuperior.hrpayroll.services;

import java.util.HashMap;
import java.util.Map;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.entities.Worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * PaymentService
 */
@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", String.valueOf(workerId));
		String uri = workerHost + "/workers/{id}";

		Worker worker = restTemplate.getForObject(uri, Worker.class, uriVariables);
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}

}
