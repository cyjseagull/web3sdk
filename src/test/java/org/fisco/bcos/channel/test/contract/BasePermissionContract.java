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
import org.fisco.bcos.web3j.abi.datatypes.Address;
import org.fisco.bcos.web3j.abi.datatypes.Bool;
import org.fisco.bcos.web3j.abi.datatypes.Event;
import org.fisco.bcos.web3j.abi.datatypes.Function;
import org.fisco.bcos.web3j.abi.datatypes.Type;
import org.fisco.bcos.web3j.abi.datatypes.Utf8String;
import org.fisco.bcos.web3j.crypto.Credentials;
import org.fisco.bcos.web3j.crypto.EncryptType;
import org.fisco.bcos.web3j.protocol.Web3j;
import org.fisco.bcos.web3j.protocol.core.RemoteCall;
import org.fisco.bcos.web3j.protocol.core.methods.response.Log;
import org.fisco.bcos.web3j.protocol.core.methods.response.TransactionReceipt;
import org.fisco.bcos.web3j.tuples.generated.Tuple1;
import org.fisco.bcos.web3j.tuples.generated.Tuple2;
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
public class BasePermissionContract extends Contract {
    public static String BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610bcb806100606000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680631eee9b0d1461006757806361ced04f146100e857806375b8318714610189578063d259183d14610250575b600080fd5b34801561007357600080fd5b506100ce600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610317565b604051808215151515815260200191505060405180910390f35b3480156100f457600080fd5b5061016f600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506104fd565b604051808215151515815260200191505060405180910390f35b34801561019557600080fd5b50610236600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610882565b604051808215151515815260200191505060405180910390f35b34801561025c57600080fd5b506102fd600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506108a1565b604051808215151515815260200191505060405180910390f35b6000606060003373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561037b57600192506104f6565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b82821015610496578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156104825780601f1061045757610100808354040283529160200191610482565b820191906000526020600020905b81548152906001019060200180831161046557829003601f168201915b5050505050815260200190600101906103da565b5050505091506000825111156104f157600090505b81518110156104f0576104d582828151811015156104c557fe5b9060200190602002015185610882565b156104e357600192506104f6565b80806001019150506104ab565b5b600092505b5050919050565b6000806000803373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141515610613576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f7b22636f6465223a2031363030312c226d7367223a22736f7272792c20796f7581526020017f20646f6e27742068617665207065726d697373696f6e20746f206f706572617481526020017f652e227d0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002092506000838054905011156107e85760009150600090505b828054905081101561074b57610730838281548110151561068757fe5b906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156107255780601f106106fa57610100808354040283529160200191610725565b820191906000526020600020905b81548152906001019060200180831161070857829003601f168201915b505050505086610882565b1561073e576001915061074b565b808060010191505061066a565b811515156107e7576040517f08c379a000000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f7b22636f6465223a2031363030322c226d7367223a227065726d697373696f6e81526020017f206d6574686f644e616d6520616c7265616479206578697374732e227d00000081525060400191505060405180910390fd5b5b8285908060018154018082558091505090600182039060005260206000200160009091929091909150908051906020019061082492919061098f565b505082600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020908054610874929190610a0f565b506001935050505092915050565b600060608084915083905061089782826108a1565b9250505092915050565b6000815183511415156108b75760009050610989565b816040518082805190602001908083835b6020831015156108ed57805182526020820191506020810190506020830392506108c8565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916836040518082805190602001908083835b602083101515610954578051825260208201915060208101905060208303925061092f565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161490505b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109d057805160ff19168380011785556109fe565b828001600101855582156109fe579182015b828111156109fd5782518255916020019190600101906109e2565b5b509050610a0b9190610a7f565b5090565b828054828255906000526020600020908101928215610a6e5760005260206000209182015b82811115610a6d5782829080546001816001161561010002031660029004610a5d929190610aa4565b5091600101919060010190610a34565b5b509050610a7b9190610b2b565b5090565b610aa191905b80821115610a9d576000816000905550600101610a85565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610add5780548555610b1a565b82800160010185558215610b1a57600052602060002091601f016020900482015b82811115610b19578254825591600101919060010190610afe565b5b509050610b279190610a7f565b5090565b610b5491905b80821115610b505760008181610b479190610b57565b50600101610b31565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610b7d5750610b9c565b601f016020900490600052602060002090810190610b9b9190610a7f565b5b505600a165627a7a72305820b53f69638f4d4dfde32f58a6fbffdb72046bd3bc9397b1f8e3314b0930d28e7a0029";

