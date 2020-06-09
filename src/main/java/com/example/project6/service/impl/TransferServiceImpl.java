package com.example.project6.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.project6.model.Transfer;
import com.example.project6.service.ITransferService;

@Service
@Transactional
public class TransferServiceImpl implements ITransferService{

	@Override
	public Transfer getLastTransaction(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
