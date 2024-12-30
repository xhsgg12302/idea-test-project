package site.wtfu.framework.utils.tls;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsECDH;
import org.bouncycastle.tls.crypto.impl.bc.BcTlsECDomain;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/12/29
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class ExBcTlsECDH extends BcTlsECDH {

    public ExBcTlsECDH(BcTlsECDomain domain) {
        super(domain);
    }

    public void setLocalKeyPair(AsymmetricCipherKeyPair localKeyPair) {
        this.localKeyPair = localKeyPair;
    }
}
