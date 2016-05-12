// %modified: 0 %
package com.ametis.cms.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;


public class Converter {
  public static String getDateddMMYYYYHHmmSS(java.sql.Timestamp date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }


  public static String getDateddMMYYYYHHmmSSOjik(java.util.Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }
/*
  String output;
  SimpleDateFormat formatter;
  String pattern="dd/MM/yyyy h:mm a";


  formatter = new java.text.SimpleDateFormat(pattern);
  java.util.Date today = new java.util.Date();
  output = formatter.format(today);
  System.out.println(output);
*/

  public static String getDateddMMYYYYHHmmSS(Date date) {
      if (date != null) {
SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
              return sdfDate.format(date);
      } else {
              return "";
      }
}

  /*
   * Method yang lama
      public static String getDateddMMYYYYHHmmSS(Date date) {
              if (date != null) {
       SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                      return sdfDate.format(date);
              } else {
                      return "";
              }
      }*/
  public static String getDateHHMM(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("hh:mm");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static String getDateddMMYYYYStrip(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd-MM-yyyy");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static java.sql.Date dateDDMMYYYYHHMI(String parameter,
                                               String jammenit) {
    if (! (parameter == null || parameter.equals(""))) {
      String[] result = new String[3];
      result[0] = parameter.substring(0, 2);
      result[1] = parameter.substring(3, 5);
      result[2] = parameter.substring(6);

      java.util.GregorianCalendar startcal = new java.util.GregorianCalendar();
      startcal.clear();

      java.util.StringTokenizer token = new java.util.StringTokenizer(jammenit,
          ":");
      String[] result2 = new String[2];
      int i = 0;

      for (; token.hasMoreTokens(); ) {
        result2[i] = token.nextToken();
        i++;
      }
      startcal.set(Integer.parseInt(result[2]), Integer.parseInt(result[1]) -
                   1, Integer.parseInt(result[0]), Integer.parseInt(result2[0]),
                   Integer.parseInt(result2[1]));

      return new java.sql.Date(startcal.getTime().getTime());
    }
    else {
      return null;
    }
  }

  public static java.sql.Date dateResultDDMMYYYYStrip(String parameter) {
    if (! (parameter == null || parameter.equals(""))) {
      java.util.StringTokenizer token =
          new java.util.StringTokenizer(parameter, "-");
      String[] result = new String[3];
      int i = 0;

      for (; token.hasMoreTokens(); ) {
        result[i] = token.nextToken();
        i++;
      }

      java.util.GregorianCalendar startcal =
          new java.util.GregorianCalendar();
      startcal.clear();
      startcal.set(
          Integer.parseInt(result[2]),
          Integer.parseInt(result[1]) - 1,
          Integer.parseInt(result[0]));

      return new java.sql.Date(startcal.getTime().getTime());
    }
    else {
      return null;
    }
  }

  public static String getDateHHMMEmpty(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("hhmm");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static String getDateMMMMYYYY(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("MM");
      String month = sdfDate.format(date);
      String mm = "";

      switch (Integer.parseInt(month)) {
        case 1:
          mm = "Januari";
          break;

        case 2:
          mm = "Februari";
          break;

        case 3:
          mm = "Maret";
          break;

        case 4:
          mm = "April";
          break;

        case 5:
          mm = "Mei";
          break;

        case 6:
          mm = "Juni";
          break;

        case 7:
          mm = "Juli";
          break;

        case 8:
          mm = "Agustus";
          break;

        case 9:
          mm = "September";
          break;

        case 10:
          mm = "Oktober";
          break;

        case 11:
          mm = "November";
          break;

        case 12:
          mm = "Desember";
          break;
      }

      SimpleDateFormat sdf1Date = new SimpleDateFormat("yyyy");

      return mm + " " + sdf1Date.format(date);
    }
    else {
      return "";
    }
  }

  public static String getDateMMYYYY(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("MM yyyy");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static String getDateddMMMMYYYY(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd MMMM',' yyyy");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static String getDateddMMYYYY(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static String getDateddMMYYYYEmpty(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("ddMMyyyy");

      return sdfDate.format(date);
    }
    else {
      return "";
    }
  }

  public static String[] getDateddMMYYYYPart(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDate = new SimpleDateFormat("dd");
      SimpleDateFormat sdfDateMonth = new SimpleDateFormat("MM");
      SimpleDateFormat sdfDateYear = new SimpleDateFormat("yyyy");
      String[] result = {
          sdfDate.format(date),
          sdfDateMonth.format(date),
          sdfDateYear.format(date)};

      return result;
    }
    else {
      return null;
    }
  }

