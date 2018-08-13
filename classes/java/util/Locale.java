/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.util;

import java.io.Serializable;

public final class Locale implements Cloneable, Serializable {

  private static final long serialVersionUID = 9149081749638150636L;

  public static final Locale ENGLISH = new Locale("en");
  public static final Locale US = new Locale("en", "US");
  public static final Locale UK = new Locale("en", "GB");
  public static final Locale CANADA = new Locale("en", "CA");

  public static final Locale FRENCH = new Locale("fr");
  public static final Locale FRANCE = new Locale("fr", "FR");
  public static final Locale CANADA_FRENCH = new Locale("fr", "CA");

  public static final Locale GERMAN = new Locale("de");
  public static final Locale GERMANY = new Locale("de", "DE");

  public static final Locale ITALIAN = new Locale("it");
  public static final Locale ITALY = new Locale("it", "IT");

  public static final Locale JAPANESE = new Locale("ja");
  public static final Locale JAPAN = new Locale("ja", "JP");

  public static final Locale KOREAN = new Locale("ko");
  public static final Locale KOREA = new Locale("ko", "KR");

  public static final Locale CHINESE = new Locale("zh");
  public static final Locale SIMPLIFIED_CHINESE = new Locale("zh", "CN");
  public static final Locale CHINA = SIMPLIFIED_CHINESE;
  public static final Locale PRC = SIMPLIFIED_CHINESE;
  public static final Locale TRADITIONAL_CHINESE = new Locale("zh", "TW");
  public static final Locale TAIWAN = TRADITIONAL_CHINESE;

  static {
    String language = System.getProperty("user.language");
    String region = System.getProperty("user.region");
    String country = region;
    String variant = "";
    int index = region.indexOf('_');
    if (index != -1) {
      country = region.substring(0, index);
      variant = region.substring(index+1);
    }
    setDefault(new Locale(language, country, variant));
  }

  private static Locale locale;

  public static synchronized void setDefault(Locale locale) {
    if (locale == null)
      throw new NullPointerException();
    SecurityManager sm = System.getSecurityManager();
    if (sm != null)
      sm.checkPropertyWriteAccess("user.language");
    Locale.locale = locale;
  }

  public static synchronized Locale getDefault() {
    return locale;
  }

  private static String triplet(String language, String country, String variant) {
    language = language.toLowerCase(US);
    country = country.toUpperCase(US);
    variant = variant.toUpperCase(US);
    if (language.indexOf('_') != -1)
      throw new IllegalArgumentException(language);
    if (country.indexOf('_') != -1)
      throw new IllegalArgumentException(country);
    StringBuffer sb = new StringBuffer();
    sb.append(language);
    if (country.length() != 0) {
      sb.append('_');
      sb.append(country);
    }
    if (sb.length() != 0)
      if (variant.length() != 0) {
        if (country.length() == 0)
          sb.append('_');
        sb.append('_');
        sb.append(variant);
      }
    return sb.toString();
  }

  public static Locale[] getAvailableLocales() {
    throw new InternalError("Unimplemented");
  }

  public static String[] getISOLanguages() {
    return new String[] {
      "aa", "ab", "af", "am", "ar", "as", "ay", "az", "ba", "be",
      "bg", "bh", "bi", "bn", "bo", "br", "ca", "co", "cs", "cy",
      "da", "de", "dz", "el", "en", "eo", "es", "et", "eu", "fa",
      "fi", "fj", "fo", "fr", "fy", "ga", "gd", "gl", "gn", "gu",
      "ha", "he", "hi", "hr", "hu", "hy", "ia", "id", "ie", "ik",
      "in", "is", "it", "iu", "iw", "ja", "ji", "jw", "ka", "kk",
      "kl", "km", "kn", "ko", "ks", "ku", "ky", "la", "ln", "lo",
      "lt", "lv", "mg", "mi", "mk", "ml", "mn", "mo", "mr", "ms",
      "mt", "my", "na", "ne", "nl", "no", "oc", "om", "or", "pa",
      "pl", "ps", "pt", "qu", "rm", "rn", "ro", "ru", "rw", "sa",
      "sd", "sg", "sh", "si", "sk", "sl", "sm", "sn", "so", "sq",
      "sr", "ss", "st", "su", "sv", "sw", "ta", "te", "tg", "th",
      "ti", "tk", "tl", "tn", "to", "tr", "ts", "tt", "tw", "ug",
      "uk", "ur", "uz", "vi", "vo", "wo", "xh", "yi", "yo", "za",
      "zh", "zu",
    };
  }
  
