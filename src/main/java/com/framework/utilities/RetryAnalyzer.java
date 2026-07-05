package com.framework.utilities;

import org.testng.IRetryAnalyzer;

import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

	int count = 0;

	int maxRetry = 2;

	@Override
	public boolean retry(ITestResult result) {

		if (count < maxRetry) {

			count++;

			System.out.println("Retrying Test : " + result.getName());

			return true;
		}

		return false;
	}
}