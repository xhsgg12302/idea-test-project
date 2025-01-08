package site.wtfu.framework.utils.bc.tls;

import org.bouncycastle.tls.*;
import org.bouncycastle.tls.crypto.TlsCrypto;

import java.io.IOException;
import java.util.Vector;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/6
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class CusDefaultTlsClient extends DefaultTlsClient {

    public CusDefaultTlsClient(TlsCrypto crypto) {
        super(crypto);
    }

    @Override
    public TlsAuthentication getAuthentication() throws IOException {
        return new TlsAuthentication() {
            @Override
            public void notifyServerCertificate(TlsServerCertificate serverCertificate) throws IOException {

            }

            @Override
            public TlsCredentials getClientCredentials(CertificateRequest certificateRequest) throws IOException {
                return null;
            }
        };
    }

    @Override
    protected Vector<ServerName> getSNIServerNames() {
        return super.getSNIServerNames();
    }
}
