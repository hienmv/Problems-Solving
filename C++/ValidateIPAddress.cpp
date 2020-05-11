/*
Given a list of strings, determine whether the strings represent valid IP addresses 
under the rules presented. Return an array of strings, either IPv4, IPv6 or Neither 
as appropriate for each given string. 
The pattern requirements are described below.
 
IPv4 was the first publicly used Internet Protocol. 
It uses 4-byte addresses which permits 232 distinct values. 
The typical format for an IPv4 address is A.B.C.D where A, B, C, and D are all decimal 
integers in the inclusive range between 0 and 255. The lengths of A, B, C, and D cannot 
be larger than 3. In the past, a leading zero signaled that the value is expressed 
in octal rather than decimal format. In 2005, the IETF came out with a draft regarding 
IP addressing stating that addresses should only be in dotted decimal notation: octal 
is to be deprecated. This has not been fully adopted. In this exercise, a leading zero 
is allowed, but only if the value it precedes is less than 8. For example, if A = 7, 
it can be represented 007, 07 or 7. If A = 8 or A = 11, it cannot be preceded by a zero. 
This avoids confusion such as 011(8) = 9(10).
 
IPv6 is the most recent version of Internet Protocol. IPv6 addresses have 128 bits. The 128 
bits of an IPv6 address are represented in 8 groups of 16 bits each. Each group is written 
as four hexadecimal digits and the groups are separated by colons (:). An example of this 
representation is 2001:0db8:0000:0000:0000:ff00:0042:8329. For convenience, an IPv6 address 
may be abbreviated to shorter notations by application of the following rules:
One or more leading zeros from any groups of hexadecimal digits are removed. This is usually 
done to either all or none of the leading zeros. For example, the group 0042 is converted to 42.
Consecutive sections of zeros are replaced with a double colon (::). The double colon may only 
be used once in an address, as multiple uses would render the address indeterminate.
 
Consider the IPv6 address 2001:0db8:0000:0000:0000:ff00:0000:8329. Removing all the leading 
zeros in each group yields 2001:db8:0:0:0:ff00:0:8329. After omitting the longest consecutive 
section of zeros, the result is 2001:db8::ff00:0:8329. The address, 
0000:0000:0000:0000:0000:0000:0000:0001, may be abbreviated to ::1 by using both rules.
 
Function Description 
Complete the function validateAddresses in the editor below.
 
validateAddresses has the following parameter(s):
    string addresses[n]:  an array of strings to validate
Returns:
    string[n]:  an array of strings where for each addresses[i], 
    the value at each index i should be the string IPv4, IPv6, or Neither if the address is not valid.
 
Constraints
1 ≤ n ≤ 5 × 10^3
Each string consists of digits ([0-9]), lower-case English letters ([a-z]), dot (.), and colon (:).

*****************

SAMPLE 1
STDIN           Function
-----           --------    
5               →    addresses[] size n = 5
121.18.19.20    →    addresses = ['121.18.19.20', '0.12.12.34', '121.234.12.12', '23.45.12.56', '0.1.2.3']
0.12.12.34
121.234.12.12
23.45.12.56
0.1.2.3

STDOUT:
IPv4
IPv4
IPv4
IPv4
IPv4
*****************

SAMPLE 2
STDIN                                        Function
-----                                        --------    
5                                          → addresses[] size n = 5
2001:0db8:0000:0000:0000:ff00:0042:8329    → addresses = ['2001:0db8:0000:0000:0000:ff00:0042:8329', '2001:db8:0:0:0:ff00:42:8329'\
2001:db8:0:0:0:ff00:42:8329                               '2001:db8::ff00:42:8329', '0000:0000:0000:0000:0000:0000:0000:0001', '::1']
2001:db8::ff00:42:8329
0000:0000:0000:0000:0000:0000:0000:0001
::1

STDOUT:
IPv6
IPv6
IPv6
IPv6
IPv6

*****************

SAMPLE 3
STDIN                  Function
-----                  --------
6                 →    addresses[] size n = 6
000.012.234.23    →    addresses = ['000.012.234.23', '666.666.23.23', '.213.123.23.32', '23.45.22.32.',\
666.666.23.23                       '272:2624:235e:3bc2:c46d:682:5d46:638g', '1:22:333:4444']
.213.123.23.32
23.45.22.32.
272:2624:235e:3bc2:c46d:682:5d46:638g
1:22:333:4444

STDOUT:
Neither
Neither
Neither
Neither
Neither
Neither
*/

#include <bits/stdc++.h>

using namespace std;

string ltrim(const string &);
string rtrim(const string &);

