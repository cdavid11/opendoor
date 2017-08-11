import time
import requests

list = []

while True:
	file = open("open_events.txt", "r")
	for line in file:
		if line in list:
			continue
		else:
			print(line)
			list.append(line)
			r = requests.post('', data = {'name':'Door Open'})
	file.close()
	time.sleep(2)	