  public static String[] getISOCountries() {
    return new String[] {
      "AD", "AE", "AF", "AG", "AI", "AL", "AM", "AN", "AO", "AQ",
      "AR", "AS", "AT", "AU", "AW", "AZ", "BA", "BB", "BD", "BE",
      "BF", "BG", "BH", "BI", "BJ", "BM", "BN", "BO", "BR", "BS",
      "BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CF", "CG", "CH",
      "CI", "CK", "CL", "CM", "CN", "CO", "CR", "CU", "CV", "CX",
      "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE",
      "EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO",
      "FR", "FX", "GA", "GB", "GD", "GE", "GF", "GH", "GI", "GL",
      "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY",
      "HK", "HM", "HN", "HR", "HT", "HU", "ID", "IE", "IL", "IN",
      "IO", "IQ", "IR", "IS", "IT", "JM", "JO", "JP", "KE", "KG",
      "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA",
      "LB", "LC", "LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY",
      "MA", "MC", "MD", "MG", "MH", "MK", "ML", "MM", "MN", "MO",
      "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY",
      "MZ", "NA", "NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP",
      "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG", "PH", "PK",
      "PL", "PM", "PN", "PR", "PT", "PW", "PY", "QA", "RE", "RO",
      "RU", "RW", "SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI",
      "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "ST", "SV", "SY",
      "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TM", "TN",
      "TO", "TP", "TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM",
      "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI", "VN", "VU",
      "WF", "WS", "YE", "YT", "YU", "ZA", "ZM", "ZR", "ZW",
    };
  }

  private final String triplet;

  public Locale(String language) {
    this(language, "", "");
  }

  public Locale(String language, String country) {
    this(language, country, "");
  }

  public Locale(String language, String country, String variant) {
    this.triplet = triplet(language, country, variant);
  }

  public String getLanguage() {
    int index = triplet.indexOf('_');
    if (index == -1)
      return triplet;
    return triplet.substring(0, index);
  }

  public String getCountry() {
    int index1 = triplet.indexOf('_');
    if (index1 == -1)
      return "";
    int index2 = triplet.indexOf(index1+1, '_');
    if (index2 == -1)
      return triplet.substring(index1+1);
    return triplet.substring(index1+1, index2);
  }

  public String getVariant() {
    int index1 = triplet.indexOf('_');
    if (index1 == -1)
      return "";
    int index2 = triplet.indexOf(index1+1, '_');
    if (index2 == -1)
      return "";
    return triplet.substring(index2+1);
  }

