/**
 * this test is meant to reproduce an weird issue
 * If a class implement interface via kotlin's class delegation feature,
 * jackson throws NPE when serialize the instance of that class
 *
 * however, I'm not able to reproduce it in test
 */
package cn.staynoob.postcode.sample6