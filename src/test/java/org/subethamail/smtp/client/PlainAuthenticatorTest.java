package org.subethamail.smtp.client;

import java.io.IOException;
import java.util.HashMap;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class PlainAuthenticatorTest {

    @Test
    public void testSuccess() throws IOException {
        HashMap<String, String> extensions = new HashMap<String, String>();
        extensions.put("AUTH", "GSSAPI DIGEST-MD5 PLAIN");
        SmartClient smartClient = Mockito.mock(SmartClient.class);
        Mockito.when(smartClient.getExtensions()).thenReturn(extensions);
        PlainAuthenticator authenticator = new PlainAuthenticator(smartClient, "test", "1234");
        authenticator.authenticate();
        InOrder o = Mockito.inOrder(smartClient);
        o.verify(smartClient).getExtensions();
        o.verify(smartClient).sendAndCheck("AUTH PLAIN AHRlc3QAMTIzNA==");
    }

}
