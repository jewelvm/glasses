/* GLASSES, Generic cLASSES
 * Copyright (c) 1998-2004, Rodrigo Augusto Barbato Ferreira
 */
package java.lang;

import java.io.Serializable;

public final class Character implements Comparable, Serializable {

  public static final Class TYPE = Class.getPrimitiveClass("char");

  public static final int MIN_RADIX = 2;
  public static final int MAX_RADIX = 36;
  public static final char MIN_VALUE = '\u0000';
  public static final char MAX_VALUE = '\uFFFF';

  public static final byte UNASSIGNED = 0;
  public static final byte UPPERCASE_LETTER = 1;
  public static final byte LOWERCASE_LETTER = 2;
  public static final byte TITLECASE_LETTER = 3;
  public static final byte MODIFIER_LETTER = 4;
  public static final byte OTHER_LETTER = 5;
  public static final byte NON_SPACING_MARK = 6;
  public static final byte ENCLOSING_MARK = 7;
  public static final byte COMBINING_SPACING_MARK = 8;
  public static final byte DECIMAL_DIGIT_NUMBER = 9;
  public static final byte LETTER_NUMBER = 10;
  public static final byte OTHER_NUMBER = 11;
  public static final byte SPACE_SEPARATOR = 12;
  public static final byte LINE_SEPARATOR = 13;
  public static final byte PARAGRAPH_SEPARATOR = 14;
  public static final byte CONTROL = 15;
  public static final byte FORMAT = 16;
  public static final byte PRIVATE_USE = 18;
  public static final byte SURROGATE = 19;
  public static final byte DASH_PUNCTUATION = 20;
  public static final byte START_PUNCTUATION = 21;
  public static final byte END_PUNCTUATION = 22;
  public static final byte CONNECTOR_PUNCTUATION = 23;
  public static final byte OTHER_PUNCTUATION = 24;
  public static final byte MATH_SYMBOL = 25;
  public static final byte CURRENCY_SYMBOL = 26;
  public static final byte MODIFIER_SYMBOL = 27;
  public static final byte OTHER_SYMBOL = 28;
  public static final byte INITIAL_QUOTE_PUNCTUATION = 29;
  public static final byte FINAL_QUOTE_PUNCTUATION = 30;
  
  public static final byte DIRECTIONALITY_UNDEFINED = -1;
  public static final byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;
  public static final byte DIRECTIONALITY_EUROPEAN_NUMBER = 3;
  public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR = 4;
  public static final byte DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR = 5;
  public static final byte DIRECTIONALITY_ARABIC_NUMBER = 6;
  public static final byte DIRECTIONALITY_COMMON_NUMBER_SEPARATOR = 7;
  public static final byte DIRECTIONALITY_NONSPACING_MARK = 8;
  public static final byte DIRECTIONALITY_BOUNDARY_NEUTRAL = 9;
  public static final byte DIRECTIONALITY_PARAGRAPH_SEPARATOR = 10;
  public static final byte DIRECTIONALITY_SEGMENT_SEPARATOR = 11;
  public static final byte DIRECTIONALITY_WHITESPACE = 12;
  public static final byte DIRECTIONALITY_OTHER_NEUTRALS = 13;
  public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING = 14;
  public static final byte DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE = 15;
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING = 16;
  public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE = 17;
  public static final byte DIRECTIONALITY_POP_DIRECTIONAL_FORMAT = 18;
  
