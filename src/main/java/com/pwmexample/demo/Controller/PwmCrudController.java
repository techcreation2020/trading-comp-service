package com.pwmexample.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pwmexample.demo.Service.DataStreamingService;
import com.pwmexample.demo.Service.PwmCrudServiceImpl;
import com.pwmexample.demo.model.Fund;
import com.pwmexample.demo.model.Transaction;
import com.pwmexample.demo.model.User;
import com.wissen.trading.utils.Result;

@RestController
@RequestMapping("/api")
public class PwmCrudController {

	@Autowired
	PwmCrudServiceImpl pwmCrudService;
	
	@Autowired
	DataStreamingService dataStreamingService;

	@RequestMapping(value = "/getFunds", method = RequestMethod.GET)
	public ResponseEntity<Result<List<Fund>>> listAllSecurity() {		
		return new ResponseEntity<Result<List<Fund>>>(pwmCrudService.findAllSecurity(), HttpStatus.OK);
	}

	@RequestMapping(value = "/addTransaction", method = RequestMethod.POST)
	public ResponseEntity<Result<Transaction>> createTxn(@RequestBody Transaction transaction) {
		
		Result<Transaction> createTxn = pwmCrudService.createTxn(transaction);
		if(createTxn.getData() != null) {
			if(createTxn.getData().getUser() != null)
				dataStreamingService.sendDataToKafkaTopic(createTxn.getData().getUser());
		}
		return new ResponseEntity<Result<Transaction>>(createTxn, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ResponseEntity<Result<List<User>>> getAllUsers() {
		return new ResponseEntity<Result<List<User>>>(pwmCrudService.findAllUsers(), HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/getTransactions", method = RequestMethod.GET)
	 * public ResponseEntity<List<Transaction>> listOfTransaction(
	 * 
	 * @RequestParam String user, @RequestParam String fund_name) {
	 * List<Transaction> transaction = pwmCrudService.findAllTransaction(user,
	 * fund_name); if (transaction.isEmpty()) { return new
	 * ResponseEntity<List<Transaction>>(HttpStatus.NO_CONTENT); } return new
	 * ResponseEntity<List<Transaction>>(transaction, HttpStatus.OK);
	 * 
	 * }
	 */

	@RequestMapping(value = "/getUserTransaction/{userId}/{fundId}", method = RequestMethod.GET)
	public ResponseEntity<Result<List<Transaction>>> detailOfTransactionForUser(@PathVariable Long userId, @PathVariable Long fundId) {
		return new ResponseEntity<Result<List<Transaction>>>(pwmCrudService.findTransactionForUser(userId, fundId), HttpStatus.OK);
	}

}
