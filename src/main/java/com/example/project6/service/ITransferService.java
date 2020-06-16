package com.example.project6.service;

import com.example.project6.model.Transfer;

public interface ITransferService {

	Transfer getLastTransaction(String email);

    void createTransaction(String senderEmail, String receiverEmail, double amount, String description);
}
