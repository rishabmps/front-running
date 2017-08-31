import time
from selenium import webdriver

driver = webdriver.Chrome('chromedriver')  # Optional argument, if not specified will search path.
driver.get('http://192.168.43.42:8080/front_running/')
time.sleep(1) # Let the user actually see something!
# customer_id = driver.find_element_by_name('customerid')
# quantity = driver.find_element_by_name('quantity')
# price = driver.find_element_by_name('price')
# security_name = driver.find_elements_by_name('securityname')
# trade_type = driver.find_elements_by_name('tradetype')
# security_type = driver.find_elements_by_name('securitytype')
# form = driver.find_element_by_id('submitbutton')

cases = []
case1 = [
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Buy',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'stocks',
		'15', #Quantit\
		'15' #Price
	]
]
cases.append(case1)

case2 = [
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Sell',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'stocks',
		'15', #Quantit\
		'15' #Price
	]
]


cases.append(case2)

case3 = [
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Sell',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'put_option',
		'15', #Quantit\
		'15' #Price
	]
]



cases.append(case3)

case4 = [
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Buy',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'put_option',
		'15', #Quantit\
		'15' #Price
	]
]

cases.append(case4)

case5 = [
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Buy',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'put_option',
		'15', #Quantit\
		'15' #Price
	]
]

cases.append(case5)

case6 = [
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'futures',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Sell',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'futures',
		'15', #Quantit\
		'15' #Price
	]
]

cases.append(case6)
case7 = [
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'put_option',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Sell',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'put_option',
		'15', #Quantit\
		'15' #Price
	]
]


cases.append(case7)
case8 = [
	[
		'1', #Customer ID
		'Sell',
		'apple', 
		'futures',
		'15', #Quantity
		'15' #Price
	],
	[
		'2', #Customer ID
		'Sell',
		'apple', 
		'stocks',
		'15', #Quantity
		'15' #Price
	],
	[
		'1', #Customer ID
		'Buy',
		'apple', 
		'futures',
		'15', #Quantit\
		'15' #Price
	]
]
cases.append(case8)
for case in cases:
	for trade in case:
		print(trade[0])
		customer_id = driver.find_element_by_name('customerid')
		quantity = driver.find_element_by_name('quantity')
		price = driver.find_element_by_name('price')
		security_name = driver.find_elements_by_name('securityname')
		trade_type = driver.find_elements_by_name('tradetype')
		security_type = driver.find_elements_by_name('securitytype')
		form = driver.find_element_by_id('submitbutton')
		customer_id.send_keys(trade[0])
		quantity.send_keys(trade[4])
		price.send_keys(trade[5])
		# if (security_name.get_attribute('value') == trade[2]):
		for security in security_name:
			if (security.get_attribute('value') == trade[2]):
				security.click()
		for security_t in security_type:
			if (security_t.get_attribute('value') == trade[3]):
				security_t.click()
		for t_type in trade_type:
			if (t_type.get_attribute('value') == trade[1]):
				t_type.click()
		time.sleep(1)
		form.click()
	time.sleep(15)
	back = driver.find_element_by_id('back_button')
	back.click()
driver.get('http://192.168.43.42:8080/front_running/trade?operation=listDisplay')