    public static final String ABI = "[{\"constant\":true,\"inputs\":[{\"name\":\"_methodName\",\"type\":\"string\"}],\"name\":\"validPermission\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_accountAddress\",\"type\":\"address\"},{\"name\":\"_methodName\",\"type\":\"string\"}],\"name\":\"addPermission\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"a\",\"type\":\"string\"},{\"name\":\"b\",\"type\":\"string\"}],\"name\":\"compareString\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[{\"name\":\"a\",\"type\":\"bytes\"},{\"name\":\"b\",\"type\":\"bytes\"}],\"name\":\"compareBytes\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":false,\"name\":\"isSuccess\",\"type\":\"bool\"},{\"indexed\":false,\"name\":\"desc\",\"type\":\"string\"}],\"name\":\"LogEvent\",\"type\":\"event\"}]";

    public static final TransactionDecoder transactionDecoder = new TransactionDecoder(ABI, BINARY);

    public static String SM_BINARY = "608060405234801561001057600080fd5b50336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550610bcb806100606000396000f300608060405260043610610062576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680634626c650146100675780635f6d91181461012e578063689e4a8a146101cf578063a111107814610250575b600080fd5b34801561007357600080fd5b50610114600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610317565b604051808215151515815260200191505060405180910390f35b34801561013a57600080fd5b506101b5600480360381019080803573ffffffffffffffffffffffffffffffffffffffff169060200190929190803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290505050610336565b604051808215151515815260200191505060405180910390f35b3480156101db57600080fd5b50610236600480360381019080803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506106bb565b604051808215151515815260200191505060405180910390f35b34801561025c57600080fd5b506102fd600480360381019080803590602001908201803590602001908080601f0160208091040260200160405190810160405280939291908181526020018383808284378201915050505050509192919290803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091929192905050506108a1565b604051808215151515815260200191505060405180910390f35b600060608084915083905061032c82826108a1565b9250505092915050565b6000806000803373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614151561044c576040517fc703cb120000000000000000000000000000000000000000000000000000000081526004018080602001828103825260448152602001807f7b22636f6465223a2031363030312c226d7367223a22736f7272792c20796f7581526020017f20646f6e27742068617665207065726d697373696f6e20746f206f706572617481526020017f652e227d0000000000000000000000000000000000000000000000000000000081525060600191505060405180910390fd5b600160008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002092506000838054905011156106215760009150600090505b82805490508110156105845761056983828154811015156104c057fe5b906000526020600020018054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561055e5780601f106105335761010080835404028352916020019161055e565b820191906000526020600020905b81548152906001019060200180831161054157829003601f168201915b505050505086610317565b156105775760019150610584565b80806001019150506104a3565b81151515610620576040517fc703cb1200000000000000000000000000000000000000000000000000000000815260040180806020018281038252603d8152602001807f7b22636f6465223a2031363030322c226d7367223a227065726d697373696f6e81526020017f206d6574686f644e616d6520616c7265616479206578697374732e227d00000081525060400191505060405180910390fd5b5b8285908060018154018082558091505090600182039060005260206000200160009091929091909150908051906020019061065d92919061098f565b505082600160008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000209080546106ad929190610a0f565b506001935050505092915050565b6000606060003373ffffffffffffffffffffffffffffffffffffffff166000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16141561071f576001925061089a565b600160003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020805480602002602001604051908101604052809291908181526020016000905b8282101561083a578382906000526020600020018054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156108265780601f106107fb57610100808354040283529160200191610826565b820191906000526020600020905b81548152906001019060200180831161080957829003601f168201915b50505050508152602001906001019061077e565b50505050915060008251111561089557600090505b815181101561089457610879828281518110151561086957fe5b9060200190602002015185610317565b15610887576001925061089a565b808060010191505061084f565b5b600092505b5050919050565b6000815183511415156108b75760009050610989565b816040518082805190602001908083835b6020831015156108ed57805182526020820191506020810190506020830392506108c8565b6001836020036101000a038019825116818451168082178552505050505050905001915050604051809103902060001916836040518082805190602001908083835b602083101515610954578051825260208201915060208101905060208303925061092f565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600019161490505b92915050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106109d057805160ff19168380011785556109fe565b828001600101855582156109fe579182015b828111156109fd5782518255916020019190600101906109e2565b5b509050610a0b9190610a7f565b5090565b828054828255906000526020600020908101928215610a6e5760005260206000209182015b82811115610a6d5782829080546001816001161561010002031660029004610a5d929190610aa4565b5091600101919060010190610a34565b5b509050610a7b9190610b2b565b5090565b610aa191905b80821115610a9d576000816000905550600101610a85565b5090565b90565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10610add5780548555610b1a565b82800160010185558215610b1a57600052602060002091601f016020900482015b82811115610b19578254825591600101919060010190610afe565b5b509050610b279190610a7f565b5090565b610b5491905b80821115610b505760008181610b479190610b57565b50600101610b31565b5090565b90565b50805460018160011615610100020316600290046000825580601f10610b7d5750610b9c565b601f016020900490600052602060002090810190610b9b9190610a7f565b5b505600a165627a7a723058206c43a82eeba50a963de074fcdccfdb7cd1ab0217febe4d75498f643c1cc7e1d80029";

