#!/usr/bin/python
import boto.ec2.cloudwatch
import sys

AWS_KEY = "AKIAJH7I4TBUS7PVTAMA"
AWS_SECRET = "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"

def senddata(data):
	global AWS_KEY
	global AWS_SECRET

	cw = boto.ec2.cloudwatch.CloudWatchConnection(AWS_KEY,AWS_SECRET)
	cw.put_metric_data(namespace="frontendThroughput", name="WaydreamQuery", value=data, unit="Percent", dimensions = {"AutoScalingGroupName": "waydreamTwitter"})


def main():
	data = str(sys.argv[1])
	senddata(int(data))

if __name__ == '__main__':
	main()
