/**
 * while using LazyCollection(Extra)
 * If collection hasn't loaded yet, collection.contains(ele) won't load
 * the collection. Instead, it will fire a sql select statement
 * to check whether the element is exist in database.
 * This mechanism is different from a loaded collection, which check existence
 * via hashcode method.
 * therefore, the behavior of Set.add(ele) method can be unpredictable.
 */
package cn.staynoob.postcode.sample2