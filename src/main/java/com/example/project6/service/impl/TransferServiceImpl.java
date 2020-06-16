package com.example.project6.service.impl;

import com.example.project6.dao.ITransferDAO;
import com.example.project6.model.User;
import com.example.project6.service.IUserService;
import com.example.project6.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.Transfer;
import com.example.project6.service.ITransferService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TransferServiceImpl implements ITransferService{

	@Autowired
	private IUserService userService;

	@Autowired
	private ITransferDAO transferDAO;

	@Override
	public Transfer getLastTransaction(String email) {
		User user = userService.findUserByEmail(email);
		if(user==null){
			return null;
		}

		List<Transfer> transfers = user.getTransfers();
		if(transfers.size()==0){
			return null;
		}
		return transfers.get(transfers.size()-1);
	}

	@Override
	public void createTransaction(String senderEmail, String receiverEmail, double amount, String description) {
		User sender = userService.findUserByEmail(senderEmail);
		User receiver = userService.findUserByEmail(receiverEmail);
		double commission = amount * Constant.TRANSFER_COMMISSION;
		Transfer transfer = new Transfer(sender, receiver, new Date(), amount, description, commission);
		sender.getTransfers().add(transfer);
		transferDAO.save(transfer);
	}

}
