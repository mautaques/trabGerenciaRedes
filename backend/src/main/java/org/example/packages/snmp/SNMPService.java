package org.example.packages.snmp;

import org.snmp4j.*;
import org.snmp4j.smi.*;
import org.snmp4j.mp.*;
import org.snmp4j.transport.*;
import org.snmp4j.event.*;
import org.snmp4j.PDU;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SNMPService {

    private final String community = "public"; // Substitua pela comunidade do seu switch
    private final int snmpVersion = SnmpConstants.version2c; // Use v2c ou v3, dependendo do switch
    private final int port = 161; // Porta padrão SNMP

    public String setPortState(String ipAddress, int portIndex, boolean enable) {
        try {
            Address targetAddress = GenericAddress.parse("udp:" + ipAddress + "/" + port);

            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(1500);
            target.setVersion(snmpVersion);

            String oid = "1.3.6.1.2.1.2.2.1.7." + portIndex; // Base OID do IF-MIB + índice da porta
            int state = enable ? 1 : 2; // 1 = up (habilitada), 2 = down (desabilitada)

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid), new Integer32(state)));
            pdu.setType(PDU.SET);

            // Envia o comando SNMP
            TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();

            ResponseEvent response = snmp.send(pdu, target);
            snmp.close();

            // Verifica a resposta
            if (response.getResponse() != null && response.getResponse().getErrorStatus() == PDU.noError) {
                return "Porta " + portIndex + " do switch " + ipAddress + " foi " +
                        (enable ? "habilitada" : "desabilitada") + " com sucesso.";
            } else {
                return "Erro ao alterar o estado da porta: " + response.getResponse().getErrorStatusText();
            }
        } catch (IOException e) {
            return "Erro na comunicação SNMP: " + e.getMessage();
        }
    }
}