  public static String getFormat(int size, int decimal, double value) {
    StringBuffer buff = new StringBuffer();
    int sizedecimal = size + decimal;

    for (int i = 0; i < sizedecimal; i++) {
      buff.append("0");
    }

    StringBuffer buffdec = new StringBuffer("1");

    for (int i = 0; i < decimal; i++) {
      buffdec.append("0");
    }

    int t = Integer.parseInt(buffdec.toString());
    DecimalFormat formats = new DecimalFormat();
    formats.applyPattern(buff.toString());

    return formats.format(value * t);
  }

  public static String getMoney(double value) {
    NumberFormat nf = NumberFormat.getCurrencyInstance();

    if (nf instanceof DecimalFormat) {
      ( (DecimalFormat) nf).applyPattern("#,##0.00;(#,##0.00)");
      //((DecimalFormat) nf).applyPattern("#.##0,00;(#.##0,00)");
    }

    if (value == 0) {
      return "";
    }
    else {
      return nf.format(value);
    }
  }

  public static String getFormatDecimalUnusualy(double value) {

    DecimalFormatSymbols unusualSymbols = new DecimalFormatSymbols();

    unusualSymbols.setDecimalSeparator(',');
    unusualSymbols.setGroupingSeparator('.');

    String strange = "#,##0.###";
    DecimalFormat weirdFormatter = new DecimalFormat(strange, unusualSymbols);
    weirdFormatter.setGroupingSize(3);
    String bizarre = weirdFormatter.format(value);

    if (value == 0) {
      return "0";
    }
    else {
      return bizarre;
    }
  }

  public static String getNumberToTextIndonesia(int number) {
    if (number < 10) {
      if (number == 0) {
        return "Nol";
      }
      else if (number == 1) {
        return "Satu";
      }
      else if (number == 2) {
        return "Dua";
      }
      else if (number == 3) {
        return "Tiga";
      }
      else if (number == 4) {
        return "Empat";
      }
      else if (number == 5) {
        return "Lima";
      }
      else if (number == 6) {
        return "Enam";
      }
      else if (number == 7) {
        return "Tujuh";
      }
      else if (number == 8) {
        return "Delapan";
      }
      else if (number == 9) {
        return "Sembilan";
      }
    }
    else {
      StringBuffer buff = new StringBuffer();
      String numberString = String.valueOf(number);

      for (int i = 0; i < numberString.length(); i++) {
        buff.append(
            getNumberToTextIndonesia(
            numberString.substring(i, i + 1))).append(
            " ");
      }

      return buff.toString();
    }

    return "";
  }

  public static String getNumberToTextIndonesia(String numberString) {
    int number = Integer.parseInt(numberString);

    if (number < 10) {
      if (number == 0) {
        return "Nol";
      }
      else if (number == 1) {
        return "Satu";
      }
      else if (number == 2) {
        return "Dua";
      }
      else if (number == 3) {
        return "Tiga";
      }
      else if (number == 4) {
        return "Empat";
      }
      else if (number == 5) {
        return "Lima";
      }
      else if (number == 6) {
        return "Enam";
      }
      else if (number == 7) {
        return "Tujuh";
      }
      else if (number == 8) {
        return "Delapan";
      }
      else if (number == 9) {
        return "Sembilan";
      }
    }
    else {
      StringBuffer buff = new StringBuffer();

      for (int i = 0; i < numberString.length(); i++) {
        buff.append(
            getNumberToTextIndonesia(
            numberString.substring(i, i + 1))).append(
            " ");
      }

      return buff.toString();
    }

    return "";
  }

  public static String getRound(double value) {
    DecimalFormat formats = new DecimalFormat();
    formats.applyPattern("0");

    if (value == 0) {
      return "";
    }
    else {
      return formats.format(value);
    }
  }

  public static String getRound2(double value) {
    DecimalFormat formats = new DecimalFormat();
    formats.applyPattern("0.00");

    if (value == 0) {
      return "";
    }
    else {
      return formats.format(value);
    }
  }

  public static String getRound3(double value) {
    NumberFormat nf = NumberFormat.getCurrencyInstance();

    if (nf instanceof DecimalFormat) {
      ( (DecimalFormat) nf).applyPattern("#,##0;(#,##0)");
    }

    if (value == 0) {
      return "";
    }
    else {
      return nf.format(value);
    }
  }

  public static String getRound4(double value) {
    DecimalFormat formats = new DecimalFormat();
    formats.applyPattern("0");

    if (value == 0) {
      return "0";
    }
    else {
      return formats.format(value);
    }
  }

