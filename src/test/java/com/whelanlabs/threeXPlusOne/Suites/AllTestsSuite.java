package com.whelanlabs.threeXPlusOne.Suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.whelanlabs.threeXPlusOne.TailArrayTest;
import com.whelanlabs.threeXPlusOne.UtilsTest;

@RunWith(Suite.class)

@Suite.SuiteClasses({ TailArrayTest.class, UtilsTest.class })

public class AllTestsSuite {
}