  public static int getType(char shar) {
    if ('\u0000' <= shar && shar <= '\u001F')
      return CONTROL;
    else  if (shar == '\u0020')
      return SPACE_SEPARATOR;
    else  if ('\u0021' <= shar && shar <= '\u0023')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u0024')
      return CURRENCY_SYMBOL;
    else  if ('\u0025' <= shar && shar <= '\'')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u0028')
      return START_PUNCTUATION;
    else  if (shar == '\u0029')
      return END_PUNCTUATION;
    else  if (shar == '\u002A')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u002B')
      return MATH_SYMBOL;
    else  if (shar == '\u002C')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u002D')
      return DASH_PUNCTUATION;
    else  if ('\u002E' <= shar && shar <= '\u002F')
      return OTHER_PUNCTUATION;
    else  if ('\u0030' <= shar && shar <= '\u0039')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u003A' <= shar && shar <= '\u003B')
      return OTHER_PUNCTUATION;
    else  if ('\u003C' <= shar && shar <= '\u003E')
      return MATH_SYMBOL;
    else  if ('\u003F' <= shar && shar <= '\u0040')
      return OTHER_PUNCTUATION;
    else  if ('\u0041' <= shar && shar <= '\u005A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u005B')
      return START_PUNCTUATION;
    else  if (shar == '\\')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u005D')
      return END_PUNCTUATION;
    else  if (shar == '\u005E')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u005F')
      return CONNECTOR_PUNCTUATION;
    else  if (shar == '\u0060')
      return MODIFIER_SYMBOL;
    else  if ('\u0061' <= shar && shar <= '\u007A')
      return LOWERCASE_LETTER;
    else  if (shar == '\u007B')
      return START_PUNCTUATION;
    else  if (shar == '\u007C')
      return MATH_SYMBOL;
    else  if (shar == '\u007D')
      return END_PUNCTUATION;
    else  if (shar == '\u007E')
      return MATH_SYMBOL;
    else  if ('\u007F' <= shar && shar <= '\u009F')
      return CONTROL;
    else  if (shar == '\u00A0')
      return SPACE_SEPARATOR;
    else  if (shar == '\u00A1')
      return OTHER_PUNCTUATION;
    else  if ('\u00A2' <= shar && shar <= '\u00A5')
      return CURRENCY_SYMBOL;
    else  if ('\u00A6' <= shar && shar <= '\u00A7')
      return OTHER_SYMBOL;
    else  if (shar == '\u00A8')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u00A9')
      return OTHER_SYMBOL;
    else  if (shar == '\u00AA')
      return LOWERCASE_LETTER;
    else  if (shar == '\u00AB')
      return START_PUNCTUATION;
    else  if (shar == '\u00AC')
      return MATH_SYMBOL;
    else  if (shar == '\u00AD')
      return DASH_PUNCTUATION;
    else  if (shar == '\u00AE')
      return OTHER_SYMBOL;
    else  if (shar == '\u00AF')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u00B0')
      return OTHER_SYMBOL;
    else  if (shar == '\u00B1')
      return MATH_SYMBOL;
    else  if ('\u00B2' <= shar && shar <= '\u00B3')
      return OTHER_NUMBER;
    else  if (shar == '\u00B4')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u00B5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u00B6')
      return OTHER_SYMBOL;
    else  if (shar == '\u00B7')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u00B8')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u00B9')
      return OTHER_NUMBER;
    else  if (shar == '\u00BA')
      return LOWERCASE_LETTER;
    else  if (shar == '\u00BB')
      return END_PUNCTUATION;
    else  if ('\u00BC' <= shar && shar <= '\u00BE')
      return OTHER_NUMBER;
    else  if (shar == '\u00BF')
      return OTHER_PUNCTUATION;
    else  if ('\u00C0' <= shar && shar <= '\u00D6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u00D7')
      return MATH_SYMBOL;
    else  if ('\u00D8' <= shar && shar <= '\u00DE')
      return UPPERCASE_LETTER;
    else  if ('\u00DF' <= shar && shar <= '\u00F6')
      return LOWERCASE_LETTER;
    else  if (shar == '\u00F7')
      return MATH_SYMBOL;
    else  if ('\u00F8' <= shar && shar <= '\u00FF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0100')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0101')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0102')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0103')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0104')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0105')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0106')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0107')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0108')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0109')
      return LOWERCASE_LETTER;
    else  if (shar == '\u010A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u010B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u010C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u010D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u010E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u010F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0110')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0111')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0112')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0113')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0114')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0115')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0116')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0117')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0118')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0119')
      return LOWERCASE_LETTER;
    else  if (shar == '\u011A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u011B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u011C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u011D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u011E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u011F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0120')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0121')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0122')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0123')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0124')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0125')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0126')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0127')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0128')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0129')
      return LOWERCASE_LETTER;
    else  if (shar == '\u012A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u012B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u012C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u012D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u012E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u012F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0130')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0131')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0132')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0133')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0134')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0135')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0136')
      return UPPERCASE_LETTER;
    else  if ('\u0137' <= shar && shar <= '\u0138')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0139')
      return UPPERCASE_LETTER;
    else  if (shar == '\u013A')
      return LOWERCASE_LETTER;
    else  if (shar == '\u013B')
      return UPPERCASE_LETTER;
    else  if (shar == '\u013C')
      return LOWERCASE_LETTER;
    else  if (shar == '\u013D')
      return UPPERCASE_LETTER;
    else  if (shar == '\u013E')
      return LOWERCASE_LETTER;
    else  if (shar == '\u013F')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0140')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0141')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0142')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0143')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0144')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0145')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0146')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0147')
      return UPPERCASE_LETTER;
    else  if ('\u0148' <= shar && shar <= '\u0149')
      return LOWERCASE_LETTER;
    else  if (shar == '\u014A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u014B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u014C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u014D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u014E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u014F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0150')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0151')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0152')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0153')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0154')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0155')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0156')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0157')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0158')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0159')
      return LOWERCASE_LETTER;
    else  if (shar == '\u015A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u015B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u015C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u015D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u015E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u015F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0160')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0161')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0162')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0163')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0164')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0165')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0166')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0167')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0168')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0169')
      return LOWERCASE_LETTER;
    else  if (shar == '\u016A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u016B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u016C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u016D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u016E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u016F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0170')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0171')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0172')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0173')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0174')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0175')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0176')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0177')
      return LOWERCASE_LETTER;
    else  if ('\u0178' <= shar && shar <= '\u0179')
      return UPPERCASE_LETTER;
    else  if (shar == '\u017A')
      return LOWERCASE_LETTER;
    else  if (shar == '\u017B')
      return UPPERCASE_LETTER;
    else  if (shar == '\u017C')
      return LOWERCASE_LETTER;
    else  if (shar == '\u017D')
      return UPPERCASE_LETTER;
    else  if ('\u017E' <= shar && shar <= '\u0180')
      return LOWERCASE_LETTER;
    else  if ('\u0181' <= shar && shar <= '\u0182')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0183')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0184')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0185')
      return LOWERCASE_LETTER;
    else  if ('\u0186' <= shar && shar <= '\u0187')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0188')
      return LOWERCASE_LETTER;
    else  if ('\u0189' <= shar && shar <= '\u018B')
      return UPPERCASE_LETTER;
    else  if ('\u018C' <= shar && shar <= '\u018D')
      return LOWERCASE_LETTER;
    else  if ('\u018E' <= shar && shar <= '\u0191')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0192')
      return LOWERCASE_LETTER;
    else  if ('\u0193' <= shar && shar <= '\u0194')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0195')
      return LOWERCASE_LETTER;
    else  if ('\u0196' <= shar && shar <= '\u0198')
      return UPPERCASE_LETTER;
    else  if ('\u0199' <= shar && shar <= '\u019B')
      return LOWERCASE_LETTER;
    else  if ('\u019C' <= shar && shar <= '\u019D')
      return UPPERCASE_LETTER;
    else  if (shar == '\u019E')
      return LOWERCASE_LETTER;
    else  if ('\u019F' <= shar && shar <= '\u01A0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01A1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01A2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01A3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01A4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01A5')
      return LOWERCASE_LETTER;
    else  if ('\u01A6' <= shar && shar <= '\u01A7')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01A8')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01A9')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01AA')
      return OTHER_LETTER;
    else  if (shar == '\u01AB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01AC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01AD')
      return LOWERCASE_LETTER;
    else  if ('\u01AE' <= shar && shar <= '\u01AF')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01B0')
      return LOWERCASE_LETTER;
    else  if ('\u01B1' <= shar && shar <= '\u01B3')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01B4')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01B5')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01B6')
      return LOWERCASE_LETTER;
    else  if ('\u01B7' <= shar && shar <= '\u01B8')
      return UPPERCASE_LETTER;
    else  if ('\u01B9' <= shar && shar <= '\u01BA')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01BB')
      return OTHER_LETTER;
    else  if (shar == '\u01BC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01BD')
      return LOWERCASE_LETTER;
    else  if ('\u01BE' <= shar && shar <= '\u01C3')
      return OTHER_LETTER;
    else  if (shar == '\u01C4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01C5')
      return TITLECASE_LETTER;
    else  if (shar == '\u01C6')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01C7')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01C8')
      return TITLECASE_LETTER;
    else  if (shar == '\u01C9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01CA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01CB')
      return TITLECASE_LETTER;
    else  if (shar == '\u01CC')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01CD')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01CE')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01CF')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01D0')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01D1')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01D2')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01D3')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01D4')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01D5')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01D6')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01D7')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01D8')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01D9')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01DA')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01DB')
      return UPPERCASE_LETTER;
    else  if ('\u01DC' <= shar && shar <= '\u01DD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01DE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01DF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01E0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01E1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01E2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01E3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01E4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01E5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01E6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01E7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01E8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01E9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01EA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01EB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01EC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01ED')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01EE')
      return UPPERCASE_LETTER;
    else  if ('\u01EF' <= shar && shar <= '\u01F0')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01F1')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01F2')
      return TITLECASE_LETTER;
    else  if (shar == '\u01F3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01F4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01F5')
      return LOWERCASE_LETTER;
    else  if ('\u01F6' <= shar && shar <= '\u01F9')
      return UNASSIGNED;
    else  if (shar == '\u01FA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01FB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01FC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01FD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u01FE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u01FF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0200')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0201')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0202')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0203')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0204')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0205')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0206')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0207')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0208')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0209')
      return LOWERCASE_LETTER;
    else  if (shar == '\u020A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u020B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u020C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u020D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u020E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u020F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0210')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0211')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0212')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0213')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0214')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0215')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0216')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0217')
      return LOWERCASE_LETTER;
    else  if ('\u0218' <= shar && shar <= '\u024F')
      return UNASSIGNED;
    else  if ('\u0250' <= shar && shar <= '\u02A8')
      return LOWERCASE_LETTER;
    else  if ('\u02A9' <= shar && shar <= '\u02AF')
      return UNASSIGNED;
    else  if ('\u02B0' <= shar && shar <= '\u02B8')
      return MODIFIER_LETTER;
    else  if ('\u02B9' <= shar && shar <= '\u02BA')
      return MODIFIER_SYMBOL;
    else  if ('\u02BB' <= shar && shar <= '\u02C1')
      return MODIFIER_LETTER;
    else  if ('\u02C2' <= shar && shar <= '\u02CF')
      return MODIFIER_SYMBOL;
    else  if ('\u02D0' <= shar && shar <= '\u02D1')
      return MODIFIER_LETTER;
    else  if ('\u02D2' <= shar && shar <= '\u02DE')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u02DF')
      return UNASSIGNED;
    else  if ('\u02E0' <= shar && shar <= '\u02E4')
      return MODIFIER_LETTER;
    else  if ('\u02E5' <= shar && shar <= '\u02E9')
      return MODIFIER_SYMBOL;
    else  if ('\u02EA' <= shar && shar <= '\u02FF')
      return UNASSIGNED;
    else  if ('\u0300' <= shar && shar <= '\u0345')
      return NON_SPACING_MARK;
    else  if ('\u0346' <= shar && shar <= '\u035F')
      return UNASSIGNED;
    else  if ('\u0360' <= shar && shar <= '\u0361')
      return NON_SPACING_MARK;
    else  if ('\u0362' <= shar && shar <= '\u0373')
      return UNASSIGNED;
    else  if ('\u0374' <= shar && shar <= '\u0375')
      return OTHER_PUNCTUATION;
    else  if ('\u0376' <= shar && shar <= '\u0379')
      return UNASSIGNED;
    else  if (shar == '\u037A')
      return MODIFIER_LETTER;
    else  if ('\u037B' <= shar && shar <= '\u037D')
      return UNASSIGNED;
    else  if (shar == '\u037E')
      return OTHER_PUNCTUATION;
    else  if ('\u037F' <= shar && shar <= '\u0383')
      return UNASSIGNED;
    else  if ('\u0384' <= shar && shar <= '\u0385')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u0386')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0387')
      return OTHER_PUNCTUATION;
    else  if ('\u0388' <= shar && shar <= '\u038A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u038B')
      return UNASSIGNED;
    else  if (shar == '\u038C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u038D')
      return UNASSIGNED;
    else  if ('\u038E' <= shar && shar <= '\u038F')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0390')
      return LOWERCASE_LETTER;
    else  if ('\u0391' <= shar && shar <= '\u03A1')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03A2')
      return UNASSIGNED;
    else  if ('\u03A3' <= shar && shar <= '\u03AB')
      return UPPERCASE_LETTER;
    else  if ('\u03AC' <= shar && shar <= '\u03CE')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03CF')
      return UNASSIGNED;
    else  if ('\u03D0' <= shar && shar <= '\u03D1')
      return LOWERCASE_LETTER;
    else  if ('\u03D2' <= shar && shar <= '\u03D4')
      return UPPERCASE_LETTER;
    else  if ('\u03D5' <= shar && shar <= '\u03D6')
      return LOWERCASE_LETTER;
    else  if ('\u03D7' <= shar && shar <= '\u03D9')
      return UNASSIGNED;
    else  if (shar == '\u03DA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03DB')
      return UNASSIGNED;
    else  if (shar == '\u03DC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03DD')
      return UNASSIGNED;
    else  if (shar == '\u03DE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03DF')
      return UNASSIGNED;
    else  if (shar == '\u03E0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03E1')
      return UNASSIGNED;
    else  if (shar == '\u03E2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03E3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03E4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03E5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03E6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03E7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03E8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03E9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03EA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03EB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03EC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u03ED')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03EE')
      return UPPERCASE_LETTER;
    else  if ('\u03EF' <= shar && shar <= '\u03F2')
      return LOWERCASE_LETTER;
    else  if (shar == '\u03F3')
      return OTHER_LETTER;
    else  if ('\u03F4' <= shar && shar <= '\u0400')
      return UNASSIGNED;
    else  if ('\u0401' <= shar && shar <= '\u040C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u040D')
      return UNASSIGNED;
    else  if ('\u040E' <= shar && shar <= '\u042F')
      return UPPERCASE_LETTER;
    else  if ('\u0430' <= shar && shar <= '\u044F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0450')
      return UNASSIGNED;
    else  if ('\u0451' <= shar && shar <= '\u045C')
      return LOWERCASE_LETTER;
    else  if (shar == '\u045D')
      return UNASSIGNED;
    else  if ('\u045E' <= shar && shar <= '\u045F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0460')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0461')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0462')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0463')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0464')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0465')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0466')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0467')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0468')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0469')
      return LOWERCASE_LETTER;
    else  if (shar == '\u046A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u046B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u046C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u046D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u046E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u046F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0470')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0471')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0472')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0473')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0474')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0475')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0476')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0477')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0478')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0479')
      return LOWERCASE_LETTER;
    else  if (shar == '\u047A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u047B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u047C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u047D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u047E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u047F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0480')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0481')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0482')
      return OTHER_SYMBOL;
    else  if ('\u0483' <= shar && shar <= '\u0486')
      return NON_SPACING_MARK;
    else  if ('\u0487' <= shar && shar <= '\u048F')
      return UNASSIGNED;
    else  if (shar == '\u0490')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0491')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0492')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0493')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0494')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0495')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0496')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0497')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0498')
      return UPPERCASE_LETTER;
    else  if (shar == '\u0499')
      return LOWERCASE_LETTER;
    else  if (shar == '\u049A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u049B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u049C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u049D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u049E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u049F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04A0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04A1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04A2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04A3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04A4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04A5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04A6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04A7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04A8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04A9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04AA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04AB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04AC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04AD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04AE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04AF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04B0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04B1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04B2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04B3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04B4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04B5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04B6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04B7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04B8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04B9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04BA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04BB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04BC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04BD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04BE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04BF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04C0')
      return OTHER_LETTER;
    else  if (shar == '\u04C1')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04C2')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04C3')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04C4')
      return LOWERCASE_LETTER;
    else  if ('\u04C5' <= shar && shar <= '\u04C6')
      return UNASSIGNED;
    else  if (shar == '\u04C7')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04C8')
      return LOWERCASE_LETTER;
    else  if ('\u04C9' <= shar && shar <= '\u04CA')
      return UNASSIGNED;
    else  if (shar == '\u04CB')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04CC')
      return LOWERCASE_LETTER;
    else  if ('\u04CD' <= shar && shar <= '\u04CF')
      return UNASSIGNED;
    else  if (shar == '\u04D0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04D1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04D2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04D3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04D4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04D5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04D6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04D7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04D8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04D9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04DA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04DB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04DC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04DD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04DE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04DF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04E0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04E1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04E2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04E3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04E4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04E5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04E6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04E7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04E8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04E9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04EA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04EB')
      return LOWERCASE_LETTER;
    else  if ('\u04EC' <= shar && shar <= '\u04ED')
      return UNASSIGNED;
    else  if (shar == '\u04EE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04EF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04F0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04F1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04F2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04F3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u04F4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04F5')
      return LOWERCASE_LETTER;
    else  if ('\u04F6' <= shar && shar <= '\u04F7')
      return UNASSIGNED;
    else  if (shar == '\u04F8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u04F9')
      return LOWERCASE_LETTER;
    else  if ('\u04FA' <= shar && shar <= '\u0530')
      return UNASSIGNED;
    else  if ('\u0531' <= shar && shar <= '\u0556')
      return UPPERCASE_LETTER;
    else  if ('\u0557' <= shar && shar <= '\u0558')
      return UNASSIGNED;
    else  if (shar == '\u0559')
      return MODIFIER_LETTER;
    else  if ('\u055A' <= shar && shar <= '\u055F')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u0560')
      return UNASSIGNED;
    else  if ('\u0561' <= shar && shar <= '\u0587')
      return LOWERCASE_LETTER;
    else  if (shar == '\u0588')
      return UNASSIGNED;
    else  if (shar == '\u0589')
      return OTHER_PUNCTUATION;
    else  if ('\u058A' <= shar && shar <= '\u0590')
      return UNASSIGNED;
    else  if ('\u0591' <= shar && shar <= '\u05A1')
      return NON_SPACING_MARK;
    else  if (shar == '\u05A2')
      return UNASSIGNED;
    else  if ('\u05A3' <= shar && shar <= '\u05B9')
      return NON_SPACING_MARK;
    else  if (shar == '\u05BA')
      return UNASSIGNED;
    else  if ('\u05BB' <= shar && shar <= '\u05BD')
      return NON_SPACING_MARK;
    else  if (shar == '\u05BE')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u05BF')
      return NON_SPACING_MARK;
    else  if (shar == '\u05C0')
      return OTHER_PUNCTUATION;
    else  if ('\u05C1' <= shar && shar <= '\u05C2')
      return NON_SPACING_MARK;
    else  if (shar == '\u05C3')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u05C4')
      return NON_SPACING_MARK;
    else  if ('\u05C5' <= shar && shar <= '\u05CF')
      return UNASSIGNED;
    else  if ('\u05D0' <= shar && shar <= '\u05EA')
      return OTHER_LETTER;
    else  if ('\u05EB' <= shar && shar <= '\u05EF')
      return UNASSIGNED;
    else  if ('\u05F0' <= shar && shar <= '\u05F2')
      return OTHER_LETTER;
    else  if ('\u05F3' <= shar && shar <= '\u05F4')
      return OTHER_PUNCTUATION;
    else  if ('\u05F5' <= shar && shar <= '\u060B')
      return UNASSIGNED;
    else  if (shar == '\u060C')
      return OTHER_PUNCTUATION;
    else  if ('\u060D' <= shar && shar <= '\u061A')
      return UNASSIGNED;
    else  if (shar == '\u061B')
      return OTHER_PUNCTUATION;
    else  if ('\u061C' <= shar && shar <= '\u061E')
      return UNASSIGNED;
    else  if (shar == '\u061F')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u0620')
      return UNASSIGNED;
    else  if ('\u0621' <= shar && shar <= '\u063A')
      return OTHER_LETTER;
    else  if ('\u063B' <= shar && shar <= '\u063F')
      return UNASSIGNED;
    else  if (shar == '\u0640')
      return MODIFIER_LETTER;
    else  if ('\u0641' <= shar && shar <= '\u064A')
      return OTHER_LETTER;
    else  if ('\u064B' <= shar && shar <= '\u0652')
      return NON_SPACING_MARK;
    else  if ('\u0653' <= shar && shar <= '\u065F')
      return UNASSIGNED;
    else  if ('\u0660' <= shar && shar <= '\u0669')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u066A' <= shar && shar <= '\u066D')
      return OTHER_PUNCTUATION;
    else  if ('\u066E' <= shar && shar <= '\u066F')
      return UNASSIGNED;
    else  if (shar == '\u0670')
      return NON_SPACING_MARK;
    else  if ('\u0671' <= shar && shar <= '\u06B7')
      return OTHER_LETTER;
    else  if ('\u06B8' <= shar && shar <= '\u06B9')
      return UNASSIGNED;
    else  if ('\u06BA' <= shar && shar <= '\u06BE')
      return OTHER_LETTER;
    else  if (shar == '\u06BF')
      return UNASSIGNED;
    else  if ('\u06C0' <= shar && shar <= '\u06CE')
      return OTHER_LETTER;
    else  if (shar == '\u06CF')
      return UNASSIGNED;
    else  if ('\u06D0' <= shar && shar <= '\u06D3')
      return OTHER_LETTER;
    else  if (shar == '\u06D4')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u06D5')
      return OTHER_LETTER;
    else  if ('\u06D6' <= shar && shar <= '\u06DC')
      return NON_SPACING_MARK;
    else  if ('\u06DD' <= shar && shar <= '\u06DE')
      return ENCLOSING_MARK;
    else  if ('\u06DF' <= shar && shar <= '\u06E4')
      return NON_SPACING_MARK;
    else  if ('\u06E5' <= shar && shar <= '\u06E6')
      return MODIFIER_LETTER;
    else  if ('\u06E7' <= shar && shar <= '\u06E8')
      return NON_SPACING_MARK;
    else  if (shar == '\u06E9')
      return OTHER_SYMBOL;
    else  if ('\u06EA' <= shar && shar <= '\u06ED')
      return NON_SPACING_MARK;
    else  if ('\u06EE' <= shar && shar <= '\u06EF')
      return UNASSIGNED;
    else  if ('\u06F0' <= shar && shar <= '\u06F9')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u06FA' <= shar && shar <= '\u0900')
      return UNASSIGNED;
    else  if ('\u0901' <= shar && shar <= '\u0902')
      return NON_SPACING_MARK;
    else  if (shar == '\u0903')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0904')
      return UNASSIGNED;
    else  if ('\u0905' <= shar && shar <= '\u0939')
      return OTHER_LETTER;
    else  if ('\u093A' <= shar && shar <= '\u093B')
      return UNASSIGNED;
    else  if (shar == '\u093C')
      return NON_SPACING_MARK;
    else  if (shar == '\u093D')
      return OTHER_LETTER;
    else  if ('\u093E' <= shar && shar <= '\u0940')
      return COMBINING_SPACING_MARK;
    else  if ('\u0941' <= shar && shar <= '\u0948')
      return NON_SPACING_MARK;
    else  if ('\u0949' <= shar && shar <= '\u094C')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u094D')
      return NON_SPACING_MARK;
    else  if ('\u094E' <= shar && shar <= '\u094F')
      return UNASSIGNED;
    else  if (shar == '\u0950')
      return OTHER_SYMBOL;
    else  if ('\u0951' <= shar && shar <= '\u0954')
      return NON_SPACING_MARK;
    else  if ('\u0955' <= shar && shar <= '\u0957')
      return UNASSIGNED;
    else  if ('\u0958' <= shar && shar <= '\u0961')
      return OTHER_LETTER;
    else  if ('\u0962' <= shar && shar <= '\u0963')
      return NON_SPACING_MARK;
    else  if ('\u0964' <= shar && shar <= '\u0965')
      return OTHER_PUNCTUATION;
    else  if ('\u0966' <= shar && shar <= '\u096F')
      return DECIMAL_DIGIT_NUMBER;
    else  if (shar == '\u0970')
      return OTHER_PUNCTUATION;
    else  if ('\u0971' <= shar && shar <= '\u0980')
      return UNASSIGNED;
    else  if (shar == '\u0981')
      return NON_SPACING_MARK;
    else  if ('\u0982' <= shar && shar <= '\u0983')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0984')
      return UNASSIGNED;
    else  if ('\u0985' <= shar && shar <= '\u098C')
      return OTHER_LETTER;
    else  if ('\u098D' <= shar && shar <= '\u098E')
      return UNASSIGNED;
    else  if ('\u098F' <= shar && shar <= '\u0990')
      return OTHER_LETTER;
    else  if ('\u0991' <= shar && shar <= '\u0992')
      return UNASSIGNED;
    else  if ('\u0993' <= shar && shar <= '\u09A8')
      return OTHER_LETTER;
    else  if (shar == '\u09A9')
      return UNASSIGNED;
    else  if ('\u09AA' <= shar && shar <= '\u09B0')
      return OTHER_LETTER;
    else  if (shar == '\u09B1')
      return UNASSIGNED;
    else  if (shar == '\u09B2')
      return OTHER_LETTER;
    else  if ('\u09B3' <= shar && shar <= '\u09B5')
      return UNASSIGNED;
    else  if ('\u09B6' <= shar && shar <= '\u09B9')
      return OTHER_LETTER;
    else  if ('\u09BA' <= shar && shar <= '\u09BB')
      return UNASSIGNED;
    else  if (shar == '\u09BC')
      return NON_SPACING_MARK;
    else  if (shar == '\u09BD')
      return UNASSIGNED;
    else  if ('\u09BE' <= shar && shar <= '\u09C0')
      return COMBINING_SPACING_MARK;
    else  if ('\u09C1' <= shar && shar <= '\u09C4')
      return NON_SPACING_MARK;
    else  if ('\u09C5' <= shar && shar <= '\u09C6')
      return UNASSIGNED;
    else  if ('\u09C7' <= shar && shar <= '\u09C8')
      return COMBINING_SPACING_MARK;
    else  if ('\u09C9' <= shar && shar <= '\u09CA')
      return UNASSIGNED;
    else  if ('\u09CB' <= shar && shar <= '\u09CC')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u09CD')
      return NON_SPACING_MARK;
    else  if ('\u09CE' <= shar && shar <= '\u09D6')
      return UNASSIGNED;
    else  if (shar == '\u09D7')
      return COMBINING_SPACING_MARK;
    else  if ('\u09D8' <= shar && shar <= '\u09DB')
      return UNASSIGNED;
    else  if ('\u09DC' <= shar && shar <= '\u09DD')
      return OTHER_LETTER;
    else  if (shar == '\u09DE')
      return UNASSIGNED;
    else  if ('\u09DF' <= shar && shar <= '\u09E1')
      return OTHER_LETTER;
    else  if ('\u09E2' <= shar && shar <= '\u09E3')
      return NON_SPACING_MARK;
    else  if ('\u09E4' <= shar && shar <= '\u09E5')
      return UNASSIGNED;
    else  if ('\u09E6' <= shar && shar <= '\u09EF')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u09F0' <= shar && shar <= '\u09F1')
      return OTHER_LETTER;
    else  if ('\u09F2' <= shar && shar <= '\u09F3')
      return CURRENCY_SYMBOL;
    else  if ('\u09F4' <= shar && shar <= '\u09F9')
      return OTHER_NUMBER;
    else  if (shar == '\u09FA')
      return OTHER_SYMBOL;
    else  if ('\u09FB' <= shar && shar <= '\u0A01')
      return UNASSIGNED;
    else  if (shar == '\u0A02')
      return NON_SPACING_MARK;
    else  if ('\u0A03' <= shar && shar <= '\u0A04')
      return UNASSIGNED;
    else  if ('\u0A05' <= shar && shar <= '\u0A0A')
      return OTHER_LETTER;
    else  if ('\u0A0B' <= shar && shar <= '\u0A0E')
      return UNASSIGNED;
    else  if ('\u0A0F' <= shar && shar <= '\u0A10')
      return OTHER_LETTER;
    else  if ('\u0A11' <= shar && shar <= '\u0A12')
      return UNASSIGNED;
    else  if ('\u0A13' <= shar && shar <= '\u0A28')
      return OTHER_LETTER;
    else  if (shar == '\u0A29')
      return UNASSIGNED;
    else  if ('\u0A2A' <= shar && shar <= '\u0A30')
      return OTHER_LETTER;
    else  if (shar == '\u0A31')
      return UNASSIGNED;
    else  if ('\u0A32' <= shar && shar <= '\u0A33')
      return OTHER_LETTER;
    else  if (shar == '\u0A34')
      return UNASSIGNED;
    else  if ('\u0A35' <= shar && shar <= '\u0A36')
      return OTHER_LETTER;
    else  if (shar == '\u0A37')
      return UNASSIGNED;
    else  if ('\u0A38' <= shar && shar <= '\u0A39')
      return OTHER_LETTER;
    else  if ('\u0A3A' <= shar && shar <= '\u0A3B')
      return UNASSIGNED;
    else  if (shar == '\u0A3C')
      return NON_SPACING_MARK;
    else  if (shar == '\u0A3D')
      return UNASSIGNED;
    else  if ('\u0A3E' <= shar && shar <= '\u0A40')
      return COMBINING_SPACING_MARK;
    else  if ('\u0A41' <= shar && shar <= '\u0A42')
      return NON_SPACING_MARK;
    else  if ('\u0A43' <= shar && shar <= '\u0A46')
      return UNASSIGNED;
    else  if ('\u0A47' <= shar && shar <= '\u0A48')
      return NON_SPACING_MARK;
    else  if ('\u0A49' <= shar && shar <= '\u0A4A')
      return UNASSIGNED;
    else  if ('\u0A4B' <= shar && shar <= '\u0A4D')
      return NON_SPACING_MARK;
    else  if ('\u0A4E' <= shar && shar <= '\u0A58')
      return UNASSIGNED;
    else  if ('\u0A59' <= shar && shar <= '\u0A5C')
      return OTHER_LETTER;
    else  if (shar == '\u0A5D')
      return UNASSIGNED;
    else  if (shar == '\u0A5E')
      return OTHER_LETTER;
    else  if ('\u0A5F' <= shar && shar <= '\u0A65')
      return UNASSIGNED;
    else  if ('\u0A66' <= shar && shar <= '\u0A6F')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0A70' <= shar && shar <= '\u0A71')
      return NON_SPACING_MARK;
    else  if ('\u0A72' <= shar && shar <= '\u0A74')
      return OTHER_LETTER;
    else  if ('\u0A75' <= shar && shar <= '\u0A80')
      return UNASSIGNED;
    else  if ('\u0A81' <= shar && shar <= '\u0A82')
      return NON_SPACING_MARK;
    else  if (shar == '\u0A83')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0A84')
      return UNASSIGNED;
    else  if ('\u0A85' <= shar && shar <= '\u0A8B')
      return OTHER_LETTER;
    else  if (shar == '\u0A8C')
      return UNASSIGNED;
    else  if (shar == '\u0A8D')
      return OTHER_LETTER;
    else  if (shar == '\u0A8E')
      return UNASSIGNED;
    else  if ('\u0A8F' <= shar && shar <= '\u0A91')
      return OTHER_LETTER;
    else  if (shar == '\u0A92')
      return UNASSIGNED;
    else  if ('\u0A93' <= shar && shar <= '\u0AA8')
      return OTHER_LETTER;
    else  if (shar == '\u0AA9')
      return UNASSIGNED;
    else  if ('\u0AAA' <= shar && shar <= '\u0AB0')
      return OTHER_LETTER;
    else  if (shar == '\u0AB1')
      return UNASSIGNED;
    else  if ('\u0AB2' <= shar && shar <= '\u0AB3')
      return OTHER_LETTER;
    else  if (shar == '\u0AB4')
      return UNASSIGNED;
    else  if ('\u0AB5' <= shar && shar <= '\u0AB9')
      return OTHER_LETTER;
    else  if ('\u0ABA' <= shar && shar <= '\u0ABB')
      return UNASSIGNED;
    else  if (shar == '\u0ABC')
      return NON_SPACING_MARK;
    else  if (shar == '\u0ABD')
      return OTHER_LETTER;
    else  if ('\u0ABE' <= shar && shar <= '\u0AC0')
      return COMBINING_SPACING_MARK;
    else  if ('\u0AC1' <= shar && shar <= '\u0AC5')
      return NON_SPACING_MARK;
    else  if (shar == '\u0AC6')
      return UNASSIGNED;
    else  if ('\u0AC7' <= shar && shar <= '\u0AC8')
      return NON_SPACING_MARK;
    else  if (shar == '\u0AC9')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0ACA')
      return UNASSIGNED;
    else  if ('\u0ACB' <= shar && shar <= '\u0ACC')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0ACD')
      return NON_SPACING_MARK;
    else  if ('\u0ACE' <= shar && shar <= '\u0ACF')
      return UNASSIGNED;
    else  if (shar == '\u0AD0')
      return OTHER_SYMBOL;
    else  if ('\u0AD1' <= shar && shar <= '\u0ADF')
      return UNASSIGNED;
    else  if (shar == '\u0AE0')
      return OTHER_LETTER;
    else  if ('\u0AE1' <= shar && shar <= '\u0AE5')
      return UNASSIGNED;
    else  if ('\u0AE6' <= shar && shar <= '\u0AEF')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0AF0' <= shar && shar <= '\u0B00')
      return UNASSIGNED;
    else  if (shar == '\u0B01')
      return NON_SPACING_MARK;
    else  if ('\u0B02' <= shar && shar <= '\u0B03')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0B04')
      return UNASSIGNED;
    else  if ('\u0B05' <= shar && shar <= '\u0B0C')
      return OTHER_LETTER;
    else  if ('\u0B0D' <= shar && shar <= '\u0B0E')
      return UNASSIGNED;
    else  if ('\u0B0F' <= shar && shar <= '\u0B10')
      return OTHER_LETTER;
    else  if ('\u0B11' <= shar && shar <= '\u0B12')
      return UNASSIGNED;
    else  if ('\u0B13' <= shar && shar <= '\u0B28')
      return OTHER_LETTER;
    else  if (shar == '\u0B29')
      return UNASSIGNED;
    else  if ('\u0B2A' <= shar && shar <= '\u0B30')
      return OTHER_LETTER;
    else  if (shar == '\u0B31')
      return UNASSIGNED;
    else  if ('\u0B32' <= shar && shar <= '\u0B33')
      return OTHER_LETTER;
    else  if ('\u0B34' <= shar && shar <= '\u0B35')
      return UNASSIGNED;
    else  if ('\u0B36' <= shar && shar <= '\u0B39')
      return OTHER_LETTER;
    else  if ('\u0B3A' <= shar && shar <= '\u0B3B')
      return UNASSIGNED;
    else  if (shar == '\u0B3C')
      return NON_SPACING_MARK;
    else  if (shar == '\u0B3D')
      return OTHER_LETTER;
    else  if (shar == '\u0B3E')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0B3F')
      return NON_SPACING_MARK;
    else  if (shar == '\u0B40')
      return COMBINING_SPACING_MARK;
    else  if ('\u0B41' <= shar && shar <= '\u0B43')
      return NON_SPACING_MARK;
    else  if ('\u0B44' <= shar && shar <= '\u0B46')
      return UNASSIGNED;
    else  if ('\u0B47' <= shar && shar <= '\u0B48')
      return COMBINING_SPACING_MARK;
    else  if ('\u0B49' <= shar && shar <= '\u0B4A')
      return UNASSIGNED;
    else  if ('\u0B4B' <= shar && shar <= '\u0B4C')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0B4D')
      return NON_SPACING_MARK;
    else  if ('\u0B4E' <= shar && shar <= '\u0B55')
      return UNASSIGNED;
    else  if (shar == '\u0B56')
      return NON_SPACING_MARK;
    else  if (shar == '\u0B57')
      return COMBINING_SPACING_MARK;
    else  if ('\u0B58' <= shar && shar <= '\u0B5B')
      return UNASSIGNED;
    else  if ('\u0B5C' <= shar && shar <= '\u0B5D')
      return OTHER_LETTER;
    else  if (shar == '\u0B5E')
      return UNASSIGNED;
    else  if ('\u0B5F' <= shar && shar <= '\u0B61')
      return OTHER_LETTER;
    else  if ('\u0B62' <= shar && shar <= '\u0B65')
      return UNASSIGNED;
    else  if ('\u0B66' <= shar && shar <= '\u0B6F')
      return DECIMAL_DIGIT_NUMBER;
    else  if (shar == '\u0B70')
      return OTHER_SYMBOL;
    else  if ('\u0B71' <= shar && shar <= '\u0B81')
      return UNASSIGNED;
    else  if (shar == '\u0B82')
      return NON_SPACING_MARK;
    else  if (shar == '\u0B83')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0B84')
      return UNASSIGNED;
    else  if ('\u0B85' <= shar && shar <= '\u0B8A')
      return OTHER_LETTER;
    else  if ('\u0B8B' <= shar && shar <= '\u0B8D')
      return UNASSIGNED;
    else  if ('\u0B8E' <= shar && shar <= '\u0B90')
      return OTHER_LETTER;
    else  if (shar == '\u0B91')
      return UNASSIGNED;
    else  if ('\u0B92' <= shar && shar <= '\u0B95')
      return OTHER_LETTER;
    else  if ('\u0B96' <= shar && shar <= '\u0B98')
      return UNASSIGNED;
    else  if ('\u0B99' <= shar && shar <= '\u0B9A')
      return OTHER_LETTER;
    else  if (shar == '\u0B9B')
      return UNASSIGNED;
    else  if (shar == '\u0B9C')
      return OTHER_LETTER;
    else  if (shar == '\u0B9D')
      return UNASSIGNED;
    else  if ('\u0B9E' <= shar && shar <= '\u0B9F')
      return OTHER_LETTER;
    else  if ('\u0BA0' <= shar && shar <= '\u0BA2')
      return UNASSIGNED;
    else  if ('\u0BA3' <= shar && shar <= '\u0BA4')
      return OTHER_LETTER;
    else  if ('\u0BA5' <= shar && shar <= '\u0BA7')
      return UNASSIGNED;
    else  if ('\u0BA8' <= shar && shar <= '\u0BAA')
      return OTHER_LETTER;
    else  if ('\u0BAB' <= shar && shar <= '\u0BAD')
      return UNASSIGNED;
    else  if ('\u0BAE' <= shar && shar <= '\u0BB5')
      return OTHER_LETTER;
    else  if (shar == '\u0BB6')
      return UNASSIGNED;
    else  if ('\u0BB7' <= shar && shar <= '\u0BB9')
      return OTHER_LETTER;
    else  if ('\u0BBA' <= shar && shar <= '\u0BBD')
      return UNASSIGNED;
    else  if ('\u0BBE' <= shar && shar <= '\u0BBF')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0BC0')
      return NON_SPACING_MARK;
    else  if ('\u0BC1' <= shar && shar <= '\u0BC2')
      return COMBINING_SPACING_MARK;
    else  if ('\u0BC3' <= shar && shar <= '\u0BC5')
      return UNASSIGNED;
    else  if ('\u0BC6' <= shar && shar <= '\u0BC8')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0BC9')
      return UNASSIGNED;
    else  if ('\u0BCA' <= shar && shar <= '\u0BCC')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0BCD')
      return NON_SPACING_MARK;
    else  if ('\u0BCE' <= shar && shar <= '\u0BD6')
      return UNASSIGNED;
    else  if (shar == '\u0BD7')
      return COMBINING_SPACING_MARK;
    else  if ('\u0BD8' <= shar && shar <= '\u0BE6')
      return UNASSIGNED;
    else  if ('\u0BE7' <= shar && shar <= '\u0BEF')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0BF0' <= shar && shar <= '\u0BF2')
      return OTHER_NUMBER;
    else  if ('\u0BF3' <= shar && shar <= '\u0C00')
      return UNASSIGNED;
    else  if ('\u0C01' <= shar && shar <= '\u0C03')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0C04')
      return UNASSIGNED;
    else  if ('\u0C05' <= shar && shar <= '\u0C0C')
      return OTHER_LETTER;
    else  if (shar == '\u0C0D')
      return UNASSIGNED;
    else  if ('\u0C0E' <= shar && shar <= '\u0C10')
      return OTHER_LETTER;
    else  if (shar == '\u0C11')
      return UNASSIGNED;
    else  if ('\u0C12' <= shar && shar <= '\u0C28')
      return OTHER_LETTER;
    else  if (shar == '\u0C29')
      return UNASSIGNED;
    else  if ('\u0C2A' <= shar && shar <= '\u0C33')
      return OTHER_LETTER;
    else  if (shar == '\u0C34')
      return UNASSIGNED;
    else  if ('\u0C35' <= shar && shar <= '\u0C39')
      return OTHER_LETTER;
    else  if ('\u0C3A' <= shar && shar <= '\u0C3D')
      return UNASSIGNED;
    else  if ('\u0C3E' <= shar && shar <= '\u0C40')
      return NON_SPACING_MARK;
    else  if ('\u0C41' <= shar && shar <= '\u0C44')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0C45')
      return UNASSIGNED;
    else  if ('\u0C46' <= shar && shar <= '\u0C48')
      return NON_SPACING_MARK;
    else  if (shar == '\u0C49')
      return UNASSIGNED;
    else  if ('\u0C4A' <= shar && shar <= '\u0C4D')
      return NON_SPACING_MARK;
    else  if ('\u0C4E' <= shar && shar <= '\u0C54')
      return UNASSIGNED;
    else  if ('\u0C55' <= shar && shar <= '\u0C56')
      return NON_SPACING_MARK;
    else  if ('\u0C57' <= shar && shar <= '\u0C5F')
      return UNASSIGNED;
    else  if ('\u0C60' <= shar && shar <= '\u0C61')
      return OTHER_LETTER;
    else  if ('\u0C62' <= shar && shar <= '\u0C65')
      return UNASSIGNED;
    else  if ('\u0C66' <= shar && shar <= '\u0C6F')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0C70' <= shar && shar <= '\u0C81')
      return UNASSIGNED;
    else  if ('\u0C82' <= shar && shar <= '\u0C83')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0C84')
      return UNASSIGNED;
    else  if ('\u0C85' <= shar && shar <= '\u0C8C')
      return OTHER_LETTER;
    else  if (shar == '\u0C8D')
      return UNASSIGNED;
    else  if ('\u0C8E' <= shar && shar <= '\u0C90')
      return OTHER_LETTER;
    else  if (shar == '\u0C91')
      return UNASSIGNED;
    else  if ('\u0C92' <= shar && shar <= '\u0CA8')
      return OTHER_LETTER;
    else  if (shar == '\u0CA9')
      return UNASSIGNED;
    else  if ('\u0CAA' <= shar && shar <= '\u0CB3')
      return OTHER_LETTER;
    else  if (shar == '\u0CB4')
      return UNASSIGNED;
    else  if ('\u0CB5' <= shar && shar <= '\u0CB9')
      return OTHER_LETTER;
    else  if ('\u0CBA' <= shar && shar <= '\u0CBD')
      return UNASSIGNED;
    else  if (shar == '\u0CBE')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0CBF')
      return NON_SPACING_MARK;
    else  if ('\u0CC0' <= shar && shar <= '\u0CC4')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0CC5')
      return UNASSIGNED;
    else  if (shar == '\u0CC6')
      return NON_SPACING_MARK;
    else  if ('\u0CC7' <= shar && shar <= '\u0CC8')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0CC9')
      return UNASSIGNED;
    else  if ('\u0CCA' <= shar && shar <= '\u0CCB')
      return COMBINING_SPACING_MARK;
    else  if ('\u0CCC' <= shar && shar <= '\u0CCD')
      return NON_SPACING_MARK;
    else  if ('\u0CCE' <= shar && shar <= '\u0CD4')
      return UNASSIGNED;
    else  if ('\u0CD5' <= shar && shar <= '\u0CD6')
      return COMBINING_SPACING_MARK;
    else  if ('\u0CD7' <= shar && shar <= '\u0CDD')
      return UNASSIGNED;
    else  if (shar == '\u0CDE')
      return OTHER_LETTER;
    else  if (shar == '\u0CDF')
      return UNASSIGNED;
    else  if ('\u0CE0' <= shar && shar <= '\u0CE1')
      return OTHER_LETTER;
    else  if ('\u0CE2' <= shar && shar <= '\u0CE5')
      return UNASSIGNED;
    else  if ('\u0CE6' <= shar && shar <= '\u0CEF')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0CF0' <= shar && shar <= '\u0D01')
      return UNASSIGNED;
    else  if ('\u0D02' <= shar && shar <= '\u0D03')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0D04')
      return UNASSIGNED;
    else  if ('\u0D05' <= shar && shar <= '\u0D0C')
      return OTHER_LETTER;
    else  if (shar == '\u0D0D')
      return UNASSIGNED;
    else  if ('\u0D0E' <= shar && shar <= '\u0D10')
      return OTHER_LETTER;
    else  if (shar == '\u0D11')
      return UNASSIGNED;
    else  if ('\u0D12' <= shar && shar <= '\u0D28')
      return OTHER_LETTER;
    else  if (shar == '\u0D29')
      return UNASSIGNED;
    else  if ('\u0D2A' <= shar && shar <= '\u0D39')
      return OTHER_LETTER;
    else  if ('\u0D3A' <= shar && shar <= '\u0D3D')
      return UNASSIGNED;
    else  if ('\u0D3E' <= shar && shar <= '\u0D40')
      return COMBINING_SPACING_MARK;
    else  if ('\u0D41' <= shar && shar <= '\u0D43')
      return NON_SPACING_MARK;
    else  if ('\u0D44' <= shar && shar <= '\u0D45')
      return UNASSIGNED;
    else  if ('\u0D46' <= shar && shar <= '\u0D48')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0D49')
      return UNASSIGNED;
    else  if ('\u0D4A' <= shar && shar <= '\u0D4C')
      return COMBINING_SPACING_MARK;
    else  if (shar == '\u0D4D')
      return NON_SPACING_MARK;
    else  if ('\u0D4E' <= shar && shar <= '\u0D56')
      return UNASSIGNED;
    else  if (shar == '\u0D57')
      return COMBINING_SPACING_MARK;
    else  if ('\u0D58' <= shar && shar <= '\u0D5F')
      return UNASSIGNED;
    else  if ('\u0D60' <= shar && shar <= '\u0D61')
      return OTHER_LETTER;
    else  if ('\u0D62' <= shar && shar <= '\u0D65')
      return UNASSIGNED;
    else  if ('\u0D66' <= shar && shar <= '\u0D6F')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0D70' <= shar && shar <= '\u0E00')
      return UNASSIGNED;
    else  if ('\u0E01' <= shar && shar <= '\u0E2E')
      return OTHER_LETTER;
    else  if (shar == '\u0E2F')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u0E30')
      return OTHER_LETTER;
    else  if (shar == '\u0E31')
      return NON_SPACING_MARK;
    else  if ('\u0E32' <= shar && shar <= '\u0E33')
      return OTHER_LETTER;
    else  if ('\u0E34' <= shar && shar <= '\u0E3A')
      return NON_SPACING_MARK;
    else  if ('\u0E3B' <= shar && shar <= '\u0E3E')
      return UNASSIGNED;
    else  if (shar == '\u0E3F')
      return CURRENCY_SYMBOL;
    else  if ('\u0E40' <= shar && shar <= '\u0E45')
      return OTHER_LETTER;
    else  if (shar == '\u0E46')
      return MODIFIER_LETTER;
    else  if ('\u0E47' <= shar && shar <= '\u0E4E')
      return NON_SPACING_MARK;
    else  if (shar == '\u0E4F')
      return OTHER_SYMBOL;
    else  if ('\u0E50' <= shar && shar <= '\u0E59')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0E5A' <= shar && shar <= '\u0E5B')
      return OTHER_PUNCTUATION;
    else  if ('\u0E5C' <= shar && shar <= '\u0E80')
      return UNASSIGNED;
    else  if ('\u0E81' <= shar && shar <= '\u0E82')
      return OTHER_LETTER;
    else  if (shar == '\u0E83')
      return UNASSIGNED;
    else  if (shar == '\u0E84')
      return OTHER_LETTER;
    else  if ('\u0E85' <= shar && shar <= '\u0E86')
      return UNASSIGNED;
    else  if ('\u0E87' <= shar && shar <= '\u0E88')
      return OTHER_LETTER;
    else  if (shar == '\u0E89')
      return UNASSIGNED;
    else  if (shar == '\u0E8A')
      return OTHER_LETTER;
    else  if ('\u0E8B' <= shar && shar <= '\u0E8C')
      return UNASSIGNED;
    else  if (shar == '\u0E8D')
      return OTHER_LETTER;
    else  if ('\u0E8E' <= shar && shar <= '\u0E93')
      return UNASSIGNED;
    else  if ('\u0E94' <= shar && shar <= '\u0E97')
      return OTHER_LETTER;
    else  if (shar == '\u0E98')
      return UNASSIGNED;
    else  if ('\u0E99' <= shar && shar <= '\u0E9F')
      return OTHER_LETTER;
    else  if (shar == '\u0EA0')
      return UNASSIGNED;
    else  if ('\u0EA1' <= shar && shar <= '\u0EA3')
      return OTHER_LETTER;
    else  if (shar == '\u0EA4')
      return UNASSIGNED;
    else  if (shar == '\u0EA5')
      return OTHER_LETTER;
    else  if (shar == '\u0EA6')
      return UNASSIGNED;
    else  if (shar == '\u0EA7')
      return OTHER_LETTER;
    else  if ('\u0EA8' <= shar && shar <= '\u0EA9')
      return UNASSIGNED;
    else  if ('\u0EAA' <= shar && shar <= '\u0EAB')
      return OTHER_LETTER;
    else  if (shar == '\u0EAC')
      return UNASSIGNED;
    else  if ('\u0EAD' <= shar && shar <= '\u0EAE')
      return OTHER_LETTER;
    else  if (shar == '\u0EAF')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u0EB0')
      return OTHER_LETTER;
    else  if (shar == '\u0EB1')
      return NON_SPACING_MARK;
    else  if ('\u0EB2' <= shar && shar <= '\u0EB3')
      return OTHER_LETTER;
    else  if ('\u0EB4' <= shar && shar <= '\u0EB9')
      return NON_SPACING_MARK;
    else  if (shar == '\u0EBA')
      return UNASSIGNED;
    else  if ('\u0EBB' <= shar && shar <= '\u0EBC')
      return NON_SPACING_MARK;
    else  if (shar == '\u0EBD')
      return OTHER_LETTER;
    else  if ('\u0EBE' <= shar && shar <= '\u0EBF')
      return UNASSIGNED;
    else  if ('\u0EC0' <= shar && shar <= '\u0EC4')
      return OTHER_LETTER;
    else  if (shar == '\u0EC5')
      return UNASSIGNED;
    else  if (shar == '\u0EC6')
      return MODIFIER_LETTER;
    else  if (shar == '\u0EC7')
      return UNASSIGNED;
    else  if ('\u0EC8' <= shar && shar <= '\u0ECD')
      return NON_SPACING_MARK;
    else  if ('\u0ECE' <= shar && shar <= '\u0ECF')
      return UNASSIGNED;
    else  if ('\u0ED0' <= shar && shar <= '\u0ED9')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0EDA' <= shar && shar <= '\u0EDB')
      return UNASSIGNED;
    else  if ('\u0EDC' <= shar && shar <= '\u0EDD')
      return OTHER_LETTER;
    else  if ('\u0EDE' <= shar && shar <= '\u0EFF')
      return UNASSIGNED;
    else  if ('\u0F00' <= shar && shar <= '\u0F03')
      return OTHER_SYMBOL;
    else  if ('\u0F04' <= shar && shar <= '\u0F12')
      return OTHER_PUNCTUATION;
    else  if ('\u0F13' <= shar && shar <= '\u0F17')
      return OTHER_SYMBOL;
    else  if ('\u0F18' <= shar && shar <= '\u0F19')
      return NON_SPACING_MARK;
    else  if ('\u0F1A' <= shar && shar <= '\u0F1F')
      return OTHER_SYMBOL;
    else  if ('\u0F20' <= shar && shar <= '\u0F29')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\u0F2A' <= shar && shar <= '\u0F33')
      return OTHER_NUMBER;
    else  if (shar == '\u0F34')
      return OTHER_SYMBOL;
    else  if (shar == '\u0F35')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F36')
      return OTHER_SYMBOL;
    else  if (shar == '\u0F37')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F38')
      return OTHER_SYMBOL;
    else  if (shar == '\u0F39')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F3A')
      return START_PUNCTUATION;
    else  if (shar == '\u0F3B')
      return END_PUNCTUATION;
    else  if (shar == '\u0F3C')
      return START_PUNCTUATION;
    else  if (shar == '\u0F3D')
      return END_PUNCTUATION;
    else  if ('\u0F3E' <= shar && shar <= '\u0F3F')
      return COMBINING_SPACING_MARK;
    else  if ('\u0F40' <= shar && shar <= '\u0F47')
      return OTHER_LETTER;
    else  if (shar == '\u0F48')
      return UNASSIGNED;
    else  if ('\u0F49' <= shar && shar <= '\u0F69')
      return OTHER_LETTER;
    else  if ('\u0F6A' <= shar && shar <= '\u0F70')
      return UNASSIGNED;
    else  if ('\u0F71' <= shar && shar <= '\u0F7E')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F7F')
      return COMBINING_SPACING_MARK;
    else  if ('\u0F80' <= shar && shar <= '\u0F84')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F85')
      return OTHER_PUNCTUATION;
    else  if ('\u0F86' <= shar && shar <= '\u0F8B')
      return NON_SPACING_MARK;
    else  if ('\u0F8C' <= shar && shar <= '\u0F8F')
      return UNASSIGNED;
    else  if ('\u0F90' <= shar && shar <= '\u0F95')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F96')
      return UNASSIGNED;
    else  if (shar == '\u0F97')
      return NON_SPACING_MARK;
    else  if (shar == '\u0F98')
      return UNASSIGNED;
    else  if ('\u0F99' <= shar && shar <= '\u0FAD')
      return NON_SPACING_MARK;
    else  if ('\u0FAE' <= shar && shar <= '\u0FB0')
      return UNASSIGNED;
    else  if ('\u0FB1' <= shar && shar <= '\u0FB7')
      return NON_SPACING_MARK;
    else  if (shar == '\u0FB8')
      return UNASSIGNED;
    else  if (shar == '\u0FB9')
      return NON_SPACING_MARK;
    else  if ('\u0FBA' <= shar && shar <= '\u109F')
      return UNASSIGNED;
    else  if ('\u10A0' <= shar && shar <= '\u10C5')
      return UPPERCASE_LETTER;
    else  if ('\u10C6' <= shar && shar <= '\u10CF')
      return UNASSIGNED;
    else  if ('\u10D0' <= shar && shar <= '\u10F6')
      return LOWERCASE_LETTER;
    else  if ('\u10F7' <= shar && shar <= '\u10FA')
      return UNASSIGNED;
    else  if (shar == '\u10FB')
      return OTHER_PUNCTUATION;
    else  if ('\u10FC' <= shar && shar <= '\u10FF')
      return UNASSIGNED;
    else  if ('\u1100' <= shar && shar <= '\u1159')
      return OTHER_LETTER;
    else  if ('\u115A' <= shar && shar <= '\u115E')
      return UNASSIGNED;
    else  if ('\u115F' <= shar && shar <= '\u11A2')
      return OTHER_LETTER;
    else  if ('\u11A3' <= shar && shar <= '\u11A7')
      return UNASSIGNED;
    else  if ('\u11A8' <= shar && shar <= '\u11F9')
      return OTHER_LETTER;
    else  if ('\u11FA' <= shar && shar <= '\u1DFF')
      return UNASSIGNED;
    else  if (shar == '\u1E00')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E01')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E02')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E03')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E04')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E05')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E06')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E07')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E08')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E09')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E0A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E0B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E0C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E0D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E0E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E0F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E10')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E11')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E12')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E13')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E14')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E15')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E16')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E17')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E18')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E19')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E1A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E1B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E1C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E1D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E1E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E1F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E20')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E21')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E22')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E23')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E24')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E25')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E26')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E27')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E28')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E29')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E2A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E2B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E2C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E2D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E2E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E2F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E30')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E31')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E32')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E33')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E34')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E35')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E36')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E37')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E38')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E39')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E3A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E3B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E3C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E3D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E3E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E3F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E40')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E41')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E42')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E43')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E44')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E45')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E46')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E47')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E48')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E49')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E4A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E4B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E4C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E4D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E4E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E4F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E50')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E51')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E52')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E53')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E54')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E55')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E56')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E57')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E58')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E59')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E5A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E5B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E5C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E5D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E5E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E5F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E60')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E61')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E62')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E63')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E64')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E65')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E66')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E67')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E68')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E69')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E6A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E6B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E6C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E6D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E6E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E6F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E70')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E71')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E72')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E73')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E74')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E75')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E76')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E77')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E78')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E79')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E7A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E7B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E7C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E7D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E7E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E7F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E80')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E81')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E82')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E83')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E84')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E85')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E86')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E87')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E88')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E89')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E8A')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E8B')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E8C')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E8D')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E8E')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E8F')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E90')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E91')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E92')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1E93')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1E94')
      return UPPERCASE_LETTER;
    else  if ('\u1E95' <= shar && shar <= '\u1E9B')
      return LOWERCASE_LETTER;
    else  if ('\u1E9C' <= shar && shar <= '\u1E9F')
      return UNASSIGNED;
    else  if (shar == '\u1EA0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EA1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EA2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EA3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EA4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EA5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EA6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EA7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EA8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EA9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EAA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EAB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EAC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EAD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EAE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EAF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EB0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EB1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EB2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EB3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EB4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EB5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EB6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EB7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EB8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EB9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EBA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EBB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EBC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EBD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EBE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EBF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EC0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EC1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EC2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EC3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EC4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EC5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EC6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EC7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EC8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EC9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ECA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ECB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ECC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ECD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ECE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ECF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ED0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ED1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ED2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ED3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ED4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ED5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ED6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ED7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1ED8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1ED9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EDA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EDB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EDC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EDD')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EDE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EDF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EE0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EE1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EE2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EE3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EE4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EE5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EE6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EE7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EE8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EE9')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EEA')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EEB')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EEC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EED')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EEE')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EEF')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EF0')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EF1')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EF2')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EF3')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EF4')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EF5')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EF6')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EF7')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1EF8')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1EF9')
      return LOWERCASE_LETTER;
    else  if ('\u1EFA' <= shar && shar <= '\u1EFF')
      return UNASSIGNED;
    else  if ('\u1F00' <= shar && shar <= '\u1F07')
      return LOWERCASE_LETTER;
    else  if ('\u1F08' <= shar && shar <= '\u1F0F')
      return UPPERCASE_LETTER;
    else  if ('\u1F10' <= shar && shar <= '\u1F15')
      return LOWERCASE_LETTER;
    else  if ('\u1F16' <= shar && shar <= '\u1F17')
      return UNASSIGNED;
    else  if ('\u1F18' <= shar && shar <= '\u1F1D')
      return UPPERCASE_LETTER;
    else  if ('\u1F1E' <= shar && shar <= '\u1F1F')
      return UNASSIGNED;
    else  if ('\u1F20' <= shar && shar <= '\u1F27')
      return LOWERCASE_LETTER;
    else  if ('\u1F28' <= shar && shar <= '\u1F2F')
      return UPPERCASE_LETTER;
    else  if ('\u1F30' <= shar && shar <= '\u1F37')
      return LOWERCASE_LETTER;
    else  if ('\u1F38' <= shar && shar <= '\u1F3F')
      return UPPERCASE_LETTER;
    else  if ('\u1F40' <= shar && shar <= '\u1F45')
      return LOWERCASE_LETTER;
    else  if ('\u1F46' <= shar && shar <= '\u1F47')
      return UNASSIGNED;
    else  if ('\u1F48' <= shar && shar <= '\u1F4D')
      return UPPERCASE_LETTER;
    else  if ('\u1F4E' <= shar && shar <= '\u1F4F')
      return UNASSIGNED;
    else  if ('\u1F50' <= shar && shar <= '\u1F57')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1F58')
      return UNASSIGNED;
    else  if (shar == '\u1F59')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1F5A')
      return UNASSIGNED;
    else  if (shar == '\u1F5B')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1F5C')
      return UNASSIGNED;
    else  if (shar == '\u1F5D')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1F5E')
      return UNASSIGNED;
    else  if (shar == '\u1F5F')
      return UPPERCASE_LETTER;
    else  if ('\u1F60' <= shar && shar <= '\u1F67')
      return LOWERCASE_LETTER;
    else  if ('\u1F68' <= shar && shar <= '\u1F6F')
      return UPPERCASE_LETTER;
    else  if ('\u1F70' <= shar && shar <= '\u1F7D')
      return LOWERCASE_LETTER;
    else  if ('\u1F7E' <= shar && shar <= '\u1F7F')
      return UNASSIGNED;
    else  if ('\u1F80' <= shar && shar <= '\u1F87')
      return LOWERCASE_LETTER;
    else  if ('\u1F88' <= shar && shar <= '\u1F8F')
      return UPPERCASE_LETTER;
    else  if ('\u1F90' <= shar && shar <= '\u1F97')
      return LOWERCASE_LETTER;
    else  if ('\u1F98' <= shar && shar <= '\u1F9F')
      return UPPERCASE_LETTER;
    else  if ('\u1FA0' <= shar && shar <= '\u1FA7')
      return LOWERCASE_LETTER;
    else  if ('\u1FA8' <= shar && shar <= '\u1FAF')
      return UPPERCASE_LETTER;
    else  if ('\u1FB0' <= shar && shar <= '\u1FB4')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1FB5')
      return UNASSIGNED;
    else  if ('\u1FB6' <= shar && shar <= '\u1FB7')
      return LOWERCASE_LETTER;
    else  if ('\u1FB8' <= shar && shar <= '\u1FBC')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1FBD')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u1FBE')
      return UPPERCASE_LETTER;
    else  if ('\u1FBF' <= shar && shar <= '\u1FC1')
      return MODIFIER_SYMBOL;
    else  if ('\u1FC2' <= shar && shar <= '\u1FC4')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1FC5')
      return UNASSIGNED;
    else  if ('\u1FC6' <= shar && shar <= '\u1FC7')
      return LOWERCASE_LETTER;
    else  if ('\u1FC8' <= shar && shar <= '\u1FCC')
      return UPPERCASE_LETTER;
    else  if ('\u1FCD' <= shar && shar <= '\u1FCF')
      return MODIFIER_SYMBOL;
    else  if ('\u1FD0' <= shar && shar <= '\u1FD3')
      return LOWERCASE_LETTER;
    else  if ('\u1FD4' <= shar && shar <= '\u1FD5')
      return UNASSIGNED;
    else  if ('\u1FD6' <= shar && shar <= '\u1FD7')
      return LOWERCASE_LETTER;
    else  if ('\u1FD8' <= shar && shar <= '\u1FDB')
      return UPPERCASE_LETTER;
    else  if (shar == '\u1FDC')
      return UNASSIGNED;
    else  if ('\u1FDD' <= shar && shar <= '\u1FDF')
      return MODIFIER_SYMBOL;
    else  if ('\u1FE0' <= shar && shar <= '\u1FE7')
      return LOWERCASE_LETTER;
    else  if ('\u1FE8' <= shar && shar <= '\u1FEC')
      return UPPERCASE_LETTER;
    else  if ('\u1FED' <= shar && shar <= '\u1FEF')
      return MODIFIER_SYMBOL;
    else  if ('\u1FF0' <= shar && shar <= '\u1FF1')
      return UNASSIGNED;
    else  if ('\u1FF2' <= shar && shar <= '\u1FF4')
      return LOWERCASE_LETTER;
    else  if (shar == '\u1FF5')
      return UNASSIGNED;
    else  if ('\u1FF6' <= shar && shar <= '\u1FF7')
      return LOWERCASE_LETTER;
    else  if ('\u1FF8' <= shar && shar <= '\u1FFC')
      return UPPERCASE_LETTER;
    else  if ('\u1FFD' <= shar && shar <= '\u1FFE')
      return MODIFIER_SYMBOL;
    else  if (shar == '\u1FFF')
      return UNASSIGNED;
    else  if ('\u2000' <= shar && shar <= '\u200B')
      return SPACE_SEPARATOR;
    else  if ('\u200C' <= shar && shar <= '\u200F')
      return FORMAT;
    else  if ('\u2010' <= shar && shar <= '\u2015')
      return DASH_PUNCTUATION;
    else  if ('\u2016' <= shar && shar <= '\u2017')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u2018')
      return START_PUNCTUATION;
    else  if (shar == '\u2019')
      return END_PUNCTUATION;
    else  if ('\u201A' <= shar && shar <= '\u201C')
      return START_PUNCTUATION;
    else  if (shar == '\u201D')
      return END_PUNCTUATION;
    else  if ('\u201E' <= shar && shar <= '\u201F')
      return START_PUNCTUATION;
    else  if ('\u2020' <= shar && shar <= '\u2027')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u2028')
      return LINE_SEPARATOR;
    else  if (shar == '\u2029')
      return PARAGRAPH_SEPARATOR;
    else  if ('\u202A' <= shar && shar <= '\u202E')
      return FORMAT;
    else  if (shar == '\u202F')
      return UNASSIGNED;
    else  if ('\u2030' <= shar && shar <= '\u2038')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u2039')
      return START_PUNCTUATION;
    else  if (shar == '\u203A')
      return END_PUNCTUATION;
    else  if ('\u203B' <= shar && shar <= '\u203E')
      return OTHER_PUNCTUATION;
    else  if ('\u203F' <= shar && shar <= '\u2040')
      return CONNECTOR_PUNCTUATION;
    else  if ('\u2041' <= shar && shar <= '\u2043')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u2044')
      return MATH_SYMBOL;
    else  if (shar == '\u2045')
      return START_PUNCTUATION;
    else  if (shar == '\u2046')
      return END_PUNCTUATION;
    else  if ('\u2047' <= shar && shar <= '\u2069')
      return UNASSIGNED;
    else  if ('\u206A' <= shar && shar <= '\u206F')
      return FORMAT;
    else  if (shar == '\u2070')
      return OTHER_NUMBER;
    else  if ('\u2071' <= shar && shar <= '\u2073')
      return UNASSIGNED;
    else  if ('\u2074' <= shar && shar <= '\u2079')
      return OTHER_NUMBER;
    else  if ('\u207A' <= shar && shar <= '\u207C')
      return MATH_SYMBOL;
    else  if (shar == '\u207D')
      return START_PUNCTUATION;
    else  if (shar == '\u207E')
      return END_PUNCTUATION;
    else  if (shar == '\u207F')
      return LOWERCASE_LETTER;
    else  if ('\u2080' <= shar && shar <= '\u2089')
      return OTHER_NUMBER;
    else  if ('\u208A' <= shar && shar <= '\u208C')
      return MATH_SYMBOL;
    else  if (shar == '\u208D')
      return START_PUNCTUATION;
    else  if (shar == '\u208E')
      return END_PUNCTUATION;
    else  if ('\u208F' <= shar && shar <= '\u209F')
      return UNASSIGNED;
    else  if ('\u20A0' <= shar && shar <= '\u20AC')
      return CURRENCY_SYMBOL;
    else  if ('\u20AD' <= shar && shar <= '\u20CF')
      return UNASSIGNED;
    else  if ('\u20D0' <= shar && shar <= '\u20DC')
      return NON_SPACING_MARK;
    else  if ('\u20DD' <= shar && shar <= '\u20E0')
      return ENCLOSING_MARK;
    else  if (shar == '\u20E1')
      return NON_SPACING_MARK;
    else  if ('\u20E2' <= shar && shar <= '\u20FF')
      return UNASSIGNED;
    else  if ('\u2100' <= shar && shar <= '\u2101')
      return OTHER_SYMBOL;
    else  if (shar == '\u2102')
      return UPPERCASE_LETTER;
    else  if ('\u2103' <= shar && shar <= '\u2106')
      return OTHER_SYMBOL;
    else  if (shar == '\u2107')
      return UPPERCASE_LETTER;
    else  if ('\u2108' <= shar && shar <= '\u2109')
      return OTHER_SYMBOL;
    else  if (shar == '\u210A')
      return LOWERCASE_LETTER;
    else  if ('\u210B' <= shar && shar <= '\u210D')
      return UPPERCASE_LETTER;
    else  if ('\u210E' <= shar && shar <= '\u210F')
      return LOWERCASE_LETTER;
    else  if ('\u2110' <= shar && shar <= '\u2112')
      return UPPERCASE_LETTER;
    else  if (shar == '\u2113')
      return LOWERCASE_LETTER;
    else  if (shar == '\u2114')
      return OTHER_SYMBOL;
    else  if (shar == '\u2115')
      return UPPERCASE_LETTER;
    else  if ('\u2116' <= shar && shar <= '\u2117')
      return OTHER_SYMBOL;
    else  if ('\u2118' <= shar && shar <= '\u211D')
      return UPPERCASE_LETTER;
    else  if ('\u211E' <= shar && shar <= '\u2123')
      return OTHER_SYMBOL;
    else  if (shar == '\u2124')
      return UPPERCASE_LETTER;
    else  if (shar == '\u2125')
      return OTHER_SYMBOL;
    else  if (shar == '\u2126')
      return UPPERCASE_LETTER;
    else  if (shar == '\u2127')
      return OTHER_SYMBOL;
    else  if (shar == '\u2128')
      return UPPERCASE_LETTER;
    else  if (shar == '\u2129')
      return OTHER_SYMBOL;
    else  if ('\u212A' <= shar && shar <= '\u212D')
      return UPPERCASE_LETTER;
    else  if ('\u212E' <= shar && shar <= '\u212F')
      return LOWERCASE_LETTER;
    else  if ('\u2130' <= shar && shar <= '\u2131')
      return UPPERCASE_LETTER;
    else  if (shar == '\u2132')
      return OTHER_SYMBOL;
    else  if (shar == '\u2133')
      return UPPERCASE_LETTER;
    else  if (shar == '\u2134')
      return LOWERCASE_LETTER;
    else  if ('\u2135' <= shar && shar <= '\u2138')
      return OTHER_LETTER;
    else  if ('\u2139' <= shar && shar <= '\u2152')
      return UNASSIGNED;
    else  if ('\u2153' <= shar && shar <= '\u215F')
      return OTHER_NUMBER;
    else  if ('\u2160' <= shar && shar <= '\u2182')
      return LETTER_NUMBER;
    else  if ('\u2183' <= shar && shar <= '\u218F')
      return UNASSIGNED;
    else  if ('\u2190' <= shar && shar <= '\u2194')
      return MATH_SYMBOL;
    else  if ('\u2195' <= shar && shar <= '\u21D1')
      return OTHER_SYMBOL;
    else  if (shar == '\u21D2')
      return MATH_SYMBOL;
    else  if (shar == '\u21D3')
      return OTHER_SYMBOL;
    else  if (shar == '\u21D4')
      return MATH_SYMBOL;
    else  if ('\u21D5' <= shar && shar <= '\u21EA')
      return OTHER_SYMBOL;
    else  if ('\u21EB' <= shar && shar <= '\u21FF')
      return UNASSIGNED;
    else  if ('\u2200' <= shar && shar <= '\u22F1')
      return MATH_SYMBOL;
    else  if ('\u22F2' <= shar && shar <= '\u22FF')
      return UNASSIGNED;
    else  if (shar == '\u2300')
      return OTHER_SYMBOL;
    else  if (shar == '\u2301')
      return UNASSIGNED;
    else  if ('\u2302' <= shar && shar <= '\u2307')
      return OTHER_SYMBOL;
    else  if ('\u2308' <= shar && shar <= '\u230B')
      return MATH_SYMBOL;
    else  if ('\u230C' <= shar && shar <= '\u231F')
      return OTHER_SYMBOL;
    else  if ('\u2320' <= shar && shar <= '\u2321')
      return MATH_SYMBOL;
    else  if ('\u2322' <= shar && shar <= '\u2328')
      return OTHER_SYMBOL;
    else  if (shar == '\u2329')
      return START_PUNCTUATION;
    else  if (shar == '\u232A')
      return END_PUNCTUATION;
    else  if ('\u232B' <= shar && shar <= '\u237A')
      return OTHER_SYMBOL;
    else  if ('\u237B' <= shar && shar <= '\u23FF')
      return UNASSIGNED;
    else  if ('\u2400' <= shar && shar <= '\u2424')
      return OTHER_SYMBOL;
    else  if ('\u2425' <= shar && shar <= '\u243F')
      return UNASSIGNED;
    else  if ('\u2440' <= shar && shar <= '\u244A')
      return OTHER_SYMBOL;
    else  if ('\u244B' <= shar && shar <= '\u245F')
      return UNASSIGNED;
    else  if ('\u2460' <= shar && shar <= '\u249B')
      return OTHER_NUMBER;
    else  if ('\u249C' <= shar && shar <= '\u24E9')
      return OTHER_SYMBOL;
    else  if (shar == '\u24EA')
      return OTHER_NUMBER;
    else  if ('\u24EB' <= shar && shar <= '\u24FF')
      return UNASSIGNED;
    else  if ('\u2500' <= shar && shar <= '\u2595')
      return OTHER_SYMBOL;
    else  if ('\u2596' <= shar && shar <= '\u259F')
      return UNASSIGNED;
    else  if ('\u25A0' <= shar && shar <= '\u25EF')
      return OTHER_SYMBOL;
    else  if ('\u25F0' <= shar && shar <= '\u25FF')
      return UNASSIGNED;
    else  if ('\u2600' <= shar && shar <= '\u2613')
      return OTHER_SYMBOL;
    else  if ('\u2614' <= shar && shar <= '\u2619')
      return UNASSIGNED;
    else  if ('\u261A' <= shar && shar <= '\u266F')
      return OTHER_SYMBOL;
    else  if ('\u2670' <= shar && shar <= '\u2700')
      return UNASSIGNED;
    else  if ('\u2701' <= shar && shar <= '\u2704')
      return OTHER_SYMBOL;
    else  if (shar == '\u2705')
      return UNASSIGNED;
    else  if ('\u2706' <= shar && shar <= '\u2709')
      return OTHER_SYMBOL;
    else  if ('\u270A' <= shar && shar <= '\u270B')
      return UNASSIGNED;
    else  if ('\u270C' <= shar && shar <= '\u2727')
      return OTHER_SYMBOL;
    else  if (shar == '\u2728')
      return UNASSIGNED;
    else  if ('\u2729' <= shar && shar <= '\u274B')
      return OTHER_SYMBOL;
    else  if (shar == '\u274C')
      return UNASSIGNED;
    else  if (shar == '\u274D')
      return OTHER_SYMBOL;
    else  if (shar == '\u274E')
      return UNASSIGNED;
    else  if ('\u274F' <= shar && shar <= '\u2752')
      return OTHER_SYMBOL;
    else  if ('\u2753' <= shar && shar <= '\u2755')
      return UNASSIGNED;
    else  if (shar == '\u2756')
      return OTHER_SYMBOL;
    else  if (shar == '\u2757')
      return UNASSIGNED;
    else  if ('\u2758' <= shar && shar <= '\u275E')
      return OTHER_SYMBOL;
    else  if ('\u275F' <= shar && shar <= '\u2760')
      return UNASSIGNED;
    else  if ('\u2761' <= shar && shar <= '\u2767')
      return OTHER_SYMBOL;
    else  if ('\u2768' <= shar && shar <= '\u2775')
      return UNASSIGNED;
    else  if ('\u2776' <= shar && shar <= '\u2793')
      return OTHER_NUMBER;
    else  if (shar == '\u2794')
      return OTHER_SYMBOL;
    else  if ('\u2795' <= shar && shar <= '\u2797')
      return UNASSIGNED;
    else  if ('\u2798' <= shar && shar <= '\u27AF')
      return OTHER_SYMBOL;
    else  if (shar == '\u27B0')
      return UNASSIGNED;
    else  if ('\u27B1' <= shar && shar <= '\u27BE')
      return OTHER_SYMBOL;
    else  if ('\u27BF' <= shar && shar <= '\u2FFF')
      return UNASSIGNED;
    else  if (shar == '\u3000')
      return SPACE_SEPARATOR;
    else  if ('\u3001' <= shar && shar <= '\u3003')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u3004')
      return OTHER_SYMBOL;
    else  if (shar == '\u3005')
      return MODIFIER_LETTER;
    else  if (shar == '\u3006')
      return OTHER_PUNCTUATION;
    else  if (shar == '\u3007')
      return LETTER_NUMBER;
    else  if (shar == '\u3008')
      return START_PUNCTUATION;
    else  if (shar == '\u3009')
      return END_PUNCTUATION;
    else  if (shar == '\u300A')
      return START_PUNCTUATION;
    else  if (shar == '\u300B')
      return END_PUNCTUATION;
    else  if (shar == '\u300C')
      return START_PUNCTUATION;
    else  if (shar == '\u300D')
      return END_PUNCTUATION;
    else  if (shar == '\u300E')
      return START_PUNCTUATION;
    else  if (shar == '\u300F')
      return END_PUNCTUATION;
    else  if (shar == '\u3010')
      return START_PUNCTUATION;
    else  if (shar == '\u3011')
      return END_PUNCTUATION;
    else  if ('\u3012' <= shar && shar <= '\u3013')
      return OTHER_SYMBOL;
    else  if (shar == '\u3014')
      return START_PUNCTUATION;
    else  if (shar == '\u3015')
      return END_PUNCTUATION;
    else  if (shar == '\u3016')
      return START_PUNCTUATION;
    else  if (shar == '\u3017')
      return END_PUNCTUATION;
    else  if (shar == '\u3018')
      return START_PUNCTUATION;
    else  if (shar == '\u3019')
      return END_PUNCTUATION;
    else  if (shar == '\u301A')
      return START_PUNCTUATION;
    else  if (shar == '\u301B')
      return END_PUNCTUATION;
    else  if (shar == '\u301C')
      return DASH_PUNCTUATION;
    else  if (shar == '\u301D')
      return START_PUNCTUATION;
    else  if ('\u301E' <= shar && shar <= '\u301F')
      return END_PUNCTUATION;
    else  if (shar == '\u3020')
      return OTHER_SYMBOL;
    else  if ('\u3021' <= shar && shar <= '\u3029')
      return LETTER_NUMBER;
    else  if ('\u302A' <= shar && shar <= '\u302F')
      return NON_SPACING_MARK;
    else  if (shar == '\u3030')
      return DASH_PUNCTUATION;
    else  if ('\u3031' <= shar && shar <= '\u3035')
      return MODIFIER_LETTER;
    else  if ('\u3036' <= shar && shar <= '\u3037')
      return OTHER_SYMBOL;
    else  if ('\u3038' <= shar && shar <= '\u303E')
      return UNASSIGNED;
    else  if (shar == '\u303F')
      return OTHER_SYMBOL;
    else  if (shar == '\u3040')
      return UNASSIGNED;
    else  if ('\u3041' <= shar && shar <= '\u3094')
      return OTHER_LETTER;
    else  if ('\u3095' <= shar && shar <= '\u3098')
      return UNASSIGNED;
    else  if ('\u3099' <= shar && shar <= '\u309A')
      return NON_SPACING_MARK;
    else  if ('\u309B' <= shar && shar <= '\u309E')
      return MODIFIER_LETTER;
    else  if ('\u309F' <= shar && shar <= '\u30A0')
      return UNASSIGNED;
    else  if ('\u30A1' <= shar && shar <= '\u30FA')
      return OTHER_LETTER;
    else  if (shar == '\u30FB')
      return OTHER_PUNCTUATION;
    else  if ('\u30FC' <= shar && shar <= '\u30FE')
      return MODIFIER_LETTER;
    else  if ('\u30FF' <= shar && shar <= '\u3104')
      return UNASSIGNED;
    else  if ('\u3105' <= shar && shar <= '\u312C')
      return OTHER_LETTER;
    else  if ('\u312D' <= shar && shar <= '\u3130')
      return UNASSIGNED;
    else  if ('\u3131' <= shar && shar <= '\u318E')
      return OTHER_LETTER;
    else  if (shar == '\u318F')
      return UNASSIGNED;
    else  if ('\u3190' <= shar && shar <= '\u3191')
      return OTHER_SYMBOL;
    else  if ('\u3192' <= shar && shar <= '\u3195')
      return OTHER_NUMBER;
    else  if ('\u3196' <= shar && shar <= '\u319F')
      return OTHER_SYMBOL;
    else  if ('\u31A0' <= shar && shar <= '\u31FF')
      return UNASSIGNED;
    else  if ('\u3200' <= shar && shar <= '\u321C')
      return OTHER_SYMBOL;
    else  if ('\u321D' <= shar && shar <= '\u321F')
      return UNASSIGNED;
    else  if ('\u3220' <= shar && shar <= '\u3229')
      return OTHER_NUMBER;
    else  if ('\u322A' <= shar && shar <= '\u3243')
      return OTHER_SYMBOL;
    else  if ('\u3244' <= shar && shar <= '\u325F')
      return UNASSIGNED;
    else  if ('\u3260' <= shar && shar <= '\u327B')
      return OTHER_SYMBOL;
    else  if ('\u327C' <= shar && shar <= '\u327E')
      return UNASSIGNED;
    else  if (shar == '\u327F')
      return OTHER_SYMBOL;
    else  if ('\u3280' <= shar && shar <= '\u3289')
      return OTHER_NUMBER;
    else  if ('\u328A' <= shar && shar <= '\u32B0')
      return OTHER_SYMBOL;
    else  if ('\u32B1' <= shar && shar <= '\u32BF')
      return UNASSIGNED;
    else  if ('\u32C0' <= shar && shar <= '\u32CB')
      return OTHER_SYMBOL;
    else  if ('\u32CC' <= shar && shar <= '\u32CF')
      return UNASSIGNED;
    else  if ('\u32D0' <= shar && shar <= '\u32FE')
      return OTHER_SYMBOL;
    else  if (shar == '\u32FF')
      return UNASSIGNED;
    else  if ('\u3300' <= shar && shar <= '\u3376')
      return OTHER_SYMBOL;
    else  if ('\u3377' <= shar && shar <= '\u337A')
      return UNASSIGNED;
    else  if ('\u337B' <= shar && shar <= '\u33DD')
      return OTHER_SYMBOL;
    else  if ('\u33DE' <= shar && shar <= '\u33DF')
      return UNASSIGNED;
    else  if ('\u33E0' <= shar && shar <= '\u33FE')
      return OTHER_SYMBOL;
    else  if ('\u33FF' <= shar && shar <= '\u4DFF')
      return UNASSIGNED;
    else  if ('\u4E00' <= shar && shar <= '\u9FA5')
      return OTHER_LETTER;
    else  if ('\u9FA6' <= shar && shar <= '\uABFF')
      return UNASSIGNED;
    else  if ('\uAC00' <= shar && shar <= '\uD7A3')
      return OTHER_LETTER;
    else  if ('\uD7A4' <= shar && shar <= '\uD7FF')
      return UNASSIGNED;
    else  if ('\uD800' <= shar && shar <= '\uDFFF')
      return SURROGATE;
    else  if ('\uE000' <= shar && shar <= '\uF8FF')
      return PRIVATE_USE;
    else  if ('\uF900' <= shar && shar <= '\uFA2D')
      return OTHER_LETTER;
    else  if ('\uFA2E' <= shar && shar <= '\uFAFF')
      return UNASSIGNED;
    else  if ('\uFB00' <= shar && shar <= '\uFB06')
      return LOWERCASE_LETTER;
    else  if ('\uFB07' <= shar && shar <= '\uFB12')
      return UNASSIGNED;
    else  if ('\uFB13' <= shar && shar <= '\uFB17')
      return LOWERCASE_LETTER;
    else  if ('\uFB18' <= shar && shar <= '\uFB1D')
      return UNASSIGNED;
    else  if (shar == '\uFB1E')
      return NON_SPACING_MARK;
    else  if ('\uFB1F' <= shar && shar <= '\uFB28')
      return OTHER_LETTER;
    else  if (shar == '\uFB29')
      return MATH_SYMBOL;
    else  if ('\uFB2A' <= shar && shar <= '\uFB36')
      return OTHER_LETTER;
    else  if (shar == '\uFB37')
      return UNASSIGNED;
    else  if ('\uFB38' <= shar && shar <= '\uFB3C')
      return OTHER_LETTER;
    else  if (shar == '\uFB3D')
      return UNASSIGNED;
    else  if (shar == '\uFB3E')
      return OTHER_LETTER;
    else  if (shar == '\uFB3F')
      return UNASSIGNED;
    else  if ('\uFB40' <= shar && shar <= '\uFB41')
      return OTHER_LETTER;
    else  if (shar == '\uFB42')
      return UNASSIGNED;
    else  if ('\uFB43' <= shar && shar <= '\uFB44')
      return OTHER_LETTER;
    else  if (shar == '\uFB45')
      return UNASSIGNED;
    else  if ('\uFB46' <= shar && shar <= '\uFBB1')
      return OTHER_LETTER;
    else  if ('\uFBB2' <= shar && shar <= '\uFBD2')
      return UNASSIGNED;
    else  if ('\uFBD3' <= shar && shar <= '\uFD3D')
      return OTHER_LETTER;
    else  if (shar == '\uFD3E')
      return START_PUNCTUATION;
    else  if (shar == '\uFD3F')
      return END_PUNCTUATION;
    else  if ('\uFD40' <= shar && shar <= '\uFD4F')
      return UNASSIGNED;
    else  if ('\uFD50' <= shar && shar <= '\uFD8F')
      return OTHER_LETTER;
    else  if ('\uFD90' <= shar && shar <= '\uFD91')
      return UNASSIGNED;
    else  if ('\uFD92' <= shar && shar <= '\uFDC7')
      return OTHER_LETTER;
    else  if ('\uFDC8' <= shar && shar <= '\uFDEF')
      return UNASSIGNED;
    else  if ('\uFDF0' <= shar && shar <= '\uFDFB')
      return OTHER_LETTER;
    else  if ('\uFDFC' <= shar && shar <= '\uFE1F')
      return UNASSIGNED;
    else  if ('\uFE20' <= shar && shar <= '\uFE23')
      return NON_SPACING_MARK;
    else  if ('\uFE24' <= shar && shar <= '\uFE2F')
      return UNASSIGNED;
    else  if (shar == '\uFE30')
      return OTHER_PUNCTUATION;
    else  if ('\uFE31' <= shar && shar <= '\uFE32')
      return DASH_PUNCTUATION;
    else  if ('\uFE33' <= shar && shar <= '\uFE34')
      return CONNECTOR_PUNCTUATION;
    else  if (shar == '\uFE35')
      return START_PUNCTUATION;
    else  if (shar == '\uFE36')
      return END_PUNCTUATION;
    else  if (shar == '\uFE37')
      return START_PUNCTUATION;
    else  if (shar == '\uFE38')
      return END_PUNCTUATION;
    else  if (shar == '\uFE39')
      return START_PUNCTUATION;
    else  if (shar == '\uFE3A')
      return END_PUNCTUATION;
    else  if (shar == '\uFE3B')
      return START_PUNCTUATION;
    else  if (shar == '\uFE3C')
      return END_PUNCTUATION;
    else  if (shar == '\uFE3D')
      return START_PUNCTUATION;
    else  if (shar == '\uFE3E')
      return END_PUNCTUATION;
    else  if (shar == '\uFE3F')
      return START_PUNCTUATION;
    else  if (shar == '\uFE40')
      return END_PUNCTUATION;
    else  if (shar == '\uFE41')
      return START_PUNCTUATION;
    else  if (shar == '\uFE42')
      return END_PUNCTUATION;
    else  if (shar == '\uFE43')
      return START_PUNCTUATION;
    else  if (shar == '\uFE44')
      return END_PUNCTUATION;
    else  if ('\uFE45' <= shar && shar <= '\uFE48')
      return UNASSIGNED;
    else  if ('\uFE49' <= shar && shar <= '\uFE4C')
      return OTHER_PUNCTUATION;
    else  if ('\uFE4D' <= shar && shar <= '\uFE4F')
      return CONNECTOR_PUNCTUATION;
    else  if ('\uFE50' <= shar && shar <= '\uFE52')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFE53')
      return UNASSIGNED;
    else  if ('\uFE54' <= shar && shar <= '\uFE57')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFE58')
      return DASH_PUNCTUATION;
    else  if (shar == '\uFE59')
      return START_PUNCTUATION;
    else  if (shar == '\uFE5A')
      return END_PUNCTUATION;
    else  if (shar == '\uFE5B')
      return START_PUNCTUATION;
    else  if (shar == '\uFE5C')
      return END_PUNCTUATION;
    else  if (shar == '\uFE5D')
      return START_PUNCTUATION;
    else  if (shar == '\uFE5E')
      return END_PUNCTUATION;
    else  if ('\uFE5F' <= shar && shar <= '\uFE61')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFE62')
      return MATH_SYMBOL;
    else  if (shar == '\uFE63')
      return DASH_PUNCTUATION;
    else  if ('\uFE64' <= shar && shar <= '\uFE66')
      return MATH_SYMBOL;
    else  if (shar == '\uFE67')
      return UNASSIGNED;
    else  if (shar == '\uFE68')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFE69')
      return CURRENCY_SYMBOL;
    else  if ('\uFE6A' <= shar && shar <= '\uFE6B')
      return OTHER_PUNCTUATION;
    else  if ('\uFE6C' <= shar && shar <= '\uFE6F')
      return UNASSIGNED;
    else  if ('\uFE70' <= shar && shar <= '\uFE72')
      return OTHER_LETTER;
    else  if (shar == '\uFE73')
      return UNASSIGNED;
    else  if (shar == '\uFE74')
      return OTHER_LETTER;
    else  if (shar == '\uFE75')
      return UNASSIGNED;
    else  if ('\uFE76' <= shar && shar <= '\uFEFC')
      return OTHER_LETTER;
    else  if ('\uFEFD' <= shar && shar <= '\uFEFE')
      return UNASSIGNED;
    else  if (shar == '\uFEFF')
      return FORMAT;
    else  if (shar == '\uFF00')
      return UNASSIGNED;
    else  if ('\uFF01' <= shar && shar <= '\uFF03')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFF04')
      return CURRENCY_SYMBOL;
    else  if ('\uFF05' <= shar && shar <= '\uFF07')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFF08')
      return START_PUNCTUATION;
    else  if (shar == '\uFF09')
      return END_PUNCTUATION;
    else  if (shar == '\uFF0A')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFF0B')
      return MATH_SYMBOL;
    else  if (shar == '\uFF0C')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFF0D')
      return DASH_PUNCTUATION;
    else  if ('\uFF0E' <= shar && shar <= '\uFF0F')
      return OTHER_PUNCTUATION;
    else  if ('\uFF10' <= shar && shar <= '\uFF19')
      return DECIMAL_DIGIT_NUMBER;
    else  if ('\uFF1A' <= shar && shar <= '\uFF1B')
      return OTHER_PUNCTUATION;
    else  if ('\uFF1C' <= shar && shar <= '\uFF1E')
      return MATH_SYMBOL;
    else  if ('\uFF1F' <= shar && shar <= '\uFF20')
      return OTHER_PUNCTUATION;
    else  if ('\uFF21' <= shar && shar <= '\uFF3A')
      return UPPERCASE_LETTER;
    else  if (shar == '\uFF3B')
      return START_PUNCTUATION;
    else  if (shar == '\uFF3C')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFF3D')
      return END_PUNCTUATION;
    else  if (shar == '\uFF3E')
      return MODIFIER_SYMBOL;
    else  if (shar == '\uFF3F')
      return CONNECTOR_PUNCTUATION;
    else  if (shar == '\uFF40')
      return MODIFIER_SYMBOL;
    else  if ('\uFF41' <= shar && shar <= '\uFF5A')
      return LOWERCASE_LETTER;
    else  if (shar == '\uFF5B')
      return START_PUNCTUATION;
    else  if (shar == '\uFF5C')
      return MATH_SYMBOL;
    else  if (shar == '\uFF5D')
      return END_PUNCTUATION;
    else  if (shar == '\uFF5E')
      return MATH_SYMBOL;
    else  if ('\uFF5F' <= shar && shar <= '\uFF60')
      return UNASSIGNED;
    else  if (shar == '\uFF61')
      return OTHER_PUNCTUATION;
    else  if (shar == '\uFF62')
      return START_PUNCTUATION;
    else  if (shar == '\uFF63')
      return END_PUNCTUATION;
    else  if ('\uFF64' <= shar && shar <= '\uFF65')
      return OTHER_PUNCTUATION;
    else  if ('\uFF66' <= shar && shar <= '\uFF6F')
      return OTHER_LETTER;
    else  if (shar == '\uFF70')
      return MODIFIER_LETTER;
    else  if ('\uFF71' <= shar && shar <= '\uFF9D')
      return OTHER_LETTER;
    else  if ('\uFF9E' <= shar && shar <= '\uFF9F')
      return MODIFIER_LETTER;
    else  if ('\uFFA0' <= shar && shar <= '\uFFBE')
      return OTHER_LETTER;
    else  if ('\uFFBF' <= shar && shar <= '\uFFC1')
      return UNASSIGNED;
    else  if ('\uFFC2' <= shar && shar <= '\uFFC7')
      return OTHER_LETTER;
    else  if ('\uFFC8' <= shar && shar <= '\uFFC9')
      return UNASSIGNED;
    else  if ('\uFFCA' <= shar && shar <= '\uFFCF')
      return OTHER_LETTER;
    else  if ('\uFFD0' <= shar && shar <= '\uFFD1')
      return UNASSIGNED;
    else  if ('\uFFD2' <= shar && shar <= '\uFFD7')
      return OTHER_LETTER;
    else  if ('\uFFD8' <= shar && shar <= '\uFFD9')
      return UNASSIGNED;
    else  if ('\uFFDA' <= shar && shar <= '\uFFDC')
      return OTHER_LETTER;
    else  if ('\uFFDD' <= shar && shar <= '\uFFDF')
      return UNASSIGNED;
    else  if ('\uFFE0' <= shar && shar <= '\uFFE1')
      return CURRENCY_SYMBOL;
    else  if (shar == '\uFFE2')
      return MATH_SYMBOL;
    else  if (shar == '\uFFE3')
      return MODIFIER_SYMBOL;
    else  if (shar == '\uFFE4')
      return OTHER_SYMBOL;
    else  if ('\uFFE5' <= shar && shar <= '\uFFE6')
      return CURRENCY_SYMBOL;
    else  if (shar == '\uFFE7')
      return UNASSIGNED;
    else  if ('\uFFE8' <= shar && shar <= '\uFFEC')
      return MATH_SYMBOL;
    else  if ('\uFFED' <= shar && shar <= '\uFFEE')
      return OTHER_SYMBOL;
    else  if ('\uFFEF' <= shar && shar <= '\uFFFB')
      return UNASSIGNED;
    else
      return OTHER_SYMBOL;
  }

  public static boolean isLowerCase(char shar) {
    return ('\u0061' <= shar && shar <= '\u007A')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00DF' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u00FF')
        || '\u0101' == shar || '\u0103' == shar
        || '\u0105' == shar || '\u0107' == shar
        || '\u0109' == shar || '\u010B' == shar
        || '\u010D' == shar || '\u010F' == shar
        || '\u0111' == shar || '\u0113' == shar
        || '\u0115' == shar || '\u0117' == shar
        || '\u0119' == shar || '\u011B' == shar
        || '\u011D' == shar || '\u011F' == shar
        || '\u0121' == shar || '\u0123' == shar
        || '\u0125' == shar || '\u0127' == shar
        || '\u0129' == shar || '\u012B' == shar
        || '\u012D' == shar || '\u012F' == shar
        || '\u0131' == shar || '\u0133' == shar
        || '\u0135' == shar 
        || ('\u0137' <= shar && shar <= '\u0138')
        || '\u013A' == shar || '\u013C' == shar
        || '\u013E' == shar || '\u0140' == shar
        || '\u0142' == shar || '\u0144' == shar
        || '\u0146' == shar 
        || ('\u0148' <= shar && shar <= '\u0149')
        || '\u014B' == shar || '\u014D' == shar
        || '\u014F' == shar || '\u0151' == shar
        || '\u0153' == shar || '\u0155' == shar
        || '\u0157' == shar || '\u0159' == shar
        || '\u015B' == shar || '\u015D' == shar
        || '\u015F' == shar || '\u0161' == shar
        || '\u0163' == shar || '\u0165' == shar
        || '\u0167' == shar || '\u0169' == shar
        || '\u016B' == shar || '\u016D' == shar
        || '\u016F' == shar || '\u0171' == shar
        || '\u0173' == shar || '\u0175' == shar
        || '\u0177' == shar || '\u017A' == shar
        || '\u017C' == shar 
        || ('\u017E' <= shar && shar <= '\u0180')
        || '\u0183' == shar || '\u0185' == shar
        || '\u0188' == shar 
        || ('\u018C' <= shar && shar <= '\u018D')
        || '\u0192' == shar || '\u0195' == shar
        || ('\u0199' <= shar && shar <= '\u019B')
        || '\u019E' == shar || '\u01A1' == shar
        || '\u01A3' == shar || '\u01A5' == shar
        || '\u01A8' == shar || '\u01AB' == shar
        || '\u01AD' == shar || '\u01B0' == shar
        || '\u01B4' == shar || '\u01B6' == shar
        || ('\u01B9' <= shar && shar <= '\u01BA')
        || '\u01BD' == shar || '\u01C6' == shar
        || '\u01C9' == shar || '\u01CC' == shar
        || '\u01CE' == shar || '\u01D0' == shar
        || '\u01D2' == shar || '\u01D4' == shar
        || '\u01D6' == shar || '\u01D8' == shar
        || '\u01DA' == shar 
        || ('\u01DC' <= shar && shar <= '\u01DD')
        || '\u01DF' == shar || '\u01E1' == shar
        || '\u01E3' == shar || '\u01E5' == shar
        || '\u01E7' == shar || '\u01E9' == shar
        || '\u01EB' == shar || '\u01ED' == shar
        || ('\u01EF' <= shar && shar <= '\u01F0')
        || '\u01F3' == shar || '\u01F5' == shar
        || '\u01FB' == shar || '\u01FD' == shar
        || '\u01FF' == shar || '\u0201' == shar
        || '\u0203' == shar || '\u0205' == shar
        || '\u0207' == shar || '\u0209' == shar
        || '\u020B' == shar || '\u020D' == shar
        || '\u020F' == shar || '\u0211' == shar
        || '\u0213' == shar || '\u0215' == shar
        || '\u0217' == shar 
        || ('\u0250' <= shar && shar <= '\u02A8')
        || '\u0390' == shar 
        || ('\u03AC' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D1')
        || ('\u03D5' <= shar && shar <= '\u03D6')
        || '\u03E3' == shar || '\u03E5' == shar
        || '\u03E7' == shar || '\u03E9' == shar
        || '\u03EB' == shar || '\u03ED' == shar
        || ('\u03EF' <= shar && shar <= '\u03F2')
        || ('\u0430' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u045F')
        || '\u0461' == shar || '\u0463' == shar
        || '\u0465' == shar || '\u0467' == shar
        || '\u0469' == shar || '\u046B' == shar
        || '\u046D' == shar || '\u046F' == shar
        || '\u0471' == shar || '\u0473' == shar
        || '\u0475' == shar || '\u0477' == shar
        || '\u0479' == shar || '\u047B' == shar
        || '\u047D' == shar || '\u047F' == shar
        || '\u0481' == shar || '\u0491' == shar
        || '\u0493' == shar || '\u0495' == shar
        || '\u0497' == shar || '\u0499' == shar
        || '\u049B' == shar || '\u049D' == shar
        || '\u049F' == shar || '\u04A1' == shar
        || '\u04A3' == shar || '\u04A5' == shar
        || '\u04A7' == shar || '\u04A9' == shar
        || '\u04AB' == shar || '\u04AD' == shar
        || '\u04AF' == shar || '\u04B1' == shar
        || '\u04B3' == shar || '\u04B5' == shar
        || '\u04B7' == shar || '\u04B9' == shar
        || '\u04BB' == shar || '\u04BD' == shar
        || '\u04BF' == shar || '\u04C2' == shar
        || '\u04C4' == shar || '\u04C8' == shar
        || '\u04CC' == shar || '\u04D1' == shar
        || '\u04D3' == shar || '\u04D5' == shar
        || '\u04D7' == shar || '\u04D9' == shar
        || '\u04DB' == shar || '\u04DD' == shar
        || '\u04DF' == shar || '\u04E1' == shar
        || '\u04E3' == shar || '\u04E5' == shar
        || '\u04E7' == shar || '\u04E9' == shar
        || '\u04EB' == shar || '\u04EF' == shar
        || '\u04F1' == shar || '\u04F3' == shar
        || '\u04F5' == shar || '\u04F9' == shar
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || '\u1E01' == shar || '\u1E03' == shar
        || '\u1E05' == shar || '\u1E07' == shar
        || '\u1E09' == shar || '\u1E0B' == shar
        || '\u1E0D' == shar || '\u1E0F' == shar
        || '\u1E11' == shar || '\u1E13' == shar
        || '\u1E15' == shar || '\u1E17' == shar
        || '\u1E19' == shar || '\u1E1B' == shar
        || '\u1E1D' == shar || '\u1E1F' == shar
        || '\u1E21' == shar || '\u1E23' == shar
        || '\u1E25' == shar || '\u1E27' == shar
        || '\u1E29' == shar || '\u1E2B' == shar
        || '\u1E2D' == shar || '\u1E2F' == shar
        || '\u1E31' == shar || '\u1E33' == shar
        || '\u1E35' == shar || '\u1E37' == shar
        || '\u1E39' == shar || '\u1E3B' == shar
        || '\u1E3D' == shar || '\u1E3F' == shar
        || '\u1E41' == shar || '\u1E43' == shar
        || '\u1E45' == shar || '\u1E47' == shar
        || '\u1E49' == shar || '\u1E4B' == shar
        || '\u1E4D' == shar || '\u1E4F' == shar
        || '\u1E51' == shar || '\u1E53' == shar
        || '\u1E55' == shar || '\u1E57' == shar
        || '\u1E59' == shar || '\u1E5B' == shar
        || '\u1E5D' == shar || '\u1E5F' == shar
        || '\u1E61' == shar || '\u1E63' == shar
        || '\u1E65' == shar || '\u1E67' == shar
        || '\u1E69' == shar || '\u1E6B' == shar
        || '\u1E6D' == shar || '\u1E6F' == shar
        || '\u1E71' == shar || '\u1E73' == shar
        || '\u1E75' == shar || '\u1E77' == shar
        || '\u1E79' == shar || '\u1E7B' == shar
        || '\u1E7D' == shar || '\u1E7F' == shar
        || '\u1E81' == shar || '\u1E83' == shar
        || '\u1E85' == shar || '\u1E87' == shar
        || '\u1E89' == shar || '\u1E8B' == shar
        || '\u1E8D' == shar || '\u1E8F' == shar
        || '\u1E91' == shar || '\u1E93' == shar
        || ('\u1E95' <= shar && shar <= '\u1E9B')
        || '\u1EA1' == shar || '\u1EA3' == shar
        || '\u1EA5' == shar || '\u1EA7' == shar
        || '\u1EA9' == shar || '\u1EAB' == shar
        || '\u1EAD' == shar || '\u1EAF' == shar
        || '\u1EB1' == shar || '\u1EB3' == shar
        || '\u1EB5' == shar || '\u1EB7' == shar
        || '\u1EB9' == shar || '\u1EBB' == shar
        || '\u1EBD' == shar || '\u1EBF' == shar
        || '\u1EC1' == shar || '\u1EC3' == shar
        || '\u1EC5' == shar || '\u1EC7' == shar
        || '\u1EC9' == shar || '\u1ECB' == shar
        || '\u1ECD' == shar || '\u1ECF' == shar
        || '\u1ED1' == shar || '\u1ED3' == shar
        || '\u1ED5' == shar || '\u1ED7' == shar
        || '\u1ED9' == shar || '\u1EDB' == shar
        || '\u1EDD' == shar || '\u1EDF' == shar
        || '\u1EE1' == shar || '\u1EE3' == shar
        || '\u1EE5' == shar || '\u1EE7' == shar
        || '\u1EE9' == shar || '\u1EEB' == shar
        || '\u1EED' == shar || '\u1EEF' == shar
        || '\u1EF1' == shar || '\u1EF3' == shar
        || '\u1EF5' == shar || '\u1EF7' == shar
        || '\u1EF9' == shar 
        || ('\u1F00' <= shar && shar <= '\u1F07')
        || ('\u1F10' <= shar && shar <= '\u1F15')
        || ('\u1F20' <= shar && shar <= '\u1F27')
        || ('\u1F30' <= shar && shar <= '\u1F37')
        || ('\u1F40' <= shar && shar <= '\u1F45')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || ('\u1F60' <= shar && shar <= '\u1F67')
        || ('\u1F70' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1F87')
        || ('\u1F90' <= shar && shar <= '\u1F97')
        || ('\u1FA0' <= shar && shar <= '\u1FA7')
        || ('\u1FB0' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FB7')
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FC7')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FD7')
        || ('\u1FE0' <= shar && shar <= '\u1FE7')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FF7')
        || '\u207F' == shar || '\u210A' == shar
        || ('\u210E' <= shar && shar <= '\u210F')
        || '\u2113' == shar 
        || ('\u212E' <= shar && shar <= '\u212F')
        || '\u2134' == shar 
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFF41' <= shar && shar <= '\uFF5A');
  }
  
  public static boolean isUpperCase(char shar) {
    return ('\u0041' <= shar && shar <= '\u005A')
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00DE')
        || '\u0100' == shar || '\u0102' == shar
        || '\u0104' == shar || '\u0106' == shar
        || '\u0108' == shar || '\u010A' == shar
        || '\u010C' == shar || '\u010E' == shar
        || '\u0110' == shar || '\u0112' == shar
        || '\u0114' == shar || '\u0116' == shar
        || '\u0118' == shar || '\u011A' == shar
        || '\u011C' == shar || '\u011E' == shar
        || '\u0120' == shar || '\u0122' == shar
        || '\u0124' == shar || '\u0126' == shar
        || '\u0128' == shar || '\u012A' == shar
        || '\u012C' == shar || '\u012E' == shar
        || '\u0130' == shar || '\u0132' == shar
        || '\u0134' == shar || '\u0136' == shar
        || '\u0139' == shar || '\u013B' == shar
        || '\u013D' == shar || '\u013F' == shar
        || '\u0141' == shar || '\u0143' == shar
        || '\u0145' == shar || '\u0147' == shar
        || '\u014A' == shar || '\u014C' == shar
        || '\u014E' == shar || '\u0150' == shar
        || '\u0152' == shar || '\u0154' == shar
        || '\u0156' == shar || '\u0158' == shar
        || '\u015A' == shar || '\u015C' == shar
        || '\u015E' == shar || '\u0160' == shar
        || '\u0162' == shar || '\u0164' == shar
        || '\u0166' == shar || '\u0168' == shar
        || '\u016A' == shar || '\u016C' == shar
        || '\u016E' == shar || '\u0170' == shar
        || '\u0172' == shar || '\u0174' == shar
        || '\u0176' == shar 
        || ('\u0178' <= shar && shar <= '\u0179')
        || '\u017B' == shar || '\u017D' == shar
        || ('\u0181' <= shar && shar <= '\u0182')
        || '\u0184' == shar 
        || ('\u0186' <= shar && shar <= '\u0187')
        || ('\u0189' <= shar && shar <= '\u018B')
        || ('\u018E' <= shar && shar <= '\u0191')
        || ('\u0193' <= shar && shar <= '\u0194')
        || ('\u0196' <= shar && shar <= '\u0198')
        || ('\u019C' <= shar && shar <= '\u019D')
        || ('\u019F' <= shar && shar <= '\u01A0')
        || '\u01A2' == shar || '\u01A4' == shar
        || ('\u01A6' <= shar && shar <= '\u01A7')
        || '\u01A9' == shar || '\u01AC' == shar
        || ('\u01AE' <= shar && shar <= '\u01AF')
        || ('\u01B1' <= shar && shar <= '\u01B3')
        || '\u01B5' == shar 
        || ('\u01B7' <= shar && shar <= '\u01B8')
        || '\u01BC' == shar || '\u01C4' == shar
        || '\u01C7' == shar || '\u01CA' == shar
        || '\u01CD' == shar || '\u01CF' == shar
        || '\u01D1' == shar || '\u01D3' == shar
        || '\u01D5' == shar || '\u01D7' == shar
        || '\u01D9' == shar || '\u01DB' == shar
        || '\u01DE' == shar || '\u01E0' == shar
        || '\u01E2' == shar || '\u01E4' == shar
        || '\u01E6' == shar || '\u01E8' == shar
        || '\u01EA' == shar || '\u01EC' == shar
        || '\u01EE' == shar || '\u01F1' == shar
        || '\u01F4' == shar || '\u01FA' == shar
        || '\u01FC' == shar || '\u01FE' == shar
        || '\u0200' == shar || '\u0202' == shar
        || '\u0204' == shar || '\u0206' == shar
        || '\u0208' == shar || '\u020A' == shar
        || '\u020C' == shar || '\u020E' == shar
        || '\u0210' == shar || '\u0212' == shar
        || '\u0214' == shar || '\u0216' == shar
        || '\u0386' == shar 
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u038F')
        || ('\u0391' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03AB')
        || ('\u03D2' <= shar && shar <= '\u03D4')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || '\u03E2' == shar || '\u03E4' == shar
        || '\u03E6' == shar || '\u03E8' == shar
        || '\u03EA' == shar || '\u03EC' == shar
        || '\u03EE' == shar 
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u042F')
        || '\u0460' == shar || '\u0462' == shar
        || '\u0464' == shar || '\u0466' == shar
        || '\u0468' == shar || '\u046A' == shar
        || '\u046C' == shar || '\u046E' == shar
        || '\u0470' == shar || '\u0472' == shar
        || '\u0474' == shar || '\u0476' == shar
        || '\u0478' == shar || '\u047A' == shar
        || '\u047C' == shar || '\u047E' == shar
        || '\u0480' == shar || '\u0490' == shar
        || '\u0492' == shar || '\u0494' == shar
        || '\u0496' == shar || '\u0498' == shar
        || '\u049A' == shar || '\u049C' == shar
        || '\u049E' == shar || '\u04A0' == shar
        || '\u04A2' == shar || '\u04A4' == shar
        || '\u04A6' == shar || '\u04A8' == shar
        || '\u04AA' == shar || '\u04AC' == shar
        || '\u04AE' == shar || '\u04B0' == shar
        || '\u04B2' == shar || '\u04B4' == shar
        || '\u04B6' == shar || '\u04B8' == shar
        || '\u04BA' == shar || '\u04BC' == shar
        || '\u04BE' == shar || '\u04C1' == shar
        || '\u04C3' == shar || '\u04C7' == shar
        || '\u04CB' == shar || '\u04D0' == shar
        || '\u04D2' == shar || '\u04D4' == shar
        || '\u04D6' == shar || '\u04D8' == shar
        || '\u04DA' == shar || '\u04DC' == shar
        || '\u04DE' == shar || '\u04E0' == shar
        || '\u04E2' == shar || '\u04E4' == shar
        || '\u04E6' == shar || '\u04E8' == shar
        || '\u04EA' == shar || '\u04EE' == shar
        || '\u04F0' == shar || '\u04F2' == shar
        || '\u04F4' == shar || '\u04F8' == shar
        || ('\u0531' <= shar && shar <= '\u0556')
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || '\u1E00' == shar || '\u1E02' == shar
        || '\u1E04' == shar || '\u1E06' == shar
        || '\u1E08' == shar || '\u1E0A' == shar
        || '\u1E0C' == shar || '\u1E0E' == shar
        || '\u1E10' == shar || '\u1E12' == shar
        || '\u1E14' == shar || '\u1E16' == shar
        || '\u1E18' == shar || '\u1E1A' == shar
        || '\u1E1C' == shar || '\u1E1E' == shar
        || '\u1E20' == shar || '\u1E22' == shar
        || '\u1E24' == shar || '\u1E26' == shar
        || '\u1E28' == shar || '\u1E2A' == shar
        || '\u1E2C' == shar || '\u1E2E' == shar
        || '\u1E30' == shar || '\u1E32' == shar
        || '\u1E34' == shar || '\u1E36' == shar
        || '\u1E38' == shar || '\u1E3A' == shar
        || '\u1E3C' == shar || '\u1E3E' == shar
        || '\u1E40' == shar || '\u1E42' == shar
        || '\u1E44' == shar || '\u1E46' == shar
        || '\u1E48' == shar || '\u1E4A' == shar
        || '\u1E4C' == shar || '\u1E4E' == shar
        || '\u1E50' == shar || '\u1E52' == shar
        || '\u1E54' == shar || '\u1E56' == shar
        || '\u1E58' == shar || '\u1E5A' == shar
        || '\u1E5C' == shar || '\u1E5E' == shar
        || '\u1E60' == shar || '\u1E62' == shar
        || '\u1E64' == shar || '\u1E66' == shar
        || '\u1E68' == shar || '\u1E6A' == shar
        || '\u1E6C' == shar || '\u1E6E' == shar
        || '\u1E70' == shar || '\u1E72' == shar
        || '\u1E74' == shar || '\u1E76' == shar
        || '\u1E78' == shar || '\u1E7A' == shar
        || '\u1E7C' == shar || '\u1E7E' == shar
        || '\u1E80' == shar || '\u1E82' == shar
        || '\u1E84' == shar || '\u1E86' == shar
        || '\u1E88' == shar || '\u1E8A' == shar
        || '\u1E8C' == shar || '\u1E8E' == shar
        || '\u1E90' == shar || '\u1E92' == shar
        || '\u1E94' == shar || '\u1EA0' == shar
        || '\u1EA2' == shar || '\u1EA4' == shar
        || '\u1EA6' == shar || '\u1EA8' == shar
        || '\u1EAA' == shar || '\u1EAC' == shar
        || '\u1EAE' == shar || '\u1EB0' == shar
        || '\u1EB2' == shar || '\u1EB4' == shar
        || '\u1EB6' == shar || '\u1EB8' == shar
        || '\u1EBA' == shar || '\u1EBC' == shar
        || '\u1EBE' == shar || '\u1EC0' == shar
        || '\u1EC2' == shar || '\u1EC4' == shar
        || '\u1EC6' == shar || '\u1EC8' == shar
        || '\u1ECA' == shar || '\u1ECC' == shar
        || '\u1ECE' == shar || '\u1ED0' == shar
        || '\u1ED2' == shar || '\u1ED4' == shar
        || '\u1ED6' == shar || '\u1ED8' == shar
        || '\u1EDA' == shar || '\u1EDC' == shar
        || '\u1EDE' == shar || '\u1EE0' == shar
        || '\u1EE2' == shar || '\u1EE4' == shar
        || '\u1EE6' == shar || '\u1EE8' == shar
        || '\u1EEA' == shar || '\u1EEC' == shar
        || '\u1EEE' == shar || '\u1EF0' == shar
        || '\u1EF2' == shar || '\u1EF4' == shar
        || '\u1EF6' == shar || '\u1EF8' == shar
        || ('\u1F08' <= shar && shar <= '\u1F0F')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F28' <= shar && shar <= '\u1F2F')
        || ('\u1F38' <= shar && shar <= '\u1F3F')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar || '\u1F5F' == shar
        || ('\u1F68' <= shar && shar <= '\u1F6F')
        || ('\u1F88' <= shar && shar <= '\u1F8F')
        || ('\u1F98' <= shar && shar <= '\u1F9F')
        || ('\u1FA8' <= shar && shar <= '\u1FAF')
        || ('\u1FB8' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC8' <= shar && shar <= '\u1FCC')
        || ('\u1FD8' <= shar && shar <= '\u1FDB')
        || ('\u1FE8' <= shar && shar <= '\u1FEC')
        || ('\u1FF8' <= shar && shar <= '\u1FFC')
        || '\u2102' == shar || '\u2107' == shar
        || ('\u210B' <= shar && shar <= '\u210D')
        || ('\u2110' <= shar && shar <= '\u2112')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u212D')
        || ('\u2130' <= shar && shar <= '\u2131')
        || '\u2133' == shar 
        || ('\uFF21' <= shar && shar <= '\uFF3A');
  }
  
  public static boolean isTitleCase(char shar) {
    return shar == '\u01C5' || shar == '\u01C8'
        || shar == '\u01CB' || shar == '\u01F2';
  }

  public static boolean isDefined(char shar) {
    return ('\u0000' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02DE')
        || ('\u02E0' <= shar && shar <= '\u02E9')
        || ('\u0300' <= shar && shar <= '\u0345')
        || ('\u0360' <= shar && shar <= '\u0361')
        || ('\u0374' <= shar && shar <= '\u0375')
        || '\u037A' == shar || '\u037E' == shar
        || ('\u0384' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0486')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || ('\u0559' <= shar && shar <= '\u055F')
        || ('\u0561' <= shar && shar <= '\u0587')
        || '\u0589' == shar 
        || ('\u0591' <= shar && shar <= '\u05A1')
        || ('\u05A3' <= shar && shar <= '\u05B9')
        || ('\u05BB' <= shar && shar <= '\u05C4')
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F4')
        || '\u060C' == shar || '\u061B' == shar
        || '\u061F' == shar 
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u0652')
        || ('\u0660' <= shar && shar <= '\u066D')
        || ('\u0670' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06ED')
        || ('\u06F0' <= shar && shar <= '\u06F9')
        || ('\u0901' <= shar && shar <= '\u0903')
        || ('\u0905' <= shar && shar <= '\u0939')
        || ('\u093C' <= shar && shar <= '\u094D')
        || ('\u0950' <= shar && shar <= '\u0954')
        || ('\u0958' <= shar && shar <= '\u0970')
        || ('\u0981' <= shar && shar <= '\u0983')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || '\u09BC' == shar 
        || ('\u09BE' <= shar && shar <= '\u09C4')
        || ('\u09C7' <= shar && shar <= '\u09C8')
        || ('\u09CB' <= shar && shar <= '\u09CD')
        || '\u09D7' == shar 
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E3')
        || ('\u09E6' <= shar && shar <= '\u09FA')
        || '\u0A02' == shar 
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || '\u0A3C' == shar 
        || ('\u0A3E' <= shar && shar <= '\u0A42')
        || ('\u0A47' <= shar && shar <= '\u0A48')
        || ('\u0A4B' <= shar && shar <= '\u0A4D')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A66' <= shar && shar <= '\u0A74')
        || ('\u0A81' <= shar && shar <= '\u0A83')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || ('\u0ABC' <= shar && shar <= '\u0AC5')
        || ('\u0AC7' <= shar && shar <= '\u0AC9')
        || ('\u0ACB' <= shar && shar <= '\u0ACD')
        || '\u0AD0' == shar || '\u0AE0' == shar
        || ('\u0AE6' <= shar && shar <= '\u0AEF')
        || ('\u0B01' <= shar && shar <= '\u0B03')
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || ('\u0B3C' <= shar && shar <= '\u0B43')
        || ('\u0B47' <= shar && shar <= '\u0B48')
        || ('\u0B4B' <= shar && shar <= '\u0B4D')
        || ('\u0B56' <= shar && shar <= '\u0B57')
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B66' <= shar && shar <= '\u0B70')
        || ('\u0B82' <= shar && shar <= '\u0B83')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0BBE' <= shar && shar <= '\u0BC2')
        || ('\u0BC6' <= shar && shar <= '\u0BC8')
        || ('\u0BCA' <= shar && shar <= '\u0BCD')
        || '\u0BD7' == shar 
        || ('\u0BE7' <= shar && shar <= '\u0BF2')
        || ('\u0C01' <= shar && shar <= '\u0C03')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C3E' <= shar && shar <= '\u0C44')
        || ('\u0C46' <= shar && shar <= '\u0C48')
        || ('\u0C4A' <= shar && shar <= '\u0C4D')
        || ('\u0C55' <= shar && shar <= '\u0C56')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C66' <= shar && shar <= '\u0C6F')
        || ('\u0C82' <= shar && shar <= '\u0C83')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || ('\u0CBE' <= shar && shar <= '\u0CC4')
        || ('\u0CC6' <= shar && shar <= '\u0CC8')
        || ('\u0CCA' <= shar && shar <= '\u0CCD')
        || ('\u0CD5' <= shar && shar <= '\u0CD6')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0CE6' <= shar && shar <= '\u0CEF')
        || ('\u0D02' <= shar && shar <= '\u0D03')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D3E' <= shar && shar <= '\u0D43')
        || ('\u0D46' <= shar && shar <= '\u0D48')
        || ('\u0D4A' <= shar && shar <= '\u0D4D')
        || '\u0D57' == shar 
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0D66' <= shar && shar <= '\u0D6F')
        || ('\u0E01' <= shar && shar <= '\u0E3A')
        || ('\u0E3F' <= shar && shar <= '\u0E5B')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EB9')
        || ('\u0EBB' <= shar && shar <= '\u0EBD')
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0EC8' <= shar && shar <= '\u0ECD')
        || ('\u0ED0' <= shar && shar <= '\u0ED9')
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F00' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u0F71' <= shar && shar <= '\u0F8B')
        || ('\u0F90' <= shar && shar <= '\u0F95')
        || '\u0F97' == shar 
        || ('\u0F99' <= shar && shar <= '\u0FAD')
        || ('\u0FB1' <= shar && shar <= '\u0FB7')
        || '\u0FB9' == shar 
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || '\u10FB' == shar 
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FDD' <= shar && shar <= '\u1FEF')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFE')
        || ('\u2000' <= shar && shar <= '\u202E')
        || ('\u2030' <= shar && shar <= '\u2046')
        || ('\u206A' <= shar && shar <= '\u2070')
        || ('\u2074' <= shar && shar <= '\u208E')
        || ('\u20A0' <= shar && shar <= '\u20AC')
        || ('\u20D0' <= shar && shar <= '\u20E1')
        || ('\u2100' <= shar && shar <= '\u2138')
        || ('\u2153' <= shar && shar <= '\u2182')
        || ('\u2190' <= shar && shar <= '\u21EA')
        || ('\u2200' <= shar && shar <= '\u22F1')
        || '\u2300' == shar 
        || ('\u2302' <= shar && shar <= '\u237A')
        || ('\u2400' <= shar && shar <= '\u2424')
        || ('\u2440' <= shar && shar <= '\u244A')
        || ('\u2460' <= shar && shar <= '\u24EA')
        || ('\u2500' <= shar && shar <= '\u2595')
        || ('\u25A0' <= shar && shar <= '\u25EF')
        || ('\u2600' <= shar && shar <= '\u2613')
        || ('\u261A' <= shar && shar <= '\u266F')
        || ('\u2701' <= shar && shar <= '\u2704')
        || ('\u2706' <= shar && shar <= '\u2709')
        || ('\u270C' <= shar && shar <= '\u2727')
        || ('\u2729' <= shar && shar <= '\u274B')
        || '\u274D' == shar 
        || ('\u274F' <= shar && shar <= '\u2752')
        || '\u2756' == shar 
        || ('\u2758' <= shar && shar <= '\u275E')
        || ('\u2761' <= shar && shar <= '\u2767')
        || ('\u2776' <= shar && shar <= '\u2794')
        || ('\u2798' <= shar && shar <= '\u27AF')
        || ('\u27B1' <= shar && shar <= '\u27BE')
        || ('\u3000' <= shar && shar <= '\u3037')
        || '\u303F' == shar 
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u3099' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u3190' <= shar && shar <= '\u319F')
        || ('\u3200' <= shar && shar <= '\u321C')
        || ('\u3220' <= shar && shar <= '\u3243')
        || ('\u3260' <= shar && shar <= '\u327B')
        || ('\u327F' <= shar && shar <= '\u32B0')
        || ('\u32C0' <= shar && shar <= '\u32CB')
        || ('\u32D0' <= shar && shar <= '\u32FE')
        || ('\u3300' <= shar && shar <= '\u3376')
        || ('\u337B' <= shar && shar <= '\u33DD')
        || ('\u33E0' <= shar && shar <= '\u33FE')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uD800' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1E' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3F')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE20' <= shar && shar <= '\uFE23')
        || ('\uFE30' <= shar && shar <= '\uFE44')
        || ('\uFE49' <= shar && shar <= '\uFE52')
        || ('\uFE54' <= shar && shar <= '\uFE66')
        || ('\uFE68' <= shar && shar <= '\uFE6B')
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || '\uFEFF' == shar 
        || ('\uFF01' <= shar && shar <= '\uFF5E')
        || ('\uFF61' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC')
        || ('\uFFE0' <= shar && shar <= '\uFFE6')
        || ('\uFFE8' <= shar && shar <= '\uFFEE')
        || ('\uFFFC' <= shar && shar <= '\uFFFD');
  }
  
  public static boolean isLetter(char shar) {
    return ('\u0041' <= shar && shar <= '\u005A')
        || ('\u0061' <= shar && shar <= '\u007A')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02B8')
        || ('\u02BB' <= shar && shar <= '\u02C1')
        || ('\u02D0' <= shar && shar <= '\u02D1')
        || ('\u02E0' <= shar && shar <= '\u02E4')
        || '\u037A' == shar || '\u0386' == shar
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0481')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || '\u0559' == shar 
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F2')
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u064A')
        || ('\u0671' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06D3')
        || '\u06D5' == shar 
        || ('\u06E5' <= shar && shar <= '\u06E6')
        || ('\u0905' <= shar && shar <= '\u0939')
        || '\u093D' == shar 
        || ('\u0958' <= shar && shar <= '\u0961')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E1')
        || ('\u09F0' <= shar && shar <= '\u09F1')
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A72' <= shar && shar <= '\u0A74')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || '\u0ABD' == shar || '\u0AE0' == shar
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || '\u0B3D' == shar 
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0E01' <= shar && shar <= '\u0E2E')
        || '\u0E30' == shar 
        || ('\u0E32' <= shar && shar <= '\u0E33')
        || ('\u0E40' <= shar && shar <= '\u0E46')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EAE')
        || '\u0EB0' == shar 
        || ('\u0EB2' <= shar && shar <= '\u0EB3')
        || '\u0EBD' == shar 
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F40' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FCC')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FE0' <= shar && shar <= '\u1FEC')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFC')
        || '\u207F' == shar || '\u2102' == shar
        || '\u2107' == shar 
        || ('\u210A' <= shar && shar <= '\u2113')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u2131')
        || ('\u2133' <= shar && shar <= '\u2138')
        || '\u3005' == shar 
        || ('\u3031' <= shar && shar <= '\u3035')
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u309B' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FA')
        || ('\u30FC' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uF900' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1F' <= shar && shar <= '\uFB28')
        || ('\uFB2A' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3D')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || ('\uFF21' <= shar && shar <= '\uFF3A')
        || ('\uFF41' <= shar && shar <= '\uFF5A')
        || ('\uFF66' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC');
  }

  public static boolean isDigit(char shar) {
    return ('\u0030' <= shar && shar <= '\u0039')
        || ('\u0660' <= shar && shar <= '\u0669')
        || ('\u06F0' <= shar && shar <= '\u06F9')
        || ('\u0966' <= shar && shar <= '\u096F')
        || ('\u09E6' <= shar && shar <= '\u09EF')
        || ('\u0A66' <= shar && shar <= '\u0A6F')
        || ('\u0AE6' <= shar && shar <= '\u0AEF')
        || ('\u0B66' <= shar && shar <= '\u0B6F')
        || ('\u0BE7' <= shar && shar <= '\u0BEF')
        || ('\u0C66' <= shar && shar <= '\u0C6F')
        || ('\u0CE6' <= shar && shar <= '\u0CEF')
        || ('\u0D66' <= shar && shar <= '\u0D6F')
        || ('\u0E50' <= shar && shar <= '\u0E59')
        || ('\u0ED0' <= shar && shar <= '\u0ED9')
        || ('\u0F20' <= shar && shar <= '\u0F29')
        || ('\uFF10' <= shar && shar <= '\uFF19');
  }

  public static boolean isLetterOrDigit(char shar) {
    return ('\u0030' <= shar && shar <= '\u0039')
        || ('\u0041' <= shar && shar <= '\u005A')
        || ('\u0061' <= shar && shar <= '\u007A')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02B8')
        || ('\u02BB' <= shar && shar <= '\u02C1')
        || ('\u02D0' <= shar && shar <= '\u02D1')
        || ('\u02E0' <= shar && shar <= '\u02E4')
        || '\u037A' == shar || '\u0386' == shar
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0481')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || '\u0559' == shar 
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F2')
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u064A')
        || ('\u0660' <= shar && shar <= '\u0669')
        || ('\u0671' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06D3')
        || '\u06D5' == shar 
        || ('\u06E5' <= shar && shar <= '\u06E6')
        || ('\u06F0' <= shar && shar <= '\u06F9')
        || ('\u0905' <= shar && shar <= '\u0939')
        || '\u093D' == shar 
        || ('\u0958' <= shar && shar <= '\u0961')
        || ('\u0966' <= shar && shar <= '\u096F')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E1')
        || ('\u09E6' <= shar && shar <= '\u09F1')
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A66' <= shar && shar <= '\u0A6F')
        || ('\u0A72' <= shar && shar <= '\u0A74')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || '\u0ABD' == shar || '\u0AE0' == shar
        || ('\u0AE6' <= shar && shar <= '\u0AEF')
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || '\u0B3D' == shar 
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B66' <= shar && shar <= '\u0B6F')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0BE7' <= shar && shar <= '\u0BEF')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C66' <= shar && shar <= '\u0C6F')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0CE6' <= shar && shar <= '\u0CEF')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0D66' <= shar && shar <= '\u0D6F')
        || ('\u0E01' <= shar && shar <= '\u0E2E')
        || '\u0E30' == shar 
        || ('\u0E32' <= shar && shar <= '\u0E33')
        || ('\u0E40' <= shar && shar <= '\u0E46')
        || ('\u0E50' <= shar && shar <= '\u0E59')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EAE')
        || '\u0EB0' == shar 
        || ('\u0EB2' <= shar && shar <= '\u0EB3')
        || '\u0EBD' == shar 
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0ED0' <= shar && shar <= '\u0ED9')
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F20' <= shar && shar <= '\u0F29')
        || ('\u0F40' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FCC')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FE0' <= shar && shar <= '\u1FEC')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFC')
        || '\u207F' == shar || '\u2102' == shar
        || '\u2107' == shar 
        || ('\u210A' <= shar && shar <= '\u2113')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u2131')
        || ('\u2133' <= shar && shar <= '\u2138')
        || '\u3005' == shar 
        || ('\u3031' <= shar && shar <= '\u3035')
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u309B' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FA')
        || ('\u30FC' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uF900' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1F' <= shar && shar <= '\uFB28')
        || ('\uFB2A' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3D')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || ('\uFF10' <= shar && shar <= '\uFF19')
        || ('\uFF21' <= shar && shar <= '\uFF3A')
        || ('\uFF41' <= shar && shar <= '\uFF5A')
        || ('\uFF66' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC');
  }

  public static boolean isIdentifierIgnorable(char shar) {
    return ('\u0000' <= shar && shar <= '\u0008')
        || ('\u000E' <= shar && shar <= '\u001B')
        || ('\u007F' <= shar && shar <= '\u009F')
        || ('\u200C' <= shar && shar <= '\u200F')
        || ('\u202A' <= shar && shar <= '\u202E')
        || ('\u206A' <= shar && shar <= '\u206F')
        || shar == '\uFEFF';
  }

  /** @deprecated */
  public static boolean isJavaLetter(char shar) {
    return isJavaIdentifierStart(shar);
  }

  /** @deprecated */
  public static boolean isJavaLetterOrDigit(char shar) {
    return isJavaIdentifierPart(shar);
  }

  public static boolean isJavaIdentifierStart(char shar) {
    return '\u0024' == shar 
        || ('\u0041' <= shar && shar <= '\u005A')
        || '\u005F' == shar 
        || ('\u0061' <= shar && shar <= '\u007A')
        || ('\u00A2' <= shar && shar <= '\u00A5')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02B8')
        || ('\u02BB' <= shar && shar <= '\u02C1')
        || ('\u02D0' <= shar && shar <= '\u02D1')
        || ('\u02E0' <= shar && shar <= '\u02E4')
        || '\u037A' == shar || '\u0386' == shar
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0481')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || '\u0559' == shar 
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F2')
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u064A')
        || ('\u0671' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06D3')
        || '\u06D5' == shar 
        || ('\u06E5' <= shar && shar <= '\u06E6')
        || ('\u0905' <= shar && shar <= '\u0939')
        || '\u093D' == shar 
        || ('\u0958' <= shar && shar <= '\u0961')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E1')
        || ('\u09F0' <= shar && shar <= '\u09F3')
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A72' <= shar && shar <= '\u0A74')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || '\u0ABD' == shar || '\u0AE0' == shar
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || '\u0B3D' == shar 
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0E01' <= shar && shar <= '\u0E2E')
        || '\u0E30' == shar 
        || ('\u0E32' <= shar && shar <= '\u0E33')
        || ('\u0E3F' <= shar && shar <= '\u0E46')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EAE')
        || '\u0EB0' == shar 
        || ('\u0EB2' <= shar && shar <= '\u0EB3')
        || '\u0EBD' == shar 
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F40' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FCC')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FE0' <= shar && shar <= '\u1FEC')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFC')
        || ('\u203F' <= shar && shar <= '\u2040')
        || '\u207F' == shar 
        || ('\u20A0' <= shar && shar <= '\u20AC')
        || '\u2102' == shar || '\u2107' == shar
        || ('\u210A' <= shar && shar <= '\u2113')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u2131')
        || ('\u2133' <= shar && shar <= '\u2138')
        || ('\u2160' <= shar && shar <= '\u2182')
        || '\u3005' == shar || '\u3007' == shar
        || ('\u3021' <= shar && shar <= '\u3029')
        || ('\u3031' <= shar && shar <= '\u3035')
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u309B' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FA')
        || ('\u30FC' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uF900' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1F' <= shar && shar <= '\uFB28')
        || ('\uFB2A' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3D')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE33' <= shar && shar <= '\uFE34')
        || ('\uFE4D' <= shar && shar <= '\uFE4F')
        || '\uFE69' == shar 
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || '\uFF04' == shar 
        || ('\uFF21' <= shar && shar <= '\uFF3A')
        || '\uFF3F' == shar 
        || ('\uFF41' <= shar && shar <= '\uFF5A')
        || ('\uFF66' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC')
        || ('\uFFE0' <= shar && shar <= '\uFFE1')
        || ('\uFFE5' <= shar && shar <= '\uFFE6');
  }

  public static boolean isJavaIdentifierPart(char shar) {
    return ('\u0000' <= shar && shar <= '\u0008')
        || ('\u000E' <= shar && shar <= '\u001B')
        || '\u0024' == shar 
        || ('\u0030' <= shar && shar <= '\u0039')
        || ('\u0041' <= shar && shar <= '\u005A')
        || '\u005F' == shar 
        || ('\u0061' <= shar && shar <= '\u007A')
        || ('\u007F' <= shar && shar <= '\u009F')
        || ('\u00A2' <= shar && shar <= '\u00A5')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02B8')
        || ('\u02BB' <= shar && shar <= '\u02C1')
        || ('\u02D0' <= shar && shar <= '\u02D1')
        || ('\u02E0' <= shar && shar <= '\u02E4')
        || ('\u0300' <= shar && shar <= '\u0345')
        || ('\u0360' <= shar && shar <= '\u0361')
        || '\u037A' == shar || '\u0386' == shar
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0481')
        || ('\u0483' <= shar && shar <= '\u0486')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || '\u0559' == shar 
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u0591' <= shar && shar <= '\u05A1')
        || ('\u05A3' <= shar && shar <= '\u05B9')
        || ('\u05BB' <= shar && shar <= '\u05BD')
        || '\u05BF' == shar 
        || ('\u05C1' <= shar && shar <= '\u05C2')
        || '\u05C4' == shar 
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F2')
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u0652')
        || ('\u0660' <= shar && shar <= '\u0669')
        || ('\u0670' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06D3')
        || ('\u06D5' <= shar && shar <= '\u06DC')
        || ('\u06DF' <= shar && shar <= '\u06E8')
        || ('\u06EA' <= shar && shar <= '\u06ED')
        || ('\u06F0' <= shar && shar <= '\u06F9')
        || ('\u0901' <= shar && shar <= '\u0903')
        || ('\u0905' <= shar && shar <= '\u0939')
        || ('\u093C' <= shar && shar <= '\u094D')
        || ('\u0951' <= shar && shar <= '\u0954')
        || ('\u0958' <= shar && shar <= '\u0963')
        || ('\u0966' <= shar && shar <= '\u096F')
        || ('\u0981' <= shar && shar <= '\u0983')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || '\u09BC' == shar 
        || ('\u09BE' <= shar && shar <= '\u09C4')
        || ('\u09C7' <= shar && shar <= '\u09C8')
        || ('\u09CB' <= shar && shar <= '\u09CD')
        || '\u09D7' == shar 
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E3')
        || ('\u09E6' <= shar && shar <= '\u09F3')
        || '\u0A02' == shar 
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || '\u0A3C' == shar 
        || ('\u0A3E' <= shar && shar <= '\u0A42')
        || ('\u0A47' <= shar && shar <= '\u0A48')
        || ('\u0A4B' <= shar && shar <= '\u0A4D')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A66' <= shar && shar <= '\u0A74')
        || ('\u0A81' <= shar && shar <= '\u0A83')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || ('\u0ABC' <= shar && shar <= '\u0AC5')
        || ('\u0AC7' <= shar && shar <= '\u0AC9')
        || ('\u0ACB' <= shar && shar <= '\u0ACD')
        || '\u0AE0' == shar 
        || ('\u0AE6' <= shar && shar <= '\u0AEF')
        || ('\u0B01' <= shar && shar <= '\u0B03')
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || ('\u0B3C' <= shar && shar <= '\u0B43')
        || ('\u0B47' <= shar && shar <= '\u0B48')
        || ('\u0B4B' <= shar && shar <= '\u0B4D')
        || ('\u0B56' <= shar && shar <= '\u0B57')
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B66' <= shar && shar <= '\u0B6F')
        || ('\u0B82' <= shar && shar <= '\u0B83')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0BBE' <= shar && shar <= '\u0BC2')
        || ('\u0BC6' <= shar && shar <= '\u0BC8')
        || ('\u0BCA' <= shar && shar <= '\u0BCD')
        || '\u0BD7' == shar 
        || ('\u0BE7' <= shar && shar <= '\u0BEF')
        || ('\u0C01' <= shar && shar <= '\u0C03')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C3E' <= shar && shar <= '\u0C44')
        || ('\u0C46' <= shar && shar <= '\u0C48')
        || ('\u0C4A' <= shar && shar <= '\u0C4D')
        || ('\u0C55' <= shar && shar <= '\u0C56')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C66' <= shar && shar <= '\u0C6F')
        || ('\u0C82' <= shar && shar <= '\u0C83')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || ('\u0CBE' <= shar && shar <= '\u0CC4')
        || ('\u0CC6' <= shar && shar <= '\u0CC8')
        || ('\u0CCA' <= shar && shar <= '\u0CCD')
        || ('\u0CD5' <= shar && shar <= '\u0CD6')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0CE6' <= shar && shar <= '\u0CEF')
        || ('\u0D02' <= shar && shar <= '\u0D03')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D3E' <= shar && shar <= '\u0D43')
        || ('\u0D46' <= shar && shar <= '\u0D48')
        || ('\u0D4A' <= shar && shar <= '\u0D4D')
        || '\u0D57' == shar 
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0D66' <= shar && shar <= '\u0D6F')
        || ('\u0E01' <= shar && shar <= '\u0E2E')
        || ('\u0E30' <= shar && shar <= '\u0E3A')
        || ('\u0E3F' <= shar && shar <= '\u0E4E')
        || ('\u0E50' <= shar && shar <= '\u0E59')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EAE')
        || ('\u0EB0' <= shar && shar <= '\u0EB9')
        || ('\u0EBB' <= shar && shar <= '\u0EBD')
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0EC8' <= shar && shar <= '\u0ECD')
        || ('\u0ED0' <= shar && shar <= '\u0ED9')
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F18' <= shar && shar <= '\u0F19')
        || ('\u0F20' <= shar && shar <= '\u0F29')
        || '\u0F35' == shar || '\u0F37' == shar
        || '\u0F39' == shar 
        || ('\u0F3E' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u0F71' <= shar && shar <= '\u0F84')
        || ('\u0F86' <= shar && shar <= '\u0F8B')
        || ('\u0F90' <= shar && shar <= '\u0F95')
        || '\u0F97' == shar 
        || ('\u0F99' <= shar && shar <= '\u0FAD')
        || ('\u0FB1' <= shar && shar <= '\u0FB7')
        || '\u0FB9' == shar 
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FCC')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FE0' <= shar && shar <= '\u1FEC')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFC')
        || ('\u200C' <= shar && shar <= '\u200F')
        || ('\u202A' <= shar && shar <= '\u202E')
        || ('\u203F' <= shar && shar <= '\u2040')
        || ('\u206A' <= shar && shar <= '\u206F')
        || '\u207F' == shar 
        || ('\u20A0' <= shar && shar <= '\u20AC')
        || ('\u20D0' <= shar && shar <= '\u20DC')
        || '\u20E1' == shar || '\u2102' == shar
        || '\u2107' == shar 
        || ('\u210A' <= shar && shar <= '\u2113')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u2131')
        || ('\u2133' <= shar && shar <= '\u2138')
        || ('\u2160' <= shar && shar <= '\u2182')
        || '\u3005' == shar || '\u3007' == shar
        || ('\u3021' <= shar && shar <= '\u302F')
        || ('\u3031' <= shar && shar <= '\u3035')
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u3099' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FA')
        || ('\u30FC' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uF900' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1E' <= shar && shar <= '\uFB28')
        || ('\uFB2A' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3D')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE20' <= shar && shar <= '\uFE23')
        || ('\uFE33' <= shar && shar <= '\uFE34')
        || ('\uFE4D' <= shar && shar <= '\uFE4F')
        || '\uFE69' == shar 
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || '\uFEFF' == shar || '\uFF04' == shar
        || ('\uFF10' <= shar && shar <= '\uFF19')
        || ('\uFF21' <= shar && shar <= '\uFF3A')
        || '\uFF3F' == shar 
        || ('\uFF41' <= shar && shar <= '\uFF5A')
        || ('\uFF66' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC')
        || ('\uFFE0' <= shar && shar <= '\uFFE1')
        || ('\uFFE5' <= shar && shar <= '\uFFE6');
  }

  public static boolean isUnicodeIdentifierStart(char shar) {
    return ('\u0041' <= shar && shar <= '\u005A')
        || ('\u0061' <= shar && shar <= '\u007A')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02B8')
        || ('\u02BB' <= shar && shar <= '\u02C1')
        || ('\u02D0' <= shar && shar <= '\u02D1')
        || ('\u02E0' <= shar && shar <= '\u02E4')
        || '\u037A' == shar || '\u0386' == shar
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0481')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || '\u0559' == shar 
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F2')
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u064A')
        || ('\u0671' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06D3')
        || '\u06D5' == shar 
        || ('\u06E5' <= shar && shar <= '\u06E6')
        || ('\u0905' <= shar && shar <= '\u0939')
        || '\u093D' == shar 
        || ('\u0958' <= shar && shar <= '\u0961')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E1')
        || ('\u09F0' <= shar && shar <= '\u09F1')
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A72' <= shar && shar <= '\u0A74')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || '\u0ABD' == shar || '\u0AE0' == shar
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || '\u0B3D' == shar 
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0E01' <= shar && shar <= '\u0E2E')
        || '\u0E30' == shar 
        || ('\u0E32' <= shar && shar <= '\u0E33')
        || ('\u0E40' <= shar && shar <= '\u0E46')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EAE')
        || '\u0EB0' == shar 
        || ('\u0EB2' <= shar && shar <= '\u0EB3')
        || '\u0EBD' == shar 
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F40' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FCC')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FE0' <= shar && shar <= '\u1FEC')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFC')
        || '\u207F' == shar || '\u2102' == shar
        || '\u2107' == shar 
        || ('\u210A' <= shar && shar <= '\u2113')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u2131')
        || ('\u2133' <= shar && shar <= '\u2138')
        || ('\u2160' <= shar && shar <= '\u2182')
        || '\u3005' == shar || '\u3007' == shar
        || ('\u3021' <= shar && shar <= '\u3029')
        || ('\u3031' <= shar && shar <= '\u3035')
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u309B' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FA')
        || ('\u30FC' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uF900' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1F' <= shar && shar <= '\uFB28')
        || ('\uFB2A' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3D')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || ('\uFF21' <= shar && shar <= '\uFF3A')
        || ('\uFF41' <= shar && shar <= '\uFF5A')
        || ('\uFF66' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC');
  }

  public static boolean isUnicodeIdentifierPart(char shar) {
    return ('\u0000' <= shar && shar <= '\u0008')
        || ('\u000E' <= shar && shar <= '\u001B')
        || ('\u0030' <= shar && shar <= '\u0039')
        || ('\u0041' <= shar && shar <= '\u005A')
        || '\u005F' == shar 
        || ('\u0061' <= shar && shar <= '\u007A')
        || ('\u007F' <= shar && shar <= '\u009F')
        || '\u00AA' == shar || '\u00B5' == shar
        || '\u00BA' == shar 
        || ('\u00C0' <= shar && shar <= '\u00D6')
        || ('\u00D8' <= shar && shar <= '\u00F6')
        || ('\u00F8' <= shar && shar <= '\u01F5')
        || ('\u01FA' <= shar && shar <= '\u0217')
        || ('\u0250' <= shar && shar <= '\u02A8')
        || ('\u02B0' <= shar && shar <= '\u02B8')
        || ('\u02BB' <= shar && shar <= '\u02C1')
        || ('\u02D0' <= shar && shar <= '\u02D1')
        || ('\u02E0' <= shar && shar <= '\u02E4')
        || ('\u0300' <= shar && shar <= '\u0345')
        || ('\u0360' <= shar && shar <= '\u0361')
        || '\u037A' == shar || '\u0386' == shar
        || ('\u0388' <= shar && shar <= '\u038A')
        || '\u038C' == shar 
        || ('\u038E' <= shar && shar <= '\u03A1')
        || ('\u03A3' <= shar && shar <= '\u03CE')
        || ('\u03D0' <= shar && shar <= '\u03D6')
        || '\u03DA' == shar || '\u03DC' == shar
        || '\u03DE' == shar || '\u03E0' == shar
        || ('\u03E2' <= shar && shar <= '\u03F3')
        || ('\u0401' <= shar && shar <= '\u040C')
        || ('\u040E' <= shar && shar <= '\u044F')
        || ('\u0451' <= shar && shar <= '\u045C')
        || ('\u045E' <= shar && shar <= '\u0481')
        || ('\u0483' <= shar && shar <= '\u0486')
        || ('\u0490' <= shar && shar <= '\u04C4')
        || ('\u04C7' <= shar && shar <= '\u04C8')
        || ('\u04CB' <= shar && shar <= '\u04CC')
        || ('\u04D0' <= shar && shar <= '\u04EB')
        || ('\u04EE' <= shar && shar <= '\u04F5')
        || ('\u04F8' <= shar && shar <= '\u04F9')
        || ('\u0531' <= shar && shar <= '\u0556')
        || '\u0559' == shar 
        || ('\u0561' <= shar && shar <= '\u0587')
        || ('\u0591' <= shar && shar <= '\u05A1')
        || ('\u05A3' <= shar && shar <= '\u05B9')
        || ('\u05BB' <= shar && shar <= '\u05BD')
        || '\u05BF' == shar 
        || ('\u05C1' <= shar && shar <= '\u05C2')
        || '\u05C4' == shar 
        || ('\u05D0' <= shar && shar <= '\u05EA')
        || ('\u05F0' <= shar && shar <= '\u05F2')
        || ('\u0621' <= shar && shar <= '\u063A')
        || ('\u0640' <= shar && shar <= '\u0652')
        || ('\u0660' <= shar && shar <= '\u0669')
        || ('\u0670' <= shar && shar <= '\u06B7')
        || ('\u06BA' <= shar && shar <= '\u06BE')
        || ('\u06C0' <= shar && shar <= '\u06CE')
        || ('\u06D0' <= shar && shar <= '\u06D3')
        || ('\u06D5' <= shar && shar <= '\u06DC')
        || ('\u06DF' <= shar && shar <= '\u06E8')
        || ('\u06EA' <= shar && shar <= '\u06ED')
        || ('\u06F0' <= shar && shar <= '\u06F9')
        || ('\u0901' <= shar && shar <= '\u0903')
        || ('\u0905' <= shar && shar <= '\u0939')
        || ('\u093C' <= shar && shar <= '\u094D')
        || ('\u0951' <= shar && shar <= '\u0954')
        || ('\u0958' <= shar && shar <= '\u0963')
        || ('\u0966' <= shar && shar <= '\u096F')
        || ('\u0981' <= shar && shar <= '\u0983')
        || ('\u0985' <= shar && shar <= '\u098C')
        || ('\u098F' <= shar && shar <= '\u0990')
        || ('\u0993' <= shar && shar <= '\u09A8')
        || ('\u09AA' <= shar && shar <= '\u09B0')
        || '\u09B2' == shar 
        || ('\u09B6' <= shar && shar <= '\u09B9')
        || '\u09BC' == shar 
        || ('\u09BE' <= shar && shar <= '\u09C4')
        || ('\u09C7' <= shar && shar <= '\u09C8')
        || ('\u09CB' <= shar && shar <= '\u09CD')
        || '\u09D7' == shar 
        || ('\u09DC' <= shar && shar <= '\u09DD')
        || ('\u09DF' <= shar && shar <= '\u09E3')
        || ('\u09E6' <= shar && shar <= '\u09F1')
        || '\u0A02' == shar 
        || ('\u0A05' <= shar && shar <= '\u0A0A')
        || ('\u0A0F' <= shar && shar <= '\u0A10')
        || ('\u0A13' <= shar && shar <= '\u0A28')
        || ('\u0A2A' <= shar && shar <= '\u0A30')
        || ('\u0A32' <= shar && shar <= '\u0A33')
        || ('\u0A35' <= shar && shar <= '\u0A36')
        || ('\u0A38' <= shar && shar <= '\u0A39')
        || '\u0A3C' == shar 
        || ('\u0A3E' <= shar && shar <= '\u0A42')
        || ('\u0A47' <= shar && shar <= '\u0A48')
        || ('\u0A4B' <= shar && shar <= '\u0A4D')
        || ('\u0A59' <= shar && shar <= '\u0A5C')
        || '\u0A5E' == shar 
        || ('\u0A66' <= shar && shar <= '\u0A74')
        || ('\u0A81' <= shar && shar <= '\u0A83')
        || ('\u0A85' <= shar && shar <= '\u0A8B')
        || '\u0A8D' == shar 
        || ('\u0A8F' <= shar && shar <= '\u0A91')
        || ('\u0A93' <= shar && shar <= '\u0AA8')
        || ('\u0AAA' <= shar && shar <= '\u0AB0')
        || ('\u0AB2' <= shar && shar <= '\u0AB3')
        || ('\u0AB5' <= shar && shar <= '\u0AB9')
        || ('\u0ABC' <= shar && shar <= '\u0AC5')
        || ('\u0AC7' <= shar && shar <= '\u0AC9')
        || ('\u0ACB' <= shar && shar <= '\u0ACD')
        || '\u0AE0' == shar 
        || ('\u0AE6' <= shar && shar <= '\u0AEF')
        || ('\u0B01' <= shar && shar <= '\u0B03')
        || ('\u0B05' <= shar && shar <= '\u0B0C')
        || ('\u0B0F' <= shar && shar <= '\u0B10')
        || ('\u0B13' <= shar && shar <= '\u0B28')
        || ('\u0B2A' <= shar && shar <= '\u0B30')
        || ('\u0B32' <= shar && shar <= '\u0B33')
        || ('\u0B36' <= shar && shar <= '\u0B39')
        || ('\u0B3C' <= shar && shar <= '\u0B43')
        || ('\u0B47' <= shar && shar <= '\u0B48')
        || ('\u0B4B' <= shar && shar <= '\u0B4D')
        || ('\u0B56' <= shar && shar <= '\u0B57')
        || ('\u0B5C' <= shar && shar <= '\u0B5D')
        || ('\u0B5F' <= shar && shar <= '\u0B61')
        || ('\u0B66' <= shar && shar <= '\u0B6F')
        || ('\u0B82' <= shar && shar <= '\u0B83')
        || ('\u0B85' <= shar && shar <= '\u0B8A')
        || ('\u0B8E' <= shar && shar <= '\u0B90')
        || ('\u0B92' <= shar && shar <= '\u0B95')
        || ('\u0B99' <= shar && shar <= '\u0B9A')
        || '\u0B9C' == shar 
        || ('\u0B9E' <= shar && shar <= '\u0B9F')
        || ('\u0BA3' <= shar && shar <= '\u0BA4')
        || ('\u0BA8' <= shar && shar <= '\u0BAA')
        || ('\u0BAE' <= shar && shar <= '\u0BB5')
        || ('\u0BB7' <= shar && shar <= '\u0BB9')
        || ('\u0BBE' <= shar && shar <= '\u0BC2')
        || ('\u0BC6' <= shar && shar <= '\u0BC8')
        || ('\u0BCA' <= shar && shar <= '\u0BCD')
        || '\u0BD7' == shar 
        || ('\u0BE7' <= shar && shar <= '\u0BEF')
        || ('\u0C01' <= shar && shar <= '\u0C03')
        || ('\u0C05' <= shar && shar <= '\u0C0C')
        || ('\u0C0E' <= shar && shar <= '\u0C10')
        || ('\u0C12' <= shar && shar <= '\u0C28')
        || ('\u0C2A' <= shar && shar <= '\u0C33')
        || ('\u0C35' <= shar && shar <= '\u0C39')
        || ('\u0C3E' <= shar && shar <= '\u0C44')
        || ('\u0C46' <= shar && shar <= '\u0C48')
        || ('\u0C4A' <= shar && shar <= '\u0C4D')
        || ('\u0C55' <= shar && shar <= '\u0C56')
        || ('\u0C60' <= shar && shar <= '\u0C61')
        || ('\u0C66' <= shar && shar <= '\u0C6F')
        || ('\u0C82' <= shar && shar <= '\u0C83')
        || ('\u0C85' <= shar && shar <= '\u0C8C')
        || ('\u0C8E' <= shar && shar <= '\u0C90')
        || ('\u0C92' <= shar && shar <= '\u0CA8')
        || ('\u0CAA' <= shar && shar <= '\u0CB3')
        || ('\u0CB5' <= shar && shar <= '\u0CB9')
        || ('\u0CBE' <= shar && shar <= '\u0CC4')
        || ('\u0CC6' <= shar && shar <= '\u0CC8')
        || ('\u0CCA' <= shar && shar <= '\u0CCD')
        || ('\u0CD5' <= shar && shar <= '\u0CD6')
        || '\u0CDE' == shar 
        || ('\u0CE0' <= shar && shar <= '\u0CE1')
        || ('\u0CE6' <= shar && shar <= '\u0CEF')
        || ('\u0D02' <= shar && shar <= '\u0D03')
        || ('\u0D05' <= shar && shar <= '\u0D0C')
        || ('\u0D0E' <= shar && shar <= '\u0D10')
        || ('\u0D12' <= shar && shar <= '\u0D28')
        || ('\u0D2A' <= shar && shar <= '\u0D39')
        || ('\u0D3E' <= shar && shar <= '\u0D43')
        || ('\u0D46' <= shar && shar <= '\u0D48')
        || ('\u0D4A' <= shar && shar <= '\u0D4D')
        || '\u0D57' == shar 
        || ('\u0D60' <= shar && shar <= '\u0D61')
        || ('\u0D66' <= shar && shar <= '\u0D6F')
        || ('\u0E01' <= shar && shar <= '\u0E2E')
        || ('\u0E30' <= shar && shar <= '\u0E3A')
        || ('\u0E40' <= shar && shar <= '\u0E4E')
        || ('\u0E50' <= shar && shar <= '\u0E59')
        || ('\u0E81' <= shar && shar <= '\u0E82')
        || '\u0E84' == shar 
        || ('\u0E87' <= shar && shar <= '\u0E88')
        || '\u0E8A' == shar || '\u0E8D' == shar
        || ('\u0E94' <= shar && shar <= '\u0E97')
        || ('\u0E99' <= shar && shar <= '\u0E9F')
        || ('\u0EA1' <= shar && shar <= '\u0EA3')
        || '\u0EA5' == shar || '\u0EA7' == shar
        || ('\u0EAA' <= shar && shar <= '\u0EAB')
        || ('\u0EAD' <= shar && shar <= '\u0EAE')
        || ('\u0EB0' <= shar && shar <= '\u0EB9')
        || ('\u0EBB' <= shar && shar <= '\u0EBD')
        || ('\u0EC0' <= shar && shar <= '\u0EC4')
        || '\u0EC6' == shar 
        || ('\u0EC8' <= shar && shar <= '\u0ECD')
        || ('\u0ED0' <= shar && shar <= '\u0ED9')
        || ('\u0EDC' <= shar && shar <= '\u0EDD')
        || ('\u0F18' <= shar && shar <= '\u0F19')
        || ('\u0F20' <= shar && shar <= '\u0F29')
        || '\u0F35' == shar || '\u0F37' == shar
        || '\u0F39' == shar 
        || ('\u0F3E' <= shar && shar <= '\u0F47')
        || ('\u0F49' <= shar && shar <= '\u0F69')
        || ('\u0F71' <= shar && shar <= '\u0F84')
        || ('\u0F86' <= shar && shar <= '\u0F8B')
        || ('\u0F90' <= shar && shar <= '\u0F95')
        || '\u0F97' == shar 
        || ('\u0F99' <= shar && shar <= '\u0FAD')
        || ('\u0FB1' <= shar && shar <= '\u0FB7')
        || '\u0FB9' == shar 
        || ('\u10A0' <= shar && shar <= '\u10C5')
        || ('\u10D0' <= shar && shar <= '\u10F6')
        || ('\u1100' <= shar && shar <= '\u1159')
        || ('\u115F' <= shar && shar <= '\u11A2')
        || ('\u11A8' <= shar && shar <= '\u11F9')
        || ('\u1E00' <= shar && shar <= '\u1E9B')
        || ('\u1EA0' <= shar && shar <= '\u1EF9')
        || ('\u1F00' <= shar && shar <= '\u1F15')
        || ('\u1F18' <= shar && shar <= '\u1F1D')
        || ('\u1F20' <= shar && shar <= '\u1F45')
        || ('\u1F48' <= shar && shar <= '\u1F4D')
        || ('\u1F50' <= shar && shar <= '\u1F57')
        || '\u1F59' == shar || '\u1F5B' == shar
        || '\u1F5D' == shar 
        || ('\u1F5F' <= shar && shar <= '\u1F7D')
        || ('\u1F80' <= shar && shar <= '\u1FB4')
        || ('\u1FB6' <= shar && shar <= '\u1FBC')
        || '\u1FBE' == shar 
        || ('\u1FC2' <= shar && shar <= '\u1FC4')
        || ('\u1FC6' <= shar && shar <= '\u1FCC')
        || ('\u1FD0' <= shar && shar <= '\u1FD3')
        || ('\u1FD6' <= shar && shar <= '\u1FDB')
        || ('\u1FE0' <= shar && shar <= '\u1FEC')
        || ('\u1FF2' <= shar && shar <= '\u1FF4')
        || ('\u1FF6' <= shar && shar <= '\u1FFC')
        || ('\u200C' <= shar && shar <= '\u200F')
        || ('\u202A' <= shar && shar <= '\u202E')
        || ('\u203F' <= shar && shar <= '\u2040')
        || ('\u206A' <= shar && shar <= '\u206F')
        || '\u207F' == shar 
        || ('\u20D0' <= shar && shar <= '\u20DC')
        || '\u20E1' == shar || '\u2102' == shar
        || '\u2107' == shar 
        || ('\u210A' <= shar && shar <= '\u2113')
        || '\u2115' == shar 
        || ('\u2118' <= shar && shar <= '\u211D')
        || '\u2124' == shar || '\u2126' == shar
        || '\u2128' == shar 
        || ('\u212A' <= shar && shar <= '\u2131')
        || ('\u2133' <= shar && shar <= '\u2138')
        || ('\u2160' <= shar && shar <= '\u2182')
        || '\u3005' == shar || '\u3007' == shar
        || ('\u3021' <= shar && shar <= '\u302F')
        || ('\u3031' <= shar && shar <= '\u3035')
        || ('\u3041' <= shar && shar <= '\u3094')
        || ('\u3099' <= shar && shar <= '\u309E')
        || ('\u30A1' <= shar && shar <= '\u30FA')
        || ('\u30FC' <= shar && shar <= '\u30FE')
        || ('\u3105' <= shar && shar <= '\u312C')
        || ('\u3131' <= shar && shar <= '\u318E')
        || ('\u4E00' <= shar && shar <= '\u9FA5')
        || ('\uAC00' <= shar && shar <= '\uD7A3')
        || ('\uF900' <= shar && shar <= '\uFA2D')
        || ('\uFB00' <= shar && shar <= '\uFB06')
        || ('\uFB13' <= shar && shar <= '\uFB17')
        || ('\uFB1E' <= shar && shar <= '\uFB28')
        || ('\uFB2A' <= shar && shar <= '\uFB36')
        || ('\uFB38' <= shar && shar <= '\uFB3C')
        || '\uFB3E' == shar 
        || ('\uFB40' <= shar && shar <= '\uFB41')
        || ('\uFB43' <= shar && shar <= '\uFB44')
        || ('\uFB46' <= shar && shar <= '\uFBB1')
        || ('\uFBD3' <= shar && shar <= '\uFD3D')
        || ('\uFD50' <= shar && shar <= '\uFD8F')
        || ('\uFD92' <= shar && shar <= '\uFDC7')
        || ('\uFDF0' <= shar && shar <= '\uFDFB')
        || ('\uFE20' <= shar && shar <= '\uFE23')
        || ('\uFE33' <= shar && shar <= '\uFE34')
        || ('\uFE4D' <= shar && shar <= '\uFE4F')
        || ('\uFE70' <= shar && shar <= '\uFE72')
        || '\uFE74' == shar 
        || ('\uFE76' <= shar && shar <= '\uFEFC')
        || '\uFEFF' == shar 
        || ('\uFF10' <= shar && shar <= '\uFF19')
        || ('\uFF21' <= shar && shar <= '\uFF3A')
        || '\uFF3F' == shar 
        || ('\uFF41' <= shar && shar <= '\uFF5A')
        || ('\uFF66' <= shar && shar <= '\uFFBE')
        || ('\uFFC2' <= shar && shar <= '\uFFC7')
        || ('\uFFCA' <= shar && shar <= '\uFFCF')
        || ('\uFFD2' <= shar && shar <= '\uFFD7')
        || ('\uFFDA' <= shar && shar <= '\uFFDC');
  }

  /** @deprecated */
  public static boolean isSpace(char shar) {
    return shar == '\t'
        || shar == '\n'
        || shar == '\f'
        || shar == '\r'
        || shar == ' ';
  }

  public static boolean isSpaceChar(char shar) {
    return shar == '\u0020' || shar == '\u00A0'
        || ('\u2000' <= shar && shar <= '\u200B')
        || ('\u2028' <= shar && shar <= '\u2029')
        || shar == '\u3000';
  }

  public static boolean isWhitespace(char shar) {
    return ('\u0009' <= shar && shar <= '\r')
        || ('\u001C' <= shar && shar <= '\u0020')
        || ('\u2000' <= shar && shar <= '\u200B')
        || ('\u2028' <= shar && shar <= '\u2029')
        || shar == '\u3000';
  }

  public static boolean isISOControl(char shar) {
    return ('\u0000' <= shar && shar <= '\u001F') 
        || ('\u007F' <= shar && shar <= '\u009F');
  }

  public static char toLowerCase(char shar) {
    switch (shar) {
    case '\u0041': return '\u0061';
    case '\u0042': return '\u0062';
    case '\u0043': return '\u0063';
    case '\u0044': return '\u0064';
    case '\u0045': return '\u0065';
    case '\u0046': return '\u0066';
    case '\u0047': return '\u0067';
    case '\u0048': return '\u0068';
    case '\u0049': return '\u0069';
    case '\u004A': return '\u006A';
    case '\u004B': return '\u006B';
    case '\u004C': return '\u006C';
    case '\u004D': return '\u006D';
    case '\u004E': return '\u006E';
    case '\u004F': return '\u006F';
    case '\u0050': return '\u0070';
    case '\u0051': return '\u0071';
    case '\u0052': return '\u0072';
    case '\u0053': return '\u0073';
    case '\u0054': return '\u0074';
    case '\u0055': return '\u0075';
    case '\u0056': return '\u0076';
    case '\u0057': return '\u0077';
    case '\u0058': return '\u0078';
    case '\u0059': return '\u0079';
    case '\u005A': return '\u007A';
    case '\u00C0': return '\u00E0';
    case '\u00C1': return '\u00E1';
    case '\u00C2': return '\u00E2';
    case '\u00C3': return '\u00E3';
    case '\u00C4': return '\u00E4';
    case '\u00C5': return '\u00E5';
    case '\u00C6': return '\u00E6';
    case '\u00C7': return '\u00E7';
    case '\u00C8': return '\u00E8';
    case '\u00C9': return '\u00E9';
    case '\u00CA': return '\u00EA';
    case '\u00CB': return '\u00EB';
    case '\u00CC': return '\u00EC';
    case '\u00CD': return '\u00ED';
    case '\u00CE': return '\u00EE';
    case '\u00CF': return '\u00EF';
    case '\u00D0': return '\u00F0';
    case '\u00D1': return '\u00F1';
    case '\u00D2': return '\u00F2';
    case '\u00D3': return '\u00F3';
    case '\u00D4': return '\u00F4';
    case '\u00D5': return '\u00F5';
    case '\u00D6': return '\u00F6';
    case '\u00D8': return '\u00F8';
    case '\u00D9': return '\u00F9';
    case '\u00DA': return '\u00FA';
    case '\u00DB': return '\u00FB';
    case '\u00DC': return '\u00FC';
    case '\u00DD': return '\u00FD';
    case '\u00DE': return '\u00FE';
    case '\u0100': return '\u0101';
    case '\u0102': return '\u0103';
    case '\u0104': return '\u0105';
    case '\u0106': return '\u0107';
    case '\u0108': return '\u0109';
    case '\u010A': return '\u010B';
    case '\u010C': return '\u010D';
    case '\u010E': return '\u010F';
    case '\u0110': return '\u0111';
    case '\u0112': return '\u0113';
    case '\u0114': return '\u0115';
    case '\u0116': return '\u0117';
    case '\u0118': return '\u0119';
    case '\u011A': return '\u011B';
    case '\u011C': return '\u011D';
    case '\u011E': return '\u011F';
    case '\u0120': return '\u0121';
    case '\u0122': return '\u0123';
    case '\u0124': return '\u0125';
    case '\u0126': return '\u0127';
    case '\u0128': return '\u0129';
    case '\u012A': return '\u012B';
    case '\u012C': return '\u012D';
    case '\u012E': return '\u012F';
    case '\u0130': return '\u0069';
    case '\u0132': return '\u0133';
    case '\u0134': return '\u0135';
    case '\u0136': return '\u0137';
    case '\u0139': return '\u013A';
    case '\u013B': return '\u013C';
    case '\u013D': return '\u013E';
    case '\u013F': return '\u0140';
    case '\u0141': return '\u0142';
    case '\u0143': return '\u0144';
    case '\u0145': return '\u0146';
    case '\u0147': return '\u0148';
    case '\u014A': return '\u014B';
    case '\u014C': return '\u014D';
    case '\u014E': return '\u014F';
    case '\u0150': return '\u0151';
    case '\u0152': return '\u0153';
    case '\u0154': return '\u0155';
    case '\u0156': return '\u0157';
    case '\u0158': return '\u0159';
    case '\u015A': return '\u015B';
    case '\u015C': return '\u015D';
    case '\u015E': return '\u015F';
    case '\u0160': return '\u0161';
    case '\u0162': return '\u0163';
    case '\u0164': return '\u0165';
    case '\u0166': return '\u0167';
    case '\u0168': return '\u0169';
    case '\u016A': return '\u016B';
    case '\u016C': return '\u016D';
    case '\u016E': return '\u016F';
    case '\u0170': return '\u0171';
    case '\u0172': return '\u0173';
    case '\u0174': return '\u0175';
    case '\u0176': return '\u0177';
    case '\u0178': return '\u00FF';
    case '\u0179': return '\u017A';
    case '\u017B': return '\u017C';
    case '\u017D': return '\u017E';
    case '\u0181': return '\u0253';
    case '\u0182': return '\u0183';
    case '\u0184': return '\u0185';
    case '\u0186': return '\u0254';
    case '\u0187': return '\u0188';
    case '\u0189': return '\u0256';
    case '\u018A': return '\u0257';
    case '\u018B': return '\u018C';
    case '\u018E': return '\u01DD';
    case '\u018F': return '\u0259';
    case '\u0190': return '\u025B';
    case '\u0191': return '\u0192';
    case '\u0193': return '\u0260';
    case '\u0194': return '\u0263';
    case '\u0196': return '\u0269';
    case '\u0197': return '\u0268';
    case '\u0198': return '\u0199';
    case '\u019C': return '\u026F';
    case '\u019D': return '\u0272';
    case '\u019F': return '\u0275';
    case '\u01A0': return '\u01A1';
    case '\u01A2': return '\u01A3';
    case '\u01A4': return '\u01A5';
    case '\u01A7': return '\u01A8';
    case '\u01A9': return '\u0283';
    case '\u01AC': return '\u01AD';
    case '\u01AE': return '\u0288';
    case '\u01AF': return '\u01B0';
    case '\u01B1': return '\u028A';
    case '\u01B2': return '\u028B';
    case '\u01B3': return '\u01B4';
    case '\u01B5': return '\u01B6';
    case '\u01B7': return '\u0292';
    case '\u01B8': return '\u01B9';
    case '\u01BC': return '\u01BD';
    case '\u01C4': return '\u01C6';
    case '\u01C5': return '\u01C6';
    case '\u01C7': return '\u01C9';
    case '\u01C8': return '\u01C9';
    case '\u01CA': return '\u01CC';
    case '\u01CB': return '\u01CC';
    case '\u01CD': return '\u01CE';
    case '\u01CF': return '\u01D0';
    case '\u01D1': return '\u01D2';
    case '\u01D3': return '\u01D4';
    case '\u01D5': return '\u01D6';
    case '\u01D7': return '\u01D8';
    case '\u01D9': return '\u01DA';
    case '\u01DB': return '\u01DC';
    case '\u01DE': return '\u01DF';
    case '\u01E0': return '\u01E1';
    case '\u01E2': return '\u01E3';
    case '\u01E4': return '\u01E5';
    case '\u01E6': return '\u01E7';
    case '\u01E8': return '\u01E9';
    case '\u01EA': return '\u01EB';
    case '\u01EC': return '\u01ED';
    case '\u01EE': return '\u01EF';
    case '\u01F1': return '\u01F3';
    case '\u01F2': return '\u01F3';
    case '\u01F4': return '\u01F5';
    case '\u01FA': return '\u01FB';
    case '\u01FC': return '\u01FD';
    case '\u01FE': return '\u01FF';
    case '\u0200': return '\u0201';
    case '\u0202': return '\u0203';
    case '\u0204': return '\u0205';
    case '\u0206': return '\u0207';
    case '\u0208': return '\u0209';
    case '\u020A': return '\u020B';
    case '\u020C': return '\u020D';
    case '\u020E': return '\u020F';
    case '\u0210': return '\u0211';
    case '\u0212': return '\u0213';
    case '\u0214': return '\u0215';
    case '\u0216': return '\u0217';
    case '\u0275': return '\u019F';
    case '\u0386': return '\u03AC';
    case '\u0388': return '\u03AD';
    case '\u0389': return '\u03AE';
    case '\u038A': return '\u03AF';
    case '\u038C': return '\u03CC';
    case '\u038E': return '\u03CD';
    case '\u038F': return '\u03CE';
    case '\u0391': return '\u03B1';
    case '\u0392': return '\u03B2';
    case '\u0393': return '\u03B3';
    case '\u0394': return '\u03B4';
    case '\u0395': return '\u03B5';
    case '\u0396': return '\u03B6';
    case '\u0397': return '\u03B7';
    case '\u0398': return '\u03B8';
    case '\u0399': return '\u03B9';
    case '\u039A': return '\u03BA';
    case '\u039B': return '\u03BB';
    case '\u039C': return '\u03BC';
    case '\u039D': return '\u03BD';
    case '\u039E': return '\u03BE';
    case '\u039F': return '\u03BF';
    case '\u03A0': return '\u03C0';
    case '\u03A1': return '\u03C1';
    case '\u03A3': return '\u03C3';
    case '\u03A4': return '\u03C4';
    case '\u03A5': return '\u03C5';
    case '\u03A6': return '\u03C6';
    case '\u03A7': return '\u03C7';
    case '\u03A8': return '\u03C8';
    case '\u03A9': return '\u03C9';
    case '\u03AA': return '\u03CA';
    case '\u03AB': return '\u03CB';
    case '\u03E2': return '\u03E3';
    case '\u03E4': return '\u03E5';
    case '\u03E6': return '\u03E7';
    case '\u03E8': return '\u03E9';
    case '\u03EA': return '\u03EB';
    case '\u03EC': return '\u03ED';
    case '\u03EE': return '\u03EF';
    case '\u0401': return '\u0451';
    case '\u0402': return '\u0452';
    case '\u0403': return '\u0453';
    case '\u0404': return '\u0454';
    case '\u0405': return '\u0455';
    case '\u0406': return '\u0456';
    case '\u0407': return '\u0457';
    case '\u0408': return '\u0458';
    case '\u0409': return '\u0459';
    case '\u040A': return '\u045A';
    case '\u040B': return '\u045B';
    case '\u040C': return '\u045C';
    case '\u040E': return '\u045E';
    case '\u040F': return '\u045F';
    case '\u0410': return '\u0430';
    case '\u0411': return '\u0431';
    case '\u0412': return '\u0432';
    case '\u0413': return '\u0433';
    case '\u0414': return '\u0434';
    case '\u0415': return '\u0435';
    case '\u0416': return '\u0436';
    case '\u0417': return '\u0437';
    case '\u0418': return '\u0438';
    case '\u0419': return '\u0439';
    case '\u041A': return '\u043A';
    case '\u041B': return '\u043B';
    case '\u041C': return '\u043C';
    case '\u041D': return '\u043D';
    case '\u041E': return '\u043E';
    case '\u041F': return '\u043F';
    case '\u0420': return '\u0440';
    case '\u0421': return '\u0441';
    case '\u0422': return '\u0442';
    case '\u0423': return '\u0443';
    case '\u0424': return '\u0444';
    case '\u0425': return '\u0445';
    case '\u0426': return '\u0446';
    case '\u0427': return '\u0447';
    case '\u0428': return '\u0448';
    case '\u0429': return '\u0449';
    case '\u042A': return '\u044A';
    case '\u042B': return '\u044B';
    case '\u042C': return '\u044C';
    case '\u042D': return '\u044D';
    case '\u042E': return '\u044E';
    case '\u042F': return '\u044F';
    case '\u0460': return '\u0461';
    case '\u0462': return '\u0463';
    case '\u0464': return '\u0465';
    case '\u0466': return '\u0467';
    case '\u0468': return '\u0469';
    case '\u046A': return '\u046B';
    case '\u046C': return '\u046D';
    case '\u046E': return '\u046F';
    case '\u0470': return '\u0471';
    case '\u0472': return '\u0473';
    case '\u0474': return '\u0475';
    case '\u0476': return '\u0477';
    case '\u0478': return '\u0479';
    case '\u047A': return '\u047B';
    case '\u047C': return '\u047D';
    case '\u047E': return '\u047F';
    case '\u0480': return '\u0481';
    case '\u0490': return '\u0491';
    case '\u0492': return '\u0493';
    case '\u0494': return '\u0495';
    case '\u0496': return '\u0497';
    case '\u0498': return '\u0499';
    case '\u049A': return '\u049B';
    case '\u049C': return '\u049D';
    case '\u049E': return '\u049F';
    case '\u04A0': return '\u04A1';
    case '\u04A2': return '\u04A3';
    case '\u04A4': return '\u04A5';
    case '\u04A6': return '\u04A7';
    case '\u04A8': return '\u04A9';
    case '\u04AA': return '\u04AB';
    case '\u04AC': return '\u04AD';
    case '\u04AE': return '\u04AF';
    case '\u04B0': return '\u04B1';
    case '\u04B2': return '\u04B3';
    case '\u04B4': return '\u04B5';
    case '\u04B6': return '\u04B7';
    case '\u04B8': return '\u04B9';
    case '\u04BA': return '\u04BB';
    case '\u04BC': return '\u04BD';
    case '\u04BE': return '\u04BF';
    case '\u04C1': return '\u04C2';
    case '\u04C3': return '\u04C4';
    case '\u04C7': return '\u04C8';
    case '\u04CB': return '\u04CC';
    case '\u04D0': return '\u04D1';
    case '\u04D2': return '\u04D3';
    case '\u04D4': return '\u04D5';
    case '\u04D6': return '\u04D7';
    case '\u04D8': return '\u04D9';
    case '\u04DA': return '\u04DB';
    case '\u04DC': return '\u04DD';
    case '\u04DE': return '\u04DF';
    case '\u04E0': return '\u04E1';
    case '\u04E2': return '\u04E3';
    case '\u04E4': return '\u04E5';
    case '\u04E6': return '\u04E7';
    case '\u04E8': return '\u04E9';
    case '\u04EA': return '\u04EB';
    case '\u04EE': return '\u04EF';
    case '\u04F0': return '\u04F1';
    case '\u04F2': return '\u04F3';
    case '\u04F4': return '\u04F5';
    case '\u04F8': return '\u04F9';
    case '\u0531': return '\u0561';
    case '\u0532': return '\u0562';
    case '\u0533': return '\u0563';
    case '\u0534': return '\u0564';
    case '\u0535': return '\u0565';
    case '\u0536': return '\u0566';
    case '\u0537': return '\u0567';
    case '\u0538': return '\u0568';
    case '\u0539': return '\u0569';
    case '\u053A': return '\u056A';
    case '\u053B': return '\u056B';
    case '\u053C': return '\u056C';
    case '\u053D': return '\u056D';
    case '\u053E': return '\u056E';
    case '\u053F': return '\u056F';
    case '\u0540': return '\u0570';
    case '\u0541': return '\u0571';
    case '\u0542': return '\u0572';
    case '\u0543': return '\u0573';
    case '\u0544': return '\u0574';
    case '\u0545': return '\u0575';
    case '\u0546': return '\u0576';
    case '\u0547': return '\u0577';
    case '\u0548': return '\u0578';
    case '\u0549': return '\u0579';
    case '\u054A': return '\u057A';
    case '\u054B': return '\u057B';
    case '\u054C': return '\u057C';
    case '\u054D': return '\u057D';
    case '\u054E': return '\u057E';
    case '\u054F': return '\u057F';
    case '\u0550': return '\u0580';
    case '\u0551': return '\u0581';
    case '\u0552': return '\u0582';
    case '\u0553': return '\u0583';
    case '\u0554': return '\u0584';
    case '\u0555': return '\u0585';
    case '\u0556': return '\u0586';
    case '\u10A0': return '\u10D0';
    case '\u10A1': return '\u10D1';
    case '\u10A2': return '\u10D2';
    case '\u10A3': return '\u10D3';
    case '\u10A4': return '\u10D4';
    case '\u10A5': return '\u10D5';
    case '\u10A6': return '\u10D6';
    case '\u10A7': return '\u10D7';
    case '\u10A8': return '\u10D8';
    case '\u10A9': return '\u10D9';
    case '\u10AA': return '\u10DA';
    case '\u10AB': return '\u10DB';
    case '\u10AC': return '\u10DC';
    case '\u10AD': return '\u10DD';
    case '\u10AE': return '\u10DE';
    case '\u10AF': return '\u10DF';
    case '\u10B0': return '\u10E0';
    case '\u10B1': return '\u10E1';
    case '\u10B2': return '\u10E2';
    case '\u10B3': return '\u10E3';
    case '\u10B4': return '\u10E4';
    case '\u10B5': return '\u10E5';
    case '\u10B6': return '\u10E6';
    case '\u10B7': return '\u10E7';
    case '\u10B8': return '\u10E8';
    case '\u10B9': return '\u10E9';
    case '\u10BA': return '\u10EA';
    case '\u10BB': return '\u10EB';
    case '\u10BC': return '\u10EC';
    case '\u10BD': return '\u10ED';
    case '\u10BE': return '\u10EE';
    case '\u10BF': return '\u10EF';
    case '\u10C0': return '\u10F0';
    case '\u10C1': return '\u10F1';
    case '\u10C2': return '\u10F2';
    case '\u10C3': return '\u10F3';
    case '\u10C4': return '\u10F4';
    case '\u10C5': return '\u10F5';
    case '\u1E00': return '\u1E01';
    case '\u1E02': return '\u1E03';
    case '\u1E04': return '\u1E05';
    case '\u1E06': return '\u1E07';
    case '\u1E08': return '\u1E09';
    case '\u1E0A': return '\u1E0B';
    case '\u1E0C': return '\u1E0D';
    case '\u1E0E': return '\u1E0F';
    case '\u1E10': return '\u1E11';
    case '\u1E12': return '\u1E13';
    case '\u1E14': return '\u1E15';
    case '\u1E16': return '\u1E17';
    case '\u1E18': return '\u1E19';
    case '\u1E1A': return '\u1E1B';
    case '\u1E1C': return '\u1E1D';
    case '\u1E1E': return '\u1E1F';
    case '\u1E20': return '\u1E21';
    case '\u1E22': return '\u1E23';
    case '\u1E24': return '\u1E25';
    case '\u1E26': return '\u1E27';
    case '\u1E28': return '\u1E29';
    case '\u1E2A': return '\u1E2B';
    case '\u1E2C': return '\u1E2D';
    case '\u1E2E': return '\u1E2F';
    case '\u1E30': return '\u1E31';
    case '\u1E32': return '\u1E33';
    case '\u1E34': return '\u1E35';
    case '\u1E36': return '\u1E37';
    case '\u1E38': return '\u1E39';
    case '\u1E3A': return '\u1E3B';
    case '\u1E3C': return '\u1E3D';
    case '\u1E3E': return '\u1E3F';
    case '\u1E40': return '\u1E41';
    case '\u1E42': return '\u1E43';
    case '\u1E44': return '\u1E45';
    case '\u1E46': return '\u1E47';
    case '\u1E48': return '\u1E49';
    case '\u1E4A': return '\u1E4B';
    case '\u1E4C': return '\u1E4D';
    case '\u1E4E': return '\u1E4F';
    case '\u1E50': return '\u1E51';
    case '\u1E52': return '\u1E53';
    case '\u1E54': return '\u1E55';
    case '\u1E56': return '\u1E57';
    case '\u1E58': return '\u1E59';
    case '\u1E5A': return '\u1E5B';
    case '\u1E5C': return '\u1E5D';
    case '\u1E5E': return '\u1E5F';
    case '\u1E60': return '\u1E61';
    case '\u1E62': return '\u1E63';
    case '\u1E64': return '\u1E65';
    case '\u1E66': return '\u1E67';
    case '\u1E68': return '\u1E69';
    case '\u1E6A': return '\u1E6B';
    case '\u1E6C': return '\u1E6D';
    case '\u1E6E': return '\u1E6F';
    case '\u1E70': return '\u1E71';
    case '\u1E72': return '\u1E73';
    case '\u1E74': return '\u1E75';
    case '\u1E76': return '\u1E77';
    case '\u1E78': return '\u1E79';
    case '\u1E7A': return '\u1E7B';
    case '\u1E7C': return '\u1E7D';
    case '\u1E7E': return '\u1E7F';
    case '\u1E80': return '\u1E81';
    case '\u1E82': return '\u1E83';
    case '\u1E84': return '\u1E85';
    case '\u1E86': return '\u1E87';
    case '\u1E88': return '\u1E89';
    case '\u1E8A': return '\u1E8B';
    case '\u1E8C': return '\u1E8D';
    case '\u1E8E': return '\u1E8F';
    case '\u1E90': return '\u1E91';
    case '\u1E92': return '\u1E93';
    case '\u1E94': return '\u1E95';
    case '\u1EA0': return '\u1EA1';
    case '\u1EA2': return '\u1EA3';
    case '\u1EA4': return '\u1EA5';
    case '\u1EA6': return '\u1EA7';
    case '\u1EA8': return '\u1EA9';
    case '\u1EAA': return '\u1EAB';
    case '\u1EAC': return '\u1EAD';
    case '\u1EAE': return '\u1EAF';
    case '\u1EB0': return '\u1EB1';
    case '\u1EB2': return '\u1EB3';
    case '\u1EB4': return '\u1EB5';
    case '\u1EB6': return '\u1EB7';
    case '\u1EB8': return '\u1EB9';
    case '\u1EBA': return '\u1EBB';
    case '\u1EBC': return '\u1EBD';
    case '\u1EBE': return '\u1EBF';
    case '\u1EC0': return '\u1EC1';
    case '\u1EC2': return '\u1EC3';
    case '\u1EC4': return '\u1EC5';
    case '\u1EC6': return '\u1EC7';
    case '\u1EC8': return '\u1EC9';
    case '\u1ECA': return '\u1ECB';
    case '\u1ECC': return '\u1ECD';
    case '\u1ECE': return '\u1ECF';
    case '\u1ED0': return '\u1ED1';
    case '\u1ED2': return '\u1ED3';
    case '\u1ED4': return '\u1ED5';
    case '\u1ED6': return '\u1ED7';
    case '\u1ED8': return '\u1ED9';
    case '\u1EDA': return '\u1EDB';
    case '\u1EDC': return '\u1EDD';
    case '\u1EDE': return '\u1EDF';
    case '\u1EE0': return '\u1EE1';
    case '\u1EE2': return '\u1EE3';
    case '\u1EE4': return '\u1EE5';
    case '\u1EE6': return '\u1EE7';
    case '\u1EE8': return '\u1EE9';
    case '\u1EEA': return '\u1EEB';
    case '\u1EEC': return '\u1EED';
    case '\u1EEE': return '\u1EEF';
    case '\u1EF0': return '\u1EF1';
    case '\u1EF2': return '\u1EF3';
    case '\u1EF4': return '\u1EF5';
    case '\u1EF6': return '\u1EF7';
    case '\u1EF8': return '\u1EF9';
    case '\u1F08': return '\u1F00';
    case '\u1F09': return '\u1F01';
    case '\u1F0A': return '\u1F02';
    case '\u1F0B': return '\u1F03';
    case '\u1F0C': return '\u1F04';
    case '\u1F0D': return '\u1F05';
    case '\u1F0E': return '\u1F06';
    case '\u1F0F': return '\u1F07';
    case '\u1F18': return '\u1F10';
    case '\u1F19': return '\u1F11';
    case '\u1F1A': return '\u1F12';
    case '\u1F1B': return '\u1F13';
    case '\u1F1C': return '\u1F14';
    case '\u1F1D': return '\u1F15';
    case '\u1F28': return '\u1F20';
    case '\u1F29': return '\u1F21';
    case '\u1F2A': return '\u1F22';
    case '\u1F2B': return '\u1F23';
    case '\u1F2C': return '\u1F24';
    case '\u1F2D': return '\u1F25';
    case '\u1F2E': return '\u1F26';
    case '\u1F2F': return '\u1F27';
    case '\u1F38': return '\u1F30';
    case '\u1F39': return '\u1F31';
    case '\u1F3A': return '\u1F32';
    case '\u1F3B': return '\u1F33';
    case '\u1F3C': return '\u1F34';
    case '\u1F3D': return '\u1F35';
    case '\u1F3E': return '\u1F36';
    case '\u1F3F': return '\u1F37';
    case '\u1F48': return '\u1F40';
    case '\u1F49': return '\u1F41';
    case '\u1F4A': return '\u1F42';
    case '\u1F4B': return '\u1F43';
    case '\u1F4C': return '\u1F44';
    case '\u1F4D': return '\u1F45';
    case '\u1F59': return '\u1F51';
    case '\u1F5B': return '\u1F53';
    case '\u1F5D': return '\u1F55';
    case '\u1F5F': return '\u1F57';
    case '\u1F68': return '\u1F60';
    case '\u1F69': return '\u1F61';
    case '\u1F6A': return '\u1F62';
    case '\u1F6B': return '\u1F63';
    case '\u1F6C': return '\u1F64';
    case '\u1F6D': return '\u1F65';
    case '\u1F6E': return '\u1F66';
    case '\u1F6F': return '\u1F67';
    case '\u1F88': return '\u1F80';
    case '\u1F89': return '\u1F81';
    case '\u1F8A': return '\u1F82';
    case '\u1F8B': return '\u1F83';
    case '\u1F8C': return '\u1F84';
    case '\u1F8D': return '\u1F85';
    case '\u1F8E': return '\u1F86';
    case '\u1F8F': return '\u1F87';
    case '\u1F98': return '\u1F90';
    case '\u1F99': return '\u1F91';
    case '\u1F9A': return '\u1F92';
    case '\u1F9B': return '\u1F93';
    case '\u1F9C': return '\u1F94';
    case '\u1F9D': return '\u1F95';
    case '\u1F9E': return '\u1F96';
    case '\u1F9F': return '\u1F97';
    case '\u1FA8': return '\u1FA0';
    case '\u1FA9': return '\u1FA1';
    case '\u1FAA': return '\u1FA2';
    case '\u1FAB': return '\u1FA3';
    case '\u1FAC': return '\u1FA4';
    case '\u1FAD': return '\u1FA5';
    case '\u1FAE': return '\u1FA6';
    case '\u1FAF': return '\u1FA7';
    case '\u1FB8': return '\u1FB0';
    case '\u1FB9': return '\u1FB1';
    case '\u1FBA': return '\u1F70';
    case '\u1FBB': return '\u1F71';
    case '\u1FBC': return '\u1FB3';
    case '\u1FC8': return '\u1F72';
    case '\u1FC9': return '\u1F73';
    case '\u1FCA': return '\u1F74';
    case '\u1FCB': return '\u1F75';
    case '\u1FCC': return '\u1FC3';
    case '\u1FD8': return '\u1FD0';
    case '\u1FD9': return '\u1FD1';
    case '\u1FDA': return '\u1F76';
    case '\u1FDB': return '\u1F77';
    case '\u1FE8': return '\u1FE0';
    case '\u1FE9': return '\u1FE1';
    case '\u1FEA': return '\u1F7A';
    case '\u1FEB': return '\u1F7B';
    case '\u1FEC': return '\u1FE5';
    case '\u1FF8': return '\u1F78';
    case '\u1FF9': return '\u1F79';
    case '\u1FFA': return '\u1F7C';
    case '\u1FFB': return '\u1F7D';
    case '\u1FFC': return '\u1FF3';
    case '\u2160': return '\u2170';
    case '\u2161': return '\u2171';
    case '\u2162': return '\u2172';
    case '\u2163': return '\u2173';
    case '\u2164': return '\u2174';
    case '\u2165': return '\u2175';
    case '\u2166': return '\u2176';
    case '\u2167': return '\u2177';
    case '\u2168': return '\u2178';
    case '\u2169': return '\u2179';
    case '\u216A': return '\u217A';
    case '\u216B': return '\u217B';
    case '\u216C': return '\u217C';
    case '\u216D': return '\u217D';
    case '\u216E': return '\u217E';
    case '\u216F': return '\u217F';
    case '\u24B6': return '\u24D0';
    case '\u24B7': return '\u24D1';
    case '\u24B8': return '\u24D2';
    case '\u24B9': return '\u24D3';
    case '\u24BA': return '\u24D4';
    case '\u24BB': return '\u24D5';
    case '\u24BC': return '\u24D6';
    case '\u24BD': return '\u24D7';
    case '\u24BE': return '\u24D8';
    case '\u24BF': return '\u24D9';
    case '\u24C0': return '\u24DA';
    case '\u24C1': return '\u24DB';
    case '\u24C2': return '\u24DC';
    case '\u24C3': return '\u24DD';
    case '\u24C4': return '\u24DE';
    case '\u24C5': return '\u24DF';
    case '\u24C6': return '\u24E0';
    case '\u24C7': return '\u24E1';
    case '\u24C8': return '\u24E2';
    case '\u24C9': return '\u24E3';
    case '\u24CA': return '\u24E4';
    case '\u24CB': return '\u24E5';
    case '\u24CC': return '\u24E6';
    case '\u24CD': return '\u24E7';
    case '\u24CE': return '\u24E8';
    case '\u24CF': return '\u24E9';
    case '\uFF21': return '\uFF41';
    case '\uFF22': return '\uFF42';
    case '\uFF23': return '\uFF43';
    case '\uFF24': return '\uFF44';
    case '\uFF25': return '\uFF45';
    case '\uFF26': return '\uFF46';
    case '\uFF27': return '\uFF47';
    case '\uFF28': return '\uFF48';
    case '\uFF29': return '\uFF49';
    case '\uFF2A': return '\uFF4A';
    case '\uFF2B': return '\uFF4B';
    case '\uFF2C': return '\uFF4C';
    case '\uFF2D': return '\uFF4D';
    case '\uFF2E': return '\uFF4E';
    case '\uFF2F': return '\uFF4F';
    case '\uFF30': return '\uFF50';
    case '\uFF31': return '\uFF51';
    case '\uFF32': return '\uFF52';
    case '\uFF33': return '\uFF53';
    case '\uFF34': return '\uFF54';
    case '\uFF35': return '\uFF55';
    case '\uFF36': return '\uFF56';
    case '\uFF37': return '\uFF57';
    case '\uFF38': return '\uFF58';
    case '\uFF39': return '\uFF59';
    case '\uFF3A': return '\uFF5A';
    default: return shar;
    }
  }

  public static char toUpperCase(char shar) {
    switch (shar) {
    case '\u0061': return '\u0041';
    case '\u0062': return '\u0042';
    case '\u0063': return '\u0043';
    case '\u0064': return '\u0044';
    case '\u0065': return '\u0045';
    case '\u0066': return '\u0046';
    case '\u0067': return '\u0047';
    case '\u0068': return '\u0048';
    case '\u0069': return '\u0049';
    case '\u006A': return '\u004A';
    case '\u006B': return '\u004B';
    case '\u006C': return '\u004C';
    case '\u006D': return '\u004D';
    case '\u006E': return '\u004E';
    case '\u006F': return '\u004F';
    case '\u0070': return '\u0050';
    case '\u0071': return '\u0051';
    case '\u0072': return '\u0052';
    case '\u0073': return '\u0053';
    case '\u0074': return '\u0054';
    case '\u0075': return '\u0055';
    case '\u0076': return '\u0056';
    case '\u0077': return '\u0057';
    case '\u0078': return '\u0058';
    case '\u0079': return '\u0059';
    case '\u007A': return '\u005A';
    case '\u00E0': return '\u00C0';
    case '\u00E1': return '\u00C1';
    case '\u00E2': return '\u00C2';
    case '\u00E3': return '\u00C3';
    case '\u00E4': return '\u00C4';
    case '\u00E5': return '\u00C5';
    case '\u00E6': return '\u00C6';
    case '\u00E7': return '\u00C7';
    case '\u00E8': return '\u00C8';
    case '\u00E9': return '\u00C9';
    case '\u00EA': return '\u00CA';
    case '\u00EB': return '\u00CB';
    case '\u00EC': return '\u00CC';
    case '\u00ED': return '\u00CD';
    case '\u00EE': return '\u00CE';
    case '\u00EF': return '\u00CF';
    case '\u00F0': return '\u00D0';
    case '\u00F1': return '\u00D1';
    case '\u00F2': return '\u00D2';
    case '\u00F3': return '\u00D3';
    case '\u00F4': return '\u00D4';
    case '\u00F5': return '\u00D5';
    case '\u00F6': return '\u00D6';
    case '\u00F8': return '\u00D8';
    case '\u00F9': return '\u00D9';
    case '\u00FA': return '\u00DA';
    case '\u00FB': return '\u00DB';
    case '\u00FC': return '\u00DC';
    case '\u00FD': return '\u00DD';
    case '\u00FE': return '\u00DE';
    case '\u00FF': return '\u0178';
    case '\u0101': return '\u0100';
    case '\u0103': return '\u0102';
    case '\u0105': return '\u0104';
    case '\u0107': return '\u0106';
    case '\u0109': return '\u0108';
    case '\u010B': return '\u010A';
    case '\u010D': return '\u010C';
    case '\u010F': return '\u010E';
    case '\u0111': return '\u0110';
    case '\u0113': return '\u0112';
    case '\u0115': return '\u0114';
    case '\u0117': return '\u0116';
    case '\u0119': return '\u0118';
    case '\u011B': return '\u011A';
    case '\u011D': return '\u011C';
    case '\u011F': return '\u011E';
    case '\u0121': return '\u0120';
    case '\u0123': return '\u0122';
    case '\u0125': return '\u0124';
    case '\u0127': return '\u0126';
    case '\u0129': return '\u0128';
    case '\u012B': return '\u012A';
    case '\u012D': return '\u012C';
    case '\u012F': return '\u012E';
    case '\u0131': return '\u0049';
    case '\u0133': return '\u0132';
    case '\u0135': return '\u0134';
    case '\u0137': return '\u0136';
    case '\u013A': return '\u0139';
    case '\u013C': return '\u013B';
    case '\u013E': return '\u013D';
    case '\u0140': return '\u013F';
    case '\u0142': return '\u0141';
    case '\u0144': return '\u0143';
    case '\u0146': return '\u0145';
    case '\u0148': return '\u0147';
    case '\u014B': return '\u014A';
    case '\u014D': return '\u014C';
    case '\u014F': return '\u014E';
    case '\u0151': return '\u0150';
    case '\u0153': return '\u0152';
    case '\u0155': return '\u0154';
    case '\u0157': return '\u0156';
    case '\u0159': return '\u0158';
    case '\u015B': return '\u015A';
    case '\u015D': return '\u015C';
    case '\u015F': return '\u015E';
    case '\u0161': return '\u0160';
    case '\u0163': return '\u0162';
    case '\u0165': return '\u0164';
    case '\u0167': return '\u0166';
    case '\u0169': return '\u0168';
    case '\u016B': return '\u016A';
    case '\u016D': return '\u016C';
    case '\u016F': return '\u016E';
    case '\u0171': return '\u0170';
    case '\u0173': return '\u0172';
    case '\u0175': return '\u0174';
    case '\u0177': return '\u0176';
    case '\u017A': return '\u0179';
    case '\u017C': return '\u017B';
    case '\u017E': return '\u017D';
    case '\u017F': return '\u0053';
    case '\u0183': return '\u0182';
    case '\u0185': return '\u0184';
    case '\u0188': return '\u0187';
    case '\u018C': return '\u018B';
    case '\u0192': return '\u0191';
    case '\u0199': return '\u0198';
    case '\u01A1': return '\u01A0';
    case '\u01A3': return '\u01A2';
    case '\u01A5': return '\u01A4';
    case '\u01A8': return '\u01A7';
    case '\u01AD': return '\u01AC';
    case '\u01B0': return '\u01AF';
    case '\u01B4': return '\u01B3';
    case '\u01B6': return '\u01B5';
    case '\u01B9': return '\u01B8';
    case '\u01BD': return '\u01BC';
    case '\u01C5': return '\u01C4';
    case '\u01C6': return '\u01C4';
    case '\u01C8': return '\u01C7';
    case '\u01C9': return '\u01C7';
    case '\u01CB': return '\u01CA';
    case '\u01CC': return '\u01CA';
    case '\u01CE': return '\u01CD';
    case '\u01D0': return '\u01CF';
    case '\u01D2': return '\u01D1';
    case '\u01D4': return '\u01D3';
    case '\u01D6': return '\u01D5';
    case '\u01D8': return '\u01D7';
    case '\u01DA': return '\u01D9';
    case '\u01DC': return '\u01DB';
    case '\u01DD': return '\u018E';
    case '\u01DF': return '\u01DE';
    case '\u01E1': return '\u01E0';
    case '\u01E3': return '\u01E2';
    case '\u01E5': return '\u01E4';
    case '\u01E7': return '\u01E6';
    case '\u01E9': return '\u01E8';
    case '\u01EB': return '\u01EA';
    case '\u01ED': return '\u01EC';
    case '\u01EF': return '\u01EE';
    case '\u01F2': return '\u01F1';
    case '\u01F3': return '\u01F1';
    case '\u01F5': return '\u01F4';
    case '\u01FB': return '\u01FA';
    case '\u01FD': return '\u01FC';
    case '\u01FF': return '\u01FE';
    case '\u0201': return '\u0200';
    case '\u0203': return '\u0202';
    case '\u0205': return '\u0204';
    case '\u0207': return '\u0206';
    case '\u0209': return '\u0208';
    case '\u020B': return '\u020A';
    case '\u020D': return '\u020C';
    case '\u020F': return '\u020E';
    case '\u0211': return '\u0210';
    case '\u0213': return '\u0212';
    case '\u0215': return '\u0214';
    case '\u0217': return '\u0216';
    case '\u0253': return '\u0181';
    case '\u0254': return '\u0186';
    case '\u0256': return '\u0189';
    case '\u0257': return '\u018A';
    case '\u0259': return '\u018F';
    case '\u025B': return '\u0190';
    case '\u0260': return '\u0193';
    case '\u0263': return '\u0194';
    case '\u0268': return '\u0197';
    case '\u0269': return '\u0196';
    case '\u026F': return '\u019C';
    case '\u0272': return '\u019D';
    case '\u0283': return '\u01A9';
    case '\u0288': return '\u01AE';
    case '\u028A': return '\u01B1';
    case '\u028B': return '\u01B2';
    case '\u0292': return '\u01B7';
    case '\u03AC': return '\u0386';
    case '\u03AD': return '\u0388';
    case '\u03AE': return '\u0389';
    case '\u03AF': return '\u038A';
    case '\u03B1': return '\u0391';
    case '\u03B2': return '\u0392';
    case '\u03B3': return '\u0393';
    case '\u03B4': return '\u0394';
    case '\u03B5': return '\u0395';
    case '\u03B6': return '\u0396';
    case '\u03B7': return '\u0397';
    case '\u03B8': return '\u0398';
    case '\u03B9': return '\u0399';
    case '\u03BA': return '\u039A';
    case '\u03BB': return '\u039B';
    case '\u03BC': return '\u039C';
    case '\u03BD': return '\u039D';
    case '\u03BE': return '\u039E';
    case '\u03BF': return '\u039F';
    case '\u03C0': return '\u03A0';
    case '\u03C1': return '\u03A1';
    case '\u03C2': return '\u03A3';
    case '\u03C3': return '\u03A3';
    case '\u03C4': return '\u03A4';
    case '\u03C5': return '\u03A5';
    case '\u03C6': return '\u03A6';
    case '\u03C7': return '\u03A7';
    case '\u03C8': return '\u03A8';
    case '\u03C9': return '\u03A9';
    case '\u03CA': return '\u03AA';
    case '\u03CB': return '\u03AB';
    case '\u03CC': return '\u038C';
    case '\u03CD': return '\u038E';
    case '\u03CE': return '\u038F';
    case '\u03D0': return '\u0392';
    case '\u03D1': return '\u0398';
    case '\u03D5': return '\u03A6';
    case '\u03D6': return '\u03A0';
    case '\u03E3': return '\u03E2';
    case '\u03E5': return '\u03E4';
    case '\u03E7': return '\u03E6';
    case '\u03E9': return '\u03E8';
    case '\u03EB': return '\u03EA';
    case '\u03ED': return '\u03EC';
    case '\u03EF': return '\u03EE';
    case '\u03F0': return '\u039A';
    case '\u03F1': return '\u03A1';
    case '\u0430': return '\u0410';
    case '\u0431': return '\u0411';
    case '\u0432': return '\u0412';
    case '\u0433': return '\u0413';
    case '\u0434': return '\u0414';
    case '\u0435': return '\u0415';
    case '\u0436': return '\u0416';
    case '\u0437': return '\u0417';
    case '\u0438': return '\u0418';
    case '\u0439': return '\u0419';
    case '\u043A': return '\u041A';
    case '\u043B': return '\u041B';
    case '\u043C': return '\u041C';
    case '\u043D': return '\u041D';
    case '\u043E': return '\u041E';
    case '\u043F': return '\u041F';
    case '\u0440': return '\u0420';
    case '\u0441': return '\u0421';
    case '\u0442': return '\u0422';
    case '\u0443': return '\u0423';
    case '\u0444': return '\u0424';
    case '\u0445': return '\u0425';
    case '\u0446': return '\u0426';
    case '\u0447': return '\u0427';
    case '\u0448': return '\u0428';
    case '\u0449': return '\u0429';
    case '\u044A': return '\u042A';
    case '\u044B': return '\u042B';
    case '\u044C': return '\u042C';
    case '\u044D': return '\u042D';
    case '\u044E': return '\u042E';
    case '\u044F': return '\u042F';
    case '\u0451': return '\u0401';
    case '\u0452': return '\u0402';
    case '\u0453': return '\u0403';
    case '\u0454': return '\u0404';
    case '\u0455': return '\u0405';
    case '\u0456': return '\u0406';
    case '\u0457': return '\u0407';
    case '\u0458': return '\u0408';
    case '\u0459': return '\u0409';
    case '\u045A': return '\u040A';
    case '\u045B': return '\u040B';
    case '\u045C': return '\u040C';
    case '\u045E': return '\u040E';
    case '\u045F': return '\u040F';
    case '\u0461': return '\u0460';
    case '\u0463': return '\u0462';
    case '\u0465': return '\u0464';
    case '\u0467': return '\u0466';
    case '\u0469': return '\u0468';
    case '\u046B': return '\u046A';
    case '\u046D': return '\u046C';
    case '\u046F': return '\u046E';
    case '\u0471': return '\u0470';
    case '\u0473': return '\u0472';
    case '\u0475': return '\u0474';
    case '\u0477': return '\u0476';
    case '\u0479': return '\u0478';
    case '\u047B': return '\u047A';
    case '\u047D': return '\u047C';
    case '\u047F': return '\u047E';
    case '\u0481': return '\u0480';
    case '\u0491': return '\u0490';
    case '\u0493': return '\u0492';
    case '\u0495': return '\u0494';
    case '\u0497': return '\u0496';
    case '\u0499': return '\u0498';
    case '\u049B': return '\u049A';
    case '\u049D': return '\u049C';
    case '\u049F': return '\u049E';
    case '\u04A1': return '\u04A0';
    case '\u04A3': return '\u04A2';
    case '\u04A5': return '\u04A4';
    case '\u04A7': return '\u04A6';
    case '\u04A9': return '\u04A8';
    case '\u04AB': return '\u04AA';
    case '\u04AD': return '\u04AC';
    case '\u04AF': return '\u04AE';
    case '\u04B1': return '\u04B0';
    case '\u04B3': return '\u04B2';
    case '\u04B5': return '\u04B4';
    case '\u04B7': return '\u04B6';
    case '\u04B9': return '\u04B8';
    case '\u04BB': return '\u04BA';
    case '\u04BD': return '\u04BC';
    case '\u04BF': return '\u04BE';
    case '\u04C2': return '\u04C1';
    case '\u04C4': return '\u04C3';
    case '\u04C8': return '\u04C7';
    case '\u04CC': return '\u04CB';
    case '\u04D1': return '\u04D0';
    case '\u04D3': return '\u04D2';
    case '\u04D5': return '\u04D4';
    case '\u04D7': return '\u04D6';
    case '\u04D9': return '\u04D8';
    case '\u04DB': return '\u04DA';
    case '\u04DD': return '\u04DC';
    case '\u04DF': return '\u04DE';
    case '\u04E1': return '\u04E0';
    case '\u04E3': return '\u04E2';
    case '\u04E5': return '\u04E4';
    case '\u04E7': return '\u04E6';
    case '\u04E9': return '\u04E8';
    case '\u04EB': return '\u04EA';
    case '\u04EF': return '\u04EE';
    case '\u04F1': return '\u04F0';
    case '\u04F3': return '\u04F2';
    case '\u04F5': return '\u04F4';
    case '\u04F9': return '\u04F8';
    case '\u0561': return '\u0531';
    case '\u0562': return '\u0532';
    case '\u0563': return '\u0533';
    case '\u0564': return '\u0534';
    case '\u0565': return '\u0535';
    case '\u0566': return '\u0536';
    case '\u0567': return '\u0537';
    case '\u0568': return '\u0538';
    case '\u0569': return '\u0539';
    case '\u056A': return '\u053A';
    case '\u056B': return '\u053B';
    case '\u056C': return '\u053C';
    case '\u056D': return '\u053D';
    case '\u056E': return '\u053E';
    case '\u056F': return '\u053F';
    case '\u0570': return '\u0540';
    case '\u0571': return '\u0541';
    case '\u0572': return '\u0542';
    case '\u0573': return '\u0543';
    case '\u0574': return '\u0544';
    case '\u0575': return '\u0545';
    case '\u0576': return '\u0546';
    case '\u0577': return '\u0547';
    case '\u0578': return '\u0548';
    case '\u0579': return '\u0549';
    case '\u057A': return '\u054A';
    case '\u057B': return '\u054B';
    case '\u057C': return '\u054C';
    case '\u057D': return '\u054D';
    case '\u057E': return '\u054E';
    case '\u057F': return '\u054F';
    case '\u0580': return '\u0550';
    case '\u0581': return '\u0551';
    case '\u0582': return '\u0552';
    case '\u0583': return '\u0553';
    case '\u0584': return '\u0554';
    case '\u0585': return '\u0555';
    case '\u0586': return '\u0556';
    case '\u1E01': return '\u1E00';
    case '\u1E03': return '\u1E02';
    case '\u1E05': return '\u1E04';
    case '\u1E07': return '\u1E06';
    case '\u1E09': return '\u1E08';
    case '\u1E0B': return '\u1E0A';
    case '\u1E0D': return '\u1E0C';
    case '\u1E0F': return '\u1E0E';
    case '\u1E11': return '\u1E10';
    case '\u1E13': return '\u1E12';
    case '\u1E15': return '\u1E14';
    case '\u1E17': return '\u1E16';
    case '\u1E19': return '\u1E18';
    case '\u1E1B': return '\u1E1A';
    case '\u1E1D': return '\u1E1C';
    case '\u1E1F': return '\u1E1E';
    case '\u1E21': return '\u1E20';
    case '\u1E23': return '\u1E22';
    case '\u1E25': return '\u1E24';
    case '\u1E27': return '\u1E26';
    case '\u1E29': return '\u1E28';
    case '\u1E2B': return '\u1E2A';
    case '\u1E2D': return '\u1E2C';
    case '\u1E2F': return '\u1E2E';
    case '\u1E31': return '\u1E30';
    case '\u1E33': return '\u1E32';
    case '\u1E35': return '\u1E34';
    case '\u1E37': return '\u1E36';
    case '\u1E39': return '\u1E38';
    case '\u1E3B': return '\u1E3A';
    case '\u1E3D': return '\u1E3C';
    case '\u1E3F': return '\u1E3E';
    case '\u1E41': return '\u1E40';
    case '\u1E43': return '\u1E42';
    case '\u1E45': return '\u1E44';
    case '\u1E47': return '\u1E46';
    case '\u1E49': return '\u1E48';
    case '\u1E4B': return '\u1E4A';
    case '\u1E4D': return '\u1E4C';
    case '\u1E4F': return '\u1E4E';
    case '\u1E51': return '\u1E50';
    case '\u1E53': return '\u1E52';
    case '\u1E55': return '\u1E54';
    case '\u1E57': return '\u1E56';
    case '\u1E59': return '\u1E58';
    case '\u1E5B': return '\u1E5A';
    case '\u1E5D': return '\u1E5C';
    case '\u1E5F': return '\u1E5E';
    case '\u1E61': return '\u1E60';
    case '\u1E63': return '\u1E62';
    case '\u1E65': return '\u1E64';
    case '\u1E67': return '\u1E66';
    case '\u1E69': return '\u1E68';
    case '\u1E6B': return '\u1E6A';
    case '\u1E6D': return '\u1E6C';
    case '\u1E6F': return '\u1E6E';
    case '\u1E71': return '\u1E70';
    case '\u1E73': return '\u1E72';
    case '\u1E75': return '\u1E74';
    case '\u1E77': return '\u1E76';
    case '\u1E79': return '\u1E78';
    case '\u1E7B': return '\u1E7A';
    case '\u1E7D': return '\u1E7C';
    case '\u1E7F': return '\u1E7E';
    case '\u1E81': return '\u1E80';
    case '\u1E83': return '\u1E82';
    case '\u1E85': return '\u1E84';
    case '\u1E87': return '\u1E86';
    case '\u1E89': return '\u1E88';
    case '\u1E8B': return '\u1E8A';
    case '\u1E8D': return '\u1E8C';
    case '\u1E8F': return '\u1E8E';
    case '\u1E91': return '\u1E90';
    case '\u1E93': return '\u1E92';
    case '\u1E95': return '\u1E94';
    case '\u1E9B': return '\u1E60';
    case '\u1EA1': return '\u1EA0';
    case '\u1EA3': return '\u1EA2';
    case '\u1EA5': return '\u1EA4';
    case '\u1EA7': return '\u1EA6';
    case '\u1EA9': return '\u1EA8';
    case '\u1EAB': return '\u1EAA';
    case '\u1EAD': return '\u1EAC';
    case '\u1EAF': return '\u1EAE';
    case '\u1EB1': return '\u1EB0';
    case '\u1EB3': return '\u1EB2';
    case '\u1EB5': return '\u1EB4';
    case '\u1EB7': return '\u1EB6';
    case '\u1EB9': return '\u1EB8';
    case '\u1EBB': return '\u1EBA';
    case '\u1EBD': return '\u1EBC';
    case '\u1EBF': return '\u1EBE';
    case '\u1EC1': return '\u1EC0';
    case '\u1EC3': return '\u1EC2';
    case '\u1EC5': return '\u1EC4';
    case '\u1EC7': return '\u1EC6';
    case '\u1EC9': return '\u1EC8';
    case '\u1ECB': return '\u1ECA';
    case '\u1ECD': return '\u1ECC';
    case '\u1ECF': return '\u1ECE';
    case '\u1ED1': return '\u1ED0';
    case '\u1ED3': return '\u1ED2';
    case '\u1ED5': return '\u1ED4';
    case '\u1ED7': return '\u1ED6';
    case '\u1ED9': return '\u1ED8';
    case '\u1EDB': return '\u1EDA';
    case '\u1EDD': return '\u1EDC';
    case '\u1EDF': return '\u1EDE';
    case '\u1EE1': return '\u1EE0';
    case '\u1EE3': return '\u1EE2';
    case '\u1EE5': return '\u1EE4';
    case '\u1EE7': return '\u1EE6';
    case '\u1EE9': return '\u1EE8';
    case '\u1EEB': return '\u1EEA';
    case '\u1EED': return '\u1EEC';
    case '\u1EEF': return '\u1EEE';
    case '\u1EF1': return '\u1EF0';
    case '\u1EF3': return '\u1EF2';
    case '\u1EF5': return '\u1EF4';
    case '\u1EF7': return '\u1EF6';
    case '\u1EF9': return '\u1EF8';
    case '\u1F00': return '\u1F08';
    case '\u1F01': return '\u1F09';
    case '\u1F02': return '\u1F0A';
    case '\u1F03': return '\u1F0B';
    case '\u1F04': return '\u1F0C';
    case '\u1F05': return '\u1F0D';
    case '\u1F06': return '\u1F0E';
    case '\u1F07': return '\u1F0F';
    case '\u1F10': return '\u1F18';
    case '\u1F11': return '\u1F19';
    case '\u1F12': return '\u1F1A';
    case '\u1F13': return '\u1F1B';
    case '\u1F14': return '\u1F1C';
    case '\u1F15': return '\u1F1D';
    case '\u1F20': return '\u1F28';
    case '\u1F21': return '\u1F29';
    case '\u1F22': return '\u1F2A';
    case '\u1F23': return '\u1F2B';
    case '\u1F24': return '\u1F2C';
    case '\u1F25': return '\u1F2D';
    case '\u1F26': return '\u1F2E';
    case '\u1F27': return '\u1F2F';
    case '\u1F30': return '\u1F38';
    case '\u1F31': return '\u1F39';
    case '\u1F32': return '\u1F3A';
    case '\u1F33': return '\u1F3B';
    case '\u1F34': return '\u1F3C';
    case '\u1F35': return '\u1F3D';
    case '\u1F36': return '\u1F3E';
    case '\u1F37': return '\u1F3F';
    case '\u1F40': return '\u1F48';
    case '\u1F41': return '\u1F49';
    case '\u1F42': return '\u1F4A';
    case '\u1F43': return '\u1F4B';
    case '\u1F44': return '\u1F4C';
    case '\u1F45': return '\u1F4D';
    case '\u1F51': return '\u1F59';
    case '\u1F53': return '\u1F5B';
    case '\u1F55': return '\u1F5D';
    case '\u1F57': return '\u1F5F';
    case '\u1F60': return '\u1F68';
    case '\u1F61': return '\u1F69';
    case '\u1F62': return '\u1F6A';
    case '\u1F63': return '\u1F6B';
    case '\u1F64': return '\u1F6C';
    case '\u1F65': return '\u1F6D';
    case '\u1F66': return '\u1F6E';
    case '\u1F67': return '\u1F6F';
    case '\u1F70': return '\u1FBA';
    case '\u1F71': return '\u1FBB';
    case '\u1F72': return '\u1FC8';
    case '\u1F73': return '\u1FC9';
    case '\u1F74': return '\u1FCA';
    case '\u1F75': return '\u1FCB';
    case '\u1F76': return '\u1FDA';
    case '\u1F77': return '\u1FDB';
    case '\u1F78': return '\u1FF8';
    case '\u1F79': return '\u1FF9';
    case '\u1F7A': return '\u1FEA';
    case '\u1F7B': return '\u1FEB';
    case '\u1F7C': return '\u1FFA';
    case '\u1F7D': return '\u1FFB';
    case '\u1F80': return '\u1F88';
    case '\u1F81': return '\u1F89';
    case '\u1F82': return '\u1F8A';
    case '\u1F83': return '\u1F8B';
    case '\u1F84': return '\u1F8C';
    case '\u1F85': return '\u1F8D';
    case '\u1F86': return '\u1F8E';
    case '\u1F87': return '\u1F8F';
    case '\u1F90': return '\u1F98';
    case '\u1F91': return '\u1F99';
    case '\u1F92': return '\u1F9A';
    case '\u1F93': return '\u1F9B';
    case '\u1F94': return '\u1F9C';
    case '\u1F95': return '\u1F9D';
    case '\u1F96': return '\u1F9E';
    case '\u1F97': return '\u1F9F';
    case '\u1FA0': return '\u1FA8';
    case '\u1FA1': return '\u1FA9';
    case '\u1FA2': return '\u1FAA';
    case '\u1FA3': return '\u1FAB';
    case '\u1FA4': return '\u1FAC';
    case '\u1FA5': return '\u1FAD';
    case '\u1FA6': return '\u1FAE';
    case '\u1FA7': return '\u1FAF';
    case '\u1FB0': return '\u1FB8';
    case '\u1FB1': return '\u1FB9';
    case '\u1FB3': return '\u1FBC';
    case '\u1FC3': return '\u1FCC';
    case '\u1FD0': return '\u1FD8';
    case '\u1FD1': return '\u1FD9';
    case '\u1FE0': return '\u1FE8';
    case '\u1FE1': return '\u1FE9';
    case '\u1FE5': return '\u1FEC';
    case '\u1FF3': return '\u1FFC';
    case '\u2170': return '\u2160';
    case '\u2171': return '\u2161';
    case '\u2172': return '\u2162';
    case '\u2173': return '\u2163';
    case '\u2174': return '\u2164';
    case '\u2175': return '\u2165';
    case '\u2176': return '\u2166';
    case '\u2177': return '\u2167';
    case '\u2178': return '\u2168';
    case '\u2179': return '\u2169';
    case '\u217A': return '\u216A';
    case '\u217B': return '\u216B';
    case '\u217C': return '\u216C';
    case '\u217D': return '\u216D';
    case '\u217E': return '\u216E';
    case '\u217F': return '\u216F';
    case '\u24D0': return '\u24B6';
    case '\u24D1': return '\u24B7';
    case '\u24D2': return '\u24B8';
    case '\u24D3': return '\u24B9';
    case '\u24D4': return '\u24BA';
    case '\u24D5': return '\u24BB';
    case '\u24D6': return '\u24BC';
    case '\u24D7': return '\u24BD';
    case '\u24D8': return '\u24BE';
    case '\u24D9': return '\u24BF';
    case '\u24DA': return '\u24C0';
    case '\u24DB': return '\u24C1';
    case '\u24DC': return '\u24C2';
    case '\u24DD': return '\u24C3';
    case '\u24DE': return '\u24C4';
    case '\u24DF': return '\u24C5';
    case '\u24E0': return '\u24C6';
    case '\u24E1': return '\u24C7';
    case '\u24E2': return '\u24C8';
    case '\u24E3': return '\u24C9';
    case '\u24E4': return '\u24CA';
    case '\u24E5': return '\u24CB';
    case '\u24E6': return '\u24CC';
    case '\u24E7': return '\u24CD';
    case '\u24E8': return '\u24CE';
    case '\u24E9': return '\u24CF';
    case '\uFF41': return '\uFF21';
    case '\uFF42': return '\uFF22';
    case '\uFF43': return '\uFF23';
    case '\uFF44': return '\uFF24';
    case '\uFF45': return '\uFF25';
    case '\uFF46': return '\uFF26';
    case '\uFF47': return '\uFF27';
    case '\uFF48': return '\uFF28';
    case '\uFF49': return '\uFF29';
    case '\uFF4A': return '\uFF2A';
    case '\uFF4B': return '\uFF2B';
    case '\uFF4C': return '\uFF2C';
    case '\uFF4D': return '\uFF2D';
    case '\uFF4E': return '\uFF2E';
    case '\uFF4F': return '\uFF2F';
    case '\uFF50': return '\uFF30';
    case '\uFF51': return '\uFF31';
    case '\uFF52': return '\uFF32';
    case '\uFF53': return '\uFF33';
    case '\uFF54': return '\uFF34';
    case '\uFF55': return '\uFF35';
    case '\uFF56': return '\uFF36';
    case '\uFF57': return '\uFF37';
    case '\uFF58': return '\uFF38';
    case '\uFF59': return '\uFF39';
    case '\uFF5A': return '\uFF3A';
    default: return shar;
    }
  }

  public static char toTitleCase(char shar) {
    switch (shar) {
    case '\u0061': return '\u0041';
    case '\u0062': return '\u0042';
    case '\u0063': return '\u0043';
    case '\u0064': return '\u0044';
    case '\u0065': return '\u0045';
    case '\u0066': return '\u0046';
    case '\u0067': return '\u0047';
    case '\u0068': return '\u0048';
    case '\u0069': return '\u0049';
    case '\u006A': return '\u004A';
    case '\u006B': return '\u004B';
    case '\u006C': return '\u004C';
    case '\u006D': return '\u004D';
    case '\u006E': return '\u004E';
    case '\u006F': return '\u004F';
    case '\u0070': return '\u0050';
    case '\u0071': return '\u0051';
    case '\u0072': return '\u0052';
    case '\u0073': return '\u0053';
    case '\u0074': return '\u0054';
    case '\u0075': return '\u0055';
    case '\u0076': return '\u0056';
    case '\u0077': return '\u0057';
    case '\u0078': return '\u0058';
    case '\u0079': return '\u0059';
    case '\u007A': return '\u005A';
    case '\u00E0': return '\u00C0';
    case '\u00E1': return '\u00C1';
    case '\u00E2': return '\u00C2';
    case '\u00E3': return '\u00C3';
    case '\u00E4': return '\u00C4';
    case '\u00E5': return '\u00C5';
    case '\u00E6': return '\u00C6';
    case '\u00E7': return '\u00C7';
    case '\u00E8': return '\u00C8';
    case '\u00E9': return '\u00C9';
    case '\u00EA': return '\u00CA';
    case '\u00EB': return '\u00CB';
    case '\u00EC': return '\u00CC';
    case '\u00ED': return '\u00CD';
    case '\u00EE': return '\u00CE';
    case '\u00EF': return '\u00CF';
    case '\u00F0': return '\u00D0';
    case '\u00F1': return '\u00D1';
    case '\u00F2': return '\u00D2';
    case '\u00F3': return '\u00D3';
    case '\u00F4': return '\u00D4';
    case '\u00F5': return '\u00D5';
    case '\u00F6': return '\u00D6';
    case '\u00F8': return '\u00D8';
    case '\u00F9': return '\u00D9';
    case '\u00FA': return '\u00DA';
    case '\u00FB': return '\u00DB';
    case '\u00FC': return '\u00DC';
    case '\u00FD': return '\u00DD';
    case '\u00FE': return '\u00DE';
    case '\u00FF': return '\u0178';
    case '\u0101': return '\u0100';
    case '\u0103': return '\u0102';
    case '\u0105': return '\u0104';
    case '\u0107': return '\u0106';
    case '\u0109': return '\u0108';
    case '\u010B': return '\u010A';
    case '\u010D': return '\u010C';
    case '\u010F': return '\u010E';
    case '\u0111': return '\u0110';
    case '\u0113': return '\u0112';
    case '\u0115': return '\u0114';
    case '\u0117': return '\u0116';
    case '\u0119': return '\u0118';
    case '\u011B': return '\u011A';
    case '\u011D': return '\u011C';
    case '\u011F': return '\u011E';
    case '\u0121': return '\u0120';
    case '\u0123': return '\u0122';
    case '\u0125': return '\u0124';
    case '\u0127': return '\u0126';
    case '\u0129': return '\u0128';
    case '\u012B': return '\u012A';
    case '\u012D': return '\u012C';
    case '\u012F': return '\u012E';
    case '\u0131': return '\u0049';
    case '\u0133': return '\u0132';
    case '\u0135': return '\u0134';
    case '\u0137': return '\u0136';
    case '\u013A': return '\u0139';
    case '\u013C': return '\u013B';
    case '\u013E': return '\u013D';
    case '\u0140': return '\u013F';
    case '\u0142': return '\u0141';
    case '\u0144': return '\u0143';
    case '\u0146': return '\u0145';
    case '\u0148': return '\u0147';
    case '\u014B': return '\u014A';
    case '\u014D': return '\u014C';
    case '\u014F': return '\u014E';
    case '\u0151': return '\u0150';
    case '\u0153': return '\u0152';
    case '\u0155': return '\u0154';
    case '\u0157': return '\u0156';
    case '\u0159': return '\u0158';
    case '\u015B': return '\u015A';
    case '\u015D': return '\u015C';
    case '\u015F': return '\u015E';
    case '\u0161': return '\u0160';
    case '\u0163': return '\u0162';
    case '\u0165': return '\u0164';
    case '\u0167': return '\u0166';
    case '\u0169': return '\u0168';
    case '\u016B': return '\u016A';
    case '\u016D': return '\u016C';
    case '\u016F': return '\u016E';
    case '\u0171': return '\u0170';
    case '\u0173': return '\u0172';
    case '\u0175': return '\u0174';
    case '\u0177': return '\u0176';
    case '\u017A': return '\u0179';
    case '\u017C': return '\u017B';
    case '\u017E': return '\u017D';
    case '\u017F': return '\u0053';
    case '\u0183': return '\u0182';
    case '\u0185': return '\u0184';
    case '\u0188': return '\u0187';
    case '\u018C': return '\u018B';
    case '\u0192': return '\u0191';
    case '\u0199': return '\u0198';
    case '\u01A1': return '\u01A0';
    case '\u01A3': return '\u01A2';
    case '\u01A5': return '\u01A4';
    case '\u01A8': return '\u01A7';
    case '\u01AD': return '\u01AC';
    case '\u01B0': return '\u01AF';
    case '\u01B4': return '\u01B3';
    case '\u01B6': return '\u01B5';
    case '\u01B9': return '\u01B8';
    case '\u01BD': return '\u01BC';
    case '\u01C4': return '\u01C5';
    case '\u01C6': return '\u01C5';
    case '\u01C7': return '\u01C8';
    case '\u01C9': return '\u01C8';
    case '\u01CA': return '\u01CB';
    case '\u01CC': return '\u01CB';
    case '\u01CE': return '\u01CD';
    case '\u01D0': return '\u01CF';
    case '\u01D2': return '\u01D1';
    case '\u01D4': return '\u01D3';
    case '\u01D6': return '\u01D5';
    case '\u01D8': return '\u01D7';
    case '\u01DA': return '\u01D9';
    case '\u01DC': return '\u01DB';
    case '\u01DD': return '\u018E';
    case '\u01DF': return '\u01DE';
    case '\u01E1': return '\u01E0';
    case '\u01E3': return '\u01E2';
    case '\u01E5': return '\u01E4';
    case '\u01E7': return '\u01E6';
    case '\u01E9': return '\u01E8';
    case '\u01EB': return '\u01EA';
    case '\u01ED': return '\u01EC';
    case '\u01EF': return '\u01EE';
    case '\u01F1': return '\u01F2';
    case '\u01F3': return '\u01F2';
    case '\u01F5': return '\u01F4';
    case '\u01FB': return '\u01FA';
    case '\u01FD': return '\u01FC';
    case '\u01FF': return '\u01FE';
    case '\u0201': return '\u0200';
    case '\u0203': return '\u0202';
    case '\u0205': return '\u0204';
    case '\u0207': return '\u0206';
    case '\u0209': return '\u0208';
    case '\u020B': return '\u020A';
    case '\u020D': return '\u020C';
    case '\u020F': return '\u020E';
    case '\u0211': return '\u0210';
    case '\u0213': return '\u0212';
    case '\u0215': return '\u0214';
    case '\u0217': return '\u0216';
    case '\u0253': return '\u0181';
    case '\u0254': return '\u0186';
    case '\u0256': return '\u0189';
    case '\u0257': return '\u018A';
    case '\u0259': return '\u018F';
    case '\u025B': return '\u0190';
    case '\u0260': return '\u0193';
    case '\u0263': return '\u0194';
    case '\u0268': return '\u0197';
    case '\u0269': return '\u0196';
    case '\u026F': return '\u019C';
    case '\u0272': return '\u019D';
    case '\u0283': return '\u01A9';
    case '\u0288': return '\u01AE';
    case '\u028A': return '\u01B1';
    case '\u028B': return '\u01B2';
    case '\u0292': return '\u01B7';
    case '\u03AC': return '\u0386';
    case '\u03AD': return '\u0388';
    case '\u03AE': return '\u0389';
    case '\u03AF': return '\u038A';
    case '\u03B1': return '\u0391';
    case '\u03B2': return '\u0392';
    case '\u03B3': return '\u0393';
    case '\u03B4': return '\u0394';
    case '\u03B5': return '\u0395';
    case '\u03B6': return '\u0396';
    case '\u03B7': return '\u0397';
    case '\u03B8': return '\u0398';
    case '\u03B9': return '\u0399';
    case '\u03BA': return '\u039A';
    case '\u03BB': return '\u039B';
    case '\u03BC': return '\u039C';
    case '\u03BD': return '\u039D';
    case '\u03BE': return '\u039E';
    case '\u03BF': return '\u039F';
    case '\u03C0': return '\u03A0';
    case '\u03C1': return '\u03A1';
    case '\u03C2': return '\u03A3';
    case '\u03C3': return '\u03A3';
    case '\u03C4': return '\u03A4';
    case '\u03C5': return '\u03A5';
    case '\u03C6': return '\u03A6';
    case '\u03C7': return '\u03A7';
    case '\u03C8': return '\u03A8';
    case '\u03C9': return '\u03A9';
    case '\u03CA': return '\u03AA';
    case '\u03CB': return '\u03AB';
    case '\u03CC': return '\u038C';
    case '\u03CD': return '\u038E';
    case '\u03CE': return '\u038F';
    case '\u03D0': return '\u0392';
    case '\u03D1': return '\u0398';
    case '\u03D5': return '\u03A6';
    case '\u03D6': return '\u03A0';
    case '\u03E3': return '\u03E2';
    case '\u03E5': return '\u03E4';
    case '\u03E7': return '\u03E6';
    case '\u03E9': return '\u03E8';
    case '\u03EB': return '\u03EA';
    case '\u03ED': return '\u03EC';
    case '\u03EF': return '\u03EE';
    case '\u03F0': return '\u039A';
    case '\u03F1': return '\u03A1';
    case '\u0430': return '\u0410';
    case '\u0431': return '\u0411';
    case '\u0432': return '\u0412';
    case '\u0433': return '\u0413';
    case '\u0434': return '\u0414';
    case '\u0435': return '\u0415';
    case '\u0436': return '\u0416';
    case '\u0437': return '\u0417';
    case '\u0438': return '\u0418';
    case '\u0439': return '\u0419';
    case '\u043A': return '\u041A';
    case '\u043B': return '\u041B';
    case '\u043C': return '\u041C';
    case '\u043D': return '\u041D';
    case '\u043E': return '\u041E';
    case '\u043F': return '\u041F';
    case '\u0440': return '\u0420';
    case '\u0441': return '\u0421';
    case '\u0442': return '\u0422';
    case '\u0443': return '\u0423';
    case '\u0444': return '\u0424';
    case '\u0445': return '\u0425';
    case '\u0446': return '\u0426';
    case '\u0447': return '\u0427';
    case '\u0448': return '\u0428';
    case '\u0449': return '\u0429';
    case '\u044A': return '\u042A';
    case '\u044B': return '\u042B';
    case '\u044C': return '\u042C';
    case '\u044D': return '\u042D';
    case '\u044E': return '\u042E';
    case '\u044F': return '\u042F';
    case '\u0451': return '\u0401';
    case '\u0452': return '\u0402';
    case '\u0453': return '\u0403';
    case '\u0454': return '\u0404';
    case '\u0455': return '\u0405';
    case '\u0456': return '\u0406';
    case '\u0457': return '\u0407';
    case '\u0458': return '\u0408';
    case '\u0459': return '\u0409';
    case '\u045A': return '\u040A';
    case '\u045B': return '\u040B';
    case '\u045C': return '\u040C';
    case '\u045E': return '\u040E';
    case '\u045F': return '\u040F';
    case '\u0461': return '\u0460';
    case '\u0463': return '\u0462';
    case '\u0465': return '\u0464';
    case '\u0467': return '\u0466';
    case '\u0469': return '\u0468';
    case '\u046B': return '\u046A';
    case '\u046D': return '\u046C';
    case '\u046F': return '\u046E';
    case '\u0471': return '\u0470';
    case '\u0473': return '\u0472';
    case '\u0475': return '\u0474';
    case '\u0477': return '\u0476';
    case '\u0479': return '\u0478';
    case '\u047B': return '\u047A';
    case '\u047D': return '\u047C';
    case '\u047F': return '\u047E';
    case '\u0481': return '\u0480';
    case '\u0491': return '\u0490';
    case '\u0493': return '\u0492';
    case '\u0495': return '\u0494';
    case '\u0497': return '\u0496';
    case '\u0499': return '\u0498';
    case '\u049B': return '\u049A';
    case '\u049D': return '\u049C';
    case '\u049F': return '\u049E';
    case '\u04A1': return '\u04A0';
    case '\u04A3': return '\u04A2';
    case '\u04A5': return '\u04A4';
    case '\u04A7': return '\u04A6';
    case '\u04A9': return '\u04A8';
    case '\u04AB': return '\u04AA';
    case '\u04AD': return '\u04AC';
    case '\u04AF': return '\u04AE';
    case '\u04B1': return '\u04B0';
    case '\u04B3': return '\u04B2';
    case '\u04B5': return '\u04B4';
    case '\u04B7': return '\u04B6';
    case '\u04B9': return '\u04B8';
    case '\u04BB': return '\u04BA';
    case '\u04BD': return '\u04BC';
    case '\u04BF': return '\u04BE';
    case '\u04C2': return '\u04C1';
    case '\u04C4': return '\u04C3';
    case '\u04C8': return '\u04C7';
    case '\u04CC': return '\u04CB';
    case '\u04D1': return '\u04D0';
    case '\u04D3': return '\u04D2';
    case '\u04D5': return '\u04D4';
    case '\u04D7': return '\u04D6';
    case '\u04D9': return '\u04D8';
    case '\u04DB': return '\u04DA';
    case '\u04DD': return '\u04DC';
    case '\u04DF': return '\u04DE';
    case '\u04E1': return '\u04E0';
    case '\u04E3': return '\u04E2';
    case '\u04E5': return '\u04E4';
    case '\u04E7': return '\u04E6';
    case '\u04E9': return '\u04E8';
    case '\u04EB': return '\u04EA';
    case '\u04EF': return '\u04EE';
    case '\u04F1': return '\u04F0';
    case '\u04F3': return '\u04F2';
    case '\u04F5': return '\u04F4';
    case '\u04F9': return '\u04F8';
    case '\u0561': return '\u0531';
    case '\u0562': return '\u0532';
    case '\u0563': return '\u0533';
    case '\u0564': return '\u0534';
    case '\u0565': return '\u0535';
    case '\u0566': return '\u0536';
    case '\u0567': return '\u0537';
    case '\u0568': return '\u0538';
    case '\u0569': return '\u0539';
    case '\u056A': return '\u053A';
    case '\u056B': return '\u053B';
    case '\u056C': return '\u053C';
    case '\u056D': return '\u053D';
    case '\u056E': return '\u053E';
    case '\u056F': return '\u053F';
    case '\u0570': return '\u0540';
    case '\u0571': return '\u0541';
    case '\u0572': return '\u0542';
    case '\u0573': return '\u0543';
    case '\u0574': return '\u0544';
    case '\u0575': return '\u0545';
    case '\u0576': return '\u0546';
    case '\u0577': return '\u0547';
    case '\u0578': return '\u0548';
    case '\u0579': return '\u0549';
    case '\u057A': return '\u054A';
    case '\u057B': return '\u054B';
    case '\u057C': return '\u054C';
    case '\u057D': return '\u054D';
    case '\u057E': return '\u054E';
    case '\u057F': return '\u054F';
    case '\u0580': return '\u0550';
    case '\u0581': return '\u0551';
    case '\u0582': return '\u0552';
    case '\u0583': return '\u0553';
    case '\u0584': return '\u0554';
    case '\u0585': return '\u0555';
    case '\u0586': return '\u0556';
    case '\u1E01': return '\u1E00';
    case '\u1E03': return '\u1E02';
    case '\u1E05': return '\u1E04';
    case '\u1E07': return '\u1E06';
    case '\u1E09': return '\u1E08';
    case '\u1E0B': return '\u1E0A';
    case '\u1E0D': return '\u1E0C';
    case '\u1E0F': return '\u1E0E';
    case '\u1E11': return '\u1E10';
    case '\u1E13': return '\u1E12';
    case '\u1E15': return '\u1E14';
    case '\u1E17': return '\u1E16';
    case '\u1E19': return '\u1E18';
    case '\u1E1B': return '\u1E1A';
    case '\u1E1D': return '\u1E1C';
    case '\u1E1F': return '\u1E1E';
    case '\u1E21': return '\u1E20';
    case '\u1E23': return '\u1E22';
    case '\u1E25': return '\u1E24';
    case '\u1E27': return '\u1E26';
    case '\u1E29': return '\u1E28';
    case '\u1E2B': return '\u1E2A';
    case '\u1E2D': return '\u1E2C';
    case '\u1E2F': return '\u1E2E';
    case '\u1E31': return '\u1E30';
    case '\u1E33': return '\u1E32';
    case '\u1E35': return '\u1E34';
    case '\u1E37': return '\u1E36';
    case '\u1E39': return '\u1E38';
    case '\u1E3B': return '\u1E3A';
    case '\u1E3D': return '\u1E3C';
    case '\u1E3F': return '\u1E3E';
    case '\u1E41': return '\u1E40';
    case '\u1E43': return '\u1E42';
    case '\u1E45': return '\u1E44';
    case '\u1E47': return '\u1E46';
    case '\u1E49': return '\u1E48';
    case '\u1E4B': return '\u1E4A';
    case '\u1E4D': return '\u1E4C';
    case '\u1E4F': return '\u1E4E';
    case '\u1E51': return '\u1E50';
    case '\u1E53': return '\u1E52';
    case '\u1E55': return '\u1E54';
    case '\u1E57': return '\u1E56';
    case '\u1E59': return '\u1E58';
    case '\u1E5B': return '\u1E5A';
    case '\u1E5D': return '\u1E5C';
    case '\u1E5F': return '\u1E5E';
    case '\u1E61': return '\u1E60';
    case '\u1E63': return '\u1E62';
    case '\u1E65': return '\u1E64';
    case '\u1E67': return '\u1E66';
    case '\u1E69': return '\u1E68';
    case '\u1E6B': return '\u1E6A';
    case '\u1E6D': return '\u1E6C';
    case '\u1E6F': return '\u1E6E';
    case '\u1E71': return '\u1E70';
    case '\u1E73': return '\u1E72';
    case '\u1E75': return '\u1E74';
    case '\u1E77': return '\u1E76';
    case '\u1E79': return '\u1E78';
    case '\u1E7B': return '\u1E7A';
    case '\u1E7D': return '\u1E7C';
    case '\u1E7F': return '\u1E7E';
    case '\u1E81': return '\u1E80';
    case '\u1E83': return '\u1E82';
    case '\u1E85': return '\u1E84';
    case '\u1E87': return '\u1E86';
    case '\u1E89': return '\u1E88';
    case '\u1E8B': return '\u1E8A';
    case '\u1E8D': return '\u1E8C';
    case '\u1E8F': return '\u1E8E';
    case '\u1E91': return '\u1E90';
    case '\u1E93': return '\u1E92';
    case '\u1E95': return '\u1E94';
    case '\u1E9B': return '\u1E60';
    case '\u1EA1': return '\u1EA0';
    case '\u1EA3': return '\u1EA2';
    case '\u1EA5': return '\u1EA4';
    case '\u1EA7': return '\u1EA6';
    case '\u1EA9': return '\u1EA8';
    case '\u1EAB': return '\u1EAA';
    case '\u1EAD': return '\u1EAC';
    case '\u1EAF': return '\u1EAE';
    case '\u1EB1': return '\u1EB0';
    case '\u1EB3': return '\u1EB2';
    case '\u1EB5': return '\u1EB4';
    case '\u1EB7': return '\u1EB6';
    case '\u1EB9': return '\u1EB8';
    case '\u1EBB': return '\u1EBA';
    case '\u1EBD': return '\u1EBC';
    case '\u1EBF': return '\u1EBE';
    case '\u1EC1': return '\u1EC0';
    case '\u1EC3': return '\u1EC2';
    case '\u1EC5': return '\u1EC4';
    case '\u1EC7': return '\u1EC6';
    case '\u1EC9': return '\u1EC8';
    case '\u1ECB': return '\u1ECA';
    case '\u1ECD': return '\u1ECC';
    case '\u1ECF': return '\u1ECE';
    case '\u1ED1': return '\u1ED0';
    case '\u1ED3': return '\u1ED2';
    case '\u1ED5': return '\u1ED4';
    case '\u1ED7': return '\u1ED6';
    case '\u1ED9': return '\u1ED8';
    case '\u1EDB': return '\u1EDA';
    case '\u1EDD': return '\u1EDC';
    case '\u1EDF': return '\u1EDE';
    case '\u1EE1': return '\u1EE0';
    case '\u1EE3': return '\u1EE2';
    case '\u1EE5': return '\u1EE4';
    case '\u1EE7': return '\u1EE6';
    case '\u1EE9': return '\u1EE8';
    case '\u1EEB': return '\u1EEA';
    case '\u1EED': return '\u1EEC';
    case '\u1EEF': return '\u1EEE';
    case '\u1EF1': return '\u1EF0';
    case '\u1EF3': return '\u1EF2';
    case '\u1EF5': return '\u1EF4';
    case '\u1EF7': return '\u1EF6';
    case '\u1EF9': return '\u1EF8';
    case '\u1F00': return '\u1F08';
    case '\u1F01': return '\u1F09';
    case '\u1F02': return '\u1F0A';
    case '\u1F03': return '\u1F0B';
    case '\u1F04': return '\u1F0C';
    case '\u1F05': return '\u1F0D';
    case '\u1F06': return '\u1F0E';
    case '\u1F07': return '\u1F0F';
    case '\u1F10': return '\u1F18';
    case '\u1F11': return '\u1F19';
    case '\u1F12': return '\u1F1A';
    case '\u1F13': return '\u1F1B';
    case '\u1F14': return '\u1F1C';
    case '\u1F15': return '\u1F1D';
    case '\u1F20': return '\u1F28';
    case '\u1F21': return '\u1F29';
    case '\u1F22': return '\u1F2A';
    case '\u1F23': return '\u1F2B';
    case '\u1F24': return '\u1F2C';
    case '\u1F25': return '\u1F2D';
    case '\u1F26': return '\u1F2E';
    case '\u1F27': return '\u1F2F';
    case '\u1F30': return '\u1F38';
    case '\u1F31': return '\u1F39';
    case '\u1F32': return '\u1F3A';
    case '\u1F33': return '\u1F3B';
    case '\u1F34': return '\u1F3C';
    case '\u1F35': return '\u1F3D';
    case '\u1F36': return '\u1F3E';
    case '\u1F37': return '\u1F3F';
    case '\u1F40': return '\u1F48';
    case '\u1F41': return '\u1F49';
    case '\u1F42': return '\u1F4A';
    case '\u1F43': return '\u1F4B';
    case '\u1F44': return '\u1F4C';
    case '\u1F45': return '\u1F4D';
    case '\u1F51': return '\u1F59';
    case '\u1F53': return '\u1F5B';
    case '\u1F55': return '\u1F5D';
    case '\u1F57': return '\u1F5F';
    case '\u1F60': return '\u1F68';
    case '\u1F61': return '\u1F69';
    case '\u1F62': return '\u1F6A';
    case '\u1F63': return '\u1F6B';
    case '\u1F64': return '\u1F6C';
    case '\u1F65': return '\u1F6D';
    case '\u1F66': return '\u1F6E';
    case '\u1F67': return '\u1F6F';
    case '\u1F70': return '\u1FBA';
    case '\u1F71': return '\u1FBB';
    case '\u1F72': return '\u1FC8';
    case '\u1F73': return '\u1FC9';
    case '\u1F74': return '\u1FCA';
    case '\u1F75': return '\u1FCB';
    case '\u1F76': return '\u1FDA';
    case '\u1F77': return '\u1FDB';
    case '\u1F78': return '\u1FF8';
    case '\u1F79': return '\u1FF9';
    case '\u1F7A': return '\u1FEA';
    case '\u1F7B': return '\u1FEB';
    case '\u1F7C': return '\u1FFA';
    case '\u1F7D': return '\u1FFB';
    case '\u1F80': return '\u1F88';
    case '\u1F81': return '\u1F89';
    case '\u1F82': return '\u1F8A';
    case '\u1F83': return '\u1F8B';
    case '\u1F84': return '\u1F8C';
    case '\u1F85': return '\u1F8D';
    case '\u1F86': return '\u1F8E';
    case '\u1F87': return '\u1F8F';
    case '\u1F90': return '\u1F98';
    case '\u1F91': return '\u1F99';
    case '\u1F92': return '\u1F9A';
    case '\u1F93': return '\u1F9B';
    case '\u1F94': return '\u1F9C';
    case '\u1F95': return '\u1F9D';
    case '\u1F96': return '\u1F9E';
    case '\u1F97': return '\u1F9F';
    case '\u1FA0': return '\u1FA8';
    case '\u1FA1': return '\u1FA9';
    case '\u1FA2': return '\u1FAA';
    case '\u1FA3': return '\u1FAB';
    case '\u1FA4': return '\u1FAC';
    case '\u1FA5': return '\u1FAD';
    case '\u1FA6': return '\u1FAE';
    case '\u1FA7': return '\u1FAF';
    case '\u1FB0': return '\u1FB8';
    case '\u1FB1': return '\u1FB9';
    case '\u1FB3': return '\u1FBC';
    case '\u1FC3': return '\u1FCC';
    case '\u1FD0': return '\u1FD8';
    case '\u1FD1': return '\u1FD9';
    case '\u1FE0': return '\u1FE8';
    case '\u1FE1': return '\u1FE9';
    case '\u1FE5': return '\u1FEC';
    case '\u1FF3': return '\u1FFC';
    case '\u2170': return '\u2160';
    case '\u2171': return '\u2161';
    case '\u2172': return '\u2162';
    case '\u2173': return '\u2163';
    case '\u2174': return '\u2164';
    case '\u2175': return '\u2165';
    case '\u2176': return '\u2166';
    case '\u2177': return '\u2167';
    case '\u2178': return '\u2168';
    case '\u2179': return '\u2169';
    case '\u217A': return '\u216A';
    case '\u217B': return '\u216B';
    case '\u217C': return '\u216C';
    case '\u217D': return '\u216D';
    case '\u217E': return '\u216E';
    case '\u217F': return '\u216F';
    case '\u24D0': return '\u24B6';
    case '\u24D1': return '\u24B7';
    case '\u24D2': return '\u24B8';
    case '\u24D3': return '\u24B9';
    case '\u24D4': return '\u24BA';
    case '\u24D5': return '\u24BB';
    case '\u24D6': return '\u24BC';
    case '\u24D7': return '\u24BD';
    case '\u24D8': return '\u24BE';
    case '\u24D9': return '\u24BF';
    case '\u24DA': return '\u24C0';
    case '\u24DB': return '\u24C1';
    case '\u24DC': return '\u24C2';
    case '\u24DD': return '\u24C3';
    case '\u24DE': return '\u24C4';
    case '\u24DF': return '\u24C5';
    case '\u24E0': return '\u24C6';
    case '\u24E1': return '\u24C7';
    case '\u24E2': return '\u24C8';
    case '\u24E3': return '\u24C9';
    case '\u24E4': return '\u24CA';
    case '\u24E5': return '\u24CB';
    case '\u24E6': return '\u24CC';
    case '\u24E7': return '\u24CD';
    case '\u24E8': return '\u24CE';
    case '\u24E9': return '\u24CF';
    case '\uFF41': return '\uFF21';
    case '\uFF42': return '\uFF22';
    case '\uFF43': return '\uFF23';
    case '\uFF44': return '\uFF24';
    case '\uFF45': return '\uFF25';
    case '\uFF46': return '\uFF26';
    case '\uFF47': return '\uFF27';
    case '\uFF48': return '\uFF28';
    case '\uFF49': return '\uFF29';
    case '\uFF4A': return '\uFF2A';
    case '\uFF4B': return '\uFF2B';
    case '\uFF4C': return '\uFF2C';
    case '\uFF4D': return '\uFF2D';
    case '\uFF4E': return '\uFF2E';
    case '\uFF4F': return '\uFF2F';
    case '\uFF50': return '\uFF30';
    case '\uFF51': return '\uFF31';
    case '\uFF52': return '\uFF32';
    case '\uFF53': return '\uFF33';
    case '\uFF54': return '\uFF34';
    case '\uFF55': return '\uFF35';
    case '\uFF56': return '\uFF36';
    case '\uFF57': return '\uFF37';
    case '\uFF58': return '\uFF38';
    case '\uFF59': return '\uFF39';
    case '\uFF5A': return '\uFF3A';
    default: return shar;
    }
  }
  
  public static int getNumericValue(char shar) {
    int value = digit(shar, MAX_RADIX);
    if (value != -1)
      return value;
    if ('\u00B2' <= shar && shar <= '\u00B3')
      return shar-'\u00B0';
    else if (shar == '\u00B9')
      return 1;
    else if ('\u00BC' <= shar && shar <= '\u00BE')
      return -2;
    else if ('\u09F4' <= shar && shar <= '\u09F7')
      return shar-'\u09F3';
    else if (shar == '\u09F8')
      return -2;
    else if (shar == '\u09F9') 
      return 16;
    else if (shar == '\u0BF0') 
      return 10;
    else if (shar == '\u0BF1') 
      return 100;
    else if (shar == '\u0BF2') 
      return 1000;
    else if (shar == '\u2070') 
      return 0;
    else if ('\u2074' <= shar && shar <= '\u2089')
      return (shar-'\u2070')%10;
    else if ('\u2153' <= shar && shar <= '\u215E')
      return -2;
    else if (shar == '\u215F') 
      return 1;
    else if ('\u2160' <= shar && shar <= '\u216B')
      return shar-'\u215F';
    else if (shar == '\u216C') 
      return 50;
    else if (shar == '\u216D') 
      return 100;
    else if (shar == '\u216E') 
      return 500;
    else if (shar == '\u216F') 
      return 1000;
    else if ('\u2170' <= shar && shar <= '\u217B')
      return shar-'\u216F';
    else if (shar == '\u217C') 
      return 50;
    else if (shar == '\u217D') 
      return 100;
    else if (shar == '\u217E') 
      return 500;
    else if ('\u217F' <= shar && shar <= '\u2180')
      return 1000;
    else if (shar == '\u2181') 
      return 5000;
    else if (shar == '\u2182') 
      return 10000;
    else if ('\u2460' <= shar && shar <= '\u2473')
      return shar-'\u245F';
    else if ('\u2474' <= shar && shar <= '\u2487')
      return shar-'\u2473';
    else if ('\u2488' <= shar && shar <= '\u249B')
      return shar-'\u2487';
    else if (shar == '\u24EA') 
      return 0;
    else if ('\u2776' <= shar && shar <= '\u277F')
      return shar-'\u2775';
    else if ('\u2780' <= shar && shar <= '\u2789')
      return shar-'\u277F';
    else if ('\u278A' <= shar && shar <= '\u2793')
      return shar-'\u2789';
    else if (shar == '\u3007') 
      return 0;
    else if ('\u3021' <= shar && shar <= '\u3029')
      return shar-'\u3020';
    else if ('\u3280' <= shar && shar <= '\u3289')
      return shar-'\u327F';
    return -1;
  }

  public static int digit(char shar, int radix) {
    if (radix < MIN_RADIX || radix > MAX_RADIX)
      return -1;
    int digit;
    if ('\u0030' <= shar && shar <= '\u0039')
      digit = shar-'\u0030';
    else if ('\u0041' <= shar && shar <= '\u005A')
      digit = shar-'\u0037';
    else if ('\u0061' <= shar && shar <= '\u007A')
      digit = shar-'\u0057';
    else if ('\u0660' <= shar && shar <= '\u0669')
      digit = shar-'\u0660';
    else if ('\u06F0' <= shar && shar <= '\u06F9')
      digit = shar-'\u06F0';
    else if ('\u0966' <= shar && shar <= '\u096F')
      digit = shar-'\u0966';
    else if ('\u09E6' <= shar && shar <= '\u09EF')
      digit = shar-'\u09E6';
    else if ('\u0A66' <= shar && shar <= '\u0A6F')
      digit = shar-'\u0A66';
    else if ('\u0AE6' <= shar && shar <= '\u0AEF')
      digit = shar-'\u0AE6';
    else if ('\u0B66' <= shar && shar <= '\u0B6F')
      digit = shar-'\u0B66';
    else if ('\u0BE7' <= shar && shar <= '\u0BEF')
      digit = shar-'\u0BE6';
    else if ('\u0C66' <= shar && shar <= '\u0C6F')
      digit = shar-'\u0C66';
    else if ('\u0CE6' <= shar && shar <= '\u0CEF')
      digit = shar-'\u0CE6';
    else if ('\u0D66' <= shar && shar <= '\u0D6F')
      digit = shar-'\u0D66';
    else if ('\u0E50' <= shar && shar <= '\u0E59')
      digit = shar-'\u0E50';
    else if ('\u0ED0' <= shar && shar <= '\u0ED9')
      digit = shar-'\u0ED0';
    else if ('\u0F20' <= shar && shar <= '\u0F29')
      digit = shar-'\u0F20';
    else if ('\uFF10' <= shar && shar <= '\uFF19')
      digit = shar-'\uFF10';
    else if ('\uFF21' <= shar && shar <= '\uFF3A')
      digit = shar-'\uFF17';
    else if ('\uFF41' <= shar && shar <= '\uFF5A')
      digit = shar-'\uFF37';
    else
      return -1;
    return digit < radix ? digit : -1;
  }

  public static char forDigit(int ant, int radix) {
    if (radix < MIN_RADIX || radix > MAX_RADIX)
      return '\u0000';
    if (ant < 0 || ant >= radix)
      return '\u0000';
    return (char)(ant < 10 ? ant+'0' : ant-10+'a');
  }

  public static String toString(char shar) {
    return new String(new char[]{ shar });
  }

  public static class Subset {

    private final String name;

    protected Subset(String name) {
      this.name = name;
    }

    public final boolean equals(Object object) {
      return super.equals(object);
    }

    public final int hashCode() {
      return super.hashCode();
    }

    public final String toString() {
      return name;
    }

  }

  public static final class UnicodeBlock extends Subset {
    
    public static final UnicodeBlock ALPHABETIC_PRESENTATION_FORMS = new UnicodeBlock("ALPHABETIC_PRESENTATION_FORMS", '\uFB00', '\uFB4F');
    public static final UnicodeBlock ARABIC = new UnicodeBlock("ARABIC", '\u0600', '\u06FF');
    public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_A = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_A", '\uFB50', '\uFDFF');
    public static final UnicodeBlock ARABIC_PRESENTATION_FORMS_B = new UnicodeBlock("ARABIC_PRESENTATION_FORMS_B", '\uFE70', '\uFEFE');
    public static final UnicodeBlock ARMENIAN = new UnicodeBlock("ARMENIAN", '\u0530', '\u058F');
    public static final UnicodeBlock ARROWS = new UnicodeBlock("ARROWS", '\u2190', '\u21FF');
    public static final UnicodeBlock BASIC_LATIN = new UnicodeBlock("BASIC_LATIN", '\u0000', '\u007F');
    public static final UnicodeBlock BENGALI = new UnicodeBlock("BENGALI", '\u0980', '\u09FF');
    public static final UnicodeBlock BLOCK_ELEMENTS = new UnicodeBlock("BLOCK_ELEMENTS", '\u2580', '\u259F');
    public static final UnicodeBlock BOPOMOFO = new UnicodeBlock("BOPOMOFO", '\u3100', '\u312F');
    public static final UnicodeBlock BOPOMOFO_EXTENDED = new UnicodeBlock("BOPOMOFO_EXTENDED", '\u31A0', '\u31BF');
    public static final UnicodeBlock BOX_DRAWING = new UnicodeBlock("BOX_DRAWING", '\u2500', '\u257F');
    public static final UnicodeBlock BRAILLE_PATTERNS = new UnicodeBlock("BRAILLE_PATTERNS", '\u2800', '\u28FF');
    public static final UnicodeBlock CHEROKEE = new UnicodeBlock("CHEROKEE", '\u139F', '\u13FF');
    public static final UnicodeBlock CJK_COMPATIBILITY = new UnicodeBlock("CJK_COMPATIBILITY", '\u3300', '\u33FF');
    public static final UnicodeBlock CJK_COMPATIBILITY_FORMS = new UnicodeBlock("CJK_COMPATIBILITY_FORMS", '\uFE30', '\uFE4F');
    public static final UnicodeBlock CJK_COMPATIBILITY_IDEOGRAPHS = new UnicodeBlock("CJK_COMPATIBILITY_IDEOGRAPHS", '\uF900', '\uFAFF');
    public static final UnicodeBlock CJK_RADICALS_SUPPLEMENT = new UnicodeBlock("CJK_RADICALS_SUPPLEMENT", '\u2E80', '\u2EFF');
    public static final UnicodeBlock CJK_SYMBOLS_AND_PUNCTUATION = new UnicodeBlock("CJK_SYMBOLS_AND_PUNCTUATION", '\u3000', '\u303F');
    public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS", '\u4E00', '\u9FFF');
    public static final UnicodeBlock CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A = new UnicodeBlock("CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A", '\u3400', '\u4DB5');
    public static final UnicodeBlock COMBINING_DIACRITICAL_MARKS = new UnicodeBlock("COMBINING_DIACRITICAL_MARKS", '\u0300', '\u036F');
    public static final UnicodeBlock COMBINING_HALF_MARKS = new UnicodeBlock("COMBINING_HALF_MARKS", '\uFE20', '\uFE2F');
    public static final UnicodeBlock COMBINING_MARKS_FOR_SYMBOLS = new UnicodeBlock("COMBINING_MARKS_FOR_SYMBOLS", '\u20D0', '\u20FF');
    public static final UnicodeBlock CONTROL_PICTURES = new UnicodeBlock("CONTROL_PICTURES", '\u2400', '\u243F');
    public static final UnicodeBlock CURRENCY_SYMBOLS = new UnicodeBlock("CURRENCY_SYMBOLS", '\u20A0', '\u20CF');
    public static final UnicodeBlock CYRILLIC = new UnicodeBlock("CYRILLIC", '\u0400', '\u04FF');
    public static final UnicodeBlock DEVANAGARI = new UnicodeBlock("DEVANAGARI", '\u0900', '\u097F');
    public static final UnicodeBlock DINGBATS = new UnicodeBlock("DINGBATS", '\u2700', '\u27BF');
    public static final UnicodeBlock ENCLOSED_ALPHANUMERICS = new UnicodeBlock("ENCLOSED_ALPHANUMERICS", '\u2460', '\u24FF');
    public static final UnicodeBlock ENCLOSED_CJK_LETTERS_AND_MONTHS = new UnicodeBlock("ENCLOSED_CJK_LETTERS_AND_MONTHS", '\u3200', '\u32FF');
    public static final UnicodeBlock ETHIOPIC = new UnicodeBlock("ETHIOPIC", '\u1200', '\u137F');
    public static final UnicodeBlock GENERAL_PUNCTUATION = new UnicodeBlock("GENERAL_PUNCTUATION", '\u2000', '\u206F');
    public static final UnicodeBlock GEOMETRIC_SHAPES = new UnicodeBlock("GEOMETRIC_SHAPES", '\u25A0', '\u25FF');
    public static final UnicodeBlock GEORGIAN = new UnicodeBlock("GEORGIAN", '\u10A0', '\u10FF');
    public static final UnicodeBlock GREEK = new UnicodeBlock("GREEK", '\u0370', '\u03FF');
    public static final UnicodeBlock GREEK_EXTENDED = new UnicodeBlock("GREEK_EXTENDED", '\u1F00', '\u1FFF');
    public static final UnicodeBlock GUJARATI = new UnicodeBlock("GUJARATI", '\u0A80', '\u0AFF');
    public static final UnicodeBlock GURMUKHI = new UnicodeBlock("GURMUKHI", '\u0A00', '\u0A7F');
    public static final UnicodeBlock HALFWIDTH_AND_FULLWIDTH_FORMS = new UnicodeBlock("HALFWIDTH_AND_FULLWIDTH_FORMS", '\uFF00', '\uFFEF');
    public static final UnicodeBlock HANGUL_COMPATIBILITY_JAMO = new UnicodeBlock("HANGUL_COMPATIBILITY_JAMO", '\u3130', '\u318F');
    public static final UnicodeBlock HANGUL_JAMO = new UnicodeBlock("HANGUL_JAMO", '\u1100', '\u11FF');
    public static final UnicodeBlock HANGUL_SYLLABLES = new UnicodeBlock("HANGUL_SYLLABLES", '\uAC00', '\uD7A3');
    public static final UnicodeBlock HEBREW = new UnicodeBlock("HEBREW", '\u0590', '\u05FF');
    public static final UnicodeBlock HIRAGANA = new UnicodeBlock("HIRAGANA", '\u3040', '\u309F');
    public static final UnicodeBlock IDEOGRAPHIC_DESCRIPTION_CHARACTERS = new UnicodeBlock("IDEOGRAPHIC_DESCRIPTION_CHARACTERS", '\u2FF0', '\u2FFF');
    public static final UnicodeBlock IPA_EXTENSIONS = new UnicodeBlock("IPA_EXTENSIONS", '\u0250', '\u02AF');
    public static final UnicodeBlock KANBUN = new UnicodeBlock("KANBUN", '\u3190', '\u31FF');
    public static final UnicodeBlock KANGXI_RADICALS = new UnicodeBlock("KANGXI_RADICALS", '\u2F00', '\u2FDF');
    public static final UnicodeBlock KANNADA = new UnicodeBlock("KANNADA", '\u0C80', '\u0CFF');
    public static final UnicodeBlock KATAKANA = new UnicodeBlock("KATAKANA", '\u30A0', '\u30FF');
    public static final UnicodeBlock KHMER = new UnicodeBlock("KHMER", '\u1780', '\u17FF');
    public static final UnicodeBlock LAO = new UnicodeBlock("LAO", '\u0E80', '\u0EFF');
    public static final UnicodeBlock LATIN_1_SUPPLEMENT = new UnicodeBlock("LATIN_1_SUPPLEMENT", '\u0080', '\u00FF');
    public static final UnicodeBlock LATIN_EXTENDED_A = new UnicodeBlock("LATIN_EXTENDED_A", '\u0100', '\u017F');
    public static final UnicodeBlock LATIN_EXTENDED_ADDITIONAL = new UnicodeBlock("LATIN_EXTENDED_ADDITIONAL", '\u1E00', '\u1EFF');
    public static final UnicodeBlock LATIN_EXTENDED_B = new UnicodeBlock("LATIN_EXTENDED_B", '\u0180', '\u024F');
    public static final UnicodeBlock LETTERLIKE_SYMBOLS = new UnicodeBlock("LETTERLIKE_SYMBOLS", '\u2100', '\u214F');
    public static final UnicodeBlock MALAYALAM = new UnicodeBlock("MALAYALAM", '\u0D00', '\u0D7F');
    public static final UnicodeBlock MATHEMATICAL_OPERATORS = new UnicodeBlock("MATHEMATICAL_OPERATORS", '\u2200', '\u22FF');
    public static final UnicodeBlock MISCELLANEOUS_SYMBOLS = new UnicodeBlock("MISCELLANEOUS_SYMBOLS", '\u2600', '\u26FF');
    public static final UnicodeBlock MISCELLANEOUS_TECHNICAL = new UnicodeBlock("MISCELLANEOUS_TECHNICAL", '\u2300', '\u23FF');
    public static final UnicodeBlock MONGOLIAN = new UnicodeBlock("MONGOLIAN", '\u1800', '\u18AF');
    public static final UnicodeBlock MYANMAR = new UnicodeBlock("MYANMAR", '\u1000', '\u109f');
    public static final UnicodeBlock NUMBER_FORMS = new UnicodeBlock("NUMBER_FORMS", '\u2150', '\u218F');
    public static final UnicodeBlock OGHAM = new UnicodeBlock("OGHAM", '\u1680', '\u169F');
    public static final UnicodeBlock OPTICAL_CHARACTER_RECOGNITION = new UnicodeBlock("OPTICAL_CHARACTER_RECOGNITION", '\u2440', '\u245F');
    public static final UnicodeBlock ORIYA = new UnicodeBlock("ORIYA", '\u0B00', '\u0B7F');
    public static final UnicodeBlock PRIVATE_USE_AREA = new UnicodeBlock("PRIVATE_USE_AREA", '\uE000', '\uF8FF');
    public static final UnicodeBlock RUNIC = new UnicodeBlock("RUNIC", '\u16A0', '\u16FF');
    public static final UnicodeBlock SINHALA = new UnicodeBlock("SINHALA", '\u0D80', '\u0DFF');
    public static final UnicodeBlock SMALL_FORM_VARIANTS = new UnicodeBlock("SMALL_FORM_VARIANTS", '\uFE50', '\uFE6F');
    public static final UnicodeBlock SPACING_MODIFIER_LETTERS = new UnicodeBlock("SPACING_MODIFIER_LETTERS", '\u02B0', '\u02FF');
    public static final UnicodeBlock SPECIALS = new UnicodeBlock("SPECIALS", '\uFFF0', '\uFFFE');
    public static final UnicodeBlock SUPERSCRIPTS_AND_SUBSCRIPTS = new UnicodeBlock("SUPERSCRIPTS_AND_SUBSCRIPTS", '\u2070', '\u209F');
    public static final UnicodeBlock SURROGATES_AREA = new UnicodeBlock("SURROGATES_AREA", '\uD800', '\uDFFF');
    public static final UnicodeBlock SYRIAC = new UnicodeBlock("SYRIAC", '\u0700', '\u074F');
    public static final UnicodeBlock TAMIL = new UnicodeBlock("TAMIL", '\u0B80', '\u0BFF');
    public static final UnicodeBlock TELUGU = new UnicodeBlock("TELUGU", '\u0C00', '\u0C7F');
    public static final UnicodeBlock THAANA = new UnicodeBlock("THAANA", '\u0780', '\u07BF');
    public static final UnicodeBlock THAI = new UnicodeBlock("THAI", '\u0E00', '\u0E7F');
    public static final UnicodeBlock TIBETAN = new UnicodeBlock("TIBETAN", '\u0F00', '\u0FBF');
    public static final UnicodeBlock UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS = new UnicodeBlock("UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS", '\u1400', '\u167F');
    public static final UnicodeBlock YI_SYLLABLES = new UnicodeBlock("YI_SYLLABLES", '\uA000', '\uA48F');
    public static final UnicodeBlock YI_RADICALS = new UnicodeBlock("YI_RADICALS", '\uA490', '\uA4CF');
    
    private static final UnicodeBlock blocks[] = {
      BASIC_LATIN,                            LATIN_1_SUPPLEMENT,
      LATIN_EXTENDED_A,                       LATIN_EXTENDED_B,
      IPA_EXTENSIONS,                         SPACING_MODIFIER_LETTERS,
      COMBINING_DIACRITICAL_MARKS,            GREEK,
      CYRILLIC,                               ARMENIAN,                       
      HEBREW,                                 ARABIC,
      SYRIAC,                                 THAANA,
      DEVANAGARI,                             BENGALI,
      GURMUKHI,                               GUJARATI,
      ORIYA,                                  TAMIL,
      TELUGU,                                 KANNADA,
      MALAYALAM,                              SINHALA,
      THAI,                                   LAO,
      TIBETAN,                                MYANMAR,
      GEORGIAN,                               HANGUL_JAMO,
      ETHIOPIC,                               CHEROKEE,
      UNIFIED_CANADIAN_ABORIGINAL_SYLLABICS,  OGHAM,
      RUNIC,                                  KHMER,
      MONGOLIAN,                              LATIN_EXTENDED_ADDITIONAL,
      GREEK_EXTENDED,                         GENERAL_PUNCTUATION,                    
      SUPERSCRIPTS_AND_SUBSCRIPTS,            CURRENCY_SYMBOLS,
      COMBINING_MARKS_FOR_SYMBOLS,            LETTERLIKE_SYMBOLS,
      NUMBER_FORMS,                           ARROWS,
      MATHEMATICAL_OPERATORS,                 MISCELLANEOUS_TECHNICAL,
      CONTROL_PICTURES,                       OPTICAL_CHARACTER_RECOGNITION,
      ENCLOSED_ALPHANUMERICS,                 BOX_DRAWING,
      BLOCK_ELEMENTS,                         GEOMETRIC_SHAPES,
      MISCELLANEOUS_SYMBOLS,                  DINGBATS,
      BRAILLE_PATTERNS,                       CJK_RADICALS_SUPPLEMENT,
      KANGXI_RADICALS,                        IDEOGRAPHIC_DESCRIPTION_CHARACTERS,
      CJK_SYMBOLS_AND_PUNCTUATION,            HIRAGANA,
      KATAKANA,                               BOPOMOFO,
      HANGUL_COMPATIBILITY_JAMO,              KANBUN,
      BOPOMOFO_EXTENDED,                      ENCLOSED_CJK_LETTERS_AND_MONTHS,
      CJK_COMPATIBILITY,                      CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A,
      CJK_UNIFIED_IDEOGRAPHS,                 YI_SYLLABLES,
      YI_RADICALS,                            HANGUL_SYLLABLES,
      SURROGATES_AREA,                        PRIVATE_USE_AREA,
      CJK_COMPATIBILITY_IDEOGRAPHS,           ALPHABETIC_PRESENTATION_FORMS,
      ARABIC_PRESENTATION_FORMS_A,            COMBINING_HALF_MARKS,
      CJK_COMPATIBILITY_FORMS,                SMALL_FORM_VARIANTS,
      ARABIC_PRESENTATION_FORMS_B,            HALFWIDTH_AND_FULLWIDTH_FORMS,
      SPECIALS,
    };

    public static UnicodeBlock of(char shar) {
      int left = 0;
      int right = blocks.length;
      while (left < right) {
        int middle = (left+right)/2;
        int result = blocks[middle].elementOrder(shar);
        if (result < 0)
          right = middle;
        else if (result > 0)
          left = middle+1;
        else
          return blocks[middle];
      }
      if (shar == '\uFEFF')
        return SPECIALS;
      return null;
    }

    private final char start;
    private final char end;
    
    private UnicodeBlock(String name, char start, char end) {
      super(name);
      this.start = start;
      this.end = end;
    }

    private int elementOrder(char shar) {
      return shar < start ? -1 : shar > end ? 1 : 0;
    }

  }

  private final char value;

  public Character(char value) {
    this.value = value;
  }

  public char charValue() {
    return value;
  }

  public int compareTo(Character character) {
    return value - character.value;
  }

  public int compareTo(Object object) {
    return compareTo((Character)object);
  }

  public int hashCode() {
    return value;
  }

  public boolean equals(Object object) {
    return object instanceof Character
        && ((Character)object).value == value;
  }

  public String toString() {
    return toString(value);
  }

}

