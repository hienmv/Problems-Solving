
def sub(number):
	""" 
	Find numbers in range [0, numbers] containning number 1 in this
	Example: input 22, Output: 12  { 1, 10 , 11, 12, 13, 14, 15, 16, 17, 18, 19, 21}
	"""
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
