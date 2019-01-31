package org.mql.crowddonating.business.implementations;

import com.paypal.api.payments.*;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.mql.crowddonating.business.IPaypalBusiness;
import org.mql.crowddonating.config.PaypalPaymentIntent;
import org.mql.crowddonating.config.PaypalPaymentMethod;
import org.mql.crowddonating.models.utility.PaypalPayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class PaypalBusiness implements IPaypalBusiness {
    @Autowired
    private APIContext apiContext;

    @Override
    public Payment createPayment(PaypalPayment paypalPayment) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setCurrency(paypalPayment.getCurrency());
        paypalPayment.setTotal(new BigDecimal(paypalPayment.getTotal()).setScale(2, RoundingMode.HALF_UP).doubleValue());
        amount.setTotal(String.format("%.2f", paypalPayment.getTotal()));

        Transaction transaction = new Transaction();
        transaction.setDescription(paypalPayment.getDescription());
        transaction.setAmount(amount);
        // This is a custom attribute, can be used for sending data back to the server.
        transaction.setCustom(paypalPayment.getCustom());

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction);

        Payer payer = new Payer();
        payer.setPaymentMethod(paypalPayment.getMethod().toString());
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setEmail("imouad.you-seller@gmail.com");
        payerInfo.setPayerId("1");
        payerInfo.setFirstName("Mouad");
        payerInfo.setLastName("Youssef");
        payer.setPayerInfo(payerInfo);

        Payee payee = new Payee();
        payee.setAccountNumber("7333646453");

        Payment payment = new Payment();
        payment.setIntent(paypalPayment.getIntent().toString());
        payment.setPayer(payer);
        payment.setPayee(null);
        payment.setTransactions(transactions);

        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl(paypalPayment.getCancelUrl());
        redirectUrls.setReturnUrl(paypalPayment.getSuccessUrl());
        payment.setRedirectUrls(redirectUrls);

        return payment.create(apiContext);
    }

    @Override
    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        Payment payment = new Payment();
        payment.setId(paymentId);
        PaymentExecution paymentExecute = new PaymentExecution();
        paymentExecute.setPayerId(payerId);
        return payment.execute(apiContext, paymentExecute);
    }
}
