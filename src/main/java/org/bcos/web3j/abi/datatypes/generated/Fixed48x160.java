package org.bcos.web3j.abi.datatypes.generated;

import java.math.BigInteger;
import org.bcos.web3j.abi.datatypes.Fixed;

/**
 * <p>Auto generated code.<br>
 * <strong>Do not modifiy!</strong><br>
 * Please use {@link org.bcos.web3j.codegen.AbiTypesGenerator} to update.</p>
 */
public class Fixed48x160 extends Fixed {
    public static final Fixed48x160 DEFAULT = new Fixed48x160(BigInteger.ZERO);

    public Fixed48x160(BigInteger value) {
        super(48, 160, value);
    }

    public Fixed48x160(int mBitSize, int nBitSize, BigInteger m, BigInteger n) {
        super(48, 160, m, n);
    }
}
