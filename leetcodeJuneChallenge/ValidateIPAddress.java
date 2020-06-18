/*
https://leetcode.com/explore/featured/card/june-leetcoding-challenge/541/week-3-june-15th-june-21st/3362/
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers,
 each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits.
The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. 
Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, 
so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons (::) to pursue simplicity. 
For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.
Besides, extra leading zeros in the IPv6 is also invalid. 
For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note: You may assume there is no extra space or special characters in the input string.

Example 1:
Input: "172.16.254.1"
Output: "IPv4"
Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:
Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
Output: "IPv6"
Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:
Input: "256.256.256.256"
Output: "Neither"
Explanation: This is neither a IPv4 address nor a IPv6 address.
==========================================================================================================================
1) One pass
use 3 counters to iterate through the string
i - the head of the iterator
j - the number of clauses in the string 
k - the number of characters in a clause

for ipv4, 
the leading zeroes are not allowed thus we check if there exist a clause of 1 length and value 0
the value of a clause must be between 0 and 255 inclusive

for ipv6, 
the characters allowed include a-f and A-F 

other than that, the two verifications include a few common checks
check that the number of characters in a clause do not exceed the respective lengths of 3 and 4
check that each clause has at least 1 length ie no adjacent delimiters
check that the string does not start and/or end with the delimiter
check that the number of clauses are of 4 and 8
check that there are no invalid characters in the string

Time Complexity: O(n)
Space Complexity: O(n) for char array for convenience
*/

import java.util.*;

public class ValidateIPAddress {

    public String validIPAddress(String IP) {
        if (verifyIPv4(IP)) {
            return "IPv4";
        }
        if (verifyIPv6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean verifyIPv4(String IP) {
        int l = IP.length();
        if (IP.indexOf(".") == -1) {
            // not ipv4 format
            // System.out.println("not ipv4 format");
            return false;
        }
        char[] ip = IP.toCharArray();
        int i = 0;
        int j = 0;
        int k = 0;
        int v = 0;
        while (i < l && j < 4) {
            if (!(48 <= ip[i] && ip[i] <= 57)) {
                if (ip[i] == '.') {
                    if (k == 0) {
                        // .. or start with .
                        // System.out.println(".. or start with .");
                        return false;
                    }
                    if (i == l - 1) {
                        // end with .
                        // System.out.println("end with .");
                        return false;
                    }
                    if (j == 3) {
                        // extra .
                        // System.out.println("extra clause");
                        return false;
                    }
                    j++;
                    k = -1;
                    v = 0;
                } else {
                    // invalid character
                    // System.out.println("invalid character");
                    return false;
                }
            }
            if (k == 1 && ip[i - 1] == 48) {
                // leading zero
                // System.out.println("leading zero");
                return false;
            }
            if (k > 3) {
                // more than 3 characters in a clause
                // System.out.println("more than 3 characters in a clause");
                return false;
            }

            // check if within range of 0-255

            if (k >= 0) {
                v = v * 10 + Integer.parseInt(IP.substring(i, i + 1));
                if (!(0 <= v && v <= 255)) {
                    // System.out.println(String.format("%s invalid value", v));
                    return false;
                }
            }
            i++;
            k++;
        }
        if (j != 3) {
            // not enough clauses
            return false;
        }
        return true;
    }

    private boolean verifyIPv6(String IP) {
        int l = IP.length();
        char[] ip = IP.toCharArray();
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < l && j < 8) {
            // check if 0-9 or A-F or a-f
            if (!((48 <= ip[i] && ip[i] <= 57) || (65 <= ip[i] && ip[i] <= 70) || (97 <= ip[i] && ip[i] <= 102))) {
                if (ip[i] == ':') {
                    if (k == 0) {
                        // :: or start with :
                        // System.out.println(":: or start with :");
                        return false;
                    }
                    if (j == 7) {
                        // extra :
                        // System.out.println("extra :");
                        return false;
                    }
                    j++;
                    k = -1;
                } else {
                    // invalid character
                    // System.out.println("invalid character");
                    return false;
                }
            }
            if (k == 4) {
                // more than 4characters in a clause
                // System.out.println("5 characters");
                return false;
            }
            i++;
            k++;
        }
        if (j != 7) {
            // not enough clauses
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        String IP1 = "2001:db8:85a3:0:0:8A2E:0370:7334";
        String IP2 = "2001:0db8:85a3:0000:0000:8a2e:0370:7334";
        String IP3 = "2001:0db8:85a3::8A2E:0370:7334";
        String IP4 = "02001:0db8:85a3:0000:0000:8a2e:0370:7334";
        String IP5 = "172.16.254.1";
        String IP6 = "172.16.254.01";
        String IP7 = "256.256.256.256";
        String IP8 = "1.0.1.";
        String IP9 = "172.16.254.1";
        // String[] tokens = IP.split("\\:");
        // System.out.println(tokens.length);
        // for (String s : tokens) {
        // System.out.println(s);
        // }
        ValidateIPAddress v = new ValidateIPAddress();
        System.out.println(v.validIPAddress(IP1));
        System.out.println(v.validIPAddress(IP2));
        System.out.println(v.validIPAddress(IP3));
        System.out.println(v.validIPAddress(IP4));
        System.out.println(v.validIPAddress(IP5));
        System.out.println(v.validIPAddress(IP6));
        System.out.println(v.validIPAddress(IP7));
        System.out.println(v.validIPAddress(IP8));
        System.out.println(v.validIPAddress(IP9));

    }
}