package org.mql.crowddonating.controllers;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.json.JSONObject;
import org.mql.crowddonating.business.IPaypalBusiness;
import org.mql.crowddonating.config.PaypalPaymentIntent;
import org.mql.crowddonating.config.PaypalPaymentMethod;
import org.mql.crowddonating.models.utility.PaypalPayment;
import org.mql.crowddonating.models.utility.PaypalPaymentBuilder;
import org.mql.crowddonating.utilities.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PaypalController {

    private static final String PAYPAL_SUCCESS_URL = "/paypal/pay/success";
    private static final String PAYPAL_CANCEL_URL = "/paypal/pay/cancel";

    @Autowired
    IPaypalBusiness paypalBusiness;

    @GetMapping("/paypal")
    public String home() {
        return "paypal/index";
    }

    public static void main(String[] args) {
        String json = Utility.getJsonFromUrl("http://free.currencyconverterapi.com/api/v6/convert?q=MAD_USD&compact=ultra");

        JSONObject obj = new JSONObject(json);
        double amount = obj.getDouble("MAD_USD");

        System.out.println(amount);
    }

    @PostMapping("/cases/paypal")
    public String pay(HttpServletRequest request, @RequestParam long id, @RequestParam double value) {
        String cancelUrl = Utility.getBaseURL(request) + PAYPAL_CANCEL_URL;
        String successUrl = Utility.getBaseURL(request) + PAYPAL_SUCCESS_URL;

        String json = Utility.getJsonFromUrl("http://free.currencyconverterapi.com/api/v6/convert?q=MAD_USD&compact=ultra");
        JSONObject obj = new JSONObject(json);
        double dollarPrice = obj.getDouble("MAD_USD");
        value = value * dollarPrice;

        JSONObject custom = new JSONObject();
        custom.put("test", 123);
        custom.put("test2", 321);

        try {
            PaypalPayment paypalPayment = new PaypalPaymentBuilder()
                    .setTotal(value)
                    .setCurrency("USD")
                    .setMethod(PaypalPaymentMethod.paypal)
                    .setIntent(PaypalPaymentIntent.sale)
                    .setCancelUrl(cancelUrl)
                    .setSuccessUrl(successUrl)
                    // TODO: update this
                    .setDescription("Crowd Donating payment description")
                    .setCustom(custom.toString())
                    .build();
            Payment payment = paypalBusiness.createPayment(paypalPayment);

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
        // TODO: implement refund functionality
        try {
            Payment payment = paypalBusiness.executePayment(paymentId, payerId);

            if (payment.getState().equals("approved")) {
//                String json = Utility.getJsonFromUrl("http://free.currencyconverterapi.com/api/v6/convert?q=MAD_USD&compact=ultra");
//                JSONObject obj = new JSONObject(json);
//                double dollarPrice = obj.getDouble("MAD_USD");
//
//                JSONObject paymentJson = new JSONObject(payment.toJSON());
//                obj = paymentJson.getJSONObject("transactions").getJSONObject("related_resources");
//
//                String transaction_id = obj.getJSONObject("sale").getString("id");
//                double transaction_fee = obj.getJSONObject("transaction_fee").getDouble("value");
//                double amount = obj.getJSONObject("amount").getDouble("total");
//                String custom = obj.getString("custom");
//
//                transaction_fee *= dollarPrice;
//                amount *= dollarPrice;
//
//                System.out.println(transaction_id);
//                System.out.println(transaction_fee);
//                System.out.println(amount);
//                System.out.println(custom);

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
