package org.fisco.bcos.channel.test.contract;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.channel.client.TransactionSucCallback;
import org.fisco.bcos.channel.event.filter.EventLogPushWithDecodeCallback;
import org.fisco.bcos.web3j.abi.EventEncoder;
import org.fisco.bcos.web3j.abi.FunctionReturnDecoder;
import org.fisco.bcos.web3j.abi.TypeReference;
import org.fisco.bcos.web3j.abi.datatypes.DynamicBytes;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.generated.Bytes20;
import org.fisco.bcos.web3j.abi.datatypes.generated.Uint256;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
import org.fisco.bcos.web3j.tuples.generated.Tuple3;
import org.fisco.bcos.web3j.tx.Contract;
import org.fisco.bcos.web3j.tx.TransactionManager;
import org.fisco.bcos.web3j.tx.gas.ContractGasProvider;
import org.fisco.bcos.web3j.tx.txdecode.TransactionDecoder;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.fisco.bcos.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version none.
 */
@SuppressWarnings("unchecked")
public class IOHeavy extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b5061084e806100206000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680635acecc78146100725780636531695d14610127578063c315d63e14610168578063d4cd8790146101a9578063d778e2da146101ea575b600080fd5b34801561007e57600080fd5b506100ac60048036038101908080356bffffffffffffffffffffffff1916906020019092919050505061026c565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100ec5780820151818401526020810190506100d1565b50505050905090810190601f1680156101195780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561013357600080fd5b5061016660048036038101908080359060200190929190803590602001909291908035906020019092919050505061033e565b005b34801561017457600080fd5b506101a76004803603810190808035906020019092919080359060200190929190803590602001909291905050506103b6565b005b3480156101b557600080fd5b506101e8600480360381019080803590602001909291908035906020019092919080359060200190929190505050610433565b005b3480156101f657600080fd5b5061026a60048036038101908080356bffffffffffffffffffffffff19169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104b1565b005b6060600080836bffffffffffffffffffffffff19166bffffffffffffffffffffffff191681526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103325780601f1061030757610100808354040283529160200191610332565b820191906000526020600020905b81548152906001019060200180831161031557829003601f168201915b50505050509050919050565b606060008090505b838110156103705761036161035c8287016104fa565b61026c565b91508080600101915050610346565b7f2e8128137e55a67bef5f6fa7e5c6722c5632e21b8c8bcf6df64bc32239dd6a3f8484604051808381526020018281526020019250505060405180910390a15050505050565b606060008090505b838110156103ed576103de6103d960018387890103036104fa565b61026c565b915080806001019150506103be565b7f2e8128137e55a67bef5f6fa7e5c6722c5632e21b8c8bcf6df64bc32239dd6a3f8484604051808381526020018281526020019250505060405180910390a15050505050565b60008090505b8281101561046c5761045f61044f8286016104fa565b61045a83870161050c565b6104b1565b8080600101915050610439565b7fe849f68c74be0ec2d162615e7bc539b752b8e3e7db7ccb69f93eb19c85597f7e8383604051808381526020018281526020019250505060405180910390a150505050565b80600080846bffffffffffffffffffffffff19166bffffffffffffffffffffffff1916815260200190815260200160002090805190602001906104f59291906106e7565b505050565b600061050582610626565b9050919050565b6060600060646040519080825280601f01601f1916602001820160405280156105445781602001602082028038833980820191505090505b509150600090505b60648110156106205760c0604051908101604052806096815260200161078d609691398160328581151561057c57fe5b060181518110151561058a57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f01000000000000000000000000000000000000000000000000000000000000000282828151811015156105e357fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350808060010191505061054c565b50919050565b600080821415610658577f303030303030303030303030303030303030303000000000000000000000000090506106df565b5b60008211156106de57610100816c01000000000000000000000000900481151561067f57fe5b046c010000000000000000000000000290507301000000000000000000000000000000000000006030600a848115156106b457fe5b0601026c010000000000000000000000000281179050600a828115156106d657fe5b049150610659565b5b809050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061072857805160ff1916838001178555610756565b82800160010185558215610756579182015b8281111561075557825182559160200191906001019061073a565b5b5090506107639190610767565b5090565b61078991905b8082111561078557600081600090555060010161076d565b5090565b9056006162636465666768696a6b6c6d6e6f707172737475767778792324255e262a28295f2b5b5d7b7d7c3b3a2c2e2f3c3e3f607e6162636465666768696a6b6c6d6e6f707172737475767778792324255e262a28295f2b5b5d7b7d7c3b3a2c2e2f3c3e3f607e6162636465666768696a6b6c6d6e6f707172737475767778792324255e262a28295f2b5b5d7b7d7c3b3a2c2e2f3c3e3f607ea165627a7a723058202861767af7ce53db740d0f88f3399ba221c21a22a2b52f6d3078365e34a4a9500029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"key\",\"type\":\"bytes20\"}],\"name\":\"get\",\"outputs\":[{\"name\":\"\",\"type\":\"bytes\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"start_key\",\"type\":\"uint256\"},{\"name\":\"size\",\"type\":\"uint256\"},{\"name\":\"signature\",\"type\":\"uint256\"}],\"name\":\"scan\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"start_key\",\"type\":\"uint256\"},{\"name\":\"size\",\"type\":\"uint256\"},{\"name\":\"signature\",\"type\":\"uint256\"}],\"name\":\"revert_scan\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"start_key\",\"type\":\"uint256\"},{\"name\":\"size\",\"type\":\"uint256\"},{\"name\":\"signature\",\"type\":\"uint256\"}],\"name\":\"write\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"key\",\"type\":\"bytes20\"},{\"name\":\"value\",\"type\":\"bytes\"}],\"name\":\"set\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"size\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"signature\",\"type\":\"uint256\"}],\"name\":\"finishWrite\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"size\",\"type\":\"uint256\"},{\"indexed\":false,\"name\":\"signature\",\"type\":\"uint256\"}],\"name\":\"finishScan\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static String SM_BINARY = "608060405234801561001057600080fd5b5061084e806100206000396000f30060806040526004361061006d576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680630eda3a4c146100725780631627bcf91461012757806380b936f614610168578063894a160b146101ea578063a4c3b8dd1461022b575b600080fd5b34801561007e57600080fd5b506100ac60048036038101908080356bffffffffffffffffffffffff1916906020019092919050505061026c565b6040518080602001828103825283818151815260200191508051906020019080838360005b838110156100ec5780820151818401526020810190506100d1565b50505050905090810190601f1680156101195780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561013357600080fd5b5061016660048036038101908080359060200190929190803590602001909291908035906020019092919050505061033e565b005b34801561017457600080fd5b506101e860048036038101908080356bffffffffffffffffffffffff19169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506103bb565b005b3480156101f657600080fd5b50610229600480360381019080803590602001909291908035906020019092919080359060200190929190505050610404565b005b34801561023757600080fd5b5061026a60048036038101908080359060200190929190803590602001909291908035906020019092919050505061047c565b005b6060600080836bffffffffffffffffffffffff19166bffffffffffffffffffffffff191681526020019081526020016000208054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156103325780601f1061030757610100808354040283529160200191610332565b820191906000526020600020905b81548152906001019060200180831161031557829003601f168201915b50505050509050919050565b606060008090505b838110156103755761036661036160018387890103036104fa565b61026c565b91508080600101915050610346565b7fbf9cdcebb79aa6ac5b8e59a19cc597cbee7628b04665c3cae3bf609b2be98f3c8484604051808381526020018281526020019250505060405180910390a15050505050565b80600080846bffffffffffffffffffffffff19166bffffffffffffffffffffffff1916815260200190815260200160002090805190602001906103ff9291906106e7565b505050565b606060008090505b83811015610436576104276104228287016104fa565b61026c565b9150808060010191505061040c565b7fbf9cdcebb79aa6ac5b8e59a19cc597cbee7628b04665c3cae3bf609b2be98f3c8484604051808381526020018281526020019250505060405180910390a15050505050565b60008090505b828110156104b5576104a86104988286016104fa565b6104a383870161050c565b6103bb565b8080600101915050610482565b7febff4f19a94e035413e2f8319123a93f2fdfc4f4b5c255e9cb8fbcb13448bf548383604051808381526020018281526020019250505060405180910390a150505050565b600061050582610626565b9050919050565b6060600060646040519080825280601f01601f1916602001820160405280156105445781602001602082028038833980820191505090505b509150600090505b60648110156106205760c0604051908101604052806096815260200161078d609691398160328581151561057c57fe5b060181518110151561058a57fe5b9060200101517f010000000000000000000000000000000000000000000000000000000000000090047f01000000000000000000000000000000000000000000000000000000000000000282828151811015156105e357fe5b9060200101907effffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff1916908160001a905350808060010191505061054c565b50919050565b600080821415610658577f303030303030303030303030303030303030303000000000000000000000000090506106df565b5b60008211156106de57610100816c01000000000000000000000000900481151561067f57fe5b046c010000000000000000000000000290507301000000000000000000000000000000000000006030600a848115156106b457fe5b0601026c010000000000000000000000000281179050600a828115156106d657fe5b049150610659565b5b809050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061072857805160ff1916838001178555610756565b82800160010185558215610756579182015b8281111561075557825182559160200191906001019061073a565b5b5090506107639190610767565b5090565b61078991905b8082111561078557600081600090555060010161076d565b5090565b9056006162636465666768696a6b6c6d6e6f707172737475767778792324255e262a28295f2b5b5d7b7d7c3b3a2c2e2f3c3e3f607e6162636465666768696a6b6c6d6e6f707172737475767778792324255e262a28295f2b5b5d7b7d7c3b3a2c2e2f3c3e3f607e6162636465666768696a6b6c6d6e6f707172737475767778792324255e262a28295f2b5b5d7b7d7c3b3a2c2e2f3c3e3f607ea165627a7a72305820e655ff2ff0334d1b3b971b7f35d9f608b4987e4beb8379067352c9f91d4d97e70029";

    public static final String FUNC_GET = "get";

    public static final String FUNC_SCAN = "scan";

    public static final String FUNC_REVERT_SCAN = "revert_scan";

    public static final String FUNC_WRITE = "write";

    public static final String FUNC_SET = "set";

    public static final Event FINISHWRITE_EVENT = new Event("finishWrite", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event FINISHSCAN_EVENT = new Event("finishScan", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected IOHeavy(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IOHeavy(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IOHeavy(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IOHeavy(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<byte[]> get(byte[] key) {
        final Function function = new Function(FUNC_GET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes20(key)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicBytes>() {}));
        return executeRemoteCallSingleValueReturn(function, byte[].class);
    }

    public RemoteCall<TransactionReceipt> scan(BigInteger start_key, BigInteger size, BigInteger signature) {
        final Function function = new Function(
                FUNC_SCAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void scan(BigInteger start_key, BigInteger size, BigInteger signature, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SCAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String scanSeq(BigInteger start_key, BigInteger size, BigInteger signature) {
        final Function function = new Function(
                FUNC_SCAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getScanInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SCAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> revert_scan(BigInteger start_key, BigInteger size, BigInteger signature) {
        final Function function = new Function(
                FUNC_REVERT_SCAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void revert_scan(BigInteger start_key, BigInteger size, BigInteger signature, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_REVERT_SCAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String revert_scanSeq(BigInteger start_key, BigInteger size, BigInteger signature) {
        final Function function = new Function(
                FUNC_REVERT_SCAN, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getRevert_scanInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_REVERT_SCAN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> write(BigInteger start_key, BigInteger size, BigInteger signature) {
        final Function function = new Function(
                FUNC_WRITE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void write(BigInteger start_key, BigInteger size, BigInteger signature, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_WRITE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String writeSeq(BigInteger start_key, BigInteger size, BigInteger signature) {
        final Function function = new Function(
                FUNC_WRITE, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(start_key), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(size), 
                new org.fisco.bcos.web3j.abi.datatypes.generated.Uint256(signature)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple3<BigInteger, BigInteger, BigInteger> getWriteInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_WRITE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple3<BigInteger, BigInteger, BigInteger>(

                (BigInteger) results.get(0).getValue(), 
                (BigInteger) results.get(1).getValue(), 
                (BigInteger) results.get(2).getValue()
                );
    }

    public RemoteCall<TransactionReceipt> set(byte[] key, byte[] value) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes20(key), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void set(byte[] key, byte[] value, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes20(key), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(value)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String setSeq(byte[] key, byte[] value) {
        final Function function = new Function(
                FUNC_SET, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.generated.Bytes20(key), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(value)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<byte[], byte[]> getSetInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_SET, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes20>() {}, new TypeReference<DynamicBytes>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<byte[], byte[]>(

                (byte[]) results.get(0).getValue(), 
                (byte[]) results.get(1).getValue()
                );
    }

    public List<FinishWriteEventResponse> getFinishWriteEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINISHWRITE_EVENT, transactionReceipt);
        ArrayList<FinishWriteEventResponse> responses = new ArrayList<FinishWriteEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinishWriteEventResponse typedResponse = new FinishWriteEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.signature = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerfinishWriteEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISHWRITE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerfinishWriteEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISHWRITE_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    public List<FinishScanEventResponse> getFinishScanEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(FINISHSCAN_EVENT, transactionReceipt);
        ArrayList<FinishScanEventResponse> responses = new ArrayList<FinishScanEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FinishScanEventResponse typedResponse = new FinishScanEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.size = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.signature = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerfinishScanEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISHSCAN_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerfinishScanEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(FINISHSCAN_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static IOHeavy load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IOHeavy(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IOHeavy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IOHeavy(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IOHeavy load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IOHeavy(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IOHeavy load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IOHeavy(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IOHeavy> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IOHeavy.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IOHeavy> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IOHeavy.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    public static RemoteCall<IOHeavy> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IOHeavy.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<IOHeavy> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IOHeavy.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }

    public static class FinishWriteEventResponse {
        public Log log;

        public BigInteger size;

        public BigInteger signature;
    }

    public static class FinishScanEventResponse {
        public Log log;

        public BigInteger size;

        public BigInteger signature;
    }
}
