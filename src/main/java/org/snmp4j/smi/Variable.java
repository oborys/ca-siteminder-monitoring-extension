/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */
package org.snmp4j.smi;

import org.snmp4j.asn1.BERSerializable;

/**
 * The <code>Variable</code> interface defines common attributes of all SNMP
 * variables.
 * <p>
 * Before version 1.8, Variable has been an abstract class which has been
 * renamed to {@link AbstractVariable}.
 *
 * @author Frank Fock
 * @version 1.8
 * @since 1.8
 */
public interface Variable extends Cloneable, Comparable<Variable>, BERSerializable {

  /**
   * This definition of a serialVersionUID for the Variable interface
   * has been made for backward compatibility with SNMP4J 1.7.x or earlier
   * serialized Variable instances.
   */
  long serialVersionUID = 1395840752909725320L;

  boolean equals(Object o);

  int compareTo(Variable o);

  int hashCode();

  /**
   * Clones this variable. Cloning can be used by the SNMP4J API to better
   * support concurrency by creating a clone for internal processing.
   * The content of this object is independent to the content of the clone.
   * Thus, changes to the clone will have no effect to this object.
   *
   * @return
   *    a new instance of this <code>Variable</code> with the same value.
   */
  Object clone();

  /**
   * Gets the ASN.1 syntax identifier value of this SNMP variable.
   * @return
   *    an integer value < 128 for regular SMI objects and a value >= 128
   *    for exception values like noSuchObject, noSuchInstance, and
   *    endOfMibView.
   */
  int getSyntax();

  /**
   * Checks whether this variable represents an exception like
   * noSuchObject, noSuchInstance, and endOfMibView.
   * @return
   *    <code>true</code> if the syntax of this variable is an instance of
   *    <code>Null</code> and its syntax equals one of the following:
   *    <UL>
   *    <LI>{@link SMIConstants#EXCEPTION_NO_SUCH_OBJECT}</LI>
   *    <LI>{@link SMIConstants#EXCEPTION_NO_SUCH_INSTANCE}</LI>
   *    <LI>{@link SMIConstants#EXCEPTION_END_OF_MIB_VIEW}</LI>
   *    </UL>
   */
  boolean isException();

  /**
   * Gets a string representation of the variable.
   * @return
   *    a string representation of the variable's value.
   */
  String toString();

  /**
   * Returns an integer representation of this variable if
   * such a representation exists.
   * @return
   *    an integer value (if the native representation of this variable
   *    would be a long, then the long value will be casted to int).
   * @throws UnsupportedOperationException if an integer representation
   * does not exists for this Variable.
   */
  int toInt();

  /**
   * Returns a long representation of this variable if
   * such a representation exists.
   * @return
   *    a long value.
   * @throws UnsupportedOperationException if a long representation
   * does not exists for this Variable.
   */
  long toLong();


  /**
   * Gets a textual description of this Variable.
   * @return
   *    a textual description like 'Integer32'
   *    as used in the Structure of Management Information (SMI) modules.
   *    '?' is returned if the syntax is unknown.
   */
  String getSyntaxString();

  /**
   * Converts the value of this <code>Variable</code> to a (sub-)index
   * value.
   * @param impliedLength
   *    specifies if the sub-index has an implied length. This parameter applies
   *    to variable length variables only (e.g. {@link OctetString} and
   *    {@link OID}). For other variables it has no effect.
   * @return
   *    an OID that represents this value as an (sub-)index.
   * @throws UnsupportedOperationException
   *    if this variable cannot be used in an index.
   */
  OID toSubIndex(boolean impliedLength);

  /**
   * Sets the value of this <code>Variable</code> from the supplied (sub-)index.
   * @param subIndex
   *    the sub-index OID.
   * @param impliedLength
   *    specifies if the sub-index has an implied length. This parameter applies
   *    to variable length variables only (e.g. {@link OctetString} and
   *    {@link OID}). For other variables it has no effect.
   * @throws UnsupportedOperationException
   *    if this variable cannot be used in an index.
   */
  void fromSubIndex(OID subIndex, boolean impliedLength);

  /**
   * Indicates whether this variable is dynamic. If a variable is dynamic,
   * precautions have to be taken when a Variable is serialized using BER
   * encoding, because between determining the length with
   * {@link #getBERLength()} for encoding enclosing SEQUENCES and the actual
   * encoding of the Variable itself with {@link #encodeBER} changes to the
   * value need to be blocked by synchronization.
   * <p>
   * In order to ensure proper synchronization if a <code>Variable</code> is
   * dynamic, modifications of the variables content need to synchronize on
   * the <code>Variable</code> instance. This can be achieved for the standard
   * SMI Variable implementations for example by
   * <pre>
   *    public static modifyVariable(Integer32 variable, int value)
   *      synchronize(variable) {
   *        variable.setValue(value);
   *      }
   *    }
   * </pre>
   *
   * @return
   *    <code>true</code> if the variable might change its value between
   *    two calls to {@link #getBERLength()} and {@link #encodeBER} and
   *    <code>false</code> if the value is immutable or if its value does
   *    not change while serialization because of measures taken by the
   *    implementor (i.e. variable cloning).
   */
  boolean isDynamic();
}
