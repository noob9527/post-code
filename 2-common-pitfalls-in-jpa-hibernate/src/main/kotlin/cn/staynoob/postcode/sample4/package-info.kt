/**
 * preUpdate lifecycle event won't be fired if no dirty fields are identified
 * Therefore, change an element of @oneToMany collection will not be considered as update action
 *
 * https://stackoverflow.com/questions/19842541/preupdate-does-not-work-with-spring-data-jpa
 */
package cn.staynoob.postcode.sample4