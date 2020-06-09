package com.example.project6.service;

import com.example.project6.model.CreditCard;
import com.example.project6.model.TopUp;
import com.example.project6.model.User;

public interface ITopUpService {

	TopUp getLastTransaction(String email);

	boolean createTransaction(User user, CreditCard card, double amount);

}
