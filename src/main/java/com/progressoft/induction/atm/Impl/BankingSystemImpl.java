package com.progressoft.induction.atm.Impl;

import com.progressoft.induction.atm.BankingSystem;
import com.progressoft.induction.atm.Banknote;
import com.progressoft.induction.atm.exceptions.AccountNotFoundException;
import com.progressoft.induction.atm.exceptions.InsufficientFundsException;

import java.math.BigDecimal;
import java.util.*;

public class BankingSystemImpl implements BankingSystem {
    Map<String, BigDecimal> accountBalanceMap = new HashMap<String, BigDecimal>();
    EnumMap<Banknote, Integer> atmCashMap = new EnumMap<>(Banknote.class);

    public BankingSystemImpl() {
        atmCashMap.put(Banknote.FIFTY_JOD, 10);
        atmCashMap.put(Banknote.TWENTY_JOD, 20);
        atmCashMap.put(Banknote.TEN_JOD, 100);
        atmCashMap.put(Banknote.FIVE_JOD, 100);

        accountBalanceMap.put("123456789", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("111111111", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("222222222", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("333333333", BigDecimal.valueOf(1000.0));
        accountBalanceMap.put("444444444", BigDecimal.valueOf(1000.0));
    }

    public BigDecimal sumOfMoneyInAtm() {
        // Your code
        BigDecimal balanceInATM = new BigDecimal("0.0");
        for (Banknote banknote : atmCashMap.keySet()) {
            balanceInATM = balanceInATM.add(banknote.getValue().multiply(BigDecimal.valueOf(atmCashMap.get(banknote))));

        }
        return balanceInATM;
    }

    public List<Banknote> getCombinationOfBankNote(BigDecimal amount) {
        BigDecimal withdrawnNotes = new BigDecimal("0.0");
        List<Banknote> notes = new ArrayList<>();
        for (Banknote banknote : atmCashMap.keySet()) {
            int count = atmCashMap.get(banknote);
            while (count > 0 && amount.compareTo(withdrawnNotes.add(new BigDecimal(String.valueOf(banknote.getValue())))) >= 0) {
                notes.add(banknote);
                withdrawnNotes = withdrawnNotes.add(banknote.getValue());
                count--;
            }
        }
        return notes;
    }


    @Override
    public BigDecimal getAccountBalance(String accountNumber) {
        //your code
        if (!accountBalanceMap.containsKey(accountNumber)) {
            throw new AccountNotFoundException(String.format("Account with account number : %s doesn't exist!", accountNumber));
        }
        return accountBalanceMap.get(accountNumber);
    }

    @Override
    public void debitAccount(String accountNumber, BigDecimal amount) {
        accountBalanceMap.put(accountNumber, accountBalanceMap.get(accountNumber).subtract(amount));
        List<Banknote> withdrawnNotes = getCombinationOfBankNote(amount);
        for (Banknote banknote : withdrawnNotes) {
            atmCashMap.put(banknote, atmCashMap.get(banknote) - 1);
        }
        //your code
    }
}
