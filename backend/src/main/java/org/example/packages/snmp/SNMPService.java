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

    private final String community = "private";
    private final int snmpVersion = SnmpConstants.version2c;
    private final int port = 161;

    public String setPortState(String ipAddress, int portIndex, boolean enable) {
        try {
            Address targetAddress = GenericAddress.parse("udp:" + ipAddress + "/" + port);

            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetAddress);
            target.setRetries(2);
            target.setTimeout(5000);
            target.setVersion(snmpVersion);


            String oid = ".1.3.6.1.2.1.2.2.1.1." + portIndex;
            int state = enable ? 1 : 2;

            PDU pdu = new PDU();
            pdu.add(new VariableBinding(new OID(oid), new Integer32(state)));
            pdu.setType(PDU.SET);

            System.out.println("Enviando SNMP para IP: " + ipAddress + ", Porta: " + port + ", OID: " + oid);

            TransportMapping<UdpAddress> transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();

            ResponseEvent response = snmp.send(pdu, target);

            if (response == null || response.getResponse() == null) {
                return "Erro: Não houve resposta do dispositivo SNMP.";
            }

            if (response.getResponse().getErrorStatus() == PDU.noError) {
                return "Porta " + portIndex + " do switch " + ipAddress + " foi " +
                        (enable ? "habilitada" : "desabilitada") + " com sucesso.";
            } else {
                return "Erro ao alterar o estado da porta: " + response.getResponse().getErrorStatusText();
            }
        } catch (IOException e) {
            return "Erro na comunicação SNMP: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado: " + e.getMessage();
        }
    }
}
