package org.mql.crowddonating.controllers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.mql.crowddonating.business.IPaypalBusiness;
import org.mql.crowddonating.config.PaypalPaymentIntent;
import org.mql.crowddonating.config.PaypalPaymentMethod;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaypalSandboxController {

    private static final String PAYPAL_SUCCESS_URL = "/paypal/pay/success";
    private static final String PAYPAL_CANCEL_URL = "/paypal/pay/cancel";

    @Autowired
    IPaypalBusiness paypalBusiness;

    @GetMapping("/paypal")
    public String home() {
        return "paypal/index";
    }

    @PostMapping("/paypal/pay")
//    @ResponseBody
    public String pay(HttpServletRequest request) {
        String cancelUrl = Utility.getBaseURL(request) + PAYPAL_CANCEL_URL;
        String successUrl = Utility.getBaseURL(request) + PAYPAL_SUCCESS_URL;
        try {
            Payment payment = paypalBusiness.createPayment(
                    3.00,
                    "EUR",
                    PaypalPaymentMethod.paypal,
                    PaypalPaymentIntent.sale,
                    "Crowd Donating payment description",
                    cancelUrl,
                    successUrl
            );
            for (Links links : payment.getLinks()) {
                if (links.getRel().equals("approval_url")) {
                    return "redirect:" + links.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getMessage());
        }
        return "redirect:/paypal";
    }

    @ResponseBody
    @GetMapping(value = PAYPAL_SUCCESS_URL, produces = "application/json")
    public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            System.out.println("--------------------------------------");
            System.out.println("Payment ID : " + paymentId);
            System.out.println("Payer ID   : " + payerId);
            System.out.println("--------------------------------------");
            Payment payment = paypalBusiness.executePayment(paymentId, payerId);
            System.out.println("__________________________");
            System.out.println(payment.getPayer().getPayerInfo().getAccountNumber());
            System.out.println("__________________________");

            if (payment.getState().equals("approved")) {
//                return "success";
                return payment.toJSON();
            }
        } catch (PayPalRESTException e) {
            System.err.println(e.getMessage());
        }
        return "success";
    }

    @ResponseBody
    @GetMapping(PAYPAL_CANCEL_URL)
    public String cancel() {
        return "Canceled.";
    }

}
