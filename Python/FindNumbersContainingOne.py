'''
https://leetcode.com/problems/number-of-digit-one/
#math
'''

def sub(number):
	if number < 10:
		return 1

	count = 0
	
	itera = int(number/10)
	
	while itera > 0:
		count += 1
		itera = int(itera / 10)

	temp = pow(10,count)
	if number/temp > 1:
		return temp + (number/temp - 1) * sub(temp -1) + sub(number % temp)
	else:
		return number % temp + sub(temp -1)

n = input("input: ")
n = int(n)
print("output: " + str(sub(n)))
