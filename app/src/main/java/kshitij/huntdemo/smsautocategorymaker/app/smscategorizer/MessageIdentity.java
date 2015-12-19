package kshitij.huntdemo.smsautocategorymaker.app.smscategorizer;

import android.util.Log;

import java.util.List;

/**
 * Created by SZLAP299 on 12/15/15.
 */
public class MessageIdentity {
    Category categoryObject = new Category();
    List l;

    private static MessageIdentity messageIdentity = new MessageIdentity();
    private MessageIdentity(){
        this.l = this.categoryObject.categoryList();
    }

    public static MessageIdentity getInstance() {
        return messageIdentity;
    }
    // "Expenses", "Mobile Recharge and Internet", "Wallet", "Shopping", "Food", "Cab", "Investments", "OTP",  "Travel", "Services"
    public static void Init(Integer sms_id, String aD, String message) {
        if (aD.length() <= 9) {
            Category smsCat = new Category();

            // OTP check
            if (
                    message.contains("verification") ||
                    message.contains(" OTP") ||
                    message.contains("(OTP)") ||
                    message.contains("one time password") ||
                    message.contains("Dynamic Access Code")
               ) {
                smsCat = smsCat.fromStringName("OTP");
            }
            // other type of messages
            else {
                String lm = message.toLowerCase();
                if (aD.contains("ultcash") || aD.contains("momoes") || aD.contains("payumn")) {
                    smsCat = smsCat.fromStringName("Wallet");
                }

                else if (
                        aD.contains("docomo") || aD.contains("indcom") || aD.contains("reliance") ||
                        aD.contains("rm-roam") || aD.contains("fchrge")
                        ) {
                    smsCat = smsCat.fromStringName("Mobile Recharge and Internet");
                }

                else if (aD.contains("mywash")) {
                    smsCat = smsCat.fromStringName("Services");
                }

                else if (aD.contains("goibib") || aD.contains("irctc")|| aD.contains("indigo")) {
                    smsCat = smsCat.fromStringName("Travel");
                }

                else if (aD.contains("paytm")) {
                    if (lm.contains("recharge of") && lm.contains("mobile")) smsCat = smsCat.fromStringName("Mobile Recharge and Internet");
                    if (lm.contains("have received") || lm.contains("has added")) smsCat = smsCat.fromStringName("Wallet");
                    if (lm.contains("you paid") || lm.contains("your paytm order")) smsCat = smsCat.fromStringName("Shopping");
                }

                else if (aD.contains("uber") || aD.contains("olac")) {
                    smsCat = smsCat.fromStringName("Cab");
                }

                else if (aD.contains("myntra") || aD.contains("flpkrt")) {
                    smsCat = smsCat.fromStringName("Shopping");
                }

                else if (aD.contains("dspblk") || aD.contains("rmfund")) {
                    smsCat = smsCat.fromStringName("Investments");
                }

                else if (
                        aD.contains("domino") || aD.contains("fpanda") || aD.contains("dunkin") ||
                        aD.contains("eatloa") || aD.contains("swiggy")
                        ) {
                    smsCat = smsCat.fromStringName("Food");
                }

                else if (aD.contains("kotak")) {
                    smsCat = smsCat.fromStringName("Expenses");
                }

            }

            CategorySMSMapping sms = new CategorySMSMapping(
                    smsCat,
                    sms_id,
                    aD,
                    message
            );
            sms.save();
        }
    }
}
