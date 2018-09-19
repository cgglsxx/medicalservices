

package com.api.util;

import java.util.Collection;
import java.util.Map;

public class ValidateUtil {


    public ValidateUtil() {
    }


    public static boolean isEmpty(Object var0) {
        if (var0 == null) {
            return true;
        } else {
            if (var0 instanceof String) {
                if (((String)var0).length() == 0) {
                    return true;
                }
            } else if (var0 instanceof Collection) {
                if (((Collection)var0).size() == 0) {
                    return true;
                }
            } else if (var0 instanceof Map && ((Map)var0).size() == 0) {
                return true;
            }

            return false;
        }
    }

    public static boolean isEmpty(String var0) {
        return var0 == null || var0.length() == 0;
    }

    public static boolean isEmpty(Collection var0) {
        return var0 == null || var0.size() == 0;
    }

    public static boolean isNotEmpty(String var0) {
        return var0 != null && var0.length() > 0;
    }

    public static boolean isNotEmpty(Collection var0) {
        return var0 != null && var0.size() > 0;
    }

    public static boolean isString(Object var0) {
        return var0 != null && var0 instanceof String;
    }

    public static boolean isWhitespace(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            for(int var1 = 0; var1 < var0.length(); ++var1) {
                char var2 = var0.charAt(var1);
                if (" \t\n\r".indexOf(var2) == -1) {
                    return false;
                }
            }

            return true;
        }
    }

    public static String stripCharsInBag(String var0, String var1) {
        StringBuffer var3 = new StringBuffer("");

        for(int var2 = 0; var2 < var0.length(); ++var2) {
            char var4 = var0.charAt(var2);
            if (var1.indexOf(var4) == -1) {
                var3.append(var4);
            }
        }

        return var3.toString();
    }

    public static String stripCharsNotInBag(String var0, String var1) {
        StringBuffer var3 = new StringBuffer("");

        for(int var2 = 0; var2 < var0.length(); ++var2) {
            char var4 = var0.charAt(var2);
            if (var1.indexOf(var4) != -1) {
                var3.append(var4);
            }
        }

        return var3.toString();
    }

    public static String stripWhitespace(String var0) {
        return stripCharsInBag(var0, " \t\n\r");
    }

    public static boolean charInString(char var0, String var1) {
        return var1.indexOf(var0) != -1;
    }

    public static String stripInitialWhitespace(String var0) {
        int var1;
        for(var1 = 0; var1 < var0.length() && charInString(var0.charAt(var1), " \t\n\r"); ++var1) {
            ;
        }

        return var0.substring(var1);
    }

    public static boolean isLetter(char var0) {
        return Character.isLetter(var0);
    }

    public static boolean isDigit(char var0) {
        return Character.isDigit(var0);
    }

    public static boolean isLetterOrDigit(char var0) {
        return Character.isLetterOrDigit(var0);
    }

    public static boolean isInteger(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            for(int var1 = 0; var1 < var0.length(); ++var1) {
                char var2 = var0.charAt(var1);
                if (!isDigit(var2)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isSignedInteger(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                Integer.parseInt(var0);
                return true;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isSignedLong(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                Long.parseLong(var0);
                return true;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isPositiveInteger(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                long var1 = Long.parseLong(var0);
                return var1 > 0L;
            } catch (Exception var3) {
                return false;
            }
        }
    }

    public static boolean isNonnegativeInteger(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                int var1 = Integer.parseInt(var0);
                return var1 >= 0;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isNegativeInteger(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                int var1 = Integer.parseInt(var0);
                return var1 < 0;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isNonpositiveInteger(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                int var1 = Integer.parseInt(var0);
                return var1 <= 0;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isFloat(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            boolean var1 = false;
            if (var0.startsWith(".")) {
                return false;
            } else {
                for(int var2 = 0; var2 < var0.length(); ++var2) {
                    char var3 = var0.charAt(var2);
                    if (var3 == ".".charAt(0)) {
                        if (var1) {
                            return false;
                        }

                        var1 = true;
                    } else if (!isDigit(var3)) {
                        return false;
                    }
                }

                return true;
            }
        }
    }

    public static boolean isSignedFloat(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                float var1 = Float.parseFloat(var0);
                return var1 <= 0.0F;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isSignedDouble(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            try {
                Double.parseDouble(var0);
                return true;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isAlphabetic(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            for(int var1 = 0; var1 < var0.length(); ++var1) {
                char var2 = var0.charAt(var1);
                if (!isLetter(var2)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isAlphanumeric(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            for(int var1 = 0; var1 < var0.length(); ++var1) {
                char var2 = var0.charAt(var1);
                if (!isLetterOrDigit(var2)) {
                    return false;
                }
            }

            return true;
        }
    }

    public static boolean isSSN(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            String var1 = stripCharsInBag(var0, "- ");
            return isInteger(var1) && var1.length() == 9;
        }
    }

    public static boolean isUSPhoneNumber(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            String var1 = stripCharsInBag(var0, "()- ");
            return isInteger(var1) && var1.length() == 10;
        }
    }

    public static boolean isUSPhoneAreaCode(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            String var1 = stripCharsInBag(var0, "()- ");
            return isInteger(var1) && var1.length() == 3;
        }
    }

    public static boolean isUSPhoneMainNumber(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            String var1 = stripCharsInBag(var0, "()- ");
            return isInteger(var1) && var1.length() == 7;
        }
    }

    public static boolean isInternationalPhoneNumber(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            String var1 = stripCharsInBag(var0, "()- ");
            return isPositiveInteger(var1);
        }
    }

    public static boolean isZipCode(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            String var1 = stripCharsInBag(var0, "-");
            return isInteger(var1) && (var1.length() == 5 || var1.length() == 9);
        }
    }

    public static boolean isContiguousZipCode(String var0) {
        boolean var1 = false;
        if (isZipCode(var0)) {
            if (isEmpty(var0)) {
                var1 = true;
            } else {
                String var2 = var0.substring(0, 5);
                int var3 = Integer.parseInt(var2);
                if ((var3 < 96701 || var3 > 96898) && (var3 < 99501 || var3 > 99950)) {
                    var1 = true;
                } else {
                    var1 = false;
                }
            }
        }

        return var1;
    }

    public static boolean isStateCode(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            return "AL|AK|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|MP|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY|AE|AA|AE|AE|AP".indexOf(var0) != -1 && var0.indexOf("|") == -1;
        }
    }

    public static boolean isContiguousStateCode(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            return "AL|AZ|AR|CA|CO|CT|DE|DC|FL|GA|ID|IL|IN|IA|KS|KY|LA|ME|MD|MA|MI|MN|MS|MO|MT|NE|NV|NH|NJ|NM|NY|NC|ND|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VT|VA|WA|WV|WI|WY".indexOf(var0) != -1 && var0.indexOf("|") == -1;
        }
    }

    public static boolean isEmail(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else if (isWhitespace(var0)) {
            return false;
        } else {
            int var1 = 1;

            int var2;
            for(var2 = var0.length(); var1 < var2 && var0.charAt(var1) != '@'; ++var1) {
                ;
            }

            return var1 < var2 - 1 && var0.charAt(var1) == '@';
        }
    }

    public static boolean isUrl(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else {
            return var0.indexOf("://") != -1;
        }
    }

    public static boolean isYear(String var0) {
        if (isEmpty(var0)) {
            return true;
        } else if (!isNonnegativeInteger(var0)) {
            return false;
        } else {
            return var0.length() == 2 || var0.length() == 4;
        }
    }

    public static boolean isIntegerInRange(String var0, int var1, int var2) {
        if (isEmpty(var0)) {
            return true;
        } else if (!isSignedInteger(var0)) {
            return false;
        } else {
            int var3 = Integer.parseInt(var0);
            return var3 >= var1 && var3 <= var2;
        }
    }




}
