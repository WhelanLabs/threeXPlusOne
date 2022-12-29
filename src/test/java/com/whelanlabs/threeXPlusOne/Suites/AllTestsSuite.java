package com.whelanlabs.threeXPlusOne.Suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.whelanlabs.threeXPlusOne.AppTest;
import com.whelanlabs.threeXPlusOne.TailArrayTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ AppTest.class, TailArrayTest.class })

public class AllTestsSuite {
}