  public static String getSayMoneyInIndonesia(double money) {
    StringBuffer result = new StringBuffer();

    if (money < 0) {
      result.append("Minus ");

    }
    money = Math.abs(money);

    DecimalFormat dec = new DecimalFormat("#,##0.00");
    String value = dec.format(money);
    StringTokenizer token = new StringTokenizer(value, ",");
    String[] ps = new String[6];
    int i;

    for (i = 0; token.hasMoreTokens(); i++) {
      ps[i] = token.nextToken();
    }

    // Untuk Trilyun
    if (i >= 5) {
      String first = ps[i - 5];
      int length = first.length();

      if (length == 3) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          result.append("Seratus ");
        }
        else if (!tiga.equals("0")) {
          result.append(getNumberToTextIndonesia(tiga)).append(" Ratus ");

        }
        String dua = first.substring(1, 2);

        if (dua.equals("0")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Trilyun ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Trilyun ");
          }
        }
        else if (dua.equals("1")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Sepuluh Trilyun ");
          }
          else if (first.substring(2, 3).equals("1")) {
            result.append("Sebelas Trilyun ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Belas Trilyun ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(dua)).append(" Puluh ");

          if (first.substring(2, 3).equals("0")) {
            result.append("Trilyun ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Trilyun ");
          }
        }
      }
      else if (length == 2) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          if (first.substring(1, 2).equals("0")) {
            result.append("Sepuluh Trilyun ");
          }
          else if (first.substring(1, 2).equals("1")) {
            result.append("Sebelas Trilyun ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Belas Trilyun ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(tiga)).append(" Puluh ");

          if (first.substring(1, 2).equals("0")) {
            result.append("Trilyun ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Trilyun ");
          }
        }
      }
      else if (length == 1) {
        result.append(getNumberToTextIndonesia(first.substring(0, 1))).append(
            " Trilyun ");
      }
    }

    // Untuk Milyar
    if (i >= 4) {
      String first = ps[i - 4];
      int length = first.length();

      if (length == 3) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          result.append("Seratus ");
        }
        else if (!tiga.equals("0")) {
          result.append(getNumberToTextIndonesia(tiga)).append(" Ratus ");

        }
        String dua = first.substring(1, 2);

        if (dua.equals("0")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Milyar ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Milyar ");
          }
        }
        else if (dua.equals("1")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Sepuluh Milyar ");
          }
          else if (first.substring(2, 3).equals("1")) {
            result.append("Sebelas Milyar ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Belas Milyar ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(dua)).append(" Puluh ");

          if (first.substring(2, 3).equals("0")) {
            result.append("Milyar ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Milyar ");
          }
        }
      }
      else if (length == 2) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          if (first.substring(1, 2).equals("0")) {
            result.append("Sepuluh Milyar ");
          }
          else if (first.substring(1, 2).equals("1")) {
            result.append("Sebelas Milyar ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Belas Milyar ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(tiga)).append(" Puluh ");

          if (first.substring(1, 2).equals("0")) {
            result.append("Milyar ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Milyar ");
          }
        }
      }
      else if (length == 1) {
        result.append(getNumberToTextIndonesia(first.substring(0, 1))).append(
            " Milyar ");
      }
    }

    // Untuk Jutaan
    if (i >= 3) {
      String first = ps[i - 3];
      int length = first.length();

      if (length == 3) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          result.append("Seratus ");
        }
        else if (!tiga.equals("0")) {
          result.append(getNumberToTextIndonesia(tiga)).append(" Ratus ");

        }
        String dua = first.substring(1, 2);

        if (dua.equals("0")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Juta ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Juta ");
          }
        }
        else if (dua.equals("1")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Sepuluh Juta ");
          }
          else if (first.substring(2, 3).equals("1")) {
            result.append("Sebelas Juta ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Belas Juta ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(dua)).append(" Puluh ");

          if (first.substring(2, 3).equals("0")) {
            result.append("Juta ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Juta ");
          }
        }
      }
      else if (length == 2) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          if (first.substring(1, 2).equals("0")) {
            result.append("Sepuluh Juta ");
          }
          else if (first.substring(1, 2).equals("1")) {
            result.append("Sebelas Juta ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Belas Juta ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(tiga)).append(" Puluh ");

          if (first.substring(1, 2).equals("0")) {
            result.append("Juta ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Juta ");
          }
        }
      }
      else if (length == 1) {
        result.append(getNumberToTextIndonesia(first.substring(0, 1))).append(
            " Juta ");
      }
    }

    // Untuk Ribuan
    if (i >= 2) {
      String first = ps[i - 2];
      int length = first.length();

      if (length == 3) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          result.append("Seratus ");
        }
        else if (!tiga.equals("0")) {
          result.append(getNumberToTextIndonesia(tiga)).append(" Ratus ");

        }
        String dua = first.substring(1, 2);

        if (dua.equals("0")) {
          if (first.substring(2, 3).equals("0") && !tiga.equals("0")) {
            result.append("Ribu ");
          }
          else if (!first.substring(2, 3).equals("0") &&
                   !first.substring(2, 3).equals("1")) {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Ribu ");
          }
          else if (first.substring(2, 3).equals("0") &&
                   first.substring(1, 2).equals("0") &&
                   first.substring(0, 1).equals("0")) {
            ;
          }
          else {
            result.append("Se").append("Ribu ");
          }
        }
        else if (dua.equals("1")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Sepuluh Ribu ");
          }
          else if (first.substring(2, 3).equals("1")) {
            result.append("Sebelas Ribu ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Belas Ribu ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(dua)).append(" Puluh ");

          if (first.substring(2, 3).equals("0")) {
            result.append("Ribu ");
            //else if(tiga.equals("0") && dua.equals("0"))
            //    result.append("Se").append(" Ribu ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Ribu ");
          }
        }
      }
      else if (length == 2) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          if (first.substring(1, 2).equals("0")) {
            result.append("Sepuluh Ribu ");
          }
          else if (first.substring(1, 2).equals("1")) {
            result.append("Sebelas Ribu ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Belas Ribu ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(tiga)).append(" Puluh ");

          if (first.substring(1, 2).equals("0")) {
            result.append("Ribu ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Ribu ");
          }
        }
      }
      else if (length == 1) {
        if (first.substring(0, 1).equals("1")) {
          result.append(" Seribu ");
        }
        else {
          result.append(getNumberToTextIndonesia(first.substring(0, 1))).append(
              " Ribu ");
        }
      }
    }

    // Untuk Satuan
    if (i >= 1) {
      String firstman = ps[i - 1];
      String first = "";
      int titik = firstman.indexOf('.');

      if (titik > 0) {
        first = firstman.substring(0, titik);
      }

      int length = first.length();

      if (length == 3) {
        String tiga = first.substring(0, 1);
        if (tiga.equals("1")) {
          result.append("Seratus ");
        }
        else if (!tiga.equals("0")) {
          result.append(getNumberToTextIndonesia(tiga)).append(" Ratus ");

        }
        String dua = first.substring(1, 2);

        if (dua.equals("0")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" ");
          }
        }
        else if (dua.equals("1")) {
          if (first.substring(2, 3).equals("0")) {
            result.append("Sepuluh ");
          }
          else if (first.substring(2, 3).equals("1")) {
            result.append("Sebelas ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" Belas ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(dua)).append(" Puluh ");

          if (!first.substring(2, 3).equals("0")) {
            result.append(getNumberToTextIndonesia(first.substring(2, 3))).
                append(" ");
          }
        }
      }
      else if (length == 2) {
        String tiga = first.substring(0, 1);

        if (tiga.equals("1")) {
          if (first.substring(1, 2).equals("0")) {
            result.append("Sepuluh ");
          }
          else if (first.substring(1, 2).equals("1")) {
            result.append("Sebelas ");
          }
          else {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" Belas ");
          }
        }
        else {
          result.append(getNumberToTextIndonesia(tiga)).append(" Puluh ");

          if (!first.substring(1, 2).equals("0")) {
            result.append(getNumberToTextIndonesia(first.substring(1, 2))).
                append(" ");
          }
        }
      }
      else if (length == 1) {
        result.append(getNumberToTextIndonesia(first.substring(0, 1))).append(
            " ");
      }

      String aftertitik = "";

      if (titik > 0) {
        aftertitik = getNumberToTextIndonesia(firstman.substring(titik +
            1));
        result.append("Koma ").append(aftertitik);
      }
    }

    return result.toString();
  }

  public static java.util.Vector getTokenFrfile(String frfile) {
    java.util.Vector vect = new java.util.Vector();

    if (! (frfile == null || frfile.equals(""))) {
      java.util.StringTokenizer token =
          new java.util.StringTokenizer(frfile, "|");

      for (int i = 0; token.hasMoreTokens(); ) {
        vect.addElement(token.nextToken());
        i++;
      }
    }

    return vect;
  }

  public static String getYear(Date date) {
    if (date != null) {
      SimpleDateFormat sdfDateYear = new SimpleDateFormat("yyyy");

      return sdfDateYear.format(date);
    }
    else {
      return null;
    }
  }

  public static java.sql.Date getDateMMDDYYYY(String dateParam){
      java.sql.Date result = null;
      
      StringTokenizer tokenizer = new StringTokenizer(dateParam,"-");
      String dd = tokenizer.nextToken();
      String mm = tokenizer.nextToken();
      String yyyy = tokenizer.nextToken();
      String date = yyyy+"-"+mm+"-"+dd;
      System.out.println("Date Configuration yang mau di convert : " + date);
      result = java.sql.Date.valueOf(date);
      
      return result;
  }
  public static java.util.Date dateResultDDMMYYYY(String parameter) {
    if (! (parameter == null || parameter.equals(""))) {
      java.util.StringTokenizer token =
          new java.util.StringTokenizer(parameter, "/");
      String[] result = new String[3];
      int i = 0;

      for (; token.hasMoreTokens(); ) {
        result[i] = token.nextToken();
        i++;
      }

      java.util.GregorianCalendar startcal = new java.util.GregorianCalendar();
      startcal.clear();
      startcal.set(
          Integer.parseInt(result[2]),
          Integer.parseInt(result[1]) - 1,
          Integer.parseInt(result[0]));

      return new java.sql.Date(startcal.getTime().getTime());
    }
    else {
      return null;
    }
  }

  public static java.sql.Date dateResultDDMMYYYY2(String parameter) throws
      NumberFormatException {
    if (! (parameter == null || parameter.equals(""))) {
      String[] result = new String[3];
      result[0] = parameter.substring(0, 2);
      result[1] = parameter.substring(2, 4);
      result[2] = parameter.substring(4);

      java.util.GregorianCalendar startcal =
          new java.util.GregorianCalendar();
      startcal.clear();
      startcal.set(
          Integer.parseInt(result[2]),
          Integer.parseInt(result[1]) - 1,
          Integer.parseInt(result[0]));

      return new java.sql.Date(startcal.getTime().getTime());
    }
    else {
      return null;
    }
  }

  public static java.sql.Date dateResultHHMI(String jammenit) {
    if (! (jammenit == null || jammenit.equals(""))) {
      java.util.StringTokenizer token =
          new java.util.StringTokenizer(jammenit, ":");
      String[] result = new String[2];
      int i = 0;

      for (; token.hasMoreTokens(); ) {
        result[i] = token.nextToken();
        i++;
      }

      java.util.GregorianCalendar startcal =
          new java.util.GregorianCalendar();
      startcal.setTime(new java.util.Date());
      startcal.set(Calendar.HOUR, Integer.parseInt(result[0]));
      startcal.set(Calendar.MINUTE, Integer.parseInt(result[1]));

      return new java.sql.Date(startcal.getTime().getTime());
    }
    else {
      return null;
    }
  }

  public static java.sql.Date dateResultHHMI2(String jammenit) throws
      NumberFormatException {
    if (! (jammenit == null || jammenit.equals(""))) {
      String[] result = new String[2];
      result[0] = jammenit.substring(0, 2);
      result[1] = jammenit.substring(2, 4);

      java.util.GregorianCalendar startcal =
          new java.util.GregorianCalendar();
      startcal.setTime(new java.util.Date());
      startcal.set(Calendar.HOUR, Integer.parseInt(result[0]));
      startcal.set(Calendar.MINUTE, Integer.parseInt(result[1]));

      return new java.sql.Date(startcal.getTime().getTime());
    }
    else {
      return null;
    }
  }

  public static java.sql.Date dateResultMonth(int parameter) {
    java.util.GregorianCalendar startcal =
        new java.util.GregorianCalendar();
    startcal.clear();
    startcal.set(Calendar.MONTH, parameter);

    return new java.sql.Date(startcal.getTime().getTime());
  }

  public static void main(String[] arg) {
   // System.out.println(getSayMoneyInIndonesia(Double.parseDouble("2340104000")));
//    System.out.println(getFormatDecimalUnusualy(Double.parseDouble("2000.90")));
  }

  public static String[] tokenDate(String parameter) {
    String[] result = new String[3];
    int i = 0;
    java.util.StringTokenizer token =
        new java.util.StringTokenizer(parameter, "/");

    for (; token.hasMoreTokens(); ) {
      result[i] = token.nextToken();
      i++;
    }

    return result;
  }
}
