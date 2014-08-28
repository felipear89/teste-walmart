package com.farodrigues.teste_walmart;

import javax.transaction.Transactional;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TransactionConfiguration;

@SpringApplicationConfiguration(classes = App.class)
@ActiveProfiles("test")
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class AppConfTest {

}