    public static final String FUNC_VALIDPERMISSION = "validPermission";

    public static final String FUNC_ADDPERMISSION = "addPermission";

    public static final String FUNC_COMPARESTRING = "compareString";

    public static final String FUNC_COMPAREBYTES = "compareBytes";

    public static final Event LOGEVENT_EVENT = new Event("LogEvent", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected BasePermissionContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected BasePermissionContract(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected BasePermissionContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(getBinary(), contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected BasePermissionContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(getBinary(), contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static String getBinary() {
        return (EncryptType.encryptType == EncryptType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static TransactionDecoder getTransactionDecoder() {
        return transactionDecoder;
    }

    public RemoteCall<Boolean> validPermission(String _methodName) {
        final Function function = new Function(FUNC_VALIDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<TransactionReceipt> addPermission(String _accountAddress, String _methodName) {
        final Function function = new Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_accountAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public void addPermission(String _accountAddress, String _methodName, TransactionSucCallback callback) {
        final Function function = new Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_accountAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Collections.<TypeReference<?>>emptyList());
        asyncExecuteTransaction(function, callback);
    }

    public String addPermissionSeq(String _accountAddress, String _methodName) {
        final Function function = new Function(
                FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Address(_accountAddress), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(_methodName)), 
                Collections.<TypeReference<?>>emptyList());
        return createTransactionSeq(function);
    }

    public Tuple2<String, String> getAddPermissionInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple2<String, String>(

                (String) results.get(0).getValue(), 
                (String) results.get(1).getValue()
                );
    }

    public Tuple1<Boolean> getAddPermissionOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_ADDPERMISSION, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());;
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public RemoteCall<Boolean> compareString(String a, String b) {
        final Function function = new Function(FUNC_COMPARESTRING, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.Utf8String(a), 
                new org.fisco.bcos.web3j.abi.datatypes.Utf8String(b)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteCall<Boolean> compareBytes(byte[] a, byte[] b) {
        final Function function = new Function(FUNC_COMPAREBYTES, 
                Arrays.<Type>asList(new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(a), 
                new org.fisco.bcos.web3j.abi.datatypes.DynamicBytes(b)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public List<LogEventEventResponse> getLogEventEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(LOGEVENT_EVENT, transactionReceipt);
        ArrayList<LogEventEventResponse> responses = new ArrayList<LogEventEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            LogEventEventResponse typedResponse = new LogEventEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.isSuccess = (Boolean) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.desc = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public void registerLogEventEventLogFilter(String fromBlock, String toBlock, List<String> otherTopcs, EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,fromBlock,toBlock,otherTopcs,callback);
    }

    public void registerLogEventEventLogFilter(EventLogPushWithDecodeCallback callback) {
        String topic0 = EventEncoder.encode(LOGEVENT_EVENT);
        registerEventLogPushFilter(ABI,BINARY,topic0,callback);
    }

    @Deprecated
    public static BasePermissionContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new BasePermissionContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static BasePermissionContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new BasePermissionContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static BasePermissionContract load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new BasePermissionContract(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static BasePermissionContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new BasePermissionContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<BasePermissionContract> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BasePermissionContract.class, web3j, credentials, contractGasProvider, getBinary(), "");
    }

    public static RemoteCall<BasePermissionContract> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(BasePermissionContract.class, web3j, transactionManager, contractGasProvider, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<BasePermissionContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BasePermissionContract.class, web3j, credentials, gasPrice, gasLimit, getBinary(), "");
    }

    @Deprecated
    public static RemoteCall<BasePermissionContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(BasePermissionContract.class, web3j, transactionManager, gasPrice, gasLimit, getBinary(), "");
    }

    public static class LogEventEventResponse {
        public Log log;

        public Boolean isSuccess;

        public String desc;
    }
}