  public String getISO3Language() throws MissingResourceException {
    String language = getLanguage();
    switch (language.length()) {
    case 0: return  "";
    case 2:
      switch (language.charAt(0)<<16|language.charAt(1)) {
      case 'a'<<16|'a': return "aar"; case 'a'<<16|'b': return "abk"; case 'a'<<16|'f': return "afr";
      case 'a'<<16|'m': return "amh"; case 'a'<<16|'r': return "ara"; case 'a'<<16|'s': return "asm";
      case 'a'<<16|'y': return "aym"; case 'a'<<16|'z': return "aze"; case 'b'<<16|'a': return "bak";
      case 'b'<<16|'e': return "bel"; case 'b'<<16|'g': return "bul"; case 'b'<<16|'h': return "bih";
      case 'b'<<16|'i': return "bis"; case 'b'<<16|'n': return "ben"; case 'b'<<16|'o': return "bod";
      case 'b'<<16|'r': return "bre"; case 'c'<<16|'a': return "cat"; case 'c'<<16|'o': return "cos";
      case 'c'<<16|'s': return "ces"; case 'c'<<16|'y': return "cym"; case 'd'<<16|'a': return "dan";
      case 'd'<<16|'e': return "deu"; case 'd'<<16|'z': return "dzo"; case 'e'<<16|'l': return "ell";
      case 'e'<<16|'n': return "eng"; case 'e'<<16|'o': return "epo"; case 'e'<<16|'s': return "spa";
      case 'e'<<16|'t': return "est"; case 'e'<<16|'u': return "eus"; case 'f'<<16|'a': return "fas";
      case 'f'<<16|'i': return "fin"; case 'f'<<16|'j': return "fij"; case 'f'<<16|'o': return "fao";
      case 'f'<<16|'r': return "fra"; case 'f'<<16|'y': return "fry"; case 'g'<<16|'a': return "gai";
      case 'g'<<16|'d': return "gdh"; case 'g'<<16|'l': return "glg"; case 'g'<<16|'n': return "grn";
      case 'g'<<16|'u': return "guj"; case 'h'<<16|'a': return "hau"; case 'h'<<16|'e': return "heb";
      case 'h'<<16|'i': return "hin"; case 'h'<<16|'r': return "hrv"; case 'h'<<16|'u': return "hun";
      case 'h'<<16|'y': return "hye"; case 'i'<<16|'a': return "ina"; case 'i'<<16|'d': return "ind";
      case 'i'<<16|'e': return "ile"; case 'i'<<16|'k': return "ipk"; case 'i'<<16|'n': return "ind";
      case 'i'<<16|'s': return "isl"; case 'i'<<16|'t': return "ita"; case 'i'<<16|'u': return "iku";
      case 'i'<<16|'w': return "heb"; case 'j'<<16|'a': return "jpn"; case 'j'<<16|'i': return "yid";
      case 'j'<<16|'w': return "jaw"; case 'k'<<16|'a': return "kat"; case 'k'<<16|'k': return "kaz";
      case 'k'<<16|'l': return "kal"; case 'k'<<16|'m': return "khm"; case 'k'<<16|'n': return "kan";
      case 'k'<<16|'o': return "kor"; case 'k'<<16|'s': return "kas"; case 'k'<<16|'u': return "kur";
      case 'k'<<16|'y': return "kir"; case 'l'<<16|'a': return "lat"; case 'l'<<16|'n': return "lin";
      case 'l'<<16|'o': return "lao"; case 'l'<<16|'t': return "lit"; case 'l'<<16|'v': return "lav";
      case 'm'<<16|'g': return "mlg"; case 'm'<<16|'i': return "mri"; case 'm'<<16|'k': return "mkd";
      case 'm'<<16|'l': return "mal"; case 'm'<<16|'n': return "mon"; case 'm'<<16|'o': return "mol";
      case 'm'<<16|'r': return "mar"; case 'm'<<16|'s': return "msa"; case 'm'<<16|'t': return "mlt";
      case 'm'<<16|'y': return "mya"; case 'n'<<16|'a': return "nau"; case 'n'<<16|'e': return "nep";
      case 'n'<<16|'l': return "nld"; case 'n'<<16|'o': return "nor"; case 'o'<<16|'c': return "oci";
      case 'o'<<16|'m': return "orm"; case 'o'<<16|'r': return "ori"; case 'p'<<16|'a': return "pan";
      case 'p'<<16|'l': return "pol"; case 'p'<<16|'s': return "pus"; case 'p'<<16|'t': return "por";
      case 'q'<<16|'u': return "que"; case 'r'<<16|'m': return "roh"; case 'r'<<16|'n': return "run";
      case 'r'<<16|'o': return "ron"; case 'r'<<16|'u': return "rus"; case 'r'<<16|'w': return "kin";
      case 's'<<16|'a': return "san"; case 's'<<16|'d': return "snd"; case 's'<<16|'g': return "sag";
      case 's'<<16|'h': return "srp"; case 's'<<16|'i': return "sin"; case 's'<<16|'k': return "slk";
      case 's'<<16|'l': return "slv"; case 's'<<16|'m': return "smo"; case 's'<<16|'n': return "sna";
      case 's'<<16|'o': return "som"; case 's'<<16|'q': return "sqi"; case 's'<<16|'r': return "srp";
      case 's'<<16|'s': return "ssw"; case 's'<<16|'t': return "sot"; case 's'<<16|'u': return "sun";
      case 's'<<16|'v': return "swe"; case 's'<<16|'w': return "swa"; case 't'<<16|'a': return "tam";
      case 't'<<16|'e': return "tel"; case 't'<<16|'g': return "tgk"; case 't'<<16|'h': return "tha";
      case 't'<<16|'i': return "tir"; case 't'<<16|'k': return "tuk"; case 't'<<16|'l': return "tgl";
      case 't'<<16|'n': return "tsn"; case 't'<<16|'o': return "ton"; case 't'<<16|'r': return "tur";
      case 't'<<16|'s': return "tso"; case 't'<<16|'t': return "tat"; case 't'<<16|'w': return "twi";
      case 'u'<<16|'g': return "uig"; case 'u'<<16|'k': return "ukr"; case 'u'<<16|'r': return "urd";
      case 'u'<<16|'z': return "uzb"; case 'v'<<16|'i': return "vie"; case 'v'<<16|'o': return "vol";
      case 'w'<<16|'o': return "wol"; case 'x'<<16|'h': return "xho"; case 'y'<<16|'i': return "yid";
      case 'y'<<16|'o': return "yor"; case 'z'<<16|'a': return "zha"; case 'z'<<16|'h': return "zho";
      case 'z'<<16|'u': return "zul";
      }
    }
    throw new MissingResourceException("Cannot find ISO3 language code: "+language, triplet, language);
  }