bool checkSectionIpv4(string& address, int start, int len);
bool checkSectionIpv6(string& address, int start, int len, bool beforeAbbreviation = false);
string getIpv4Name(string& address);
string getIpv6Name(string& address);
string getIpName(string& address);

bool checkSectionIpv4(string& address, int start, int len) {
    if (len < 1 || len > 3)  {
        return false;
    }
    int section = stoi(address.substr(start, len));
    if(address[start] == '0' &&  section >= 8) {
        return false;
    }
    if (section > 255) {
        return false;
    }
    return true;
}
string getIpv4Name(string& address) {
    for (char c : address) {
        if (c != '.' && (c < '0' || c > '9')) return "Neither";
    }
    int pre = 0;
    int countSplit = 0;
    int len = (int) address.size();
    for (auto idx = 0; idx < len; ++idx) {
        if (address[idx] == '.') {
            countSplit++;
            if (countSplit > 3) break;
            // check section
            if (!checkSectionIpv4(address, pre, idx - pre)) {
                return "Neither";;
            }
            pre = idx+1;
        }
    }
    // check last section
    if (!checkSectionIpv4(address, pre, len - pre)) {
        return "Neither";;
    }
    if (countSplit != 3) return "Neither";
    return "IPv4";
}

bool checkSectionIpv6(string& address, int start, int len, bool beforeAbbreviation) {
    if ( (!beforeAbbreviation && len < 1) || len > 4)  {
        return false;
    }
    return true;
}

string getIpv6Name(string& address) {
    for (char c : address) {
        if (c != ':' && (c < '0' || c > 'f')) return "Neither";
    }
    int countSplit = 0;
    int countSplitAbbreviation = 0;
    int idx = 0;
    int pre = 0;
    int len = (int) address.size();
    bool isNeedCheckLastSection = true;
    while(idx < len) {
        if (address[idx] == ':') {
            // SplitAbbreviation
            if (address[idx+1] == ':') {
                // check section
                if(!checkSectionIpv6(address, pre, idx - pre, true)) {
                    return "Neither";
                }
                countSplitAbbreviation++;
                idx += 2;
                if (idx == len) isNeedCheckLastSection = false;
            }
            // split
            else {
                // check section
                if(!checkSectionIpv6(address, pre, idx - pre)) {
                    return "Neither";
                }
                countSplit++;
                idx++;
            }
            pre = idx;
        }
        else {
            idx++;
        }
    }
    // check last section
    if(isNeedCheckLastSection && !checkSectionIpv6(address, pre, len - pre)) {
        return "Neither";
    }
    
    if (countSplitAbbreviation > 1) {
        return "Neither";
    }
    if (countSplitAbbreviation == 0 && countSplit != 7) {
        return "Neither";
    }
    return "IPv6";
}
        
// helper function
string getIpName(string& address) {
    int ipv4_able = 0;
    int ipv6_able = 0;
    for (char c : address) {
        if (c == '.') {
            ipv4_able = 1;
        }
        if (c == ':') {
            ipv6_able = 1;
        }
    }
    if ((ipv4_able ^ ipv6_able) == 0) return "Neither";
    if (ipv4_able) {
        return getIpv4Name(address);
    }
    if (ipv6_able) {
        return getIpv6Name(address);
    }
    return "Neither";
}

/*
 * Complete the 'validateAddresses' function below.
 *
 * The function is expected to return a STRING_ARRAY.
 * The function accepts STRING_ARRAY addresses as parameter.
 */
vector<string> validateAddresses(vector<string> addresses) {
    vector<string> result;
    for (auto address : addresses) {
        result.push_back(getIpName(address));
    }
    return result;
}

int main()
{
    ofstream fout(getenv("OUTPUT_PATH"));

    string addresses_count_temp;
    getline(cin, addresses_count_temp);

    int addresses_count = stoi(ltrim(rtrim(addresses_count_temp)));

    vector<string> addresses(addresses_count);

    for (int i = 0; i < addresses_count; i++) {
        string addresses_item;
        getline(cin, addresses_item);

        addresses[i] = addresses_item;
    }

    vector<string> result = validateAddresses(addresses);

    for (int i = 0; i < result.size(); i++) {
        fout << result[i];

        if (i != result.size() - 1) {
            fout << "\n";
        }
    }

    fout << "\n";

    fout.close();

    return 0;
}

string ltrim(const string &str) {
    string s(str);

    s.erase(
        s.begin(),
        find_if(s.begin(), s.end(), not1(ptr_fun<int, int>(isspace)))
    );

    return s;
}

string rtrim(const string &str) {
    string s(str);

    s.erase(
        find_if(s.rbegin(), s.rend(), not1(ptr_fun<int, int>(isspace))).base(),
        s.end()
    );

    return s;
}
