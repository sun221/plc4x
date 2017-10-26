/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.plc4x.java.profinet.requests;

import org.apache.plc4x.java.profinet.types.FunctionCode;
import org.apache.plc4x.java.profinet.types.MessageType;

public class WriteRequest {

    /*
     * Template for a connection request packet.
     * Parts with "(parameter)" will be customized when using the template.
     * Related Links:
     * - S7 Protocol (http://gmiru.com/article/s7comm/)
     * - ISO Transport Protocol (Class 0) (https://tools.ietf.org/html/rfc905)
     * - ISO on TCP (https://tools.ietf.org/html/rfc1006)
     * - Structure and some constants of a variable read/write request:
     *      https://support.industry.siemens.com/tf/ww/en/posts/classic-style-any-pounter-to-variant-type/126024/?page=0&pageSize=10
     */
    public static final byte[] TEMPLATE = {
        ////////////////////////////////////////////////////
        // RFC 1006 (ISO on TCP)
        (byte) 0x03,                // Version (is always constant 0x03)
        (byte) 0x00,                // Reserved (is always constant 0x00)
        (byte) 0x00, (byte) 0x1f,   // Packet length (including ISOonTCP header)

        ////////////////////////////////////////////////////
        // RFC 905 (ISO Transport Protocol)
        (byte) 0x02,                // Length indicator field
        (byte) 0xf0,                // TPDU Code (First 4 bits, 1111 = Data)
        (byte) 0x80,                // EOT (Bit 8 = 1) / TPDU (All other bits 0)

        ////////////////////////////////////////////////////
        // S7 Protocol
        (byte) 0x32,                // Protocol id for standard S7 protocol
        MessageType.JOB.getCode(),
        (byte) 0x00, (byte) 0x00,   // Reserved
        (byte) 0x08, (byte) 0x00,   // PDU Reference (Request Id, generated by the initiating node)
        (byte) 0x00, (byte) 0x0e,   // Parameter field length (0x000e = 14)
        (byte) 0x00, (byte) 0x00,   // Data field length

        ///////////////////////////////////
        // Parameter field
        FunctionCode.WRITE_VAR.getCode(),
        (byte) 0x01,                // Item count (Read one variable at a time)
        // Item 1
    };

}