  public String getISO3Country() throws MissingResourceException {
    String country = getCountry();
    switch (country.length()) {
    case 0: return  "";
    case 2:
      switch (country.charAt(0)<<16|country.charAt(1)) {
      case 'A'<<16|'D': return "AND"; case 'A'<<16|'E': return "ARE"; case 'A'<<16|'F': return "AFG";
      case 'A'<<16|'G': return "ATG"; case 'A'<<16|'I': return "AIA"; case 'A'<<16|'L': return "ALB";
      case 'A'<<16|'M': return "ARM"; case 'A'<<16|'N': return "ANT"; case 'A'<<16|'O': return "AGO";
      case 'A'<<16|'Q': return "ATA"; case 'A'<<16|'R': return "ARG"; case 'A'<<16|'S': return "ASM";
      case 'A'<<16|'T': return "AUT"; case 'A'<<16|'U': return "AUS"; case 'A'<<16|'W': return "ABW";
      case 'A'<<16|'Z': return "AZE"; case 'B'<<16|'A': return "BIH"; case 'B'<<16|'B': return "BRB";
      case 'B'<<16|'D': return "BGD"; case 'B'<<16|'E': return "BEL"; case 'B'<<16|'F': return "BFA";
      case 'B'<<16|'G': return "BGR"; case 'B'<<16|'H': return "BHR"; case 'B'<<16|'I': return "BDI";
      case 'B'<<16|'J': return "BEN"; case 'B'<<16|'M': return "BMU"; case 'B'<<16|'N': return "BRN";
      case 'B'<<16|'O': return "BOL"; case 'B'<<16|'R': return "BRA"; case 'B'<<16|'S': return "BHS";
      case 'B'<<16|'T': return "BTN"; case 'B'<<16|'V': return "BVT"; case 'B'<<16|'W': return "BWA";
      case 'B'<<16|'Y': return "BLR"; case 'B'<<16|'Z': return "BLZ"; case 'C'<<16|'A': return "CAN";
      case 'C'<<16|'C': return "CCK"; case 'C'<<16|'F': return "CAF"; case 'C'<<16|'G': return "COG";
      case 'C'<<16|'H': return "CHE"; case 'C'<<16|'I': return "CIV"; case 'C'<<16|'K': return "COK";
      case 'C'<<16|'L': return "CHL"; case 'C'<<16|'M': return "CMR"; case 'C'<<16|'N': return "CHN";
      case 'C'<<16|'O': return "COL"; case 'C'<<16|'R': return "CRI"; case 'C'<<16|'U': return "CUB";
      case 'C'<<16|'V': return "CPV"; case 'C'<<16|'X': return "CXR"; case 'C'<<16|'Y': return "CYP";
      case 'C'<<16|'Z': return "CZE"; case 'D'<<16|'E': return "DEU"; case 'D'<<16|'J': return "DJI";
      case 'D'<<16|'K': return "DNK"; case 'D'<<16|'M': return "DMA"; case 'D'<<16|'O': return "DOM";
      case 'D'<<16|'Z': return "DZA"; case 'E'<<16|'C': return "ECU"; case 'E'<<16|'E': return "EST";
      case 'E'<<16|'G': return "EGY"; case 'E'<<16|'H': return "ESH"; case 'E'<<16|'R': return "ERI";
      case 'E'<<16|'S': return "ESP"; case 'E'<<16|'T': return "ETH"; case 'F'<<16|'I': return "FIN";
      case 'F'<<16|'J': return "FJI"; case 'F'<<16|'K': return "FLK"; case 'F'<<16|'M': return "FSM";
      case 'F'<<16|'O': return "FRO"; case 'F'<<16|'R': return "FRA"; case 'F'<<16|'X': return "FXX";
      case 'G'<<16|'A': return "GAB"; case 'G'<<16|'B': return "GBR"; case 'G'<<16|'D': return "GRD";
      case 'G'<<16|'E': return "GEO"; case 'G'<<16|'F': return "GUF"; case 'G'<<16|'H': return "GHA";
      case 'G'<<16|'I': return "GIB"; case 'G'<<16|'L': return "GRL"; case 'G'<<16|'M': return "GMB";
      case 'G'<<16|'N': return "GIN"; case 'G'<<16|'P': return "GLP"; case 'G'<<16|'Q': return "GNQ";
      case 'G'<<16|'R': return "GRC"; case 'G'<<16|'S': return "SGS"; case 'G'<<16|'T': return "GTM";
      case 'G'<<16|'U': return "GUM"; case 'G'<<16|'W': return "GNB"; case 'G'<<16|'Y': return "GUY";
      case 'H'<<16|'K': return "HKG"; case 'H'<<16|'M': return "HMD"; case 'H'<<16|'N': return "HND";
      case 'H'<<16|'R': return "HRV"; case 'H'<<16|'T': return "HTI"; case 'H'<<16|'U': return "HUN";
      case 'I'<<16|'D': return "IDN"; case 'I'<<16|'E': return "IRL"; case 'I'<<16|'L': return "ISR";
      case 'I'<<16|'N': return "IND"; case 'I'<<16|'O': return "IOT"; case 'I'<<16|'Q': return "IRQ";
      case 'I'<<16|'R': return "IRN"; case 'I'<<16|'S': return "ISL"; case 'I'<<16|'T': return "ITA";
      case 'J'<<16|'M': return "JAM"; case 'J'<<16|'O': return "JOR"; case 'J'<<16|'P': return "JPN";
      case 'K'<<16|'E': return "KEN"; case 'K'<<16|'G': return "KGZ"; case 'K'<<16|'H': return "KHM";
      case 'K'<<16|'I': return "KIR"; case 'K'<<16|'M': return "COM"; case 'K'<<16|'N': return "KNA";
      case 'K'<<16|'P': return "PRK"; case 'K'<<16|'R': return "KOR"; case 'K'<<16|'W': return "KWT";
      case 'K'<<16|'Y': return "CYM"; case 'K'<<16|'Z': return "KAZ"; case 'L'<<16|'A': return "LAO";
      case 'L'<<16|'B': return "LBN"; case 'L'<<16|'C': return "LCA"; case 'L'<<16|'I': return "LIE";
      case 'L'<<16|'K': return "LKA"; case 'L'<<16|'R': return "LBR"; case 'L'<<16|'S': return "LSO";
      case 'L'<<16|'T': return "LTU"; case 'L'<<16|'U': return "LUX"; case 'L'<<16|'V': return "LVA";
      case 'L'<<16|'Y': return "LBY"; case 'M'<<16|'A': return "MAR"; case 'M'<<16|'C': return "MCO";
      case 'M'<<16|'D': return "MDA"; case 'M'<<16|'G': return "MDG"; case 'M'<<16|'H': return "MHL";
      case 'M'<<16|'K': return "MKD"; case 'M'<<16|'L': return "MLI"; case 'M'<<16|'M': return "MMR";
      case 'M'<<16|'N': return "MNG"; case 'M'<<16|'O': return "MAC"; case 'M'<<16|'P': return "MNP";
      case 'M'<<16|'Q': return "MTQ"; case 'M'<<16|'R': return "MRT"; case 'M'<<16|'S': return "MSR";
      case 'M'<<16|'T': return "MLT"; case 'M'<<16|'U': return "MUS"; case 'M'<<16|'V': return "MDV";
      case 'M'<<16|'W': return "MWI"; case 'M'<<16|'X': return "MEX"; case 'M'<<16|'Y': return "MYS";
      case 'M'<<16|'Z': return "MOZ"; case 'N'<<16|'A': return "NAM"; case 'N'<<16|'C': return "NCL";
      case 'N'<<16|'E': return "NER"; case 'N'<<16|'F': return "NFK"; case 'N'<<16|'G': return "NGA";
      case 'N'<<16|'I': return "NIC"; case 'N'<<16|'L': return "NLD"; case 'N'<<16|'O': return "NOR";
      case 'N'<<16|'P': return "NPL"; case 'N'<<16|'R': return "NRU"; case 'N'<<16|'U': return "NIU";
      case 'N'<<16|'Z': return "NZL"; case 'O'<<16|'M': return "OMN"; case 'P'<<16|'A': return "PAN";
      case 'P'<<16|'E': return "PER"; case 'P'<<16|'F': return "PYF"; case 'P'<<16|'G': return "PNG";
      case 'P'<<16|'H': return "PHL"; case 'P'<<16|'K': return "PAK"; case 'P'<<16|'L': return "POL";
      case 'P'<<16|'M': return "SPM"; case 'P'<<16|'N': return "PCN"; case 'P'<<16|'R': return "PRI";
      case 'P'<<16|'T': return "PRT"; case 'P'<<16|'W': return "PLW"; case 'P'<<16|'Y': return "PRY";
      case 'Q'<<16|'A': return "QAT"; case 'R'<<16|'E': return "REU"; case 'R'<<16|'O': return "ROM";
      case 'R'<<16|'U': return "RUS"; case 'R'<<16|'W': return "RWA"; case 'S'<<16|'A': return "SAU";
      case 'S'<<16|'B': return "SLB"; case 'S'<<16|'C': return "SYC"; case 'S'<<16|'D': return "SDN";
      case 'S'<<16|'E': return "SWE"; case 'S'<<16|'G': return "SGP"; case 'S'<<16|'H': return "SHN";
      case 'S'<<16|'I': return "SVN"; case 'S'<<16|'J': return "SJM"; case 'S'<<16|'K': return "SVK";
      case 'S'<<16|'L': return "SLE"; case 'S'<<16|'M': return "SMR"; case 'S'<<16|'N': return "SEN";
      case 'S'<<16|'O': return "SOM"; case 'S'<<16|'R': return "SUR"; case 'S'<<16|'T': return "STP";
      case 'S'<<16|'V': return "SLV"; case 'S'<<16|'Y': return "SYR"; case 'S'<<16|'Z': return "SWZ";
      case 'T'<<16|'C': return "TCA"; case 'T'<<16|'D': return "TCD"; case 'T'<<16|'F': return "ATF";
      case 'T'<<16|'G': return "TGO"; case 'T'<<16|'H': return "THA"; case 'T'<<16|'J': return "TJK";
      case 'T'<<16|'K': return "TKL"; case 'T'<<16|'M': return "TKM"; case 'T'<<16|'N': return "TUN";
      case 'T'<<16|'O': return "TON"; case 'T'<<16|'P': return "TMP"; case 'T'<<16|'R': return "TUR";
      case 'T'<<16|'T': return "TTO"; case 'T'<<16|'V': return "TUV"; case 'T'<<16|'W': return "TWN";
      case 'T'<<16|'Z': return "TZA"; case 'U'<<16|'A': return "UKR"; case 'U'<<16|'G': return "UGA";
      case 'U'<<16|'M': return "UMI"; case 'U'<<16|'S': return "USA"; case 'U'<<16|'Y': return "URY";
      case 'U'<<16|'Z': return "UZB"; case 'V'<<16|'A': return "VAT"; case 'V'<<16|'C': return "VCT";
      case 'V'<<16|'E': return "VEN"; case 'V'<<16|'G': return "VGB"; case 'V'<<16|'I': return "VIR";
      case 'V'<<16|'N': return "VNM"; case 'V'<<16|'U': return "VUT"; case 'W'<<16|'F': return "WLF";
      case 'W'<<16|'S': return "WSM"; case 'Y'<<16|'E': return "YEM"; case 'Y'<<16|'T': return "MYT";
      case 'Y'<<16|'U': return "YUG"; case 'Z'<<16|'A': return "ZAF"; case 'Z'<<16|'M': return "ZMB";
      case 'Z'<<16|'R': return "ZAR"; case 'Z'<<16|'W': return "ZWE";
      }
    }
    throw new MissingResourceException("Cannot find ISO3 country code: "+country, triplet, country);
  }

  public String getDisplayLanguage() {
    return getDisplayLanguage(getDefault());
  }

  public String getDisplayLanguage(Locale locale) {
    throw new InternalError("Unimplemented");
  }

  public String getDisplayCountry() {
    return getDisplayCountry(getDefault());
  }

  public String getDisplayCountry(Locale locale) {
    throw new InternalError("Unimplemented");
  }

  public String getDisplayVariant() {
    return getDisplayVariant(getDefault());
  }

  public String getDisplayVariant(Locale locale) {
    throw new InternalError("Unimplemented");
  }

  public String getDisplayName() {
    return getDisplayName(getDefault());
  }

  public String getDisplayName(Locale locale) {
    throw new InternalError("Unimplemented");
  }

  public Object clone() {
    try {
      return (Locale)super.clone();
    } catch (CloneNotSupportedException e) {
      throw new InternalError(e.getMessage());
    }
  }

  public int hashCode() {
    return triplet.hashCode();
  }

  public boolean equals(Object object) {
    return object instanceof Locale
        && ((Locale)object).triplet.equals(triplet);
  }

  public String toString() {
    return triplet;
  }

}

