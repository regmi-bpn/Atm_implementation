package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.ATM;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;
import com.progressoft.induction.atm.exceptions.NotEnoughMoneyInATMException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ATMImpl implements ATM {
    private final BankingSystemImpl bankingSystem = new BankingSystemImpl();

    @Override
    public List<Banknote> withdraw(String accountNumber, BigDecimal amount) {
        if (amount.compareTo(bankingSystem.getAccountBalance(accountNumber)) > 0) {
            throw new InsufficientFundsException("Your account doesn't have sufficient balance!");
        } else if (amount.compareTo(bankingSystem.sumOfMoneyInAtm()) > 0) {
            throw new NotEnoughMoneyInATMException("ATM don't have enough money at the moment!");
        }
        List<Banknote> withdrawnBankNotes = bankingSystem.getCombinationOfBankNote(amount);
        bankingSystem.debitAccount(accountNumber, amount);
        // Your code here
        return withdrawnBankNotes;
    }

    @Override
    public BigDecimal checkBalance(String accountNumber) {
        return bankingSystem.getAccountBalance(accountNumber);
    }
}
