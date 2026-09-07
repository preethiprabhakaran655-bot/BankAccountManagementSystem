                           package org.anudip.bams.main;

import org.anudip.bams.controller.BankAccountController;
import org.anudip.bams.repository.BankAccountRepository;
import org.anudip.bams.repository.BankAccountRepositoryImpl;
import org.anudip.bams.service.BankAccountService;
import org.anudip.bams.service.BankAccountServiceImpl;

public class BAMSApp {

    public static void main(String[] args) {

        BankAccountRepository repository =
                new BankAccountRepositoryImpl();
        BankAccountService service =
                new BankAccountServiceImpl(repository);

        BankAccountController controller =
                new BankAccountController(service);

        controller.start();
    }
}