/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 *
 */


package org.snmp4j;

import java.util.EventListener;

/**
 * <code>CommandResponder</code> process incoming request, report and
 * notification PDUs. An event may only processed once. A command responder
 * must therefore set the <code>processed</code> member of the supplied
 * <code>CommandResponderEvent</code> object to <code>true</code> when it has
 * processed the PDU.
 *
 * @author Jochen Katz & Frank Fock
 * @version 1.0
 */
public interface CommandResponder extends EventListener {

  /**
   * Process an incoming request, report or notification PDU.
   * @param event
   *    a <code>CommandResponderEvent</code> instance containing the PDU to
   *    process and some additional information returned by the message
   *    processing model that decoded the SNMP message.
   */
  void processPdu(CommandResponderEvent event);

}
