package com.pwmexample.demo.Service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pwmexample.demo.configuration.ApplicationConfiguration;
import com.pwmexample.demo.model.Fund;
import com.pwmexample.demo.model.Transaction;
import com.pwmexample.demo.model.User;
import com.wissen.trading.utils.Message;
import com.wissen.trading.utils.MessageType;
import com.wissen.trading.utils.Result;

@Service
public class PwmCrudServiceImpl {
	
	@Autowired
	private ApplicationConfiguration configuration;
	
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unchecked")
	public Result<List<Fund>> findAllSecurity() {
		Result<List<Fund>> result = null;
		final String uri = configuration.getApiUrl() + "/dataService/api/getFunds";
		
		try {
			return restTemplate.getForObject(uri, Result.class);			
		} catch(final Exception e) {
			e.printStackTrace();
			result = new Result<>();
			result.setMessages(Collections.singletonList(new Message("Error occured while fetching funds please retry.", MessageType.INFO)));
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Result<List<User>> findAllUsers() {
		Result<List<User>> result = null;
		final String uri = configuration.getApiUrl() + "/dataService/api/getUsers";
		
		try {
			return restTemplate.getForObject(uri, Result.class);			
		} catch(final Exception e) {
			e.printStackTrace();
			result = new Result<>();
			result.setMessages(Collections.singletonList(new Message("Error occured while fetching users please retry.", MessageType.INFO)));
		}
		return result;
	}
	
	public Result<Transaction> createTxn(final Transaction transaction) {
		final String uri = configuration.getApiUrl() + "/dataService/api/addTransaction";
		Result<Transaction> result = null;
		try {
			ParameterizedTypeReference<Result<Transaction>> param = new ParameterizedTypeReference<Result<Transaction>>() {};
			ResponseEntity<Result<Transaction>> response =  restTemplate.exchange(uri, HttpMethod.POST, new HttpEntity<Transaction>(transaction), param);
			result = response.getBody();							  
		} catch(Exception e) {			
			e.printStackTrace();
			result = new Result<>();
			result.setMessages(Collections.singletonList(new Message("Error occured while saving transaction please retry.", MessageType.INFO)));
		}
		return result;
		/*
		 * try { uri = configuration.getApiUrl()
		 * +"/dataService/api/getUserBalance?userName="+transaction.getUserName()+
		 * "&security="+transaction.getSecurity(); Integer balance =
		 * restTemplate.getForObject(uri, Integer.class); if(balance !=null) {
		 * result.setTransactionUnit(balance); } } catch(Exception e) {
		 * 
		 * } if(result !=null && result.getId()!=null) { try {
		 * restTemplate.postForObject(configuration.getApiUrl() +
		 * "/stream-service/create", result, Transaction.class); } catch(Exception e) {
		 * e.printStackTrace(); } }
		 *return result;
		 */
	}
	
	/*
	 * public List<Transaction> findAllTransaction(String user,String fund_name) {
	 * 
	 * //below URL needs to be changed final String uri =
	 * "http://localhost:9080/api/getUserFundList?name="+user+"&security="+
	 * fund_name; RestTemplate restTemplate = new RestTemplate(); List<Transaction>
	 * transaction = null; try{ Integer response = restTemplate.getForObject( uri,
	 * Integer.class); Integer balance = response; Transaction txnDetails = null;
	 * txnDetails.setSecurity(fund_name); txnDetails.setUserName(user);
	 * txnDetails.setTransactionUnit(balance);
	 * 
	 * 
	 * transaction.add(txnDetails); } catch(Exception e) {
	 * System.out.println("null pointer"); } return transaction; }
	 */
	
	@SuppressWarnings("unchecked")
	public Result<List<Transaction>> findTransactionForUser(final Long userId, final Long fundId){
		final String uri = configuration.getApiUrl() + "/dataService/api/getUserTransaction/" + userId + "/" + fundId;
		Result<List<Transaction>> result = null;
		try {
			result = restTemplate.getForObject(uri, Result.class);
		} catch(final Exception e) {
			e.printStackTrace();
			result = new Result<>();
			result.setMessages(Collections.singletonList(new Message("Error occured while get transactions for User." , MessageType.INFO)));
		}
		return result;
	}
}
