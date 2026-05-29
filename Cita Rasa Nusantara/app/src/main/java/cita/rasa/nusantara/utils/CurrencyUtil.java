package cita.rasa.nusantara.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {
    public static String formatIDR(double amount) {
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(amount);
    }
}
